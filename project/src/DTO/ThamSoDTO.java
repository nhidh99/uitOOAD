package DTO;

public class ThamSoDTO {
	private float tiLeThueVAT;
	private float tiLeTienCoc;
	
	public float getTiLeThueVAT() {
		return tiLeThueVAT;
	}

	public float getTiLeTienCoc() {
		return tiLeTienCoc;
	}

	public ThamSoDTO(float tiLeThueVAT, float tiLeTienCoc) {
		this.tiLeThueVAT = tiLeThueVAT;
		this.tiLeTienCoc = tiLeTienCoc;
	}
}
