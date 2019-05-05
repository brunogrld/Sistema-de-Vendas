package br.com.cv.controller;

import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.cv.dao.ProdutoDAO;

//import com.jdenner.model.dao.VendaDAO;
//import com.jdenner.view.component.BotaoEditar;
//import com.jdenner.view.component.BotaoExcluir;
//import com.jdenner.view.component.ColunaData;
//import com.jdenner.view.component.ColunaValor;

import br.com.cv.model.Produto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
//import br.com.cv.iface.Controller;
import javafx.util.Callback;

public class ProdutoController implements Initializable {
	@FXML
	private Text formTitle;
	@FXML
	private TextField txtPreco;

	@FXML
	private ChoiceBox<?> txtCategoria;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtQuant;

	@FXML
	private TextField txtSku;

	@FXML
	private TextArea txtDescricao;

	/// tabela
	private long idSelected = 0;

	@FXML
	private TextField filterField;

	@FXML
	private TableView<Produto> tableProducts;

	private ObservableList<Produto> obsTableClientes = FXCollections.observableArrayList();

	@FXML
	private TableColumn<Produto, String> colNome;

	@FXML
	private TableColumn<Produto, String> colPreco;

	@FXML
	private TableColumn<Produto, String> colSku;

	@FXML
	private TableColumn<Produto, String> colquant;

	@FXML
	private TableColumn<Produto, String> colStatus;

	@FXML
	private TableColumn colBtDelete;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		/*
		 * filterField.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent e) { e.
		 * System.out.println("dxhshs"+filterField.getText()); //
		 * l.setText(b.getText()); } });
		 */

		loadTable();
	}

	private void loadTable() {
		if (!obsTableClientes.isEmpty() || !tableProducts.getColumns().isEmpty()) {
			obsTableClientes.clear();
			tableProducts.getColumns().removeAll(colNome, colPreco, colSku, colquant, colStatus, colBtDelete);
		}
		colNome = new TableColumn<>("Nome");
		colNome.setMinWidth(60);
		colPreco = new TableColumn<>("Preço");
		colPreco.setMinWidth(40);
		colSku = new TableColumn<>("SKU");
		colquant = new TableColumn<>("Quantidade");
		colStatus = new TableColumn<>("Status");
		colStatus.setMinWidth(40);
		colBtDelete = new TableColumn<>("Deletar");

		colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
		colPreco.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getPreco())));
		colSku.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSku()));
		colquant.setCellValueFactory(data -> new SimpleStringProperty(Long.toString(data.getValue().getQuantidade())));
		colStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));
   
		tableProducts.setRowFactory(tv -> {
			TableRow<Produto> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				// if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY &&
				// event.getClickCount() == 2) {
				if (!row.isEmpty() && event.getClickCount() == 1) {
					Produto clickedRow = row.getItem();
					itemSelected(clickedRow);
				}
			});
			return row;
		});

		Callback<TableColumn<Produto, String>, TableCell<Produto, String>> cellFactory = new Callback<TableColumn<Produto, String>, TableCell<Produto, String>>() {
			@Override
			public TableCell call(final TableColumn<Produto, String> param) {
				final TableCell<Produto, String> cell = new TableCell<Produto, String>() {

					@Override
					public void updateItem(String item, boolean empty) {
						Button btn = new Button();
						this.setAlignment(Pos.CENTER);
						Image img = new Image(getClass().getResourceAsStream("./../storage/icons/delete.png"), 15, 15,
								false, false);
						btn.setGraphic(new ImageView(img));
						// btn.setStyle("-fx-background-color: transparent;");

						{
							btn.setOnMouseClicked(event -> {
								// if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY) {
								if (event.getClickCount() == 2) {
									Produto clickedRow = tableProducts.getItems().get(getIndex());
									if (deleteItem(clickedRow)) {
										tableProducts.getItems().remove(getIndex());
									}
								}
							});
							/*
							 * btn.setOnAction(event -> { tableProducts.getItems().remove(getIndex()); });
							 */
						}

						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};

		colBtDelete.setCellFactory(cellFactory);

		tableProducts.getColumns().addAll(colNome, colPreco, colSku, colquant, colStatus, colBtDelete);
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos = (List<Produto>) produtoDAO.listar(filterField.getText());

		for (int i = 0; i < produtos.size(); i++) {
			obsTableClientes.add(produtos.get(i));
		}

		tableProducts.setItems(obsTableClientes);
	}

	@FXML
	void onSave() {
		// String idCategoria = txtCategoria.getId();
		String nome = txtNome.getText();
		String strPreco = txtPreco.getText();
		String sku = txtSku.getText();
		String descricao = txtDescricao.getText();
		String strQuantidade = txtQuant.getText();

		if (nome.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo nome é obrigatório!");
		} else if (strPreco.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo preço é obrigatório!");
		} else if (sku.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo SKU é obrigatório!");
		} else if (strQuantidade.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo quantidade é obrigatório!");
		} else {
			double preco = Double.parseDouble(strPreco);
			long quantidade = Long.parseLong(strQuantidade);
			Date dataCadastro = new Date(System.currentTimeMillis());

			Produto produto = new Produto(0, 1, nome, sku, preco, quantidade, descricao, dataCadastro, "SIM");

			if (idSelected != 0) {
				if (ProdutoDAO.getInstancia().editar(idSelected, produto)) {
					loadTable();
					onClear();
					JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível realizar a alteração!");
				}
			} else {
				if (ProdutoDAO.getInstancia().cadastrar(produto)) {
					loadTable();
					onClear();
					JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro!");
				}
			}
		}
	}

	protected boolean deleteItem(Produto produto) {
		Object[] options = { "Sim", "Não" };
		int opcao = JOptionPane.showOptionDialog(null, "Deseja deletar o item '" + produto.getNome() + "'?", "Deletar",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		if (opcao == 0) {
			if (ProdutoDAO.getInstancia().excluir(produto.getId())) {
				JOptionPane.showMessageDialog(null, "Item deletado com sucesso!");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Não foi possível deletar o item!");
			}
		}
		return false;
	}

	@FXML
	protected void onFilter() {
		loadTable();
	}

	@FXML
	protected void onNewRecord() {
		onClear();
	}

	@FXML
	void onClear() {
		idSelected = 0;
		formTitle.setText("Inserção");
		txtNome.clear();
		txtPreco.clear();
		txtSku.clear();
		txtQuant.clear();
		txtDescricao.clear();
	}

	@FXML
	protected void itemSelected(Produto produto) {
		onClear();
		idSelected = produto.getId();
		formTitle.setText("Edição");
		txtNome.setText(produto.getNome());
		txtPreco.setText(Double.toString(produto.getPreco()));
		txtSku.setText(produto.getSku());
		txtQuant.setText(Long.toString(produto.getQuantidade()));
		txtDescricao.setText(produto.getDescricao());
	}
}
