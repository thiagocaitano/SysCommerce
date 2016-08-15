package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.caitanosoftwares.entity.Cliente;
import com.caitanosoftwares.exception.ServiceException;
import com.caitanosoftwares.service.ClienteService;
import com.caitanosoftwares.util.jsf.MessagesUtil;

@Named
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private ClienteService clienteService;

	private List<Cliente> listaDeClientes;

	private List<Cliente> listaDeClientesFiltrados;

	private Cliente cliente = new Cliente();

	@PostConstruct
	private void init() {
		
		listaDeClientes = clienteService.obterTodos();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente Cliente) {
		this.cliente = Cliente;
	}

	public List<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}

	public List<Cliente> getListaDeClientesFiltrados() {
		return listaDeClientesFiltrados;
	}

	public void setListaDeClientesFiltrados(List<Cliente> listaDeClientesFiltrados) {
		this.listaDeClientesFiltrados = listaDeClientesFiltrados;
	}
	
	public void novoCliente(){
		cliente = new Cliente();
	}

	public void salvar() {
		clienteService.salvar(cliente);
		listaDeClientes= clienteService.obterTodos();
		MessagesUtil.addInfoMessage("Cliente cadastrado com sucesso.");
	}

	public void excluir(Cliente Cliente) {
		try {
			clienteService.excluir(Cliente);
			listaDeClientes= clienteService.obterTodos();
			MessagesUtil.addErrorMessage("Cliente excluído com sucesso.");
		} catch (ServiceException e) {
			MessagesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			MessagesUtil.addErrorMessage("Erro ao excluir cliente.");
		}
	}

}
