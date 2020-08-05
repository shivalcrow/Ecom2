package App.Repository;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.entity.KhachHang;
import App.entity.SanPham;

@Repository

public interface KhachHangRepository extends CrudRepository<KhachHang,Integer> {
	 Page<KhachHang> findAll(Pageable pageable);
	 KhachHang findByTenDangNhapKh(String tenDangNhapKh);
	KhachHang findByTenDangNhapKhAndMatKhauKh(String tenDangNhapKh, String matKhauKh);
	Optional<KhachHang> findBytenDangNhapKh(String tendangnhap);
	Optional<KhachHang> findByemailKh(String emailKh);
	Page<KhachHang> findByTenKhLikeOrderByTenKh(String tenSp, Pageable pageable);
}
