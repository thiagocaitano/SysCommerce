package com.caitanosoftwares.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.caitanosoftwares.dao.interfaces.Dao;
import com.caitanosoftwares.exception.ServiceException;

public class ServiceAbstract<T, ID> implements Service<T, ID>,Serializable {

	private static final long serialVersionUID = 6583981537744322307L;

	private Dao<T, ID> dao;

	protected ServiceAbstract(Dao<T, ID> dao) {
		this.dao = dao;
	}

	public void salvar(T c) {
		dao.save(c);
	}

	public void excluir(T c) throws ServiceException {
		dao.remove(c);
	}

	public void alterar(T c) {
		dao.update(c);
	}

	public T merge(T c) {
		return dao.merge(c);
	}

	public T obterPorId(final ID id) {
		return dao.getById(id);
	}

	public List<T> obterTodos() {
		return dao.list();
	}

	public List<T> obterPorWhere(String whereClause) {
		return dao.list(whereClause);
	}

}
