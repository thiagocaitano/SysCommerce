package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Cargo;
import com.caitanosoftwares.entity.Funcionario;
import com.caitanosoftwares.exception.ServiceException;
import com.caitanosoftwares.service.CargoService;
import com.caitanosoftwares.service.FuncionarioService;
import com.caitanosoftwares.util.jsf.MessagesUtil;

@Named
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private CargoService cargoService;

	private List<Funcionario> listaDeFuncionarios;

	private List<Funcionario> listaDeFuncionariosFiltrados;

	private List<Cargo> listaDeCargos;

	private Funcionario funcionario = new Funcionario();

	@PostConstruct
	public void init(){
		listaDeCargos= cargoService.obterTodos();
		listaDeFuncionarios = funcionarioService.obterTodos();
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
		return listaDeFuncionarios;
	}

	public List<Funcionario> getListaDeFuncionariosFiltrados() {
		return listaDeFuncionariosFiltrados;
	}

	public void setListaDeFuncionariosFiltrados(List<Funcionario> listaDeFuncionariosFiltrados) {
		this.listaDeFuncionariosFiltrados = listaDeFuncionariosFiltrados;
	}

	public void novoFuncionario(){
		funcionario = new Funcionario();
	}
	
	public void salvar() {
		funcionarioService.salvar(funcionario);
		listaDeFuncionarios = funcionarioService.obterTodos();
		MessagesUtil.addInfoMessage("Funcionário salvo com sucesso.");
	}

	public void excluir(Funcionario funcionario) {
		try {
			funcionarioService.excluir(funcionario);
			listaDeFuncionarios = funcionarioService.obterTodos();
			MessagesUtil.addInfoMessage("Funcionário excluído com sucesso.");
		} catch (ServiceException e) {
			MessagesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			MessagesUtil.addErrorMessage("Erro ao excluir funcionário.");
		}
	}

}
