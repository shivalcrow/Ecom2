package App.Repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.entity.ChatLieu;
import App.entity.DanhMuc;
import App.entity.SanPham;

@Repository

public interface ChatLieuRepository extends CrudRepository<ChatLieu,Integer> {

	List<ChatLieu> findByTenCl(String tencl);
	 Page<ChatLieu> findAll(Pageable pageable);
	

}
