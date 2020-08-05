package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import App.entity.*;

public interface HoaDonService {

	void deleteAll();

	void deleteAll(List<HoaDon> entities);

	void delete(HoaDon entity);

	void deleteById(Integer id);

	long count();

	List<HoaDon> findAllById(List<Integer> ids);

	List<HoaDon> findAll();

	boolean existsById(Integer id);

	Optional<HoaDon> findById(Integer id);

	List<HoaDon> saveAll(List<HoaDon> entities);

	HoaDon save(HoaDon entity);

	Page<HoaDon> getPaginatedArticles(Pageable pageable);

	HoaDon findbyId(Integer id);

	long counthd(TinhTrangHd tinhtrangHd);

	List<HoaDon> findTop5byKhachhang(KhachHang khachhang);



	
	
}
