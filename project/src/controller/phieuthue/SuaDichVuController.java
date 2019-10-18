package controller.phieuthue;


import java.sql.SQLException;

import BUS.PTP_DichVuBUS;
import DTO.PTP_DichVuDTO;
import controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SuaDichVuController {
	@FXML private Label lbPhieuThue;
	@FXML private Label lbDichVu;
	@FXML private TextField tfDonGia;
	
	private PTP_DichVuDTO ptp_dichVu = null;

	public void initialize(PTP_DichVuDTO ptp_dichVu) {
		lbPhieuThue.setText(ptp_dichVu.getMaPTPhong().toString());
		lbDichVu.setText(ptp_dichVu.getTenDichVu());
		tfDonGia.setText(ptp_dichVu.getDonGiaValue().toString());
		this.ptp_dichVu = ptp_dichVu;
	}
	
	public void handleXacNhanSuaDichVu() {
		ptp_dichVu.setDonGia(Integer.parseInt(tfDonGia.getText()));
		try {
			if(PTP_DichVuBUS.updatePTP_DichVu(ptp_dichVu)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Sửa thông tin dịch vụ thành công!");
				alert.setContentText(String.format("Đã sửa dịch vụ %s", ptp_dichVu.getTenDichVu()));
				alert.showAndWait();
				
				MainController mainController = (MainController) lbPhieuThue.getScene().getUserData();
				mainController.loadTablePTPDichVuByMaPT(Integer.parseInt(lbPhieuThue.getText()));
				Stage stage = (Stage) lbPhieuThue.getScene().getWindow();
				stage.close();
			}
			
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa thông tin dịch vụ!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}
	
	public void handleHuySuaDichVu() {
		Stage stage = (Stage) lbPhieuThue.getScene().getWindow();
		stage.close();
	}
}
