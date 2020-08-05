package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import App.entity.*;

public interface LoaiSPService {

	void deleteAll();

	void deleteAll(List<LoaiSp> entities);

	void delete(LoaiSp entity);

	void deleteById(Integer id);

	long count();

	List<LoaiSp> findAllById(List<Integer> ids);

	List<LoaiSp> findAll();

	boolean existsById(Integer id);

	Optional<LoaiSp> findById(Integer id);

	/* List<LoaiSp> saveAll(List<LoaiSp> entities); */

	LoaiSp save(LoaiSp entity);

	List<LoaiSp> findByTenDm(String tenDm);

	List<LoaiSp> saveAll(List<LoaiSp> entities);

	Page<LoaiSp> getPaginatedArticles(Pageable pageable);

	
	
}
