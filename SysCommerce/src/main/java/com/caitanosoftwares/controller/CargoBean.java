package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Cargo;
import com.caitanosoftwares.entity.Departamento;
import com.caitanosoftwares.exception.ServiceException;
import com.caitanosoftwares.service.CargoService;
import com.caitanosoftwares.service.DepartamentoService;
import com.caitanosoftwares.util.jsf.MessagesUtil;

@Named
@ViewScoped
public class CargoBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private CargoService cargoService;

	@Inject
	private DepartamentoService departamentoService;

	private List<Cargo> listaDeCargos;

	private List<Departamento> listaDeDepartamentos;

	private List<Departamento> listaDeDepartamentosFiltrados;

	private Cargo cargo = new Cargo();
	
	@PostConstruct
	public void init(){
		
		listaDeDepartamentos = departamentoService.obterTodos();
		listaDeCargos = cargoService.obterTodos();
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo Cargo) {
		this.cargo = Cargo;
	}

	public List<Cargo> getListaDeCargos() {

		return listaDeCargos;
	}
		
	public List<Departamento> getListaDepartamento(){
		return listaDeDepartamentos;
	}

	public List<Departamento> getListaDeDepartamentosFiltrados() {
		return listaDeDepartamentosFiltrados;
	}

	public void setListaDeDepartamentosFiltrados(List<Departamento> listaDeDepartamentosFiltrados) {
		this.listaDeDepartamentosFiltrados = listaDeDepartamentosFiltrados;
	}

	public void novoCargo(){
		cargo = new Cargo();	
	}
	
	public void salvar() {
		cargoService.salvar(cargo);
		listaDeCargos=cargoService.obterTodos();
		MessagesUtil.addInfoMessage("Cargo salvo com sucesso.");
	}

	public void excluir() {
		try {
			cargoService.excluir(cargo);
			listaDeCargos=cargoService.obterTodos();
			MessagesUtil.addInfoMessage("Cargo excluído com sucesso.");
		} catch (ServiceException e) {
			MessagesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			MessagesUtil.addErrorMessage("Erro ao excluir cargo.");
		}
	}
}
