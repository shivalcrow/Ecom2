package App.entity;
// Generated Aug 7, 2020 10:34:01 PM by Hibernate Tools 4.3.1.Final

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

/**
 * ChatLieu generated by hbm2java
 */
@Entity
@Table(name = "chat_lieu", catalog = "pl9x6o2av23gjabw")
public class ChatLieu implements java.io.Serializable {

	private Integer maCl;
	private String tenCl;
	private Set<SanPham> sanPhams = new HashSet<SanPham>(0);

	public ChatLieu() {
	}

	public ChatLieu(String tenCl, Set<SanPham> sanPhams) {
		this.tenCl = tenCl;
		this.sanPhams = sanPhams;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ma_cl", unique = true, nullable = false)
	public Integer getMaCl() {
		return this.maCl;
	}

	public void setMaCl(Integer maCl) {
		this.maCl = maCl;
	}

	@Column(name = "ten_cl", length = 20)
	public String getTenCl() {
		return this.tenCl;
	}

	public void setTenCl(String tenCl) {
		this.tenCl = tenCl;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chatLieu")
	public Set<SanPham> getSanPhams() {
		return this.sanPhams;
	}

	public void setSanPhams(Set<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}

}
