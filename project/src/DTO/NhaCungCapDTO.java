package DTO;

import javafx.beans.property.*;

public class NhaCungCapDTO {
	private SimpleIntegerProperty maNhaCungCap;
	private SimpleStringProperty tenNhaCungCap;
	private SimpleStringProperty soDienThoai;
	
	public Integer getMaNhaCungCap() {
		return maNhaCungCap.get();
	}
	
	public String getTenNhaCungCap() {
		return tenNhaCungCap.get();
	}
	
	public String getSoDienThoai() {
		return soDienThoai.get();
	}
		
	public NhaCungCapDTO(Integer maNhaCungCap, String tenNhaCungCap, String soDienThoai) {
		this.maNhaCungCap = new SimpleIntegerProperty(maNhaCungCap);
		this.tenNhaCungCap = new SimpleStringProperty(tenNhaCungCap);
		this.soDienThoai = new SimpleStringProperty(soDienThoai);
	}
}