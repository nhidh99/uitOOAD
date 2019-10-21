package controller;

import java.sql.SQLException;

import BUS.LoaiPhongBUS;
import DTO.LoaiPhongDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoaiPhongController {

	@FXML
	Label lbTieuDe;

	@FXML
	TextField tfLoaiPhong;

	@FXML
	TextField tfSoKhachToiDa;

	@FXML
	TextField tfDonGia;

	enum Tag {
		INSERT, UPDATE
	}

	Tag tag = Tag.INSERT;

	LoaiPhongDTO loaiPhong;

	public void initialize(LoaiPhongDTO loaiPhong) {
		this.loaiPhong = loaiPhong;
		this.tag = Tag.UPDATE;
		lbTieuDe.setText("🏛  SỬA LOẠI PHÒNG");
		tfLoaiPhong.setText(loaiPhong.getTenLoaiPhong());
		tfSoKhachToiDa.setText(loaiPhong.getSoKhachToiDa().toString());
		tfDonGia.setText(loaiPhong.getDonGiaValue().toString());
	}

	public void handleXacNhan(ActionEvent e) {
		if (tfSoKhachToiDa.getText().trim().isEmpty() || tfLoaiPhong.getText().trim().isEmpty()
				|| tfDonGia.getText().trim().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể thêm loại phòng!");
			alert.setContentText("Các thông tin không được để trống!");
			alert.showAndWait();
			return;
		}

		switch (tag) {
		case INSERT: {
			try {
				LoaiPhongDTO loaiPhong = new LoaiPhongDTO(tfLoaiPhong.getText(),
						Integer.valueOf(tfSoKhachToiDa.getText()), Integer.valueOf(tfDonGia.getText()));

				if (LoaiPhongBUS.insertLoaiPhong(loaiPhong)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Bạn đã thêm thành công loại phòng " + loaiPhong.getTenLoaiPhong());
					alert.setContentText("");
					alert.showAndWait();
					MainController controller = (MainController) lbTieuDe.getScene().getUserData();
					controller.loadTableLoaiPhong();
					Stage stage = (Stage) lbTieuDe.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Bạn đã thêm loại phòng đã tồn tại!");
					alert.setContentText("Vui lòng nhập lại loại phòng khác!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể thêm loại phòng!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();
			}
			break;
		}
		case UPDATE: {
			try {
				LoaiPhongDTO newLoaiPhong = new LoaiPhongDTO(loaiPhong.getMaLoaiPhong(), tfLoaiPhong.getText(),
						Integer.valueOf(tfSoKhachToiDa.getText()), Integer.valueOf(tfDonGia.getText()));

				if (LoaiPhongBUS.updateLoaiPhong(newLoaiPhong)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Bạn đã sửa thành công loại phòng " + newLoaiPhong.getTenLoaiPhong());
					alert.setContentText("");
					alert.showAndWait();
					MainController controller = (MainController) lbTieuDe.getScene().getUserData();
					controller.loadTableLoaiPhong();
					controller.loadComboboxes();
					Stage stage = (Stage) lbTieuDe.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Sửa loại phòng thất bại!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể sửa loại phòng!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();
			}
			break;
		}
		}
	}

	public void handleHuyBo(ActionEvent e) {
		Stage stage = (Stage) lbTieuDe.getScene().getWindow();
		stage.close();
	}
}
