package DTO;

public class ThongKeLoaiDichVuDTO {
	private Integer doanhThu;
	private String tenLoaiDichVu;
	
	public ThongKeLoaiDichVuDTO(Integer doanhThu, String tenLoaiDichVu) {
		this.doanhThu = doanhThu;
		this.tenLoaiDichVu = tenLoaiDichVu;
	}
		
	public Integer getDoanhThu() {
		return this.doanhThu;
	}
	
	public String getTenLoaiDichVu() {
		return this.tenLoaiDichVu;
	}
}
