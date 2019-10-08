package controller;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.DichVuBUS;
import BUS.LoaiDichVuBUS;
import BUS.NhaCungCapBUS;
import DTO.DichVuDTO;
import DTO.LoaiDichVuDTO;
import DTO.NhaCungCapDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

public class PopupController implements Initializable {
	@FXML private TextField tfDV_Ten;
	@FXML private TextField tfDV_DonViTinh;
	@FXML private TextField tfDV_SoLuongTon;
	@FXML private TextField tfDV_DonGia;
	@FXML private TextField tfDV_KhaDung;
	@FXML private ComboBox<String> cbDV_LoaiDichVu;
	@FXML private ComboBox<String> cbDV_NhaCungCap;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> listLoaiDichVu = FXCollections.observableArrayList();
		try {
			for(LoaiDichVuDTO loaiDV : LoaiDichVuBUS.getDSLoaiDichVu()) {
				listLoaiDichVu.add(loaiDV.getTenLoaiDichVu());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<String> listNhaCungCap = FXCollections.observableArrayList();
		try {
			for(NhaCungCapDTO ncc : NhaCungCapBUS.getDSNhaCungCap()) {
				listNhaCungCap.add(ncc.getTenNhaCungCap());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cbDV_LoaiDichVu.setItems(listLoaiDichVu);
		cbDV_NhaCungCap.setItems(listNhaCungCap);
	}
	
	public void handleXacNhan() throws NumberFormatException, SQLException {
		DichVuDTO dichVu = new DichVuDTO(
								tfDV_Ten.getText(),
								tfDV_DonViTinh.getText(),
								Integer.parseInt(tfDV_SoLuongTon.getText()),
								Integer.parseInt(tfDV_DonGia.getText()),
								LoaiDichVuBUS.getMaLoaiDichVu(cbDV_LoaiDichVu.getSelectionModel().getSelectedItem().toString()),
								NhaCungCapBUS.getMaNhaCungCap(cbDV_NhaCungCap.getSelectionModel().getSelectedItem().toString())
							);
		System.out.println(LoaiDichVuBUS.getMaLoaiDichVu(cbDV_LoaiDichVu.getSelectionModel().getSelectedItem()).toString());
		System.out.println(NhaCungCapBUS.getMaNhaCungCap(cbDV_NhaCungCap.getSelectionModel().getSelectedItem()).toString());
		try 
		{
			if(DichVuBUS.insertDSDichVu(dichVu)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông báo");
				alert.setHeaderText("Đã thêm thành công!");
				alert.showAndWait();
				Stage stage = (Stage)tfDV_Ten.getScene().getWindow();
				stage.close();
			}
		} 
		catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Thêm thất bại!");
			alert.showAndWait();
			Stage stage = (Stage)tfDV_Ten.getScene().getWindow();
			stage.close();	
			e.printStackTrace();
		}
	}
	
	public void handleHuyBo() {
		Stage stage = (Stage)tfDV_Ten.getScene().getWindow();
		stage.close();	
	}
}