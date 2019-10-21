package controller;

import java.sql.SQLException;

import BUS.NhaCungCapBUS;
import DAO.LoaiDichVuDAO;
import DTO.NhaCungCapDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ThemLoaiDichVuController {
	@FXML
	TextField tfLoaiDichVu;
	
	@FXML
	Button btnXacNhan;
	
	@FXML
	Button btnHuyBo;
	
	@FXML
	public void handleBtnXacNhan(ActionEvent e) throws SQLException {
		if (tfLoaiDichVu.getText() == null || tfLoaiDichVu.getText().trim().isEmpty()) 
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tạo loại dịch vụ mới khi chưa nhập tên loại dịch vụ!");
			alert.setContentText("Vui lòng nhập tên loại phòng trước khi thêm!");
			alert.showAndWait();
		}
		else
		{
			if(LoaiDichVuDAO.addLoaiDichVu(tfLoaiDichVu.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Bạn đã thêm thành công loại dịch vụ mới!");
				alert.setContentText("");
				alert.showAndWait();
			}
			else
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Bạn đã thêm loại dịch vụ đã tồn tại!");
				alert.setContentText("Vui lòng nhập lại loại dịch vụ khác!");
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
