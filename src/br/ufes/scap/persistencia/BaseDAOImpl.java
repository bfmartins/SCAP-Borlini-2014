package br.ufes.scap.persistencia;




import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class BaseDAOImpl<T> implements BaseDAO<T> {
	
	public EntityManager getConnection(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scapdbt");
		EntityManager em = factory.createEntityManager();
		
		return em;
		
	}

	public void salvar(T object){		

		EntityManager entityManager = null;
		BaseDAOImpl<T> base = new BaseDAOImpl<T>();
		entityManager= base.getConnection();
		
		entityManager.getTransaction().begin();	
		entityManager.persist(object);	
		entityManager.getTransaction().commit();

		
	}
	
	public T merge(T object) {	
		
		EntityManager em = null;
		
		BaseDAOImpl<T> base = new BaseDAOImpl<T>();
		em.getTransaction().begin();
		em= base.getConnection();
		em.merge(object);
		em.getTransaction().commit();
		return em.merge(object);
		
	}
	
	public void delete(T object) {
		
		EntityManager entityManager = null;
		BaseDAOImpl<T> base = new BaseDAOImpl<T>();
		entityManager= base.getConnection();
		
		
		entityManager.remove(entityManager.merge(object));
	}

	

	

	

	
}
