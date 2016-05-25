package com.caitanosoftwares.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.caitanosoftwares.dao.FornecedorDao;
import com.caitanosoftwares.entity.Fornecedor;

@FacesConverter(value = "fornecedorConverter")
public class FornecedorConverter implements Converter {

	@Inject
	private FornecedorDao dao;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		return dao.getById(Long.parseLong(value));

	}

	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Fornecedor f = (Fornecedor) o;
		return String.valueOf(f.getId());
	}

}
