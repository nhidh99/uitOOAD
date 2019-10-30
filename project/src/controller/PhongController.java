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
			alert.setTitle("Lá»—i");
			alert.setHeaderText("KhÃ´ng thá»ƒ táº£i danh sÃ¡ch tÃ¬nh tráº¡ng!");
			alert.setContentText("Lá»—i database!");
			alert.showAndWait();
		}
	}

	public void initialize(PhongDTO phong) {
		this.tag = Tag.UPDATE;
		this.phong = phong;
		lbTieuDe.setText("ðŸ�¢  Sá»¬A PHÃ’NG " + phong.getMaPhong());
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
				lbKhachToiDa.setText(newValue.getSoKhachToiDa().toString() + " khÃ¡ch");
			});
			cbbLoaiPhong.getSelectionModel().selectFirst();
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lá»—i");
			alert.setHeaderText("KhÃ´ng thá»ƒ táº£i danh sÃ¡ch loáº¡i phÃ²ng!");
			alert.setContentText("Lá»—i database!");
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
					alert.setTitle("ThÃ nh cÃ´ng");
					alert.setHeaderText("ThÃªm phÃ²ng thÃ nh cÃ´ng!");
					alert.setContentText("Ä�Ã£ thÃªm thÃ nh cÃ´ng phÃ²ng " + phongMoi.getMaPhong());
					alert.showAndWait();

					MainController mainController = (MainController) tfMaPhong.getScene().getUserData();
					mainController.loadTablePhong();
					mainController.handleTraCuuPhong();
					Stage stage = (Stage) tfMaPhong.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Tháº¥t báº¡i");
					alert.setHeaderText("ThÃªm phÃ²ng tháº¥t báº¡i!");
					alert.setContentText(
							"Sá»‘ phÃ²ng báº¡n nháº­p Ä‘Ã£ tá»“n táº¡i, vui lÃ²ng nháº­p sá»‘ phÃ²ng khÃ¡c!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Tháº¥t báº¡i");
				alert.setHeaderText("ThÃªm phÃ²ng tháº¥t báº¡i!");
				alert.setContentText("Lá»—i database!");
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
					alert.setTitle("ThÃ nh cÃ´ng");
					alert.setHeaderText("Sá»­a phÃ²ng thÃ nh cÃ´ng!");
					alert.setContentText("Sá»­a thÃ nh cÃ´ng thÃ´ng tin phÃ²ng " + phong.getMaPhong());
					alert.showAndWait();

					MainController mainController = (MainController) tfMaPhong.getScene().getUserData();
					mainController.showChiTietPhong(phongMoi);
					mainController.handleTraCuuPhong();
					Stage stage = (Stage) tfMaPhong.getScene().getWindow();
					stage.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Tháº¥t báº¡i");
					alert.setHeaderText("Sá»­a phÃ²ng tháº¥t báº¡i!");
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Tháº¥t báº¡i");
				alert.setHeaderText("Sá»­a phÃ²ng tháº¥t báº¡i!");
				alert.setContentText("Lá»—i database!");
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
