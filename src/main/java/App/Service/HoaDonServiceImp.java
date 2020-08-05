package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import App.Repository.HoaDonRepository;
import App.entity.HoaDon;
import App.entity.KhachHang;
import App.entity.SanPham;
import App.entity.TinhTrangHd;
@Service

public class HoaDonServiceImp implements HoaDonService {
	@Autowired
	private HoaDonRepository hoadonrepository;


	@Override
	public HoaDon save(HoaDon entity) {
		return hoadonrepository.save(entity);
	}

	@Override
	public List<HoaDon> saveAll(List<HoaDon> entities) {
		return (List<HoaDon>) hoadonrepository.saveAll(entities);
	}

	@Override
	public Optional<HoaDon> findById(Integer id) {
		return hoadonrepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return hoadonrepository.existsById(id);
	}

	@Override
	public List<HoaDon> findAll() {
		return (List<HoaDon>) hoadonrepository.findAll();
	}

	@Override
	public List<HoaDon> findAllById(List<Integer> ids) {
		return (List<HoaDon>) hoadonrepository.findAllById(ids);
	}

	@Override
	public long count() {
		return hoadonrepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		hoadonrepository.deleteById(id);
	}

	@Override
	public void delete(HoaDon entity) {
		hoadonrepository.delete(entity);
	}

	@Override
	public void deleteAll(List<HoaDon> entities) {
		hoadonrepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		hoadonrepository.deleteAll();
	}
	@Override
	public Page<HoaDon> getPaginatedArticles(Pageable pageable) {
        return hoadonrepository.findAll(pageable);
    }
	@Override
	public HoaDon findbyId(Integer id) {
		return hoadonrepository.findByMaHd(id);
		
	}
	@Override
	public long counthd(TinhTrangHd tinhtrangHd) {
		return hoadonrepository.countBytinhTrangHd(tinhtrangHd);
	}
	@Override
	public List<HoaDon> findTop5byKhachhang(KhachHang khachhang){
		return hoadonrepository.findTop5ByKhachHangOrderByMaHdDesc(khachhang);
	}
}
