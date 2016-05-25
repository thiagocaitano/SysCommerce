package com.caitanosoftwares.dao;

import java.io.Serializable;

import com.caitanosoftwares.dao.interfaces.DaoAbstract;
import com.caitanosoftwares.entity.Funcionario;

public class FuncionarioDaoImpl extends DaoAbstract<Funcionario, Long> implements FuncionarioDao,Serializable {

	private static final long serialVersionUID = -6478868120265656701L;

	@Override
	public Object getId(Funcionario funcionario) {
		return funcionario.getId();
	}


}
