package br.com.cv.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBD {
	
	public static Connection getConnection() {
	
		try{
			String sqlDriver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/cv_servicos?useSSL=false&useTimezone=true&serverTimezone=UTC";
			String user = "root";
			String password = "";
			
			Class.forName(sqlDriver);
			Connection conn = DriverManager.getConnection(url,user,password);
			return conn;			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}

}
