package com.caitanosoftwares.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor {

	@Id
	@GeneratedValue
	@Column(name = "id_fornecedor")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cnpj")
	private String cnpj;

	@Embedded
	private Endereco endereco;

	@Column(name = "telefone1")
	private String telefone1;

	@Column(name = "telefone2")
	private String telefone2;

	@Column(name = "celular")
	private String celular;

	@Column(name = "email")
	private String email;

	@Column(name = "razao_social")
	private String razaoSocial;

	@Column(name = "pessoa_contato")
	private String pessoaContato;
	
	@ManyToMany(mappedBy="listaDeFornecedores")
	private List<Produto> listaDeProdutosFornecidos;

	public Fornecedor() {
		endereco = new Endereco();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getPessoaContato() {
		return pessoaContato;
	}

	public void setPessoaContato(String pessoaContato) {
		this.pessoaContato = pessoaContato;
	}

	public List<Produto> getListaDeProdutosFornecidos() {
		return listaDeProdutosFornecidos;
	}

	public void setListaDeProdutosFornecidos(List<Produto> listaDeProdutosFornecidos) {
		this.listaDeProdutosFornecidos = listaDeProdutosFornecidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.razaoSocial;
	}

}
