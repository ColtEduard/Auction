package DAO;

import javax.persistence.TypedQuery;

import model.User;


public class UserDAO extends DAO {
	
	public void add(User user ) {
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		
	}
	
	public User getById(int id) {
		return em.find(User.class, id);
	}

	public User getByName(String name) {
		
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.userName = '"+name+"'", User.class);
		try {
		return query.getSingleResult();
		}catch(Exception e) {
			return null;
		}
		
	}
	
}
