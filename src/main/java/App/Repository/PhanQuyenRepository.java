package App.Repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import App.entity.PhanQuyen;
import App.entity.SanPham;

@Repository

public interface PhanQuyenRepository extends CrudRepository<PhanQuyen,Integer> {

	 Page<PhanQuyen> findAll(Pageable pageable);

	

}
