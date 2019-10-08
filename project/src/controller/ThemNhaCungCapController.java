package controller;

import java.sql.SQLException;

import BUS.NhaCungCapBUS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ThemNhaCungCapController {

	@FXML
	private TextField tfNhaCungCap;
	
	@FXML
	private TextField tfSoDienThoai;
	
	@FXML
	private Button btnXacNhan;
	
	@FXML
	private Button btnHuyBo;
	
	@FXML
	public void handleBtnXacNhan(ActionEvent e) throws SQLException {
		if (tfNhaCungCap.getText() == null || tfNhaCungCap.getText().trim().isEmpty()) 
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tạo nhà cung cấp mới khi chưa nhập tên nhà cung cấp!");
			alert.setContentText("Vui lòng nhập tên nhà cung cấp trước khi thêm!");
			alert.showAndWait();
		}
		else if (tfSoDienThoai.getText() == null || tfSoDienThoai.getText().trim().isEmpty()) 
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tạo nhà cung cấp mới khi chưa nhập số điện thoại!");
			alert.setContentText("Vui lòng nhập số điện thoại trước khi thêm!");
			alert.showAndWait();
		}
		else
		{
			if(NhaCungCapBUS.addNhaCungCap(tfNhaCungCap.getText(), Integer.parseInt(tfSoDienThoai.getText()))) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Bạn đã thêm thành công nhà cung cấp mới!");
				alert.setContentText("");
				alert.showAndWait();
			}
			else
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Bạn đã thêm nhà cung cấp đã tồn tại!");
				alert.setContentText("Vui lòng nhập lại nhà cung cấp khác!");
				alert.showAndWait();
			}
			Stage stage = (Stage) btnHuyBo.getScene().getWindow();
			stage.close();
		}
	}
	
	@FXML
	public void handleBtnHuyBo(ActionEvent e) {
		Stage stage = (Stage) btnHuyBo.getScene().getWindow();
		stage.close();
	}
}
