package controller.timkiem;

import java.sql.SQLException;
import java.util.List;

import BUS.KhachBUS;
import BUS.PhongBUS;
import DTO.KhachDTO;
import DTO.PhongDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TimKiemKhachController{
	@FXML
	private TextField tfTenKhach;
	@FXML
	private TextField tfCMND;

	
	public void handleTimKiem(ActionEvent e) {
		
		String hoten = "null" , cmnd = "null" ;
		
		if(tfCMND.getText()=="")
		{
			hoten=tfTenKhach.getText();
		}
		else if(tfTenKhach.getText()=="")
		{
			cmnd=tfCMND.getText();
		}
		else
		{
			hoten = tfTenKhach.getText();
			cmnd = tfCMND.getText();
		}
		
		try {
			List<KhachDTO> dsKhach = KhachBUS.searchKhach(hoten,cmnd);
			if(!dsKhach.isEmpty())
			{
			Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Thông tin khách hàng tìm được: ");
		        alert.setHeaderText("Kết quả: ");
		        String result = "";
		        for(KhachDTO kh : dsKhach) {
		        	result = result + String.format("Họ tên: %s, CMND: %s, Mã phòng: %d\n", kh.getHoten(), kh.getCMND(), kh.getMaPTPhong()); 
		        }
		        alert.setContentText(result);
		        alert.showAndWait();
		    }
			else
			{
				Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Thất bại : ");
		        alert.setHeaderText("Tìm kiếm thất bại : ");
		        alert.setContentText("Không có khách có thông tin như trên.");
		        alert.showAndWait();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	
	public void handleHuy(ActionEvent e) {
		Stage stage = (Stage)tfTenKhach.getScene().getWindow();
		stage.close();
	}
}