package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import App.entity.*;

public interface ChatLieuService {

	void deleteAll();

	void deleteAll(List<ChatLieu> entities);

	void delete(ChatLieu entity);

	void deleteById(Integer id);

	long count();

	List<ChatLieu> findAllById(List<Integer> ids);

	List<ChatLieu> findAll();

	boolean existsById(Integer id);

	Optional<ChatLieu> findById(Integer id);

	List<ChatLieu> saveAll(List<ChatLieu> entities);

	ChatLieu save(ChatLieu entity);

	List<ChatLieu> findByTenDm(String tenDm);

	Page<ChatLieu> getPaginatedArticles(Pageable pageable);

	
	
}
