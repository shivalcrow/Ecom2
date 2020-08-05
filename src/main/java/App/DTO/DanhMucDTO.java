package App.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DanhMucDTO {
	private Integer maDm;
	@NotNull(message = "Vui Lòng Nhập Tên Danh Mục")
	
	@NotBlank(message = "Vui Lòng Nhập Tên Danh Mục")
	private String tenDm;
	public Integer getMaDm() {
		return maDm;
	}
	public void setMaDm(Integer maDm) {
		this.maDm = maDm;
	}
	public String getTenDm() {
		return tenDm;
	}
	public void setTenDm(String tenDm) {
		this.tenDm = tenDm;
	}
}
