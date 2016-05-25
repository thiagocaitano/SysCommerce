package com.caitanosoftwares.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.caitanosoftwares.dao.ProdutoDao;
import com.caitanosoftwares.entity.Produto;
import com.caitanosoftwares.service.interfaces.ServiceAbstract;

public class ProdutoServiceImpl extends ServiceAbstract<Produto, Long> implements ProdutoService,Serializable {


	private static final long serialVersionUID = 4826092619028123707L;

	private ProdutoDao produtoDao;
	
	@Inject
	protected ProdutoServiceImpl(ProdutoDao dao) {
		super(dao);
		produtoDao=dao;
	}

}