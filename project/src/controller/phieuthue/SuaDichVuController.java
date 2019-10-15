package controller.phieuthue;


import DTO.PTP_DichVuDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SuaDichVuController {
	@FXML private Label lbPhieuThue;
	@FXML private Label lbDichVu;
	@FXML private Spinner<Integer> snSoLuong;
	@FXML private Label lbSoLuongTon;
	@FXML private TextField tfDonGia;

	public void initialize(PTP_DichVuDTO ptp_dichVu) {
		lbPhieuThue.setText(ptp_dichVu.getMaPTPhong().toString());
		lbDichVu.setText(ptp_dichVu.getTenDichVu());
		tfDonGia.setText(ptp_dichVu.getDonGiaValue().toString());
		if(ptp_dichVu.getSoLuongTon() == null) {
			lbSoLuongTon.setText(ptp_dichVu.getDonViTinh());
			snSoLuong.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, ptp_dichVu.getSoLuong()));
		}
		else if(ptp_dichVu.getSoLuongTon() == 0) {
			lbSoLuongTon.setText("Hết hàng");
			snSoLuong.setDisable(true);
		}
		else {
			lbSoLuongTon.setText(ptp_dichVu.getSoLuongTon() + " " + ptp_dichVu.getDonViTinh());
			snSoLuong.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, ptp_dichVu.getSoLuongTon(), ptp_dichVu.getSoLuong()));
			snSoLuong.setEditable(true);
		}	
	}
	
	public void handleXacNhanSuaDichVu() {
		
	}
	
	public void handleHuySuaDichVu() {
		Stage stage = (Stage) lbSoLuongTon.getScene().getWindow();
		stage.close();
	}
}
