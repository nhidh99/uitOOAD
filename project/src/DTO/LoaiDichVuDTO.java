package DTO;

public class LoaiDichVuDTO {
	private Integer maLoaiDichVu;
	private String tenLoaiDichVu;
	
	public Integer getMaLoaiDichVu() {
		return maLoaiDichVu;
	}
	
	public String getTenLoaiDichVu() {
		return tenLoaiDichVu;
	}
	
	public LoaiDichVuDTO(Integer maLoaiDichVu, String tenLoaiDichVu) {
		this.maLoaiDichVu = maLoaiDichVu;
		this.tenLoaiDichVu = tenLoaiDichVu;
	}
	
	public LoaiDichVuDTO(String tenLoaiDichVu) {
		this.tenLoaiDichVu = tenLoaiDichVu;
	}
}