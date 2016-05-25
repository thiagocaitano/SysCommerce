package com.caitanosoftwares.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.caitanosoftwares.dao.ClienteDao;
import com.caitanosoftwares.entity.Cliente;
import com.caitanosoftwares.service.interfaces.ServiceAbstract;

public class ClienteServiceImpl extends ServiceAbstract<Cliente, Long> implements ClienteService,Serializable {

	private static final long serialVersionUID = 3719249778467637487L;

	private ClienteDao clienteDao;
	
	@Inject
	ClienteServiceImpl(ClienteDao dao) {
		super(dao);
		clienteDao=dao;
	}

}
