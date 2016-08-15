package com.caitanosoftwares.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;

import com.caitanosoftwares.dao.FornecedorDao;
import com.caitanosoftwares.entity.Fornecedor;
import com.caitanosoftwares.exception.ServiceException;
import com.caitanosoftwares.service.interfaces.ServiceAbstract;

public class FornecedorServiceImpl extends ServiceAbstract<Fornecedor, Long> implements FornecedorService,Serializable {

	private static final long serialVersionUID = -1701929241241722861L;

	private FornecedorDao fornecedorDao;
	
	@Inject
	FornecedorServiceImpl(FornecedorDao dao) {
		super(dao);
		fornecedorDao = dao;
	}

	public List<Fornecedor> obterFornecedoresQueNaoEstaoNaLista(List<Fornecedor> fornecedores){
		return fornecedorDao.obterFornecedoresQueNaoEstaoNaLista(fornecedores);
	}

	@Override
	public List<Fornecedor> obterFornecedoresPorRazaoOuCnpj(String razao, String cnpj) {
		return fornecedorDao.obterFornecedoresPorRazaoOuCnpj(razao,cnpj);
	}
	
	@Override
	public Fornecedor obterFornecedorComProdutos(Fornecedor fornecedor){
		return fornecedorDao.obterFornecedorComProdutos(fornecedor);
	}
	
	@Override
	public void excluir(Fornecedor fornecedor) throws ServiceException {

		if(!obterFornecedorComProdutos(fornecedor).getListaDeProdutosFornecidos().isEmpty())
			throw new ServiceException("Fornecedor tem produtos fornecidos e não pode ser excluído");
		else
			super.excluir(fornecedor);
	}
	
}
