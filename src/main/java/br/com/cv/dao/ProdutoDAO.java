package br.com.cv.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.cv.model.Produto;
import br.com.cv.util.ConnectionBD;
import br.com.cv.iface.CRUD;


public class ProdutoDAO implements CRUD<Produto> {
	
	private static ProdutoDAO instancia;

	public static ProdutoDAO getInstancia() {
		if (instancia == null)
			instancia = new ProdutoDAO();
		return instancia;
	}


	@Override
	public boolean cadastrar(Produto produto) {
		try {

			Connection conn = ConnectionBD.getConnection();
			String sql = "INSERT INTO api_produtos (id_categoria, sku, nome,preco,quantidade,descricao,data_cadastro,status)"
				             	   + " VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stm.setLong(1, produto.getIdCategoria());
			stm.setString(2, produto.getSku());
			stm.setString(3, produto.getNome());
			stm.setDouble(4, produto.getPreco());
			stm.setLong(5, produto.getQuantidade());
			stm.setString(6, produto.getDescricao());
			stm.setDate(7, produto.getDataCadastro());
			stm.setString(8, produto.getStatus());

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
	public boolean editar(long id, Produto produto) {
		try {

			Connection conn = ConnectionBD.getConnection();
			String sql = "UPDATE api_produtos SET id_categoria=?, sku=?, nome=?,preco=?,quantidade=?,descricao=?,status=? WHERE id="+id;
			PreparedStatement stm = conn.prepareStatement(sql);
			
			stm.setLong(1, produto.getIdCategoria());
			stm.setString(2, produto.getSku());
			stm.setString(3, produto.getNome());
			stm.setDouble(4, produto.getPreco());
			stm.setLong(5, produto.getQuantidade());
			stm.setString(6, produto.getDescricao());
			stm.setString(7, produto.getStatus());

		    int rowAffected = stm.executeUpdate();
		   System.out.println("Alteração realizada com Sucesso!");
			return true;

		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
			return false;
		}
	}

	@Override
	public List<Produto> listar(String filter) {
		String sqlFilter="";
		if(filter!="") {
			sqlFilter = " AND nome LIKE '%"+filter+"%' OR sku LIKE '%"+filter+"%' OR descricao LIKE '%"+filter+"%' ";
		}
		
		List<Produto> produtos = new ArrayList<>();
				try {
		  Connection conn = ConnectionBD.getConnection();
		  Statement statement =  conn.createStatement();
		  ResultSet result = statement.executeQuery("SELECT * FROM api_produtos WHERE status='SIM' "+sqlFilter+" ORDER BY nome ASC");
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
			  long idCategoria = result.getLong("id_categoria");
			  String nome = result.getString("nome");
			  String sku = result.getString("sku");
			  double preco = result.getDouble("preco");
			  Long quantidade = result.getLong("quantidade");
			  String descricao = result.getString("descricao");
			  Date dataCadastro = result.getDate("data_cadastro");
			  String status = result.getString("status");
			 
			  Produto produto = new Produto(id,idCategoria,nome,sku,preco,quantidade,descricao,dataCadastro,status);
			  produtos.add(produto);
		  }
		return produtos;
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
			  PreparedStatement stm = conn.prepareStatement("DELETE FROM api_produtos WHERE id="+id);
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
