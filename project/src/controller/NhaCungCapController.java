package controller;

import java.sql.SQLException;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NhaCungCapController {

	@FXML
	private Label lbTieuDe;

	@FXML
	private TextField tfNhaCungCap;

	@FXML
	private TextField tfSoDienThoai;

	NhaCungCapDTO nhaCungCap;

	enum Tag {
		INSERT, UPDATE
	};

	Tag tag = Tag.INSERT;

	public void initialize(NhaCungCapDTO nhaCungCap) {
		this.tag = Tag.UPDATE;
		this.nhaCungCap = nhaCungCap;
		lbTieuDe.setText("ðŸ—³ Sá»¬A NHÃ€ CUNG Cáº¤P");
		tfNhaCungCap.setText(nhaCungCap.getTenNhaCungCap());
		tfSoDienThoai.setText(nhaCungCap.getSoDienThoai());
	}

	public void handleXacNhan(ActionEvent e) {
		if (tfNhaCungCap.getText().trim().isEmpty() || tfSoDienThoai.getText().trim().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lá»—i");
			alert.setHeaderText("CÃ¡c thÃ´ng tin khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng!");
			alert.setContentText("Vui lÃ²ng nháº­p Ä‘áº§y Ä‘á»§ thÃ´ng tin Ä‘á»ƒ xá»­ lÃ­!");
			alert.showAndWait();
			return;
		}
		else if(!tfSoDienThoai.getText().matches("^[0-9]$")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể thao tác1");
			alert.setContentText("Số điện thoại chỉ gồm chữ số!");
			alert.showAndWait();
			return;
		}

		switch (tag) {
		case INSERT: {
			try {
				NhaCungCapDTO nhaCungCap = new NhaCungCapDTO(tfNhaCungCap.getText(), tfSoDienThoai.getText());
				if (NhaCungCapBUS.insertNhaCungCap(nhaCungCap)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ThÃ nh cÃ´ng");
					alert.setHeaderText("ThÃªm thÃ nh cÃ´ng nhÃ  cung cáº¥p " + nhaCungCap.getTenNhaCungCap());
					alert.showAndWait();
					MainController controller = (MainController) lbTieuDe.getScene().getUserData();
					controller.loadTableNhaCungCap();
					Stage stage = (Stage) lbTieuDe.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Tháº¥t báº¡i");
					alert.setHeaderText("Báº¡n Ä‘Ã£ thÃªm nhÃ  cung cáº¥p Ä‘Ã£ tá»“n táº¡i!");
					alert.setContentText("Vui lÃ²ng nháº­p láº¡i nhÃ  cung cáº¥p khÃ¡c!");
					alert.showAndWait();
				}
				Stage stage = (Stage) lbTieuDe.getScene().getWindow();
				stage.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lá»—i");
				alert.setHeaderText("KhÃ´ng thá»ƒ thÃªm nhÃ  cung cáº¥p");
				alert.setContentText("Lá»—i database!");
				alert.showAndWait();
			}
			break;
		}

		case UPDATE: {
			try {
				NhaCungCapDTO newNhaCungCap = new NhaCungCapDTO(nhaCungCap.getMaNhaCungCap(), tfNhaCungCap.getText(),
						tfSoDienThoai.getText());
				if (NhaCungCapBUS.updateNhaCungCap(newNhaCungCap)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ThÃ nh cÃ´ng");
					alert.setHeaderText("Báº¡n Ä‘Ã£ sá»­a thÃ nh cÃ´ng nhÃ  cung cáº¥p " + newNhaCungCap.getTenNhaCungCap());
					alert.showAndWait();
					MainController controller = (MainController) lbTieuDe.getScene().getUserData();
					controller.loadTableNhaCungCap();
					controller.loadTableDichVu();
					Stage stage = (Stage) lbTieuDe.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Tháº¥t báº¡i");
					alert.setHeaderText("Báº¡n Ä‘Ã£ sá»­a nhÃ  cung cáº¥p Ä‘Ã£ tá»“n táº¡i!");
					alert.setContentText("Vui lÃ²ng nháº­p láº¡i nhÃ  cung cáº¥p khÃ¡c!");
					alert.showAndWait();
				}
				Stage stage = (Stage) lbTieuDe.getScene().getWindow();
				stage.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lá»—i");
				alert.setHeaderText("KhÃ´ng thá»ƒ sá»­a nhÃ  cung cáº¥p!");
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
