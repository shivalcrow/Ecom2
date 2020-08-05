package App.Controller;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import App.Service.SanPhamService;
import App.entity.DanhMuc;
import App.entity.SanPham;

//@Transactional
@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private SanPhamService sanphamservice;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("")
	public String home(ModelMap model) {
		Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
		String hql = "FROM SanPham S WHERE S.loaiSp=1 ";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(6);
        List<SanPham> listMen = query.list();
        
        String hql2 = "FROM SanPham S WHERE S.loaiSp=2";
        Query query2 = session.createQuery(hql2);
        query2.setFirstResult(0);
        query2.setMaxResults(6);
        List<SanPham> listWomen = query2.list();
        
        String hql3 = "FROM SanPham";
        Query query3 = session.createQuery(hql3);
        query3.setFirstResult(0);
        query3.setMaxResults(3);
        List<SanPham> newcollection = query3.list();
        
        String hql5 = "FROM SanPham ORDER BY luotMuaSp DESC";
        Query query5 = session.createQuery(hql5);
        query5.setFirstResult(0);
        query5.setMaxResults(5);
        List<SanPham> muanhieu = query5.list();
        

        List<DanhMuc> danhmuc = sanphamservice.findalldm();
               
		List<SanPham> listsp = sanphamservice.findAll();
		
		String hql4 = "FROM DanhMuc ";
        Query query4 = session.createQuery(hql4);
        query4.setFirstResult(0);
        query4.setMaxResults(4);
        List<DanhMuc> danhmucnam = query4.list();
     
        model.addAttribute("muanhieu", muanhieu);
        model.addAttribute("danhmucnam", danhmucnam);
		model.addAttribute("danhmuc", danhmuc);
		model.addAttribute("men", listMen);
		model.addAttribute("women", listWomen);
		model.addAttribute("new", newcollection);
		model.addAttribute("sanphams", listsp);
		session.close();
		return "shop/index";
	}
}