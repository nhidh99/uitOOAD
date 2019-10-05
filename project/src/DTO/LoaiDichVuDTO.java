package DTO;

import javafx.beans.property.*;

public class LoaiDichVuDTO {
	private SimpleIntegerProperty maLoaiDichVu;
	private SimpleStringProperty tenLoaiDichVu;
	
	public Integer getMaLoaiDichVu() {
		return maLoaiDichVu.get();
	}
	
	public String getTenLoaiDichVu() {
		return tenLoaiDichVu.get();
	}
	
	public LoaiDichVuDTO(Integer maLoaiDichVu, String tenLoaiDichVu) {
		this.maLoaiDichVu = new SimpleIntegerProperty(maLoaiDichVu);
		this.tenLoaiDichVu = new SimpleStringProperty(tenLoaiDichVu);
	}
}