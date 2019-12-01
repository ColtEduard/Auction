package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idProduct;
	private double initSum;
	private String productDescription;
	private String productName;
	private List<Auction> auctions;
	private User user;

	public Product() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_product")
	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}


	@Column(name="init_sum")
	public double getInitSum() {
		return this.initSum;
	}

	public void setInitSum(double initSum) {
		this.initSum = initSum;
	}


	@Column(name="product_description")
	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	@Column(name="product_name")
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	//bi-directional many-to-one association to Auction
	@OneToMany(mappedBy="product")
	public List<Auction> getAuctions() {
		return this.auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public Auction addAuction(Auction auction) {
		getAuctions().add(auction);
		auction.setProduct(this);

		return auction;
	}

	public Auction removeAuction(Auction auction) {
		getAuctions().remove(auction);
		auction.setProduct(null);

		return auction;
	}


	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}