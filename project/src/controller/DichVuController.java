package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.*;
import DTO.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;

public class DichVuController implements Initializable {
	@FXML
	private Label lbTieuDe;
	@FXML
	private TextField tfTenDichVu;
	@FXML
	private TextField tfDonViTinh;
	@FXML
	private TextField tfDonGia;
	@FXML
	private Spinner<Integer> snSoLuongTon;
	@FXML
	private ComboBox<String> cbbTonKho;
	@FXML
	private ComboBox<LoaiDichVuDTO> cbbLoaiDichVu;
	@FXML
	private ComboBox<NhaCungCapDTO> cbbNhaCungCap;

	private Integer maDichVu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			initCbbLoaiDichVu();
			initCbbNhaCungCap();
			initCbbTonKho();
			initSpinnerSoLuongTon();
			maDichVu = null;
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách nhà cung cấp/loại dịch vụ!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	private void initSpinnerSoLuongTon() {
		snSoLuongTon.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200));
		snSoLuongTon.focusedProperty().addListener((obs, oldValue, newValue) -> {
			try {
				if (newValue == false) {
					snSoLuongTon.increment(0);
				}
			} catch (NumberFormatException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Hiệu chỉnh dịch vụ thất bại!");
				alert.setContentText("Thông tin số lượng tồn không hợp lệ!");
				alert.showAndWait();
			}
		});
	}

	public void initialize(DichVuDTO dichVu) {
		lbTieuDe.setText("🎲 SỬA DỊCH VỤ");
		maDichVu = dichVu.getMaDichVu();
		tfTenDichVu.setText(dichVu.getTenDichVu());
		tfDonViTinh.setText(dichVu.getDonViTinh());
		tfDonGia.setText(dichVu.getDonGiaValue().toString());

		ObservableList<LoaiDichVuDTO> dsLoaiDichVu = cbbLoaiDichVu.getItems();
		for (int i = 0; i < dsLoaiDichVu.size(); i++) {
			if (dichVu.getMaLoaiDichVu().equals(dsLoaiDichVu.get(i).getMaLoaiDichVu())) {
				cbbLoaiDichVu.getSelectionModel().select(i);
				break;
			}
		}

		ObservableList<NhaCungCapDTO> dsNhaCungCap = cbbNhaCungCap.getItems();
		for (int i = 0; i < dsNhaCungCap.size(); i++) {
			if (dichVu.getMaNhaCungCap().equals(dsNhaCungCap.get(i).getMaNhaCungCap())) {
				cbbNhaCungCap.getSelectionModel().select(i);
				break;
			}
		}

		if (dichVu.getSoLuongTon() == null) {
			cbbTonKho.setValue("Không");
			snSoLuongTon.setDisable(true);
		} else {
			cbbTonKho.setValue("Có");
			snSoLuongTon.setDisable(false);
			snSoLuongTon.getValueFactory().setValue(dichVu.getSoLuongTon());
		}
	}

	private void initCbbTonKho() {
		cbbTonKho.setItems(FXCollections.observableArrayList("Có", "Không"));
		cbbTonKho.getSelectionModel().selectFirst();
		cbbTonKho.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> loaiTonKho, String oldValue, String newValue) {
				snSoLuongTon.setDisable(newValue.equals("Không"));
			}
		});
	}

	private void initCbbNhaCungCap() throws SQLException {
		ObservableList<NhaCungCapDTO> listNhaCungCap = FXCollections.observableArrayList();
		for (NhaCungCapDTO ncc : NhaCungCapBUS.getDSNhaCungCap()) {
			listNhaCungCap.add(ncc);
		}

		Callback<ListView<NhaCungCapDTO>, ListCell<NhaCungCapDTO>> cellFactory = new Callback<ListView<NhaCungCapDTO>, ListCell<NhaCungCapDTO>>() {
			@Override
			public ListCell<NhaCungCapDTO> call(ListView<NhaCungCapDTO> lvNhaCungCap) {
				final ListCell<NhaCungCapDTO> lcNhaCungCap = new ListCell<NhaCungCapDTO>() {
					@Override
					protected void updateItem(NhaCungCapDTO nhaCungCap, boolean empty) {
						super.updateItem(nhaCungCap, empty);
						if (nhaCungCap != null) {
							this.setText(nhaCungCap.getTenNhaCungCap());
						} else
							this.setText(null);
					}
				};
				return lcNhaCungCap;
			}
		};

		cbbNhaCungCap.setButtonCell(cellFactory.call(null));
		cbbNhaCungCap.setCellFactory(cellFactory);
		cbbNhaCungCap.setItems(listNhaCungCap);
		cbbNhaCungCap.getSelectionModel().selectFirst();
	}

	private void initCbbLoaiDichVu() throws SQLException {
		ObservableList<LoaiDichVuDTO> listLoaiDichVu = FXCollections.observableArrayList();
		for (LoaiDichVuDTO ldv : LoaiDichVuBUS.getDSLoaiDichVu()) {
			listLoaiDichVu.add(ldv);
		}

		Callback<ListView<LoaiDichVuDTO>, ListCell<LoaiDichVuDTO>> cellFactory = new Callback<ListView<LoaiDichVuDTO>, ListCell<LoaiDichVuDTO>>() {
			@Override
			public ListCell<LoaiDichVuDTO> call(ListView<LoaiDichVuDTO> lvLoaiDichVu) {
				final ListCell<LoaiDichVuDTO> lcLoaiDichVu = new ListCell<LoaiDichVuDTO>() {
					@Override
					protected void updateItem(LoaiDichVuDTO loaiDichVu, boolean empty) {
						super.updateItem(loaiDichVu, empty);
						if (loaiDichVu != null) {
							this.setText(loaiDichVu.getTenLoaiDichVu());
						} else
							this.setText(null);
					}
				};
				return lcLoaiDichVu;
			}
		};
		cbbLoaiDichVu.setButtonCell(cellFactory.call(null));
		cbbLoaiDichVu.setCellFactory(cellFactory);
		cbbLoaiDichVu.setItems(listLoaiDichVu);
		cbbLoaiDichVu.getSelectionModel().selectFirst();
	}

	public void handleXacNhan(ActionEvent e) {

		if (!(tfTenDichVu.getText().matches("^.{1,30}$") && tfDonViTinh.getText().matches("^([^0-9]{1,30})$")
				&& tfDonGia.getText().matches("^[0-9]{1,9}$"))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại!");
			alert.setHeaderText("Thêm dịch vụ thất bại!");
			alert.setContentText("- Tên dịch vụ tối đa 30 kí tự.\n"
					+ "- Đơn vị tính tối đa 30 kí tự và không chứa số.\n" + "- Đơn giá là số không âm dưới 1 tỉ VND.");
			alert.showAndWait();
			return;
		}

		DichVuDTO dichVu = new DichVuDTO(maDichVu, tfTenDichVu.getText(), tfDonViTinh.getText(),
				snSoLuongTon.isDisable() ? -1 : snSoLuongTon.getValue(), Integer.parseUnsignedInt(tfDonGia.getText()),
				cbbLoaiDichVu.getSelectionModel().getSelectedItem(),
				cbbNhaCungCap.getSelectionModel().getSelectedItem());
		try {

			if (maDichVu == null) {
				if (DichVuBUS.insertDichVu(dichVu)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thông báo");
					alert.setHeaderText("Thêm dịch vụ thành công!");
					alert.setContentText("Đã thêm dịch vụ " + dichVu.getTenDichVu());
					alert.showAndWait();
					Stage stage = (Stage) tfTenDichVu.getScene().getWindow();
					stage.close();
					MainController controller = (MainController) stage.getScene().getUserData();
					controller.loadTableDichVu();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Thêm dịch vụ thất bại!");
					alert.showAndWait();
				}
			} else {
				if (DichVuBUS.updateDichVu(dichVu)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thông báo");
					alert.setHeaderText("Sửa dịch vụ thành công!");
					alert.setContentText("Đã sửa dịch vụ " + dichVu.getTenDichVu());
					alert.showAndWait();
					Stage stage = (Stage) tfTenDichVu.getScene().getWindow();
					stage.close();
					MainController controller = (MainController) stage.getScene().getUserData();
					controller.loadTableDichVu();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Sửa dịch vụ thất bại!");
					alert.showAndWait();
				}
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Thêm dịch vụ thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NumberFormatException numberFormatException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Thêm dịch vụ thất bại!");
			alert.setContentText("Số lượng tồn không hợp lệ!");
			alert.showAndWait();
		}
	}

	public void handleHuyBo(ActionEvent event) {
		Stage stage = (Stage) tfTenDichVu.getScene().getWindow();
		stage.close();
	}
}