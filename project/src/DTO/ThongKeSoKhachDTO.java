package DTO;

public class ThongKeSoKhachDTO {
	private int soKhach;
	private String thang;
	
	public ThongKeSoKhachDTO(int soKhach, String thang) {
		this.soKhach = soKhach;
		this.thang = thang;
	}
	
	public int getSoKhach() {
		return this.soKhach;
	}
	
	public String getThang() {
		return this.thang;
	}
}
