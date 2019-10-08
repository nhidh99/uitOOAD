package DTO;

import javafx.beans.property.*;

public class DichVuDTO {
	private SimpleIntegerProperty maDichVu;
	private SimpleStringProperty tenDichVu;
	private SimpleStringProperty donViTinh;
	private SimpleIntegerProperty soLuongTon;
	private SimpleIntegerProperty donGia;
	private SimpleStringProperty tenLoaiDichVu;
	private SimpleIntegerProperty maNhaCungCap;
	private SimpleIntegerProperty khaDung;
	private SimpleIntegerProperty maLoaiDichVu;

	public String getTenDichVu() {
		return tenDichVu.get();
	}
	
	public String getDonViTinh() {
		return donViTinh.get();
	}
	
	public Integer getSoLuongTon() {
		return soLuongTon.get();
	}
	
	public Integer getDonGia() {
		return donGia.get();
	}
	
	public String getTenLoaiDichVu() {
		return tenLoaiDichVu.get();
	}
	
	public Integer getMaNhaCungCap() {
		return maNhaCungCap.get();
	}
	
	public Integer getKhaDung() {
		return khaDung.get();
	}
	
	public Integer getMaDichVu() {
		return maDichVu.get();
	}
	
	public Integer getMaLoaiDichVu() {
		return maLoaiDichVu.get();
	}
	
	public DichVuDTO(Integer maDichVu, String tenDichVu, String donViTinh,
			Integer soLuongTon, Integer donGia, String tenLoaiDichVu, Integer maLoaiDichVu, Integer maNhaCungCap, Integer khaDung) {
		this.maDichVu = new SimpleIntegerProperty(maDichVu);
		this.tenDichVu = new SimpleStringProperty(tenDichVu);
		this.tenLoaiDichVu = new SimpleStringProperty(tenLoaiDichVu);
		this.donViTinh = new SimpleStringProperty(donViTinh);
		this.soLuongTon = new SimpleIntegerProperty(soLuongTon);
		this.donGia = new SimpleIntegerProperty(donGia);
		this.maLoaiDichVu = new SimpleIntegerProperty(maLoaiDichVu);
		this.maNhaCungCap =  new SimpleIntegerProperty(maNhaCungCap);
		this.khaDung =  new SimpleIntegerProperty(khaDung);	
	}
	public DichVuDTO(String tenDichVu, String donViTinh,
			Integer soLuongTon, Integer donGia, Integer maLoaiDichVu, Integer maNhaCungCap) {
		this.tenDichVu = new SimpleStringProperty(tenDichVu);
		this.donViTinh = new SimpleStringProperty(donViTinh);
		this.soLuongTon = new SimpleIntegerProperty(soLuongTon);
		this.donGia = new SimpleIntegerProperty(donGia);
		this.maLoaiDichVu = new SimpleIntegerProperty(maLoaiDichVu);
		this.maNhaCungCap =  new SimpleIntegerProperty(maNhaCungCap);
	}
}
