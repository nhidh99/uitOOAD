package DTO;

import helper.MoneyFormatHelper;

public class PtpPtckDTO {
	Integer maPTCKPhong;
	PTPhongDTO ptPhong;
	Integer soLuong;
	Integer donGia;
	Integer triGia;
	String noiDung;
	
	public PtpPtckDTO(Integer maPTCKPhong, PTPhongDTO ptPhong, String noiDung, Integer soLuong, Integer donGia, Integer triGia) {
		this.maPTCKPhong = maPTCKPhong;
		this.ptPhong = ptPhong;
		this.noiDung = noiDung;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.triGia = triGia;
	}
	
	public PtpPtckDTO(PTPhongDTO ptPhong, String noiDung, Integer soLuong, Integer donGia, Integer triGia) {
		this.ptPhong = ptPhong;
		this.noiDung = noiDung;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.triGia = triGia;
	}
	
	public Integer getMaPTCKPhong() {
		return maPTCKPhong;
	}
	
	public PTPhongDTO getPTPhong() {
		return ptPhong;
	}
	
	public Integer getMaPTPhong() {
		return ptPhong.getMaPTPhong();
	}
	
	public String getNoiDung() {
		return noiDung;
	}
	
	public Integer getSoLuong() {
		return soLuong;
	}
	
	public String getDonGia() {
		return MoneyFormatHelper.format(donGia);
	}

	public String getTriGia() {
		return MoneyFormatHelper.format(triGia);
	}
	
	public Integer getDonGiaValue() {
		return donGia;
	}
	
	public Integer getTriGiaValue() {
		return triGia;
	}	
}