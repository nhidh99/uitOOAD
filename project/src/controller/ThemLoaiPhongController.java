package controller;

import java.io.IOException;
import java.sql.SQLException;

import BUS.LoaiPhongBUS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ThemLoaiPhongController {
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
	public void handleBtnXacNhan(ActionEvent e) throws SQLException, IOException {
		if (tfLoaiPhong.getText() == null || tfLoaiPhong.getText().trim().isEmpty()) 
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tạo loại phòng mới khi chưa nhập tên loại phòng!");
			alert.setContentText("Vui lòng nhập tên loại phòng trước khi thêm!");
			alert.showAndWait();
		}
		else if (tfSoKhachToiDa.getText() == null || tfSoKhachToiDa.getText().trim().isEmpty()) 
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tạo loại phòng mới khi chưa nhập số khách tối đa!");
			alert.setContentText("Vui lòng nhập số khách tối đa trước khi thêm!");
			alert.showAndWait();
		}
		else if (tfDonGia.getText() == null || tfDonGia.getText().trim().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tạo loại phòng mới khi chưa nhập đơn giá!");
			alert.setContentText("Vui lòng nhập đơn giá trước khi thêm!");
			alert.showAndWait();
		} 
		else 
		{
			if (LoaiPhongBUS.themLoaiPhong(tfLoaiPhong.getText(), Integer.parseInt(tfSoKhachToiDa.getText()),
					Integer.parseInt(tfDonGia.getText()))) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Bạn đã thêm thành công loại phòng!");
				alert.setContentText("");
				alert.showAndWait();
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Bạn đã thêm loại phòng đã tồn tại!");
				alert.setContentText("Vui lòng nhập lại loại phòng khác!");
				alert.showAndWait();
			}
			Stage stage = (Stage) btnHuyBo.getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	public void handleBtnHuyBo() {
		Stage stage = (Stage) btnHuyBo.getScene().getWindow();
		stage.close();
	}
}
