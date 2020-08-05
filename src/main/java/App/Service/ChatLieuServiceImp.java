package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import App.Repository.ChatLieuRepository;
import App.Repository.DanhMucRepository;
import App.entity.ChatLieu;
import App.entity.DanhMuc;
import App.entity.SanPham;
@Service

public class ChatLieuServiceImp implements ChatLieuService {
	@Autowired
	private ChatLieuRepository chatlieurepository;


	@Override
	public ChatLieu save(ChatLieu entity) {
		return chatlieurepository.save(entity);
	}

	@Override
	public List<ChatLieu> saveAll(List<ChatLieu> entities) {
		return (List<ChatLieu>) chatlieurepository.saveAll(entities);
	}

	@Override
	public Optional<ChatLieu> findById(Integer id) {
		return chatlieurepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return chatlieurepository.existsById(id);
	}

	@Override
	public List<ChatLieu> findAll() {
		return (List<ChatLieu>) chatlieurepository.findAll();
	}

	@Override
	public List<ChatLieu> findAllById(List<Integer> ids) {
		return (List<ChatLieu>) chatlieurepository.findAllById(ids);
	}

	@Override
	public long count() {
		return chatlieurepository.count();
	}

	
	
	@Override
	public void deleteById(Integer id) {
		chatlieurepository.deleteById(id);
	}

	@Override
	public void delete(ChatLieu entity) {
		chatlieurepository.delete(entity);
	}

	@Override
	public void deleteAll(List<ChatLieu> entities) {
		chatlieurepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		chatlieurepository.deleteAll();
	}

	@Override
	public List<ChatLieu> findByTenDm(String tenDm) {
		return  chatlieurepository.findByTenCl(tenDm);
	}
	@Override
	public Page<ChatLieu> getPaginatedArticles(Pageable pageable) {
        return chatlieurepository.findAll(pageable);
    }
}
