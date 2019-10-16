package DTO;

import java.util.Date;

public class PhieuThueDTO {
	private Integer maPhieuThue;
	private Integer maNhanVien;
	private Date ngayLapPhieu;
	private String tenKhachThue;
	private String cmnd;
	private String soDienThoai;
	private String email;
	private String ghiChu;
	private Boolean thanhToanCoc;

	public Integer getMaPhieuThue() {
		return maPhieuThue;
	}

	public Integer getMaNhanVien() {
		return maNhanVien;
	}

	public Date getNgayLapPhieu() {
		return ngayLapPhieu;
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

	public Boolean getThanhToanCoc() {
		return thanhToanCoc;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public PhieuThueDTO(Integer maNhanVien, Date ngayLapPhieu, String tenKhachThue, String cmnd, String soDienThoai,
			String email, String ghiChu) {
		this.maNhanVien = maNhanVien;
		this.ngayLapPhieu = ngayLapPhieu;
		this.tenKhachThue = tenKhachThue;
		this.cmnd = cmnd;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.ghiChu = ghiChu;
	}
	
	public PhieuThueDTO(Integer maPhieuThue, Integer maNhanVien, Date ngayLapPhieu, String tenKhachThue, String cmnd, String soDienThoai,
			String email, String ghiChu) {
		this.maPhieuThue = maPhieuThue;
		this.maNhanVien = maNhanVien;
		this.ngayLapPhieu = ngayLapPhieu;
		this.tenKhachThue = tenKhachThue;
		this.cmnd = cmnd;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.ghiChu = ghiChu;
	}
}
