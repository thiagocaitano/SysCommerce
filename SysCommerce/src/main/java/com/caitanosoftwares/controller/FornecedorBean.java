package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Fornecedor;
import com.caitanosoftwares.exception.ServiceException;
import com.caitanosoftwares.service.FornecedorService;
import com.caitanosoftwares.util.jsf.MessagesUtil;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private FornecedorService fornecedorService;

	private List<Fornecedor> listaDeFornecedores;
	
	private List<Fornecedor> listaDeFornecedoresFiltrados;

	private Fornecedor fornecedor = new Fornecedor();

	@PostConstruct
	private void init() {
		listaDeFornecedores = fornecedorService.obterTodos();
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor Fornecedor) {
		this.fornecedor = Fornecedor;
	}

	public List<Fornecedor> getListaDeFornecedors() {
		return listaDeFornecedores;
	}

	public List<Fornecedor> getListaDeFornecedoresFiltrados() {
		return listaDeFornecedoresFiltrados;
	}

	public void setListaDeFornecedoresFiltrados(List<Fornecedor> listaDeFornecedoresFiltrados) {
		this.listaDeFornecedoresFiltrados = listaDeFornecedoresFiltrados;
	}

	public void novoFornecedor(){
		fornecedor = new Fornecedor();
	}
	
	public void salvar() {
		fornecedorService.salvar(fornecedor);
		listaDeFornecedores = fornecedorService.obterTodos();
		MessagesUtil.addInfoMessage("Fornecedor salvo com sucesso.");
	}

	public void excluir() {
		try {
			fornecedorService.excluir(fornecedor);
			listaDeFornecedores = fornecedorService.obterTodos();
			MessagesUtil.addInfoMessage("Fornecedor excluído com sucesso.");
		} catch (ServiceException e) {
			MessagesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			MessagesUtil.addErrorMessage("Erro ao excluir o fornecedor.");
		}
	}

}
