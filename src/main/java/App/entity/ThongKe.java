package App.entity;
// Generated Mar 20, 2020 11:26:53 AM by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ThongKe generated by hbm2java
 */
@Entity
@Table(name = "thong_ke", catalog = "management_shop")
public class ThongKe implements java.io.Serializable {

	private Integer maTk;
	private HoaDon hoaDon;
	private PhamVi phamVi;

	public ThongKe() {
	}

	public ThongKe(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public ThongKe(HoaDon hoaDon, PhamVi phamVi) {
		this.hoaDon = hoaDon;
		this.phamVi = phamVi;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ma_tk", unique = true, nullable = false)
	public Integer getMaTk() {
		return this.maTk;
	}

	public void setMaTk(Integer maTk) {
		this.maTk = maTk;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ma_hd", nullable = false)
	public HoaDon getHoaDon() {
		return this.hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ma_pv")
	public PhamVi getPhamVi() {
		return this.phamVi;
	}

	public void setPhamVi(PhamVi phamVi) {
		this.phamVi = phamVi;
	}

}
