package controller;

import java.sql.SQLException;

import BUS.LoaiDichVuBUS;
import BUS.NhaCungCapBUS;
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

public class SuaLoaiDichVuController {
	private String tenLoaiDichVuCu;

	@FXML
	TextField tfLoaiDichVu;
	
	@FXML
	ChoiceBox<String> cbNhaCungCap;
	
	@FXML
	Button btnXacNhan;
	
	@FXML
	Button btnHuyBo;
	
	public void initValue() throws SQLException {
		ObservableList<String> listNhaCungCap = FXCollections.observableArrayList();
		
		for(NhaCungCapDTO nhacungcap : NhaCungCapBUS.getDSNhaCungCap()) {
			listNhaCungCap.add(nhacungcap.getTenNhaCungCap());
		}
		cbNhaCungCap.setItems(listNhaCungCap);
	}
	
	public void getDataFromMain(String tenLoaiDichVu) {
		this.tenLoaiDichVuCu = tenLoaiDichVu;
		
		tfLoaiDichVu.setText(tenLoaiDichVu);
	}
	
	@FXML
	public void handleBtnXacNhan(ActionEvent e) throws SQLException {
		if(LoaiDichVuBUS.updateLoaiDichVu(tenLoaiDichVuCu, tfLoaiDichVu.getText())) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thành công");
			alert.setHeaderText("Sửa loại dịch vụ thành công!");
			alert.setContentText("");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Sửa loại dịch vụ thất bại!");
			alert.setContentText("");
			alert.showAndWait();
		}
		Stage stage = (Stage) btnHuyBo.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void handleBtnHuyBo(ActionEvent e) {
		Stage stage = (Stage) btnHuyBo.getScene().getWindow();
		stage.close();
	}
}
