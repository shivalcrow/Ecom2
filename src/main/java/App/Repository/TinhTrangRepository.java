package App.Repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.entity.ChatLieu;
import App.entity.DanhMuc;
import App.entity.SanPham;
import App.entity.TinhTrang;

@Repository

public interface TinhTrangRepository extends CrudRepository<TinhTrang,Integer> {

	List<TinhTrang> findByTinhTrang(String tencl);
	 Page<TinhTrang> findAll(Pageable pageable);
	

}
