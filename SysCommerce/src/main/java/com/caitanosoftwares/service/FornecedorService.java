package com.caitanosoftwares.service;

import java.util.List;

import com.caitanosoftwares.entity.Fornecedor;
import com.caitanosoftwares.service.interfaces.Service;

public interface FornecedorService extends Service<Fornecedor, Long> {

	public List<Fornecedor> obterFornecedoresQueNaoEstaoNaLista(List<Fornecedor> fornecedores);

	public List<Fornecedor> obterFornecedoresPorRazaoOuCnpj(String razao, String cnj);

	public Fornecedor obterFornecedorComProdutos(Fornecedor fornecedor);
	
}
