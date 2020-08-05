package App.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import App.Service.SanPhamService;
import App.entity.DanhMuc;
import App.entity.SanPham;

@Transactional
@Controller
@RequestMapping("/sanpham")
public class SanPhamController {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private SanPhamService sanphamservice;

	// tất cả sản
	// phẩm----------------------------------------------------------------------------------------------------------
	@GetMapping("")
	public String sp(ModelMap model) {
		Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);

		String hql = "FROM SanPham";
		Query query = session.createQuery(hql);
		List<SanPham> list = query.list();

//	        String hql2 = "FROM SanPham";
//	        Query query2 = session.createQuery(hql2);
//	        List<SanPham> viewlist = query2.list(); 

		model.addAttribute("sanphams", list);
		return "redirect:/sanpham/page/1";
	}

	// phân trang tất cả sản
	// phẩm----------------------------------------------------------------------------------------------------------
	@GetMapping("/page/{page}")
	public ModelAndView listArticlesPageByPageAll(@PathVariable("page") int page, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("shop/sanpham/spdanhmuc");
		PageRequest pageable = PageRequest.of(page - 1, 6);
		Page<SanPham> articlePage = sanphamservice.getPaginatedArticles(pageable);
		int totalPages = articlePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		modelAndView.addObject("activesp", true);
		modelAndView.addObject("sanphams", articlePage.getContent());

		// Danh
		// mục-------------------------------------------------------------------------------------
		Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
		String hql4 = "FROM DanhMuc ";
		Query query4 = session.createQuery(hql4);
		query4.setFirstResult(0);
		query4.setMaxResults(4);
		List<DanhMuc> danhmucnam = query4.list();

		List<DanhMuc> danhmuc = sanphamservice.findalldm();
		model.addAttribute("danhmuc", danhmuc);
		model.addAttribute("danhmucnam", danhmucnam);
		// ----------------------------------------------------------------------------------------------

		return modelAndView;
	}

	// sản phẩm
	// nam----------------------------------------------------------------------------------------------------------
	@GetMapping("/men")
	public String men(Model model, HttpServletRequest request, RedirectAttributes redirect) {

		request.getSession().setAttribute("men", null);
		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());

		return "redirect:/sanpham/nam/page/1";
	}

	// phân trang sản phẩm
	// nam----------------------------------------------------------------------------------------------------------
	@GetMapping("/nam/page/{pageNumber}")
	public String pageMen(HttpServletRequest request, @PathVariable int pageNumber, Model model) {

		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("men");
		int pagesize = 6;
		Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);

		String hql = "FROM SanPham S WHERE S.loaiSp=1 ";
		Query query = session.createQuery(hql);
		List<SanPham> list = query.list();
		System.out.println(list.size());

		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("men", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/sanpham/nam/page/";

		// Danh
		// mục-------------------------------------------------------------------------------------
//			Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
		String hql4 = "FROM DanhMuc ";
		Query query4 = session.createQuery(hql4);
		query4.setFirstResult(0);
		query4.setMaxResults(4);
		List<DanhMuc> danhmucnam = query4.list();

		List<DanhMuc> danhmuc = sanphamservice.findalldm();
		model.addAttribute("danhmuc", danhmuc);
		model.addAttribute("danhmucnam", danhmucnam);
		// ----------------------------------------------------------------------------------------------

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("nam", pages);

		return "shop/sanpham/spmen";
	}

	// sản phẩm
	// nữ----------------------------------------------------------------------------------------------------------
	@GetMapping("/women")
	public String women(Model model, HttpServletRequest request, RedirectAttributes redirect) {

		request.getSession().setAttribute("women", null);
		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());

		return "redirect:/sanpham/nu/page/1";
	}

	// phân trang sản phẩm
	// nữ----------------------------------------------------------------------------------------------------------
	@GetMapping("/nu/page/{pageNumber}")
	public String pageWomen(HttpServletRequest request, @PathVariable int pageNumber, Model model) {

		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("women");
		int pagesize = 6;
		Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);

		String hql = "FROM SanPham S WHERE S.loaiSp=2";
		Query query = session.createQuery(hql);
		List<SanPham> list = query.list();
		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("women", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/sanpham/nu/page/";

		// Danh
		// mục-------------------------------------------------------------------------------------
		String hql4 = "FROM DanhMuc ";
		Query query4 = session.createQuery(hql4);
		query4.setFirstResult(0);
		query4.setMaxResults(4);
		List<DanhMuc> danhmucnam = query4.list();

		List<DanhMuc> danhmuc = sanphamservice.findalldm();
		model.addAttribute("danhmuc", danhmuc);
		model.addAttribute("danhmucnam", danhmucnam);
		// ----------------------------------------------------------------------------------------------

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("nu", pages);

		return "shop/sanpham/spwomen";
	}

	// danh mục sản phẩm
	// nam----------------------------------------------------------------------------------------------------------
	@GetMapping("/danhmucNam/nam/{maDm}")
	public String danhmucnam(Model model, HttpServletRequest request, @PathVariable(name = "maDm") Integer maDm,
			RedirectAttributes redirect) {

		request.getSession().setAttribute("dmNam", null);
		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());

		return "redirect:/sanpham/danhmucNam/{maDm}/page/1";
	}

	// phân trang danh mục sản phẩm
	// nam----------------------------------------------------------------------------------------------------------
	@GetMapping("/danhmucNam/{maDm}/page/{pageNumber}")
	public String PageDmNam(HttpServletRequest request, @PathVariable(name = "maDm") Integer maDm,
			@PathVariable int pageNumber, Model model) {

		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("dmNam");
		int pagesize = 6;
		Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);

		String hql = "FROM SanPham u WHERE u.loaiSp=1 and u.danhMuc.maDm=:maDm ";
		Query query = session.createQuery(hql);
		query.setParameter("maDm", maDm);
		List<SanPham> list = query.list();

		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("dmNam", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/sanpham/danhmucNam/" + maDm + "/page/";

		// Danh
		// mục-------------------------------------------------------------------------------------
		String hql4 = "FROM DanhMuc ";
		Query query4 = session.createQuery(hql4);
		query4.setFirstResult(0);
		query4.setMaxResults(4);
		List<DanhMuc> danhmucnam = query4.list();

		List<DanhMuc> danhmuc = sanphamservice.findalldm();
		model.addAttribute("danhmuc", danhmuc);
		model.addAttribute("danhmucnam", danhmucnam);
		// ----------------------------------------------------------------------------------------------

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("danhmucsp", pages);

		return "shop/sanpham/danhmucnam";
	}

	// danh mục sản phẩm
	// nữ----------------------------------------------------------------------------------------------------------
	@GetMapping("/danhmucNu/nu/{maDm}")
	public String danhmucnu(Model model, HttpServletRequest request, @PathVariable(name = "maDm") Integer maDm,
			RedirectAttributes redirect) {

		request.getSession().setAttribute("dmNu", null);
		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());

		return "redirect:/sanpham/danhmucNu/{maDm}/page/1";
	}

	// phân trang danh mục sản phẩm
	// nam----------------------------------------------------------------------------------------------------------
	@GetMapping("/danhmucNu/{maDm}/page/{pageNumber}")
	public String PageDmNu(HttpServletRequest request, @PathVariable(name = "maDm") Integer maDm,
			@PathVariable int pageNumber, Model model) {

		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("dmNu");
		int pagesize = 6;
		Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);

		String hql = "FROM SanPham u WHERE u.loaiSp=2 and u.danhMuc.maDm=:maDm ";
		Query query = session.createQuery(hql);
		query.setParameter("maDm", maDm);
		List<SanPham> list = query.list();

		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("dmNu", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/sanpham/danhmucNu/" + maDm + "/page/";

		// Danh
		// mục-------------------------------------------------------------------------------------
		String hql4 = "FROM DanhMuc ";
		Query query4 = session.createQuery(hql4);
		query4.setFirstResult(0);
		query4.setMaxResults(4);
		List<DanhMuc> danhmucnam = query4.list();

		List<DanhMuc> danhmuc = sanphamservice.findalldm();
		model.addAttribute("danhmuc", danhmuc);
		model.addAttribute("danhmucnam", danhmucnam);
		// ----------------------------------------------------------------------------------------------

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("danhmucsp", pages);

		return "shop/sanpham/danhmucnu";
	}

	// chi tiết sản
	// phẩm----------------------------------------------------------------------------------------------------------
	@GetMapping("/chitiet/{maSp}")
	public String chitiet(ModelMap model, @PathVariable(name = "maSp") Integer maSp) {
		Optional<SanPham> chitiet = sanphamservice.findById(maSp);
		if (chitiet.isPresent()) {
			List<DanhMuc> danhmuc = sanphamservice.findalldm();

			Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
			String hql4 = "FROM DanhMuc ";
			Query query4 = session.createQuery(hql4);
			query4.setFirstResult(0);
			query4.setMaxResults(4);
			List<DanhMuc> danhmucnam = query4.list();

			model.addAttribute("danhmucnam", danhmucnam);
			model.addAttribute("danhmuc", danhmuc);
			model.addAttribute("chitiet", chitiet.get());
		} else {
			return sp(model);
		}

		return "shop/single-product";
	}

	// sản phẩm theo danh mục
	// ----------------------------------------------------------------------------------------------------------
	@GetMapping("/danhmuc/{maDm}")
	public String danhmuc(Model model, HttpServletRequest request, @PathVariable(name = "maDm") Integer maDm,
			RedirectAttributes redirect) {

		request.getSession().setAttribute("dm", null);
		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());

		return "redirect:/sanpham/danhmuc/{maDm}/page/1";
	}

	// phân trang sản phẩm theo danh
	// mục----------------------------------------------------------------------------------------------------------
	@GetMapping("/danhmuc/{maDm}/page/{pageNumber}")
	public String PageDm(HttpServletRequest request, @PathVariable(name = "maDm") Integer maDm,
			@PathVariable int pageNumber, Model model) {

		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("dm");
		int pagesize = 6;
		Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);

		String hql = "FROM SanPham u WHERE u.danhMuc.maDm=:maDm ";
		Query query = session.createQuery(hql);
		query.setParameter("maDm", maDm);
		List<SanPham> list = query.list();

		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("dm", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/sanpham/danhmuc/" + maDm + "/page/";

		// Danh
		// mục-------------------------------------------------------------------------------------
		String hql4 = "FROM DanhMuc ";
		Query query4 = session.createQuery(hql4);
		query4.setFirstResult(0);
		query4.setMaxResults(4);
		List<DanhMuc> danhmucnam = query4.list();

		List<DanhMuc> danhmuc = sanphamservice.findalldm();
		model.addAttribute("danhmuc", danhmuc);
		model.addAttribute("danhmucnam", danhmucnam);
		// ----------------------------------------------------------------------------------------------

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("danhmucsp", pages);

		return "shop/shop-list";
	}

}
