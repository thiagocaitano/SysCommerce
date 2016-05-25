package com.caitanosoftwares.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;



public interface Dao<T,ID> extends Serializable {


	EntityManager getEntityManager();
	
	public void save(T c);
	public T getById(final ID id);
	public List<T> list();
	public List<T> list(String valor);
	public void remove(T c);
	public void update(T c);
	public T merge(T c);
	public Class<T> getTypeClass();
	
}
