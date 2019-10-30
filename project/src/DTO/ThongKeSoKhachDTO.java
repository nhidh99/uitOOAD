package DTO;

public class ThongKeSoKhachDTO {
	private Integer soKhach;
	private Integer thang;
	
	public ThongKeSoKhachDTO(Integer soKhach, Integer thang) {
		this.soKhach = soKhach;
		this.thang = thang;
	}
	
	public Integer getSoKhach() {
		return this.soKhach;
	}
	
	public Integer getThang() {
		return this.thang;
	}
}
