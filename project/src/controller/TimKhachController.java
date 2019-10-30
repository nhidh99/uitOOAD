package controller;

import java.sql.SQLException;
import java.util.List;

import BUS.PhongBUS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TimKhachController {
	@FXML
	TextField tfHoTen;

	@FXML
	TextField tfCMND;

	public void handleTimTheoTen(ActionEvent e) {
		try {
			if(!tfHoTen.getText().matches("^[0-9]$")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Tìm khách thất bại!");
				alert.setContentText("Tên khách chỉ gồm chữ!");
				alert.showAndWait();
			}
			else {
			List<String> listPhong = PhongBUS.findPhongByTenKhach(tfHoTen.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ThÃ nh cÃ´ng");
			alert.setHeaderText("Danh sÃ¡ch phÃ²ng tÃ¬m tháº¥y: " + String.join(", ", listPhong));
			alert.setContentText(
					"TÃ¬m tháº¥y " + listPhong.size() + " phÃ²ng chá»©a khÃ¡ch hÃ ng vá»›i há»� tÃªn: " + tfHoTen.getText());
			alert.showAndWait();
			}
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Tháº¥t báº¡i");
			alert.setHeaderText("TÃ¬m kiáº¿m phÃ²ng tháº¥t báº¡i!");
			alert.setContentText("Lá»—i database!");
			alert.showAndWait();
		}
	}

	public void handleTimTheoCMND(ActionEvent e) {
		try {
			if(!tfCMND.getText().matches("^[0-9]{1,15}$")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Tìm khách thất bại!");
				alert.setContentText("CMND chỉ gồm số và có 15 kí tự!");
				alert.showAndWait();
			}
			else {
			List<String> listPhong = PhongBUS.findPhongByCMNDKhach(tfCMND.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ThÃ nh cÃ´ng");
			alert.setHeaderText("Danh sÃ¡ch phÃ²ng tÃ¬m tháº¥y: " + String.join(", ", listPhong));
			alert.setContentText(
					"TÃ¬m tháº¥y " + listPhong.size() + " phÃ²ng chá»©a khÃ¡ch hÃ ng vá»›i CMND: " + tfCMND.getText());
			alert.showAndWait();
			}
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Tháº¥t báº¡i");
			alert.setHeaderText("TÃ¬m kiáº¿m phÃ²ng tháº¥t báº¡i!");
			alert.setContentText("Lá»—i database!");
			alert.showAndWait();
		}
	}

	public void handleThoat(ActionEvent e) {
		Stage stage = (Stage) tfHoTen.getScene().getWindow();
		stage.close();
	}
}
