package controller.nhanvien;

import DTO.*;
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
		ObservableList<String> dsChucVu = FXCollections.observableArrayList("Kế toán", "Lễ tân", "Quản lí");
		cbbChucVu.setItems(dsChucVu);
		cbbChucVu.getSelectionModel().selectFirst();
	}

	public void handleThemNhanVien(ActionEvent event) {
		
		if (!(tfHoTen.getText().matches("^([^0-9]{1,30})$") 
				&& tfCMND.getText().matches("^[0-9]{1,15}$")
				&& tfDienThoai.getText().matches("^[0-9]{1,15}$") 
				&& tfEmail.getText().matches("^.{1,45}$")
				&& tfDiaChi.getText().matches("^.{0,45}$") 
				&& tfTaiKhoan.getText().matches("^[a-z0-9_]{6,20}$")
				&& pfMatKhau.getText().matches("^.{6,30}$"))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại!");
			alert.setHeaderText("Tạo nhân viên thất bại!");
			alert.setContentText(
					"- Tên nhân viên đa 30 kí tự và không chứa số.\n" 
					+ "- CMND chỉ chứa số và tối đa 15 kí tự.\n"
					+ "- Điện thoại chỉ chứa số và tối đa 15 kí tự.\n" 
					+ "- Email tối đa 45 kí tự.\n"
					+ "- Địa chỉ tối đa 45 kí tự.\n" 
					+ "- Tên tài khoản từ 6-20 kí tự, chỉ chấp nhận chữ không dấu, số và kí tự '_'"
					+ "- Mật khẩu từ 6-30 kí tự.");
			alert.showAndWait();
			return;
		}
		
		if (pfMatKhau.getText().equals(pfMatKhau2.getText())) {
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
				if (NhanVienBUS.insertNhanVien(nhanVien)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Thêm nhân viên thành công!");
					alert.setContentText(String.format("Thêm thành công %s %s.", nhanVien.getChucVu(), nhanVien.getTenNhanVien()));
					alert.showAndWait();
					
					Runnable reloadTableNhanVien = (Runnable) tfCMND.getScene().getUserData();
					reloadTableNhanVien.run();
					Stage stage = (Stage) tfHoTen.getScene().getWindow();
					stage.close();
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Thêm nhân viên thất bại!");
					alert.setContentText("Nhân viên vừa tạo trùng CMND hoặc tên tài khoản.");
					alert.showAndWait();
				}
			}
			catch (SQLException SQLException) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể tạo nhân viên!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();				
			}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tạo nhân viên!");
			alert.setContentText("Mật khẩu không trùng khớp!");
			alert.showAndWait();
		}
	}

	public void handleHuyThemNhanVien(ActionEvent event) {
		Stage stage = (Stage) tfHoTen.getScene().getWindow();
		stage.close();
	}
}
