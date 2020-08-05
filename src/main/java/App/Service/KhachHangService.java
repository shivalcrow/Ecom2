package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import App.entity.KhachHang;

public interface KhachHangService {

	void deleteAll();

	void deleteAll(List<KhachHang> entities);

	void delete(KhachHang entity);

	void deleteById(Integer id);

	long count();

	List<KhachHang> findAllById(List<Integer> ids);

	List<KhachHang> findAll();

	boolean existsById(Integer id);

	Optional<KhachHang> findById(Integer id);

	List<KhachHang> saveAll(List<KhachHang> entities);

	KhachHang save(KhachHang entity);

	Page<KhachHang> getPaginatedArticles(Pageable pageable);
	
	KhachHang findByTenDangNhapKhAndMatKhauKh(String tenDangNhapKh, String matKhauKh);


	
	
	
	public KhachHang find(String tenDangNhapKh);

	Optional<KhachHang> findBytenDangNhapKh(String ten);

	Optional<KhachHang> findByemailKh(String email);

	Page<KhachHang> find(String name, Pageable pageable);
}
