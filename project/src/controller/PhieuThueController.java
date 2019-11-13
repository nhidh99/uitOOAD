package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import BUS.NhanVienBUS;
import BUS.PTPhongBUS;
import BUS.PhieuThueBUS;
import DTO.PTPhongDTO;
import DTO.PhieuThueDTO;
import helper.MoneyFormatHelper;
import helper.PDFCreateHelper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PhieuThueController {

	@FXML
	Label lbPT_TieuDe;
	@FXML
	Label lbPT_TenNhanVien;
	@FXML
	Label lbPT_SoLuongPhong;
	@FXML
	Label lbPT_TongCoc;
	@FXML
	Label lbPT_NgayLap;

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
	TextField tfPT_KhachThue;
	@FXML
	TextField tfPT_CMND;
	@FXML
	TextField tfPT_DienThoai;
	@FXML
	TextField tfPT_Email;
	@FXML
	TextField tfPT_GhiChu;

	PhieuThueDTO phieuThue;

	public void initialize(Integer maPhieuThue) {
		try {
			phieuThue = PhieuThueBUS.getPhieuThueById(maPhieuThue);
			lbPT_TenNhanVien.setText(NhanVienBUS.getNhanVienById(phieuThue.getMaNhanVien()).getTenNhanVien());
			lbPT_NgayLap.setText(phieuThue.getNgayLap());
			tfPT_DienThoai.setText(phieuThue.getSoDienThoai());
			tfPT_KhachThue.setText(phieuThue.getTenKhachThue());
			tfPT_CMND.setText(phieuThue.getCMND());
			tfPT_Email.setText(phieuThue.getEmail());
			tfPT_GhiChu.setText(phieuThue.getGhiChu());

			initTablePTPhong();
			loadTablePTPhong();

		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể xem chi tiết phiếu thuê!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
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

	private void loadTablePTPhong() throws SQLException {
		ObservableList<PTPhongDTO> dsPTPhong = FXCollections.observableArrayList();
		for (PTPhongDTO ptp : PTPhongBUS.getDSPTPhongByMaPhieu(phieuThue.getMaPhieuThue())) {
			dsPTPhong.add(ptp);
		}
		tvPTPhong.setItems(dsPTPhong);
		lbPT_SoLuongPhong.setText(String.format("%d phòng (%d khách)", tvPTPhong.getItems().size(),
				tvPTPhong.getItems().stream().mapToInt(PTPhongDTO::getSoKhachToiDa).sum()));
		lbPT_TongCoc.setText(MoneyFormatHelper
				.format(tvPTPhong.getItems().stream().mapToInt(PTPhongDTO::getTienCocValue).sum(), "VND"));
	}

	public void handleCapNhatPhieuThue(ActionEvent e) {
		try {
			PhieuThueDTO newPhieuThue = new PhieuThueDTO(phieuThue.getMaPhieuThue(), null, null,
					tfPT_KhachThue.getText(), tfPT_CMND.getText(), tfPT_DienThoai.getText(), tfPT_Email.getText(), 
					null, null, tfPT_GhiChu.getText());
			
			if (PhieuThueBUS.updatePhieuThue(newPhieuThue)) {
				loadTablePTPhong();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Cập nhật thông tin phiếu thuê thành công");
				alert.showAndWait();

				Scene scene = lbPT_NgayLap.getScene();
				Runnable reloadTablePhieuThue = (Runnable) scene.getUserData();
				reloadTablePhieuThue.run();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thất bại");
				alert.setHeaderText("Cập nhật thông tin phiếu thuê thất bại");
				alert.showAndWait();
			}
		} catch (SQLException SQLException) {
			SQLException.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Cập nhật thông tin phiếu thuê thất bại");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	public void handleInPhieuThue(ActionEvent e) throws IOException {
		List<ArrayList<String>> CTPT = new ArrayList<ArrayList<String>>();
		String[] firstRowValue = {"Mã phòng", "Loại phòng", "Tiền cọc (VND)", "Nhận phòng", "Trả phòng"};
		CTPT.add(new ArrayList<String>(Arrays.asList(firstRowValue)));
		for(PTPhongDTO ptp : tvPTPhong.getItems()) {
			String[] rowValue = {ptp.getMaPhong(), ptp.getLoaiPhongThue(), ptp.getTienCoc(), ptp.getNgayNhan(), ptp.getNgayTra()};
			CTPT.add(new ArrayList<String>(Arrays.asList(rowValue)));
		}
		
		List<String> thongTinPhieu = new ArrayList<String>();
		String[] value = {
				phieuThue.getMaPhieuThue().toString(), 
				tfPT_KhachThue.getText(),
				tfPT_CMND.getText(), 
				tfPT_Email.getText(),
				lbPT_SoLuongPhong.getText(),
				lbPT_TenNhanVien.getText(),
				lbPT_NgayLap.getText(),
				tfPT_DienThoai.getText(),
				lbPT_TongCoc.getText(),
				(tfPT_GhiChu.getText() == null) ? "Không có" : tfPT_GhiChu.getText()
		};
		thongTinPhieu.addAll(new ArrayList<String> (Arrays.asList(value)));
		PDFCreateHelper.createPhieuThuePDF(thongTinPhieu, CTPT);
	}
	
	public void handleDong() {
		Stage stage = (Stage) lbPT_NgayLap.getScene().getWindow();
		stage.close();
	}
}
