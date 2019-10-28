package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.KhachBUS;
import BUS.PTPhongBUS;
import BUS.PhieuThueBUS;
import BUS.PtpDichVuBUS;
import BUS.PtpPtckBUS;
import DTO.KhachDTO;
import DTO.PTPhongDTO;
import DTO.PhieuThueDTO;
import DTO.PtpDichVuDTO;
import DTO.PtpPtckDTO;
import helper.MoneyFormatHelper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class CTHDController implements Initializable {
	
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
	Label lbPhong_LoaiPhong;
	@FXML
	Label lbPhong_MaPTP;
	@FXML
	Label lbPhong_TienDichVu;
	@FXML
	Label lbPhong_TienPTCK;
	
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTableKhach();
		initTableDichVu();
		initTablePtck();
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
	
	private void initTableDichVu() {
		tcPtpDV_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvPtpDichVu.getItems().indexOf(column.getValue()) + 1));
		tcPtpDV_MaPTPDV.setCellValueFactory(new PropertyValueFactory<>("maPTPDichVu"));
		tcPtpDV_TenDichVu.setCellValueFactory(new PropertyValueFactory<>("tenDichVu"));
		tcPtpDV_SoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
		tcPtpDV_DonViTinh.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		tcPtpDV_DonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
		tcPtpDV_ThanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
	}

	private void initTablePtck() {
		tcPtpPTCK_STT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvPtpPTCK.getItems().indexOf(column.getValue()) + 1));
		tcPtpPTCK_MaPTCK.setCellValueFactory(new PropertyValueFactory<>("MaPTCKPhong"));
		tcPtpPTCK_NoiDung.setCellValueFactory(new PropertyValueFactory<>("NoiDung"));
		tcPtpPTCK_SoLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
		tcPtpPTCK_DonGia.setCellValueFactory(new PropertyValueFactory<>("DonGia"));
		tcPtpPTCK_ThanhTien.setCellValueFactory(new PropertyValueFactory<>("TriGia"));
	}
	
	public void initialize(Integer maPTP) {
		loadTableKhach(maPTP);
		loadTableDichVu(maPTP);
		loadTablePtck(maPTP);
		loadTableChiTiet(maPTP);
	}
	
	private void loadTableKhach(Integer maPTP) {
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
	
	private void loadTableDichVu(Integer maPTP) {
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
	
	private void loadTablePtck(Integer maPTP) {
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
	
	private void loadTableChiTiet(Integer maPTP) {
		try {
			PTPhongDTO ptPhong = PTPhongBUS.getPTPhongById(maPTP);
			PhieuThueDTO phieuThue = PhieuThueBUS.getPhieuThueByMaPTP(maPTP);
			lbPhong_KhachThue.setText(phieuThue.getTenKhachThue());
			lbPhong_MaPhong.setText(ptPhong.getPhong().getMaPhong());
			lbPhong_MaPTP.setText(maPTP.toString());
			lbPhong_LoaiPhong.setText(ptPhong.getPhong().getLoaiPhong().getTenLoaiPhong());
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
}
