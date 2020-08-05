package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import App.entity.*;

public interface TinhTrangService {

	void deleteAll();

	void deleteAll(List<TinhTrang> entities);

	void delete(TinhTrang entity);

	void deleteById(Integer id);

	long count();

	List<TinhTrang> findAllById(List<Integer> ids);

	List<TinhTrang> findAll();

	boolean existsById(Integer id);

	Optional<TinhTrang> findById(Integer id);

	List<TinhTrang> saveAll(List<TinhTrang> entities);

	TinhTrang save(TinhTrang entity);

	List<TinhTrang> findByTenDm(String tenDm);

	Page<TinhTrang> getPaginatedArticles(Pageable pageable);

	
	
}
