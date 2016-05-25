package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Endereco;
import com.caitanosoftwares.entity.Fornecedor;
import com.caitanosoftwares.service.FornecedorService;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private FornecedorService fornecedorService;

	private List<Fornecedor> listaDeFornecedores;

	private String pesq = "";

	private Fornecedor fornecedor = new Fornecedor(new Endereco());


	private List<Fornecedor> preencherLista() {
		return listaDeFornecedores = fornecedorService.obterTodos();
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor Fornecedor) {
		this.fornecedor = Fornecedor;
	}

	public List<Fornecedor> getListaDeFornecedors() {

		preencherLista();
		return listaDeFornecedores.stream().filter(fornecedor -> {
			return fornecedor.getNome().toLowerCase().contains(pesq.toLowerCase());
		}).collect(Collectors.toList());
	}

	public String getPesq() {
		return pesq;
	}

	public void setPesq(String pesq) {
		this.pesq = pesq;
	}

	public String adicionar() {
		fornecedorService.salvar(fornecedor);
		fornecedor = new Fornecedor();
		return "fornecedor.xhtml?faces-redirect=true";
	}

	public void excluir(Fornecedor Fornecedor) {
		fornecedorService.excluir(Fornecedor);
		listaDeFornecedores.clear();
	}

	public String alterar() {
		fornecedorService.alterar(fornecedor);
		fornecedor = new Fornecedor();
		return "fornecedor.xhtml?faces-redirect=true";
	}

}
