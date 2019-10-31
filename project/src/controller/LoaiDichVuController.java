package controller;

import java.sql.SQLException;

import BUS.LoaiDichVuBUS;
import DTO.LoaiDichVuDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoaiDichVuController {
	enum Tag {
		INSERT, UPDATE
	}

	@FXML
	Label lbTieuDe;

	@FXML
	TextField tfLoaiDichVu;

	LoaiDichVuDTO loaiDichVu;

	Tag tag = Tag.INSERT;

	public void initialize(LoaiDichVuDTO loaiDichVu) {
		tag = Tag.UPDATE;
		this.loaiDichVu = loaiDichVu;
		lbTieuDe.setText("📦  SỬA LOẠI DỊCH VỤ");
		tfLoaiDichVu.setText(loaiDichVu.getTenLoaiDichVu());
	}

	public void handleXacNhan(ActionEvent e) {
		if (!(tfLoaiDichVu.getText().matches("^.{1,20}$"))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại!");
			alert.setHeaderText("Hiệu chỉnh loại dịch vụ thất bại!");
			alert.setContentText("Tên loại dịch vụ tối đa 20 kí tự");
			alert.showAndWait();
			return;
		}

		switch (tag) {
		case INSERT: {
			try {
				LoaiDichVuDTO loaiDichVu = new LoaiDichVuDTO(tfLoaiDichVu.getText());
				if (LoaiDichVuBUS.insertLoaiDichVu(loaiDichVu)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Thêm thành công loại dịch vụ " + loaiDichVu.getTenLoaiDichVu());
					alert.showAndWait();
					MainController controller = (MainController) lbTieuDe.getScene().getUserData();
					controller.loadTableLoaiDichVu();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Loại dịch vụ cần thêm đã tồn tại!");
					alert.setContentText("Vui lòng nhập lại loại dịch vụ khác!");
					alert.showAndWait();
				}
				Stage stage = (Stage) lbTieuDe.getScene().getWindow();
				stage.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể thêm loại dịch vụ!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();
			}
			break;
		}

		case UPDATE: {
			try {
				LoaiDichVuDTO newLoaiDichVu = new LoaiDichVuDTO(loaiDichVu.getMaLoaiDichVu(), tfLoaiDichVu.getText());
				if (LoaiDichVuBUS.updateLoaiDichVu(newLoaiDichVu)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Sửa thành công loại dịch vụ " + newLoaiDichVu.getTenLoaiDichVu());
					alert.setContentText(String.format("Các dịch vụ %s sẽ trở thành dịch vụ %s", loaiDichVu.getTenLoaiDichVu(), newLoaiDichVu.getTenLoaiDichVu()));
					alert.showAndWait();
					MainController controller = (MainController) lbTieuDe.getScene().getUserData();
					controller.loadTableLoaiDichVu();
					controller.loadTableDichVu();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Sửa loại dịch vụ đã tồn tại!");
					alert.setContentText("Vui lòng nhập lại loại dịch vụ khác!");
					alert.showAndWait();
				}
				Stage stage = (Stage) lbTieuDe.getScene().getWindow();
				stage.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể sửa loại dịch vụ!");
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
