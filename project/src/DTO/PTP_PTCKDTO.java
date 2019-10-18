package DTO;

public class PTP_PTCKDTO {
	Integer maPTCKPhong;
	Integer maPTPhong;
	Integer soLuong;
	Integer donGia;
	Integer triGia;
	String noiDung;
	
	public PTP_PTCKDTO(Integer maPTCKPhong, Integer maPTPhong, String noiDung, Integer soLuong, Integer donGia, Integer triGia) {
		this.maPTCKPhong = maPTCKPhong;
		this.maPTPhong = maPTPhong;
		this.noiDung = noiDung;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.triGia = triGia;
	}
	
	public Integer getMaPTCKPhong() {
		return maPTCKPhong;
	}
	
	public Integer getMaPTPhong() {
		return maPTPhong;
	}
	
	public String getNoiDung() {
		return noiDung;
	}
	
	public Integer getSoLuong() {
		return soLuong;
	}
	
	public Integer getDonGia() {
		return donGia;
	}
	
	public Integer getTriGia() {
		return triGia;
	}
}
