package br.ufes.scap.persistencia;

import java.util.logging.Level;

import javax.persistence.EntityManager;

public interface BaseDAO<T> {
	
	public EntityManager getConnection();	
	
	public void salvar(T object);
	
	T merge(T object);
	
}
