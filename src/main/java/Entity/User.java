package Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String avatar;

	@Column(name="dia_chi")
	private String diaChi;

	private String email;

	@Column(name="gioi_tinh")
	private int gioiTinh;

	@Column(name="ho_ten")
	private String hoTen;
	
	private int admin;

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public List<Userproduct> getUserProduct() {
		return UserProduct;
	}

	public void setUserProduct(List<Userproduct> userProduct) {
		UserProduct = userProduct;
	}

	public List<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}



	private String password;

	private String sdt;

	@OneToMany(mappedBy="user")
	private List<Category>category;
	
	@OneToMany(mappedBy="user")
	private List<Userproduct> UserProduct;
	
	@OneToMany(mappedBy="user")
	private List<Invoice> invoice;
	
	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDiaChi() {
		return this.diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGioiTinh() {
		return this.gioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getHoTen() {
		return this.hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSdt() {
		return this.sdt;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
	
	
}