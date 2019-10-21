package controller.phong;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.LoaiPhongBUS;
import BUS.PhongBUS;
import BUS.TinhTrangBUS;
import DTO.LoaiPhongDTO;
import DTO.PhongDTO;
import DTO.TinhTrangDTO;
import controller.MainController;
import helper.MoneyFormatHelper;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;

public class themPhongController implements Initializable {
	@FXML
	private TextField tfMaPhong;

	@FXML
	private ComboBox<TinhTrangDTO> cbbTinhTrang;

	@FXML
	private Label lbKhachToiDa;

	@FXML
	private ComboBox<LoaiPhongDTO> cbbLoaiPhong;

	@FXML
	private Label lbDonGia;

	@FXML
	private TextField tfGhiChu;

	@FXML
	public void handleBtnXacNhan(ActionEvent e) throws SQLException {
		try {
			PhongDTO phongMoi = new PhongDTO(tfMaPhong.getText(), cbbLoaiPhong.getSelectionModel().getSelectedItem(),
					cbbTinhTrang.getSelectionModel().getSelectedItem(), tfGhiChu.getText());

			if (PhongBUS.insertPhong(phongMoi)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Thêm phòng thành công!");
				alert.setContentText("Thêm thành công phòng mới.");
				alert.showAndWait();

				MainController mainController = (MainController) tfMaPhong.getScene().getUserData();

				if (!mainController.tpPhong.getChildren().isEmpty()) {
					mainController.tpPhong.getChildren().clear();
				}
				mainController.loadTablePhong();

				Stage stage = (Stage) tfMaPhong.getScene().getWindow();
				stage.close();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Thêm phòng thất bại!");
				alert.setContentText("Số phòng bạn nhập đã tồn tại, vui lòng nhập số phòng khác!");
				alert.showAndWait();
			}
		} catch (NullPointerException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Thêm phòng thất bại!");
			alert.setContentText("Vui lòng chọn loại phòng và loại tình trạng khác bất kì!");
			alert.showAndWait();
		}
	}

	@FXML
	public void handleBtnHuyBo(ActionEvent e) {
		Stage stage = (Stage) tfMaPhong.getScene().getWindow();
		stage.close();
	}

	private void initComboboxLoaiPhong() {
		cbbLoaiPhong.valueProperty().addListener(new ChangeListener<LoaiPhongDTO>() {
			@Override
			public void changed(ObservableValue<? extends LoaiPhongDTO> loaiPhong, LoaiPhongDTO loaiPhongCu,
					LoaiPhongDTO loaiPhongMoi) {
				if (loaiPhongMoi == null) {
					lbDonGia.setText("Theo loại phòng");
				} else {
					lbDonGia.setText(MoneyFormatHelper.format(loaiPhongMoi.getDonGia(), "VND"));
					lbKhachToiDa.setText(loaiPhongMoi.getSoKhachToiDa().toString());
				}
			}
		});
	}

	private void initComboboxTinhTrang() {
		cbbTinhTrang.valueProperty().addListener(new ChangeListener<TinhTrangDTO>() {
			@Override
			public void changed(ObservableValue<? extends TinhTrangDTO> tinhTrang, TinhTrangDTO tinhTrangCu,
					TinhTrangDTO tinhTrangMoi) {
				if (tinhTrangMoi == null) {
					lbDonGia.setText("Theo tình trạng");
				} else {
					// cbbTinhTrang.getSelectionModel().getSelectedItem()
				}
			}
		});

	}

	private void loadComboboxTinhTrang() {
		try {
			ObservableList<TinhTrangDTO> dsTinhTrang = FXCollections.observableArrayList();
			for (TinhTrangDTO tinhTrang : TinhTrangBUS.getDSTinhTrang()) {
				dsTinhTrang.add(tinhTrang);
			}

			Callback<ListView<TinhTrangDTO>, ListCell<TinhTrangDTO>> cellFactory = new Callback<ListView<TinhTrangDTO>, ListCell<TinhTrangDTO>>() {
				@Override
				public ListCell<TinhTrangDTO> call(ListView<TinhTrangDTO> lvTinhTrang) {
					final ListCell<TinhTrangDTO> lcTinhTrang = new ListCell<TinhTrangDTO>() {
						@Override
						protected void updateItem(TinhTrangDTO tinhTrang, boolean empty) {
							super.updateItem(tinhTrang, empty);
							if (tinhTrang != null) {
								this.setText(tinhTrang.getTenTinhTrang());
							} else
								this.setText("Bất kì");
						}
					};
					return lcTinhTrang;
				}
			};

			cbbTinhTrang.setButtonCell(cellFactory.call(null));
			cbbTinhTrang.setCellFactory(cellFactory);
			cbbTinhTrang.setItems(dsTinhTrang);
			cbbTinhTrang.getSelectionModel().selectFirst();
		} catch (SQLException e) {
			// do nothing :)
		}
	}

	private void loadComboboxLoaiPhong() {
		try {
			ObservableList<LoaiPhongDTO> dsLoaiPhong = FXCollections.observableArrayList();
			for (LoaiPhongDTO loaiPhong : LoaiPhongBUS.getDSLoaiPhong()) {
				dsLoaiPhong.add(loaiPhong);
			}

			Callback<ListView<LoaiPhongDTO>, ListCell<LoaiPhongDTO>> cellFactory = new Callback<ListView<LoaiPhongDTO>, ListCell<LoaiPhongDTO>>() {
				@Override
				public ListCell<LoaiPhongDTO> call(ListView<LoaiPhongDTO> lvLoaiPhong) {
					final ListCell<LoaiPhongDTO> lcLoaiPhong = new ListCell<LoaiPhongDTO>() {
						@Override
						protected void updateItem(LoaiPhongDTO loaiPhong, boolean empty) {
							super.updateItem(loaiPhong, empty);
							if (loaiPhong != null) {
								this.setText(loaiPhong.getTenLoaiPhong());
							} else
								this.setText("Bất kì");
						}
					};
					return lcLoaiPhong; 
				}
			};

			cbbLoaiPhong.setButtonCell(cellFactory.call(null));
			cbbLoaiPhong.setCellFactory(cellFactory);
			cbbLoaiPhong.setItems(dsLoaiPhong);
			cbbLoaiPhong.getSelectionModel().selectFirst();
		} catch (SQLException e) {
			// do nothing :)
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.initComboboxLoaiPhong();
		this.loadComboboxLoaiPhong();
		this.initComboboxTinhTrang();
		this.loadComboboxTinhTrang();
	}
}
