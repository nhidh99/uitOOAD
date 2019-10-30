package DTO;

import helper.MoneyFormatHelper;

public class PtpDichVuDTO {
	private Integer maPTPDV;
	private PTPhongDTO ptPhong;
	private DichVuDTO dichVu;
	private Integer soLuong;
	private Integer donGia;
	private Integer thanhTien;

	public PtpDichVuDTO(Integer maPTPDV, PTPhongDTO ptPhong, DichVuDTO dichVu, Integer soLuong, Integer donGia,
			Integer thanhTien) {
		this.maPTPDV = maPTPDV;
		this.ptPhong = ptPhong;
		this.dichVu = dichVu;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
	}

	public PtpDichVuDTO(PTPhongDTO ptPhong, DichVuDTO dichVu, Integer soLuong, Integer donGia) {
		this.ptPhong = ptPhong;
		this.dichVu = dichVu;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = soLuong * donGia;
	}

	public Integer getMaPTPDichVu() {
		return maPTPDV;
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

	public PTPhongDTO getPTPhong() {
		return ptPhong;
	}
}