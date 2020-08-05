package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import App.Repository.DanhMucRepository;
import App.Repository.PhanQuyenRepository;
import App.entity.DanhMuc;
import App.entity.PhanQuyen;
import App.entity.SanPham;
@Service

public class PhanQuyenServiceImp implements PhanQuyenService {
	@Autowired
	private PhanQuyenRepository phanquyenrepository;


	@Override
	public PhanQuyen save(PhanQuyen entity) {
		return phanquyenrepository.save(entity);
	}

	@Override
	public List<PhanQuyen> saveAll(List<PhanQuyen> entities) {
		return (List<PhanQuyen>) phanquyenrepository.saveAll(entities);
	}

	@Override
	public Optional<PhanQuyen> findById(Integer id) {
		return phanquyenrepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return phanquyenrepository.existsById(id);
	}

	@Override
	public List<PhanQuyen> findAll() {
		return (List<PhanQuyen>) phanquyenrepository.findAll();
	}

	@Override
	public List<PhanQuyen> findAllById(List<Integer> ids) {
		return (List<PhanQuyen>) phanquyenrepository.findAllById(ids);
	}

	@Override
	public long count() {
		return phanquyenrepository.count();
	}

	
	
	@Override
	public void deleteById(Integer id) {
		phanquyenrepository.deleteById(id);
	}

	@Override
	public void delete(PhanQuyen entity) {
		phanquyenrepository.delete(entity);
	}

	@Override
	public void deleteAll(List<PhanQuyen> entities) {
		phanquyenrepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		phanquyenrepository.deleteAll();
	}

	@Override
	public Page<PhanQuyen> getPaginatedArticles(Pageable pageable) {
        return phanquyenrepository.findAll(pageable);
    }

}
