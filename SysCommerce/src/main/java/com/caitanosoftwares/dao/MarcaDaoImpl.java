package com.caitanosoftwares.dao;

import java.io.Serializable;

import com.caitanosoftwares.dao.interfaces.DaoAbstract;
import com.caitanosoftwares.entity.Marca;

public class MarcaDaoImpl extends DaoAbstract<Marca, Long> implements MarcaDao,Serializable {

	private static final long serialVersionUID = 8434227627362728308L;

	@Override
	public Object getId(Marca marca) {
		return marca.getId();
	}


}
