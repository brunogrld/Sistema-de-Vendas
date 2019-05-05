package br.com.cv.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.cv.util.ConnectionBD;

public class LoginDAO{
	
	private static LoginDAO instancia;

	public static LoginDAO getInstancia() {
		if (instancia == null)
			instancia = new LoginDAO();
		return instancia;
	}
	
	public boolean login(String user,String password) throws SQLException {
		  Connection conn = ConnectionBD.getConnection();
		  Statement statement =  conn.createStatement();
		  try {
			  ResultSet result = statement.executeQuery("SELECT * FROM sys_usuario WHERE status='SIM' AND nome= '" + user + "'");
			  result.first();
			    if (result.getString("senha").equals(password)) {
			    	return true;
			    } else {
			    	System.out.println("Senha inválida! Tente novamente.");
			    	return false;
			    }
			} catch (Exception e) {
				 System.out.println("Erro: "+e.getMessage());
				return false;
			}	
	}

}
