package br.com.cv.controller;


import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import br.com.cv.main.Main;
import br.com.cv.dao.LoginDAO;
import br.com.cv.util.Hash;

public class LoginController {
	
	/** The actiontarget. */
	@FXML
	private Text returnMsg;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

	@FXML
	protected void onAuth(ActionEvent event) throws IOException, SQLException {
		 returnMsg.setText("");
	   if(username.getText().isEmpty()) {
		   returnMsg.setText("O campo nome é obrigatório.");
	   }else if(password.getText().isEmpty()) {
		   returnMsg.setText("O campo senha é obrigatório.");
	   }else {
		 String pass = Hash.getHash(password.getText());
		  if(LoginDAO.getInstancia().login(username.getText(),pass)){
			 this.clearFields();
		     Main.changePage("menu");
		  }else {
			returnMsg.setText("Login ou senha incorreto.");
		  }
	   }
	}
	


	public void clearFields(){
		    username.setText("");
		    password.setText("");
		    returnMsg.setText("");
	    }

}
