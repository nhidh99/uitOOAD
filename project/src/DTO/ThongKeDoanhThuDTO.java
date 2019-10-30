package DTO;

public class ThongKeDoanhThuDTO {
	
	private Integer thang;
	private Integer doanhThu;
	
	public ThongKeDoanhThuDTO(Integer thang, Integer doanhThu) {
		this.thang = thang;
		this.doanhThu = doanhThu;
	}
	
	public Integer getThang() {
		return this.thang;
	}
	
	public Integer getDoanhThu() {
		return this.doanhThu;
	}
}
