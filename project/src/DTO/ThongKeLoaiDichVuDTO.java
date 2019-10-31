package DTO;

public class ThongKeLoaiDichVuDTO {
	private Integer doanhThu;
	private String tenLoaiDichVu;
	private Float tiLe;
	
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

	public Float getTiLe() {
		return tiLe;
	}

	public void setTiLe(Float tiLe) {
		this.tiLe = tiLe;
	}
}
