package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Cargo;
import com.caitanosoftwares.entity.Departamento;
import com.caitanosoftwares.service.CargoService;
import com.caitanosoftwares.service.DepartamentoService;

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

	private String pesq = "";

	private Cargo cargo = new Cargo();
	
	@PostConstruct
	public void init(){
		
		listaDeDepartamentos = departamentoService.obterTodos();
	}


	private List<Cargo> preencherLista() {
		return listaDeCargos = cargoService.obterTodos();
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo Cargo) {
		this.cargo = Cargo;
	}

	public List<Cargo> getListaDeCargos() {

		preencherLista();
		return listaDeCargos.stream().filter(cargo -> {
			return cargo.getNome().toLowerCase().contains(pesq.toLowerCase());
		}).collect(Collectors.toList());
	}
	
	public List<Departamento> getListaDepartamento(){
		return listaDeDepartamentos;
	}

	public String getPesq() {
		return pesq;
	}

	public void setPesq(String pesq) {
		this.pesq = pesq;
	}

	public String adicionar() {
		cargoService.salvar(cargo);
		cargo = new Cargo();
		return "cargo.xhtml?faces-redirect=true";
	}

	public void excluir(Cargo Cargo) {
		cargoService.excluir(Cargo);
		listaDeCargos.clear();
	}

	public String alterar() {
		cargoService.alterar(cargo);
		cargo = new Cargo();
		return "cargo.xhtml?faces-redirect=true";
	}

}
