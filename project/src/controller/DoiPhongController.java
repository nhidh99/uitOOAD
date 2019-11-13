package controller;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import BUS.PTPhongBUS;
import BUS.PhongBUS;
import DTO.PTPhongDTO;
import helper.AlertHelper;
import helper.ConfirmDialogHelper;
import helper.DateFormatHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class DoiPhongController implements Initializable {

	@FXML
	Button btnXacNhan;
	@FXML
	Label lbMaPhongCu;
	@FXML
	Label lbNgayTra;
	@FXML
	DatePicker dpNgayDoi;
	@FXML
	ComboBox<String> cbbMaPhongMoi;
	@FXML
	Spinner<Integer> snGioDoi;

	PTPhongDTO ptphong;
	boolean isSuccess;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dpNgayDoi.setConverter(DateFormatHelper.getDatePickerFormatter());
		dpNgayDoi.setValue(LocalDate.now());
		snGioDoi.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, LocalDateTime.now().getHour()));

		dpNgayDoi.valueProperty().addListener(e -> {
			isSuccess = false;
			btnXacNhan.setText("✔ TÌM PHÒNG");
			cbbMaPhongMoi.setItems(null);
		});

		snGioDoi.valueProperty().addListener(e -> {
			isSuccess = false;
			btnXacNhan.setText("✔ TÌM PHÒNG");
			cbbMaPhongMoi.setItems(null);
		});
	}

	public void initialize(PTPhongDTO ptp) {
		lbMaPhongCu.setText(ptp.getMaPhong());
		lbNgayTra.setText(String.format("%dh %s", ptp.getNgayTraValue().toLocalDateTime().getHour(),
				DateFormatHelper.toString(ptp.getNgayTraValue().toLocalDateTime().toLocalDate())));
		this.ptphong = ptp;
	}

	private void loadBookableRoom() {
		Timestamp ngayNhan = Timestamp
				.valueOf(LocalDateTime.of(dpNgayDoi.getValue(), LocalTime.of(snGioDoi.getValue(), 0)));

		if (ngayNhan.compareTo(ptphong.getNgayTraValue()) >= 0) {
			AlertHelper.showAlert("Lỗi", "Ngày đổi phòng phải nhỏ hơn ngày trả");
			cbbMaPhongMoi.setItems(null);
			return;
		}

		ObservableList<String> dsMaPhongMoi = FXCollections.observableArrayList();
		try {
			PhongBUS.getDSPhongCoTheThue(ngayNhan, ptphong.getNgayTraValue(), ptphong.getMaLoaiPhong()).stream()
					.forEach(phong -> dsMaPhongMoi.add(phong.getMaPhong()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cbbMaPhongMoi.setItems(dsMaPhongMoi);

		if (dsMaPhongMoi.isEmpty()) {
			isSuccess = false;
			btnXacNhan.setText("✔ TÌM PHÒNG");
			AlertHelper.showAlert("Lỗi", "Không tìm thấy phòng để đổi");
		} else {
			isSuccess = true;
			btnXacNhan.setText("✔ ĐỔI PHÒNG");
			cbbMaPhongMoi.getSelectionModel().selectFirst();
		}
	}

	public void handleXacNhan() {
		if (isSuccess) {
			ptphong.setMaPhong(cbbMaPhongMoi.getSelectionModel().getSelectedItem());
			try {
				if (ConfirmDialogHelper.confirm("Xác nhận đổi phòng?") && PTPhongBUS.updatePTPhong(ptphong)) {
					AlertHelper.showAlert("Thành công",
							"Đã đổi phòng " + lbMaPhongCu.getText() + " sang phòng " + ptphong.getMaPhong());
					Stage stage = (Stage) lbMaPhongCu.getScene().getWindow();										
					Runnable reloadTablePhong = (Runnable) stage.getScene().getUserData();
					reloadTablePhong.run();
					stage.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			loadBookableRoom();
		}
	}
	
	public void handleHuyBo() {
		Stage stage = (Stage) lbMaPhongCu.getScene().getWindow();		
		stage.close();
	}
}
