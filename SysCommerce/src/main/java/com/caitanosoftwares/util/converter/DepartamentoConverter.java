package com.caitanosoftwares.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.caitanosoftwares.dao.DepartamentoDao;
import com.caitanosoftwares.entity.Departamento;

@FacesConverter(value = "departamentoConverter", forClass = Departamento.class)
public class DepartamentoConverter implements Converter {
	@Inject
	private DepartamentoDao departamentoDao;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && !value.equals(""))
			return departamentoDao.getById(Long.parseLong(value));
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		if (o instanceof Departamento) {
			Departamento c = (Departamento) o;
			return String.valueOf(c.getId());
		} else
			return null;
	}

}
