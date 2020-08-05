package App.DTO;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import App.entity.ChatLieu;
import App.entity.DanhMuc;
import App.entity.HoaDonChiTiet;
import App.entity.LoaiSp;
import App.entity.TinhTrang;

public class SanPhamDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer maSp;
	private String moTaSp;

	public String getMoTaSp() {
		return moTaSp;
	}

	public void setMoTaSp(String moTaSp) {
		this.moTaSp = moTaSp;
	}

	private Integer chatLieu;

	private Integer danhMuc;

	private Integer loaiSp;

	private Integer tinhTrang;
	@NotNull
	@NotEmpty(message = "Không Được Để Trống!") 
	
	private String tenSp;

	@NotNull(message = "Vui Lòng Nhập Giá Bán")
	@Range(min =1000,message = "Giá Trị Quá Ít")
	private Double giaBan;

	private Integer luotMuaSp;
	@NotNull(message = "Vui Lòng Chọn Hình Ảnh")
	
	private MultipartFile hinhAnhSp;

	public Integer getMaSp() {
		return maSp;
	}

	public void setMaSp(Integer maSp) {
		this.maSp = maSp;
	}

	public Integer getChatLieu() {
		return chatLieu;
	}

	public void setChatLieu(Integer chatLieu) {
		this.chatLieu = chatLieu;
	}

	public Integer getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(Integer danhMuc) {
		this.danhMuc = danhMuc;
	}

	public Integer getLoaiSp() {
		return loaiSp;
	}

	public void setLoaiSp(Integer loaiSp) {
		this.loaiSp = loaiSp;
	}

	public Integer getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(Integer tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getTenSp() {
		return tenSp;
	}

	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}

	public Double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(Double giaBan) {
		this.giaBan = giaBan;
	}

	public Integer getLuotMuaSp() {
		return luotMuaSp;
	}

	public void setLuotMuaSp(Integer luotMuaSp) {
		this.luotMuaSp = luotMuaSp;
	}

	public MultipartFile getHinhAnhSp() {
		return hinhAnhSp;
	}

	public void setHinhAnhSp(MultipartFile hinhAnhSp) {
		this.hinhAnhSp = hinhAnhSp;
	}

}
