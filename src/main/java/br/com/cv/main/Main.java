package br.com.cv.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main  extends Application {
	
	   private final static Main instance = new Main();
	    public static Main getInstance() {
	        return instance;
	    }
	
    private static Stage newStage;
    
    private static Scene loginScene;
    private static Scene menuScene;

	
    @Override
    public void start(Stage stage) throws Exception {
         newStage = stage;
      /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/cv/view/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root,700,550);
        */
    	Parent login =  FXMLLoader.load(getClass().getResource("/br/com/cv/view/Login.fxml"));
         loginScene = new Scene(login,700,550);
    	
    	Parent menu =  FXMLLoader.load(getClass().getResource("/br/com/cv/view/Menu.fxml"));
    	 menuScene = new Scene(menu);
    	
        stage.setTitle("Controle Vendas");
        Image appIcon = new Image(getClass().getResourceAsStream("../storage/icons/account.png"));
        stage.getIcons().add(appIcon);
        stage.setScene(loginScene);
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }
    
    public static void changePage(String name) {
    	if(name=="menu") {
    		newStage.setScene(menuScene);
    	}else if(name=="login") {
    		newStage.setScene(loginScene);	
    	}
    }
	public static void main(String[] args) {
		launch(args);
	}

}
