package com.caitanosoftwares.dao;

import java.io.Serializable;

import com.caitanosoftwares.dao.interfaces.DaoAbstract;
import com.caitanosoftwares.entity.Cargo;

public class CargoDaoImpl extends DaoAbstract<Cargo, Long> implements CargoDao,Serializable {

	private static final long serialVersionUID = 4989108835830853391L;

	@Override
	public Object getId(Cargo cargo) {
		return cargo.getId();
	}


}
