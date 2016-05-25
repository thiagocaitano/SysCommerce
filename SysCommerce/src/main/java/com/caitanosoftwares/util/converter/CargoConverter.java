


package com.caitanosoftwares.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.caitanosoftwares.dao.CargoDao;
import com.caitanosoftwares.entity.Cargo;




@FacesConverter(value="cargoConverter")
public class CargoConverter implements Converter{
	
	@Inject
	private CargoDao dao;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Cargo c = dao.getById(Long.parseLong(value));
        return c;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Cargo c = (Cargo) o;  
        return String.valueOf( c.getId() );
    }

}
