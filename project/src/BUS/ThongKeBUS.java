package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.ThongKeDAO;
import DTO.ThongKeLoaiDichVuDTO;
import DTO.ThongKeDoanhThuDTO;
import DTO.ThongKeLoaiPhongDTO;
import DTO.ThongKeSoKhachDTO;

public class ThongKeBUS {
	public static List<ThongKeDoanhThuDTO> getDoanhThuTheoNam(Integer nam) throws SQLException {
		return ThongKeDAO.getDoanhThuTheoNam(nam);
	}

	public static List<ThongKeSoKhachDTO> getSoKhachTheoNam(Integer nam) throws SQLException {
		return ThongKeDAO.getSoKhachTheoNam(nam);
	}

	public static List<ThongKeLoaiPhongDTO> getLoaiPhongTheoThang(Integer thang, Integer nam) throws SQLException {
		return ThongKeDAO.getLoaiPhongTheoThang(thang, nam);
	}

	public static List<ThongKeLoaiDichVuDTO> getLoaiDichVuTheoThang(Integer thang, Integer nam) throws SQLException {
		return ThongKeDAO.getLoaiDichVuTheoThang(thang, nam);
	}
}
