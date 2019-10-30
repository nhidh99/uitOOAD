package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.KhachBUS;
import DTO.KhachDTO;
import DTO.PTPhongDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class KhachController implements Initializable {

	@FXML
	Label lbMaPTP;
	@FXML
	Label lbTieuDe;
	@FXML
	TextField tfHoTen;
	@FXML
	TextField tfDienThoai;
	@FXML
	TextField tfCMND;
	@FXML
	TextField tfEmail;
	@FXML
	TextField tfQuocTich;
	@FXML
	ComboBox<String> cbbGioiTinh;

	private enum Tag {
		INSERT, UPDATE
	};

	Tag tag = Tag.INSERT;
	PTPhongDTO ptPhong;
	KhachDTO khach;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbbGioiTinh.setItems(FXCollections.observableArrayList("Nam", "Nữ"));
		cbbGioiTinh.getSelectionModel().selectFirst();
	}

	public void initialize(PTPhongDTO ptPhong) {
		this.ptPhong = ptPhong;
		lbMaPTP.setText(String.format("%d (Phòng %s)", ptPhong.getMaPTPhong(), ptPhong.getMaPhong()));
	}

	public void initialize(KhachDTO khach) {
		this.tag = Tag.UPDATE;
		this.ptPhong = khach.getPTPhong();
		this.khach = khach;
		lbMaPTP.setText(String.format("%d (Phòng %s)", ptPhong.getMaPTPhong(), ptPhong.getMaPhong()));
		tfHoTen.setText(khach.getHoTen());
		tfDienThoai.setText(khach.getSoDienThoai());
		tfCMND.setText(khach.getCMND());
		tfQuocTich.setText(khach.getQuocTich());
	}

	public void handleXacNhan(ActionEvent e) {

		switch (tag) {
		case INSERT: {
			KhachDTO khach = new KhachDTO(ptPhong, tfHoTen.getText(), tfCMND.getText(), tfDienThoai.getText(),
					cbbGioiTinh.getValue(), tfQuocTich.getText());
			try {
				if (KhachBUS.insertKhach(khach)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Thêm khách thành công!");
					alert.setContentText(String.format("Đã thêm khách khách: %s.", khach.getHoTen()));
					alert.showAndWait();

					MainController mainController = (MainController) lbTieuDe.getScene().getUserData();
					mainController.loadTableKhach(ptPhong.getMaPTPhong());
					Stage stage = (Stage) lbTieuDe.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Thêm khách thất bại!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Thêm khách thất bại!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();
			}
			break;
		}
		case UPDATE: {
			KhachDTO newKhach = new KhachDTO(khach.getMaKhachHang(), ptPhong, tfHoTen.getText(), tfCMND.getText(),
					tfDienThoai.getText(), cbbGioiTinh.getValue(), tfQuocTich.getText());
			try {
				if (KhachBUS.updateKhach(newKhach)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Sửa khách thành công!");
					alert.setContentText(String.format("Đã sửa khách khách: %s.", newKhach.getHoTen()));
					alert.showAndWait();

					MainController mainController = (MainController) lbTieuDe.getScene().getUserData();
					mainController.loadTableKhach(ptPhong.getMaPTPhong());
					Stage stage = (Stage) lbTieuDe.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Sửa khách thất bại!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Sửa khách thất bại!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();
			}
			break;
		}
		}
	}
}
