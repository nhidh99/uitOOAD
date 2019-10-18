package controller.phieuthue;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.PTP_PTCKBUS;
import DTO.PTP_PTCKDTO;
import controller.MainController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ThemPTCKController  implements Initializable{

	@FXML private Label lbPhieuThue;
	@FXML private ComboBox<String> cbbPTCK;
	@FXML private Spinner<Integer> snSoLuong;
	@FXML private TextField tfNoiDung;
	@FXML private TextField tfDonGia;
	@FXML private Label lbTriGia;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbbPTCK.setItems(FXCollections.observableArrayList("Phụ thu", "Chiết khấu"));
		cbbPTCK.getSelectionModel().selectFirst();
		snSoLuong.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
		lbTriGia.setText("0");
		tfDonGia.setText("0");
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
	
	public void setMaPTPhong(Integer maPhieuThue) {
		lbPhieuThue.setText(maPhieuThue.toString());
	}
	
	public void handleXacNhanThemPTCK() throws NumberFormatException, SQLException {
		PTP_PTCKDTO ptck = new PTP_PTCKDTO(
				PTP_PTCKBUS.getMaxMaPTP_PTCK(),
				Integer.parseInt(lbPhieuThue.getText()),
				tfNoiDung.getText(),
				snSoLuong.getValue(),
				Integer.parseInt(tfDonGia.getText()),
				Integer.parseInt(lbTriGia.getText())
				);
		
		if(PTP_PTCKBUS.insertPTP_PTCK(ptck)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thành công");
			alert.setHeaderText("Thêm phụ thu/ chiết khấu thành công!");
			alert.setContentText(String.format("Nội dung phụ thu/ chiết khấu: %s.", ptck.getNoiDung()));
			alert.showAndWait();
			
			MainController mainController = (MainController) lbPhieuThue.getScene().getUserData();
			mainController.loadTablePTPPTCKByMaPT(Integer.parseInt(lbPhieuThue.getText()));
			Stage stage = (Stage) lbPhieuThue.getScene().getWindow();
			stage.close();
		}
		else {
			//Đã tồn tại dịch vụ trong danh sách hoặc hết hàng tồn: Update lại số lượng
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Thêm dịch vụ thất bại!");
			alert.setContentText("Dịch vụ đã hết hàng!");
			alert.showAndWait();
		}
	}
	
	public void calTriGia() {
		// Todo: Dùng regex để kiểm soát giá trị nhập vào là số, và khác null rồi mới thực hiện tính lại giá trị Trị giá
		Integer isPTCK = cbbPTCK.getValue().equals("Phụ thu") ? 1 : -1;
		Integer triGia = isPTCK * snSoLuong.getValue() * Integer.parseInt(tfDonGia.getText());
		lbTriGia.setText(triGia.toString());
	}
	
	public void handleHuyThemPTCK() {
		Stage stage = (Stage) lbPhieuThue.getScene().getWindow();
		stage.close();
	}
	
}
