package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.caitanosoftwares.entity.Fornecedor;
import com.caitanosoftwares.entity.Marca;
import com.caitanosoftwares.entity.Produto;
import com.caitanosoftwares.exception.ServiceException;
import com.caitanosoftwares.reports.ReportUtil;
import com.caitanosoftwares.service.FornecedorService;
import com.caitanosoftwares.service.MarcaService;
import com.caitanosoftwares.service.ProdutoService;
import com.caitanosoftwares.util.jsf.MessagesUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1572378275846375709L;

	@Inject
	private ProdutoService produtoService;

	@Inject
	private MarcaService marcaService;

	@Inject
	private FornecedorService fornecedorService;

	private List<Produto> listaDeProdutos;
	
	private List<Produto> listaDeProdutosFiltrados;

	private List<Marca> listaDeMarcas;

	private DualListModel<Fornecedor> dualListTodosFornecedores;

	private Produto produto = new Produto();

	@PostConstruct
	public void init(){
		listaDeMarcas = marcaService.obterTodos();
		listaDeProdutos = produtoService.obterProdutosComFornecedores();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto Produto) {
		this.produto = Produto;
	}

	public List<Marca> getListaDeMarcas() {
		return listaDeMarcas;
	}

	public DualListModel<Fornecedor> getDualListTodosFornecedores() {
		
		List<Fornecedor> listaDeFornecedoresQueFornecemOProduto = produto.getListaDeFornecedores();
		List<Fornecedor> listaDeFornecedoresQueNaoFornecemOProduto = 
				fornecedorService.obterFornecedoresQueNaoEstaoNaLista(listaDeFornecedoresQueFornecemOProduto);
		dualListTodosFornecedores = new DualListModel<>(listaDeFornecedoresQueNaoFornecemOProduto, listaDeFornecedoresQueFornecemOProduto);  
		return dualListTodosFornecedores;

	}

	public void setDualListTodosFornecedores(DualListModel<Fornecedor> dualListTodosFornecedores) {
		this.dualListTodosFornecedores = dualListTodosFornecedores;
	}

	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public List<Produto> getListaDeProdutosFiltrados() {
		return listaDeProdutosFiltrados;
	}

	public void setListaDeProdutosFiltrados(List<Produto> listaDeProdutosFiltrados) {
		this.listaDeProdutosFiltrados = listaDeProdutosFiltrados;
	}

	public void novoProduto(){
		produto = new Produto();
	}
	
	public void salvar() {
		produtoService.salvar(produto);
		listaDeProdutos=produtoService.obterProdutosComFornecedores();
		MessagesUtil.addInfoMessage("Produto salvo com sucesso.");
	}

	public void excluir() {
			try {
				produtoService.excluir(produto);
				listaDeProdutos=produtoService.obterProdutosComFornecedores();
				MessagesUtil.addInfoMessage("Produto excluído com sucesso.");
			} catch (ServiceException e) {
				MessagesUtil.addErrorMessage(e.getMessage());
			} catch (Exception e) {
				MessagesUtil.addErrorMessage("Erro ao excluir produto.");
			}
	}
		

	public void onTransfer(TransferEvent event) {
		produto.setListaDeFornecedores(dualListTodosFornecedores.getTarget());
	 }
	
	public void calcularValores() {


		produto.setPrecoVenda(produto.getCusto());
		
		if (produto.getValorMargem() != 0)
			produto.setPrecoVenda(produto.getCusto() + (produto.getValorMargem()/ 100) * produto.getCusto());
		
		if(produto.getPrecoVenda()>produto.getCusto())
		produto.setValorLucro(produto.getPrecoVenda() - produto.getCusto());

	}
	
	public void imprimirTodos() throws JRException{
		Map<String, Object> parametros= new HashMap<>();
		ReportUtil.executarRelatorio("/WEB-INF/reports/produto.jasper", parametros, "lista de produtos", new JRBeanCollectionDataSource(listaDeProdutos));
	}
	
}
