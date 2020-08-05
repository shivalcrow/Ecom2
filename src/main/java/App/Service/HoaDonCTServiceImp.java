package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import App.Repository.HoaDonCTRepository;
import App.Repository.HoaDonRepository;
import App.entity.HoaDon;
import App.entity.HoaDonChiTiet;
import App.entity.SanPham;
@Service

public class HoaDonCTServiceImp implements HoaDonCTService {
	@Autowired
	private HoaDonCTRepository hoadonctrepository;


	@Override
	public HoaDonChiTiet save(HoaDonChiTiet entity) {
		return hoadonctrepository.save(entity);
	}

	@Override
	public List<HoaDonChiTiet> saveAll(List<HoaDonChiTiet> entities) {
		return (List<HoaDonChiTiet>) hoadonctrepository.saveAll(entities);
	}

	@Override
	public Optional<HoaDonChiTiet> findById(Integer id) {
		return hoadonctrepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return hoadonctrepository.existsById(id);
	}

	@Override
	public List<HoaDonChiTiet> findAll() {
		return (List<HoaDonChiTiet>) hoadonctrepository.findAll();
	}

	@Override
	public List<HoaDonChiTiet> findAllById(List<Integer> ids) {
		return (List<HoaDonChiTiet>) hoadonctrepository.findAllById(ids);
	}

	@Override
	public long count() {
		return hoadonctrepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		hoadonctrepository.deleteById(id);
	}

	@Override
	public void delete(HoaDonChiTiet entity) {
		hoadonctrepository.delete(entity);
	}

	@Override
	public void deleteAll(List<HoaDonChiTiet> entities) {
		hoadonctrepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		hoadonctrepository.deleteAll();
	}
	@Override
	public Page<HoaDonChiTiet> getPaginatedArticles(Pageable pageable) {
        return hoadonctrepository.findAll(pageable);
    }
	@Override
	public List<HoaDonChiTiet> findbyHoaDon(HoaDon id){
		return hoadonctrepository.findByHoaDon(id);
	}
	
	
}
