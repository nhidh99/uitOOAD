package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import BUS.*;

public class MainController implements Initializable {
	@FXML
	private Button btnExit;
	@FXML
	private ListView<String> lvList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		handleLoadList();
		lvList.getSelectionModel().selectFirst();
	}

	public void handleLoadList() {
		try {
			ObservableList<String> nameList = FXCollections.observableArrayList();
			for (String name : ListBUS.getListName()) {
				nameList.add(name);
			}
			lvList.setItems(nameList);
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh mục!");
			alert.setContentText("Lỗi kết nối database!");
			alert.showAndWait();
		}
	}

	public void handleView() {
		try {
			Stage primaryStage = (Stage) btnExit.getScene().getWindow();
			System.out.println(1);
			Stage stage = new Stage();
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Popup.fxml"));
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add("view/popup.css");
			
			stage.setOnShown(event -> {
				primaryStage.hide();
			});
			
			stage.setOnHidden(event -> {
				primaryStage.show();
				lvList.getSelectionModel().selectFirst();
			});
			
			stage.setTitle("Danh mục sản phẩm");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleDelete() {
		try {
			String selectedName = lvList.getSelectionModel().getSelectedItem();
			if (ListBUS.deleteListByName(selectedName)) {
				handleLoadList();
			}
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xoá danh mục!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleExit() {
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}
}
