package DTO;

import helper.MoneyFormatHelper;

public class DichVuDTO {
	private Integer maDichVu;
	private String tenDichVu;
	private String donViTinh;
	private Integer soLuongTon;
	private Integer donGia;
	private LoaiDichVuDTO loaiDichVu;
	private NhaCungCapDTO nhaCungCap;
		
	public String getTenDichVu() {
		return tenDichVu;
	}
	
	public String getDonViTinh() {
		return donViTinh;
	}
	
	public Integer getSoLuongTon() {
		return soLuongTon == -1 ? null : soLuongTon;
	}
	
	public String getDonGia() {
		return MoneyFormatHelper.format(donGia);
	}
		
	public Integer getDonGiaValue() {
		return donGia;
	}

	public String getTenLoaiDichVu() {
		return loaiDichVu.getTenLoaiDichVu();
	}
	
	public Integer getMaDichVu() {
		return maDichVu;
	}
	
	public Integer getMaLoaiDichVu() {
		return loaiDichVu.getMaLoaiDichVu();
	}

	public Integer getMaNhaCungCap() {
		return nhaCungCap.getMaNhaCungCap();
	}
	
	public DichVuDTO(Integer maDichVu, String tenDichVu, String donViTinh, Integer soLuongTon, 
			Integer donGia, LoaiDichVuDTO loaiDichVu, NhaCungCapDTO nhaCungCap) {
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.donViTinh = donViTinh;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
		this.loaiDichVu = loaiDichVu;
		this.nhaCungCap = nhaCungCap;
	}
	
	public DichVuDTO(String tenDichVu, String donViTinh, Integer soLuongTon, 
			Integer donGia, LoaiDichVuDTO loaiDichVu, NhaCungCapDTO nhaCungCap) {
		this.tenDichVu = tenDichVu;
		this.donViTinh = donViTinh;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
		this.loaiDichVu = loaiDichVu;
		this.nhaCungCap = nhaCungCap;
	}

	public DichVuDTO(String tenDichVu, String donViTinh, Integer soLuongTon, Integer donGia) {
		this.tenDichVu = tenDichVu;
		this.donViTinh = donViTinh;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
	}
}