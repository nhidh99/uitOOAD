package DTO;

import helper.MoneyFormatHelper;

public class LoaiPhongDTO {
	private Integer maLoaiPhong;
	private String tenLoaiPhong;
	private Integer soKhachToiDa;
	private Integer donGia;
	
	public Integer getMaLoaiPhong() {
		return maLoaiPhong;
	}
	
	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}
	
	public Integer getSoKhachToiDa() {
		return soKhachToiDa;
	}
	
	public String getDonGia() {
		return MoneyFormatHelper.format(donGia);
	}
	
	public Integer getDonGiaValue() {
		return donGia;
	}
	
	public LoaiPhongDTO(Integer maLoaiPhong, String tenLoaiPhong, Integer soKhachToiDa, Integer donGia) {
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.soKhachToiDa = soKhachToiDa;
		this.donGia = donGia;		
	}
	
	public LoaiPhongDTO(String tenLoaiPhong, Integer soKhachToiDa, Integer donGia) {
		this.tenLoaiPhong = tenLoaiPhong;
		this.soKhachToiDa = soKhachToiDa;
		this.donGia = donGia;		
	}
}
