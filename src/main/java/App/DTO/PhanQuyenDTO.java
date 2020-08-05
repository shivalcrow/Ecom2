package App.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PhanQuyenDTO {
	private Integer maPhanQuyen;
	@NotNull(message = "Vui Lòng Nhập Tên Danh Mục")
	
	@NotBlank(message = "Vui Lòng Nhập Tên Danh Mục")
	private String tenPhanQuyen;
	public Integer getMaPhanQuyen() {
		return maPhanQuyen;
	}
	public void setMaPhanQuyen(Integer maPhanQuyen) {
		this.maPhanQuyen = maPhanQuyen;
	}
	public String getTenPhanQuyen() {
		return tenPhanQuyen;
	}
	public void setTenPhanQuyen(String tenPhanQuyen) {
		this.tenPhanQuyen = tenPhanQuyen;
	}
}
