package App.Repository;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.entity.NhanVien;
import App.entity.SanPham;

@Repository

public interface NhanVienRepository extends CrudRepository<NhanVien,Integer> {
	 Page<NhanVien> findAll(Pageable pageable);
	 NhanVien findBytenDangNhapNv(String username);
	 Page<NhanVien> findBytenNvLike(String tennv,Pageable pageable);
	NhanVien findByemailNv (String email);

}
