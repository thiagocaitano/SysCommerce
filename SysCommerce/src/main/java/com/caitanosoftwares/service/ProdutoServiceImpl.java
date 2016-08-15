package com.caitanosoftwares.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.caitanosoftwares.dao.ProdutoDao;
import com.caitanosoftwares.entity.Produto;
import com.caitanosoftwares.exception.ServiceException;
import com.caitanosoftwares.service.interfaces.ServiceAbstract;

public class ProdutoServiceImpl extends ServiceAbstract<Produto, Long> implements ProdutoService,Serializable {


	private static final long serialVersionUID = 4826092619028123707L;

	private ProdutoDao produtoDao;
	
	@Inject
	protected ProdutoServiceImpl(ProdutoDao dao) {
		super(dao);
		produtoDao=dao;
	}
	
	@Override
	public List<Produto> obterProdutosComFornecedores(){
		return produtoDao.obterProdutosComFornecedores();
	}
	
	public void excluir(Produto produto) throws ServiceException{
		if(!produto.getListaDeFornecedores().isEmpty())
			throw new ServiceException("Produto possui fornecedores ! ");
		else
			super.excluir(produto);
	}

}