package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import BUS.*;
import DTO.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {
	@FXML
	TableView<NhanVienDTO> tvNhanVien;
	@FXML
	TableColumn<NhanVienDTO, Integer> tcNV_STT;
	@FXML
	TableColumn<NhanVienDTO, Integer> tcNV_MaNhanVien;
	@FXML
	TableColumn<NhanVienDTO, String> tcNV_TenNhanVien;
	@FXML
	TableColumn<NhanVienDTO, String> tcNV_CMND;
	@FXML
	TableColumn<NhanVienDTO, String> tcNV_SoDienThoai;
	@FXML
	TableColumn<NhanVienDTO, String> tcNV_DiaChi;
	@FXML
	TableColumn<NhanVienDTO, String> tcNV_Email;
	@FXML
	TableColumn<NhanVienDTO, String> tcNV_ChucVu;

	@FXML
	Label lbTS_SoNgayTraCoc;
	@FXML
	Label lbTS_TiLeThueVAT;
	@FXML
	Label lbTS_TiLeTienCoc;
	@FXML
	Label lbTS_QuaKhach;
	@FXML
	Label lbTS_TraPhongTre;

	@FXML
	TableView<LoaiDichVuDTO> tvLoaiDichVu;
	@FXML
	TableColumn<LoaiDichVuDTO, Integer> tcLDV_STT;
	@FXML
	TableColumn<LoaiDichVuDTO, Integer> tcLDV_MaLoaiDV;
	@FXML
	TableColumn<LoaiDichVuDTO, String> tcLDV_TenLoaiDV;

	@FXML
	TableView<NhaCungCapDTO> tvNhaCungCap;
	@FXML
	TableColumn<NhaCungCapDTO, Integer> tcNCC_STT;
	@FXML
	TableColumn<NhaCungCapDTO, Integer> tcNCC_MaNhaCC;
	@FXML
	TableColumn<NhaCungCapDTO, String> tcNCC_TenNhaCC;
	@FXML
	TableColumn<NhaCungCapDTO, Integer> tcNCC_SoDienThoai;

	@FXML
	TableView<LoaiPhongDTO> tvLoaiPhong;
	@FXML
	TableColumn<LoaiPhongDTO, Integer> tcLP_STT;
	@FXML
	TableColumn<LoaiPhongDTO, Integer> tcLP_MaLoaiPhong;
	@FXML
	TableColumn<LoaiPhongDTO, String> tcLP_TenLoaiPhong;
	@FXML
	TableColumn<LoaiPhongDTO, Integer> tcLP_SoKhachToiDa;
	@FXML
	TableColumn<LoaiPhongDTO, Integer> tcLP_DonGia;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTables();
		loadTables();
	}
	
	private boolean confirmDialog(String content) {
		Alert dialog = new Alert(AlertType.CONFIRMATION);
		dialog.setTitle("Xác nhận");
		dialog.setHeaderText(content);
		ButtonType yesButton = new ButtonType("Xác nhận");
		ButtonType noButton = new ButtonType("Hủy bỏ");
		dialog.getButtonTypes().setAll(yesButton, noButton);
		Optional<ButtonType> result = dialog.showAndWait();
		return result.get() == yesButton;
	}

	private void initTables() {
		initTableNhanVien();
		initTableLoaiPhong();
		initTableNhaCungCap();
		initTableLoaiDichVu();
	}

	private void loadTables() {
		loadTableNhanVien();
		loadTableLoaiPhong();
		loadTableNhaCungCap();
		loadTableLoaiDichVu();
		loadTableThamSo();
	}

	private void initTableNhanVien() {
		tcNV_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvNhanVien.getItems().indexOf(column.getValue()) + 1));
		tcNV_MaNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
		tcNV_TenNhanVien.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
		tcNV_CMND.setCellValueFactory(new PropertyValueFactory<>("CMND"));
		tcNV_DiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
		tcNV_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcNV_SoDienThoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
		tcNV_ChucVu.setCellValueFactory(new PropertyValueFactory<>("chucVu"));
	}

	private void initTableLoaiPhong() {
		tcLP_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvLoaiPhong.getItems().indexOf(column.getValue()) + 1));
		tcLP_MaLoaiPhong.setCellValueFactory(new PropertyValueFactory<>("maLoaiPhong"));
		tcLP_TenLoaiPhong.setCellValueFactory(new PropertyValueFactory<>("tenLoaiPhong"));
		tcLP_SoKhachToiDa.setCellValueFactory(new PropertyValueFactory<>("soKhachToiDa"));
		tcLP_DonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
	}

	private void initTableLoaiDichVu() {
		tcLDV_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvLoaiDichVu.getItems().indexOf(column.getValue()) + 1));
		tcLDV_MaLoaiDV.setCellValueFactory(new PropertyValueFactory<>("maLoaiDichVu"));
		tcLDV_TenLoaiDV.setCellValueFactory(new PropertyValueFactory<>("tenLoaiDichVu"));
	}

	private void initTableNhaCungCap() {
		tcNCC_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvNhaCungCap.getItems().indexOf(column.getValue()) + 1));
		tcNCC_MaNhaCC.setCellValueFactory(new PropertyValueFactory<>("maNhaCungCap"));
		tcNCC_TenNhaCC.setCellValueFactory(new PropertyValueFactory<>("tenNhaCungCap"));
		tcNCC_SoDienThoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
	}

	public void loadTableNhanVien() {
		try {
			ObservableList<NhanVienDTO> dsNhanVien = FXCollections.observableArrayList();
			for (NhanVienDTO nv : NhanVienBUS.getDSNhanVien()) {
				dsNhanVien.add(nv);
			}
			tvNhanVien.setItems(dsNhanVien);
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách loại dịch vụ!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTableLoaiPhong() {
		try {
			ObservableList<LoaiPhongDTO> dsLoaiPhong = FXCollections.observableArrayList();
			for (LoaiPhongDTO lp : LoaiPhongBUS.getDSLoaiPhong()) {
				dsLoaiPhong.add(lp);
			}
			tvLoaiPhong.setItems(dsLoaiPhong);
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách loại phòng!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTableNhaCungCap() {
		try {
			ObservableList<NhaCungCapDTO> dsNhaCungCap = FXCollections.observableArrayList();
			for (NhaCungCapDTO ncc : NhaCungCapBUS.getDSNhaCungCap()) {
				dsNhaCungCap.add(ncc);
			}
			tvNhaCungCap.setItems(dsNhaCungCap);
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách nhà cung cấp!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTableLoaiDichVu() {
		try {
			ObservableList<LoaiDichVuDTO> dsLoaiDichVu = FXCollections.observableArrayList();
			for (LoaiDichVuDTO ldv : LoaiDichVuBUS.getDSLoaiDichVu()) {
				dsLoaiDichVu.add(ldv);
			}
			tvLoaiDichVu.setItems(dsLoaiDichVu);
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách loại dịch vụ!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTableThamSo() {
		try {
			ThamSoDTO thamSo = ThamSoBUS.getThamSo();
			lbTS_SoNgayTraCoc.setText(Integer.toString(thamSo.getSoNgayTraCoc()));
			lbTS_TiLeTienCoc.setText(String.format("%.0f", thamSo.getTiLeTienCoc() * 100) + "%");
			lbTS_TiLeThueVAT.setText(String.format("%.0f", thamSo.getTiLeThueVAT() * 100) + "%");
			lbTS_QuaKhach.setText(String.format("%.0f", thamSo.getPhuThuQuaKhach() * 100) + "%");
			lbTS_TraPhongTre.setText(String.format("%.0f", thamSo.getPhuthuTraPhongTre() * 100) + "%");
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách tham số!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	// Xóa loại phòng
	public void handleXoaLoaiPhong(ActionEvent e) {
		try {
			LoaiPhongDTO loaiPhong = tvLoaiPhong.getSelectionModel().getSelectedItem();
			if (confirmDialog("Xác nhận xóa loại phòng " + loaiPhong.getTenLoaiPhong() + "?")) {
				try {
					if (LoaiPhongBUS.deleteLoaiPhong(loaiPhong.getMaLoaiPhong())) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Thành công");
						alert.setHeaderText("Xóa loại phòng thành công!");
						alert.setContentText("Đã xóa loại phòng " + loaiPhong.getTenLoaiPhong() + "!");
						alert.showAndWait();
						loadTableLoaiPhong();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Lỗi");
						alert.setHeaderText("Không thể xóa loại phòng!");
						alert.setContentText("Vẫn còn phòng loại " + loaiPhong.getTenLoaiPhong() + "!");
						alert.showAndWait();
					}
				} catch (SQLException SQLException) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Không thể xóa loại phòng!");
					alert.setContentText("Lỗi database!");
					alert.showAndWait();
				}
			}
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xóa loại phòng!");
			alert.setContentText("Vui lòng chọn loại phòng cần xóa!");
			alert.showAndWait();
		}
	}

	// Xóa loại dịch vụ
	public void handleXoaLoaiDichVu(ActionEvent e) {
		try {
			LoaiDichVuDTO loaiDichVu = tvLoaiDichVu.getSelectionModel().getSelectedItem();
			if (confirmDialog("Xác nhận xóa loại dịch vụ " + loaiDichVu.getTenLoaiDichVu() + "?")) {
				try {
					if (LoaiDichVuBUS.deleteLoaiDichVu(loaiDichVu.getMaLoaiDichVu())) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Thành công");
						alert.setHeaderText("Xóa loại dịch vụ thành công!");
						alert.setContentText("Đã xóa loại dịch vụ " + loaiDichVu.getTenLoaiDichVu() + "!");
						alert.showAndWait();
						loadTableLoaiDichVu();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Lỗi");
						alert.setHeaderText("Không thể xóa loại dịch vụ!");
						alert.setContentText("Vẫn còn dịch vụ loại " + loaiDichVu.getTenLoaiDichVu() + "!");
						alert.showAndWait();
					}
				} catch (SQLException SQLException) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Không thể xóa loại dịch vụ!");
					alert.setContentText("Lỗi database!");
					alert.showAndWait();
				}
			}
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xóa loại dịch vụ!");
			alert.setContentText("Vui lòng chọn loại dịch vụ cần xóa!");
			alert.showAndWait();
		}
	}
}
