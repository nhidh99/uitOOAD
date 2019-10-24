package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.LoaiPhongBUS;
import BUS.PhongBUS;
import BUS.TinhTrangBUS;
import DTO.LoaiPhongDTO;
import DTO.PhongDTO;
import DTO.TinhTrangDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PhongController implements Initializable {

	@FXML
	Label lbTieuDe;

	@FXML
	Label lbKhachToiDa;

	@FXML
	Label lbDonGia;

	@FXML
	TextField tfMaPhong;

	@FXML
	TextField tfGhiChu;

	@FXML
	ComboBox<TinhTrangDTO> cbbTinhTrang;

	@FXML
	ComboBox<LoaiPhongDTO> cbbLoaiPhong;

	enum Tag {
		INSERT, UPDATE
	};

	PhongDTO phong;

	Tag tag = Tag.INSERT;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initComboboxTinhTrang();
		initComboboxLoaiPhong();
	}

	private void initComboboxTinhTrang() {
		try {
			ObservableList<TinhTrangDTO> listTinhTrang = FXCollections.observableArrayList();
			for (TinhTrangDTO tinhTrang : TinhTrangBUS.getDSTinhTrang()) {
				listTinhTrang.add(tinhTrang);
			}

			Callback<ListView<TinhTrangDTO>, ListCell<TinhTrangDTO>> cellFactory = new Callback<ListView<TinhTrangDTO>, ListCell<TinhTrangDTO>>() {
				@Override
				public ListCell<TinhTrangDTO> call(ListView<TinhTrangDTO> lvTinhTrang) {
					final ListCell<TinhTrangDTO> lcTinhTrang = new ListCell<TinhTrangDTO>() {
						@Override
						protected void updateItem(TinhTrangDTO TinhTrang, boolean empty) {
							super.updateItem(TinhTrang, empty);
							if (TinhTrang != null) {
								this.setText(TinhTrang.getTenTinhTrang());
							} else
								this.setText(null);
						}
					};
					return lcTinhTrang;
				}
			};
			cbbTinhTrang.setButtonCell(cellFactory.call(null));
			cbbTinhTrang.setCellFactory(cellFactory);
			cbbTinhTrang.setItems(listTinhTrang);
			cbbTinhTrang.getSelectionModel().selectFirst();
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách tình trạng!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void initialize(PhongDTO phong) {
		this.tag = Tag.UPDATE;
		this.phong = phong;
		lbTieuDe.setText("🏢  SỬA PHÒNG " + phong.getMaPhong());
		tfMaPhong.setText(phong.getMaPhong());
		tfMaPhong.setDisable(true);
		tfGhiChu.setText(phong.getGhiChu());

		ObservableList<LoaiPhongDTO> dsLoaiPhong = cbbLoaiPhong.getItems();
		for (int i = 0; i < dsLoaiPhong.size(); i++) {
			if (phong.getLoaiPhong().getMaLoaiPhong().equals(dsLoaiPhong.get(i).getMaLoaiPhong())) {
				cbbLoaiPhong.getSelectionModel().select(i);
				break;
			}
		}

		ObservableList<TinhTrangDTO> dsTinhTrang = cbbTinhTrang.getItems();
		for (int i = 0; i < dsTinhTrang.size(); i++) {
			if (phong.getTinhTrang().getMaTinhTrang().equals(dsTinhTrang.get(i).getMaTinhTrang())) {
				cbbTinhTrang.getSelectionModel().select(i);
				break;
			}
		}
	}

	private void initComboboxLoaiPhong() {
		try {
			ObservableList<LoaiPhongDTO> listLoaiPhong = FXCollections.observableArrayList();
			for (LoaiPhongDTO tinhTrang : LoaiPhongBUS.getDSLoaiPhong()) {
				listLoaiPhong.add(tinhTrang);
			}

			Callback<ListView<LoaiPhongDTO>, ListCell<LoaiPhongDTO>> cellFactory = new Callback<ListView<LoaiPhongDTO>, ListCell<LoaiPhongDTO>>() {
				@Override
				public ListCell<LoaiPhongDTO> call(ListView<LoaiPhongDTO> lvLoaiPhong) {
					final ListCell<LoaiPhongDTO> lcLoaiPhong = new ListCell<LoaiPhongDTO>() {
						@Override
						protected void updateItem(LoaiPhongDTO LoaiPhong, boolean empty) {
							super.updateItem(LoaiPhong, empty);
							if (LoaiPhong != null) {
								this.setText(LoaiPhong.getTenLoaiPhong());
							} else
								this.setText(null);
						}
					};
					return lcLoaiPhong;
				}
			};
			cbbLoaiPhong.setButtonCell(cellFactory.call(null));
			cbbLoaiPhong.setCellFactory(cellFactory);
			cbbLoaiPhong.setItems(listLoaiPhong);
			cbbLoaiPhong.valueProperty().addListener((obs, oldValue, newValue) -> {
				lbDonGia.setText(newValue.getDonGia() + " VND");
				lbKhachToiDa.setText(newValue.getSoKhachToiDa().toString() + " khách");
			});
			cbbLoaiPhong.getSelectionModel().selectFirst();
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách loại phòng!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleXacNhan(ActionEvent e) {
		switch (tag) {
		case INSERT: {
			try {
				PhongDTO phongMoi = new PhongDTO(tfMaPhong.getText(),
						cbbLoaiPhong.getSelectionModel().getSelectedItem(),
						cbbTinhTrang.getSelectionModel().getSelectedItem(), tfGhiChu.getText());

				if (PhongBUS.insertPhong(phongMoi)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Thêm phòng thành công!");
					alert.setContentText("Đã thêm thành công phòng " + phongMoi.getMaPhong());
					alert.showAndWait();

					MainController mainController = (MainController) tfMaPhong.getScene().getUserData();
					mainController.loadTablePhong();
					mainController.handleTraCuuPhong();
					Stage stage = (Stage) tfMaPhong.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Thêm phòng thất bại!");
					alert.setContentText("Số phòng bạn nhập đã tồn tại, vui lòng nhập số phòng khác!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Thêm phòng thất bại!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();
			}
			break;
		}

		case UPDATE: {
			try {
				PhongDTO phongMoi = new PhongDTO(tfMaPhong.getText(),
						cbbLoaiPhong.getSelectionModel().getSelectedItem(),
						cbbTinhTrang.getSelectionModel().getSelectedItem(), tfGhiChu.getText());

				if (PhongBUS.updatePhong(phongMoi)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Sửa phòng thành công!");
					alert.setContentText("Sửa thành công thông tin phòng " + phong.getMaPhong());
					alert.showAndWait();

					MainController mainController = (MainController) tfMaPhong.getScene().getUserData();
					mainController.showChiTietPhong(phongMoi);
					mainController.handleTraCuuPhong();
					Stage stage = (Stage) tfMaPhong.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Sửa phòng thất bại!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Sửa phòng thất bại!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();
			}
			break;
		}
		}
	}

	public void handleHuyBo(ActionEvent e) {
		Stage stage = (Stage) tfMaPhong.getScene().getWindow();
		stage.close();
	}
}
