package controller.ptpdv;

import java.sql.SQLException;

import BUS.PtpDichVuBUS;
import DTO.PtpDichVuDTO;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SuaPtpDichVuController {

	@FXML
	Label lbPhieuThue;

	@FXML
	Label lbDichVu;

	@FXML
	TextField tfDonGia;

	PtpDichVuDTO ptp_dv;

	public void initialize(PtpDichVuDTO ptp_dv) {
		this.ptp_dv = ptp_dv;
		lbPhieuThue.setText(
				String.format("%d (Phòng %s)", ptp_dv.getPTPhong().getMaPTPhong(), ptp_dv.getPTPhong().getMaPhong()));
		lbDichVu.setText(String.format("%s (%d %s)", ptp_dv.getDichVu().getTenDichVu(), ptp_dv.getSoLuong(),
				ptp_dv.getDonViTinh()));
		tfDonGia.setText(ptp_dv.getDonGiaValue().toString());
	}

	public void handleXacNhan(ActionEvent e) {
		if (!tfDonGia.getText().matches("^[0-9]{1,8}$")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại!");
			alert.setHeaderText("Thêm dịch vụ thất bại!");
			alert.setContentText("- Đơn giá là số không âm dưới 100 triệu VND.");
			alert.showAndWait();
			return;
		}

		try {
			ptp_dv.setDonGia(Integer.parseInt(tfDonGia.getText()));
			if (PtpDichVuBUS.updatePtpDichVu(ptp_dv)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Sửa thông tin dịch vụ thành công!");
				alert.setContentText(String.format("Đã sửa dịch vụ %s", ptp_dv.getTenDichVu()));
				alert.showAndWait();

				MainController mainController = (MainController) lbPhieuThue.getScene().getUserData();
				mainController.loadTablePTP_DV(ptp_dv.getPTPhong().getMaPTPhong());
				Stage stage = (Stage) lbPhieuThue.getScene().getWindow();
				stage.close();
			}

		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa thông tin dịch vụ!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleHuy(ActionEvent e) {
		Stage stage = (Stage) lbPhieuThue.getScene().getWindow();
		stage.close();
	}
}
