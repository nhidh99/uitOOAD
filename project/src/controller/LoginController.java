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
	@FXML 
	private TextField tfDN_TenTaiKhoan;
	@FXML
	private PasswordField pfDN_MatKhau;
	
	
	public void handleDangNhap() throws Exception {
		NhanVienDTO nhanVien = NhanVienBUS.DangNhap(tfDN_TenTaiKhoan.getText(), pfDN_MatKhau.getText());
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
	
	public void handleThoat() {
		System.exit(0);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfDN_TenTaiKhoan.setPromptText("username");
		pfDN_MatKhau.setPromptText("password");
	}
}
