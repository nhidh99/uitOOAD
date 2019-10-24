package DTO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import DAO.PhieuThueDAO;
import helper.DateFormatHelper;
import helper.MoneyFormatHelper;

public class PTPhongDTO {
	private Integer maPhieuThuePhong;
	private PhieuThueDTO phieuThue;
	private PhongDTO phong;
	private String loaiPhongThue;
	private Integer soKhachToiDa;
	private Integer donGiaThue;
	private Timestamp ngayNhan;
	private Timestamp ngayTra;
	private Integer tienCoc;
	private Integer thanhTien;
	
	public PTPhongDTO(Integer maPTP, PhongDTO phong, Timestamp ngayNhan, Timestamp ngayTra, Integer tienCoc) {
		LoaiPhongDTO loaiPhong = phong.getLoaiPhong();
		this.maPhieuThuePhong = maPTP;
		this.phong = phong;
		this.loaiPhongThue = loaiPhong.getTenLoaiPhong();
		this.soKhachToiDa = loaiPhong.getSoKhachToiDa();
		this.donGiaThue = loaiPhong.getDonGiaValue();
		this.ngayNhan = ngayNhan;
		this.ngayTra = ngayTra;
		this.tienCoc = tienCoc;

		try {
			this.phieuThue = PhieuThueDAO.getPhieuThueByMaPTP(maPTP);
		} catch (SQLException ex) {
			this.phieuThue = null;
		}
	}
	
	public PTPhongDTO(PhongDTO phong, Timestamp ngayNhan, Timestamp ngayTra, Integer tienCoc) {
		LoaiPhongDTO loaiPhong = phong.getLoaiPhong();
		this.phong = phong;
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
		return phong.getMaPhong();
	}

	public PhieuThueDTO getPhieuThue() {
		return phieuThue;
	}

	public PhongDTO getPhong() {
		return phong;
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

	public Integer getThanhTien() {
		return thanhTien;
	}
	
	public String getKhachThue() {
		return phieuThue.getTenKhachThue();
	}
	
	public String getDienThoai() {
		return phieuThue.getSoDienThoai();
	}
}
