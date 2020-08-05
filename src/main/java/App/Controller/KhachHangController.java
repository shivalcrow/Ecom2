package App.Controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import App.DTO.DanhMucDTO;
import App.Repository.HoaDonRepository;
import App.Service.HoaDonCTService;
import App.Service.HoaDonService;
import App.Service.KhachHangService;
import App.entity.DanhMuc;
import App.entity.HoaDon;
import App.entity.HoaDonChiTiet;
import App.entity.KhachHang;

@Controller

public class KhachHangController {

	@Autowired
	private KhachHangService khachHangService;
	@Autowired
	private HoaDonService hoadonservice;
	@Autowired
	private HoaDonCTService hoadonchitietservice;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("Khachhang", new KhachHang());
		return "shop/login";
	}

	@GetMapping("myaccount")
	public String myaccount(HttpSession session, ModelMap model) {

		Optional<KhachHang> opt = khachHangService
				.findBytenDangNhapKh(session.getAttribute("tenDangNhapKh").toString());
		if (opt.isPresent()) {
			model.addAttribute("KhachHang", opt.get());
		} else {
			return "redirect:../login";
		}

		List<HoaDon> list = hoadonservice.findTop5byKhachhang(opt.get());
		model.addAttribute("hoadonkh", list);

		return "shop/my-account";
	}

	@GetMapping("detail/hoadon/{id}")
	public String detail(@PathVariable(name = "id") HoaDon id1, ModelMap model) {
		double total = 0;
		List<HoaDonChiTiet> list = hoadonchitietservice.findbyHoaDon(id1);
		for (HoaDonChiTiet hdct : list) {
			total += hdct.getTongTienSp();
		}
		Locale localevn = new Locale("vi", "VN");
		NumberFormat vn = NumberFormat.getInstance(localevn);
		String money = vn.format(total);

		model.addAttribute("detailCT2", list);

		model.addAttribute("total2", money);
		return "shop/thongtinhoadon";
	}

	@PostMapping("/Update-KhachHang")
	public String saveorupdate(ModelMap model, @Validated @ModelAttribute("KhachHang") KhachHang KhachHang,
			BindingResult result) {
		KhachHang kh=new KhachHang();
		kh.setTenKh(KhachHang.getTenKh());
		kh.setDiaChiKh(KhachHang.getDiaChiKh());
		kh.setDiemTichLuyKh(KhachHang.getDiemTichLuyKh());
		kh.setEmailKh(KhachHang.getEmailKh());
		kh.setMaKh(KhachHang.getMaKh());
		kh.setMatKhauKh(KhachHang.getMatKhauKh());
		kh.setNgaySinhKh(KhachHang.getNgaySinhKh());
		kh.setSoDienThoaiKh(KhachHang.getSoDienThoaiKh());
		kh.setTenDangNhapKh(KhachHang.getTenDangNhapKh());
		
		
		khachHangService.save(kh);

		model.addAttribute("KhachHang", KhachHang);
		model.addAttribute("message", "Cập Nhật Thông Tin!!!");
		return "shop/my-account";
	}

	@PostMapping("/dangky")
	public String saveorupdatekh(ModelMap model, @Validated @ModelAttribute("Khachhang") KhachHang khachhang,
			BindingResult result, @RequestParam String password2) {
		Optional<KhachHang> kh = khachHangService.findByemailKh(khachhang.getEmailKh());
		if (kh.isPresent()) {
			model.addAttribute("message", "Email đã có người sử dụng");
			return "shop/login";
		}
		if (result.hasErrors()) {
			model.addAttribute("message", "Vui Lòng Nhập Tất Cả Thông Tin!!");
			model.addAttribute("Khachhang", khachhang);
			return "shop/login";
		}
		if (khachhang.getMaKh() != null && khachhang.getMaKh() > 0) {
			model.addAttribute("message", "Cập Nhật Khách Hàng!!!");
		} else {
			model.addAttribute("message", "Thêm Khách Hàng!!!");
		}

		if (!password2.equalsIgnoreCase(khachhang.getMatKhauKh())) {

			model.addAttribute("message", "Mật Khẩu Không Trùng Khớp");
			return "shop/login";
		} else {

			khachHangService.save(khachhang);
			model.addAttribute(khachhang);
		}

		return "shop/login";
	}

	@RequestMapping(value = "logout-account", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("maKh");
		session.removeAttribute("tenDangNhapKh");
		session.removeAttribute("tenKh");
		session.removeAttribute("emailKh");
		session.removeAttribute("diaChiKh");
		session.removeAttribute("soDienThoaiKh");

		return "redirect:/home";

	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute("Khachhang") KhachHang khachhang, ModelMap modelMap, HttpSession session,
			HttpServletRequest re, ModelMap model) {
		KhachHang kh = khachHangService.findByTenDangNhapKhAndMatKhauKh(khachhang.getTenDangNhapKh(),
				khachhang.getMatKhauKh());
		if (kh != null) {
			session.setAttribute("maKh", khachhang.getMaKh());
			session.setAttribute("tenDangNhapKh", khachhang.getTenDangNhapKh());
			session.setAttribute("tenKh", kh.getTenKh());
			session.setAttribute("emailKh", kh.getEmailKh());
			session.setAttribute("diaChiKh", kh.getDiaChiKh());
			session.setAttribute("soDienThoaiKh", kh.getSoDienThoaiKh());

			return "cart/cart";
		} else {
			model.addAttribute("messagelogin", "Đăng nhập thất bại");
			return "shop/login";
		}
	}

	@GetMapping("changepass")
	public String change() {
		return "shop/changepassword";
	}

	@PostMapping("changepass")
	public String change(ModelMap model, @ModelAttribute("khachhang") KhachHang khachhang, @RequestParam String passs1,
			@RequestParam String passs2, @RequestParam String passs3, HttpSession session) {

		Optional<KhachHang> opt = khachHangService
				.findBytenDangNhapKh(session.getAttribute("tenDangNhapKh").toString());
		System.out.println(opt.get().getMatKhauKh());
		if (opt.isPresent()) {
			if (opt.get().getMatKhauKh().equalsIgnoreCase(passs1)) {
				if (!opt.get().getMatKhauKh().equalsIgnoreCase(passs2)) {
					if (passs2.equalsIgnoreCase(passs3)) {
						KhachHang kh = new KhachHang();
						kh.setDiaChiKh(opt.get().getDiaChiKh());
						kh.setDiemTichLuyKh(opt.get().getDiemTichLuyKh());
						kh.setEmailKh(opt.get().getEmailKh());
						kh.setMaKh(opt.get().getMaKh());
						kh.setNgaySinhKh(opt.get().getNgaySinhKh());
						kh.setSoDienThoaiKh(opt.get().getSoDienThoaiKh());
						kh.setTenDangNhapKh(opt.get().getTenDangNhapKh());
						kh.setTenKh(opt.get().getTenKh());
						kh.setMatKhauKh(passs2);
						khachHangService.save(kh);
						model.addAttribute("message", "Đổi Mật Khẩu Thành Công");
					} else {
						model.addAttribute("message", "Xác Nhận Mật Khẩu Không Khớp");
					}
				} else {
					model.addAttribute("message", "Mật Khẩu Mới Trùng Mật Khẩu Cũ");
				}
			} else {
				model.addAttribute("message", "Sai Mật Khẩu");
			}
		} else {
			return "redirect:../login";
		}
		return "shop/changepassword";

	}

}