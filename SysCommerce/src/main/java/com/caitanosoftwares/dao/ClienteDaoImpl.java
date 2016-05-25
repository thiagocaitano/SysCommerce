package com.caitanosoftwares.dao;

import java.io.Serializable;

import com.caitanosoftwares.dao.interfaces.DaoAbstract;
import com.caitanosoftwares.entity.Cliente;

public class ClienteDaoImpl extends DaoAbstract<Cliente, Long> implements ClienteDao,Serializable {

	private static final long serialVersionUID = 5213808940194153799L;

	@Override
	public Object getId(Cliente cliente) {
		return cliente.getId();
	}


}
