package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public abstract class DAO {

	@PersistenceContext
	protected EntityManager em = getEntityManager();

	protected EntityManager getEntityManager() {
		EntityManagerFactory ef = Persistence.createEntityManagerFactory("SQL");
		return ef.createEntityManager();
	}

}
