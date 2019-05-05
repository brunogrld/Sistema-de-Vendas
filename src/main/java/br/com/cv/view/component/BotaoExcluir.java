package br.com.cv.view.component;

import br.com.cv.controller.ProdutoController;
import br.com.cv.iface.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class BotaoExcluir implements Callback<TableColumn<Object, Integer>, TableCell<Object, Integer>> {
	
    private Controller controller;
   // public BotaoExcluir(Controller controller) {
    public BotaoExcluir() {
      //  this.controller = controller;
    }

    @Override
    public TableCell<Object, Integer> call(TableColumn<Object, Integer> param) {
        TableCell<Object, Integer> cell = new TableCell<Object, Integer>() {

            @Override
            public void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                    return;
                }

                Button btn = new Button();
                Image img = new Image(getClass().getResourceAsStream("../storage/icons/rubbish-bin.png"));
                btn.setGraphic(new ImageView(img));
                btn.setStyle("-fx-background-color: transparent;");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controller.excluir(item);
                    }
                });
                if (item == 0) {
                    btn.setDisable(true);
                }
                setGraphic(btn);
                setText(null);
            }
        };
        return cell;
    }


}
