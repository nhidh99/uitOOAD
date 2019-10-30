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
			maDichVu = null;
			snSoLuongTon.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200));
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lá»—i");
			alert.setHeaderText("KhÃ´ng thá»ƒ táº£i danh sÃ¡ch nhÃ  cung cáº¥p/loáº¡i dá»‹ch vá»¥!");
			alert.setContentText("Lá»—i database!");
			alert.showAndWait();
		}
	}

	public void initialize(DichVuDTO dichVu) {
		lbTieuDe.setText("ðŸŽ² Sá»¬A Dá»ŠCH Vá»¤");
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
			cbbTonKho.setValue("KhÃ´ng");
			snSoLuongTon.setDisable(true);
		} else {
			cbbTonKho.setValue("CÃ³");
			snSoLuongTon.setDisable(false);
			snSoLuongTon.getValueFactory().setValue(dichVu.getSoLuongTon());
		}
	}

	private void initCbbTonKho() {
		cbbTonKho.setItems(FXCollections.observableArrayList("CÃ³", "KhÃ´ng"));
		cbbTonKho.getSelectionModel().selectFirst();
		cbbTonKho.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> loaiTonKho, String oldValue, String newValue) {
				snSoLuongTon.setDisable(newValue.equals("KhÃ´ng"));
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
		DichVuDTO dichVu = new DichVuDTO(maDichVu, tfTenDichVu.getText(), tfDonViTinh.getText(),
				snSoLuongTon.isDisable() ? -1 : snSoLuongTon.getValue(), Integer.parseInt(tfDonGia.getText()),
				cbbLoaiDichVu.getSelectionModel().getSelectedItem(),
				cbbNhaCungCap.getSelectionModel().getSelectedItem());
		try {
			if (!tfDonViTinh.getText().matches("^[a-zA-Z]$") || tfDonGia.getText().matches("-([0-9]*)")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại!");
				alert.setHeaderText("Thêm dịch v	ụ thất bại!");
				alert.setContentText("Đơn vị tính không gồm số ! Đơn giá là một số dương !");
				alert.showAndWait();
			}
			else if (maDichVu == null) {
				if (DichVuBUS.insertDichVu(dichVu)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ThÃ´ng bÃ¡o");
					alert.setHeaderText("ThÃªm dá»‹ch vá»¥ thÃ nh cÃ´ng!");
					alert.setContentText("Ä�Ã£ thÃªm dá»‹ch vá»¥ " + dichVu.getTenDichVu());
					alert.showAndWait();
					Stage stage = (Stage) tfTenDichVu.getScene().getWindow();
					stage.close();
					MainController controller = (MainController) stage.getScene().getUserData();
					controller.loadTableDichVu();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lá»—i");
					alert.setHeaderText("ThÃªm dá»‹ch vá»¥ tháº¥t báº¡i!");
					alert.showAndWait();
				}
			} else {
				if (DichVuBUS.updateDichVu(dichVu)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ThÃ´ng bÃ¡o");
					alert.setHeaderText("Sá»­a dá»‹ch vá»¥ thÃ nh cÃ´ng!");
					alert.setContentText("Ä�Ã£ sá»­a dá»‹ch vá»¥ " + dichVu.getTenDichVu());
					alert.showAndWait();
					Stage stage = (Stage) tfTenDichVu.getScene().getWindow();
					stage.close();
					MainController controller = (MainController) stage.getScene().getUserData();
					controller.loadTableDichVu();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lá»—i");
					alert.setHeaderText("Sá»­a dá»‹ch vá»¥ tháº¥t báº¡i!");
					alert.showAndWait();
				}
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lá»—i");
			alert.setHeaderText("ThÃªm dá»‹ch vá»¥ tháº¥t báº¡i!");
			alert.setContentText("Lá»—i database!");
			alert.showAndWait();
		}
	}

	public void handleHuyBo(ActionEvent event) {
		Stage stage = (Stage) tfTenDichVu.getScene().getWindow();
		stage.close();
	}
}