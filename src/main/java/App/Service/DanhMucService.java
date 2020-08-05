package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import App.entity.*;

public interface DanhMucService {

	void deleteAll();

	void deleteAll(List<DanhMuc> entities);

	void delete(DanhMuc entity);

	void deleteById(Integer id);

	long count();

	List<DanhMuc> findAllById(List<Integer> ids);

	List<DanhMuc> findAll();

	boolean existsById(Integer id);

	Optional<DanhMuc> findById(Integer id);

	List<DanhMuc> saveAll(List<DanhMuc> entities);

	DanhMuc save(DanhMuc entity);

	List<DanhMuc> findByTenDm(String tenDm);

	Page<DanhMuc> getPaginatedArticles(Pageable pageable);

	
	
}
