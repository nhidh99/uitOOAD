package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.*;
import DTO.*;
import application.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {
	@FXML TableView <NhanVienDTO> tvNhanVien;
	@FXML TableColumn<NhanVienDTO, Integer> tcNV_STT;
	@FXML TableColumn<NhanVienDTO, Integer> tcNV_MaNhanVien;
	@FXML TableColumn<NhanVienDTO, String> tcNV_TenNhanVien;
	@FXML TableColumn<NhanVienDTO, String> tcNV_CMND;
	@FXML TableColumn<NhanVienDTO, String> tcNV_SoDienThoai;
	@FXML TableColumn<NhanVienDTO, String> tcNV_DiaChi;
	@FXML TableColumn<NhanVienDTO, String> tcNV_Email;
	@FXML TableColumn<NhanVienDTO, String> tcNV_ChucVu;
	
	@FXML Label lbTS_SoNgayTraCoc;
	@FXML Label lbTS_TiLeThueVAT;
	@FXML Label lbTS_TiLeTienCoc;
	@FXML Label lbTS_QuaKhach;
	@FXML Label lbTS_TraPhongTre;
	
	@FXML TableView <LoaiDichVuDTO> tvLoaiDichVu;
	@FXML TableColumn<LoaiDichVuDTO, Integer> tcLDV_STT;
	@FXML TableColumn<LoaiDichVuDTO, Integer> tcLDV_MaLoaiDV;
	@FXML TableColumn<LoaiDichVuDTO, String> tcLDV_TenLoaiDV;
	
	@FXML TableView <NhaCungCapDTO> tvNhaCungCap;
	@FXML TableColumn<NhaCungCapDTO, Integer> tcNCC_STT;
	@FXML TableColumn<NhaCungCapDTO, Integer> tcNCC_MaNhaCC;
	@FXML TableColumn<NhaCungCapDTO, String> tcNCC_TenNhaCC;
	@FXML TableColumn<NhaCungCapDTO, Integer> tcNCC_SoDienThoai;
	
	@FXML TableView <LoaiPhongDTO> tvLoaiPhong;
	@FXML TableColumn<LoaiPhongDTO, Integer> tcLP_STT;
	@FXML TableColumn<LoaiPhongDTO, Integer> tcLP_MaLoaiPhong;
	@FXML TableColumn<LoaiPhongDTO, String> tcLP_TenLoaiPhong;
	@FXML TableColumn<LoaiPhongDTO, Integer> tcLP_SoKhachToiDa;
	@FXML TableColumn<LoaiPhongDTO, Integer> tcLP_DonGia;
	
	//Thêm từ đây
	@FXML Label lbNV_MaNhanVien;
	@FXML Label lbNV_TenNhanVien;
	@FXML Label lbNV_CMND;
	@FXML Label lbNV_DiaChi;
	@FXML Label lbNV_Email;
	@FXML Label lbNV_SoDienThoai;
	@FXML Label lbNV_ChucVu;
	
	@FXML Tab tabNV_DanhMucTaiKhoan;
	@FXML Tab tabPhong;
	@FXML Tab tabTraCuu;
	@FXML Tab tabPhieuThue;
	@FXML Tab tabThietLap;
	@FXML Tab tabDichVu;
	@FXML Tab tabThongKe;
	
	@FXML TabPane tpNV_ThongTinTaiKhoan;
	@FXML TabPane tpMain;
	
	@FXML Button btnNV_DangXuat;
	@FXML Button btnPhong_Them;
	@FXML Button btnPhong_Xoa;
	@FXML Button btnDichVu_Them;
	@FXML Button btnDichVu_Xoa;
	@FXML Button btnDichVu_Sua;
	
	private Main app;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTables();
		loadTables();
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
	
	private void loadPrivileges() {
		loadTabQuanLiTaiKhoan();
		loadPhanQuyen();
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
		}
		catch (SQLException SQLException) {
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
		}
		catch (SQLException SQLException) {
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
		}
		catch (SQLException SQLException) {
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
		}
		catch (SQLException SQLException) {
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
		}
		catch (SQLException SQLException) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách tham số!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();			
		}
	}
	
	//Thêm từ đây
	public void setApp(Main app) {
		this.app = app;
		loadPrivileges();
	}
	
	public void loadTabQuanLiTaiKhoan() {
			NhanVienDTO nv = app.getNhanVien();
			lbNV_MaNhanVien.setText(nv.getMaNhanVien().toString());
			lbNV_TenNhanVien.setText(nv.getTenNhanVien());
			lbNV_CMND.setText(nv.getCMND());
			lbNV_DiaChi.setText(nv.getDiaChi());
			lbNV_Email.setText(nv.getEmail());
			lbNV_SoDienThoai.setText(nv.getSoDienThoai());
			lbNV_ChucVu.setText(nv.getChucVu());
	}
	
	public void loadPhanQuyen() {
		NhanVienDTO nv = app.getNhanVien();
		switch(nv.getChucVu()) 
		{
		case "Lễ tân":
		{
			tpNV_ThongTinTaiKhoan.getTabs().remove(tabNV_DanhMucTaiKhoan);
			tpMain.getTabs().removeAll(tabThietLap,tabThongKe);
			VBox vb = (VBox)btnPhong_Them.getParent();
			vb.getChildren().remove(btnPhong_Them);
			HBox hbPhong = (HBox)btnPhong_Xoa.getParent();
			hbPhong.getChildren().remove(btnPhong_Xoa);
			HBox hbDichVu = (HBox)btnDichVu_Them.getParent();
			hbDichVu.getChildren().removeAll(btnDichVu_Them, btnDichVu_Xoa);
			btnDichVu_Sua.setText("⚙ NHẬP HÀNG");
			break;
		}
		case "Kế toán":
		{
			tpNV_ThongTinTaiKhoan.getTabs().remove(tabNV_DanhMucTaiKhoan);
			tpMain.getTabs().removeAll(tabThietLap,tabPhong,tabPhieuThue, tabTraCuu);
			HBox hbDichVu = (HBox)btnDichVu_Them.getParent();
			hbDichVu.getChildren().removeAll(btnDichVu_Them, btnDichVu_Xoa);
			btnDichVu_Sua.setText("⚙ NHẬP HÀNG");
			break;
		}
		default:
			break;
		}
	}
	
	public void handleXoaNhanVien() {
		NhanVienDTO nv = tvNhanVien.getSelectionModel().getSelectedItem();
		if(nv!=null) {
		try {
			if(NhanVienBUS.deleteNhanVien(nv)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông báo");
				alert.setHeaderText("Đã xóa thành công!");
				alert.showAndWait();	
				loadTableNhanVien();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách tham số!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();	
			e.printStackTrace();
		}
		}
	} 
	public void btnNV_XuLy_DangXuat() throws Exception {
		app.logOut();
	}
}