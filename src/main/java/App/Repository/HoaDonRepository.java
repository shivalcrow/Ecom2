package App.Repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.entity.DanhMuc;
import App.entity.HoaDon;
import App.entity.KhachHang;
import App.entity.SanPham;
import App.entity.TinhTrangHd;

@Repository

public interface HoaDonRepository extends CrudRepository<HoaDon,Integer> {


	 Page<HoaDon> findAll(Pageable pageable);
	 
	 HoaDon  findByMaHd(Integer maHd);
	 
	 long countBytinhTrangHd(TinhTrangHd tinhtranghd);
	 
	 List<HoaDon> findTop5ByKhachHangOrderByMaHdDesc(KhachHang khachHang);
	 
}
