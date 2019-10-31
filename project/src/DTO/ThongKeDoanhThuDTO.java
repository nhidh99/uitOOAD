package DTO;

public class ThongKeDoanhThuDTO {

	private Integer thang;
	private Integer doanhThu;
	private Float tiLe;

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

	public Float getTiLe() {
		return tiLe;
	}
	
	public void setTile(Float tiLe) {
		this.tiLe = tiLe;
	}
}
