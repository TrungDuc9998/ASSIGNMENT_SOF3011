package Entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userproducts database table.
 * 
 */
@Entity
@Table(name="userproducts")
@NamedQuery(name="Userproduct.findAll", query="SELECT u FROM Userproduct u")
public class Userproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private int soLuong;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Userproduct() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}