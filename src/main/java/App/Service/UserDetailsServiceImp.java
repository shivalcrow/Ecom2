package App.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import App.Repository.NhanVienRepository;

import App.entity.NhanVien;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	@Autowired
	private NhanVienRepository nhanVienRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		NhanVien user=nhanVienRepository.findBytenDangNhapNv(username);
		if(user==null) throw new UsernameNotFoundException("Tài Khoản Không Tồn Tại");
		
		List<GrantedAuthority> authoritie =new ArrayList<GrantedAuthority>();
		String rolename=user.getPhanQuyen().getTenPhanQuyen();
		authoritie.add(new SimpleGrantedAuthority(rolename));
		
		return new User(user.getTenDangNhapNv(),user.getMatKhauNv(),authoritie);
		
	}
	

}
