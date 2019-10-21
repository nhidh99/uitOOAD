package DTO;

public class ThongKeDoanhThuDTO {
	private String thang;
	private int tienPhong;
	private int tienDichVu;
	private int tienPTCK;
	
	public ThongKeDoanhThuDTO(String thang, int tienPhong, int tienDichVu, int tienPTCK) {
		this.thang = thang;
		this.tienDichVu = tienDichVu;
		this.tienPhong = tienPhong;
		this.tienPTCK = tienPTCK;
	}
	
	public String getThang() {
		return this.thang;
	}
	
	public int getTienPhong() {
		return this.tienPhong;
	}
	
	public int getTienDichVu() {
		return this.tienDichVu;
	}
	
	public int getTienPTCK() {
		return this.tienPTCK;
	}
}
