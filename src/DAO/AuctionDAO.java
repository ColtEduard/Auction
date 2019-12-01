package DAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.Auction;

public class AuctionDAO extends DAO {

	public void add(Auction auction) {
		
		em.getTransaction().begin();
		em.persist(auction);
		em.getTransaction().commit();
		
	}
	
	public List<Auction> getMyAuctions(int userId) {
		
		TypedQuery<Auction> qr = em.createQuery(
				"Select auc from Auction auc JOIN auc.user1 user1 where user1.idUser = '" + userId + "'", Auction.class);
		return qr.getResultList();	
		
	}
	
	public boolean isAuctionExpired(int auctionId) {
		TypedQuery<Auction> qr = em.createQuery(
				"Select auc from Auction auc where auc.expDate <= CURRENT_DATE and auc.idAuction =" + auctionId, Auction.class);
		
		try {
			qr.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<Auction> getAllAuctions() {
		TypedQuery<Auction> qr = em.createQuery(
				"Select auc from Auction auc where auc.expDate > CURRENT_DATE", Auction.class);
		return qr.getResultList();	
	}

	
	public void updateBid(Auction auction, double bid) {
		em.getTransaction().begin();
		auction.setCurrentBid(bid);
		em.merge(auction);
		em.getTransaction().commit();
		
	}
	
	
	
}
