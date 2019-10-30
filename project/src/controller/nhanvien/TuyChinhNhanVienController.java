package controller.nhanvien;

import java.sql.SQLException;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TuyChinhNhanVienController {

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
	Label lbMaNhanVien;
	@FXML
	Label lbTaiKhoan;
	@FXML
	Label lbChucVu;
	@FXML
	PasswordField pfMatKhau;
	@FXML
	PasswordField pfMatKhau2;

	public void initialize(NhanVienDTO nhanVien) {
		tfHoTen.setText(nhanVien.getTenNhanVien());
		tfCMND.setText(nhanVien.getCMND());
		tfDiaChi.setText(nhanVien.getDiaChi());
		tfEmail.setText(nhanVien.getEmail());
		tfDienThoai.setText(nhanVien.getSoDienThoai());
		lbMaNhanVien.setText(nhanVien.getMaNhanVien().toString());
		lbTaiKhoan.setText(nhanVien.getTaiKhoan());
		lbChucVu.setText(nhanVien.getChucVu());
		pfMatKhau.setText(nhanVien.getMatKhau());
		pfMatKhau2.setText(nhanVien.getMatKhau());
	}

	public void handleTuyChinhNhanVien(ActionEvent event) {
		if (pfMatKhau.getText().equals(pfMatKhau2.getText())) {
			NhanVienDTO nhanVien = new NhanVienDTO(Integer.parseInt(lbMaNhanVien.getText()), tfHoTen.getText(),
					tfCMND.getText(), tfDiaChi.getText(), tfEmail.getText(), tfDienThoai.getText(), lbChucVu.getText(),
					lbTaiKhoan.getText(), pfMatKhau.getText());
			try {
				if (pfMatKhau.getText().equals(pfMatKhau2.getText())) {
					if (tfHoTen.getText().matches("^[a-zA-Z]$") || !tfCMND.getText().matches("^[0-9]{1,15}$")
							|| !tfDienThoai.getText().matches("^[0-9]{11}$")) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Thất bại");
						alert.setHeaderText("Sửa thông tin nhân viên thất bại!");
						alert.setContentText(
								"Tên khách chỉ gồm chữ cái viết hoa và viết thường! | CMND chỉ gồm chữ số và có tối đa 15 kí tự! | Số điện thoại chỉ có tối đa 11 chữ số! | Tiền nhận chỉ gồm chữ cố!");
						alert.showAndWait();
					} else if (NhanVienBUS.updateNhanVien(nhanVien)) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("ThÃ nh cÃ´ng");
						alert.setHeaderText("Sá»­a thÃ´ng tin nhÃ¢n viÃªn thÃ nh cÃ´ng!");
						alert.setContentText(String.format("Sá»­a thÃ nh cÃ´ng thÃ´ng tin %s %s.", nhanVien.getChucVu(),
								nhanVien.getTenNhanVien()));
						alert.showAndWait();

						MainController mainController = (MainController) lbMaNhanVien.getScene().getUserData();
						mainController.loadTableNhanVien();
						mainController.loadNhanVienByUsername(nhanVien.getTaiKhoan());
						Stage stage = (Stage) lbMaNhanVien.getScene().getWindow();
						stage.close();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Tháº¥t báº¡i");
						alert.setHeaderText("Sá»­a thÃ´ng tin nhÃ¢n viÃªn tháº¥t báº¡i!");
						alert.setContentText("NhÃ¢n viÃªn vá»«a sá»­a trÃ¹ng CMND");
						alert.showAndWait();
					}
				}
			} catch (SQLException SQLException) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lá»—i");
				alert.setHeaderText("KhÃ´ng thá»ƒ sá»­a thÃ´ng tin nhÃ¢n viÃªn!");
				alert.setContentText("Lá»—i database!");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lá»—i");
			alert.setHeaderText("KhÃ´ng thá»ƒ sá»­a thÃ´ng tin nhÃ¢n viÃªn!");
			alert.setContentText("Máº­t kháº©u khÃ´ng trÃ¹ng khá»›p!");
			alert.showAndWait();
		}
	}

	public void handleHuyTuyChinhNhanVien(ActionEvent event) {
		Stage stage = (Stage) lbMaNhanVien.getScene().getWindow();
		stage.close();
	}
}
