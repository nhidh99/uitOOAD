package DTO;

public class KhachDTO{
	private Integer maKhachHang;
	private Integer maPTPhong;
	private String tenKhachHang;
	private String CMND;
	private String SDT;
	private String GioiTinh;
	private String QuocTich;
	private String GhiChu;
	
	public Integer getmaKhachHang(){
		return maKhachHang;
	}
	
	public Integer getMaPTPhong() {
		return maPTPhong;
	}
	
	public String getHoten() {
		return tenKhachHang;
	}
	
	public String getCMND() {
		return CMND;
	}
	
	public String getSDT() {
		return SDT;
	}
	
	public String getGioiTinh() {
		return GioiTinh;
	}
	
	public String getQuocTich() {
		return QuocTich;
	}
	
	public String getGhiChu() {
		return GhiChu;
	}
	
	public KhachDTO(Integer maKH, Integer maPTP, String tenKH, String CMND, String SDT, String gioitinh, String quoctich, String ghichu)
	{
		this.maKhachHang = maKH;
		this.maPTPhong = maPTP;
		this.tenKhachHang = tenKH;
		this.CMND = CMND;
		this.SDT = SDT;
		this.GioiTinh = gioitinh;
		this.QuocTich = quoctich;
		this.GhiChu = ghichu;
	}
	
	public KhachDTO(String tenKH, String CMND, Integer MaPTPhong)
	{
		tenKhachHang = tenKH;
		this.CMND = CMND;
		this.maPTPhong = MaPTPhong;
	}
}

