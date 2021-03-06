package com.caitanosoftwares.dao;

import java.util.List;

import com.caitanosoftwares.dao.interfaces.Dao;
import com.caitanosoftwares.entity.Produto;

public interface ProdutoDao extends Dao<Produto, Long> {

	public Produto obterProdutoComFornecedoresPorId(Long id);
	
	public List<Produto> obterProdutosComFornecedores();

}
