package controller.nhanvien;

import DTO.*;
import controller.MainController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.NhanVienBUS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ThemNhanVienController implements Initializable {

	@FXML
	TextField tfHoTen;
	@FXML
	TextField tfCMND;
	@FXML
	TextField tfDiaChi;
	@FXML
	TextField tfEmail;
	@FXML
	TextField tfDienThoai;
	@FXML
	TextField tfTaiKhoan;
	@FXML
	PasswordField pfMatKhau;
	@FXML
	PasswordField pfMatKhau2;
	@FXML
	ComboBox<String> cbbChucVu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> dsChucVu = FXCollections.observableArrayList("Káº¿ toÃ¡n", "Lá»… tÃ¢n", "Quáº£n lÃ­");
		cbbChucVu.setItems(dsChucVu);
		cbbChucVu.getSelectionModel().selectFirst();
	}

	public void handleThemNhanVien(ActionEvent event) {
		if (pfMatKhau.getText().equals(pfMatKhau2.getText())) {
			if(tfHoTen.getText().matches("^[a-zA-Z]$") || !tfCMND.getText().matches("^[0-9]{1,15}$") || !tfDienThoai.getText().matches("^[0-9]{11}$")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");	
				alert.setHeaderText("Sửa thông tin nhân viên thất bại!");
				alert.setContentText("Tên khách chỉ gồm chữ cái viết hoa và viết thường! | CMND chỉ gồm chữ số và có tối đa 15 kí tự! | Số điện thoại chỉ có tối đa 11 chữ số! | Tiền nhận chỉ gồm chữ cố!");
				alert.showAndWait();
			}
			
			NhanVienDTO nhanVien = new NhanVienDTO(
					tfHoTen.getText(),
					tfCMND.getText(),
					tfDiaChi.getText(),
					tfEmail.getText(),
					tfDienThoai.getText(),
					cbbChucVu.getSelectionModel().getSelectedItem(),
					tfTaiKhoan.getText(),
					pfMatKhau.getText());

			try {
				if(tfHoTen.getText().matches("^[0-9]$") || !tfCMND.getText().matches("^[0-9]{1,15}$") || !tfDienThoai.getText().matches("^[0-9]{11}$")) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");	
					alert.setHeaderText("Sửa thông tin nhân viên thất bại!");
					alert.setContentText("Tên khách chỉ gồm chữ cái viết hoa và viết thường! | CMND chỉ gồm chữ số và có tối đa 15 kí tự! | Số điện thoại chỉ có tối đa 11 chữ số! | Tiền nhận chỉ gồm chữ cố!");
					alert.showAndWait();
				}
				
				if (NhanVienBUS.insertNhanVien(nhanVien)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ThÃ nh cÃ´ng");
					alert.setHeaderText("ThÃªm nhÃ¢n viÃªn thÃ nh cÃ´ng!");
					alert.setContentText(String.format("ThÃªm thÃ nh cÃ´ng %s %s.", nhanVien.getChucVu(), nhanVien.getTenNhanVien()));
					alert.showAndWait();
					
					MainController mainController = (MainController) tfHoTen.getScene().getUserData();
					mainController.loadTableNhanVien();
					Stage stage = (Stage) tfHoTen.getScene().getWindow();
					stage.close();
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Tháº¥t báº¡i");
					alert.setHeaderText("ThÃªm nhÃ¢n viÃªn tháº¥t báº¡i!");
					alert.setContentText("NhÃ¢n viÃªn vá»«a táº¡o trÃ¹ng CMND hoáº·c tÃªn tÃ i khoáº£n.");
					alert.showAndWait();
				}
			}
			catch (SQLException SQLException) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lá»—i");
				alert.setHeaderText("KhÃ´ng thá»ƒ táº¡o nhÃ¢n viÃªn!");
				alert.setContentText("Lá»—i database!");
				alert.showAndWait();				
			}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lá»—i");
			alert.setHeaderText("KhÃ´ng thá»ƒ táº¡o nhÃ¢n viÃªn!");
			alert.setContentText("Máº­t kháº©u khÃ´ng trÃ¹ng khá»›p!");
			alert.showAndWait();
		}
	}

	public void handleHuyThemNhanVien(ActionEvent event) {
		Stage stage = (Stage) tfHoTen.getScene().getWindow();
		stage.close();
	}
}
