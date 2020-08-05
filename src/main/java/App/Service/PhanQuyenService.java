package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import App.entity.*;

public interface PhanQuyenService {

	void deleteAll();

	void deleteAll(List<PhanQuyen> entities);

	void delete(PhanQuyen entity);

	void deleteById(Integer id);

	long count();

	List<PhanQuyen> findAllById(List<Integer> ids);

	List<PhanQuyen> findAll();

	boolean existsById(Integer id);

	Optional<PhanQuyen> findById(Integer id);

	List<PhanQuyen> saveAll(List<PhanQuyen> entities);

	PhanQuyen save(PhanQuyen  entity);

	Page<PhanQuyen> getPaginatedArticles(Pageable pageable);



	
	
}
