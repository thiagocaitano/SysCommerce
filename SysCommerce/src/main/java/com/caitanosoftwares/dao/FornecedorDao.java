package com.caitanosoftwares.dao;

import java.util.List;

import com.caitanosoftwares.dao.interfaces.Dao;
import com.caitanosoftwares.entity.Fornecedor;

public interface FornecedorDao extends Dao<Fornecedor, Long> {
	

	public List<Fornecedor> obterFornecedoresQueNaoEstaoNaLista(List<Fornecedor> fornecedores);

	public List<Fornecedor> obterFornecedoresPorRazaoOuCnpj(String razao, String cnpj);

	public Fornecedor obterFornecedorComProdutos(Fornecedor fornecedor);

}
