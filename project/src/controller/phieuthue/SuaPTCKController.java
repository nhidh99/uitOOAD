package controller.phieuthue;

import java.sql.SQLException;

import BUS.PTP_PTCKBUS;
import DTO.PTP_PTCKDTO;

import controller.MainController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SuaPTCKController {
	@FXML private Label lbPhieuThue;
	@FXML private ComboBox<String> cbbPTCK;
	@FXML private Spinner<Integer> snSoLuong;
	@FXML private TextField tfNoiDung;
	@FXML private TextField tfDonGia;
	@FXML private Label lbTriGia;
	
	private PTP_PTCKDTO ptck = null;
	
	public void initialize(PTP_PTCKDTO ptck) {
		this.ptck = ptck;
		lbPhieuThue.setText(ptck.getMaPTPhong().toString());
		tfNoiDung.setText(ptck.getNoiDung());
		lbTriGia.setText(ptck.getTriGia().toString());
		tfDonGia.setText(ptck.getDonGia().toString());
		
		cbbPTCK.setItems(FXCollections.observableArrayList("Phụ thu", "Chiết khấu"));
		if(ptck.getTriGia() > 0)
			cbbPTCK.getSelectionModel().selectFirst();
		else
			cbbPTCK.getSelectionModel().selectLast();
		
		snSoLuong.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, ptck.getSoLuong()));
		cbbPTCK.valueProperty().addListener(new ChangeListener<String>() {
	        public void changed(ObservableValue<? extends String> loai, String loaiCu, String loaiMoi) {
	        	calTriGia();
	        }
		}
		);
		tfDonGia.textProperty().addListener((donGia, donGiaCu, donGiaMoi) -> {
			calTriGia();
		}
		);
		snSoLuong.valueProperty().addListener((soLuong, soLuongCu, soLuongMoi)-> {
			calTriGia();
		}
		);		
	}
	
	public void handleXacNhanSuaPTCK() throws NumberFormatException, SQLException {
		PTP_PTCKDTO newPTCK = new PTP_PTCKDTO(
				ptck.getMaPTCKPhong(),
				Integer.parseInt(lbPhieuThue.getText()),
				tfNoiDung.getText(),
				snSoLuong.getValue(),
				Integer.parseInt(tfDonGia.getText()),
				Integer.parseInt(lbTriGia.getText())
				);
		
		if(PTP_PTCKBUS.updatePTP_PTCK(newPTCK)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thành công");
			alert.setHeaderText("Sửa phụ thu/ chiết khấu thành công!");
			alert.setContentText(String.format("Nội dung phụ thu/ chiết khấu: %s.", ptck.getNoiDung()));
			alert.showAndWait();
			
			MainController mainController = (MainController) lbPhieuThue.getScene().getUserData();
			mainController.loadTablePTPPTCKByMaPT(Integer.parseInt(lbPhieuThue.getText()));
			Stage stage = (Stage) lbPhieuThue.getScene().getWindow();
			stage.close();
		}
	}
	
	public void calTriGia() {
		// Todo: Dùng regex để kiểm soát giá trị nhập vào là số, và khác null rồi mới thực hiện tính lại giá trị Trị giá
		Integer isPTCK = cbbPTCK.getValue().equals("Phụ thu") ? 1 : -1;
		Integer triGia = isPTCK * snSoLuong.getValue() * Integer.parseInt(tfDonGia.getText());
		lbTriGia.setText(triGia.toString());
	}
	
	public void handleHuySuaPTCK() {
		Stage stage = (Stage) lbPhieuThue.getScene().getWindow();
		stage.close();
	}
}
