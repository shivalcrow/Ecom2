package App.entity;
// Generated Mar 20, 2020 11:24:49 AM by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * KhachHang generated by hbm2java
 */
@Entity
@Table(name = "khach_hang", catalog = "management_shop")
public class KhachHang implements java.io.Serializable {

	private Integer maKh;
	private String tenDangNhapKh;
	private String matKhauKh;
	private String tenKh;
	private int soDienThoaiKh;
	private String emailKh;
	private Date ngaySinhKh;
	private String diaChiKh;
	private int diemTichLuyKh;
	private Set<HoaDon> hoaDons = new HashSet<HoaDon>(0);

	public KhachHang() {
	}

	public KhachHang(String tenDangNhapKh, String matKhauKh, String tenKh, int soDienThoaiKh, String emailKh,
			Date ngaySinhKh, String diaChiKh, int diemTichLuyKh) {
		this.tenDangNhapKh = tenDangNhapKh;
		this.matKhauKh = matKhauKh;
		this.tenKh = tenKh;
		this.soDienThoaiKh = soDienThoaiKh;
		this.emailKh = emailKh;
		this.ngaySinhKh = ngaySinhKh;
		this.diaChiKh = diaChiKh;
		this.diemTichLuyKh = diemTichLuyKh;
	}

	public KhachHang(String tenDangNhapKh, String matKhauKh, String tenKh, int soDienThoaiKh, String emailKh,
			Date ngaySinhKh, String diaChiKh, int diemTichLuyKh, Set<HoaDon> hoaDons) {
		this.tenDangNhapKh = tenDangNhapKh;
		this.matKhauKh = matKhauKh;
		this.tenKh = tenKh;
		this.soDienThoaiKh = soDienThoaiKh;
		this.emailKh = emailKh;
		this.ngaySinhKh = ngaySinhKh;
		this.diaChiKh = diaChiKh;
		this.diemTichLuyKh = diemTichLuyKh;
		this.hoaDons = hoaDons;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ma_kh", unique = true, nullable = false)
	public Integer getMaKh() {
		return this.maKh;
	}

	public void setMaKh(Integer maKh) {
		this.maKh = maKh;
	}

	@Column(name = "ten_dang_nhap_kh", nullable = false, length = 20)
	public String getTenDangNhapKh() {
		return this.tenDangNhapKh;
	}

	public void setTenDangNhapKh(String tenDangNhapKh) {
		this.tenDangNhapKh = tenDangNhapKh;
	}

	@Column(name = "mat_khau_kh", nullable = false, length = 30)
	public String getMatKhauKh() {
		return this.matKhauKh;
	}

	public void setMatKhauKh(String matKhauKh) {
		this.matKhauKh = matKhauKh;
	}

	@Column(name = "ten_kh", nullable = false, length = 50)
	public String getTenKh() {
		return this.tenKh;
	}

	public void setTenKh(String tenKh) {
		this.tenKh = tenKh;
	}

	@Column(name = "so_dien_thoai_kh", nullable = false)
	public int getSoDienThoaiKh() {
		return this.soDienThoaiKh;
	}

	public void setSoDienThoaiKh(int soDienThoaiKh) {
		this.soDienThoaiKh = soDienThoaiKh;
	}

	@Column(name = "email_kh", nullable = false, length = 30)
	public String getEmailKh() {
		return this.emailKh;
	}

	public void setEmailKh(String emailKh) {
		this.emailKh = emailKh;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_sinh_kh", nullable = false, length = 10)
	public Date getNgaySinhKh() {
		return this.ngaySinhKh;
	}

	public void setNgaySinhKh(Date ngaySinhKh) {
		this.ngaySinhKh = ngaySinhKh;
	}

	@Column(name = "dia_chi_kh", nullable = false, length = 500)
	public String getDiaChiKh() {
		return this.diaChiKh;
	}

	public void setDiaChiKh(String diaChiKh) {
		this.diaChiKh = diaChiKh;
	}

	@Column(name = "diem_tich_luy_kh", nullable = false)
	public int getDiemTichLuyKh() {
		return this.diemTichLuyKh;
	}

	public void setDiemTichLuyKh(int diemTichLuyKh) {
		this.diemTichLuyKh = diemTichLuyKh;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khachHang")
	public Set<HoaDon> getHoaDons() {
		return this.hoaDons;
	}

	public void setHoaDons(Set<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}

}
