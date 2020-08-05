package App.Controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import App.DTO.SanPhamDTO;
import App.Service.HoaDonCTService;
import App.Service.HoaDonService;
import App.Service.KhachHangService;

import App.entity.HoaDon;
import App.entity.HoaDonChiTiet;
import App.entity.KhachHang;
import App.entity.PhanQuyen;
import App.entity.TinhTrangHd;

@Controller
@RequestMapping("/employee")
public class NhanVienController {
	@Autowired
	private HoaDonService hoadonservice;
	@Autowired
	private KhachHangService khachhangservice;
	@Autowired
	private HoaDonCTService hoadonchitietservice;

	@GetMapping("")
	public ModelAndView index() {

		return listArticlesPageByPagehd(1);
	}

	@GetMapping("/HoaDon/page/{page}")
	public ModelAndView listArticlesPageByPagehd(@PathVariable("page") Integer page) {
		ModelAndView modelAndView = new ModelAndView("/NhanVien/DonHang");
		PageRequest pageable = PageRequest.of(page - 1, 10,Sort.by("tinhTrangHd"));
		Page<HoaDon> articlePage = hoadonservice.getPaginatedArticles(pageable);
		int totalPages = articlePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		modelAndView.addObject("activehoadon", true);
		modelAndView.addObject("hoadons", articlePage.getContent());
		return modelAndView;
	}

	@GetMapping("HoaDon/detail/{id1}/{id2}")
	public String detail(@PathVariable(name = "id1") HoaDon id1, @PathVariable(name = "id2") Integer id2,
			ModelMap model) {
		double total = 0;
		List<HoaDonChiTiet> list = hoadonchitietservice.findbyHoaDon(id1);
		for (HoaDonChiTiet hdct : list) {
			total += hdct.getTongTienSp();
		}
		Locale localevn = new Locale("vi", "VN");
		NumberFormat vn = NumberFormat.getInstance(localevn);
		String money = vn.format(total);

		HoaDon hd = hoadonservice.findbyId(id2);
		model.addAttribute("detailCT", list);
		model.addAttribute("hd", hd);
		model.addAttribute("total", money);

		return "NhanVien/DonHangCT";
	}

	@GetMapping("/Savedelete/{id}")
	public String saveorupdatehd(ModelMap model, @PathVariable(name = "id") Integer id) {
		HoaDon op = hoadonservice.findbyId(id);
		TinhTrangHd tinhtranghd = new TinhTrangHd();
		tinhtranghd.setIdTinhTrangHd(3);
		op.setTinhTrangHd(tinhtranghd);
		hoadonservice.save(op);
		model.addAttribute(op);

		return "redirect:/employee/HoaDon/page/1";
	}

	@GetMapping("/Saveaccept/{id}")
	public String saveorupdaehd(ModelMap model, @PathVariable(name = "id") Integer id) {
		HoaDon op = hoadonservice.findbyId(id);
		TinhTrangHd tinhtranghd = new TinhTrangHd();
		tinhtranghd.setIdTinhTrangHd(2);
		op.setTinhTrangHd(tinhtranghd);
		hoadonservice.save(op);
		model.addAttribute(op);

		return "redirect:/employee/HoaDon/page/1";
	}

	// Khách
	// Hàng---------------------------------------------------------------------------------------
//	@GetMapping("/KhachHang")
//	public String listkh(ModelMap model) {
//		List<KhachHang> list = khachhangservice.findAll();
//		model.addAttribute("khachhangs", list);
//		return "NhanVien/KhachHang";
//	}
	@GetMapping("/KhachHang/page/{page}")
	public ModelAndView listArticlesPageByPagepq(@PathVariable("page") int page,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
			@RequestParam(defaultValue = "") String name) {
		ModelAndView modelAndView = new ModelAndView("/NhanVien/KhachHang");
		PageRequest pageable = PageRequest.of(page - 1, 10);
		Page<KhachHang> articlePage = khachhangservice.find(name, pageable);
		int totalPages = articlePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		modelAndView.addObject("activekhachhang", true);
		modelAndView.addObject("khachhangs", articlePage.getContent());
		return modelAndView;
	}

	@GetMapping(value = { "/add-kh" })
	public String adddanhmuc(ModelMap model) {
		model.addAttribute("khachhang", new KhachHang());
		return "NhanVien/Form/ThemKhachHang";
	}

	@PostMapping("/SaveOrUpdate-kh")
	public String saveorupdatekh(ModelMap model, @Validated @ModelAttribute("khachhang") KhachHang khachhang,
			BindingResult result, @RequestParam String password2) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Vui Lòng Nhập Tất Cả Thông Tin!!");
			model.addAttribute("khachhang", khachhang);
			return "NhanVien/Form/ThemKhachHang";
		}
		if (khachhang.getMaKh() != null && khachhang.getMaKh() > 0) {
			model.addAttribute("message", "Cập Nhật Khách Hàng!!!");
		} else {
			model.addAttribute("message", "Thêm Khách Hàng!!!");
		}
		if (!password2.equalsIgnoreCase(khachhang.getMatKhauKh())) {
			model.addAttribute("message", "Mật Khẩu Không Trùng Khớp");
			return "NhanVien/Form/ThemKhachHang";
		} else {

			khachhangservice.save(khachhang);
			model.addAttribute(khachhang);
		}
		return "redirect:/employee/KhachHang/page/1";
	}

	@GetMapping("/edit-kh/{id}")
	public String edit(@PathVariable(name = "id") Integer id, ModelMap model) {
		Optional<KhachHang> opt = khachhangservice.findById(id);
		if (opt.isPresent()) {
			model.addAttribute("khachhang", opt.get());
		} else {
			return "redirect:/NhanVien/KhachHang/page/1";
		}
		return "NhanVien/Form/ThemKhachHang";
	}

	@GetMapping("/delete-kh/{id}")
	public String deletekh(ModelMap model, @PathVariable(name = "id") Integer id) {
		khachhangservice.deleteById(id);
		return "redirect:/employee/KhachHang/page/1";
	}

}
