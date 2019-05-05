package br.com.cv.model;

import java.sql.Date;

public class Produto {

	private long id;
	private long idCategoria;
	private String sku;
	private String nome;
	private double preco;
	private long quantidade;
	private String descricao;
	private Date dataCadastro;
	private String status;

	public Produto(long id,long idCategoria, String nome,String sku, double preco,Long quantidade, String descricao,Date dataCadastro,String status) {
		this.id=id;
		this.idCategoria = idCategoria;
		this.nome = nome;
		this.sku=sku;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.dataCadastro=dataCadastro;
		this.status=status;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

}
