package com.caitanosoftwares.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.caitanosoftwares.dao.MarcaDao;
import com.caitanosoftwares.entity.Marca;

@FacesConverter(value = "marcaConverter", forClass = Marca.class)
public class MarcaConverter implements Converter {
	@Inject
	private MarcaDao dao;

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (!value.equals("") && value != null)
			return dao.getById(Long.parseLong(value));
		else
			return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		if (o instanceof Marca) {
			Marca m = (Marca) o;
			return String.valueOf(m.getId());
		} else
			return null;
	}

}
