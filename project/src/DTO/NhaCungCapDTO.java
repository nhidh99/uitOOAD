package DTO;

public class NhaCungCapDTO {
	private Integer maNhaCungCap;
	private String tenNhaCungCap;
	private String soDienThoai;
	
	public Integer getMaNhaCungCap() {
		return maNhaCungCap;
	}
	
	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}
	
	public String getSoDienThoai() {
		return soDienThoai;
	}
		
	public NhaCungCapDTO(Integer maNhaCungCap, String tenNhaCungCap, String soDienThoai) {
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.soDienThoai = soDienThoai;
	}
}