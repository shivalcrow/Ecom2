package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import App.Repository.NhanVienRepository;
import App.Repository.PhanQuyenRepository;
import App.entity.NhanVien;
import App.entity.PhanQuyen;
import App.entity.SanPham;

@Service
public class NhanVienServiceImp implements NhanVienService {
	@Autowired
	private NhanVienRepository nhanvientrepository;
	@Autowired 
	private PhanQuyenRepository phanquyenrepository;
	
	@Override
	public List<PhanQuyen> findall(){
		return (List<PhanQuyen>) phanquyenrepository.findAll();
	}
	
	@Override
	public NhanVien save(NhanVien entity) {
		return nhanvientrepository.save(entity);
	}

	@Override
	public List<NhanVien> saveAll(List<NhanVien> entities) {
		return (List<NhanVien>) nhanvientrepository.saveAll(entities);
	}

	@Override
	public Optional<NhanVien> findById(Integer id) {
		return nhanvientrepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return nhanvientrepository.existsById(id);
	}

	@Override
	public List<NhanVien> findAll() {
		return (List<NhanVien>) nhanvientrepository.findAll();
	}

	@Override
	public List<NhanVien> findAllById(List<Integer> ids) {
		return (List<NhanVien>) nhanvientrepository.findAllById(ids);
	}

	@Override
	public long count() {
		return nhanvientrepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		nhanvientrepository.deleteById(id);
	}

	@Override
	public void delete(NhanVien entity) {
		nhanvientrepository.delete(entity);
	}

	@Override
	public void deleteAll(List<NhanVien> entities) {
		nhanvientrepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		nhanvientrepository.deleteAll();
	}
	
	@Override
	public Page<NhanVien> getPaginatedArticles(Pageable pageable) {
        return nhanvientrepository.findAll(pageable);
    }
	
	@Override
	public Page<NhanVien> getPaginatednv(String tennv,Pageable pageable) {
        return nhanvientrepository.findBytenNvLike("%"+tennv+"%", pageable);
    }
	@Override
	public NhanVien findbyemail(String email){
		return nhanvientrepository.findByemailNv(email);
	}
	
	
}
