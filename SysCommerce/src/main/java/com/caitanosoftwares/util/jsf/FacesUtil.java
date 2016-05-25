package com.caitanosoftwares.util.jsf;

import javax.faces.context.FacesContext;

public class FacesUtil {
	
	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}
	
	public static boolean isNotPostback() {
		return !isPostback();
	}
}
