package App.Controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Page;
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

import com.fasterxml.jackson.databind.util.JSONPObject;

import App.DTO.DanhMucDTO;
import App.DTO.NhanVienDTO;
import App.DTO.PhanQuyenDTO;
import App.DTO.SanPhamDTO;
import App.Repository.ConfirmationTokenRepository;
import App.Service.DanhMucService;
import App.Service.HoaDonService;
import App.Service.NhanVienService;
import App.Service.PhanQuyenService;
import App.Service.SanPhamService;
import App.entity.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private DanhMucService danhmucservice;
	@Autowired
	private SanPhamService sanphamservice;
	@Autowired
	private NhanVienService nhanvienservice;
	@Autowired
	private PhanQuyenService phanquyenservice;
	@Autowired
	private HoaDonService hoadonservice;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	// Index--------------------------------------------------------------------------
	@RequestMapping("")
	public String indexAdmin(ModelMap model) {
		try {
			List<DanhMuc> list1 = danhmucservice.findAll();
			List<SanPham> list = sanphamservice.findAll();
			DanhMuc dm = new DanhMuc();
			Map<String, Integer> countMap = new LinkedHashMap<>();
			long count = 0;
			String namedm = "";
			for (DanhMuc iddm : list1) {
				Integer iddmm = iddm.getMaDm();
				namedm = iddm.getTenDm();
				dm.setMaDm(iddmm);
				for (SanPham sp : list) {
					count = sanphamservice.countBydanhMuc(dm);
				}
				countMap.put(namedm, (int) count);
			}

			model.addAttribute("countMap", countMap);

			TinhTrangHd tthd = new TinhTrangHd();
			tthd.setIdTinhTrangHd(1);
			long counthd1 = hoadonservice.counthd(tthd);
			model.addAttribute("tinhtrang1", counthd1);
			TinhTrangHd tthd1 = new TinhTrangHd();
			tthd1.setIdTinhTrangHd(2);
			long counthd2 = hoadonservice.counthd(tthd1);
			model.addAttribute("tinhtrang2", counthd2);
			TinhTrangHd tthd2 = new TinhTrangHd();
			tthd2.setIdTinhTrangHd(3);

			long counthd3 = hoadonservice.counthd(tthd2);
			model.addAttribute("tinhtrang3", counthd3);
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}
		return "Dark/index";
	}

	// DanhMuc-------------------------------------------------------------------
	@GetMapping("/DanhMuc/page/{page}")
	public ModelAndView listArticlesPageByPagedm(@PathVariable("page") int page) {
		ModelAndView modelAndView = new ModelAndView("/Dark/DanhMuc");

		PageRequest pageable = PageRequest.of(page - 1, 5);
		Page<DanhMuc> articlePage = danhmucservice.getPaginatedArticles(pageable);
		int totalPages = articlePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		modelAndView.addObject("activedanhmuc", true);
		modelAndView.addObject("danhmucs", articlePage.getContent());
		return modelAndView;
	}

	@GetMapping("/add-dm")
	public String adddanhmuc(ModelMap model) {
		model.addAttribute("danhmucdto", new DanhMuc());
		return "Dark/form/ThemDanhMuc";
	}

	@PostMapping("/SaveOrUpdate-dm")
	public String saveorupdate(ModelMap model, @Validated @ModelAttribute("danhmucdto") DanhMucDTO danhmucdto,
			BindingResult bind) {
		if (bind.hasErrors()) {
			model.addAttribute("message", "Vui Lòng Nhập Tất Cả Thông Tin!!");
			model.addAttribute("danhmucdto", danhmucdto);
			return "Dark/form/ThemDanhMuc";
		}
		if (danhmucdto.getMaDm() != null && danhmucdto.getMaDm() > 0) {
			model.addAttribute("message", "Cập Nhật Danh Mục!!!");
		} else {
			model.addAttribute("message", "Thêm Danh Mục!!!");
		}
		DanhMuc dm = new DanhMuc();
		dm.setMaDm(danhmucdto.getMaDm());
		dm.setTenDm(danhmucdto.getTenDm());
		danhmucservice.save(dm);
		model.addAttribute("danhmucdto", danhmucdto);
		return "Dark/form/ThemDanhMuc";
	}

	@GetMapping("/edit-dm/{id}")
	public String edit(@PathVariable(name = "id") Integer id, ModelMap model) {
		Optional<DanhMuc> opt = danhmucservice.findById(id);
		if (opt.isPresent()) {
			model.addAttribute("danhmucdto", opt.get());
		} else {
			return "redirect:/admin/SanPham/page/1";
		}
		return "Dark/form/ThemDanhMuc";
	}

	@GetMapping("/delete-dm/{id}")
	public String delete(ModelMap model, @PathVariable(name = "id") Integer id) {
		danhmucservice.deleteById(id);
		return "redirect:/admin/DanhMuc/page/1";
	}

//	@GetMapping("/DanhMuc")
//	public String listDM(ModelMap model) {
//		List<DanhMuc> list = danhmucservice.findAll();
//		model.addAttribute("danhmucs", list);
//		return "Dark/DanhMuc";
//	}
	// SanPham---------------------------------------------------------------------------------
	/*
	 * @GetMapping("/SanPham") public String listSP(ModelMap model) { List<SanPham>
	 * listsp = sanphamservice.findAll(); model.addAttribute("sanphams", listsp);
	 * return "Dark/SanPham"; }
	 * 
	 */

	@GetMapping("/SanPham/page/{page}")
	public ModelAndView listArticlesPageByPage(@PathVariable("page") int page,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
			@RequestParam(defaultValue = "") String name) {
		ModelAndView modelAndView = new ModelAndView("/Dark/SanPham");
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("tenSp").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("tenSp").descending();
		}
		PageRequest pageable = PageRequest.of(page - 1, 10, sortable);
		Page<SanPham> articlePage = sanphamservice.find(name, pageable);
		int totalPages = articlePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}

		modelAndView.addObject("activesanpham", true);
		modelAndView.addObject("sanphams", articlePage.getContent());
		return modelAndView;
	}

	@GetMapping("/add-sp")
	public String addsp(ModelMap model) {
		SanPhamDTO sp = new SanPhamDTO();

		model.addAttribute("sanphamdto", sp);
		return "Dark/form/ThemSanPham";
	}

	@PostMapping("/SaveOrUpdate-sp")
	public String saveorupdatsp(ModelMap model, @Validated @ModelAttribute("sanphamdto") SanPhamDTO sanphamdto,
			BindingResult result) {
		try {
			if (result.hasErrors()) {

				model.addAttribute("message", "Vui Lòng Nhập Tất Cả Thông Tin!!");
				model.addAttribute("sanphamdto", sanphamdto);
				return "Dark/form/ThemSanPham";
			}

			Path path = Paths.get("images/");
			try (InputStream inputstream = sanphamdto.getHinhAnhSp().getInputStream()) {
				Files.copy(inputstream, path.resolve(sanphamdto.getHinhAnhSp().getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				String filename = sanphamdto.getHinhAnhSp().getOriginalFilename();
			} catch (Exception e) {

				e.printStackTrace();
				model.addAttribute("message", "error" + e.getMessage());
			}
			SanPham sanpham = new SanPham();
			sanpham.setMaSp(sanphamdto.getMaSp());

			sanpham.setTenSp(sanphamdto.getTenSp());

			ChatLieu chatlieu = new ChatLieu();
			chatlieu.setMaCl(sanphamdto.getChatLieu());
			DanhMuc danhmuc = new DanhMuc();
			danhmuc.setMaDm(sanphamdto.getDanhMuc());
			LoaiSp loaisp = new LoaiSp();
			loaisp.setIdLoaiSp(sanphamdto.getLoaiSp());
			TinhTrang tinhtrang = new TinhTrang();
			tinhtrang.setIdTinhTrang(sanphamdto.getTinhTrang());
			sanpham.setGiaBan(sanphamdto.getGiaBan());

			sanpham.setChatLieu(chatlieu);
			sanpham.setDanhMuc(danhmuc);
			sanpham.setLoaiSp(loaisp);
			sanpham.setTinhTrang(tinhtrang);
			sanpham.setMoTaSp(sanphamdto.getMoTaSp());
			if (!sanphamdto.getHinhAnhSp().isEmpty() && sanphamdto.getHinhAnhSp().getOriginalFilename() != null) {
				sanpham.setHinhAnhSp(sanphamdto.getHinhAnhSp().getOriginalFilename());
			} else {
				Optional<SanPham> sp = sanphamservice.findById(sanphamdto.getMaSp());
				if (sp.isPresent())
					sanpham.setHinhAnhSp(sp.get().getHinhAnhSp());
			}

			if (sanphamdto.getMaSp() != null && sanphamdto.getMaSp() > 0) {

				model.addAttribute("message", "Cập Nhật Sản Phẩm");
				sanphamservice.save(sanpham);

			} else {
				Optional<SanPham> sp = sanphamservice.findbyTen(sanpham.getTenSp());
				if (sp.isPresent()) {
					model.addAttribute("message", "Sản Phẩm Đã Tồn Tại");
					return "Dark/form/ThemSanPham";
				} else {
					sanphamservice.save(sanpham);
					model.addAttribute("message", "Thêm Sản Phẩm");
					return "Dark/form/ThemSanPham";
				}
			}
			model.addAttribute("sanphamdto", sanphamdto);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Thất Bại" + e);
		}
		return "Dark/form/ThemSanPham";
	}

	@GetMapping("/edit-sp/{id}")
	public String editsp(@PathVariable(name = "id") Integer id, ModelMap model) {
		Optional<SanPham> opt = sanphamservice.findById(id);
		if (opt.isPresent()) {
			model.addAttribute("sanphamdto", opt.get());
		} else {
			return "redirect:/admin/SanPham/page/1";
		}
		return "Dark/form/ThemSanPham";
	}

	@GetMapping("/delete-sp/{id}")
	public String deletesp(ModelMap model, @PathVariable(name = "id") Integer id) {
		sanphamservice.deleteById(id);
		return "redirect:/admin/SanPham/page/1";
	}

	@ModelAttribute(name = "chatlieus")
	public List<ChatLieu> getchatlieu() {
		return sanphamservice.findallcl();
	}

	@ModelAttribute(name = "tinhtrangs")
	public List<TinhTrang> gettinhtrang() {
		return sanphamservice.findalltt();
	}

	@ModelAttribute(name = "danhmucss")
	public List<DanhMuc> getdanhmuc() {
		return sanphamservice.findalldm();
	}

	@ModelAttribute(name = "loaisps")
	public List<LoaiSp> getloaisp() {
		return sanphamservice.findallloaisp();
	}

	// NhanVien-----------------------------------------------------------------------------
//	@GetMapping("/NhanVien")
//	public String listNV(ModelMap model) {
//		List<NhanVien> list=nhanvienservice.findAll();
//		model.addAttribute("nhanvien", list);
//		return "Dark/NhanVien";
//	}
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/NhanVien/page/{page}")
	public ModelAndView listArticlesPageByPagenv(@PathVariable("page") int page,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
			@RequestParam(defaultValue = "") String name) {
		ModelAndView modelAndView = new ModelAndView("/Dark/NhanVien");
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("tenNv").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("tenNv").descending();
		}
		PageRequest pageable = PageRequest.of(page - 1, 5, sortable);
		Page<NhanVien> articlePage = nhanvienservice.getPaginatednv(name, pageable);
		int totalPages = articlePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		modelAndView.addObject("activenhanvien", true);
		modelAndView.addObject("nhanviens", articlePage.getContent());
		return modelAndView;
	}

	@GetMapping("/add-nv")
	public String addnhanvien(ModelMap model) {
		NhanVien nv = new NhanVien();
//		

		model.addAttribute("nhanviendto", nv);
		return "Dark/form/ThemNhanVien";
	}

	@PostMapping("/SaveOrUpdate-nv")
	public String saveorupdatenv(ModelMap model, @Validated @ModelAttribute("nhanviendto") NhanVienDTO nhanviendto,
			BindingResult bind) {
		try {
			if (bind.hasErrors()) {
				model.addAttribute("message", "Vui Lòng Nhập Tất Cả Thông Tin!!");
				model.addAttribute("sanphamdto", nhanviendto);
				return "Dark/form/ThemNhanVien";
			}
			NhanVien nhanvien = nhanvienservice.findbyemail(nhanviendto.getEmailNv());

			NhanVien nv = new NhanVien();
			PhanQuyen pq = new PhanQuyen();
			nv.setMaNv(nhanviendto.getMaNv());
			nv.setCmndNv(nhanviendto.getCmndNv());
			nv.setDiaChiNv(nhanviendto.getDiaChiNv());
			nv.setEmailNv(nhanviendto.getEmailNv());
			nv.setMatKhauNv(passwordEncoder.encode(nhanviendto.getMatKhauNv()));
			nv.setNgaySinhNv(nhanviendto.getNgaySinhNv());
			pq.setMaPhanQuyen(nhanviendto.getPhanQuyen());
			nv.setPhanQuyen(pq);
			nv.setSdtNv(nhanviendto.getSdtNv());
			nv.setTenDangNhapNv(nhanviendto.getTenDangNhapNv());
			nv.setTenNv(nhanviendto.getTenNv());
			if (nhanviendto.getMaNv() != null && nhanviendto.getMaNv() > 0) {
				model.addAttribute("message", "Cập Nhật Nhân Viên");
				nhanvienservice.save(nv);
				ConfirmationToken confirmationtoken = new ConfirmationToken(nv);
				confirmationTokenRepository.save(confirmationtoken);
			} else {
				model.addAttribute("message", "Thêm Nhân Viên");
				if (nhanvien != null) {
					model.addAttribute("message", "Email đã có người sử dụng!");
					return "Dark/form/ThemNhanVien";
				}
				nhanvienservice.save(nv);
				ConfirmationToken confirmationtoken = new ConfirmationToken(nv);
				confirmationTokenRepository.save(confirmationtoken);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Dark/form/ThemNhanVien";
	}

	@GetMapping("/edit-nv/{id}")
	public String editnv(@PathVariable(name = "id") Integer id, ModelMap model) {
		Optional<NhanVien> opt = nhanvienservice.findById(id);
		if (opt.isPresent()) {
			model.addAttribute("nhanviendto", opt.get());
			System.out.println(opt.get().getPhanQuyen().getTenPhanQuyen());
		} else {
			return "redirect:/admin/NhanVien/page/1";
		}
		return "Dark/form/ThemNhanVien";
	}

	@GetMapping("/delete-nv/{id}")
	public String deletenv(ModelMap model, @PathVariable(name = "id") Integer id) {
		nhanvienservice.deleteById(id);
		return "redirect:/admin/NhanVien/page/1";
	}

	@ModelAttribute(name = "phanquyens")
	public List<PhanQuyen> getPhanQuyen() {
		return nhanvienservice.findall();
	}

	// Phân
	// Quyền----------------------------------------------------------------------------------------
//	@GetMapping("/PhanQuyen")
//	public String listPQ(ModelMap model) {
//		List<PhanQuyen> list = phanquyenservice.findAll();
//		model.addAttribute("phanquyens", list);
//		return "Dark/PhanQuyen";
//	}
	@GetMapping("/PhanQuyen/page/{page}")
	public ModelAndView listArticlesPageByPagepq(@PathVariable("page") int page,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		ModelAndView modelAndView = new ModelAndView("/Dark/PhanQuyen");
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("tenPhanQuyen").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("tenPhanQuyen").descending();
		}
		PageRequest pageable = PageRequest.of(page - 1, 5, sortable);
		Page<PhanQuyen> articlePage = phanquyenservice.getPaginatedArticles(pageable);
		int totalPages = articlePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		modelAndView.addObject("activephanquyen", true);
		modelAndView.addObject("phanquyens", articlePage.getContent());
		return modelAndView;
	}

	@GetMapping("/add-pq")
	public String addphanquyen(ModelMap model) {
		model.addAttribute("phanquyendto", new PhanQuyen());
		return "Dark/form/ThemPhanQuyen";
	}

	@PostMapping("/SaveOrUpdate-pq")
	public String saveorupdatepq(ModelMap model, @Validated @ModelAttribute("phanquyendto") PhanQuyenDTO phanquyendto,
			BindingResult bind) {
		if (bind.hasErrors()) {
			model.addAttribute("message", "Vui Lòng Nhập Tất Cả Thông Tin!!");
			model.addAttribute("phanquyendtodto", phanquyendto);
			return "Dark/form/ThemPhanQuyen";
		}
		PhanQuyen pq = new PhanQuyen();
		pq.setMaPhanQuyen(phanquyendto.getMaPhanQuyen());
		pq.setTenPhanQuyen(phanquyendto.getTenPhanQuyen());
		phanquyenservice.save(pq);
		model.addAttribute(phanquyendto);

		return "redirect:/admin/PhanQuyen/page/1";
	}

	@GetMapping("/edit-pq/{id}")
	public String editpq(@PathVariable(name = "id") Integer id, ModelMap model) {
		Optional<PhanQuyen> opt = phanquyenservice.findById(id);
		if (opt.isPresent()) {
			model.addAttribute("phanquyendto", opt.get());
		} else {

			return "redirect:/admin/PhanQuyen/page/1";
		}
		return "Dark/form/ThemPhanQuyen";
	}

	@GetMapping("/delete-pq/{id}")
	public String deletepq(ModelMap model, @PathVariable(name = "id") Integer id) {
		phanquyenservice.deleteById(id);
		model.addAttribute("message", "Xóa Thành Công");
		return "redirect:/admin/PhanQuyen/page/1";

	}

}
