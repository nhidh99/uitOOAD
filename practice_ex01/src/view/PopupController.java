package view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import BUS.*;
import DAO.*;
import DTO.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class PopupController implements Initializable {

	@FXML
	private TableView<ProductDTO> tvProduct;

	@FXML
	private TableColumn<ProductDTO, Number> tcOrderId;

	@FXML
	private TableColumn<ProductDTO, String> tcId;

	@FXML
	private TableColumn<ProductDTO, String> tcName;

	@FXML
	private TableColumn<ProductDTO, Integer> tcNumber;

	@FXML
	private TableColumn<ProductDTO, Integer> tcPrice;

	@FXML
	private TableColumn<ProductDTO, String> tcOrigin;

	@FXML
	private TableColumn<ProductDTO, String> tcListId;

	@FXML
	private TextField tfId;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfNumber;

	@FXML
	private TextField tfPrice;

	@FXML
	private TextField tfOrigin;

	@FXML
	private ComboBox<String> cbList;

	@FXML
	private Button btnBackToMenu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initColumns();
		initComboBoxList();
		initCellEnterEvent();
		handleLoadProducts();
		tvProduct.getSelectionModel().selectFirst();
	}

	private void initColumns() {
		tcOrderId.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Number>(tvProduct.getItems().indexOf(column.getValue()) + 1));
		tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tcNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
		tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		tcOrigin.setCellValueFactory(new PropertyValueFactory<>("origin"));
		tcListId.setCellValueFactory(new PropertyValueFactory<>("listId"));
	}

	private void initComboBoxList() {
		ObservableList<String> names = FXCollections.observableArrayList();
		try {
			for (String name : ListDAO.getListName()) {
				names.add(name);
			}
			cbList.setItems(names);
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách danh mục sản phẩm!");
			alert.setContentText("Lỗi kết nối database!");
			alert.showAndWait();
		}
	}

	private void initCellEnterEvent() {
		tvProduct.getSelectionModel().selectedIndexProperty().addListener(event -> {
			ProductDTO product = tvProduct.getSelectionModel().getSelectedItem();
			tfId.setText(product.getId());
			tfName.setText(product.getName());
			tfNumber.setText(product.getNumber().toString());
			tfPrice.setText(product.getPrice().toString());
			tfOrigin.setText(product.getOrigin());

			try {
				String list = ProductBUS.getListById(product.getListId());
				cbList.setValue(list);
			} catch (SQLException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể tải loại danh mục từ mã!");
				alert.setContentText("Lỗi kết nối database!");
				alert.showAndWait();
			}
		});
	}

	public void handleLoadProducts() {
		try {
			ObservableList<ProductDTO> productList = FXCollections.observableArrayList();
			for (ProductDTO product : ProductDAO.getProducts()) {
				productList.add(product);
			}
			tvProduct.setItems(productList);
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách sản phẩm!");
			alert.setContentText("Lỗi kết nối database!");
			alert.showAndWait();
		}
	}

	public void handleInsertProduct() {
		try {
			String listId = ListBUS.getIdByName(cbList.getValue());
			
			ProductDTO product = new ProductDTO(tfId.getText(), tfName.getText(), Integer.parseUnsignedInt(tfNumber.getText()),
					Integer.parseUnsignedInt(tfPrice.getText()), tfOrigin.getText(), listId);

			if (ProductBUS.insertProduct(product)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("THÊM THÀNH CÔNG");
				alert.setHeaderText("Thêm thành công sản phẩm: " + product.getName());
				alert.showAndWait();

				handleLoadProducts();
				tvProduct.getSelectionModel().selectFirst();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Thông tin nhập vào không hợp lệ!");
				alert.setContentText("Lưu ý: Mã SP trong danh sách không được trùng.");
				alert.showAndWait();
			}
		} catch (NumberFormatException nfEx) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Thông tin nhập vào không hợp lệ!");
			alert.setContentText("Lưu ý: Số lượng và đơn giá phải là số nguyên dương.");
			alert.showAndWait();
		} catch (SQLException sqlEx) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể thêm sản phẩm!");
			alert.setContentText("Lỗi kết nối database!");
			alert.showAndWait();
		}
	}

	public void handleUpdateProduct() {
		try {
			String id = tvProduct.getSelectionModel().getSelectedItem().getId();
			String listId = ListBUS.getIdByName(cbList.getValue());
			ProductDTO product = new ProductDTO(id, tfName.getText(), Integer.parseUnsignedInt(tfNumber.getText()),
					Integer.parseUnsignedInt(tfPrice.getText()), tfOrigin.getText(), listId);

			if (ProductBUS.updateProduct(product)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("SỬA THÀNH CÔNG");
				alert.setHeaderText("Cập nhật sản phẩm thành công!");
				alert.showAndWait();

				handleLoadProducts();
				tvProduct.getSelectionModel().selectFirst();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể cập nhật sản phẩm!");
				alert.showAndWait();
			}
		} catch (NumberFormatException nfEx) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Thông tin nhập vào không hợp lệ!");
			alert.setContentText("Lưu ý: Số lượng và đơn giá phải là số nguyên dương.");
			alert.showAndWait();
		} catch (SQLException sqlEx) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể thêm sản phẩm!");
			alert.setContentText("Lỗi kết nối database!");
			alert.showAndWait();
		}
	}

	public void handleBackToMenu() {
		Stage stage = (Stage) btnBackToMenu.getScene().getWindow();
		stage.close();
	}
}
		