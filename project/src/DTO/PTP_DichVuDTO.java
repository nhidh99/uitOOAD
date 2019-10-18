package DTO;

import helper.MoneyFormatHelper;

public class PTP_DichVuDTO {
	private Integer maPTPDV;
	private Integer maPTPhong;
	private DichVuDTO dichVu;
	private Integer soLuong;
	private Integer donGia;
	private Integer thanhTien;
	
	public PTP_DichVuDTO(Integer maPTPDV, Integer maPTPhong, DichVuDTO dichVu, Integer soLuong, Integer donGia, Integer thanhTien) {
		this.maPTPDV = maPTPDV;
		this.maPTPhong = maPTPhong;
		this.dichVu = dichVu;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
	}

	public Integer getMaPTPDichVu() {
		return maPTPDV;
	}
	
	public Integer getMaPTPhong() {
		return maPTPhong;
	}
	
	public Integer getSoLuong() {
		return soLuong;
	}
	
	public DichVuDTO getDichVu() {
		return dichVu;
	}
	
	public Integer getMaDichVu() {
		return dichVu.getMaDichVu();
	}
	
	public String getTenDichVu() {
		return dichVu.getTenDichVu();
	}
	
	public String getDonViTinh() {
		return dichVu.getDonViTinh();
	}
	
	public Integer getSoLuongTon() {
		return dichVu.getSoLuongTon();
	}
	
	public Integer getDonGiaValue() {
		return donGia;
	}
	
	public String getDonGia() {
		return MoneyFormatHelper.format(donGia);
	}
	
	public Integer getThanhTienValue() {
		return thanhTien;
	}
	
	public String getThanhTien() {
		return MoneyFormatHelper.format(thanhTien);
	}
	
	public void setDonGia(Integer donGia) {
		this.donGia = donGia;
		this.thanhTien = this.donGia * this.soLuong;
	}
}