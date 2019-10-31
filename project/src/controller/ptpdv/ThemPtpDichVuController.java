package controller.ptpdv;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.DichVuBUS;
import BUS.PtpDichVuBUS;
import DTO.DichVuDTO;
import DTO.PTPhongDTO;
import DTO.PtpDichVuDTO;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ThemPtpDichVuController implements Initializable {
	@FXML
	private Label lbPhieuThue;
	@FXML
	private ComboBox<DichVuDTO> cbbDichVu;
	@FXML
	private Spinner<Integer> snSoLuong;
	@FXML
	private Label lbSoLuongTon;
	@FXML
	private Label lbLuongTon;
	@FXML
	private TextField tfDonGia;

	private PTPhongDTO ptPhong;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadCbbDichVu();
		cbbDichVu.getSelectionModel().selectFirst();
		snSoLuong.focusedProperty().addListener((obs, oldValue, newValue) -> {
			try {
				if (newValue == false)
					snSoLuong.increment(0);
			} catch (NumberFormatException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Thêm dịch vụ thất bại!");
				alert.setContentText("Thông tin số lượng không hợp lệ!");
				alert.showAndWait();
			}
		});
	}

	public void initialize(PTPhongDTO ptPhong) {
		this.ptPhong = ptPhong;
		lbPhieuThue.setText(String.format("%d (Phòng %s)", ptPhong.getMaPTPhong(), ptPhong.getMaPhong()));
	}

	public void handleThemDichVu() {
		if (!tfDonGia.getText().matches("^[0-9]{1,8}$")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại!");
			alert.setHeaderText("Thêm dịch vụ thất bại!");
			alert.setContentText("- Đơn giá là số không âm dưới 100 triệu VND.");
			alert.showAndWait();
			return;
		}

		try {
			if (snSoLuong.isDisable()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Thêm dịch vụ thất bại!");
				alert.setContentText("Đã hết hàng!");
				alert.showAndWait();
				return;
			}

			PtpDichVuDTO ptp_dv = new PtpDichVuDTO(ptPhong, cbbDichVu.getSelectionModel().getSelectedItem(),
					snSoLuong.getValue(), Integer.parseInt(tfDonGia.getText()));

			if (PtpDichVuBUS.insertPtpDichVu(ptp_dv)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Thêm dịch vụ thành công!");
				alert.setContentText(String.format("Thêm thành công %d %s %s.", ptp_dv.getSoLuong(),
						ptp_dv.getDonViTinh().toLowerCase(), ptp_dv.getDichVu().getTenDichVu()));
				alert.showAndWait();

				MainController mainController = (MainController) lbSoLuongTon.getScene().getUserData();
				mainController.loadTablePTP_DV(ptPhong.getMaPTPhong());
				mainController.loadTableDichVu();
				Stage stage = (Stage) lbSoLuongTon.getScene().getWindow();
				stage.close();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Thêm dịch vụ thất bại!");
				alert.setContentText("Dịch vụ đã hết hàng!");
				alert.showAndWait();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Thêm dịch vụ thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleHuyThemDichVu() {
		Stage stage = (Stage) lbSoLuongTon.getScene().getWindow();
		stage.close();
	}

	public void loadCbbDichVu() {
		try {
			ObservableList<DichVuDTO> dsDichVu = FXCollections.observableArrayList();
			for (DichVuDTO dichVu : DichVuBUS.getDSDichVu()) {
				dsDichVu.add(dichVu);
			}

			Callback<ListView<DichVuDTO>, ListCell<DichVuDTO>> cellFactory = new Callback<ListView<DichVuDTO>, ListCell<DichVuDTO>>() {
				@Override
				public ListCell<DichVuDTO> call(ListView<DichVuDTO> lvDichVu) {
					final ListCell<DichVuDTO> lcDichVu = new ListCell<DichVuDTO>() {
						@Override
						protected void updateItem(DichVuDTO dichVu, boolean empty) {
							super.updateItem(dichVu, empty);
							if (dichVu != null) {
								this.setText(dichVu.getTenDichVu());
							} else
								this.setText(null);
						}
					};
					return lcDichVu;
				}
			};

			cbbDichVu.setButtonCell(cellFactory.call(null));
			cbbDichVu.setCellFactory(cellFactory);

			cbbDichVu.valueProperty().addListener((obs, dichVuCu, dichVuMoi) -> {
				if (dichVuMoi.getSoLuongTon() == null) {
					lbLuongTon.setText("Đơn vị tính:");
					lbSoLuongTon.setText(dichVuMoi.getDonViTinh());
					snSoLuong.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
					snSoLuong.setDisable(false);
				} else if (dichVuMoi.getSoLuongTon() == 0) {
					lbLuongTon.setText("Lượng tồn:");
					lbSoLuongTon.setText("Hết hàng");
					snSoLuong.setDisable(true);
				} else {
					lbLuongTon.setText("Lượng tồn:");
					lbSoLuongTon.setText(dichVuMoi.getSoLuongTon() + " " + dichVuMoi.getDonViTinh());
					snSoLuong.setValueFactory(
							new SpinnerValueFactory.IntegerSpinnerValueFactory(1, dichVuMoi.getSoLuongTon(), 1));
					snSoLuong.setDisable(false);
				}
				tfDonGia.setText(dichVuMoi.getDonGiaValue().toString());
			});
			cbbDichVu.setItems(dsDichVu);
		} catch (SQLException e) {
			// do nothing :)
		}
	}
}
