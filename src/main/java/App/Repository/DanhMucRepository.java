package App.Repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.entity.DanhMuc;
import App.entity.SanPham;

@Repository

public interface DanhMucRepository extends CrudRepository<DanhMuc,Integer> {

	List<DanhMuc> findByTenDm(String tenDm);
	 Page<DanhMuc> findAll(Pageable pageable);
	

}
