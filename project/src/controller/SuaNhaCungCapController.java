package controller;

import java.sql.SQLException;

import DAO.NhaCungCapDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SuaNhaCungCapController {
	private String tenNhaCungCapCu;
	
	@FXML
	TextField tfNhaCungCap;

	@FXML
	TextField tfSoDienThoai;

	@FXML
	Button btnXacNhan;

	@FXML
	Button btnHuyBo;

	@FXML
	public void handleBtnXacNhan(ActionEvent e) throws SQLException {
		if (NhaCungCapDAO.updateNhaCungCap(tenNhaCungCapCu, tfNhaCungCap.getText(), Integer.parseInt(tfSoDienThoai.getText()))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thành công");
			alert.setHeaderText("Sửa nhà cung cấp thành công!");
			alert.setContentText("");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Sửa nhà cung cấp thất bại!");
			alert.setContentText("");
			alert.showAndWait();
		}

		Stage stage = (Stage) btnXacNhan.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void handleBtnHuyBo(ActionEvent e) {
		Stage stage = (Stage) btnHuyBo.getScene().getWindow();
		stage.close();
	}
	
	public void initValue(String tenNhaCungCapCu, String soDienThoaiCu) {
		this.tenNhaCungCapCu = tenNhaCungCapCu;
		
		tfNhaCungCap.setText(tenNhaCungCapCu);
		tfSoDienThoai.setText(soDienThoaiCu);
	}
}
