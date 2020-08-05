package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import App.entity.*;

public interface HoaDonCTService {

	void deleteAll();

	void deleteAll(List<HoaDonChiTiet> entities);

	void delete(HoaDonChiTiet entity);

	void deleteById(Integer id);

	long count();

	List<HoaDonChiTiet> findAllById(List<Integer> ids);

	List<HoaDonChiTiet> findAll();

	boolean existsById(Integer id);

	Optional<HoaDonChiTiet> findById(Integer id);

	List<HoaDonChiTiet> saveAll(List<HoaDonChiTiet> entities);

	HoaDonChiTiet save(HoaDonChiTiet entity);

	Page<HoaDonChiTiet> getPaginatedArticles(Pageable pageable);

	List<HoaDonChiTiet> findbyHoaDon(HoaDon id);



	


	
	
}
