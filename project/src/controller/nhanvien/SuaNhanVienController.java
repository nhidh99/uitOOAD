package controller.nhanvien;

import java.sql.SQLException;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SuaNhanVienController {
	@FXML
	Label lbMaNhanVien;
	@FXML
	Label lbTaiKhoan;
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
	ComboBox<String> cbbChucVu;

	public void initialize(NhanVienDTO nhanVien) {
		lbMaNhanVien.setText(nhanVien.getMaNhanVien().toString());
		lbTaiKhoan.setText(nhanVien.getTaiKhoan());
		tfHoTen.setText(nhanVien.getTenNhanVien());
		tfCMND.setText(nhanVien.getCMND());
		tfDiaChi.setText(nhanVien.getDiaChi());
		tfEmail.setText(nhanVien.getEmail());
		tfDienThoai.setText(nhanVien.getSoDienThoai());
		cbbChucVu.setValue(nhanVien.getChucVu());
		cbbChucVu.setItems(FXCollections.observableArrayList("Káº¿ toÃ¡n", "Lá»… tÃ¢n", "Quáº£n lÃ­"));
	}

	public void handleSuaNhanVien(ActionEvent event) {
		try {
			if(tfHoTen.getText().matches("^[a-zA-Z]$") || !tfCMND.getText().matches("^[0-9]{1,15}$") || !tfDienThoai.getText().matches("^[0-9]{11}$")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");	
				alert.setHeaderText("Sửa thông tin nhân viên thất bại!");
				alert.setContentText("Tên khách chỉ gồm chữ cái viết hoa và viết thường! | CMND chỉ gồm chữ số và có tối đa 15 kí tự! | Số điện thoại chỉ có tối đa 11 chữ số! | Tiền nhận chỉ gồm chữ cố!");
				alert.showAndWait();
			}
			
			NhanVienDTO nhanVien = new NhanVienDTO(Integer.parseInt(lbMaNhanVien.getText()), tfHoTen.getText(),
					tfCMND.getText(), tfDiaChi.getText(), tfEmail.getText(), tfDienThoai.getText(),
					cbbChucVu.getSelectionModel().getSelectedItem(), null, null);

			if (NhanVienBUS.updateNhanVien(nhanVien)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ThÃ nh cÃ´ng");
				alert.setHeaderText("Sá»­a thÃ´ng tin nhÃ¢n viÃªn thÃ nh cÃ´ng!");
				alert.setContentText(String.format("Sá»­a thÃ nh cÃ´ng thÃ´ng tin %s %s.", nhanVien.getChucVu(), nhanVien.getTenNhanVien()));
				alert.showAndWait();

				MainController mainController = (MainController) lbMaNhanVien.getScene().getUserData();
				mainController.loadTableNhanVien();
				Stage stage = (Stage) lbMaNhanVien.getScene().getWindow();
				stage.close();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Tháº¥t báº¡i");
				alert.setHeaderText("Sá»­a thÃ´ng tin nhÃ¢n viÃªn tháº¥t báº¡i!");
				alert.setContentText("NhÃ¢n viÃªn vá»«a sá»­a trÃ¹ng CMND");
				alert.showAndWait();
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lá»—i");
			alert.setHeaderText("KhÃ´ng thá»ƒ sá»­a thÃ´ng tin nhÃ¢n viÃªn!");
			alert.setContentText("Lá»—i database!");
			alert.showAndWait();
		}
	}

	public void handleHuySuaNhanVien(ActionEvent event) {
		Stage stage = (Stage) lbMaNhanVien.getScene().getWindow();
		stage.close();
	}
}
