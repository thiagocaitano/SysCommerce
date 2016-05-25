package com.caitanosoftwares.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.caitanosoftwares.dao.ProdutoDao;
import com.caitanosoftwares.entity.Produto;

@FacesConverter(value="produtoFornecedorConverter",forClass=Produto.class)
public class ProdutoFornecedorConverter implements Converter{
	
	@Inject
	private ProdutoDao dao;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Produto produto = dao.obterProdutoComFornecedoresPorId(Long.parseLong(value));
        return produto;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Produto produto = (Produto) o;  
        return String.valueOf( produto.getId() );
    }

}
