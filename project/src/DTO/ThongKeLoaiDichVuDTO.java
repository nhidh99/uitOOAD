package DTO;

public class ThongKeLoaiDichVuDTO {
	private String thang;
	private int doanhThu;
	private String tenLoaiDichVu;
	
	public ThongKeLoaiDichVuDTO(int doanhThu, String thang, String tenLoaiDichVu) {
		this.doanhThu = doanhThu;
		this.thang = thang;
		this.tenLoaiDichVu = tenLoaiDichVu;
	}
	
	public String getThang() {
		return this.thang;
	}
	
	public int getDoanhThu() {
		return this.doanhThu;
	}
	
	public String getTenLoaiDichVu() {
		return this.tenLoaiDichVu;
	}
}
