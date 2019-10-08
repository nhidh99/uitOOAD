package BUS;

import java.sql.SQLException;
import DAO.ThamSoDAO;
import DTO.ThamSoDTO;

public class ThamSoBUS {
	public static ThamSoDTO getThamSo() throws SQLException {
		return ThamSoDAO.getThamSo();
	}
	
	public static boolean updateThamSo(Integer soNgayTraCoc, float tiLeThueVAT, float tiLeTienCoc, float phuThuQuaKhach, float phuThuTraPhongTre) throws SQLException {
		if(ThamSoDAO.updateThamSo(soNgayTraCoc, tiLeThueVAT, tiLeTienCoc, phuThuQuaKhach, phuThuTraPhongTre)) {
			return true;
		}
		else
			return false;
	}
}