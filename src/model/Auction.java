package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the auction database table.
 * 
 */
@Entity
@NamedQuery(name="Auction.findAll", query="SELECT a FROM Auction a")
public class Auction implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idAuction;
	private double currentBid;
	private Date expDate;
	private Product product;
	private User user1;
	private User user2;

	public Auction() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_auction")
	public int getIdAuction() {
		return this.idAuction;
	}

	public void setIdAuction(int idAuction) {
		this.idAuction = idAuction;
	}


	@Column(name="current_bid")
	public double getCurrentBid() {
		return this.currentBid;
	}

	public void setCurrentBid(double currentBid) {
		this.currentBid = currentBid;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="exp_date")
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}


	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="id_product")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}


	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="highest_bidder")
	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
	public String getExpDateString() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(this.expDate);
	}
	
	public String getUserFromName() {
		return this.user1.getUserName();
	}
	
	public String getHighestBidderName() {
		if(user2 == null) {
			return "None";
		}
		return this.user2.getUserName();
	}
	
	public String getProductName() {
		return this.product.getProductName();
	}
	

}