package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Marca;
import com.caitanosoftwares.service.MarcaService;

@Named
@ViewScoped
public class MarcaBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private MarcaService marcaService;

	private List<Marca> listaDeMarcas;

	private String pesq = "";

	private Marca marca = new Marca();


	private List<Marca> preencherLista() {
		return listaDeMarcas = marcaService.obterTodos();
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Marca> getListaDeMarcas() {

		preencherLista();
		return listaDeMarcas.stream().filter(marca -> {
			return marca.getNome().toLowerCase().contains(pesq.toLowerCase());
		}).collect(Collectors.toList());
	}

	public String getPesq() {
		return pesq;
	}

	public void setPesq(String pesq) {
		this.pesq = pesq;
	}

	public String adicionar() {
		marcaService.salvar(marca);
		marca = new Marca();
		return "marca.xhtml?faces-redirect=true";
	}

	public void excluir(Marca marca) {
		marcaService.excluir(marca);
		listaDeMarcas.clear();
	}

	public String alterar() {
		marcaService.alterar(marca);
		marca = new Marca();
		return "marca.xhtml?faces-redirect=true";
	}

}
