package com.caitanosoftwares.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.caitanosoftwares.dao.FuncionarioDao;
import com.caitanosoftwares.entity.Funcionario;

@FacesConverter(value = "funcionarioConverter")
public class FuncionarioConverter implements Converter {

	@Inject
	private FuncionarioDao dao;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		return dao.getById(Long.parseLong(value));

	}

	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Funcionario f = (Funcionario) o;
		return String.valueOf(f.getId());
	}

}
