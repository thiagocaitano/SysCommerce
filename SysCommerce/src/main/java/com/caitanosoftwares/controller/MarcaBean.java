package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Marca;
import com.caitanosoftwares.exception.ServiceException;
import com.caitanosoftwares.service.MarcaService;
import com.caitanosoftwares.util.jsf.MessagesUtil;

@Named
@ViewScoped
public class MarcaBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private MarcaService marcaService;

	private List<Marca> listaDeMarcas;

	private List<Marca> listaDeMarcasFiltradas;

	private Marca marca = new Marca();

	@PostConstruct
	private void init() {
		listaDeMarcas=marcaService.obterTodos();
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Marca> getListaDeMarcas() {

		return this.listaDeMarcas;
	}

	public List<Marca> getListaDeMarcasFiltradas() {
		return listaDeMarcasFiltradas;
	}

	public void setListaDeMarcasFiltradas(List<Marca> listaDeMarcasFiltradas) {
		this.listaDeMarcasFiltradas = listaDeMarcasFiltradas;
	}
	
	public void novaMarca(){
		marca = new Marca();
	}

	public void salvar() {
		marcaService.salvar(marca);
		listaDeMarcas=marcaService.obterTodos();
		MessagesUtil.addInfoMessage("Marca salva com sucesso!");
	}

	public void excluir() {
		try {
			marcaService.excluir(marca);
			listaDeMarcas=marcaService.obterTodos();
			MessagesUtil.addInfoMessage("Marca excluída com sucesso!");
		} catch (Exception e) {
			MessagesUtil.addInfoMessage("Erro ao excluir produto");
		}
	}
}
