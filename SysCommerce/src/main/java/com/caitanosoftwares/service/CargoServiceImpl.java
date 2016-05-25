package com.caitanosoftwares.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.caitanosoftwares.dao.CargoDao;
import com.caitanosoftwares.entity.Cargo;
import com.caitanosoftwares.service.interfaces.ServiceAbstract;

public class CargoServiceImpl extends ServiceAbstract<Cargo, Long> implements CargoService,Serializable {

	private static final long serialVersionUID = -5259020588483323929L;

	private CargoDao cargoDao;
	
	@Inject
	protected CargoServiceImpl(CargoDao dao) {
		super(dao);
		cargoDao=dao;
	}
}