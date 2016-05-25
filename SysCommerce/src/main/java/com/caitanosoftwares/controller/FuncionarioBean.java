package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Cargo;
import com.caitanosoftwares.entity.Endereco;
import com.caitanosoftwares.entity.Funcionario;
import com.caitanosoftwares.service.CargoService;
import com.caitanosoftwares.service.FuncionarioService;

@Named
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private CargoService cargoService;

	private List<Funcionario> listaDeFuncionarios;

	private List<Cargo> listaDeCargos;

	private String pesq = "";

	private Funcionario funcionario = new Funcionario(new Endereco(),new Cargo());

	@PostConstruct
	public void init(){
		listaDeCargos= cargoService.obterTodos();
	}

	private List<Funcionario> preencherLista() {
		return listaDeFuncionarios = funcionarioService.obterTodos();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario Funcionario) {
		this.funcionario = Funcionario;
	}

	public List<Cargo> getListaDeCargos() {
		return listaDeCargos;
	}
	
	public List<Funcionario> getListaDeFuncionarios() {

		preencherLista();
		return listaDeFuncionarios.stream().filter(funcionario -> {
			return funcionario.getNome().toLowerCase().contains(pesq.toLowerCase());
		}).collect(Collectors.toList());
	}

	public String getPesq() {
		return pesq;
	}

	public void setPesq(String pesq) {
		this.pesq = pesq;
	}

	public String adicionar() {
		funcionarioService.salvar(funcionario);
		funcionario = new Funcionario();
		return "funcionario.xhtml?faces-redirect=true";
	}

	public void excluir(Funcionario funcionario) {
		funcionarioService.excluir(funcionario);
		listaDeFuncionarios.clear();
	}

	public String alterar() {
		funcionarioService.alterar(funcionario);
		funcionario = new Funcionario();
		return "funcionario.xhtml?faces-redirect=true";
	}

}
