package com.caitanosoftwares.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.caitanosoftwares.entity.Fornecedor;
import com.caitanosoftwares.entity.Marca;
import com.caitanosoftwares.entity.Produto;
import com.caitanosoftwares.service.FornecedorService;
import com.caitanosoftwares.service.MarcaService;
import com.caitanosoftwares.service.ProdutoService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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

	private List<Marca> listaDeMarcas;

	private DualListModel<Fornecedor> dualListTodosFornecedores;

	private String pesq = "";

	private Produto produto = new Produto();

	@PostConstruct
	public void init(){
		listaDeMarcas = marcaService.obterTodos();
	}

	private List<Produto> preencherLista() {
		return listaDeProdutos = produtoService.obterTodos();
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

		preencherLista();
		return listaDeProdutos.stream().filter(produto -> {
			return produto.getDescricao().toLowerCase().contains(pesq.toLowerCase());
		}).collect(Collectors.toList());
	}

	public String getPesq() {
		return pesq;
	}

	public void setPesq(String pesq) {
		this.pesq = pesq;
	}

	public String adicionar() {
		produtoService.salvar(produto);
		produto = new Produto();
		return "produto.xhtml?faces-redirect=true";
	}

	public void excluir(Produto produto) {
		produtoService.excluir(produto);
		listaDeProdutos.clear();
	}

	public String alterar() {
		produtoService.alterar(produto);
		produto = new Produto();
		return "produto.xhtml?faces-redirect=true";
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
	/*	JasperReport report = JasperCompileManager.compileReport("reports/produto.jrxml");		
		JasperPrint print = JasperFillManager.fillReport(report, null,new JRBeanCollectionDataSource(listaDeProdutos));	
		JasperExportManager.exportReportToPdfFile(print,"C:/Users/Thiago Caitano/Documents");
		*/
	}
	
}
