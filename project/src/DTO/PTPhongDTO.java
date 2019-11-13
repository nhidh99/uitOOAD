package DTO;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import helper.DateFormatHelper;
import helper.MoneyFormatHelper;

public class PTPhongDTO {
	private Integer maPhieuThuePhong;
	private String maPhong;
	private PhieuThueDTO phieuThue;
	private HoaDonDTO hoaDon;
	private Integer maLoaiPhong;
	private String loaiPhongThue;
	private Integer soKhachToiDa;
	private Integer donGiaThue;
	private Timestamp ngayNhan;
	private Timestamp ngayTra;
	private Integer tienCoc;
	private Integer thanhTien;

	public PTPhongDTO(Integer maPhieuThuePhong, String maPhong, LoaiPhongDTO loaiPhong, PhieuThueDTO phieuThue, Integer donGiaThue,
			Timestamp ngayNhan, Timestamp ngayTra, Integer tienCoc, Integer thanhTien) {
		this.maPhieuThuePhong = maPhieuThuePhong;
		this.maPhong = maPhong;
		this.maLoaiPhong = loaiPhong.getMaLoaiPhong();
		this.loaiPhongThue = loaiPhong.getTenLoaiPhong();
		this.soKhachToiDa = loaiPhong.getSoKhachToiDa();
		this.donGiaThue = donGiaThue;
		this.phieuThue = phieuThue;
		this.ngayNhan = ngayNhan;
		this.ngayTra = ngayTra;
		this.tienCoc = tienCoc;
		this.thanhTien = thanhTien;
	}

	public PTPhongDTO(String maPhong, LoaiPhongDTO loaiPhong, Timestamp ngayNhan, Timestamp ngayTra, Integer tienCoc) {
		this.maPhong = maPhong;
		this.maLoaiPhong = loaiPhong.getMaLoaiPhong();
		this.loaiPhongThue = loaiPhong.getTenLoaiPhong();
		this.loaiPhongThue = loaiPhong.getTenLoaiPhong();
		this.soKhachToiDa = loaiPhong.getSoKhachToiDa();
		this.donGiaThue = loaiPhong.getDonGiaValue();
		this.ngayNhan = ngayNhan;
		this.ngayTra = ngayTra;
		this.tienCoc = tienCoc;
	}

	public Integer getMaPTPhong() {
		return maPhieuThuePhong;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public PhieuThueDTO getPhieuThue() {
		return phieuThue;
	}

	public String getLoaiPhongThue() {
		return loaiPhongThue;
	}

	public Integer getSoKhachToiDa() {
		return soKhachToiDa;
	}

	public String getDonGiaThue() {
		return MoneyFormatHelper.format(donGiaThue);
	}

	public Integer getDonGiaThueValue() {
		return donGiaThue;
	}

	public String getNgayNhan() {
		Date date = new Date(ngayNhan.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return String.format("%02dh %s", cal.get(Calendar.HOUR_OF_DAY), DateFormatHelper.toString(date));
	}

	public String getNgayTra() {
		Date date = new Date(ngayTra.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return String.format("%02dh %s", cal.get(Calendar.HOUR_OF_DAY), DateFormatHelper.toString(date));
	}

	public Timestamp getNgayNhanValue() {
		return ngayNhan;
	}

	public Timestamp getNgayTraValue() {
		return ngayTra;
	}

	public String getTienCoc() {
		return MoneyFormatHelper.format(tienCoc);
	}

	public Integer getTienCocValue() {
		return tienCoc;
	}

	public String getThanhTien() {
		return MoneyFormatHelper.format(thanhTien);
	}

	public Integer getThanhTienValue() {
		return thanhTien;
	}

	public String getTenKhachThue() {
		return phieuThue.getTenKhachThue();
	}

	public String getSoDienThoai() {
		return phieuThue.getSoDienThoai();
	}

	public HoaDonDTO getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDonDTO hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Integer getMaLoaiPhong() {
		return maLoaiPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
}
