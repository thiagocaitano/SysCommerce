package com.caitanosoftwares.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.caitanosoftwares.dao.interfaces.DaoAbstract;
import com.caitanosoftwares.entity.Produto;

public class ProdutoDaoImpl extends DaoAbstract<Produto, Long> implements ProdutoDao,Serializable {

	private static final long serialVersionUID = -6478868120265656701L;

	@Override
	public Object getId(Produto produto) {
		return produto.getId();
	}
	
	public Produto obterProdutoComFornecedoresPorId(Long id) {
		String sql = "SELECT p FROM Produto p LEFT JOIN FETCH p.listaDeFornecedores f WHERE p.id = :id";
		TypedQuery<Produto> query = getEntityManager().createQuery(sql, Produto.class);
		query.setParameter("id", id);
			return query.getSingleResult();
	}
	
	public List<Produto> obterProdutosComFornecedores() {
		String sql = "SELECT DISTINCT(p) FROM Produto p LEFT JOIN FETCH p.listaDeFornecedores";
		TypedQuery<Produto> query = getEntityManager().createQuery(sql, Produto.class);
		return query.getResultList();
	}
	
	

}
