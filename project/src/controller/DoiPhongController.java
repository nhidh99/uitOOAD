package controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import BUS.LoaiPhongBUS;
import BUS.PTPhongBUS;
import BUS.PhongBUS;
import DTO.LoaiPhongDTO;
import DTO.PTPhongDTO;
import DTO.PhongDTO;
import helper.DateFormatHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;

public class DoiPhongController {

	@FXML private DatePicker dpDP_NgayDoiPhong;
	@FXML private ComboBox<PhongDTO> cbbDP_PhongDoi;
	@FXML private Label lbDP_MaPhong;
	
	private PTPhongDTO ptPhongHienTai = null;
	public void initialize(PTPhongDTO ptPhongHienTai) {
		this.ptPhongHienTai = ptPhongHienTai;
		lbDP_MaPhong.setText(ptPhongHienTai.getMaPhong());
		initDatePicker();
		initCbbDoiPhong();
	}
	private void initDatePicker() {
		dpDP_NgayDoiPhong.setConverter(DateFormatHelper.getDatePickerFormatter());
		dpDP_NgayDoiPhong.setValue(LocalDate.now());
		
		dpDP_NgayDoiPhong.valueProperty().addListener((obs, oldValue, newValue) -> {
			if(ptPhongHienTai != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateFormatHelper.getDate(dpDP_NgayDoiPhong.getValue()));
				Timestamp ngayDoiPhong = new Timestamp(cal.getTimeInMillis());
				Timestamp ngayTra = ptPhongHienTai.getNgayTraValue();
				
				try {
					List<LoaiPhongDTO> dsLoaiPhong = LoaiPhongBUS.getDSLoaiPhong();
					ObservableList<PhongDTO> dsPhong = FXCollections.observableArrayList();
					for(LoaiPhongDTO loaiPhong : dsLoaiPhong) {
						for (PhongDTO phong : PhongBUS.getDSPhongCoTheThue(ngayDoiPhong, ngayTra, loaiPhong.getMaLoaiPhong())) {
							dsPhong.add(phong);
						}
					}
					if(dsPhong.size() < 0) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Thông báo");
						alert.setHeaderText("Không tìm được phòng còn trống!");
						alert.setContentText("Đã hết phòng trống!");
						alert.showAndWait();
					}
					else {
						cbbDP_PhongDoi.setItems(dsPhong);
					}
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Lỗi");
					alert.setHeaderText("Không thể tải danh sách phòng khách sạn!");
					alert.setContentText("Lỗi database!");
					alert.showAndWait();
					e.printStackTrace();
				}
			}
		});
	}
	
	public void initCbbDoiPhong() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateFormatHelper.getDate(dpDP_NgayDoiPhong.getValue()));
		Timestamp ngayDoiPhong = new Timestamp(cal.getTimeInMillis());
		Timestamp ngayTra = ptPhongHienTai.getNgayTraValue();
		
		ObservableList<PhongDTO> dsPhong = FXCollections.observableArrayList();
		try {
			for(LoaiPhongDTO loaiPhong :LoaiPhongBUS.getDSLoaiPhong()) {
				for (PhongDTO phong : PhongBUS.getDSPhongCoTheThue(ngayDoiPhong, ngayTra, loaiPhong.getMaLoaiPhong())) {
					dsPhong.add(phong);
				}
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể tải danh sách phòng khách sạn!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
			e.printStackTrace();
		}
		
		Callback<ListView<PhongDTO>, ListCell<PhongDTO>> cellFactory = new Callback<ListView<PhongDTO>, ListCell<PhongDTO>>() {
			@Override
			public ListCell<PhongDTO> call(ListView<PhongDTO> lvPhong) {
				final ListCell<PhongDTO> lcPhong = new ListCell<PhongDTO>() {
					@Override
					protected void updateItem(PhongDTO phong, boolean empty) {
						super.updateItem(phong, empty);
						if (phong != null) {
							this.setText(phong.getMaPhong());
						} else
							this.setText(null);
					}
				};
				return lcPhong;
			}
		};
		cbbDP_PhongDoi.setItems(dsPhong);
		cbbDP_PhongDoi.getSelectionModel().selectFirst();
		
		cbbDP_PhongDoi.setButtonCell(cellFactory.call(null));
		cbbDP_PhongDoi.setCellFactory(cellFactory);
	}
	
	public void handleXacNhan(ActionEvent e) {
		try {
			if (PTPhongBUS.doiPhong(ptPhongHienTai.getMaPTPhong(), Integer.parseInt(cbbDP_PhongDoi.getSelectionModel().getSelectedItem().getMaPhong()))) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thành công");
				alert.setHeaderText("Đổi phòng thành công!");
				alert.setContentText(String.format("Đã đổi từ phòng %s sang phòng %s", lbDP_MaPhong.getText(), cbbDP_PhongDoi.getSelectionModel().getSelectedItem().getMaPhong()));
				alert.showAndWait();

				MainController mainController = (MainController) lbDP_MaPhong.getScene().getUserData();
				mainController.loadTablePhong();
				Stage stage = (Stage) lbDP_MaPhong.getScene().getWindow();
				stage.close();
			}

		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể sửa thông tin dịch vụ!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}
	
	public void handleHuyBo(ActionEvent e) {
		Stage stage = (Stage)lbDP_MaPhong.getScene().getWindow();
		stage.close();
	}
}
