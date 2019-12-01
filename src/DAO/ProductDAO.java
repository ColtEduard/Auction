package DAO;

import model.Product;

public class ProductDAO extends DAO {

	public void add(Product product) {
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
	}
	
	
}
