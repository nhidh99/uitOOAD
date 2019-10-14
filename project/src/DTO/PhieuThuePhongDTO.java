package DTO;

import java.util.Date;

public class PhieuThuePhongDTO {
	private Integer maPTPhong;
	private Integer maPhieuThue;
	private Integer maPhong;
	private String loaiPhongThue;
	private Integer soKhachToiDa;
	private Date ngayNhanPhong;
	private Date ngayTraPhong;
	private Integer donGiaThue;
	private Integer tienCoc;
	private Integer maHoaDon;
	private Integer thanhTien;
	
	public PhieuThuePhongDTO(Integer maPTPhong, Integer maPhong, String loaiPhongThue, Integer soKhachToiDa, Date ngayNhanPhong, Date ngayTraPhong, Integer donGiaThue ) {
		this.maPTPhong = maPTPhong;
		this.maPhong = maPhong;
		this.loaiPhongThue = loaiPhongThue;
		this.soKhachToiDa = soKhachToiDa;
		this.ngayNhanPhong = ngayNhanPhong;
		this.ngayTraPhong = ngayTraPhong;
		this.donGiaThue = donGiaThue;
	}
	
	public Integer getMaPhieuThue() {
		return maPhieuThue;
	}
	
	public Integer getMaPTPhong() {
		return maPTPhong;
	}
	
	public Integer getMaPhong() {
		return maPhong;
	}
	
	public Integer getSoKhachToiDa() {
		return soKhachToiDa;
	}
	public Integer getDonGiaThue() {
		return donGiaThue;
	}
}