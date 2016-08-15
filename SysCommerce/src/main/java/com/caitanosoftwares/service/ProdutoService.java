package com.caitanosoftwares.service;

import java.util.List;

import com.caitanosoftwares.entity.Produto;
import com.caitanosoftwares.service.interfaces.Service;

public interface ProdutoService extends Service<Produto, Long> {

	public List<Produto> obterProdutosComFornecedores();

}
