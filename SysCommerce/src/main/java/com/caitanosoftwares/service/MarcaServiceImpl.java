package com.caitanosoftwares.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.caitanosoftwares.dao.MarcaDao;
import com.caitanosoftwares.entity.Marca;
import com.caitanosoftwares.service.interfaces.ServiceAbstract;

public class MarcaServiceImpl extends ServiceAbstract<Marca, Long> implements MarcaService,Serializable {

	private static final long serialVersionUID = 9088378308212023374L;

	private MarcaDao marcaDao;
	
	@Inject
	MarcaServiceImpl(MarcaDao dao) {
		super(dao);
		marcaDao=dao;
	}

}
