package App.entity;
// Generated Mar 20, 2020 11:24:49 AM by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TinhTrang generated by hbm2java
 */
@Entity
@Table(name = "tinh_trang", catalog = "management_shop")
public class TinhTrang implements java.io.Serializable {

	private int idTinhTrang;
	private String tinhTrang;
	private Set<SanPham> sanPhams = new HashSet<SanPham>(0);

	public TinhTrang() {
	}

	public TinhTrang(int idTinhTrang) {
		this.idTinhTrang = idTinhTrang;
	}

	public TinhTrang(int idTinhTrang, String tinhTrang, Set<SanPham> sanPhams) {
		this.idTinhTrang = idTinhTrang;
		this.tinhTrang = tinhTrang;
		this.sanPhams = sanPhams;
	}

	@Id

	@Column(name = "id_tinh_trang", unique = true, nullable = false)
	public int getIdTinhTrang() {
		return this.idTinhTrang;
	}

	public void setIdTinhTrang(int idTinhTrang) {
		this.idTinhTrang = idTinhTrang;
	}

	@Column(name = "tinh_trang", length = 45)
	public String getTinhTrang() {
		return this.tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tinhTrang")
	public Set<SanPham> getSanPhams() {
		return this.sanPhams;
	}

	public void setSanPhams(Set<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}

}
