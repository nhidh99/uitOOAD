package DTO;

public class ThongKeLoaiPhongDTO {
	private String loaiPhong;
	private String thang;
	private int doanhThu;
	
	public ThongKeLoaiPhongDTO(String loaiPhong,  int doanhThu,String thang) {
		this.loaiPhong = loaiPhong;
		this.doanhThu = doanhThu;
		this.thang = thang;
	}
	
	public String getLoaiPhong() {
		return this.loaiPhong;
	}
	
	public String getThang() {
		return this.thang;
	}
	
	public int getDoanhThu() {
		return this.doanhThu;
	}
}

