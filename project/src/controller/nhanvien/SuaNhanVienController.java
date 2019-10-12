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
		cbbChucVu.setItems(FXCollections.observableArrayList("Kế toán", "Lễ tân", "Quản lí"));
	}

	public void handleSuaNhanVien(ActionEvent event) {
		try {
			NhanVienDTO nhanVien = new NhanVienDTO(Integer.parseInt(lbMaNhanVien.getText()), tfHoTen.getText(),
					tfCMND.getText(), tfDiaChi.getText(), tfEmail.getText(), tfDienThoai.getText(),
					cbbChucVu.getSelectionModel().getSelectedItem(), null, null);

			if (NhanVienBUS.updateNhanVien(nhanVien)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Sửa thông tin nhân viên thành công!");
				alert.setContentText(String.format("Sửa thành công thông tin %s %s.", nhanVien.getChucVu(), nhanVien.getTenNhanVien()));
				alert.showAndWait();

				MainController mainController = (MainController) lbMaNhanVien.getScene().getUserData();
				mainController.loadTableNhanVien();
				Stage stage = (Stage) lbMaNhanVien.getScene().getWindow();
				stage.close();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Sửa thông tin nhân viên thất bại!");
				alert.setContentText("Nhân viên vừa sửa trùng CMND");
				alert.showAndWait();
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa thông tin nhân viên!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleHuySuaNhanVien(ActionEvent event) {
		Stage stage = (Stage) lbMaNhanVien.getScene().getWindow();
		stage.close();
	}
}
