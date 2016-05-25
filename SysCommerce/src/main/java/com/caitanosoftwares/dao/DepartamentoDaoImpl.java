package com.caitanosoftwares.dao;

import java.io.Serializable;

import com.caitanosoftwares.dao.interfaces.DaoAbstract;
import com.caitanosoftwares.entity.Departamento;

public class DepartamentoDaoImpl extends DaoAbstract<Departamento, Long> implements DepartamentoDao,Serializable {

	private static final long serialVersionUID = -6478868120265656701L;

	@Override
	public Object getId(Departamento departamento) {
		return departamento.getId();
	}


}
