package br.com.cv.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.cv.dao.ClienteDAO;
import br.com.cv.model.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.Callback;

public class ClienteController implements Initializable {

	@FXML
	private Text formTitle;
	
	@FXML
	private ChoiceBox<?> txtTipo;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtCpfCnpj;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TextArea txtDescricao;

	/// tabela
	private long idSelected = 0;

	@FXML
	private TextField filterField;

	@FXML
	private TableView<Cliente> tableProducts;

	private ObservableList<Cliente> obsTableClientes = FXCollections.observableArrayList();

	@FXML
	private TableColumn<Cliente, String> colNome;

	@FXML
	private TableColumn<Cliente, String> colCpfCnpj;

	@FXML
	private TableColumn<Cliente, String> colEmail;

	@FXML
	private TableColumn<Cliente, String> colStatus;

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
			tableProducts.getColumns().removeAll(colNome,colCpfCnpj,colEmail , colStatus, colBtDelete);
		}
		colNome = new TableColumn<>("Nome");
		colNome.setMinWidth(60);
		colCpfCnpj = new TableColumn<>("CPF/CNPJ");
		colCpfCnpj.setMinWidth(40);
		colEmail = new TableColumn<>("E-mail");
		colStatus = new TableColumn<>("Status");
		colStatus.setMinWidth(40);
		colBtDelete = new TableColumn<>("Deletar");

		colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
		colCpfCnpj.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCpfCnpj()));
		colEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
		colStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));
   
		tableProducts.setRowFactory(tv -> {
			TableRow<Cliente> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				// if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY &&
				// event.getClickCount() == 2) {
				if (!row.isEmpty() && event.getClickCount() == 1) {
					Cliente clickedRow = row.getItem();
					itemSelected(clickedRow);
				}
			});
			return row;
		});

		Callback<TableColumn<Cliente, String>, TableCell<Cliente, String>> cellFactory = new Callback<TableColumn<Cliente, String>, TableCell<Cliente, String>>() {
			@Override
			public TableCell call(final TableColumn<Cliente, String> param) {
				final TableCell<Cliente, String> cell = new TableCell<Cliente, String>() {

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
									Cliente clickedRow = tableProducts.getItems().get(getIndex());
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

		tableProducts.getColumns().addAll(colNome, colCpfCnpj, colEmail, colStatus, colBtDelete);
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> clientes = (List<Cliente>) clienteDAO.listar(filterField.getText());

		for (int i = 0; i < clientes.size(); i++) {
			obsTableClientes.add(clientes.get(i));
		}

		tableProducts.setItems(obsTableClientes);
	}

	@FXML
	void onSave() {
		// String idCategoria = txtCategoria.getId();
		String nome = txtNome.getText();
		String cpfCnpj = txtCpfCnpj.getText();
		String email = txtEmail.getText();
		String telefone = txtTelefone.getText();
		String descricao = txtDescricao.getText();

		if (nome.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo nome é obrigatório!");
		} else if (cpfCnpj.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo CPF/CNPJ é obrigatório!");
		} else if (email.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo e-mail é obrigatório!");
		} else if (telefone.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo telefone é obrigatório!");
		} else {
			Date dataCadastro = new Date(System.currentTimeMillis());

			Cliente cliente = new Cliente(0, "1", nome, cpfCnpj, email, telefone, descricao, dataCadastro, "SIM");

			if (idSelected != 0) {
				if (ClienteDAO.getInstancia().editar(idSelected, cliente)) {
					loadTable();
					onClear();
					JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível realizar a alteração!");
				}
			} else {
				if (ClienteDAO.getInstancia().cadastrar(cliente)) {
					loadTable();
					onClear();
					JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro!");
				}
			}
		}
	}

	protected boolean deleteItem(Cliente cliente) {
		Object[] options = { "Sim", "Não" };
		int opcao = JOptionPane.showOptionDialog(null, "Deseja deletar o item '" + cliente.getNome() + "'?", "Deletar",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		if (opcao == 0) {
			if (ClienteDAO.getInstancia().excluir(cliente.getId())) {
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
		txtEmail.clear();
		txtCpfCnpj.clear();
		txtTelefone.clear();
		txtDescricao.clear();
	}

	@FXML
	protected void itemSelected(Cliente cliente) {
		onClear();
		idSelected = cliente.getId();
		formTitle.setText("Edição");
		txtNome.setText(cliente.getNome());
		txtEmail.setText(cliente.getEmail());
		txtCpfCnpj.setText(cliente.getCpfCnpj());
		txtTelefone.setText(cliente.getTelefone());
		txtDescricao.setText(cliente.getDescricao());
	}
	
	
}
