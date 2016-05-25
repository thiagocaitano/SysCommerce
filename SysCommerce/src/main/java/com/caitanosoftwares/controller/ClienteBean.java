package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Cliente;
import com.caitanosoftwares.entity.Endereco;
import com.caitanosoftwares.service.ClienteService;

@Named
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private ClienteService clienteService;

	private List<Cliente> listaDeClientees;

	private String pesq = "";

	private Cliente cliente = new Cliente(new Endereco());


	private List<Cliente> preencherLista() {
		return listaDeClientees = clienteService.obterTodos();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente Cliente) {
		this.cliente = Cliente;
	}

	public List<Cliente> getListaDeClientes() {

		preencherLista();
		return listaDeClientees.stream().filter(cliente -> {
			return cliente.getNome().toLowerCase().contains(pesq.toLowerCase());
		}).collect(Collectors.toList());
	}

	public String getPesq() {
		return pesq;
	}

	public void setPesq(String pesq) {
		this.pesq = pesq;
	}

	public String adicionar() {
		clienteService.salvar(cliente);
		cliente = new Cliente();
		return "cliente.xhtml?faces-redirect=true";
	}

	public void excluir(Cliente Cliente) {
		clienteService.excluir(Cliente);
		listaDeClientees.clear();
	}

	public String alterar() {
		clienteService.alterar(cliente);
		cliente = new Cliente();
		return "cliente.xhtml?faces-redirect=true";
	}

}
