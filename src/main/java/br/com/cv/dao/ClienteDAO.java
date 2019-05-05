package br.com.cv.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cv.iface.CRUD;
import br.com.cv.model.Cliente;
import br.com.cv.util.ConnectionBD;

public class ClienteDAO implements CRUD<Cliente> {

	private static ClienteDAO instancia;

	public static ClienteDAO getInstancia() {
		if (instancia == null)
			instancia = new ClienteDAO();
		return instancia;
	}


	@Override
	public boolean cadastrar(Cliente cliente) {
		try {

			Connection conn = ConnectionBD.getConnection();
			String sql = "INSERT INTO api_clientes (nome, cpf_cnpj, email,telefone,descricao,data_cadastro,status,tipo)"
				             	   + " VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getCpfCnpj());
			stm.setString(3, cliente.getEmail());
			stm.setString(4, cliente.getTelefone());
			stm.setString(5, cliente.getDescricao());
			stm.setDate(6, cliente.getDataCadastro());
			stm.setString(7, cliente.getStatus());
			stm.setString(8, cliente.getTipo());

		int rowAffected = stm.executeUpdate();
		  /* if(rowAffected == 1){
			   ResultSet rs = stm.getGeneratedKeys();
               if(rs.next()) {
             //  System.out.println(rs);
              // System.out.println(rs.);
               }
           }*/
		   System.out.println("cadastrado com Sucesso!");
			return true;

		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean editar(long id, Cliente cliente) {
		try {

			Connection conn = ConnectionBD.getConnection();
			String sql = "UPDATE api_clientes SET nome=?, cpf_cnpj=?, email=?,telefone=?,descricao=?,status=?,tipo=? WHERE id="+id;
			PreparedStatement stm = conn.prepareStatement(sql);
			
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getCpfCnpj());
			stm.setString(3, cliente.getEmail());
			stm.setString(4, cliente.getTelefone());
			stm.setString(5, cliente.getDescricao());
			stm.setString(6, cliente.getStatus());
			stm.setString(7, cliente.getTipo());

		    int rowAffected = stm.executeUpdate();
		   System.out.println("Alteração realizada com Sucesso!");
			return true;

		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
			return false;
		}
	}

	@Override
	public List<Cliente> listar(String filter) {
		String sqlFilter="";
		if(filter!="") {
			sqlFilter = " AND nome LIKE '%"+filter+"%' OR cpf_cnpj LIKE '%"+filter+"%' OR email LIKE '%"+filter+"%' OR descricao LIKE '%"+filter+"%' ";
		}
		
		List<Cliente> clientes = new ArrayList<>();
				try {
		  Connection conn = ConnectionBD.getConnection();
		  Statement statement =  conn.createStatement();
		  ResultSet result = statement.executeQuery("SELECT * FROM api_clientes WHERE status='SIM' "+sqlFilter+" ORDER BY nome ASC");
		/*  ArrayList<Telefone> fones= new ArrayList<Telefone>();
			while(result.next()) {
				String nome = result.getString("nome");
				int idade = result.getInt("idade");
				String cpf = result.getString("cpf");
				//Telefone fone = new Telefone();
		       	Aluno aluno = new Aluno(nome,idade,cpf,fones);
		       	alunos.add(aluno);
		 }*/
		  while(result.next()) {
			  long id = result.getLong("id");
			  String tipo = result.getString("tipo");
			  String nome = result.getString("nome");
			  String cpfCnpj = result.getString("cpf_cnpj");
			  String email = result.getString("email");
			  String telefone = result.getString("telefone");
			  String descricao = result.getString("descricao");
			  Date dataCadastro = result.getDate("data_cadastro");
			  String status = result.getString("status");
			 
			  Cliente cliente = new Cliente(id,tipo,nome,cpfCnpj,email,telefone,descricao,dataCadastro,status);
			  clientes.add(cliente);
		  }
		return clientes;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean excluir(long id) {
		try {
			  Connection conn = ConnectionBD.getConnection();
			  PreparedStatement stm = conn.prepareStatement("DELETE FROM api_clientes WHERE id="+id);
			 // if(rowAffected == 1){
			  int rowAffected = stm.executeUpdate();
			   System.out.println("Item deletado com Sucesso!");
			  return true;
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
			return false;
		}
	}

}
