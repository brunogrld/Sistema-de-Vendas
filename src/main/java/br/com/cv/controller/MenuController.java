package br.com.cv.controller;

import br.com.cv.main.Main;
import javafx.fxml.FXML;

public class MenuController {
	
	
	@FXML
	protected void onLogout(){
		Main.changePage("login");
	}

}
