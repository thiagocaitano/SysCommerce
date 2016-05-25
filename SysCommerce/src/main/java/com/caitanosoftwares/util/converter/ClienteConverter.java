package com.caitanosoftwares.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.caitanosoftwares.dao.ClienteDao;
import com.caitanosoftwares.entity.Cliente;

@FacesConverter(value = "clienteConverter")
public class ClienteConverter implements Converter {

	@Inject
	private ClienteDao dao;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		Cliente c = dao.getById(Long.parseLong(value));
		return c;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Cliente c = (Cliente) o;
		return String.valueOf(c.getId());
	}

}
