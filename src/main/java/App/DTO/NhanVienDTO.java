package App.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;


public class NhanVienDTO {
	private Integer maNv;
	private Integer phanQuyen;
	@NotNull
	@NotEmpty(message = "Không Được Để Trống")
	private String tenNv;
	@NotNull
	private int sdtNv;

	@NotNull
	@NotEmpty(message = "Không Được Trống")
	@Email
	private String emailNv;
	
	@NotNull
	private int cmndNv;
	@Past
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date ngaySinhNv;
	@NotEmpty(message = "Không Được Để Trống")
	private String diaChiNv;
	@NotEmpty(message = "Không Được Để Trống")
	private String tenDangNhapNv;

	public Integer getMaNv() {
		return maNv;
	}

	public void setMaNv(Integer maNv) {
		this.maNv = maNv;
	}

	public Integer getPhanQuyen() {
		return phanQuyen;
	}

	public void setPhanQuyen(Integer phanQuyen) {
		this.phanQuyen = phanQuyen;
	}

	public String getTenNv() {
		return tenNv;
	}

	public void setTenNv(String tenNv) {
		this.tenNv = tenNv;
	}

	public int getSdtNv() {
		return sdtNv;
	}

	public void setSdtNv(int sdtNv) {
		this.sdtNv = sdtNv;
	}

	public String getEmailNv() {
		return emailNv;
	}

	public void setEmailNv(String emailNv) {
		this.emailNv = emailNv;
	}

	public int getCmndNv() {
		return cmndNv;
	}

	public void setCmndNv(int cmndNv) {
		this.cmndNv = cmndNv;
	}

	public Date getNgaySinhNv() {
		return ngaySinhNv;
	}

	public void setNgaySinhNv(Date ngaySinhNv) {
		this.ngaySinhNv = ngaySinhNv;
	}

	public String getDiaChiNv() {
		return diaChiNv;
	}

	public void setDiaChiNv(String diaChiNv) {
		this.diaChiNv = diaChiNv;
	}

	public String getTenDangNhapNv() {
		return tenDangNhapNv;
	}

	public void setTenDangNhapNv(String tenDangNhapNv) {
		this.tenDangNhapNv = tenDangNhapNv;
	}

	public String getMatKhauNv() {
		return matKhauNv;
	}

	public void setMatKhauNv(String matKhauNv) {
		this.matKhauNv = matKhauNv;
	}

	@NotEmpty(message = "Không Được Để Trống")
	private String matKhauNv;
}
