package controller;

import java.sql.SQLException;

import BUS.ThamSoBUS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SuaThamSoController {
	@FXML
	private TextField tfSoNgayTraCoc;
	
	@FXML
	private TextField tfTiLeThueVAT;
	
	@FXML
	private TextField tfTiLeTienCoc;
	
	@FXML
	private TextField tfPhuThuQuaKhach;
	
	@FXML
	private TextField tfPhuThuTraPhongTre;
	
	@FXML
	private Button btnXacNhan;
	
	@FXML
	private Button btnHuyBo;
	
	@FXML
	public void handleBtnXacNhan(ActionEvent e) throws NumberFormatException, SQLException {	
		if(ThamSoBUS.updateThamSo(Integer.parseInt(tfSoNgayTraCoc.getText()), 
				Float.parseFloat(tfTiLeThueVAT.getText()),Float.parseFloat(tfTiLeTienCoc.getText()),
				Float.parseFloat(tfPhuThuQuaKhach.getText()),Float.parseFloat(tfPhuThuTraPhongTre.getText()))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thành công");
			alert.setHeaderText("Sửa tham số thành công!");
			alert.setContentText("");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Sửa tham số thất bại!");
			alert.setContentText("Vui lòng nhập lại các giá trị tham số!");
			alert.showAndWait();
		}

		Stage stage = (Stage) btnXacNhan.getScene().getWindow();
		stage.close();

	}
	
	public void handleBtnHuyBo(ActionEvent e) {
		Stage stage = (Stage) btnHuyBo.getScene().getWindow();
		stage.close();
	}
	
	public void initValue(Integer soNgayTraCoc, Float tiLeThueVAT, Float tiLeTienCoc, Float phuThuQuaKhach, Float phuThuTraPhongTre) {
		tfSoNgayTraCoc.setText(soNgayTraCoc.toString());
		tfTiLeThueVAT.setText(tiLeThueVAT.toString());
		tfTiLeTienCoc.setText(tiLeTienCoc.toString());
		tfPhuThuQuaKhach.setText(phuThuQuaKhach.toString());
		tfPhuThuTraPhongTre.setText(phuThuTraPhongTre.toString());
	}
}
