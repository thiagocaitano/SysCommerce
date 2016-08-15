package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Departamento;
import com.caitanosoftwares.exception.ServiceException;
import com.caitanosoftwares.service.DepartamentoService;
import com.caitanosoftwares.util.jsf.MessagesUtil;

@Named
@ViewScoped
public class DepartamentoBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private DepartamentoService departamentoService;

	private List<Departamento> listaDeDepartamentos;

	private List<Departamento> listaDeDepartamentosFiltrados;

	private Departamento departamento = new Departamento();

	@PostConstruct
	private void init() {
		listaDeDepartamentos = departamentoService.obterTodos();
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento Departamento) {
		this.departamento = Departamento;
	}

	public List<Departamento> getListaDeDepartamentos() {
		return listaDeDepartamentos;
	}

	public List<Departamento> getListaDeDepartamentosFiltrados() {
		return listaDeDepartamentosFiltrados;
	}

	public void setListaDeDepartamentosFiltrados(List<Departamento> listaDeDepartamentosFiltrados) {
		this.listaDeDepartamentosFiltrados = listaDeDepartamentosFiltrados;
	}

	public void novoDepartamento(){
		departamento = new Departamento();
	}
	
	public void salvar() {
		departamentoService.salvar(departamento);
		listaDeDepartamentos=departamentoService.obterTodos();
		MessagesUtil.addInfoMessage("Departamento salvo com sucesso");
	}

	public void excluir() {
		try {
			departamentoService.excluir(departamento);
			listaDeDepartamentos=departamentoService.obterTodos();
			MessagesUtil.addInfoMessage("Departamento excluído com sucesso.");
		} catch (ServiceException e) {
			MessagesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			MessagesUtil.addErrorMessage("Erro ao salvar departamento.");
		}
	}

}
