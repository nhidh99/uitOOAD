package DTO;

import javafx.beans.property.*;

public class NhanVienDTO {
	private SimpleIntegerProperty maNhanVien;
	private SimpleStringProperty tenNhanVien;
	private SimpleStringProperty cmnd;
	private SimpleStringProperty diaChi;
	private SimpleStringProperty email;
	private SimpleStringProperty soDienThoai;
	private SimpleStringProperty chucVu;
	
	public Integer getMaNhanVien() {
		return maNhanVien.get();
	}
	
	public String getTenNhanVien() {
		return tenNhanVien.get();
	}
	
	public String getCMND() {
		return cmnd.get();
	}
	
	public String getEmail() {
		return email.get();
	}
	
	public String getDiaChi() {
		return diaChi.get();
	}
	
	public String getSoDienThoai() {
		return soDienThoai.get();
	}
	
	public String getChucVu() {
		return chucVu.get();
	}
	
	public NhanVienDTO(Integer maNhanVien, String tenNhanVien, String cmnd, String diaChi,
			String email, String soDienThoai, String chucVu) {
		this.maNhanVien = new SimpleIntegerProperty(maNhanVien);
		this.tenNhanVien = new SimpleStringProperty(tenNhanVien);
		this.cmnd = new SimpleStringProperty(cmnd);
		this.diaChi = new SimpleStringProperty(diaChi);
		this.email = new SimpleStringProperty(email);
		this.soDienThoai = new SimpleStringProperty(soDienThoai);
		this.chucVu = new SimpleStringProperty(chucVu);
	}
}
