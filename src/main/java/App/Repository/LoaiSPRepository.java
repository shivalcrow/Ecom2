package App.Repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.entity.ChatLieu;
import App.entity.DanhMuc;
import App.entity.LoaiSp;
import App.entity.SanPham;

@Repository

public interface LoaiSPRepository extends CrudRepository<LoaiSp,Integer> {

	List<LoaiSp> findByTenLoaiSp(String tenLoaiSp);
	 Page<LoaiSp> findAll(Pageable pageable);
	

}
