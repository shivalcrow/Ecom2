package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import App.entity.ChatLieu;
import App.entity.DanhMuc;
import App.entity.LoaiSp;
import App.entity.SanPham;
import App.entity.TinhTrang;

public interface SanPhamService {

	void deleteAll();

	void deleteAll(List<SanPham> entities);

	void delete(SanPham entity);

	void deleteById(Integer id);

	long count();

	List<SanPham> findAllById(List<Integer> ids);

	List<SanPham> findAll();

	boolean existsById(Integer id);

	Optional<SanPham> findById(Integer id);

	List<SanPham> saveAll(List<SanPham> entities);

	SanPham save(SanPham entity);

	List<ChatLieu> findallcl();

	List<DanhMuc> findalldm();

	List<TinhTrang> findalltt();

	List<LoaiSp> findallloaisp();

	Page<SanPham> getPaginatedArticles(Pageable pageable);

	Page<SanPham> find(String name, Pageable pageable);

	long countBydanhMuc(DanhMuc danhMuc);

	SanPham findByID(Integer id);

	Optional<SanPham> findbyTen(String ten);
	






}
