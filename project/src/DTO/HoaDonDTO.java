package DTO;

import java.util.Date;

import helper.DateFormatHelper;
import helper.MoneyFormatHelper;

public class HoaDonDTO {
	private Integer maHoaDon;
	private NhanVienDTO nhanVien;
	private Date ngayHoaDon;
	private String tenKhach;
	private String CMND;
	private String soDienThoai;
	private String email;
	private Integer tongTienPhong;
	private Integer tongTienPTCK;
	private Integer tongTienCoc;
	private Integer giaTri;
	private Integer tienNhan;
	private Integer tienThua;
	private String ghiChu;

	public Integer getMaHoaDon() {
		return maHoaDon;
	}

	public NhanVienDTO getNhanVien() {
		return nhanVien;
	}

	public String getNgayHoaDon() {
		return DateFormatHelper.toString(ngayHoaDon);
	}

	public Date getNgayHoaDonValue() {
		return ngayHoaDon;
	}

	public String getTenKhach() {
		return tenKhach;
	}

	public String getCMND() {
		return CMND;
	}

	public String getDienThoai() {
		return soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public Integer getTongTienPhongValue() {
		return tongTienPhong;
	}

	public Integer getTongTienPTCKValue() {
		return tongTienPTCK;
	}

	public Integer getTongTienCocValue() {
		return tongTienCoc;
	}

	public Integer getGiaTriValue() {
		return giaTri;
	}

	public Integer getTienNhanValue() {
		return tienNhan;
	}

	public Integer getTienThuaValue() {
		return tienThua;
	}

	public String getTongTienPhong() {
		return MoneyFormatHelper.format(tongTienPhong);
	}

	public String getTongTienPTCK() {
		return MoneyFormatHelper.format(tongTienPTCK);
	}

	public String getTongTienCoc() {
		return MoneyFormatHelper.format(tongTienCoc);
	}

	public String getGiaTri() {
		return MoneyFormatHelper.format(giaTri);
	}

	public String getTienNhan() {
		return MoneyFormatHelper.format(tienNhan);
	}

	public String getTienThua() {
		return MoneyFormatHelper.format(tienThua);
	}

	public String getGhiChu() {
		return ghiChu;
	}
	
	public String getTenNhanVien() {
		return nhanVien.getTenNhanVien();
	}

	public HoaDonDTO(Integer maHoaDon, NhanVienDTO nhanVien, Date ngayHoaDon, String tenKhach, String CMND,
			String soDienThoai, String email, Integer tongTienPhong, Integer tongTienPTCK, Integer tongTienCoc,
			Integer giaTri, Integer tienNhan, Integer tienThua, String ghiChu) {
		this.maHoaDon = maHoaDon;
		this.nhanVien = nhanVien;
		this.ngayHoaDon = ngayHoaDon;
		this.tenKhach = tenKhach;
		this.CMND = CMND;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.tongTienCoc = tongTienCoc;
		this.tongTienPhong = tongTienPhong;
		this.tongTienPTCK = tongTienPTCK;
		this.giaTri = giaTri;
		this.tienNhan = tienNhan;
		this.tienThua = tienThua;
		this.ghiChu = ghiChu;
	}

	public HoaDonDTO(NhanVienDTO nhanVien, Date ngayHoaDon, String tenKhach, String CMND, String soDienThoai,
			String email, Integer tongTienPhong, Integer tongTienPTCK, Integer tongTienCoc, Integer giaTri,
			Integer tienNhan, Integer tienThua, String ghiChu) {
		this.nhanVien = nhanVien;
		this.ngayHoaDon = ngayHoaDon;
		this.tenKhach = tenKhach;
		this.CMND = CMND;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.tongTienCoc = tongTienCoc;
		this.tongTienPhong = tongTienPhong;
		this.tongTienPTCK = tongTienPTCK;
		this.giaTri = giaTri;
		this.tienNhan = tienNhan;
		this.tienThua = tienThua;
		this.ghiChu = ghiChu;
	}

	public void setMaHoaDon(Integer maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
}
