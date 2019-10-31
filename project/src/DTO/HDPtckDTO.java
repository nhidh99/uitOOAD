package DTO;

import helper.MoneyFormatHelper;

public class HDPtckDTO {
	private Integer maHDPtck;
	private HoaDonDTO hoaDon;
	private String noiDung;
	private Integer triGia;

	public HDPtckDTO(Integer maHDPtck, HoaDonDTO hoaDon, String noiDung, Integer triGia) {
		this.maHDPtck = maHDPtck;
		this.hoaDon = hoaDon;
		this.noiDung = noiDung;
		this.triGia = triGia;
	}

	public HDPtckDTO(HoaDonDTO hoaDon, String noiDung, Integer triGia) {
		this.hoaDon = hoaDon;
		this.noiDung = noiDung;
		this.triGia = triGia;
	}

	public HDPtckDTO(String noiDung, Integer triGia) {
		this.noiDung = noiDung;
		this.triGia = triGia;
	}

	public Integer getMaHDPtck() {
		return maHDPtck;
	}

	public HoaDonDTO getHoaDon() {
		return hoaDon;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public String getTriGia() {
		return MoneyFormatHelper.format(triGia);
	}

	public Integer getTriGiaValue() {
		return triGia;
	}

	public void setHoaDon(HoaDonDTO hoaDon) {
		this.hoaDon = hoaDon;
	}
}