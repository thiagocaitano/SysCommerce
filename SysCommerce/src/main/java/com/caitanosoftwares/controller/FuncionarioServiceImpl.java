package com.caitanosoftwares.controller;

import java.io.Serializable;

import javax.inject.Inject;

import com.caitanosoftwares.dao.FuncionarioDao;
import com.caitanosoftwares.entity.Funcionario;
import com.caitanosoftwares.service.FuncionarioService;
import com.caitanosoftwares.service.interfaces.ServiceAbstract;

public class FuncionarioServiceImpl extends ServiceAbstract<Funcionario, Long> implements FuncionarioService,Serializable {


	private static final long serialVersionUID = 2245129850986728087L;

	private FuncionarioDao funcionarioDao;
	
	@Inject
	FuncionarioServiceImpl(FuncionarioDao dao) {
		super(dao);
		funcionarioDao=dao;
	}

}
