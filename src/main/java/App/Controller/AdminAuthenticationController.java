package App.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class AdminAuthenticationController {
//	@RequestMapping("/admin" )
//	public String loginad(@RequestParam(required = false) String message, final Model model) {
//	    if (message != null && !message.isEmpty()) {
//	        if (message.equals("logout")) {
//	          model.addAttribute("message", "Logout!");
//	        }
//	        if (message.equals("error")) {
//	          model.addAttribute("message", "Login Failed!");
//	        }
//	      }
//
//		return "Dark/page-login";
//	
//}
	@RequestMapping("/login/employee")
	public String loginmn(@RequestParam(required = false) String message, final Model model) {
	    if (message != null && !message.isEmpty()) {
	        if (message.equals("logout")) {
	          model.addAttribute("message", "Logout!");
	        }
	        if (message.equals("error")) {
	          model.addAttribute("message", "Login Failed!");
	        }
	      }

		return "NhanVien/page-login";
	
}
	@RequestMapping("/403")
	public String ero() {
		return "403";
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login/employee";
	}

}
	