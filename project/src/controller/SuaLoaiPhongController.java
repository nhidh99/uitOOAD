package controller;

import java.sql.SQLException;

import BUS.LoaiPhongBUS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SuaLoaiPhongController {
	private String tenLoaiPhongCu;
	
	@FXML
	TextField tfLoaiPhong;

	@FXML
	TextField tfSoKhachToiDa;

	@FXML
	TextField tfDonGia;

	@FXML
	Button btnXacNhan;

	@FXML
	Button btnHuyBo;

	@FXML
	public void handleBtnXacNhan(ActionEvent e) throws SQLException {
		if (LoaiPhongBUS.suaLoaiPhong(tenLoaiPhongCu, tfLoaiPhong.getText(), Integer.parseInt(tfSoKhachToiDa.getText()),
				Integer.parseInt(tfDonGia.getText()))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thành công");
			alert.setHeaderText("Sửa loại phòng thành công!");
			alert.setContentText("");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Sửa loại phòng thất bại!");
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

	public void getDataFromMain(String tenLoaiPhongCu, Integer soKhachToiDa, Integer donGia) {
		this.tenLoaiPhongCu = tenLoaiPhongCu;
		tfLoaiPhong.setText(tenLoaiPhongCu);
		tfSoKhachToiDa.setText(Integer.toString(soKhachToiDa));
		tfDonGia.setText(Integer.toString(donGia));
	}
}
