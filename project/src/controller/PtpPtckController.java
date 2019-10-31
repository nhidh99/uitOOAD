package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.PtpPtckBUS;
import DTO.PTPhongDTO;
import DTO.PtpPtckDTO;
import helper.MoneyFormatHelper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PtpPtckController implements Initializable {

	@FXML
	private Label lbPhieuThue;
	@FXML
	private ComboBox<String> cbbPTCK;
	@FXML
	private Spinner<Integer> snSoLuong;
	@FXML
	private TextField tfNoiDung;
	@FXML
	private TextField tfDonGia;
	@FXML
	private Label lbTriGia;
	@FXML
	private Label lbTieuDe;

	private enum Tag {
		INSERT, UPDATE
	};

	Tag tag;

	PtpPtckDTO ptp_ptck;
	PTPhongDTO ptPhong;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbbPTCK.setItems(FXCollections.observableArrayList("Phụ thu", "Chiết khấu"));
		cbbPTCK.getSelectionModel().selectFirst();
		tfDonGia.setText("0");
		lbTriGia.setText("0");

		snSoLuong.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
		tfDonGia.textProperty().addListener((obs, oldValue, newValue) -> calculateGiaTri());
		snSoLuong.valueProperty().addListener((obs, oldValue, newValue) -> calculateGiaTri());
		cbbPTCK.valueProperty().addListener((obs, oldValue, newValue) -> calculateGiaTri());
	}

	public void initialize(PTPhongDTO ptPhong) {
		tag = Tag.INSERT;
		this.ptPhong = ptPhong;
		lbPhieuThue.setText(String.format("%d (Phòng %s)", ptPhong.getMaPTPhong(), ptPhong.getMaPhong()));
	}

	public void initialize(PtpPtckDTO ptck) {
		tag = Tag.UPDATE;
		this.ptPhong = ptck.getPTPhong();
		this.ptp_ptck = ptck;
		
		if (ptck.getTriGiaValue() < 0) cbbPTCK.getSelectionModel().selectLast();
		lbTieuDe.setText("💸 SỬA PT/CK");
		tfDonGia.setText(ptck.getDonGiaValue().toString());
		snSoLuong.getValueFactory().setValue(ptck.getSoLuong());
		tfNoiDung.setText(ptck.getNoiDung());
		lbTriGia.setText(MoneyFormatHelper.format(ptck.getTriGiaValue(), "VND"));
		lbPhieuThue.setText(String.format("%d (Phòng %s)", ptPhong.getMaPTPhong(), ptPhong.getMaPhong()));
	}

	private void calculateGiaTri() {
		try {
			int heso = (cbbPTCK.getValue().equals("Phụ thu") ? 1 : -1);
			Integer newTriGia = heso * snSoLuong.getValue() * Integer.parseUnsignedInt(tfDonGia.getText());
			lbTriGia.setText(MoneyFormatHelper.format(newTriGia, "VND"));
		} catch (NumberFormatException ex) {
			lbTriGia.setText(null);
		}
	}

	public void handleXacNhan(ActionEvent e) {
		
		if (!(tfNoiDung.getText().matches("^.{1,45}$")
				&& tfDonGia.getText().matches("^[0-9]{1,8}$"))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại!");
			alert.setHeaderText("Thêm ptck thất bại!");
			alert.setContentText(
					"- Nội dung ptck tối đa 45 kí tự.\n"
					+ "- Đơn giá ptck là số không âm dưới 100 triệu.");
			alert.showAndWait();
			return;
		}
		
		switch (tag) {
		case INSERT: {
			PtpPtckDTO ptck = new PtpPtckDTO(ptPhong, tfNoiDung.getText(), snSoLuong.getValue(),
					Integer.valueOf(tfDonGia.getText()), MoneyFormatHelper.fromString(lbTriGia.getText()));

			try {
				if (PtpPtckBUS.insertPtpPtck(ptck)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Thêm phụ thu/chiết khấu thành công!");
					alert.setContentText(String.format("Nội dung phụ thu/chiết khấu: %s.", ptck.getNoiDung()));
					alert.showAndWait();

					MainController mainController = (MainController) lbPhieuThue.getScene().getUserData();
					mainController.loadTablePtpPTCK(ptPhong.getMaPTPhong());
					Stage stage = (Stage) lbPhieuThue.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Thêm ptck thất bại!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Thêm ptck thất bại!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();
			}
			break;
		}
		
		case UPDATE: {
			PtpPtckDTO ptck = new PtpPtckDTO(ptp_ptck.getMaPTCKPhong(), ptPhong, tfNoiDung.getText(), snSoLuong.getValue(),
					Integer.valueOf(tfDonGia.getText()), MoneyFormatHelper.fromString(lbTriGia.getText()));

			try {
				if (PtpPtckBUS.updatePtpPtck(ptck)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Sửa phụ thu/chiết khấu thành công!");
					alert.setContentText(String.format("Nội dung phụ thu/chiết khấu: %s.", ptck.getNoiDung()));
					alert.showAndWait();

					MainController mainController = (MainController) lbPhieuThue.getScene().getUserData();
					mainController.loadTablePtpPTCK(ptPhong.getMaPTPhong());
					Stage stage = (Stage) lbPhieuThue.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Sửa ptck thất bại!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Sửa ptck thất bại!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();
			}
			break;
		}
		}
	}

	public void handleHuyBo(ActionEvent e) {
		Stage stage = (Stage) lbPhieuThue.getScene().getWindow();
		stage.close();
	}
}
