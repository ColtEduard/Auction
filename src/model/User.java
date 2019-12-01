package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idUser;
	private String userName;
	private String userPass;
	private List<Auction> auctions1;
	private List<Auction> auctions2;
	private List<Product> products;

	public User() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	@Column(name="user_name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Column(name="user_pass")
	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}


	//bi-directional many-to-one association to Auction
	@OneToMany(mappedBy="user1")
	public List<Auction> getAuctions1() {
		return this.auctions1;
	}

	public void setAuctions1(List<Auction> auctions1) {
		this.auctions1 = auctions1;
	}

	public Auction addAuctions1(Auction auctions1) {
		getAuctions1().add(auctions1);
		auctions1.setUser1(this);

		return auctions1;
	}

	public Auction removeAuctions1(Auction auctions1) {
		getAuctions1().remove(auctions1);
		auctions1.setUser1(null);

		return auctions1;
	}


	//bi-directional many-to-one association to Auction
	@OneToMany(mappedBy="user2")
	public List<Auction> getAuctions2() {
		return this.auctions2;
	}

	public void setAuctions2(List<Auction> auctions2) {
		this.auctions2 = auctions2;
	}

	public Auction addAuctions2(Auction auctions2) {
		getAuctions2().add(auctions2);
		auctions2.setUser2(this);

		return auctions2;
	}

	public Auction removeAuctions2(Auction auctions2) {
		getAuctions2().remove(auctions2);
		auctions2.setUser2(null);

		return auctions2;
	}


	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="user")
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setUser(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setUser(null);

		return product;
	}

}