package com.caitanosoftwares.service.interfaces;

import java.util.List;

public interface Service<T, ID> {

	public void salvar(T c);

	public void excluir(T c);

	public void alterar(T c);

	public T merge(T c);

	public T obterPorId(final ID id);

	public List<T> obterTodos();

	public List<T> obterPorWhere(String whereClause);

}
