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
	
	@FXML TextField tfHoTen;
	@FXML TextField	tfCMND;
	@FXML TextField	tfDiaChi;
	@FXML TextField	tfEmail;
	@FXML TextField	tfDienThoai;
	@FXML Label lbMaNhanVien;
	@FXML Label lbTaiKhoan;
	@FXML Label lbChucVu;
	@FXML PasswordField pfMatKhau;
	@FXML PasswordField pfMatKhau2;
	
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
			NhanVienDTO nhanVien = new NhanVienDTO(
					Integer.parseInt(lbMaNhanVien.getText()),
					tfHoTen.getText(),
					tfCMND.getText(),
					tfDiaChi.getText(),
					tfEmail.getText(),
					tfDienThoai.getText(),
					lbChucVu.getText(),
					lbTaiKhoan.getText(),
					pfMatKhau.getText());
			try {
				if (NhanVienBUS.updateNhanVien(nhanVien)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Sửa thông tin nhân viên thành công!");
					alert.setContentText(String.format("Sửa thành công thông tin %s %s.", nhanVien.getChucVu(), nhanVien.getTenNhanVien()));
					alert.showAndWait();

					MainController mainController = (MainController) lbMaNhanVien.getScene().getUserData();
					mainController.loadTableNhanVien();
					mainController.loadNhanVienByUsername(nhanVien.getTaiKhoan());
					Stage stage = (Stage) lbMaNhanVien.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Sửa thông tin nhân viên thất bại!");
					alert.setContentText("Nhân viên vừa sửa trùng CMND");
					alert.showAndWait();
				}
			}
			catch (SQLException SQLException) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể sửa thông tin nhân viên!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();				
			}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa thông tin nhân viên!");
			alert.setContentText("Mật khẩu không trùng khớp!");
			alert.showAndWait();
		}
	}

	public void handleHuyTuyChinhNhanVien(ActionEvent event) {
		Stage stage = (Stage) lbMaNhanVien.getScene().getWindow();
		stage.close();
	}
}
