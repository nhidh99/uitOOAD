package DTO;

import java.util.Date;

import helper.DateFormatHelper;
import helper.MoneyFormatHelper;

public class PhieuThueDTO {
	private Integer maPhieuThue;
	private NhanVienDTO nhanVien;
	private Date ngayLapPhieu;
	private String tenKhachThue;
	private String cmnd;
	private String soDienThoai;
	private String email;
	private String ghiChu;
	private Integer tongTienCoc;
	private Boolean thanhToanCoc;

	public Integer getMaPhieuThue() {
		return maPhieuThue;
	}

	public Integer getMaNhanVien() {
		return nhanVien.getMaNhanVien();
	}

	public Date getNgayLapValue() {
		return ngayLapPhieu;
	}
	
	public String getNgayLap() {
		return DateFormatHelper.toString(ngayLapPhieu);
	}

	public String getTenKhachThue() {
		return tenKhachThue;
	}

	public String getCMND() {
		return cmnd;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public String getEmail() {
		return email;
	}
	
	public Integer getTongTienCocValue() {
		return tongTienCoc;
	}
	
	public String getTongTienCoc() {
		return MoneyFormatHelper.format(tongTienCoc);
	}

	public Boolean getThanhToanCocValue() {
		return thanhToanCoc;
	}

	public String getThanhToanCoc() {
		return thanhToanCoc ? "Đã thanh toán" : "Chưa thanh toán";
	}

	public String getGhiChu() {
		return ghiChu;
	}
	
	public NhanVienDTO getNhanVien() {
		return nhanVien;
	}

	public PhieuThueDTO(NhanVienDTO nhanVien, Date ngayLapPhieu, String tenKhachThue, String cmnd, String soDienThoai,
			String email, String ghiChu) {
		this.nhanVien = nhanVien;
		this.ngayLapPhieu = ngayLapPhieu;
		this.tenKhachThue = tenKhachThue;
		this.cmnd = cmnd;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.ghiChu = ghiChu;
	}

	public PhieuThueDTO(Integer maPhieuThue, NhanVienDTO nhanVien, Date ngayLapPhieu, String tenKhachThue, String cmnd, String soDienThoai,
			String email, Integer tongTienCoc, Boolean thanhToanCoc, String ghiChu) {
		this.maPhieuThue = maPhieuThue;
		this.nhanVien = nhanVien;
		this.ngayLapPhieu = ngayLapPhieu;
		this.tenKhachThue = tenKhachThue;
		this.cmnd = cmnd;
		this.soDienThoai = soDienThoai;
		this.tongTienCoc = tongTienCoc;
		this.thanhToanCoc = thanhToanCoc;
		this.email = email;
		this.ghiChu = ghiChu;
	}
	
	public PhieuThueDTO(Integer maPhieuThue, NhanVienDTO nhanVien, Date ngayLapPhieu, String tenKhachThue, String cmnd, String soDienThoai,
			String email, String ghiChu) {
		this.maPhieuThue = maPhieuThue;
		this.nhanVien = nhanVien;
		this.ngayLapPhieu = ngayLapPhieu;
		this.tenKhachThue = tenKhachThue;
		this.cmnd = cmnd;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.ghiChu = ghiChu;
	}
}
