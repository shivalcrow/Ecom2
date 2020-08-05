package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import App.Repository.KhachHangRepository;
import App.entity.KhachHang;
import App.entity.SanPham;;

@Service
public class KhachHangServiceImp implements KhachHangService {

	@Autowired
	private KhachHangRepository khachhangrepository;

	@Override
	public KhachHang save(KhachHang entity) {
		return khachhangrepository.save(entity);
	}

	@Override
	public List<KhachHang> saveAll(List<KhachHang> entities) {
		return (List<KhachHang>) khachhangrepository.saveAll(entities);
	}

	@Override
	public Optional<KhachHang> findById(Integer id) {
		return khachhangrepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return khachhangrepository.existsById(id);
	}

	@Override
	public List<KhachHang> findAll() {
		return (List<KhachHang>) khachhangrepository.findAll();
	}

	@Override
	public List<KhachHang> findAllById(List<Integer> ids) {
		return (List<KhachHang>) khachhangrepository.findAllById(ids);
	}

	@Override
	public long count() {
		return khachhangrepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		khachhangrepository.deleteById(id);
	}

	@Override
	public void delete(KhachHang entity) {
		khachhangrepository.delete(entity);
	}

	@Override
	public void deleteAll(List<KhachHang> entities) {
		khachhangrepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		khachhangrepository.deleteAll();
	}

	@Override
	public Page<KhachHang> getPaginatedArticles(Pageable pageable) {
		return khachhangrepository.findAll(pageable);
	}

	@Override
	public KhachHang findByTenDangNhapKhAndMatKhauKh(String tenDangNhapKh, String matKhauKh) {
		return khachhangrepository.findByTenDangNhapKhAndMatKhauKh(tenDangNhapKh, matKhauKh);

	}
	@Override
	public KhachHang find(String tenDangNhapKh) {
		
		return khachhangrepository.findByTenDangNhapKh(tenDangNhapKh);
	}
	@Override
	public Optional<KhachHang> findBytenDangNhapKh(String ten){
		return khachhangrepository.findBytenDangNhapKh(ten);
	} 
	@Override
	public Optional<KhachHang> findByemailKh(String email){
		return khachhangrepository.findByemailKh(email);
	}
	@Override
	public Page<KhachHang> find(String name,Pageable pageable){
		return khachhangrepository.findByTenKhLikeOrderByTenKh("%"+name+"%", pageable);
	}
}
