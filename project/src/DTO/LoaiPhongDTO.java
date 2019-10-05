package DTO;

import javafx.beans.property.*;

public class LoaiPhongDTO {
	private SimpleIntegerProperty maLoaiPhong;
	private SimpleStringProperty tenLoaiPhong;
	private SimpleIntegerProperty soKhachToiDa;
	private SimpleIntegerProperty donGia;
	
	public Integer getMaLoaiPhong() {
		return maLoaiPhong.get();
	}
	
	public String getTenLoaiPhong() {
		return tenLoaiPhong.get();
	}
	
	public Integer getSoKhachToiDa() {
		return soKhachToiDa.get();
	}
	
	public Integer getDonGia() {
		return donGia.get();
	}
	
	public LoaiPhongDTO(Integer maLoaiPhong, String tenLoaiPhong, Integer soKhachToiDa, Integer donGia) {
		this.maLoaiPhong = new SimpleIntegerProperty(maLoaiPhong);
		this.tenLoaiPhong = new SimpleStringProperty(tenLoaiPhong);
		this.soKhachToiDa = new SimpleIntegerProperty(soKhachToiDa);
		this.donGia = new SimpleIntegerProperty(donGia);		
	}
}
