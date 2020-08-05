package App.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import App.entity.NhanVien;
import App.entity.PhanQuyen;
import App.entity.SanPham;

public interface NhanVienService {

	void deleteAll();

	void deleteAll(List<NhanVien> entities);

	void delete(NhanVien entity);

	void deleteById(Integer id);

	long count();

	List<NhanVien> findAllById(List<Integer> ids);

	List<NhanVien> findAll();

	boolean existsById(Integer id);

	Optional<NhanVien> findById(Integer id);

	List<NhanVien> saveAll(List<NhanVien> entities);

	NhanVien save(NhanVien entity);

	List<PhanQuyen> findall();

	Page<NhanVien> getPaginatedArticles(Pageable pageable);

	Page<NhanVien> getPaginatednv(String tennv, Pageable pageable);

	NhanVien findbyemail(String email);

	

}
