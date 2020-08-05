package App.Controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import App.Service.HoaDonCTService;
import App.Service.HoaDonService;
import App.Service.KhachHangService;
import App.Service.SanPhamService;
import App.entity.Cart;
import App.entity.HinhThucThanhToan;
import App.entity.HoaDon;
import App.entity.HoaDonChiTiet;

import App.entity.KhachHang;
import App.entity.SanPham;
import App.entity.TinhTrangHd;

@Controller
@RequestMapping("/cart")
public class CartController {

	
	@Autowired
	private KhachHangService khachhangservice;
	@Autowired
	private HoaDonService hoadonservice;
	@Autowired
	private HoaDonCTService hoadonchitietservice;
	@Autowired
	private SanPhamService sanphamservice;

	@GetMapping("/add/{id}")
	public String add(ModelMap model,HttpSession session,@PathVariable("id")int id) {
		HashMap<Integer, Cart>Cartitems=(HashMap<Integer, Cart>)session.getAttribute("mycartitem");
		if(Cartitems==null) {
			Cartitems=new HashMap<>();
		}
		SanPham sanpham=sanphamservice.findByID(id);
		if(sanpham !=null) {
			if(Cartitems.containsKey(id)) {
				Cart item=Cartitems.get(id);
				item.setSanpham(sanpham);
				item.setQuantity(item.getQuantity() +1);
			}else {
				Cart item=new Cart();
				item.setSanpham(sanpham);
				item.setQuantity(1);
				Cartitems.put(id,item);
			}
		}
		session.setAttribute("mycartitem", Cartitems);
		session.setAttribute("mycarttotal",totalPrice(Cartitems));
		session.setAttribute("mycartnum", Cartitems.size());
		return "redirect:/cart";
	}
	public double totalPrice(HashMap<Integer,Cart> cartitem) {
		int count=0;
		for(Map.Entry<Integer, Cart> list:cartitem.entrySet()) {
			count +=list.getValue().getSanpham().getGiaBan()*list.getValue().getQuantity();
		}
		return count;
	}
	
	
	@GetMapping("/remove/{id}")
	public String remove(ModelMap model, HttpSession session,@PathVariable("id") Integer id) {
		HashMap<Integer, Cart> cartitem=(HashMap<Integer, Cart>)session.getAttribute("mycartitem");
		if(cartitem==null) {
			cartitem=new HashMap<>();
		}
		if(cartitem.containsKey(id)) {
			cartitem.remove(id);
		}
		session.setAttribute("mycartitem", cartitem);
		session.setAttribute("mycarttotal",totalPrice(cartitem));
		session.setAttribute("mycartnum", cartitem.size());
		return "redirect:/cart";
	}

	@PostMapping("/checkout")
	public String checkout(ModelMap model,HttpSession session,@ModelAttribute("hoadon") HoaDon hoadon) {
		HashMap<Integer, Cart> cartitems=(HashMap<Integer, Cart>) session.getAttribute("mycartitem");
		if(cartitems==null) {
			cartitems=new HashMap<>();
		}
		if (session.getAttribute("tenDangNhapKh") == null) {	
			
			return "redirect:../login";
		} else {
			HinhThucThanhToan httt=new HinhThucThanhToan();
			httt.setMaHt(1);
			hoadon.setHinhThucThanhToan(httt);
			//
			/*
			 * int makh= (int) session.getAttribute("maKh");
			 * System.out.println("Max Khach hang" + makh); KhachHang kh=new KhachHang();
			 * kh.setMaKh(makh);
			 */
			hoadon.setKhachHang(khachhangservice.find(session.getAttribute("tenDangNhapKh").toString()));
			//
			hoadon.setThoiGianHd(new Timestamp(new Date().getTime()));
			//
			TinhTrangHd tthd=new TinhTrangHd();
			tthd.setIdTinhTrangHd(1);
			hoadon.setTinhTrangHd(tthd);
			hoadonservice.save(hoadon);
			for(Map.Entry<Integer, Cart> entry: cartitems.entrySet()) {
				HoaDonChiTiet hdct=new HoaDonChiTiet();
				hdct.setHoaDon(hoadon);
				hdct.setSanPham(entry.getValue().getSanpham());
				hdct.setSoLuongSp(entry.getValue().getQuantity());
				hdct.setTongTienSp(totalPrice(cartitems));
				hoadonchitietservice.save(hdct);
				SanPham sanpham=new SanPham();
				Optional<SanPham> sp =sanphamservice.findById(entry.getValue().getSanpham().getMaSp());
				if(sp.isPresent()) {
					sanpham.setChatLieu(entry.getValue().getSanpham().getChatLieu());
					sanpham.setDanhMuc(entry.getValue().getSanpham().getDanhMuc());
					sanpham.setGiaBan(entry.getValue().getSanpham().getGiaBan());
					sanpham.setHinhAnhSp(entry.getValue().getSanpham().getHinhAnhSp());
					sanpham.setLoaiSp(entry.getValue().getSanpham().getLoaiSp());
					sanpham.setMaSp(entry.getValue().getSanpham().getMaSp());
					sanpham.setMoTaSp(entry.getValue().getSanpham().getMoTaSp());
					sanpham.setTenSp(entry.getValue().getSanpham().getTenSp());
					sanpham.setTinhTrang(entry.getValue().getSanpham().getTinhTrang());
					sanpham.setLuotMuaSp(sp.get().getLuotMuaSp()+ entry.getValue().getQuantity());
					sanphamservice.save(sanpham);
				}
				
			}
			cartitems=new HashMap<>();
			session.setAttribute("mycartitem", cartitems);
			session.setAttribute("mycarttotal",0);
			session.setAttribute("mycartnum", 0);
			//
			
			
			
			
			return "shop/thanks";
		}
			}

}
