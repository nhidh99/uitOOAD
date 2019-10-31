package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import BUS.HDPtckBUS;
import BUS.PTPhongBUS;
import DTO.HDPtckDTO;
import DTO.HoaDonDTO;
import DTO.PTPhongDTO;
import helper.PDFCreateHelper;
import helper.PopUpStageHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;

public class HoaDonController implements Initializable {

	@FXML
	Label lbHD_NgayLap;
	@FXML
	Label lbHD_TenNhanVien;
	@FXML
	Label lbHD_SoLuong;
	@FXML
	Label lbHD_KhachTra;
	@FXML
	Label lbHD_DienThoai;
	@FXML
	Label lbHD_CMND;
	@FXML
	Label lbHD_Email;
	@FXML
	Label lbHD_GhiChu;

	@FXML
	TableView<PTPhongDTO> tvHDPhong;
	@FXML
	TableColumn<PTPhongDTO, String> tcHD_SoPhong;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcHD_MaPTP;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcHD_TienCoc;
	@FXML
	TableColumn<PTPhongDTO, Integer> tcHD_ThanhTienValue;
	@FXML
	TableColumn<PTPhongDTO, String> tcHD_ThanhTien;

	@FXML
	TableView<HDPtckDTO> tvHDPtck;
	@FXML
	TableColumn<HDPtckDTO, String> tcHDPtck_NoiDung;
	@FXML
	TableColumn<HDPtckDTO, String> tcHDPtck_TriGia;
	@FXML
	TableColumn<HDPtckDTO, Integer> tcHDPtck_TriGiaValue;

	@FXML
	Label lbHD_TienPhong;
	@FXML
	Label lbHD_TienCoc;
	@FXML
	Label lbHD_TienPtck;
	@FXML
	Label lbHD_TamTinh;
	@FXML
	Label lbHD_TienNhan;
	@FXML
	Label lbHD_TienThua;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcHD_MaPTP.setCellValueFactory(new PropertyValueFactory<>("MaPTPhong"));
		tcHD_SoPhong.setCellValueFactory(new PropertyValueFactory<>("MaPhong"));
		tcHD_TienCoc.setCellValueFactory(new PropertyValueFactory<>("TienCoc"));
		tcHD_ThanhTien.setCellValueFactory(new PropertyValueFactory<>("ThanhTien"));
		tcHD_ThanhTienValue.setCellValueFactory(new PropertyValueFactory<>("ThanhTienValue"));

		tcHDPtck_NoiDung.setCellValueFactory(new PropertyValueFactory<>("noiDung"));
		tcHDPtck_TriGia.setCellValueFactory(new PropertyValueFactory<>("triGia"));
	}

	public void initialize(HoaDonDTO hoaDon) {
		initLabels(hoaDon);
		initTables(hoaDon);
	}

	private void initTables(HoaDonDTO hoaDon) {
		try {
			ObservableList<PTPhongDTO> dsHDPhong = FXCollections.observableArrayList();
			ObservableList<HDPtckDTO> dsHDPtck = FXCollections.observableArrayList();
			PTPhongBUS.getDSPTPhongByMaHD(hoaDon.getMaHoaDon()).stream().forEach(ptp -> dsHDPhong.add(ptp));
			HDPtckBUS.getDSPtckByMaHD(hoaDon.getMaHoaDon()).stream().forEach(ptck -> dsHDPtck.add(ptck));
			tvHDPhong.setItems(dsHDPhong);
			tvHDPtck.setItems(dsHDPtck);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void initLabels(HoaDonDTO hoaDon) {
		lbHD_SoLuong.setText(hoaDon.getMaHoaDon().toString());
		lbHD_TenNhanVien.setText(hoaDon.getTenNhanVien());
		lbHD_KhachTra.setText(hoaDon.getTenKhach());
		lbHD_DienThoai.setText(hoaDon.getDienThoai());
		lbHD_NgayLap.setText(hoaDon.getNgayHoaDon());
		lbHD_CMND.setText(hoaDon.getCMND());
		lbHD_Email.setText(hoaDon.getEmail());
		lbHD_GhiChu.setText(hoaDon.getGhiChu());

		lbHD_TienPhong.setText(hoaDon.getTongTienPhong());
		lbHD_TienCoc.setText(hoaDon.getTongTienCoc());
		lbHD_TienPtck.setText(hoaDon.getTongTienPTCK());
		lbHD_TamTinh.setText(hoaDon.getGiaTri());
		lbHD_TienNhan.setText(hoaDon.getTienNhan());
		lbHD_TienThua.setText(hoaDon.getTienThua());
	}

	public void handleXemChiTiet(ActionEvent e) {
		try {
			String link = "/application/popupChiTietHD.fxml";
			Stage popUpStage = PopUpStageHelper.createPopUpStage(link, 800, 475);
			FXMLLoader loader = (FXMLLoader) popUpStage.getUserData();
			CTHDController controller = loader.getController();
			PTPhongDTO ptPhong = tvHDPhong.getSelectionModel().getSelectedItem();
			Integer maPTP = ptPhong.getMaPTPhong();
			String maPhong = ptPhong.getMaPhong();
			controller.initialize(maPTP);
			popUpStage.setTitle("CHI TIẾT THUÊ PHÒNG " + maPhong);
			popUpStage.showAndWait();
		} catch (NullPointerException nex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Xem chi tiết thuê phòng thất bại!");
			alert.setContentText("Vui lòng chọn phòng cần xem!");
			alert.showAndWait();
		}
	}
	
	public void handleInHoaDon(ActionEvent e) throws IOException {
		List<String> thongTinHoaDon = new ArrayList<String>();
		String[] value = {
				lbHD_KhachTra.getText(), 
				lbHD_DienThoai.getText(),
				lbHD_CMND.getText(), 
				lbHD_Email.getText(),
				lbHD_SoLuong.getText(),
				lbHD_NgayLap.getText(),
				lbHD_TenNhanVien.getText(),
				lbHD_GhiChu.getText() == null ? "Không có" : lbHD_GhiChu.getText(),
				lbHD_TienNhan.getText(),
				lbHD_TamTinh.getText(),
				lbHD_TienThua.getText(),
				lbHD_TienCoc.getText()
		};
		thongTinHoaDon.addAll(new ArrayList<String> (Arrays.asList(value)));
		List<PTPhongDTO> listPhong = new ArrayList<PTPhongDTO>();
		for(PTPhongDTO ptp : tvHDPhong.getItems()) {
			listPhong.add(ptp);
		}
		
		List<HDPtckDTO> listPtck = new ArrayList<HDPtckDTO>();
		for(HDPtckDTO ptck : tvHDPtck.getItems()) {
			listPtck.add(ptck);
		}
		PDFCreateHelper.createHoaDonPDF(listPhong, listPtck, thongTinHoaDon);
	}
}
