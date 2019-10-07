package controller;


import java.net.URL;
import java.util.ResourceBundle;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import application.Main;


public class LoginController extends AnchorPane implements Initializable {
	private Main app;
	boolean isSuccess = false;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnCancel;
	@FXML 
	private TextField tfUsername;
	@FXML
	private PasswordField tfPassword;
	
	
	@FXML 
	public void btnHandleLogin() throws Exception {
		NhanVienDTO nhanVien = NhanVienBUS.DangNhap(tfUsername.getText(), tfPassword.getText());
		if(nhanVien != null) {
			app.setNhanVien(nhanVien);
		}
		else {
			System.out.println("Đăng nhập thất bại");
		}
	}
	

	public void setApp(Main app) {
		this.app = app;
	}
	
	@FXML 
	public void btnHandleCancel() {
		tfUsername.clear();
		tfPassword.clear();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfUsername.setPromptText("username");
		tfPassword.setPromptText("password");
	}
}
