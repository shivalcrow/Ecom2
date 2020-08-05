package App.Controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import App.Repository.ConfirmationTokenRepository;
import App.Repository.SanPhamRepository;
import App.Service.KhachHangService;
import App.Service.NhanVienService;
import App.entity.ConfirmationToken;
import App.entity.DanhMuc;
import App.entity.KhachHang;
import App.entity.NhanVien;
import App.entity.SanPham;

@SuppressWarnings("unused")
@Controller

public class DefaultController {
	private KhachHangService KhachHangService;
	@Autowired
	private NhanVienService nhanvienservice;
	@Autowired
	public JavaMailSender emailSender;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ConfirmationTokenRepository confirmationtokenrepository;

	@RequestMapping("/cart")
	public String cart() {
		return "cart/cart";
	}

	@GetMapping("/fogot")
	public String fogot(ModelMap model, NhanVien nv) {
		model.addAttribute("nv", nv);
		return "Dark/page-recoverpw";
	}

	
	@PostMapping("/fogot")
	public String fogots(ModelMap model, @ModelAttribute("nv")NhanVien nv) {

		NhanVien checknv = nhanvienservice.findbyemail(nv.getEmailNv());
		try {
			if (checknv !=null ) {
				ConfirmationToken confirmationtoken = new ConfirmationToken(checknv);
				confirmationtokenrepository.save(confirmationtoken);
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(nv.getEmailNv());
				message.setSubject("Fogot Password");
				message.setText("Xin chào, chúng tôi xác nhận bạn quên mật khẩu hãy nhấp vào link này:  "
						+ "http://localhost:8090/confirm-reset?token=" + confirmationtoken.getConfirmationToken());
				this.emailSender.send(message);
			} else {
				model.addAttribute("message", "Không tìm thấy email!!");
				return "Dark/page-recoverpw";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("nv", nv);
		return "Dark/page-confirm-mail";

	}
	 @RequestMapping(value="/confirm-reset", method= {RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token")String confirmationToken) {
	        ConfirmationToken token = confirmationtokenrepository.findByconfirmationToken(confirmationToken);

	        if (token != null) {
	            NhanVien user = nhanvienservice.findbyemail(token.getNhanvien().getEmailNv());
	           
	            nhanvienservice.save(user);
	            modelAndView.addObject("nv", user);
	            modelAndView.addObject("emailId", user.getEmailNv());
	            modelAndView.setViewName("Dark/resetPassword");
	        } else {
	            modelAndView.addObject("message", "The link is invalid or broken!");
	            modelAndView.setViewName("error");
	        }
	        return modelAndView;
	    }

	    // Endpoint to update a user's password
	    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	    public ModelAndView resetUserPassword(ModelAndView modelAndView, NhanVien user) {
	        if (user.getEmailNv() != null) {
	            // Use email to find user
	           NhanVien tokenUser = nhanvienservice.findbyemail(user.getEmailNv());
	            tokenUser.setMatKhauNv(passwordEncoder.encode(user.getMatKhauNv()));
	            nhanvienservice.save(tokenUser);
	            modelAndView.addObject("message", "Mật khẩu được thanh đổi thành công, bạn có thể đăng nhập");
	            modelAndView.setViewName("NhanVien/page-login");
	        } else {
	            modelAndView.addObject("message","The link is invalid or broken!");
	            modelAndView.setViewName("error");
	        }
	        return modelAndView;
	    }

}
