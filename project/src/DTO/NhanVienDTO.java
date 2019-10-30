package DTO;

public class NhanVienDTO {
	private Integer maNhanVien;
	private String tenNhanVien;
	private String cmnd;
	private String diaChi;
	private String email;
	private String soDienThoai;
	private String chucVu;
	private String taiKhoan;
	private String matKhau;
	
	public Integer getMaNhanVien() {
		return maNhanVien;
	}
	
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	
	public String getCMND() {
		return cmnd;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getDiaChi() {
		return diaChi;
	}
	
	public String getSoDienThoai() {
		return soDienThoai;
	}
	
	public String getChucVu() {
		return chucVu;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}
	
	public String getMatKhau() {
		return matKhau;
	}
	
	public NhanVienDTO(Integer maNhanVien, String tenNhanVien, String cmnd, String diaChi,
			String email, String soDienThoai, String chucVu, String taiKhoan, String matKhau) {
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.cmnd = cmnd;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.chucVu = chucVu;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
	}

	public NhanVienDTO(String tenNhanVien, String cmnd, String diaChi, String email, 
			String soDienThoai, String chucVu, String taiKhoan, String matKhau) {
		this.tenNhanVien = tenNhanVien;
		this.cmnd = cmnd;
		this.diaChi = diaChi;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.chucVu = chucVu;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
	}
}