package DTO;

public class ThamSoDTO {
	private int soNgayTraCoc;
	private float tiLeThueVAT;
	private float tiLeTienCoc;
	private float phuThuQuaKhach;
	private float phuthuTraPhongTre;

	public int getSoNgayTraCoc() {
		return soNgayTraCoc;
	}

	public float getTiLeThueVAT() {
		return tiLeThueVAT;
	}

	public float getTiLeTienCoc() {
		return tiLeTienCoc;
	}

	public float getPhuThuQuaKhach() {
		return phuThuQuaKhach;
	}

	public float getPhuthuTraPhongTre() {
		return phuthuTraPhongTre;
	}

	public ThamSoDTO(int soNgayTraCoc, float tiLeThueVAT, float tiLeTienCoc, float phuThuQuaKhach,
			float phuthuTraPhongTre) {
		this.soNgayTraCoc = soNgayTraCoc;
		this.tiLeThueVAT = tiLeThueVAT;
		this.tiLeTienCoc = tiLeTienCoc;
		this.phuThuQuaKhach = phuThuQuaKhach;
		this.phuthuTraPhongTre = phuthuTraPhongTre;
	}
}
