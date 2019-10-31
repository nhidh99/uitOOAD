package DTO;

public class ThongKeLoaiPhongDTO {
	private String loaiPhong;
	private Integer doanhThu;
	private Float tiLe;

	public ThongKeLoaiPhongDTO(String loaiPhong, Integer doanhThu) {
		this.loaiPhong = loaiPhong;
		this.doanhThu = doanhThu;
	}

	public String getLoaiPhong() {
		return this.loaiPhong;
	}

	public Integer getDoanhThu() {
		return this.doanhThu;
	}

	public Float getTiLe() {
		return tiLe;
	}

	public void setTiLe(Float tiLe) {
		this.tiLe = tiLe;
	}
}