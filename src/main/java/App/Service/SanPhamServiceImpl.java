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
import App.Repository.SanPhamRepository;
import App.Repository.TinhTrangRepository;
import App.entity.ChatLieu;
import App.entity.DanhMuc;
import App.entity.LoaiSp;
import App.entity.PhanQuyen;
import App.entity.SanPham;
import App.entity.TinhTrang;

@Service

public class SanPhamServiceImpl implements SanPhamService {
	@Autowired
	private SanPhamRepository sanphamrepository;
	@Autowired
	private ChatLieuRepository	chatlieurepository;
	@Autowired 
	private DanhMucRepository danhmucresoritory;
	@Autowired
	private	TinhTrangRepository tinhtrangrepository;
	@Autowired
	private	LoaiSPRepository loaisprepository;
	
	@Override
	public List<LoaiSp> findallloaisp(){
		return (List<LoaiSp>) loaisprepository.findAll();
	}
	@Override
	public List<TinhTrang> findalltt(){
		return (List<TinhTrang>) tinhtrangrepository.findAll();
	}
	@Override
	public List<DanhMuc> findalldm(){
		return (List<DanhMuc>) danhmucresoritory.findAll();
	}
	@Override
	public List<ChatLieu> findallcl(){
		return (List<ChatLieu>) chatlieurepository.findAll();
	}
	@Override
	public SanPham save(SanPham entity) {
		return sanphamrepository.save(entity);
	}

	@Override
	public List<SanPham> saveAll(List<SanPham> entities) {
		return (List<SanPham>) sanphamrepository.saveAll(entities);
	}

	@Override
	public Optional<SanPham> findById(Integer id) {
		return sanphamrepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return sanphamrepository.existsById(id);
	}

	@Override
	public List<SanPham> findAll() {
		return (List<SanPham>) sanphamrepository.findAll();
	}

	@Override
	public List<SanPham> findAllById(List<Integer> ids) {
		return (List<SanPham>) sanphamrepository.findAllById(ids);
	}

	@Override
	public long count() {
		return sanphamrepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		sanphamrepository.deleteById(id);
	}

	@Override
	public void delete(SanPham entity) {
		sanphamrepository.delete(entity);
	}

	@Override
	public void deleteAll(List<SanPham> entities) {
		sanphamrepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		sanphamrepository.deleteAll();
	}
	@Override
	public Page<SanPham> getPaginatedArticles(Pageable pageable) {
        return sanphamrepository.findAll(pageable);
    }
	
	@Override
	public Page<SanPham> find(String name,Pageable pageable){
		return sanphamrepository.findByTenSpLikeOrderByTenSp("%"+name+"%", pageable);
	}
	@Override
	public long countBydanhMuc(DanhMuc dm) {
		return sanphamrepository.countBydanhMuc(dm);
	}
	@Override
	public SanPham findByID (Integer id) {
		return sanphamrepository.findByMaSp(id);
	}
	@Override
	public Optional<SanPham> findbyTen(String ten) {
		return sanphamrepository.findByTenSp(ten);
	}
	
}
