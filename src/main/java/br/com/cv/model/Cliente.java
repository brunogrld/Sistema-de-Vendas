package br.com.cv.model;

import java.sql.Date;

public class Cliente {
	
	private long id;
	private String tipo;
    private String nome;
    private String cpfCnpj;
    private String email;
    private String telefone;
    private String descricao;
    private Date dataCadastro;
    private String status; 
    
    
    
	public Cliente(long id, String tipo, String nome, String cpfCnpj, String email, String telefone, String descricao,Date dataCadastro,String status) {
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.telefone = telefone;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
		this.status = status;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setgetCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
    
    
}