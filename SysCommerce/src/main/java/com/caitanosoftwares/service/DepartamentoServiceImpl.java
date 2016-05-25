package com.caitanosoftwares.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.caitanosoftwares.dao.DepartamentoDao;
import com.caitanosoftwares.entity.Departamento;
import com.caitanosoftwares.service.interfaces.ServiceAbstract;

public class DepartamentoServiceImpl extends ServiceAbstract<Departamento, Long> implements DepartamentoService,Serializable {


	private static final long serialVersionUID = -7918477474720539871L;

	private DepartamentoDao departamentoDao;
	
	@Inject
	DepartamentoServiceImpl(DepartamentoDao dao) {
		super(dao);
		departamentoDao = dao;
	}
}