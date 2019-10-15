package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import BUS.*;
import DTO.*;
import controller.dichvu.DichVuController;
import controller.nhanvien.SuaNhanVienController;
import controller.nhanvien.TuyChinhNhanVienController;
import controller.phieuthue.SuaDichVuController;
import controller.phieuthue.ThemDichVuController;
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
	TilePane tpPhong;

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
	
	//Tab Phòng khách san -> Dịch Vụ
	@FXML 
	TableView<PTP_DichVuDTO> tvPTP_DichVu;
	@FXML
	TableColumn<PTP_DichVuDTO, Integer> tcPTP_DichVu_STT;
	@FXML
	TableColumn<PTP_DichVuDTO, String> tcPTP_DichVu_Ten;
	@FXML
	TableColumn<PTP_DichVuDTO, Integer> tcPTP_DichVu_SoLuong;
	@FXML
	TableColumn<PTP_DichVuDTO, String> tcPTP_DichVu_DonViTinh;
	@FXML
	TableColumn<PTP_DichVuDTO, Integer> tcPTP_DichVu_DonGia;
	@FXML
	TableColumn<PTP_DichVuDTO, Integer> tcPTP_DichVu_ThanhTien;

	private PhongDTO phongDangChon = null;
	
	public PhongDTO getPhongDangChon() {
		return phongDangChon;
	}
	//Thêm table PTP Dịch Vụ:
	private void initTablePTPDichVu() {
		tcPTP_DichVu_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvPTP_DichVu.getItems().indexOf(column.getValue()) + 1));
		tcPTP_DichVu_Ten.setCellValueFactory(new PropertyValueFactory<>("tenDichVu"));
		tcPTP_DichVu_SoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
		tcPTP_DichVu_DonViTinh.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		tcPTP_DichVu_DonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
		tcPTP_DichVu_ThanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
	}
	
	private class RoomClickedHandler {
		public void handleChiTietPhong(PhongDTO phong) {
			phongDangChon = phong;
			showChiTietPhong(phong);
			updateControlsByTinhTrang(phong.getTinhTrang().getTenTinhTrang());
			//Thêm từ đây
			
			if(phong.getTinhTrang().getTenTinhTrang().equals("Thuê"))
				try {
					loadTablePTPDichVuByMaPT(PhieuThuePhongBUS.getPTPhongByMaPhong(Integer.parseInt(phong.getMaPhong())).getMaPTPhong());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Mã phiếu thuê không tồn tại");
					alert.setContentText("Không tìm được mã phiếu thuê phòng!");
					alert.showAndWait();
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Không thể tải danh sách dịch vụ của phiếu thuê phòng!");
					alert.setContentText("Lỗi database!");
					alert.showAndWait();
					e.printStackTrace();
				}
			
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

	private RoomClickedHandler roomClickedHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roomClickedHandler = new RoomClickedHandler();
		initTables();
		initComboboxes();
		initSpinners();
		initDatePickers();
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

	private void initTables() {
		initTablePhong();
		initTableDichVu();
		initTableNhanVien();
		initTableLoaiPhong();
		initTableNhaCungCap();
		initTableLoaiDichVu();
		initTablePTPDichVu();
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
				} else {
					lbTC_DonGia.setText(MoneyFormatHelper.format(loaiPhongMoi.getDonGiaValue(), "VND"));
				}
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

	private void initTablePhong() {
		tpPhong.setHgap(2);
		tpPhong.setVgap(20);
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

	public void loadTablePhong() {
		try {
			List<PhongDTO> dsPhong = PhongBUS.getDSPhong();
			ListRoomDetailPane listPanes = new ListRoomDetailPane(dsPhong);
			for (RoomDetailPane pane : listPanes.getPanes()) {
				pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						PhongDTO phong = pane.getPhong();
						roomClickedHandler.handleChiTietPhong(phong);
					}
				});
				tpPhong.getChildren().add(pane);
			}
			PhongDTO phong = listPanes.getPanes().get(0).getPhong();
			roomClickedHandler.handleChiTietPhong(phong);
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách phòng khách sạn!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
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
			SQLException.printStackTrace();
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
			alert.setHeaderText(String.format(
					"Chi tiết nhà cung cấp [%s]:\n"
					+ "- Nhà cung cấp: %s.\n"
					+ "- Số điện thoại: %s.", 
					dichVu.getTenDichVu(), 
					nhaCungCap.getTenNhaCungCap(), 
					nhaCungCap.getSoDienThoai())); 
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

	// Phiếu thuê - Dịch Vụ:
	public void handleThemDichVu_PTP() throws NumberFormatException, SQLException {
		String link = "/application/PTP_ThemDichVu.fxml";
		Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 980, 460);
		FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
		ThemDichVuController controller = loader.getController();
		controller.setMaPTPhong(PhieuThuePhongBUS.getPTPhongByMaPhong(Integer.parseInt(phongDangChon.getMaPhong())).getMaPTPhong());
		popUpStage.getScene().setUserData(this);
		popUpStage.showAndWait();
	}
	
	public void handleXoaDichVu_PTP() {
		try {
			PTP_DichVuDTO ptp_dichVu = tvPTP_DichVu.getSelectionModel().getSelectedItem();
			if (confirmDialog("Xác nhận xoá dịch vụ " + ptp_dichVu.getTenDichVu() + "?")) {
				if (PTP_DichVuBUS.deletePTP_DichVu(ptp_dichVu.getMaPTPDichVu())) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xóa loại dịch vụ thành công!");
					alert.setContentText("Đã xóa dịch vụ " + ptp_dichVu.getTenDichVu() + "!");
					alert.showAndWait();
					loadTablePTPDichVuByMaPT(PhieuThuePhongBUS.getPTPhongByMaPhong(Integer.parseInt(phongDangChon.getMaPhong())).getMaPTPhong());
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
	
	public void handleSuaDichVu_PTP() {
		try {
			PTP_DichVuDTO ptp_dichVu = tvPTP_DichVu.getSelectionModel().getSelectedItem();
			String link = "/application/PTP_SuaDichVu.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 980, 460);
			popUpStage.getScene().setUserData(this);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			SuaDichVuController controller = loader.getController();
			controller.initialize(ptp_dichVu);
			popUpStage.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa dịch vụ!");
			alert.setContentText("Vui lòng chọn dịch vụ cần sửa!");
			alert.showAndWait();
		}
	}
	
	public void loadTablePTPDichVuByMaPT(Integer maPhieuThue) {
		try {
			ObservableList<PTP_DichVuDTO> dsDichVu = FXCollections.observableArrayList();
			for (PTP_DichVuDTO dv : PTP_DichVuBUS.getDSDichVuByMaPT(maPhieuThue)) {
				dsDichVu.add(dv);
			}
			tvPTP_DichVu.setItems(dsDichVu);
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách loại dịch vụ!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	
	
}