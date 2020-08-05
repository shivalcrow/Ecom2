package App.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.entity.HoaDon;
import App.entity.HoaDonChiTiet;
import App.entity.SanPham;

@Repository

public interface HoaDonCTRepository extends CrudRepository<HoaDonChiTiet, Integer> {

	List<HoaDonChiTiet> findByHoaDon(HoaDon hoaDon);

	Page<HoaDonChiTiet> findAll(Pageable pageable);
	
	

}
