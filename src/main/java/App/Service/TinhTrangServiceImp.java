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
import App.Repository.TinhTrangRepository;
import App.entity.ChatLieu;
import App.entity.DanhMuc;
import App.entity.SanPham;
import App.entity.TinhTrang;
@Service

public class TinhTrangServiceImp implements TinhTrangService {
	@Autowired
	private TinhTrangRepository tinhtrangrepository;


	@Override
	public TinhTrang save(TinhTrang entity) {
		return tinhtrangrepository.save(entity);
	}

	@Override
	public List<TinhTrang> saveAll(List<TinhTrang> entities) {
		return (List<TinhTrang>) tinhtrangrepository.saveAll(entities);
	}

	@Override
	public Optional<TinhTrang> findById(Integer id) {
		return tinhtrangrepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return tinhtrangrepository.existsById(id);
	}

	@Override
	public List<TinhTrang> findAll() {
		return (List<TinhTrang>) tinhtrangrepository.findAll();
	}

	@Override
	public List<TinhTrang> findAllById(List<Integer> ids) {
		return (List<TinhTrang>) tinhtrangrepository.findAllById(ids);
	}

	@Override
	public long count() {
		return tinhtrangrepository.count();
	}

	
	
	@Override
	public void deleteById(Integer id) {
		tinhtrangrepository.deleteById(id);
	}

	@Override
	public void delete(TinhTrang entity) {
		tinhtrangrepository.delete(entity);
	}

	@Override
	public void deleteAll(List<TinhTrang> entities) {
		tinhtrangrepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		tinhtrangrepository.deleteAll();
	}

	@Override
	public List<TinhTrang> findByTenDm(String tenDm) {
		return  tinhtrangrepository.findByTinhTrang(tenDm);
	}
	@Override
	public Page<TinhTrang> getPaginatedArticles(Pageable pageable) {
        return tinhtrangrepository.findAll(pageable);
    }

}
