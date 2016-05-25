package com.caitanosoftwares.dao.interfaces;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.caitanosoftwares.util.jpa.Transactional;


@Transactional
public abstract class  DaoAbstract<T,ID> implements Dao<T,ID>, Serializable {


	private static final long serialVersionUID = -5730451180346997437L;
	
	@Inject
	private EntityManager manager;

	public EntityManager getEntityManager() {
		return this.manager;
	}

	@Transactional
	public void save(T c) {
		
	getEntityManager().merge(c);

	}

	

	public Class<T> getTypeClass() {
		return (Class<T>) ((ParameterizedType) ((Class<T>) getClass().getGenericSuperclass()).getGenericSuperclass()).getActualTypeArguments()[0];
	}

	private String getNameEntity() {

		return getTypeClass().getSimpleName().toString();
	}

	public T getById(final ID id) {
		return (T) getEntityManager().getReference(getTypeClass(), id);
	}

	public List<T> list() {
		Query query = getEntityManager().createQuery(
				"from " + getNameEntity() + "");
		return query.getResultList();

	}

	public List<T> list(String whereClause) {
		Query query = getEntityManager().createQuery(
				"from " + getNameEntity() + " where " + whereClause);
		return query.getResultList();
	}
	
	@Transactional
	public void remove(T c) {
    	manager.remove(getById((ID) getId(c)));
	}
	
	@Transactional
	public void update(T c) {

    	manager.merge(c);
	}

	public T merge(T c) {

    	return manager.merge(c);
	}
	
	public abstract Object getId(T entity);
	

//	private Object getId(Object entity) {
//		String methodGetID = "";
//
//		try {
//			Field[] fields = entity.getClass().getDeclaredFields();
//
//			for (Field field : fields) {
//				Id annotation = field.getAnnotation(javax.persistence.Id.class);
//				EmbeddedId annotation2 = field
//						.getAnnotation(javax.persistence.EmbeddedId.class);
//
//				if (annotation != null || annotation2 != null) {
//					methodGetID = "get"
//							+ (field.getName().charAt(0) + "").toUpperCase()
//							+ field.getName().substring(1);
//					break;
//				}
//
//			}
//			Method method = entity.getClass().getMethod(methodGetID);
//
//			return method.invoke(entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}

}
