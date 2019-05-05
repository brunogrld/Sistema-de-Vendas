package br.com.cv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.cv.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class MenuController implements Initializable {
	
	@FXML
   // private AnchorPane content;
	 private HBox content;
	
	@FXML
	protected void onLogout(){
		Main.changePage("login");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	  onOpenPage("home");
	}

	@FXML
	protected void onOpenHome() {
		
	}
	@FXML
	protected void onOpenSale(){
		onOpenPage("sale");
	}
	@FXML
	protected void onOpenProduct(){
		onOpenPage("product");
	}
	@FXML
	protected void onOpenClient(){
		onOpenPage("client");
	}
	
	
	protected void onOpenPage(String page) {
		String file;
		switch(page) {
		case "sale":
		 file="/br/com/cv/view/Vendas.fxml";
		break;	
		case "product":
		 file="/br/com/cv/view/Produtos.fxml";
		 break;	
		case "client":
			 file="/br/com/cv/view/Clientes.fxml";
		break;
		default:
		 file="/br/com/cv/view/Home.fxml";
		}	
    try {	
    	content.getChildren().clear();
    	HBox root = FXMLLoader.load(getClass().getResource(file));
        content.getChildren().add(root);
    } catch (IOException e) {
    	System.out.println(e.getMessage());
    }

    }
	
}
