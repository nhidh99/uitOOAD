package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import BUS.*;
import DTO.*;
import controller.nhanvien.SuaNhanVienController;
import controller.nhanvien.TuyChinhNhanVienController;
import custom.control.ListRoomDetailPane;
import custom.control.RoomDetailPane;
import helper.DateFormatHelper;
import helper.MoneyFormatHelper;
import helper.PopUpStageHelper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainController implements Initializable {

	@FXML
	TilePane tpPhong;

	@FXML
	TabPane tpPhong_ChiTietThue;

	@FXML
	ComboBox<LoaiPhongDTO> cbbTC_LoaiPhong;

	@FXML
	DatePicker dpTC_NgayNhan;
	@FXML
	DatePicker dpHD_NgayLap;
	@FXML
	DatePicker dpPT_NgayLap;

	@FXML
	Spinner<Integer> snTC_SoDem;

	@FXML
	Button btnPhong_SuaPhong;
	@FXML
	Button btnPhong_DoiPhong;
	@FXML
	Button btnPhong_XoaPhong;

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
	TableColumn<NhanVienDTO, String> tcNV_TaiKhoan;
	@FXML
	TableColumn<NhanVienDTO, String> tcNV_ChucVu;

	@FXML
	Label lbTC_DonGia;

	@FXML
	Label lbPhong_MaPhong;
	@FXML
	Label lbPhong_TinhTrang;
	@FXML
	Label lbPhong_KhachToiDa;
	@FXML
	Label lbPhong_LoaiPhong;
	@FXML
	Label lbPhong_DonGia;
	@FXML
	Label lbPhong_GhiChu;

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
	Label lbNV_MaNhanVien;
	@FXML
	Label lbNV_TenNhanVien;
	@FXML
	Label lbNV_CMND;
	@FXML
	Label lbNV_DiaChi;
	@FXML
	Label lbNV_Email;
	@FXML
	Label lbNV_DienThoai;
	@FXML
	Label lbNV_ChucVu;

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
		initComboboxes();
		initSpinners();
		initDatePickers();
		loadTables();
		loadComboboxes();
		loadNhanVienByUsername("nhidh99");
	}
<<<<<<< Updated upstream
<<<<<<< Updated upstream
	
=======

>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes
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
		initTablePhong();
		initTableNhanVien();
		initTableLoaiPhong();
		initTableNhaCungCap();
		initTableLoaiDichVu();
	}

	private void initSpinners() {
		snTC_SoDem.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1));
	}

	private void initComboboxes() {
		cbbTC_LoaiPhong.valueProperty().addListener(new ChangeListener<LoaiPhongDTO>() {
			@Override
			public void changed(ObservableValue<? extends LoaiPhongDTO> loaiPhong, LoaiPhongDTO loaiPhongCu,
					LoaiPhongDTO loaiPhongMoi) {
				if (loaiPhongMoi == null) {
					lbTC_DonGia.setText("Theo loại phòng");
				} else
					lbTC_DonGia.setText(MoneyFormatHelper.format(loaiPhongMoi.getDonGia(), "VND"));
			}
		});
	}

	private void initDatePickers() {
		dpTC_NgayNhan.setConverter(DateFormatHelper.getDatePickerFormatter("dd/MM/yyyy"));
		dpHD_NgayLap.setConverter(DateFormatHelper.getDatePickerFormatter("dd/MM/yyyy"));
		dpPT_NgayLap.setConverter(DateFormatHelper.getDatePickerFormatter("dd/MM/yyyy"));
		dpTC_NgayNhan.setValue(LocalDate.now());
		dpHD_NgayLap.setValue(LocalDate.now());
		dpPT_NgayLap.setValue(LocalDate.now());
	}

	private void loadComboboxes() {
		try {
			ObservableList<LoaiPhongDTO> dsLoaiPhong = FXCollections.observableArrayList();
			dsLoaiPhong.add(null);
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

			cbbTC_LoaiPhong.setButtonCell(cellFactory.call(null));
			cbbTC_LoaiPhong.setCellFactory(cellFactory);
			cbbTC_LoaiPhong.setItems(dsLoaiPhong);
			cbbTC_LoaiPhong.getSelectionModel().selectFirst();
		} catch (SQLException e) {
			// do nothing :)
		}
	}

	private void loadTables() {
		loadTablePhong();
		loadTableNhanVien();
		loadTableLoaiPhong();
		loadTableNhaCungCap();
		loadTableLoaiDichVu();
		loadTableThamSo();
	}

	private void initTablePhong() {
		tpPhong.setHgap(2);
		tpPhong.setVgap(20);
	}

	private void initTableNhanVien() {
		tcNV_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvNhanVien.getItems().indexOf(column.getValue()) + 1));
		tcNV_MaNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
		tcNV_TenNhanVien.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
		tcNV_CMND.setCellValueFactory(new PropertyValueFactory<>("CMND"));
		tcNV_DiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
		tcNV_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcNV_TaiKhoan.setCellValueFactory(new PropertyValueFactory<>("taiKhoan"));
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

	public void loadTablePhong() {
		class HandleRoomClicked {
			public void handleChiTietPhong(PhongDTO phong) {
				showChiTietPhong(phong);
				updateControlsByTinhTrang(phong.getTinhTrang().getTenTinhTrang());
			}

			private void showChiTietPhong(PhongDTO phong) {
				lbPhong_MaPhong.setText(phong.getMaPhong());
				lbPhong_TinhTrang.setText(phong.getTinhTrang().getTenTinhTrang());
				lbPhong_KhachToiDa.setText(phong.getLoaiPhong().getSoKhachToiDa().toString());
				lbPhong_LoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
				lbPhong_GhiChu.setText(phong.getGhiChu());
				lbPhong_DonGia.setText(MoneyFormatHelper.format(phong.getLoaiPhong().getDonGia(), "VND"));
			}

			private void updateControlsByTinhTrang(String tinhTrangPhong) {
				try {
					if (tinhTrangPhong.equals("Thuê")) {
						tpPhong_ChiTietThue.setVisible(true);
						btnPhong_DoiPhong.setDisable(false);
						btnPhong_SuaPhong.setDisable(true);
						btnPhong_XoaPhong.setDisable(true);
					} else {
						tpPhong_ChiTietThue.setVisible(false);
						btnPhong_DoiPhong.setDisable(true);
						btnPhong_SuaPhong.setDisable(false);
						btnPhong_XoaPhong.setDisable(false);
					}
				} catch (Exception ex) {
					// do nothing :)
				}
			}
		}

		try {
			List<PhongDTO> dsPhong = PhongBUS.getDSPhong();
			ListRoomDetailPane listPanes = new ListRoomDetailPane(dsPhong);
			HandleRoomClicked handler = new HandleRoomClicked();

			for (RoomDetailPane pane : listPanes.getPanes()) {
				pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						PhongDTO phong = pane.getPhong();
						handler.handleChiTietPhong(phong);
					}
				});
				tpPhong.getChildren().add(pane);
			}
			PhongDTO phong = listPanes.getPanes().get(0).getPhong();
			handler.handleChiTietPhong(phong);
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách phòng khách sạn!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
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
			alert.setHeaderText("Không thể tải danh sách nhân viên!");
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

	public void loadNhanVienByUsername(String username) {
		try {
			NhanVienDTO nhanVien = NhanVienBUS.getNhanVienByUsername(username);
			lbNV_MaNhanVien.setText(nhanVien.getMaNhanVien().toString());
			lbNV_TenNhanVien.setText(nhanVien.getTenNhanVien());
			lbNV_CMND.setText(nhanVien.getCMND());
			lbNV_DiaChi.setText(nhanVien.getDiaChi());
			lbNV_DienThoai.setText(nhanVien.getSoDienThoai());
			lbNV_Email.setText(nhanVien.getEmail());
			lbNV_ChucVu.setText(nhanVien.getChucVu());
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải thông tin nhân viên!");
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
<<<<<<< Updated upstream
=======
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
>>>>>>> Stashed changes
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

	// Thêm tài khoản
	public void handleThemNhanVien(ActionEvent e) {
		String link = "/application/themNhanVien.fxml";
		Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 980, 460);
		popUpStage.getScene().setUserData(this);
		popUpStage.showAndWait();
	}

	public void handleSuaNhanVien(ActionEvent e) {
		try {
			NhanVienDTO nhanVien = tvNhanVien.getSelectionModel().getSelectedItem();
			if (nhanVien == null)
				throw new NullPointerException();

			if (nhanVien.getMaNhanVien().toString().equals(lbNV_MaNhanVien.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể sửa thông tin của mình tại đây!");
				alert.setContentText("Chỉ có thể sửa thông tin của mình tại thông tin nhân viên");
				alert.showAndWait();
			} else {
				String link = "/application/suaNhanVien.fxml";
				Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 630, 630);
				FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
				SuaNhanVienController controller = loader.getController();
				controller.initialize(nhanVien);
				popUpStage.getScene().setUserData(this);
				popUpStage.showAndWait();
			}
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa nhân viên!");
			alert.setContentText("Vui lòng chọn nhân viên cần sửa!");
			alert.showAndWait();
		}
	}

	public void handleTuyChinhNhanVien(ActionEvent e) {
		try {
			NhanVienDTO nhanVien = NhanVienBUS.getNhanVienById(Integer.parseInt(lbNV_MaNhanVien.getText()));
			String link = "/application/tuyChinhNhanVien.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 980, 460);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			TuyChinhNhanVienController controller = loader.getController();
			controller.initialize(nhanVien);
			popUpStage.getScene().setUserData(this);
			popUpStage.showAndWait();
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải thông tin nhân viên!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleXoaNhanVien(ActionEvent e) {
		try {
			NhanVienDTO nhanVien = tvNhanVien.getSelectionModel().getSelectedItem();
			if (nhanVien == null)
				throw new NullPointerException();

			if (nhanVien.getMaNhanVien().toString().equals(lbNV_MaNhanVien.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể xoá tài khoản của mình!");
				alert.showAndWait();
				return;
			}

			if (confirmDialog(String.format("Xác nhận xoá %s %s?", nhanVien.getChucVu(), nhanVien.getTenNhanVien()))) {
				if (NhanVienBUS.deleteNhanVien(nhanVien.getMaNhanVien())) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xóa nhân viên thành công!");
					alert.setContentText(String.format("Đã xoá thành công %s %s.", nhanVien.getChucVu(), nhanVien.getTenNhanVien()));
					alert.showAndWait();
					loadTableNhanVien();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Không thể xóa nhân viên!");
					alert.showAndWait();
				}
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xóa nhân viên!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xoá nhân viên!");
			alert.setContentText("Vui lòng chọn nhân viên cần xoá!");
			alert.showAndWait();
		}
	}
}