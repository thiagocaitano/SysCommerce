package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Departamento;
import com.caitanosoftwares.service.DepartamentoService;

@Named
@ViewScoped
public class DepartamentoBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private DepartamentoService departamentoService;

	private List<Departamento> listaDeDepartamentos;

	private String pesq = "";

	private Departamento departamento = new Departamento();


	private List<Departamento> preencherLista() {
		return listaDeDepartamentos = departamentoService.obterTodos();
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento Departamento) {
		this.departamento = Departamento;
	}

	public List<Departamento> getListaDeDepartamentos() {

		preencherLista();
		return listaDeDepartamentos.stream().filter(departamento -> {
			return departamento.getNome().toLowerCase().contains(pesq.toLowerCase());
		}).collect(Collectors.toList());
	}

	public String getPesq() {
		return pesq;
	}

	public void setPesq(String pesq) {
		this.pesq = pesq;
	}

	public String adicionar() {
		departamentoService.salvar(departamento);
		departamento = new Departamento();
		return "departamento.xhtml?faces-redirect=true";
	}

	public void excluir(Departamento Departamento) {
		departamentoService.excluir(Departamento);
		listaDeDepartamentos.clear();
	}

	public String alterar() {
		departamentoService.alterar(departamento);
		departamento = new Departamento();
		return "departamento.xhtml?faces-redirect=true";
	}

}
