package controller;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import BUS.*;
import DTO.*;
import controller.dichvu.DichVuController;
import controller.nhanvien.SuaNhanVienController;
import controller.nhanvien.TuyChinhNhanVienController;
import custom.control.ListRoomDetailPane;
import custom.control.RoomDetailPane;
import helper.DateFormatHelper;
import helper.MoneyFormatHelper;
import helper.PopUpStageHelper;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainController implements Initializable {
	@FXML
	Tab tabPhong;
	@FXML
	Tab tabTraCuu;
	@FXML
	Tab tabPhieuThue;
	@FXML
	Tab tabThietLap;
	@FXML
	Tab tabDichVu;
	@FXML
	Tab tabThongKe;
	@FXML
	Tab tabNhanVien;
	@FXML
	Tab tabNV_NhanVien;
	@FXML
	Tab tabNV_DanhMuc;

	@FXML
	TabPane tpNhanVien;
	@FXML
	TabPane tpMain;
	@FXML
	TabPane tpPhong_ChiTiet;

	@FXML
	BorderPane bpTC_ThongTinPhong;

	@FXML
	TilePane tpPhong;
	@FXML
	TilePane tpTC_Phong;

	@FXML
	ComboBox<LoaiPhongDTO> cbbTC_LoaiPhong;

	@FXML
	CheckBox cbTC_DatCoc;

	@FXML
	DatePicker dpTC_NgayNhan;
	@FXML
	DatePicker dpHD_NgayLap;
	@FXML
	DatePicker dpPT_NgayLap;

	@FXML
	Spinner<Integer> snTC_SoDem;
	@FXML
	Spinner<Integer> snTC_GioNhan;
	@FXML
	Spinner<Integer> snTC_GioTra;

	@FXML
	Button btnPhong_ThemPhong;
	@FXML
	Button btnPhong_XoaPhong;
	@FXML
	Button btnPhong_SuaPhong;
	@FXML
	Button btnPhong_DoiPhong;
	@FXML
	Button btnDV_ThemDichVu;
	@FXML
	Button btnDV_XoaDichVu;
	@FXML
	Button btnDV_SuaDichVu;

	@FXML
	Label lbTC_DonGia;
	@FXML
	Label lbTC_NgayTra;
	@FXML
	Label lbTC_MaPhong;
	@FXML
	Label lbTC_TinhTrang;
	@FXML
	Label lbTC_TienCoc;
	@FXML
	Label lbTC_GhiChu;

	@FXML
	Label lbPT_TenNhanVien;
	@FXML
	Label lbPT_SoLuongPhong;

	@FXML
	Label lbHD_TenNhanVien;

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
	TextField tfPT_KhachThue;
	@FXML
	TextField tfPT_CMND;
	@FXML
	TextField tfPT_DienThoai;
	@FXML
	TextField tfPT_Email;
	@FXML
	TextField tfPT_GhiChu;

	@FXML
	TableView<PTPhongDTO> tvPhieuThue;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcPT_STT;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcPT_MaPTP;
	@FXML
	TableColumn<PTPhongDTO, String> tcPT_MaPhong;
	@FXML
	TableColumn<PTPhongDTO, String> tcPT_LoaiPhong;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcPT_SoKhachToiDa;
	@FXML
	TableColumn<PTPhongDTO, String> tcPT_DonGiaThue;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcPT_DonGiaThueValue;
	@FXML
	TableColumn<PTPhongDTO, String> tcPT_NgayNhan;
	@FXML
	TableColumn<PTPhongDTO, Timestamp> tcPT_NgayNhanValue;
	@FXML
	TableColumn<PTPhongDTO, String> tcPT_NgayTra;
	@FXML
	TableColumn<PTPhongDTO, Timestamp> tcPT_NgayTraValue;
	@FXML
	TableColumn<PTPhongDTO, String> tcPT_TienCoc;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcPT_TienCocValue;

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
	TableView<DichVuDTO> tvDichVu;
	@FXML
	TableColumn<DichVuDTO, Integer> tcDV_STT;
	@FXML
	TableColumn<DichVuDTO, Integer> tcDV_MaDichVu;
	@FXML
	TableColumn<DichVuDTO, Integer> tcDV_MaLoaiDichVu;
	@FXML
	TableColumn<DichVuDTO, Integer> tcDV_MaNhaCungCap;
	@FXML
	TableColumn<DichVuDTO, String> tcDV_TenDichVu;
	@FXML
	TableColumn<DichVuDTO, String> tcDV_LoaiDichVu;
	@FXML
	TableColumn<DichVuDTO, String> tcDV_DonViTinh;
	@FXML
	TableColumn<DichVuDTO, Integer> tcDV_SoLuongTon;
	@FXML
	TableColumn<DichVuDTO, String> tcDV_DonGia;

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
	TableColumn<LoaiPhongDTO, String> tcLP_DonGia;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTables();
		initComboboxes();
		initDatePickers();
		initSpinners();
		initCheckboxes();
		initLabels();
		loadTables();
		loadComboboxes();
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

	private void reCalculateTienCoc() {
		if (cbTC_DatCoc.isSelected()) {
			Integer phanTramCoc = Integer
					.parseInt(lbTS_TiLeTienCoc.getText().substring(0, lbTS_TiLeTienCoc.getText().length() - 1));
			Integer donGiaPhong = cbbTC_LoaiPhong.getSelectionModel().getSelectedItem().getDonGiaValue();
			Integer soDem = snTC_SoDem.getValue();
			Integer tienCoc = donGiaPhong * soDem * phanTramCoc / 100;
			lbTC_TienCoc.setText(MoneyFormatHelper.format(tienCoc, "VND"));
		}
	}

	private void initTables() {
		initTablePhong();
		initTablePhieuThue();
		initTableDichVu();
		initTableNhanVien();
		initTableLoaiPhong();
		initTableNhaCungCap();
		initTableLoaiDichVu();
	}

	private void loadTables() {
		loadTablePhong();
		loadTableDichVu();
		loadTableNhanVien();
		loadTableLoaiPhong();
		loadTableNhaCungCap();
		loadTableLoaiDichVu();
		loadTableThamSo();
	}

	private void initLabels() {
		try {
			NhanVienDTO nhanVien = NhanVienBUS.getNhanVienById(Integer.parseInt(lbNV_MaNhanVien.getText()));
			lbPT_TenNhanVien.setText(nhanVien.getTenNhanVien());
			lbHD_TenNhanVien.setText(nhanVien.getTenNhanVien());
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải thông tin label!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	private void initSpinners() {
		snTC_SoDem.valueProperty().addListener((obs, soDemCu, soDemMoi) -> {
			LocalDate ngayTra = dpTC_NgayNhan.getValue().plusDays(soDemMoi);
			lbTC_NgayTra.setText(DateFormatHelper.toString(ngayTra));
			tpTC_Phong.getChildren().clear();
			bpTC_ThongTinPhong.setVisible(false);
			reCalculateTienCoc();
		});

		snTC_GioNhan.valueProperty().addListener((obs, soDemCu, soDemMoi) -> {
			tpTC_Phong.getChildren().clear();
			bpTC_ThongTinPhong.setVisible(false);
		});

		snTC_GioTra.valueProperty().addListener((obs, soDemCu, soDemMoi) -> {
			tpTC_Phong.getChildren().clear();
			bpTC_ThongTinPhong.setVisible(false);
		});

		snTC_SoDem.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1));
		snTC_GioNhan.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8));
		snTC_GioTra.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12));
	}

	private void initComboboxes() {
		cbbTC_LoaiPhong.valueProperty().addListener((obs, loaiPhongCu, loaiPhongMoi) -> {
			lbTC_DonGia.setText(MoneyFormatHelper.format(loaiPhongMoi.getDonGiaValue(), "VND"));
			bpTC_ThongTinPhong.setVisible(false);
			reCalculateTienCoc();
		});
	}

	private void initDatePickers() {
		dpTC_NgayNhan.setConverter(DateFormatHelper.getDatePickerFormatter());
		dpHD_NgayLap.setConverter(DateFormatHelper.getDatePickerFormatter());
		dpPT_NgayLap.setConverter(DateFormatHelper.getDatePickerFormatter());
		dpTC_NgayNhan.setValue(LocalDate.now());
		dpHD_NgayLap.setValue(LocalDate.now());
		dpPT_NgayLap.setValue(LocalDate.now());
		dpTC_NgayNhan.valueProperty().addListener((obs, oldValue, newValue) -> {
			LocalDate ngayTra = newValue.plusDays(snTC_SoDem.getValue());
			lbTC_NgayTra.setText(DateFormatHelper.toString(ngayTra));
			tpTC_Phong.getChildren().clear();
			bpTC_ThongTinPhong.setVisible(false);
		});

	}

	private void initCheckboxes() {
		cbTC_DatCoc.selectedProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == true) {
				reCalculateTienCoc();
			} else {
				lbTC_TienCoc.setText("0 VND");
			}
		});
	}

	private void initTablePhong() {
		tpPhong.setHgap(2);
		tpPhong.setVgap(20);
	}

	private void initTablePhieuThue() {
		tcPT_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvPhieuThue.getItems().indexOf(column.getValue()) + 1));
		tcPT_MaPTP.setCellValueFactory(new PropertyValueFactory<>("MaPTPhong"));
		tcPT_MaPhong.setCellValueFactory(new PropertyValueFactory<>("MaPhong"));
		tcPT_LoaiPhong.setCellValueFactory(new PropertyValueFactory<>("LoaiPhongThue"));
		tcPT_SoKhachToiDa.setCellValueFactory(new PropertyValueFactory<>("SoKhachToiDa"));
		tcPT_DonGiaThue.setCellValueFactory(new PropertyValueFactory<>("DonGiaThue"));
		tcPT_DonGiaThueValue.setCellValueFactory(new PropertyValueFactory<>("DonGiaThueValue"));
		tcPT_NgayNhan.setCellValueFactory(new PropertyValueFactory<>("NgayNhan"));
		tcPT_NgayNhanValue.setCellValueFactory(new PropertyValueFactory<>("NgayNhanValue"));
		tcPT_NgayTra.setCellValueFactory(new PropertyValueFactory<>("NgayTra"));
		tcPT_NgayTraValue.setCellValueFactory(new PropertyValueFactory<>("NgayTraValue"));
		tcPT_TienCoc.setCellValueFactory(new PropertyValueFactory<>("TienCoc"));
		tcPT_TienCocValue.setCellValueFactory(new PropertyValueFactory<>("TienCocValue"));
	}

	private void initTableDichVu() {
		tcDV_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvDichVu.getItems().indexOf(column.getValue()) + 1));
		tcDV_MaDichVu.setCellValueFactory(new PropertyValueFactory<>("MaDichVu"));
		tcDV_MaLoaiDichVu.setCellValueFactory(new PropertyValueFactory<>("maLoaiDichVu"));
		tcDV_MaNhaCungCap.setCellValueFactory(new PropertyValueFactory<>("maNhaCungCap"));
		tcDV_TenDichVu.setCellValueFactory(new PropertyValueFactory<>("tenDichVu"));
		tcDV_LoaiDichVu.setCellValueFactory(new PropertyValueFactory<>("tenLoaiDichVu"));
		tcDV_DonViTinh.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		tcDV_SoLuongTon.setCellValueFactory(new PropertyValueFactory<>("soLuongTon"));
		tcDV_DonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
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

	private void loadComboboxes() {
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
								this.setText(null);
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

	public void loadTablePhong() {
		class RoomClickedHandler {
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
				lbPhong_DonGia.setText(MoneyFormatHelper.format(phong.getLoaiPhong().getDonGiaValue(), "VND"));
			}

			private void updateControlsByTinhTrang(String tinhTrangPhong) {
				try {
					if (tinhTrangPhong.equals("Thuê")) {
						tpPhong_ChiTiet.setVisible(true);
						btnPhong_DoiPhong.setDisable(false);
						btnPhong_SuaPhong.setDisable(true);
						btnPhong_XoaPhong.setDisable(true);
					} else {
						tpPhong_ChiTiet.setVisible(false);
						btnPhong_DoiPhong.setDisable(true);
						btnPhong_SuaPhong.setDisable(false);
						btnPhong_XoaPhong.setDisable(false);
					}
				} catch (Exception ex) {
					// do nothing :)
				}
			}
		}

		RoomClickedHandler handler = new RoomClickedHandler();
		try {
			tpPhong.getChildren().clear();
			List<PhongDTO> dsPhong = PhongBUS.getDSPhong();
			ListRoomDetailPane listPanes = new ListRoomDetailPane(dsPhong);
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
		} catch (NullPointerException NullPointerException) {
			// do nothing :P
		}
	}

	public void loadTableDichVu() {
		try {
			ObservableList<DichVuDTO> dsDichVu = FXCollections.observableArrayList();
			for (DichVuDTO dv : DichVuBUS.getDSDichVu()) {
				dsDichVu.add(dv);
			}
			tvDichVu.setItems(dsDichVu);
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách dịch vụ!");
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
			loadPhanQuyen(nhanVien.getChucVu());
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải thông tin nhân viên!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadPhanQuyen(String chucVu) {
		switch (chucVu) {
		case "Lễ tân": {
			tpNhanVien.getTabs().remove(tabNV_DanhMuc);
			tpMain.getTabs().removeAll(tabThietLap, tabThongKe);
			VBox vb = (VBox) btnPhong_ThemPhong.getParent();
			vb.getChildren().remove(btnPhong_ThemPhong);
			HBox hb = (HBox) btnPhong_XoaPhong.getParent();
			hb.getChildren().remove(btnPhong_XoaPhong);
			hb = (HBox) btnDV_ThemDichVu.getParent();
			hb.getChildren().removeAll(btnDV_ThemDichVu, btnDV_XoaDichVu);
			btnDV_SuaDichVu.setText("⚙ NHẬP HÀNG");
			break;
		}
		case "Kế toán": {
			tpNhanVien.getTabs().remove(tabNV_DanhMuc);
			tpMain.getTabs().removeAll(tabThietLap, tabPhong, tabPhieuThue, tabTraCuu);
			HBox hb = (HBox) btnDV_ThemDichVu.getParent();
			hb.getChildren().removeAll(btnDV_ThemDichVu, btnDV_XoaDichVu);
			btnDV_SuaDichVu.setText("⚙ NHẬP HÀNG");
			break;
		}
		default:
			break;
		}
	}

	public void handleTraCuuPhong() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateFormatHelper.getDate(dpTC_NgayNhan.getValue()));
		cal.set(Calendar.HOUR, snTC_GioNhan.getValue());
		Timestamp ngayNhan = new Timestamp(cal.getTimeInMillis());
		cal.setTime(DateFormatHelper.getDate(lbTC_NgayTra.getText()));
		cal.set(Calendar.HOUR, snTC_GioTra.getValue());
		Timestamp ngayTra = new Timestamp(cal.getTimeInMillis());

		try {
			tpTC_Phong.getChildren().clear();
			Integer maLoaiPhong = cbbTC_LoaiPhong.getSelectionModel().getSelectedItem().getMaLoaiPhong();
			List<PhongDTO> dsPhong = PhongBUS.getDSPhongCoTheThue(ngayNhan, ngayTra, maLoaiPhong);
			ListRoomDetailPane listPanes = new ListRoomDetailPane(dsPhong);

			for (RoomDetailPane pane : listPanes.getPanes()) {
				pane.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
					PhongDTO phong = pane.getPhong();
					lbTC_MaPhong.setText(phong.getMaPhong());
					lbTC_TinhTrang.setText(phong.getTinhTrang().getTenTinhTrang());
					lbTC_GhiChu.setText(phong.getGhiChu());
				});
				tpTC_Phong.getChildren().add(pane);
			}

			if (dsPhong.size() > 0) {
				bpTC_ThongTinPhong.setVisible(true);
				PhongDTO phong = listPanes.getPanes().get(0).getPhong();
				lbTC_MaPhong.setText(phong.getMaPhong());
				lbTC_TinhTrang.setText(phong.getTinhTrang().getTenTinhTrang());
				lbTC_GhiChu.setText(phong.getGhiChu());
			} else {
				bpTC_ThongTinPhong.setVisible(false);
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách phòng khách sạn!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			// do nothing :P
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
					alert.setContentText(
							String.format("Đã xoá thành công %s %s.", nhanVien.getChucVu(), nhanVien.getTenNhanVien()));
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

	public void handleThemDichVu(ActionEvent e) {
		String link = "/application/popupDichVu.fxml";
		Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 500, 550);
		popUpStage.getScene().setUserData(this);
		popUpStage.showAndWait();
	}

	public void handleXoaDichVu(ActionEvent e) {
		try {
			DichVuDTO dichVu = tvDichVu.getSelectionModel().getSelectedItem();
			if (confirmDialog("Xác nhận xoá dịch vụ " + dichVu.getTenDichVu() + "?")) {
				if (DichVuBUS.deleteDichVu(dichVu.getMaDichVu())) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xóa loại dịch vụ thành công!");
					alert.setContentText("Đã xóa dịch vụ " + dichVu.getTenDichVu() + "!");
					alert.showAndWait();
					loadTableDichVu();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Không thể xóa dịch vụ!");
					alert.showAndWait();
				}
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xóa dịch vụ!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xoá dịch vụ!");
			alert.setContentText("Vui lòng chọn dịch vụ cần xoá!");
			alert.showAndWait();
		}
	}

	public void handleSuaDichVu(ActionEvent e) {
		try {
			DichVuDTO dichVu = tvDichVu.getSelectionModel().getSelectedItem();
			String link = "/application/popupDichVu.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 500, 550);
			popUpStage.getScene().setUserData(this);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			DichVuController controller = loader.getController();
			controller.initialize(dichVu);
			popUpStage.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa dịch vụ!");
			alert.setContentText("Vui lòng chọn dịch vụ cần sửa!");
			alert.showAndWait();
		}
	}

	public void handleXemChiTietDichVu(ActionEvent e) {
		try {
			DichVuDTO dichVu = tvDichVu.getSelectionModel().getSelectedItem();
			NhaCungCapDTO nhaCungCap = NhaCungCapBUS.getNhaCungCapById(dichVu.getMaNhaCungCap());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Chi tiết dịch vụ");
			alert.setHeaderText(
					String.format("Chi tiết nhà cung cấp [%s]:\n" + "- Nhà cung cấp: %s.\n" + "- Số điện thoại: %s.",
							dichVu.getTenDichVu(), nhaCungCap.getTenNhaCungCap(), nhaCungCap.getSoDienThoai()));
			alert.showAndWait();
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xem chi tiết dịch vụ!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xem chi tiết dịch vụ!");
			alert.setContentText("Vui lòng chọn dịch vụ cần xem!");
			alert.showAndWait();
		}
	}

	public void handleLapPhieuThue(ActionEvent e) {
		if (confirmDialog("Bạn đã chắc chắn các thông tin để lập phiếu?")) {
			PhieuThueDTO phieuThue = new PhieuThueDTO(Integer.parseInt(lbNV_MaNhanVien.getText()),
					DateFormatHelper.getDate(dpPT_NgayLap.getValue()), tfPT_KhachThue.getText(), tfPT_CMND.getText(),
					tfPT_DienThoai.getText(), tfPT_Email.getText(), tfPT_GhiChu.getText());

			try {
				if (PhieuThueBUS.insertPhieuThue(phieuThue)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Lập phiếu thuê thành công!");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Lập phiếu thuê thất bại!");
					alert.showAndWait();
				}
			} catch (SQLException SQLException) {
				SQLException.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Lập phiếu thuê thất bại!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();
			}
		}
	}

	public void handleThemPhongVaoPhieuThue(ActionEvent e) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(DateFormatHelper.getDate(dpTC_NgayNhan.getValue()));
			cal.set(Calendar.HOUR, snTC_GioNhan.getValue());
			Timestamp ngayNhan = new Timestamp(cal.getTimeInMillis());
			cal.setTime(DateFormatHelper.getDate(lbTC_NgayTra.getText()));
			cal.set(Calendar.HOUR, snTC_GioTra.getValue());
			Timestamp ngayTra = new Timestamp(cal.getTimeInMillis());
			PhongDTO phong = PhongBUS.getPhongById(lbTC_MaPhong.getText());
			Integer tienCoc = MoneyFormatHelper.fromString(lbTC_TienCoc.getText());
			PTPhongDTO ptPhong = new PTPhongDTO(phong, ngayNhan, ngayTra, tienCoc);

			ObservableList<PTPhongDTO> dsPTPhong = FXCollections.observableArrayList();

			if (PTPhongBUS.insertPTPhong(ptPhong)) {
				for (PTPhongDTO ptp : PTPhongBUS.getDSPhongDangKy()) {
					dsPTPhong.add(ptp);
				}
				tvPhieuThue.setItems(dsPTPhong);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Thêm phòng " + ptPhong.getMaPhong() + " vào phiếu thuê thành công!");
				alert.showAndWait();
				handleTraCuuPhong();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Thêm phòng thất bại!");
				alert.showAndWait();
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Lập phiếu thuê thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}
}