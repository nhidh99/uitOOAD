package controller.phieuthue;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.DichVuBUS;
import BUS.PTP_DichVuBUS;
import BUS.PhieuThuePhongBUS;
import DTO.DichVuDTO;
import DTO.PTP_DichVuDTO;
import controller.MainController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;
public class ThemDichVuController implements Initializable {

	@FXML private Label lbPhieuThue;
	@FXML private ComboBox<DichVuDTO> cbbDichVu;
	@FXML private Spinner<Integer> snSoLuong;
	@FXML private Label lbSoLuongTon;
	@FXML private TextField tfDonGia;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadCbbDichVu();
		cbbDichVu.getSelectionModel().selectFirst();
		DichVuDTO dichVu = cbbDichVu.getSelectionModel().getSelectedItem();
		if(dichVu.getSoLuongTon() == null) {
			lbSoLuongTon.setText("Không tồn kho");
			snSoLuong.setDisable(true);
		}
		else {
			lbSoLuongTon.setText(dichVu.getSoLuongTon() + " " + dichVu.getDonViTinh());
			snSoLuong.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, dichVu.getSoLuongTon(), 1));
			snSoLuong.setEditable(true);
		}
		tfDonGia.setText(dichVu.getDonGiaValue().toString());
		//Khi chọn dịch vụ khác trong ComboBox
		cbbDichVu.valueProperty().addListener(new ChangeListener<DichVuDTO>() {
	        public void changed(ObservableValue<? extends DichVuDTO> DichVu, DichVuDTO dichVuCu, DichVuDTO dichVuMoi) {
	        	if(dichVuMoi.getSoLuongTon() == null) {
	        		lbSoLuongTon.setText("Không tồn kho");
	        		snSoLuong.setDisable(true);
	        	}
	        	else
	        	{
	        		lbSoLuongTon.setText(dichVuMoi.getSoLuongTon() + " " + dichVuMoi.getDonViTinh());
	        		snSoLuong.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, dichVuMoi.getSoLuongTon(), 1));
	    			snSoLuong.setEditable(true);
	        	}
	        	tfDonGia.setText(dichVuMoi.getDonGiaValue().toString());
	        }    
	    });
		
	}
	
	public void setMaPTPhong(Integer maPhieuThue) {
		lbPhieuThue.setText(maPhieuThue.toString());
	}
	
	public void handleThemDichVu() throws NumberFormatException, SQLException {
		PTP_DichVuDTO ptp_dichVu =  new PTP_DichVuDTO(
				Integer.parseInt(lbPhieuThue.getText()),
				cbbDichVu.getSelectionModel().getSelectedItem(),
				snSoLuong.isDisable() ? -1 : snSoLuong.getValue(),
				Integer.parseInt(tfDonGia.getText()),
				snSoLuong.getValue() * Integer.parseInt(tfDonGia.getText())
				);
		
		if(PTP_DichVuBUS.insertPTP_DichVu(ptp_dichVu)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thành công");
			alert.setHeaderText("Thêm dịch vụ thành công!");
			alert.setContentText(String.format("Thêm thành công dịch vụ %s, số lượng sử dụng: %d.", ptp_dichVu.getDichVu().getTenDichVu(), ptp_dichVu.getSoLuong()));
			alert.showAndWait();
			
			MainController mainController = (MainController) lbSoLuongTon.getScene().getUserData();
			mainController.loadTablePTPDichVuByMaPT(PhieuThuePhongBUS.getPTPhongByMaPhong(Integer.parseInt(mainController.getPhongDangChon().getMaPhong())).getMaPTPhong());
			Stage stage = (Stage) lbSoLuongTon.getScene().getWindow();
			stage.close();
		}
		else {
			//Đã tồn tại dịch vụ trong danh sách hoặc hết hàng tồn: Update lại số lượng
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thất bại");
			alert.setHeaderText("Thêm dịch vụ thất bại!");
			alert.setContentText("Dịch vụ đã tồn tại hoặc hết hàng!");
			alert.showAndWait();
		}
		
	}
	
	public void handleHuyThemDichVu() {
		Stage stage = (Stage) lbSoLuongTon.getScene().getWindow();
		stage.close();
	}
	
	public void loadCbbDichVu() {
        try {
            ObservableList<DichVuDTO> dsDichVu = FXCollections.observableArrayList();
            for (DichVuDTO dichVu : DichVuBUS.getDSDichVu()) {
                dsDichVu.add(dichVu);
            }
 
            Callback<ListView<DichVuDTO>, ListCell<DichVuDTO>> cellFactory =
                    new Callback<ListView<DichVuDTO>, ListCell<DichVuDTO>>() {
                @Override
                public ListCell<DichVuDTO> call(ListView<DichVuDTO> lvDichVu) {
                    final ListCell<DichVuDTO> lcDichVu = new ListCell<DichVuDTO>() {
                        @Override
                        protected void updateItem(DichVuDTO dichVu, boolean empty) {
                            super.updateItem(dichVu, empty);
                            if (dichVu != null) {
                                this.setText(dichVu.getTenDichVu());
                            } else
                                this.setText(null);
                        }
                    };
                    return lcDichVu;
                }
            };
           
            cbbDichVu.setButtonCell(cellFactory.call(null));
            cbbDichVu.setCellFactory(cellFactory);
            cbbDichVu.setItems(dsDichVu);
        } catch (SQLException e) {
            // do nothing :)
        }
    }
}
