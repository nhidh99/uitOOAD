package controller;

import java.net.URL;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import BUS.*;
import DTO.*;
import controller.nhanvien.SuaNhanVienController;
import controller.nhanvien.TuyChinhNhanVienController;
import controller.ptpdv.SuaPtpDichVuController;
import controller.ptpdv.ThemPtpDichVuController;
import custom.control.ListRoomDetailPane;
import custom.control.RoomDetailPane;
import helper.ConfirmDialogHelper;
import helper.DateFormatHelper;
import helper.MoneyFormatHelper;
import helper.PDFCreateHelper;
import helper.PopUpStageHelper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
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
	Tab tabPhong_PhieuDangKy;
	@FXML
	Tab tabPhong_ThongTinThue;
	@FXML
	Tab tabPhong_KhachThue;
	@FXML
	Tab tabPhong_DichVu;
	@FXML
	Tab tabPhong_PTCK;

	@FXML
	TabPane tpNhanVien;
	@FXML
	TabPane tpMain;
	@FXML
	TabPane tpPhong_ChiTiet;

	@FXML
	BorderPane bpTC_ThongTinPhong;

	@FXML
	BorderPane bpTK_DoanhThu;
	@FXML
	BorderPane bpTK_LoaiPhong;
	@FXML
	BorderPane bpTK_LuongKhach;
	@FXML
	BorderPane bpTK_LoaiDichVu;

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
	Spinner<Integer> snTK_DoanhThu;
	@FXML
	Spinner<Integer> snTK_LuongKhach;
	@FXML
	Spinner<Integer> snTK_LoaiDichVuThang;
	@FXML
	Spinner<Integer> snTK_LoaiPhongThang;
	@FXML
	Spinner<Integer> snTK_LoaiDichVuNam;
	@FXML
	Spinner<Integer> snTK_LoaiPhongNam;

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
	Button btnHD_ThanhToan;
	@FXML
	Button btnHD_ChotThongTin;

	@FXML
	Label lbTC_DonGia;
	@FXML
	Label lbTC_NgayTra;
	@FXML
	Label lbTC_MaPhong;
	@FXML
	Label lbTC_KhachToiDa;
	@FXML
	Label lbTC_TienCoc;
	@FXML
	Label lbTC_GhiChu;

	@FXML
	Label lbPT_TenNhanVien;
	@FXML
	Label lbPT_SoLuongPhong;
	@FXML
	Label lbPT_TongCoc;

	@FXML
	Label lbPhong_MaPhong;
	@FXML
	Label lbPhong_KhachThue;
	@FXML
	Label lbPhong_CMND;
	@FXML
	Label lbPhong_DienThoai;
	@FXML
	Label lbPhong_NhanVien;
	@FXML
	Label lbPhong_NgayLap;
	@FXML
	Label lbPhong_NgayNhan;
	@FXML
	Label lbPhong_NgayTra;
	@FXML
	Label lbPhong_DonGiaThue;
	@FXML
	Label lbPhong_TienCoc;
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
	Label lbPhong_MaPTP;
	@FXML
	Label lbPhong_TienDichVu;
	@FXML
	Label lbPhong_TienPTCK;

	@FXML
	Label lbTS_TiLeThueVAT;
	@FXML
	Label lbTS_TiLeTienCoc;

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
	Label lbHD_TenNhanVien;
	@FXML
	Label lbHD_TienPtck;
	@FXML
	Label lbHD_TienPhong;
	@FXML
	Label lbHD_TienCoc;
	@FXML
	Label lbHD_SoLuong;
	@FXML
	Label lbHD_TamTinh;
	@FXML
	Label lbHD_TienThua;

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
	TextField tfHD_TenKhach;
	@FXML
	TextField tfHD_DienThoai;
	@FXML
	TextField tfHD_CMND;
	@FXML
	TextField tfHD_Email;
	@FXML
	TextField tfHD_GhiChu;
	@FXML
	TextField tfHD_TienNhan;

	@FXML
	TableView<KhachDTO> tvKhach;
	@FXML
	TableColumn<KhachDTO, Integer> tcKhach_STT;
	@FXML
	TableColumn<KhachDTO, Integer> tcKhach_MaKhach;
	@FXML
	TableColumn<KhachDTO, String> tcKhach_HoTen;
	@FXML
	TableColumn<KhachDTO, String> tcKhach_SoDienThoai;
	@FXML
	TableColumn<KhachDTO, String> tcKhach_CMND;
	@FXML
	TableColumn<KhachDTO, String> tcKhach_GioiTinh;
	@FXML
	TableColumn<KhachDTO, String> tcKhach_QuocTich;

	@FXML
	TableView<PtpDichVuDTO> tvPtpDichVu;
	@FXML
	TableColumn<PtpDichVuDTO, Integer> tcPtpDV_MaPTPDV;
	@FXML
	TableColumn<PtpDichVuDTO, Integer> tcPtpDV_STT;
	@FXML
	TableColumn<PtpDichVuDTO, String> tcPtpDV_TenDichVu;
	@FXML
	TableColumn<PtpDichVuDTO, Integer> tcPtpDV_SoLuong;
	@FXML
	TableColumn<PtpDichVuDTO, String> tcPtpDV_DonViTinh;
	@FXML
	TableColumn<PtpDichVuDTO, Integer> tcPtpDV_DonGia;
	@FXML
	TableColumn<PtpDichVuDTO, Integer> tcPtpDV_ThanhTien;

	@FXML
	TableView<PtpPtckDTO> tvPtpPTCK;
	@FXML
	TableColumn<PtpPtckDTO, Integer> tcPtpPTCK_STT;
	@FXML
	TableColumn<PtpPtckDTO, Integer> tcPtpPTCK_MaPTCK;
	@FXML
	TableColumn<PtpPtckDTO, String> tcPtpPTCK_NoiDung;
	@FXML
	TableColumn<PtpPtckDTO, String> tcPtpPTCK_DonGia;
	@FXML
	TableColumn<PtpPtckDTO, Integer> tcPtpPTCK_SoLuong;
	@FXML
	TableColumn<PtpPtckDTO, String> tcPtpPTCK_ThanhTien;

	@FXML
	TableView<PhieuThueDTO> tvPhieuThue;
	@FXML
	TableColumn<PhieuThueDTO, Integer> tcPT_STT;
	@FXML
	TableColumn<PhieuThueDTO, Integer> tcPT_MaPhieu;
	@FXML
	TableColumn<PhieuThueDTO, String> tcPT_NgayLap;
	@FXML
	TableColumn<PhieuThueDTO, String> tcPT_KhachThue;
	@FXML
	TableColumn<PhieuThueDTO, String> tcPT_SoDienThoai;
	@FXML
	TableColumn<PhieuThueDTO, String> tcPT_TongTienCoc;
	@FXML
	TableColumn<PhieuThueDTO, String> tcPT_ThanhToanCoc;
	@FXML
	TableColumn<PhieuThueDTO, String> tcPT_GhiChu;

	@FXML
	TableView<PTPhongDTO> tvPTPhong;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcPTP_STT;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcPTP_MaPTP;
	@FXML
	TableColumn<PTPhongDTO, String> tcPTP_MaPhong;
	@FXML
	TableColumn<PTPhongDTO, String> tcPTP_LoaiPhong;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcPTP_SoKhachToiDa;
	@FXML
	TableColumn<PTPhongDTO, String> tcPTP_DonGiaThue;
	@FXML
	TableColumn<PTPhongDTO, String> tcPTP_NgayNhan;
	@FXML
	TableColumn<PTPhongDTO, String> tcPTP_NgayTra;
	@FXML
	TableColumn<PTPhongDTO, String> tcPTP_TienCoc;

	@FXML
	TableView<PTPhongDTO> tvPhongPTP;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcPhongPTP_STT;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcPhongPTP_MaPTP;
	@FXML
	TableColumn<PTPhongDTO, String> tcPhongPTP_KhachThue;
	@FXML
	TableColumn<PTPhongDTO, String> tcPhongPTP_DienThoai;
	@FXML
	TableColumn<PTPhongDTO, String> tcPhongPTP_NgayNhan;
	@FXML
	TableColumn<PTPhongDTO, String> tcPhongPTP_NgayTra;

	@FXML
	TableView<PTPhongDTO> tvHDPhong;
	@FXML
	TableColumn<PTPhongDTO, String> tcHD_SoPhong;
	@FXML
	TableColumn<PTPhongDTO, String> tcHD_MaPTP;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcHD_TienCoc;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcHD_ThanhTienValue;
	@FXML
	TableColumn<PTPhongDTO, String> tcHD_ThanhTien;

	@FXML
	TableView<HoaDonDTO> tvHoaDon;
	@FXML
	TableColumn<HoaDonDTO, Integer> tcHD_STT;
	@FXML
	TableColumn<HoaDonDTO, Integer> tcHD_MaHD;
	@FXML
	TableColumn<HoaDonDTO, String> tcHD_NgayLap;
	@FXML
	TableColumn<HoaDonDTO, String> tcHD_NhanVien;
	@FXML
	TableColumn<HoaDonDTO, String> tcHD_TenKhach;
	@FXML
	TableColumn<HoaDonDTO, String> tcHD_DienThoai;
	@FXML
	TableColumn<HoaDonDTO, String> tcHD_CMND;
	@FXML
	TableColumn<HoaDonDTO, String> tcHD_TriGia;
	@FXML
	TableColumn<HoaDonDTO, String> tcHD_TongCong;

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
	TableColumn<DichVuDTO, String> tcDV_NhaCungCap;
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

	@FXML
	TableView<HDPtckDTO> tvHDPtck;
	@FXML
	TableColumn<HDPtckDTO, Integer> tcHDPtck_NoiDung;
	@FXML
	TableColumn<HDPtckDTO, String> tcHDPtck_TriGia;

	ObservableList<PTPhongDTO> dsHDPhong = FXCollections.observableArrayList();
	ObservableList<HDPtckDTO> dsHDPtck = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTables();
		initTextFields();
		initComboboxes();
		initDatePickers();
		initSpinners();
		initCheckboxes();
		initLabels();
		loadTables();
		loadComboboxes();
	}

	public void initStage() {
		Stage mainStage = (Stage) lbPhong_MaPhong.getScene().getWindow();
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				if (ConfirmDialogHelper.confirm("Xác nhận đăng xuất?")) {
					Stage loginStage = PopUpStageHelper.createPopUpStage("/application/dangNhap.fxml", 500, 300);
					loginStage.show();
				} else
					event.consume();
			}
		});
	}

	private void calculateTamTinh() {
		Integer tiLeVAT = Integer.parseInt(lbTS_TiLeThueVAT.getText().split("%")[0]);
		Integer tamTinh = (MoneyFormatHelper.fromString(lbHD_TienPhong.getText())
				- MoneyFormatHelper.fromString(lbHD_TienCoc.getText())
				+ MoneyFormatHelper.fromString(lbHD_TienPtck.getText())) * (100 + tiLeVAT) / 100;
		lbHD_TamTinh.setText(MoneyFormatHelper.format(tamTinh));
		calculateTienThua();
	}

	private void calculateTienThua() {
		try {
			Integer tienThua = Integer.parseInt(tfHD_TienNhan.getText())
					- MoneyFormatHelper.fromString(lbHD_TamTinh.getText());
			lbHD_TienThua.setText(MoneyFormatHelper.format(tienThua));
		} catch (NumberFormatException ex) {
			lbHD_TienThua.setText(null);
		}
	}

	private void calculateTienCoc() {
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
		initTableTraCuu();
		initTablePhieuThue();
		initTablePtpDichVu();
		initTablePtpPtck();
		initTableKhach();
		initTableHDPhong();
		initTableHDPtck();
		initTablePTPhong();
		initTableHoaDon();
		initTableDichVu();
		initTableNhanVien();
		initTableLoaiPhong();
		initTableNhaCungCap();
		initTableLoaiDichVu();
		initTablePhongPTP();
	}

	private void loadTables() {
		loadTablePhong();
		loadTablePhieuThue();
		loadTablePTPhong();
		loadTableHoaDon();
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

	private void initTextFields() {
		tfHD_TienNhan.textProperty().addListener((obs, oldValue, newValue) -> calculateTienThua());
	}

	private void initSpinners() {
		snTC_SoDem.valueProperty().addListener((obs, soDemCu, soDemMoi) -> {
			LocalDate ngayTra = dpTC_NgayNhan.getValue().plusDays(soDemMoi);
			lbTC_NgayTra.setText(DateFormatHelper.toString(ngayTra));
			tpTC_Phong.getChildren().clear();
			bpTC_ThongTinPhong.setVisible(false);
			calculateTienCoc();
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
		
		int curMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int curYear = Calendar.getInstance().get(Calendar.YEAR);
		snTK_DoanhThu.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, 3000, curYear));
		snTK_LuongKhach.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, 3000, curYear));
		snTK_LoaiPhongThang.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, curMonth));
		snTK_LoaiDichVuThang.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, curMonth));
		snTK_LoaiPhongNam.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, 3000, curYear));
		snTK_LoaiDichVuNam.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, 3000, curYear));
	}

	private void initComboboxes() {
		cbbTC_LoaiPhong.valueProperty().addListener((obs, loaiPhongCu, loaiPhongMoi) -> {
			try {
				lbTC_DonGia.setText(MoneyFormatHelper.format(loaiPhongMoi.getDonGiaValue(), "VND"));
				bpTC_ThongTinPhong.setVisible(false);
				calculateTienCoc();
			} catch (Exception ex) {
				// do nothing
			}
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
				calculateTienCoc();
			} else {
				lbTC_TienCoc.setText("0 VND");
			}
		});
	}

	private void initTablePhong() {
		tpPhong.setHgap(2);
		tpPhong.setVgap(20);
	}

	private void initTableTraCuu() {
		tpTC_Phong.setHgap(2);
		tpTC_Phong.setVgap(20);
	}

	private void initTablePhieuThue() {
		tcPT_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvPhieuThue.getItems().indexOf(column.getValue()) + 1));
		tcPT_MaPhieu.setCellValueFactory(new PropertyValueFactory<>("MaPhieuThue"));
		tcPT_NgayLap.setCellValueFactory(new PropertyValueFactory<>("NgayLap"));
		tcPT_KhachThue.setCellValueFactory(new PropertyValueFactory<>("TenKhachThue"));
		tcPT_SoDienThoai.setCellValueFactory(new PropertyValueFactory<>("SoDienThoai"));
		tcPT_TongTienCoc.setCellValueFactory(new PropertyValueFactory<>("TongTienCoc"));
		tcPT_ThanhToanCoc.setCellValueFactory(new PropertyValueFactory<>("ThanhToanCoc"));
		tcPT_GhiChu.setCellValueFactory(new PropertyValueFactory<>("GhiChu"));
	}

	private void initTablePtpDichVu() {
		tcPtpDV_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvPtpDichVu.getItems().indexOf(column.getValue()) + 1));
		tcPtpDV_MaPTPDV.setCellValueFactory(new PropertyValueFactory<>("maPTPDichVu"));
		tcPtpDV_TenDichVu.setCellValueFactory(new PropertyValueFactory<>("tenDichVu"));
		tcPtpDV_SoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
		tcPtpDV_DonViTinh.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		tcPtpDV_DonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
		tcPtpDV_ThanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
	}

	private void initTablePtpPtck() {
		tcPtpPTCK_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvPtpPTCK.getItems().indexOf(column.getValue()) + 1));
		tcPtpPTCK_MaPTCK.setCellValueFactory(new PropertyValueFactory<>("MaPTCKPhong"));
		tcPtpPTCK_NoiDung.setCellValueFactory(new PropertyValueFactory<>("NoiDung"));
		tcPtpPTCK_SoLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
		tcPtpPTCK_DonGia.setCellValueFactory(new PropertyValueFactory<>("DonGia"));
		tcPtpPTCK_ThanhTien.setCellValueFactory(new PropertyValueFactory<>("TriGia"));
	}

	private void initTablePTPhong() {
		tcPTP_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvPTPhong.getItems().indexOf(column.getValue()) + 1));
		tcPTP_MaPTP.setCellValueFactory(new PropertyValueFactory<>("MaPTPhong"));
		tcPTP_MaPhong.setCellValueFactory(new PropertyValueFactory<>("MaPhong"));
		tcPTP_LoaiPhong.setCellValueFactory(new PropertyValueFactory<>("LoaiPhongThue"));
		tcPTP_SoKhachToiDa.setCellValueFactory(new PropertyValueFactory<>("SoKhachToiDa"));
		tcPTP_DonGiaThue.setCellValueFactory(new PropertyValueFactory<>("DonGiaThue"));
		tcPTP_NgayNhan.setCellValueFactory(new PropertyValueFactory<>("NgayNhan"));
		tcPTP_NgayTra.setCellValueFactory(new PropertyValueFactory<>("NgayTra"));
		tcPTP_TienCoc.setCellValueFactory(new PropertyValueFactory<>("TienCoc"));
	}

	private void initTableKhach() {
		tcKhach_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvKhach.getItems().indexOf(column.getValue()) + 1));
		tcKhach_MaKhach.setCellValueFactory(new PropertyValueFactory<>("MaKhachHang"));
		tcKhach_HoTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
		tcKhach_SoDienThoai.setCellValueFactory(new PropertyValueFactory<>("SoDienThoai"));
		tcKhach_CMND.setCellValueFactory(new PropertyValueFactory<>("CMND"));
		tcKhach_GioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
		tcKhach_QuocTich.setCellValueFactory(new PropertyValueFactory<>("QuocTich"));
	}

	private void initTableHDPhong() {
		dsHDPhong.addListener(new ListChangeListener<PTPhongDTO>() {
			@Override
			public void onChanged(Change<? extends PTPhongDTO> change) {
				Integer soLuong = dsHDPhong.size();
				Integer tienCoc = dsHDPhong.stream().mapToInt(PTPhongDTO::getTienCocValue).sum();
				Integer tienPhong = dsHDPhong.stream().mapToInt(PTPhongDTO::getThanhTienValue).sum();
				lbHD_SoLuong.setText(soLuong + " phòng");
				lbHD_TienCoc.setText(MoneyFormatHelper.format(tienCoc));
				lbHD_TienPhong.setText(MoneyFormatHelper.format(tienPhong));
				calculateTamTinh();
			}
		});
		tcHD_MaPTP.setCellValueFactory(new PropertyValueFactory<>("MaPTPhong"));
		tcHD_SoPhong.setCellValueFactory(new PropertyValueFactory<>("MaPhong"));
		tcHD_TienCoc.setCellValueFactory(new PropertyValueFactory<>("TienCoc"));
		tcHD_ThanhTien.setCellValueFactory(new PropertyValueFactory<>("ThanhTien"));
		tcHD_ThanhTienValue.setCellValueFactory(new PropertyValueFactory<>("ThanhTienValue"));
	}

	private void initTableHDPtck() {
		dsHDPtck.addListener(new ListChangeListener<HDPtckDTO>() {
			@Override
			public void onChanged(Change<? extends HDPtckDTO> arg0) {
				Integer tienPtck = dsHDPtck.stream().mapToInt(HDPtckDTO::getTriGiaValue).sum();
				lbHD_TienPtck.setText(MoneyFormatHelper.format(tienPtck));
				calculateTamTinh();
			}
		});
		tcHDPtck_NoiDung.setCellValueFactory(new PropertyValueFactory<>("noiDung"));
		tcHDPtck_TriGia.setCellValueFactory(new PropertyValueFactory<>("triGia"));
	}

	private void initTableHoaDon() {
		tcHD_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvHoaDon.getItems().indexOf(column.getValue()) + 1));
		tcHD_MaHD.setCellValueFactory(new PropertyValueFactory<>("MaHoaDon"));
		tcHD_NgayLap.setCellValueFactory(new PropertyValueFactory<>("NgayHoaDon"));
		tcHD_NhanVien.setCellValueFactory(new PropertyValueFactory<>("TenNhanVien"));
		tcHD_TenKhach.setCellValueFactory(new PropertyValueFactory<>("TenKhach"));
		tcHD_DienThoai.setCellValueFactory(new PropertyValueFactory<>("DienThoai"));
		tcHD_CMND.setCellValueFactory(new PropertyValueFactory<>("CMND"));
		tcHD_TriGia.setCellValueFactory(new PropertyValueFactory<>("GiaTri"));
		tcHD_TongCong.setCellValueFactory(new PropertyValueFactory<>("TongCong"));
	}

	private void initTableDichVu() {
		tcDV_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvDichVu.getItems().indexOf(column.getValue()) + 1));
		tcDV_MaDichVu.setCellValueFactory(new PropertyValueFactory<>("MaDichVu"));
		tcDV_MaLoaiDichVu.setCellValueFactory(new PropertyValueFactory<>("maLoaiDichVu"));
		tcDV_MaNhaCungCap.setCellValueFactory(new PropertyValueFactory<>("maNhaCungCap"));
		tcDV_NhaCungCap.setCellValueFactory(new PropertyValueFactory<>("tenNhaCungCap"));
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

	private void initTablePhongPTP() {
		tcPhongPTP_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvPhongPTP.getItems().indexOf(column.getValue()) + 1));
		tcPhongPTP_MaPTP.setCellValueFactory(new PropertyValueFactory<>("maPTPhong"));
		tcPhongPTP_KhachThue.setCellValueFactory(new PropertyValueFactory<>("khachThue"));
		tcPhongPTP_DienThoai.setCellValueFactory(new PropertyValueFactory<>("dienThoai"));
		tcPhongPTP_NgayNhan.setCellValueFactory(new PropertyValueFactory<>("ngayNhan"));
		tcPhongPTP_NgayTra.setCellValueFactory(new PropertyValueFactory<>("ngayTra"));
	}

	public void loadComboboxes() {
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
			handleTraCuuPhong();
		} catch (Exception e) {
			// do nothing :)
		}
	}

	public void showChiTietPhong(PhongDTO phong) {
		lbPhong_MaPhong.setText(phong.getMaPhong());
		lbPhong_TinhTrang.setText(phong.getTinhTrang().getTenTinhTrang());
		lbPhong_KhachToiDa.setText(phong.getLoaiPhong().getSoKhachToiDa().toString());
		lbPhong_LoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
		lbPhong_GhiChu.setText(phong.getGhiChu());
		lbPhong_DonGia.setText(MoneyFormatHelper.format(phong.getLoaiPhong().getDonGiaValue(), "VND"));
	}

	public void loadTablePhong() {
		class RoomClickedHandler {
			public void handleChiTietPhong(PhongDTO phong) {
				showChiTietPhong(phong);
				updateControlsByTinhTrang(phong);
			}

			private void updateControlsByTinhTrang(PhongDTO phong) {
				ObservableList<Tab> tabs = tpPhong_ChiTiet.getTabs();
				try {
					if (phong.getTinhTrang().getTenTinhTrang().equals("Thuê")) {
						if (tabs.contains(tabPhong_PhieuDangKy)) {
							tabs.remove(tabPhong_PhieuDangKy);
						}
						if (!tabs.contains(tabPhong_ThongTinThue)) {
							tabs.add(tabPhong_ThongTinThue);
							tabs.add(tabPhong_KhachThue);
							tabs.add(tabPhong_DichVu);
							tabs.add(tabPhong_PTCK);
						}
						btnPhong_DoiPhong.setDisable(false);
						btnPhong_SuaPhong.setDisable(true);
						btnPhong_XoaPhong.setDisable(true);

						try {
							Integer maPTP = PhongBUS.getMaPTP(phong.getMaPhong());
							lbPhong_MaPTP.setText(maPTP.toString());
							loadTablePTP_DV(maPTP);
							loadTablePtpPTCK(maPTP);
							loadTableKhach(maPTP);
							loadTableChiTiet(maPTP);
						} catch (SQLException e) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Lỗi");
							alert.setHeaderText("Không thể tải danh sách dịch vụ của phiếu thuê phòng!");
							alert.setContentText("Lỗi database!");
							alert.showAndWait();
						}
					} else {
						if (!tabs.contains(tabPhong_PhieuDangKy)) {
							tabs.add(0, tabPhong_PhieuDangKy);
						}
						if (tabs.contains(tabPhong_ThongTinThue)) {
							tabs.remove(tabPhong_ThongTinThue);
							tabs.remove(tabPhong_KhachThue);
							tabs.remove(tabPhong_DichVu);
							tabs.remove(tabPhong_PTCK);
						}
						loadTablePhongPTP(phong.getMaPhong());
						btnPhong_DoiPhong.setDisable(true);
						btnPhong_SuaPhong.setDisable(false);
						btnPhong_XoaPhong.setDisable(false);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		RoomClickedHandler handler = new RoomClickedHandler();
		try {
			tpPhong.getChildren().clear();
			List<String> dsMaPhong = new ArrayList<String>();
			PhongBUS.getDSPhong().stream().forEach(phong -> dsMaPhong.add(phong.getMaPhong()));

			ListRoomDetailPane listPanes = new ListRoomDetailPane(dsMaPhong);
			listPanes.getPanes().stream().forEach(pane -> {
				pane.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
					try {
						PhongDTO phong = PhongBUS.getPhongById(pane.getMaPhong());
						handler.handleChiTietPhong(phong);
					} catch (SQLException SQLException) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Lỗi");
						alert.setHeaderText("Không thể tải danh sách phòng khách sạn!");
						alert.setContentText("Lỗi database!");
						alert.showAndWait();
					}
				});
				tpPhong.getChildren().add(pane);
			});

			if (!tpPhong.getChildren().isEmpty()) {
				String maPhong = listPanes.getPanes().get(0).getMaPhong();
				PhongDTO phong = PhongBUS.getPhongById(maPhong);
				handler.handleChiTietPhong(phong);
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách phòng khách sạn!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTablePTP_DV(Integer maPTP) {
		try {
			ObservableList<PtpDichVuDTO> dsDichVu = FXCollections.observableArrayList();
			PtpDichVuBUS.getDSDichVuByMaPTP(maPTP).stream().forEach(dv -> dsDichVu.add(dv));
			tvPtpDichVu.setItems(dsDichVu);

			Integer tongTienDichVu = dsDichVu.stream().mapToInt(PtpDichVuDTO::getThanhTienValue).sum();
			lbPhong_TienDichVu.setText(MoneyFormatHelper.format(tongTienDichVu, "VND"));

		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách loại dịch vụ đã dùng!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTablePtpPTCK(Integer maPTP) {
		try {
			ObservableList<PtpPtckDTO> dsPTCK = FXCollections.observableArrayList();
			PtpPtckBUS.getDSPtckByMaPTP(maPTP).stream().forEach(ptck -> dsPTCK.add(ptck));
			tvPtpPTCK.setItems(dsPTCK);

			Integer tongTienPTCK = dsPTCK.stream().mapToInt(PtpPtckDTO::getTriGiaValue).sum();
			lbPhong_TienPTCK.setText(MoneyFormatHelper.format(tongTienPTCK, "VND"));
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách phụ thu/chiết khấu!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTableKhach(Integer maPTP) {
		try {
			ObservableList<KhachDTO> dsKhach = FXCollections.observableArrayList();
			KhachBUS.getDSKhachByMaPTP(maPTP).stream().forEach(khach -> dsKhach.add(khach));
			tvKhach.setItems(dsKhach);
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách khách trong phòng!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTableChiTiet(Integer maPTP) {
		try {
			PTPhongDTO ptPhong = PTPhongBUS.getPTPhongById(maPTP);
			PhieuThueDTO phieuThue = PhieuThueBUS.getPhieuThueByMaPTP(maPTP);
			lbPhong_KhachThue.setText(phieuThue.getTenKhachThue());
			lbPhong_TinhTrang.setText(ptPhong.getPhong().getTinhTrang().getTenTinhTrang());
			lbPhong_CMND.setText(phieuThue.getCMND());
			lbPhong_DienThoai.setText(phieuThue.getSoDienThoai());
			lbPhong_NhanVien.setText(phieuThue.getNhanVien().getTenNhanVien());
			lbPhong_NgayLap.setText(phieuThue.getNgayLap());
			lbPhong_NgayNhan.setText(ptPhong.getNgayNhan());
			lbPhong_NgayTra.setText(ptPhong.getNgayTra());
			lbPhong_DonGiaThue.setText(ptPhong.getDonGiaThue() + " VND");
			lbPhong_TienCoc.setText(ptPhong.getTienCoc() + " VND");
		} catch (SQLException ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách chi tiết phòng!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTablePhongPTP(String maPhong) {
		try {
			ObservableList<PTPhongDTO> dsPTPhong = FXCollections.observableArrayList();
			PTPhongBUS.getDSPTPhongByMaPhong(maPhong).stream().forEach(ptp -> dsPTPhong.add(ptp));
			tvPhongPTP.setItems(dsPTPhong);
		} catch (SQLException ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách chi tiết phòng!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTablePTPhong() {
		try {
			ObservableList<PTPhongDTO> dsPTPhong = FXCollections.observableArrayList();
			PTPhongBUS.getDSPhongDangKy().stream().forEach(ptp -> dsPTPhong.add(ptp));
			tvPTPhong.setItems(dsPTPhong);

			lbPT_SoLuongPhong.setText(String.format("%d phòng (%d khách)", tvPTPhong.getItems().size(),
					tvPTPhong.getItems().stream().mapToInt(PTPhongDTO::getSoKhachToiDa).sum()));
			lbPT_TongCoc.setText(MoneyFormatHelper
					.format(tvPTPhong.getItems().stream().mapToInt(PTPhongDTO::getTienCocValue).sum(), "VND"));
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Tải phiếu thuê phòng thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTableHoaDon() {
		try {
			ObservableList<HoaDonDTO> dsHoaDon = FXCollections.observableArrayList();
			HoaDonBUS.getDSHoaDon().stream().forEach(hd -> dsHoaDon.add(hd));
			tvHoaDon.setItems(dsHoaDon);
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Tải hoá đơn thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTablePhieuThue() {
		try {
			ObservableList<PhieuThueDTO> dsPhieuThue = FXCollections.observableArrayList();
			PhieuThueBUS.getDSPhieuThue().stream().forEach(pt -> dsPhieuThue.add(pt));
			tvPhieuThue.setItems(dsPhieuThue);
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Lập phiếu thuê thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void loadTableDichVu() {
		try {
			ObservableList<DichVuDTO> dsDichVu = FXCollections.observableArrayList();
			DichVuBUS.getDSDichVu().stream().forEach(dv -> dsDichVu.add(dv));
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
			NhanVienBUS.getDSNhanVien().stream().forEach(nv -> dsNhanVien.add(nv));
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
			LoaiPhongBUS.getDSLoaiPhong().stream().forEach(lp -> dsLoaiPhong.add(lp));
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
			NhaCungCapBUS.getDSNhaCungCap().stream().forEach(ncc -> dsNhaCungCap.add(ncc));
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
			LoaiDichVuBUS.getDSLoaiDichVu().stream().forEach(ldv -> dsLoaiDichVu.add(ldv));
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
			lbTS_TiLeTienCoc.setText(String.format("%.0f", thamSo.getTiLeTienCoc() * 100) + "%");
			lbTS_TiLeThueVAT.setText(String.format("%.0f", thamSo.getTiLeThueVAT() * 100) + "%");
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

	public void insertHDPtck(HDPtckDTO hdPtck) {
		dsHDPtck.add(hdPtck);
		tvHDPtck.setItems(dsHDPtck);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Thành công");
		alert.setHeaderText("Thêm ptck " + hdPtck.getNoiDung() + " thành công!");
		alert.showAndWait();
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
			List<String> dsMaPhong = new ArrayList<String>();
			PhongBUS.getDSPhongCoTheThue(ngayNhan, ngayTra, maLoaiPhong).stream()
					.forEach(phong -> dsMaPhong.add(phong.getMaPhong()));

			ListRoomDetailPane listPanes = new ListRoomDetailPane(dsMaPhong);
			for (RoomDetailPane pane : listPanes.getPanes()) {
				pane.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
					try {
						String maPhong = pane.getMaPhong();
						PhongDTO phong = PhongBUS.getPhongById(maPhong);
						lbTC_MaPhong.setText(phong.getMaPhong());
						lbTC_KhachToiDa.setText(phong.getLoaiPhong().getSoKhachToiDa().toString() + " khách");
						lbTC_GhiChu.setText(phong.getGhiChu());
					} catch (SQLException SQLException) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Lỗi");
						alert.setHeaderText("Không thể tải danh sách phòng khách sạn!");
						alert.setContentText("Lỗi database!");
						alert.showAndWait();
					}
				});
				tpTC_Phong.getChildren().add(pane);
			}

			if (dsMaPhong.size() > 0) {
				bpTC_ThongTinPhong.setVisible(true);
				String maPhong = listPanes.getPanes().get(0).getMaPhong();
				PhongDTO phong = PhongBUS.getPhongById(maPhong);
				lbTC_MaPhong.setText(phong.getMaPhong());
				lbTC_KhachToiDa.setText(phong.getLoaiPhong().getSoKhachToiDa().toString() + " khách");
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

			if (ConfirmDialogHelper
					.confirm(String.format("Xác nhận xoá %s %s?", nhanVien.getChucVu(), nhanVien.getTenNhanVien()))) {
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
			if (ConfirmDialogHelper.confirm("Xác nhận xoá dịch vụ " + dichVu.getTenDichVu() + "?")) {
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
		if (ConfirmDialogHelper.confirm("Bạn đã chắc chắn các thông tin để lập phiếu?")) {
			if (!(tfPT_KhachThue.getText().matches("^([^0-9]{1,30})$") && tfPT_CMND.getText().matches("^[0-9]{1,15}$")
					&& tfPT_DienThoai.getText().matches("^[0-9]{1,15}$") && tfPT_Email.getText().matches("^.{1,45}$")
					&& tfPT_GhiChu.getText().matches("^.{0,45}$") && tvPTPhong.getItems().size() > 0)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại!");
				alert.setHeaderText("Lập phiếu thuê thất bại!");
				alert.setContentText(
						"- Tên khách tối đa 30 kí tự và không chứa số.\n" + "- CMND chỉ chứa số và tối đa 15 kí tự.\n"
								+ "- Điện thoại chỉ chứa số và tối đa 15 kí tự.\n" + "- Email tối đa 45 kí tự.\n"
								+ "- Ghi chú tối đa 45 kí tự.\n" + "- Phải có ít nhất 1 phòng thuê trong danh sách.");
				alert.showAndWait();
				return;
			}

			try {
				NhanVienDTO nhanVien = NhanVienBUS.getNhanVienById(Integer.parseInt(lbNV_MaNhanVien.getText()));
				PhieuThueDTO phieuThue = new PhieuThueDTO(nhanVien, DateFormatHelper.getDate(dpPT_NgayLap.getValue()),
						tfPT_KhachThue.getText(), tfPT_CMND.getText(), tfPT_DienThoai.getText(), tfPT_Email.getText(),
						tfPT_GhiChu.getText());

				if (PhieuThueBUS.insertPhieuThue(phieuThue)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Lập phiếu thuê thành công!");
					alert.showAndWait();

					tfPT_KhachThue.clear();
					tfPT_CMND.clear();
					tfPT_DienThoai.clear();
					tfPT_Email.clear();
					tfPT_GhiChu.clear();
					tvPTPhong.getItems().clear();
					loadTablePhieuThue();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Lập phiếu thuê thất bại!");
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

	public void handleTaoPhieuThueMoi(ActionEvent e) {
		if (ConfirmDialogHelper.confirm("Xác nhận tạo mới phiếu thuê phòng?")) {
			tfPT_KhachThue.clear();
			tfPT_CMND.clear();
			tfPT_DienThoai.clear();
			tfPT_Email.clear();
			tfPT_GhiChu.clear();
			lbPT_SoLuongPhong.setText("0 phòng (0 khách)");
			lbPT_TongCoc.setText("0 VND");

			try {
				if (tvPhieuThue.getItems().size() > 0) {
					if (PTPhongBUS.deleteAllPTPhong()) {
						tvPTPhong.getItems().clear();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Lỗi");
						alert.setHeaderText("Xoá các phòng trong phiếu thuê thất bại!");
						alert.showAndWait();
					}
				}
			} catch (SQLException SQLException) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Xoá các phòng trong phiếu thuê thất bại!");
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

			if (PTPhongBUS.insertPTPhong(ptPhong)) {
				// tải lại phiếu thuê phòng
				loadTablePTPhong();

				// thông báo
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Thêm phòng " + ptPhong.getMaPhong() + " vào phiếu thuê thành công!");
				alert.showAndWait();

				// load lại bảng tra cứu
				handleTraCuuPhong();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Thêm phòng vào phiếu thuê thất bại!");
				alert.showAndWait();
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Thêm phòng vào phiếu thuê thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleXoaPhieuDangKy(ActionEvent e) {
		try {
			PTPhongDTO ptp = tvPTPhong.getSelectionModel().getSelectedItem();
			if (ConfirmDialogHelper.confirm("Xác nhận xoá phòng " + ptp.getMaPhong() + " khỏi phiếu thuê?")) {
				if (PTPhongBUS.deletePTPhong(ptp)) {
					loadTablePTPhong();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xoá thành công phòng " + ptp.getMaPhong() + " khỏi phiếu thuê!");
					alert.showAndWait();
					handleTraCuuPhong();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Xoá phòng trong phiếu thuê thất bại!");
					alert.setContentText("Không thể xoá phiếu thuê của phòng đang được thuê hoặc đã thanh toán");
					alert.showAndWait();
				}
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Xoá phòng trong phiếu thuê thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Xoá phòng trong phiếu thuê thất bại!");
			alert.setContentText("Vui lòng chọn phòng cần xoá!");
			alert.showAndWait();
		}
	}

	public void handleXemChiTietPhieuThue(ActionEvent e) {
		try {
			PhieuThueDTO phieuThue = tvPhieuThue.getSelectionModel().getSelectedItem();
			if (phieuThue == null)
				throw new NullPointerException();
			Stage popUpChiTiet = PopUpStageHelper.createPopUpStage("/application/suaPhieuThue.fxml", 1180, 740);
			FXMLLoader loader = (FXMLLoader) popUpChiTiet.getUserData();
			PhieuThueController controller = loader.getController();
			controller.initialize(phieuThue.getMaPhieuThue());
			popUpChiTiet.getScene().setUserData(this);
			popUpChiTiet.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xem phiêu thuê!");
			alert.setContentText("Vui lòng chọn phiếu thuê cần xem!");
			alert.showAndWait();
		}
	}

	public void handleXoaPhieuThue(ActionEvent e) {
		try {
			PhieuThueDTO phieuThue = tvPhieuThue.getSelectionModel().getSelectedItem();
			if (phieuThue == null)
				throw new NullPointerException();

			if (ConfirmDialogHelper.confirm(String.format("Xác nhận xoá phiếu thuê %s?", phieuThue.getMaPhieuThue()))) {
				if (PhieuThueBUS.deletePhieuThue(phieuThue.getMaPhieuThue())) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xóa phiếu thuê thành công!");
					alert.setContentText(String.format("Đã xoá thành công phiếu thuê %s.", phieuThue.getMaPhieuThue()));
					alert.showAndWait();
					loadTablePhieuThue();
					loadTableHoaDon();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Không thể xóa phiếu thuê!");
					alert.setContentText("Phiếu thuê chứa phòng đang được thuê hoặc đã thanh toán.");
					alert.showAndWait();
				}
			}
		} catch (SQLException SQLException) {
			SQLException.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xóa phiếu thuê!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xoá phiêu thuê!");
			alert.setContentText("Vui lòng chọn phiếu thuê cần xoá!");
			alert.showAndWait();
		}
	}

	public void handleThanhToanCoc(ActionEvent e) {
		try {
			PhieuThueDTO phieuThue = tvPhieuThue.getSelectionModel().getSelectedItem();
			if (phieuThue.getThanhToanCocValue()) {
				if (ConfirmDialogHelper
						.confirm("Xác nhận huỷ thanh toán cọc của phiếu thuê " + phieuThue.getMaPhieuThue())) {
					if (PhieuThueBUS.updateThanhToanCoc(phieuThue.getMaPhieuThue(), false)) {
						loadTablePhieuThue();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Lỗi");
						alert.setHeaderText("Không thể cập nhật phiếu thuê!");
						alert.setContentText("Đã có phòng trong phiếu được thanh toán!");
						alert.showAndWait();
					}
				}
			} else if (ConfirmDialogHelper
					.confirm("Xác nhận thanh toán cọc của phiếu thuê " + phieuThue.getMaPhieuThue())) {
				PhieuThueBUS.updateThanhToanCoc(phieuThue.getMaPhieuThue(), true);
				loadTablePhieuThue();
				loadTablePhongPTP(lbPhong_MaPhong.getText());
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể cập nhật phiếu thuê!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể cập nhật phiêu thuê!");
			alert.setContentText("Vui lòng chọn phiếu thuê cần cập nhật!");
			alert.showAndWait();
		}
	}

	public void handleThemPtpDichVu(ActionEvent e) {
		try {
			String link = "/application/themPtpDichVu.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 980, 460);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			ThemPtpDichVuController controller = loader.getController();
			PTPhongDTO ptPhong = PTPhongBUS.getPTPhongById(Integer.parseInt(lbPhong_MaPTP.getText()));
			controller.initialize(ptPhong);
			popUpStage.getScene().setUserData(this);
			popUpStage.showAndWait();
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể thêm dịch vụ!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleSuaPtpDichVu(ActionEvent e) {
		try {
			String link = "/application/suaPtpDichVu.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 500, 350);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			SuaPtpDichVuController controller = loader.getController();
			PtpDichVuDTO ptp_dv = tvPtpDichVu.getSelectionModel().getSelectedItem();
			controller.initialize(ptp_dv);
			popUpStage.getScene().setUserData(this);
			popUpStage.showAndWait();
		} catch (NullPointerException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa dịch vụ!");
			alert.setContentText("Vui lòng chọn dịch vụ cần sửa!");
			alert.showAndWait();
		}
	}

	public void handleXoaPtpDichVu(ActionEvent e) {
		try {
			PtpDichVuDTO ptpDichVu = tvPtpDichVu.getSelectionModel().getSelectedItem();
			if (ConfirmDialogHelper.confirm("Xác nhận xoá dịch vụ " + ptpDichVu.getTenDichVu() + "?")) {
				if (PtpDichVuBUS.deletePtpDichVu(ptpDichVu.getMaPTPDichVu())) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xóa loại dịch vụ thành công!");
					alert.setContentText("Đã xóa dịch vụ " + ptpDichVu.getTenDichVu() + "!");
					alert.showAndWait();
					loadTablePTP_DV(Integer.parseInt(lbPhong_MaPTP.getText()));
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

	public void handleThemPtpPtck(ActionEvent e) {
		try {
			String link = "/application/popupPtpPtck.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 600, 560);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			PtpPtckController controller = loader.getController();
			PTPhongDTO ptPhong = PTPhongBUS.getPTPhongById(Integer.parseInt(lbPhong_MaPTP.getText()));
			controller.initialize(ptPhong);
			popUpStage.getScene().setUserData(this);
			popUpStage.showAndWait();
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể thêm ptck!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleSuaPtpPtck(ActionEvent e) {
		try {
			String link = "/application/popupPtpPtck.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 600, 560);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			PtpPtckController controller = loader.getController();
			PtpPtckDTO ptck = tvPtpPTCK.getSelectionModel().getSelectedItem();
			controller.initialize(ptck);
			popUpStage.getScene().setUserData(this);
			popUpStage.showAndWait();
		} catch (NullPointerException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa ptck!");
			alert.setContentText("Vui lòng chọn ptck cần sửa!");
			alert.showAndWait();
		}
	}

	public void handleXoaPtpPtck(ActionEvent e) {
		try {
			PtpPtckDTO ptpPtck = tvPtpPTCK.getSelectionModel().getSelectedItem();
			if (ConfirmDialogHelper.confirm("Xác nhận xoá ptck: " + ptpPtck.getNoiDung() + "?")) {
				if (PtpPtckBUS.deletePtpPtck(ptpPtck.getMaPTCKPhong())) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xóa ptck thành công!");
					alert.setContentText("Đã xóa ptck: " + ptpPtck.getNoiDung() + "!");
					alert.showAndWait();
					loadTablePtpPTCK(Integer.valueOf(lbPhong_MaPTP.getText()));
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Không thể xóa ptck!");
					alert.showAndWait();
				}
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xóa ptck!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xoá ptck!");
			alert.setContentText("Vui lòng chọn ptck cần xoá!");
			alert.showAndWait();
		}
	}

	public void handleThemLoaiDichVu(ActionEvent e) {
		String link = "/application/popupLoaiDichVu.fxml";
		Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 500, 250);
		popUpStage.getScene().setUserData(this);
		popUpStage.showAndWait();
	}

	public void handleSuaLoaiDichVu(ActionEvent e) {
		try {
			String link = "/application/popupLoaiDichVu.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 500, 250);
			LoaiDichVuDTO loaiDichVu = tvLoaiDichVu.getSelectionModel().getSelectedItem();
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			LoaiDichVuController controller = loader.getController();
			controller.initialize(loaiDichVu);
			popUpStage.getScene().setUserData(this);
			popUpStage.showAndWait();
		} catch (NullPointerException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa loại dịch vụ!");
			alert.setContentText("Vui lòng chọn loại dịch vụ!");
			alert.showAndWait();
		}
	}

	public void handleXoaLoaiDichVu(ActionEvent e) {
		try {
			LoaiDichVuDTO loaiDichVu = tvLoaiDichVu.getSelectionModel().getSelectedItem();
			if (ConfirmDialogHelper.confirm("Xác nhận xóa loại dịch vụ " + loaiDichVu.getTenLoaiDichVu() + "?")) {
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

	public void handleThemLoaiPhong(ActionEvent e) {
		String link = "/application/popupLoaiPhong.fxml";
		Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 540, 360);
		popUpStage.getScene().setUserData(this);
		popUpStage.showAndWait();
	}

	public void handleSuaLoaiPhong(ActionEvent e) {
		try {
			String link = "/application/popupLoaiPhong.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 540, 360);
			LoaiPhongDTO loaiPhong = tvLoaiPhong.getSelectionModel().getSelectedItem();
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			LoaiPhongController controller = loader.getController();
			controller.initialize(loaiPhong);
			popUpStage.getScene().setUserData(this);
			popUpStage.showAndWait();
		} catch (NullPointerException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa loại phòng!");
			alert.setContentText("Vui lòng chọn loại phòng cần sửa!");
			alert.showAndWait();
		}
	}

	public void handleXoaLoaiPhong(ActionEvent e) {
		try {
			LoaiPhongDTO loaiPhong = tvLoaiPhong.getSelectionModel().getSelectedItem();
			if (ConfirmDialogHelper.confirm("Xác nhận xóa loại phòng " + loaiPhong.getTenLoaiPhong() + "?")) {
				try {
					if (LoaiPhongBUS.deleteLoaiPhong(loaiPhong.getMaLoaiPhong())) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Thành công");
						alert.setHeaderText("Xóa loại phòng thành công!");
						alert.setContentText("Đã xóa loại phòng " + loaiPhong.getTenLoaiPhong() + "!");
						alert.showAndWait();
						loadTableLoaiPhong();
						loadComboboxes();
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

	public void handleThemNhaCungCap(ActionEvent e) {
		String link = "/application/popupNhaCungCap.fxml";
		Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 520, 320);
		popUpStage.getScene().setUserData(this);
		popUpStage.showAndWait();
	}

	public void handleSuaNhaCungCap(ActionEvent e) {
		try {
			String link = "/application/popupNhaCungCap.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 520, 320);
			NhaCungCapDTO nhaCungCap = tvNhaCungCap.getSelectionModel().getSelectedItem();
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			NhaCungCapController controller = loader.getController();
			controller.initialize(nhaCungCap);
			popUpStage.getScene().setUserData(this);
			popUpStage.showAndWait();
		} catch (NullPointerException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa nhà cung cấp!");
			alert.setContentText("Vui lòng chọn nhà cung cấp cần sửa!");
			alert.showAndWait();
		}
	}

	public void handleXoaNhaCungCap(ActionEvent e) {
		NhaCungCapDTO nhaCungCap = tvNhaCungCap.getSelectionModel().getSelectedItem();
		if (ConfirmDialogHelper.confirm("Xác nhận xoá nhà cung cấp " + nhaCungCap.getTenNhaCungCap() + "?")) {
			try {
				if (NhaCungCapBUS.deleteNhaCungCap(nhaCungCap.getMaNhaCungCap())) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xóa nhà cung cấp thành công!");
					alert.setContentText("Đã xóa nhà cung cấp " + nhaCungCap.getTenNhaCungCap() + "!");
					alert.showAndWait();
					loadTableNhaCungCap();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Xóa nhà cung cấp thất bại!");
					alert.setContentText("Vẫn còn dịch vụ của nhà cung cấp " + nhaCungCap.getTenNhaCungCap());
					alert.showAndWait();
				}
			} catch (SQLException ex) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Xóa nhà cung cấp thất bại!");
				alert.setContentText("Lỗi database!");
				alert.showAndWait();
			}
		}
	}

	public void handleSuaThamSo(ActionEvent e) {
		try {
			String link = "/application/popupThamSo.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 450, 300);
			popUpStage.getScene().setUserData(this);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			ThamSoController controller = loader.getController();
			ThamSoDTO thamSo = ThamSoBUS.getThamSo();
			controller.initialize(thamSo);
			popUpStage.showAndWait();
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa tham số!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleThemPhong(ActionEvent e) {
		String link = "/application/popupPhong.fxml";
		Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 480, 530);
		popUpStage.getScene().setUserData(this);
		popUpStage.showAndWait();
	}

	public void handleSuaPhong(ActionEvent e) {
		try {
			PhongDTO phong = PhongBUS.getPhongById(lbPhong_MaPhong.getText());
			String link = "/application/popupPhong.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 480, 530);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			PhongController controller = loader.getController();
			controller.initialize(phong);
			popUpStage.getScene().setUserData(this);
			popUpStage.showAndWait();
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa phòng số!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleXoaPhong(ActionEvent e) {
		try {
			String maPhong = lbPhong_MaPhong.getText();
			if (ConfirmDialogHelper.confirm("Xác nhận xoá phòng " + maPhong + "?")) {
				if (PhongBUS.deletePhong(maPhong)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xóa loại phòng thành công!");
					alert.setContentText("Đã xóa phòng " + maPhong + "!");
					alert.showAndWait();
					loadTablePhong();
					handleTraCuuPhong();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Xóa phòng thất bại!");
					alert.showAndWait();
				}
			}
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Xóa phòng thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleThongKeDoanhThu(ActionEvent e) {
		try {
			CategoryAxis xAxis = new CategoryAxis();
			xAxis.setLabel("Tháng");

			NumberAxis yAxis = new NumberAxis();
			yAxis.setLabel("Doanh thu");

			BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
			barChart.setStyle("-fx-font-size: 16px");

			int nam = snTK_DoanhThu.getValue();
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName("Doanh thu theo tháng trong năm " + nam);

			for (ThongKeDoanhThuDTO thongke : ThongKeBUS.getDoanhThuTheoNam(nam)) {
				series.getData().add(new XYChart.Data<>(thongke.getThang().toString(), thongke.getDoanhThu()));
			}

			barChart.getData().add(series);
			bpTK_DoanhThu.setCenter(barChart);
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Không thể xem báo cáo doanh thu năm!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleThongKeLuongKhach(ActionEvent e) {
		try {
			int nam = snTK_LuongKhach.getValue();

			CategoryAxis xAxis = new CategoryAxis();
			xAxis.setLabel("Tháng");

			NumberAxis yAxis = new NumberAxis();
			yAxis.setLabel("Số khách");

			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName("Lượng khách theo tháng trong năm " + nam);

			BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
			barChart.setStyle("-fx-font-size: 16px");

			for (ThongKeSoKhachDTO thongke : ThongKeBUS.getSoKhachTheoNam(nam)) {
				series.getData().add(new XYChart.Data<>(thongke.getThang().toString(), thongke.getSoKhach()));
			}

			barChart.getData().add(series);
			bpTK_LuongKhach.setCenter(barChart);
		} catch (SQLException ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Không thể xem báo cáo lượng khách!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleThongKeLoaiPhong(ActionEvent e) {
		try {
			CategoryAxis xAxis = new CategoryAxis();
			xAxis.setLabel("Loại phòng");

			NumberAxis yAxis = new NumberAxis();
			yAxis.setLabel("Doanh thu");

			BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
			barChart.setStyle("-fx-font-size: 16px");

			int thang = snTK_LoaiPhongThang.getValue();
			int nam = snTK_LoaiPhongNam.getValue();
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName("Doanh thu theo loại phòng trong tháng " + thang + "/" + nam);

			for (ThongKeLoaiPhongDTO thongke : ThongKeBUS.getLoaiPhongTheoThang(thang, nam)) {
				series.getData().add(new XYChart.Data<>(thongke.getLoaiPhong(), thongke.getDoanhThu()));
			}

			barChart.getData().add(series);
			bpTK_LoaiPhong.setCenter(barChart);
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Không thể xem báo cáo loại phòng theo tháng!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleThongKeLoaiDichVu(ActionEvent e) {
		try {
			CategoryAxis xAxis = new CategoryAxis();
			xAxis.setLabel("Loại Dịch vụ");

			NumberAxis yAxis = new NumberAxis();
			yAxis.setLabel("Doanh thu");

			BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
			barChart.setStyle("-fx-font-size: 16px");

			int thang = snTK_LoaiDichVuThang.getValue();
			int nam = snTK_LoaiDichVuNam.getValue();
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName("Doanh thu theo loại dịch vụ trong tháng " + thang + "/" + nam);

			for (ThongKeLoaiDichVuDTO thongke : ThongKeBUS.getLoaiDichVuTheoThang(thang, nam)) {
				series.getData().add(new XYChart.Data<>(thongke.getTenLoaiDichVu(), thongke.getDoanhThu()));
			}

			barChart.getData().add(series);
			bpTK_LoaiDichVu.setCenter(barChart);
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Không thể xem báo cáo loại dịch vụ!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleThemKhach(ActionEvent e) {
		try {
			String link = "/application/popupKhach.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 480, 530);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			KhachController controller = loader.getController();
			PTPhongDTO ptPhong = PTPhongBUS.getPTPhongById(Integer.valueOf(lbPhong_MaPTP.getText()));
			controller.initialize(ptPhong);
			popUpStage.getScene().setUserData(this);
			popUpStage.showAndWait();
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Không tải thông tin phòng!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleSuaKhach(ActionEvent e) {
		try {
			String link = "/application/popupKhach.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 480, 530);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			KhachController controller = loader.getController();
			KhachDTO khach = tvKhach.getSelectionModel().getSelectedItem();
			controller.initialize(khach);
			popUpStage.getScene().setUserData(this);
			popUpStage.showAndWait();
		} catch (NullPointerException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa thông tin khách!");
			alert.setContentText("Vui lòng chọn khách hàng cần sửa!");
			alert.showAndWait();
		}
	}

	public void handleXoaKhach(ActionEvent e) {
		try {
			KhachDTO khach = tvKhach.getSelectionModel().getSelectedItem();
			if (ConfirmDialogHelper.confirm("Xác nhận xoá khách " + khach.getHoTen() + "?")) {
				if (KhachBUS.deleteKhach(khach.getMaKhachHang())) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xóa loại khách thành công!");
					alert.setContentText("Đã xóa khách " + khach.getHoTen() + "!");
					alert.showAndWait();
					loadTableKhach(Integer.parseInt(lbPhong_MaPTP.getText()));
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Không thể xóa khách!");
					alert.showAndWait();
				}
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xóa khách!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xoá khách!");
			alert.setContentText("Vui lòng chọn khách cần xoá!");
			alert.showAndWait();
		}
	}

	public void handleTimKhach(ActionEvent e) {
		String link = "/application/popupTimKhach.fxml";
		Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 590, 310);
		popUpStage.showAndWait();
	}

	public void handleNhanPhong(ActionEvent e) {
		try {
			Integer maPTPhong = tvPhongPTP.getSelectionModel().getSelectedItem().getMaPTPhong();
			String maPhong = lbPhong_MaPhong.getText();

			if (ConfirmDialogHelper.confirm("Xá nhận nhận phòng của phiếu " + maPTPhong.toString() + "?")) {
				if (PhongBUS.updateNhanPhong(maPhong, maPTPhong)) {
					ObservableList<Tab> tabs = tpPhong_ChiTiet.getTabs();
					tabs.remove(tabPhong_PhieuDangKy);
					tabs.add(tabPhong_ThongTinThue);
					tabs.add(tabPhong_KhachThue);
					tabs.add(tabPhong_DichVu);
					tabs.add(tabPhong_PTCK);

					btnPhong_DoiPhong.setDisable(false);
					btnPhong_SuaPhong.setDisable(true);
					btnPhong_XoaPhong.setDisable(true);

					Integer maPTP = PhongBUS.getMaPTP(lbPhong_MaPhong.getText());
					lbPhong_MaPTP.setText(maPTP.toString());
					loadTablePTP_DV(maPTP);
					loadTablePtpPTCK(maPTP);
					loadTableKhach(maPTP);
					loadTableChiTiet(maPTP);
				}
			}
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể nhận phòng!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Nhận phòng thất bại!");
			alert.setContentText("Vui lòng chọn phiếu thuê phòng");
			alert.showAndWait();
		}
	}

	public void handleHuyDatPhong(ActionEvent e) {
		try {
			PTPhongDTO ptp = tvPhongPTP.getSelectionModel().getSelectedItem();
			if (ptp == null) {
				throw new NullPointerException();
			}
			if (ConfirmDialogHelper.confirm("Xác nhận huỷ đặt phòng của phiếu " + ptp.getMaPTPhong() + "?")) {
				if (PTPhongBUS.deletePTPhong(ptp)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xoá thành công phòng " + ptp.getMaPhong() + " khỏi phiếu thuê!");
					alert.showAndWait();
					handleTraCuuPhong();
					loadTablePhieuThue();
					loadTablePhongPTP(ptp.getMaPhong());
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Xoá phòng trong phiếu thuê thất bại!");
					alert.setContentText("Không thể xoá phiếu thuê của phòng đang được thuê hoặc đã thanh toán");
					alert.showAndWait();
				}
			}
		} catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Xoá phiếu thuê phòng thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Xoá phiếu thuê phòng thất bại!");
			alert.setContentText("Vui lòng phiếu thuê phòng cần xoá!");
			alert.showAndWait();
		}
	}

	public void handleThemVaoHoaDon(ActionEvent e) {
		try {
			PTPhongDTO ptPhong = PTPhongBUS.getPTPhongById(Integer.valueOf(lbPhong_MaPTP.getText()));
			boolean isContainsPTP = dsHDPhong.stream().mapToInt(PTPhongDTO::getMaPTPhong)
					.anyMatch(maPTP -> maPTP == ptPhong.getMaPTPhong());
			if (isContainsPTP) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Thêm phòng vào hoá đơn thất bại!");
				alert.setContentText("Phòng đã tồn tại trong hoá đơn!");
				alert.showAndWait();
			} else {
				dsHDPhong.add(ptPhong);
				tvHDPhong.setItems(dsHDPhong);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Đã thêm phòng " + ptPhong.getMaPhong() + " vào hoá đơn!");
				alert.showAndWait();
			}
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Thêm phòng vào hoá đơn thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleXoaHoaDonPhong(ActionEvent e) {
		try {
			PTPhongDTO ptPhong = tvHDPhong.getSelectionModel().getSelectedItem();
			if (ptPhong == null)
				throw new NullPointerException();

			if (ConfirmDialogHelper.confirm("Xác nhận xoá phòng " + ptPhong.getMaPhong() + " khỏi hoá đơn?")) {
				dsHDPhong.remove(ptPhong);
				tvHDPhong.setItems(dsHDPhong);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Đã xoá phòng " + ptPhong.getMaPhong() + " khỏi hoá đơn!");
				alert.showAndWait();
			}
		} catch (NullPointerException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Xoá phòng trong hoá đơn thất bại!");
			alert.setContentText("Vui lòng chọn phòng cần xoá!");
			alert.showAndWait();
		}
	}

	public void handleXemChiTietHoaDon(ActionEvent e) {
		try {
			String link = "/application/popupChiTietHD.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 800, 475);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			CTHDController controller = loader.getController();
			String maPhong = tvHDPhong.getSelectionModel().getSelectedItem().getMaPhong();
			Integer maPTP = PhongBUS.getMaPTP(maPhong);
			controller.initialize(maPTP);
			popUpStage.setTitle("CHI TIẾT THUÊ PHÒNG " + maPhong);
			popUpStage.showAndWait();
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải chi tiết thuê phòng!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException nex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Xem chi tiết thuê phòng thất bại!");
			alert.setContentText("Vui lòng chọn phòng cần xem!");
			alert.showAndWait();
		}
	}

	public void handleXoaHoaDon(ActionEvent e) {
		try {
			HoaDonDTO hoaDon = tvHoaDon.getSelectionModel().getSelectedItem();
			if (hoaDon == null)
				throw new NullPointerException();

			if (ConfirmDialogHelper.confirm(String.format("Xác nhận xoá hoá đơn %s?", hoaDon.getMaHoaDon()))) {
				if (HoaDonBUS.deleteHoaDon(hoaDon.getMaHoaDon())) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Xóa hoá đơn thành công!");
					alert.setContentText(String.format("Đã xoá thành công hoá đơn %s.", hoaDon.getMaHoaDon()));
					alert.showAndWait();
					loadTablePhieuThue();
					loadTableHoaDon();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Không thể xóa hoá đơn!");
					alert.showAndWait();
				}
			}
		} catch (SQLException SQLException) {
			SQLException.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xóa hoá đơn!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		} catch (NullPointerException NullPointerException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xoá hoá đơn!");
			alert.setContentText("Vui lòng chọn hoá đơn cần xoá!");
			alert.showAndWait();
		}
	}

	public void handleThemHDPtck(ActionEvent e) {
		String link = "/application/popupHDPtck.fxml";
		Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 550, 380);
		popUpStage.getScene().setUserData(this);
		popUpStage.showAndWait();
	}

	public void handleXoaHDPtck(ActionEvent e) {
		try {
			HDPtckDTO hdPtck = tvHDPtck.getSelectionModel().getSelectedItem();
			if (ConfirmDialogHelper.confirm("Xác nhận xoá ptck " + hdPtck.getNoiDung() + "?")) {
				dsHDPtck.remove(hdPtck);
				tvHDPtck.setItems(dsHDPtck);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Đã xoá ptck " + hdPtck.getNoiDung());
				alert.showAndWait();
			}
		} catch (NullPointerException nex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Xoá ptck hoá đơn thất bại!");
			alert.setContentText("Vui lòng chọn ptck cần xoá!");
			alert.showAndWait();
		}
	}

	public void handleThanhToanHoaDon(ActionEvent e) {
		if (!(tfHD_TienNhan.getText().matches("^[0-9]{1,9}$") && lbHD_TienThua.getText().charAt(0) != '-')) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại!");
			alert.setHeaderText("Thanh toán hoá đơn thất bại!");
			alert.setContentText("- Tiền nhận chỉ gồm số không quá 1 tỉ.\n" + "- Tiền thừa không thể là số âm.");
			alert.showAndWait();
			return;
		}

		try {
			if (dsHDPhong.isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Tạo hoá đơn thất bại!");
				alert.setContentText("Vui lòng thêm ít nhất 1 phòng để thanh toán!");
				alert.showAndWait();
				return;
			}

			NhanVienDTO nhanVien = NhanVienBUS.getNhanVienById(Integer.valueOf(lbNV_MaNhanVien.getText()));
			HoaDonDTO hoaDon = new HoaDonDTO(nhanVien, DateFormatHelper.getDate(dpHD_NgayLap.getValue()),
					tfHD_TenKhach.getText(), tfHD_CMND.getText(), tfHD_DienThoai.getText(), tfHD_Email.getText(),
					MoneyFormatHelper.fromString(lbHD_TienPhong.getText()),
					MoneyFormatHelper.fromString(lbHD_TienPtck.getText()),
					MoneyFormatHelper.fromString(lbHD_TienCoc.getText()),
					MoneyFormatHelper.fromString(lbHD_TamTinh.getText()), Integer.valueOf(tfHD_TienNhan.getText()),
					MoneyFormatHelper.fromString(lbHD_TienThua.getText()), tfHD_GhiChu.getText());

			if (HoaDonBUS.insertHoaDon(hoaDon)) {
				// set mã hoá đơn vừa tạo
				Integer maHoaDon = HoaDonBUS.getMaxMaHoaDon();
				hoaDon.setMaHoaDon(maHoaDon);
				dsHDPtck.stream().forEach(hdPtck -> hdPtck.setHoaDon(hoaDon));

				// thêm các phụ thu chiết khấu của hoá đơn
				boolean insertHDPtck = dsHDPtck.stream().allMatch(hdPtck -> {
					try {
						return HDPtckBUS.insertHDPtck(hdPtck);
					} catch (SQLException ex1) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Thất bại");
						alert.setHeaderText("Tạo chi tiết hoá đơn thất bại!");
						alert.setContentText("Lỗi database!");
						alert.showAndWait();
						return false;
					}
				});

				// cập nhật mã hoá đơn cho phiếu thuê phòng
				boolean updateHDPhong = dsHDPhong.stream().allMatch(ptp -> {
					try {
						return PTPhongBUS.updateMaHoaDon(ptp.getMaPTPhong(), maHoaDon);
					} catch (SQLException ex2) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Thất bại");
						alert.setHeaderText("Tạo chi tiết hoá đơn thất bại!");
						alert.setContentText("Lỗi database!");
						alert.showAndWait();
						return false;
					}
				});

				if (insertHDPtck && updateHDPhong) {
					// thông báo
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thành công");
					alert.setHeaderText("Đã tạo thành công hoá đơn!");
					alert.showAndWait();

					// xoá thông tin hiện tại
					tfHD_TenKhach.clear();
					tfHD_CMND.clear();
					tfHD_DienThoai.clear();
					tfHD_Email.clear();
					tfHD_GhiChu.clear();
					tfHD_TienNhan.setText("0");
					dsHDPhong.clear();
					dsHDPtck.clear();

					// đặt lại enable các button
					btnHD_ChotThongTin.setText("🔒 CHỐT THÔNG TIN");
					dpHD_NgayLap.setDisable(!dpHD_NgayLap.isDisable());
					tfHD_TenKhach.setDisable(!tfHD_TenKhach.isDisable());
					tfHD_CMND.setDisable(!tfHD_CMND.isDisable());
					tfHD_DienThoai.setDisable(!tfHD_DienThoai.isDisable());

					tfHD_Email.setDisable(!tfHD_Email.isDisable());
					tfHD_GhiChu.setDisable(!tfHD_GhiChu.isDisable());
					btnHD_ThanhToan.setDisable(!btnHD_ThanhToan.isDisable());

					// cập nhật lại các bảng
					loadTablePhong();
					loadTableHoaDon();
					handleTraCuuPhong();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Thất bại");
					alert.setHeaderText("Tạo chi tiết hoá đơn thất bại!");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Tạo hoá đơn thất bại!");
				alert.showAndWait();
			}
		} catch (SQLException ex3) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Tạo hoá đơn thất bại!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleTaoHoaDonMoi(ActionEvent e) {
		if (ConfirmDialogHelper.confirm("Xác nhận tạo hoá đơn mới?", "Các thông tin vừa nhập sẽ bị xoá!")) {
			tfHD_TenKhach.clear();
			tfHD_CMND.clear();
			tfHD_DienThoai.clear();
			tfHD_Email.clear();
			tfHD_GhiChu.clear();
			dsHDPhong.clear();
			dsHDPtck.clear();
		}
	}

	public void handleChotThongTin(ActionEvent e) {
		if (btnHD_ThanhToan.isDisable()) {
			if (!(tfHD_TenKhach.getText().matches("^([^0-9]{1,30})$") && tfHD_CMND.getText().matches("^[0-9]{1,15}$")
					&& tfHD_DienThoai.getText().matches("^[0-9]{1,15}$") && tfHD_Email.getText().matches("^.{1,45}$")
					&& tfHD_GhiChu.getText().matches("^.{0,45}$"))) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại!");
				alert.setHeaderText("Chốt thông tin khách thất bại!");
				alert.setContentText("- Tên khách tối đa 30 kí tự và không chứa số.\n"
						+ "- CMND tối đa 15 kí tự và chỉ chứa số.\n" + "- Điện thoại tối đa 15 kí tự và chỉ chứa số.\n"
						+ "- Email tối đa 45 kí tự.\n" + "- Ghi chú tối đa 45 kí tự");
				alert.showAndWait();
				return;
			}
			btnHD_ChotThongTin.setText("🔒 SỬA THÔNG TIN");
		} else {
			btnHD_ChotThongTin.setText("🔒 CHỐT THÔNG TIN");
		}
		dpHD_NgayLap.setDisable(!dpHD_NgayLap.isDisable());
		tfHD_TenKhach.setDisable(!tfHD_TenKhach.isDisable());
		tfHD_CMND.setDisable(!tfHD_CMND.isDisable());
		tfHD_DienThoai.setDisable(!tfHD_DienThoai.isDisable());
		tfHD_Email.setDisable(!tfHD_Email.isDisable());
		tfHD_GhiChu.setDisable(!tfHD_GhiChu.isDisable());
		btnHD_ThanhToan.setDisable(!btnHD_ThanhToan.isDisable());
	}

	public void handleXemHoaDon(ActionEvent e) {
		try {
			HoaDonDTO hoaDon = tvHoaDon.getSelectionModel().getSelectedItem();
			String link = "/application/popupHoaDon.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 1180, 750);
			popUpStage.getScene().setUserData(this);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			HoaDonController controller = loader.getController();
			controller.initialize(hoaDon);
			popUpStage.showAndWait();
		} catch (NullPointerException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Không thể xem thông tin hoá đơn!");
			alert.setContentText("Vui lòng chọn hoá đơn cần xem!");
			alert.showAndWait();
		}
	}

	public void handleDangXuat(ActionEvent e) {
		Window window = (Stage) lbPhong_MaPhong.getScene().getWindow();
		window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public void handleInThongKeDoanhThu(ActionEvent e) {
		try {
			List<ArrayList<String>> CTBC = new ArrayList<ArrayList<String>>();
			String[] firstRowValue = { "Tháng", "Doanh thu (VND)", "Tỉ lệ (%)" };
			CTBC.add(new ArrayList<String>(Arrays.asList(firstRowValue)));

			Integer tongDoanhThu = ThongKeBUS.getDoanhThuTheoNam(snTK_DoanhThu.getValue()).stream()
					.mapToInt(ThongKeDoanhThuDTO::getDoanhThu).sum();

			ThongKeBUS.getDoanhThuTheoNam(snTK_DoanhThu.getValue()).stream().forEach(bc -> {
				String[] rowValue = { bc.getThang().toString(), MoneyFormatHelper.format(bc.getDoanhThu()),
						String.format("%.2f", bc.getTiLe()) };
				CTBC.add(new ArrayList<String>(Arrays.asList(rowValue)));
			});

			PDFCreateHelper.createDoanhThuNamPDF(snTK_DoanhThu.getValue(), CTBC, tongDoanhThu);
		} catch (Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Không thể xuất báo cáo!");
			alert.setContentText("Lỗi database hoặc link file!");
			alert.showAndWait();
		}
	}

	public void handleInThongKeLuongKhach(ActionEvent e) {
		try {
			List<ArrayList<String>> CTBC = new ArrayList<ArrayList<String>>();
			String[] firstRowValue = { "Tháng", "Số khách", "Tỉ lệ (%)" };
			CTBC.add(new ArrayList<String>(Arrays.asList(firstRowValue)));

			Integer tongSoKhach = ThongKeBUS.getSoKhachTheoNam(snTK_DoanhThu.getValue()).stream()
					.mapToInt(ThongKeSoKhachDTO::getSoKhach).sum();

			ThongKeBUS.getSoKhachTheoNam(snTK_DoanhThu.getValue()).stream().forEach(bc -> {
				String[] rowValue = { bc.getThang().toString(), bc.getSoKhach().toString(),
						String.format("%.2f", bc.getTiLe()) };
				CTBC.add(new ArrayList<String>(Arrays.asList(rowValue)));
			});

			PDFCreateHelper.createSoKhachNamPDF(snTK_DoanhThu.getValue(), CTBC, tongSoKhach);
		} catch (Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Không thể xuất báo cáo!");
			alert.setContentText("Lỗi database hoặc link file!");
			alert.showAndWait();
		}
	}

	public void handleInThongKeLoaiPhong(ActionEvent e) {
		try {
			List<ArrayList<String>> CTBC = new ArrayList<ArrayList<String>>();
			String[] firstRowValue = { "Loại phòng", "Doanh thu (VND)", "Tỉ lệ (%)" };
			CTBC.add(new ArrayList<String>(Arrays.asList(firstRowValue)));

			int thang = snTK_LoaiPhongThang.getValue();
			int nam = snTK_LoaiPhongNam.getValue();

			Integer tongDoanhThu = ThongKeBUS.getLoaiPhongTheoThang(thang, nam).stream()
					.mapToInt(ThongKeLoaiPhongDTO::getDoanhThu).sum();

			ThongKeBUS.getLoaiPhongTheoThang(thang, nam).forEach(bc -> {
				String[] rowValue = { bc.getLoaiPhong(), MoneyFormatHelper.format(bc.getDoanhThu()),
						String.format("%.2f", bc.getTiLe()) };
				CTBC.add(new ArrayList<String>(Arrays.asList(rowValue)));
			});

			PDFCreateHelper.createLoaiPhongThangPDF(thang, nam, CTBC, tongDoanhThu);
		} catch (Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Không thể xuất báo cáo!");
			alert.setContentText("Lỗi database hoặc link file!");
			alert.showAndWait();
		}
	}

	public void handleInThongKeLoaiDichVu(ActionEvent e) {
		try {
			List<ArrayList<String>> CTBC = new ArrayList<ArrayList<String>>();
			String[] firstRowValue = { "Loại dịch vụ", "Doanh thu (VND)", "Tỉ lệ (%)" };
			CTBC.add(new ArrayList<String>(Arrays.asList(firstRowValue)));

			int thang = snTK_LoaiDichVuThang.getValue();
			int nam = snTK_LoaiDichVuNam.getValue();

			Integer tongDoanhThu = ThongKeBUS.getLoaiDichVuTheoThang(thang, nam).stream()
					.mapToInt(ThongKeLoaiDichVuDTO::getDoanhThu).sum();

			ThongKeBUS.getLoaiDichVuTheoThang(thang, nam).forEach(bc -> {
				String[] rowValue = { bc.getTenLoaiDichVu(), MoneyFormatHelper.format(bc.getDoanhThu()),
						String.format("%.2f", bc.getTiLe()) };
				CTBC.add(new ArrayList<String>(Arrays.asList(rowValue)));
			});

			PDFCreateHelper.createLoaiDichVuPDF(thang, nam, CTBC, tongDoanhThu);
		} catch (Exception ex) {
			ex.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Không thể xuất báo cáo!");
			alert.setContentText("Lỗi database hoặc link file!");
			alert.showAndWait();
		}
	}
}