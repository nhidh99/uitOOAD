package DTO;

public class KhachDTO {
	private Integer maKhachHang;
	private PTPhongDTO ptPhong;
	private String hoTen;
	private String CMND;
	private String soDienThoai;
	private String gioiTinh;
	private String quocTich;
	private String ghiChu;

	public Integer getMaKhachHang() {
		return maKhachHang;
	}

	public PTPhongDTO getPTPhong() {
		return ptPhong;
	}

	public String getHoTen() {
		return hoTen;
	}

	public String getCMND() {
		return CMND;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public String getQuocTich() {
		return quocTich;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public KhachDTO(Integer maKH, PTPhongDTO ptPhong, String hoTen, String CMND, String soDienThoai, String gioiTinh,
			String quocTich) {
		this.maKhachHang = maKH;
		this.ptPhong = ptPhong;
		this.hoTen = hoTen;
		this.CMND = CMND;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
		this.quocTich = quocTich;
	}

	public KhachDTO(PTPhongDTO ptPhong, String hoTen, String CMND, String soDienThoai, String gioiTinh, String quocTich) {
		this.ptPhong = ptPhong;
		this.hoTen = hoTen;
		this.CMND = CMND;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
		this.quocTich = quocTich;
	}
}
