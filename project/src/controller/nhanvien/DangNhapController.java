package controller.nhanvien;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.NhanVienBUS;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import controller.MainController;
import helper.PopUpStageHelper;

public class DangNhapController implements Initializable {
	
	private Stage mainStage;
	private MainController mainController;
	
	@FXML 
	private TextField tfDN_TaiKhoan;
	@FXML
	private PasswordField pfDN_MatKhau;
	
	
	public void handleDangNhap() {
		String username = tfDN_TaiKhoan.getText();
		String password = pfDN_MatKhau.getText();

		try {
			if (NhanVienBUS.checkLoginNhanVien(username, password)) {
				Stage loginStage = (Stage) tfDN_TaiKhoan.getScene().getWindow();
				loginStage.close();
				mainController.loadNhanVienByUsername(username);
				mainStage.showAndWait();
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể đăng nhập!");
				alert.setContentText("Sai tên tài khoản hoặc mật khẩu!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể đăng nhập!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}
		
	public void handleThoat() {
		System.exit(0);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfDN_TaiKhoan.setPromptText("username");
		pfDN_MatKhau.setPromptText("password");
		mainStage = PopUpStageHelper.createPopUpStage("/application/main.fxml", 1280, 800);
		FXMLLoader loader = (FXMLLoader) mainStage.getUserData();
		mainController = loader.getController();
		
	}
}