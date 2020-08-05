package App.Repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import App.entity.DanhMuc;
import App.entity.SanPham;

@Repository

public interface SanPhamRepository extends CrudRepository<SanPham, Integer> {
	Page<SanPham> findAll(Pageable pageable);

	Page<SanPham> findByTenSpLikeOrderByTenSp(String tenSp, Pageable pageable);

	long countBydanhMuc(DanhMuc danhMuc);

	SanPham findByMaSp(Integer maSp);

	Optional<SanPham> findByTenSp(String tenSp);
	
	
}
