package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import App.Repository.DanhMucRepository;
import App.entity.DanhMuc;
import App.entity.SanPham;
@Service

public class DanhMucServiceImp implements DanhMucService {
	@Autowired
	private DanhMucRepository danhmucrepository;


	@Override
	public DanhMuc save(DanhMuc entity) {
		return danhmucrepository.save(entity);
	}

	@Override
	public List<DanhMuc> saveAll(List<DanhMuc> entities) {
		return (List<DanhMuc>) danhmucrepository.saveAll(entities);
	}

	@Override
	public Optional<DanhMuc> findById(Integer id) {
		return danhmucrepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return danhmucrepository.existsById(id);
	}

	@Override
	public List<DanhMuc> findAll() {
		return (List<DanhMuc>) danhmucrepository.findAll();
	}

	@Override
	public List<DanhMuc> findAllById(List<Integer> ids) {
		return (List<DanhMuc>) danhmucrepository.findAllById(ids);
	}

	@Override
	public long count() {
		return danhmucrepository.count();
	}

	
	
	@Override
	public void deleteById(Integer id) {
		danhmucrepository.deleteById(id);
	}

	@Override
	public void delete(DanhMuc entity) {
		danhmucrepository.delete(entity);
	}

	@Override
	public void deleteAll(List<DanhMuc> entities) {
		danhmucrepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		danhmucrepository.deleteAll();
	}

	@Override
	public List<DanhMuc> findByTenDm(String tenDm) {
		return  danhmucrepository.findByTenDm(tenDm);
	}
	@Override
	public Page<DanhMuc> getPaginatedArticles(Pageable pageable) {
        return danhmucrepository.findAll(pageable);
    }
}
