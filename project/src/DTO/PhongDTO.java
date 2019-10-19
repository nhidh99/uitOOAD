package DTO;

public class PhongDTO {
	private String maPhong;
	private LoaiPhongDTO loaiPhong;
	private TinhTrangDTO tinhTrang;
	private Integer maPTP;
	private String ghiChu;
	
	public PhongDTO(String maPhong, LoaiPhongDTO loaiPhong, TinhTrangDTO tinhTrang, String ghiChu) {
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.tinhTrang = tinhTrang;
		this.ghiChu = ghiChu;
	}
	
	public String getMaPhong() {
		return maPhong;
	}

	public LoaiPhongDTO getLoaiPhong() {
		return loaiPhong;
	}

	public TinhTrangDTO getTinhTrang() {
		return tinhTrang;
	}
	
	public String getGhiChu() {
		return ghiChu;
	}

	public Integer getMaPTP() {
		return maPTP;
	}

	public void setMaPTP(Integer maPTP) {
		this.maPTP = maPTP;
	}
}