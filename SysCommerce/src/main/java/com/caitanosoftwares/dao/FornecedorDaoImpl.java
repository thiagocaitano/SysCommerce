package com.caitanosoftwares.dao;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.caitanosoftwares.dao.interfaces.DaoAbstract;
import com.caitanosoftwares.entity.Fornecedor;

public class FornecedorDaoImpl extends DaoAbstract<Fornecedor, Long> implements FornecedorDao,Serializable {

	private static final long serialVersionUID = -6478868120265656701L;

	@Override
	public Object getId(Fornecedor fornecedor) {
		return fornecedor.getId();
	}
	
	public List<Fornecedor> obterFornecedoresQueNaoEstaoNaLista(List<Fornecedor> fornecedores){

		List<Long> listaDeId = fornecedores.stream()
		.map(Fornecedor::getId)
		.collect(Collectors.toList());
		
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Fornecedor.class);
		if(fornecedores.size()>0)
			criteria.add(Restrictions.not(Restrictions.in("id", listaDeId)));
		return criteria.list();
		
	}

	@Override
	public List<Fornecedor> obterFornecedoresPorRazaoOuCnpj(String razao, String cnpj) {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Fornecedor.class);
		criteria.add(Restrictions.like("razaoSocial", razao,MatchMode.ANYWHERE));
		criteria.add(Restrictions.like("cnpj", cnpj,MatchMode.ANYWHERE));
		return criteria.list();
	}
	
	@Override
	public Fornecedor obterFornecedorComProdutos(Fornecedor fornecedor){
		String sql = "SELECT DISTINCT(f) FROM Fornecedor f LEFT JOIN FETCH f.listaDeProdutosFornecidos WHERE f.id = :idFornecedor";
		TypedQuery<Fornecedor> query = getEntityManager().createQuery(sql,Fornecedor.class);
		query.setParameter("idFornecedor", fornecedor.getId());
		return query.getSingleResult();
	}

}
