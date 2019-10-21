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
		lbTieuDe.setText("🗳 SỬA NHÀ CUNG CẤP");
		tfNhaCungCap.setText(nhaCungCap.getTenNhaCungCap());
		tfSoDienThoai.setText(nhaCungCap.getSoDienThoai());
	}

	public void handleXacNhan(ActionEvent e) {
		if (tfNhaCungCap.getText().trim().isEmpty() || tfSoDienThoai.getText().trim().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Các thông tin không được để trống!");
			alert.setContentText("Vui lòng nhập đầy đủ thông tin để xử lí!");
			alert.showAndWait();
			return;
		}

		switch (tag) {
		case INSERT: {
			try {
				NhaCungCapDTO nhaCungCap = new NhaCungCapDTO(tfNhaCungCap.getText(), tfSoDienThoai.getText());
				if (NhaCungCapBUS.insertNhaCungCap(nhaCungCap)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Thêm thành công nhà cung cấp " + nhaCungCap.getTenNhaCungCap());
					alert.showAndWait();
					MainController controller = (MainController) lbTieuDe.getScene().getUserData();
					controller.loadTableNhaCungCap();
					Stage stage = (Stage) lbTieuDe.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Bạn đã thêm nhà cung cấp đã tồn tại!");
					alert.setContentText("Vui lòng nhập lại nhà cung cấp khác!");
					alert.showAndWait();
				}
				Stage stage = (Stage) lbTieuDe.getScene().getWindow();
				stage.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể thêm nhà cung cấp");
				alert.setContentText("Lỗi database!");
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
					alert.setTitle("Thành công");
					alert.setHeaderText("Bạn đã sửa thành công nhà cung cấp " + newNhaCungCap.getTenNhaCungCap());
					alert.showAndWait();
					MainController controller = (MainController) lbTieuDe.getScene().getUserData();
					controller.loadTableNhaCungCap();
					controller.loadTableDichVu();
					Stage stage = (Stage) lbTieuDe.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Bạn đã sửa nhà cung cấp đã tồn tại!");
					alert.setContentText("Vui lòng nhập lại nhà cung cấp khác!");
					alert.showAndWait();
				}
				Stage stage = (Stage) lbTieuDe.getScene().getWindow();
				stage.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể sửa nhà cung cấp!");
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
