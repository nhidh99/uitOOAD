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
			List<String> listPhong = PhongBUS.findPhongByTenKhach(tfHoTen.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thành công");
			alert.setHeaderText("Danh sách phòng tìm thấy: " + String.join(", ", listPhong));
			alert.setContentText(
					"Tìm thấy " + listPhong.size() + " phòng chứa khách hàng với họ tên: " + tfHoTen.getText());
			alert.showAndWait();
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Tìm kiếm phòng thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleTimTheoCMND(ActionEvent e) {
		try {
			List<String> listPhong = PhongBUS.findPhongByCMNDKhach(tfCMND.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thành công");
			alert.setHeaderText("Danh sách phòng tìm thấy: " + String.join(", ", listPhong));
			alert.setContentText(
					"Tìm thấy " + listPhong.size() + " phòng chứa khách hàng với CMND: " + tfCMND.getText());
			alert.showAndWait();
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Tìm kiếm phòng thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleThoat(ActionEvent e) {
		Stage stage = (Stage) tfHoTen.getScene().getWindow();
		stage.close();
	}
}
