package DTO;

public class KhachHangDTO{
	private Integer maKhachHang;
	private Integer maPTPhong;
	private String tenKhachHang;
	private String CMND;
	private String SDT;
	private String gioiTinh;
	private String quocTich;
	private String ghiChu;
	
	public Integer getMaKhachHang(){
		return maKhachHang;
	}
	
	public Integer getMaPTPhong() {
		return maPTPhong;
	}
	
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	
	public String getCMND() {
		return CMND;
	}
	
	public String getSDT() {
		return SDT;
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
	
	public KhachHangDTO(Integer maKhachHang, Integer maPTPhong, String tenKhachHang, String CMND, String SDT, String gioiTinh, String quocTich, String ghiChu)
	{
		this.maKhachHang = maKhachHang;
		this.maPTPhong = maPTPhong;
		this.tenKhachHang = tenKhachHang;
		this.CMND = CMND;
		this.SDT = SDT;
		this.gioiTinh = gioiTinh;
		this.quocTich = quocTich;
		this.ghiChu = ghiChu;
	}
}

