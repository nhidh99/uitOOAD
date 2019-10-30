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
		lbTieuDe.setText("ðŸ�›  Sá»¬A LOáº I PHÃ’NG");
		tfLoaiPhong.setText(loaiPhong.getTenLoaiPhong());
		tfSoKhachToiDa.setText(loaiPhong.getSoKhachToiDa().toString());
		tfDonGia.setText(loaiPhong.getDonGiaValue().toString());
	}

	public void handleXacNhan(ActionEvent e) {
		if (tfSoKhachToiDa.getText().trim().isEmpty() || tfLoaiPhong.getText().trim().isEmpty()
				|| tfDonGia.getText().trim().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lá»—i");
			alert.setHeaderText("KhÃ´ng thá»ƒ thÃªm loáº¡i phÃ²ng!");
			alert.setContentText("CÃ¡c thÃ´ng tin khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng!");
			alert.showAndWait();
			return;
		}
		else if(tfSoKhachToiDa.getText().matches("-([0-9]*)") || tfDonGia.getText().matches("-([0-9]*)")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");	
			alert.setHeaderText("Sửa thông tin nhân viên thất bại!");
			alert.setContentText("Số khách tối đa và đơn giá là một số dương");
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
					alert.setTitle("ThÃ nh cÃ´ng");
					alert.setHeaderText("Báº¡n Ä‘Ã£ thÃªm thÃ nh cÃ´ng loáº¡i phÃ²ng " + loaiPhong.getTenLoaiPhong());
					alert.setContentText("");
					alert.showAndWait();
					MainController controller = (MainController) lbTieuDe.getScene().getUserData();
					controller.loadTableLoaiPhong();
					controller.loadComboboxes();
					Stage stage = (Stage) lbTieuDe.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Tháº¥t báº¡i");
					alert.setHeaderText("Báº¡n Ä‘Ã£ thÃªm loáº¡i phÃ²ng Ä‘Ã£ tá»“n táº¡i!");
					alert.setContentText("Vui lÃ²ng nháº­p láº¡i loáº¡i phÃ²ng khÃ¡c!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lá»—i");
				alert.setHeaderText("KhÃ´ng thá»ƒ thÃªm loáº¡i phÃ²ng!");
				alert.setContentText("Lá»—i database!");
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
					alert.setTitle("ThÃ nh cÃ´ng");
					alert.setHeaderText("Báº¡n Ä‘Ã£ sá»­a thÃ nh cÃ´ng loáº¡i phÃ²ng " + newLoaiPhong.getTenLoaiPhong());
					alert.setContentText("");
					alert.showAndWait();
					MainController controller = (MainController) lbTieuDe.getScene().getUserData();
					controller.loadTableLoaiPhong();
					controller.loadComboboxes();
					Stage stage = (Stage) lbTieuDe.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Tháº¥t báº¡i");
					alert.setHeaderText("Sá»­a loáº¡i phÃ²ng tháº¥t báº¡i!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lá»—i");
				alert.setHeaderText("KhÃ´ng thá»ƒ sá»­a loáº¡i phÃ²ng!");
				alert.setContentText("Lá»—i database!");
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
