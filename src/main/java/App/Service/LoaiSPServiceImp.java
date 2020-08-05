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
import App.Repository.LoaiSPRepository;
import App.entity.ChatLieu;
import App.entity.DanhMuc;
import App.entity.LoaiSp;
import App.entity.SanPham;
@Service

public class LoaiSPServiceImp implements LoaiSPService {
	@Autowired
	private LoaiSPRepository loaisprepository;


	@Override
	public LoaiSp save(LoaiSp entity) {
		return loaisprepository.save(entity);
	}


	@Override
	public List<LoaiSp> saveAll(List<LoaiSp> entities) {
		return (List<LoaiSp>) loaisprepository.saveAll(entities);
	}

	@Override
	public Optional<LoaiSp> findById(Integer id) {
		return loaisprepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return loaisprepository.existsById(id);
	}

	@Override
	public List<LoaiSp> findAll() {
		return (List<LoaiSp>) loaisprepository.findAll();
	}

	@Override
	public List<LoaiSp> findAllById(List<Integer> ids) {
		return (List<LoaiSp>) loaisprepository.findAllById(ids);
	}

	@Override
	public long count() {
		return loaisprepository.count();
	}

	
	
	@Override
	public void deleteById(Integer id) {
		loaisprepository.deleteById(id);
	}

	@Override
	public void delete(LoaiSp entity) {
		loaisprepository.delete(entity);
	}

	@Override
	public void deleteAll(List<LoaiSp> entities) {
		loaisprepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		loaisprepository.deleteAll();
	}

	@Override
	public List<LoaiSp> findByTenDm(String tenDm) {
		return  loaisprepository.findByTenLoaiSp(tenDm);
	}

	@Override
	public Page<LoaiSp> getPaginatedArticles(Pageable pageable) {
        return loaisprepository.findAll(pageable);
    }
}
