package com.caitanosoftwares.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.caitanosoftwares.dao.FornecedorDao;
import com.caitanosoftwares.entity.Fornecedor;
import com.caitanosoftwares.service.FornecedorService;
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
	

}
