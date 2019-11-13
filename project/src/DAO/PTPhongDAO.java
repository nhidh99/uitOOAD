package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.LoaiPhongDTO;
import DTO.PTPhongDTO;
import DTO.PhieuThueDTO;
import helper.DBHelper;
import helper.DateFormatHelper;

public class PTPhongDAO {

	public static boolean insertPTPhong(PTPhongDTO ptp) throws SQLException {
		long soDem = DateFormatHelper.diffDays(ptp.getNgayTraValue(), ptp.getNgayNhanValue());
		long thanhTien = soDem * ptp.getDonGiaThueValue();

		Connection conn = DBHelper.getConnection();
		try {
			String query = "INSERT INTO PT_Phong (MaPhong, MaLoaiPhong, SoKhachToiDa,"
					+ "NgayNhan, NgayTra, DonGiaThue, TienCoc, ThanhTien) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, ptp.getMaPhong());
			statement.setInt(2, ptp.getMaLoaiPhong());
			statement.setInt(3, ptp.getSoKhachToiDa());
			statement.setTimestamp(4, ptp.getNgayNhanValue());
			statement.setTimestamp(5, ptp.getNgayTraValue());
			statement.setInt(6, ptp.getDonGiaThueValue());
			statement.setInt(7, ptp.getTienCocValue());
			statement.setLong(8, thanhTien);

			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static List<PTPhongDTO> getDSPhongDangKy() throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM PT_Phong WHERE MaPhieuThue IS NULL";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			List<PTPhongDTO> output = new ArrayList<PTPhongDTO>();
			while (rs.next()) {
				String maPhong = rs.getString("MaPhong");
				LoaiPhongDTO loaiPhong = LoaiPhongDAO.getLoaiPhongById(rs.getInt("MaLoaiPhong"));
				PTPhongDTO ptp = new PTPhongDTO(rs.getInt("MaPTPhong"), maPhong, 
						loaiPhong, null, rs.getInt("DonGiaThue"), rs.getTimestamp("NgayNhan"), 
						rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"), rs.getInt("ThanhTien"));
				output.add(ptp);
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static List<PTPhongDTO> getDSPTPhongByMaPhieu(Integer maPhieuThue) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM PT_Phong WHERE MaPhieuThue = " + maPhieuThue;
			ResultSet rs = statement.executeQuery(query);

			List<PTPhongDTO> output = new ArrayList<PTPhongDTO>();
			while (rs.next()) {
				String maPhong = rs.getString("MaPhong");
				Integer maPTPhong = rs.getInt("MaPTPhong");
				LoaiPhongDTO loaiPhong = LoaiPhongDAO.getLoaiPhongById(rs.getInt("MaLoaiPhong"));
				PhieuThueDTO phieuThue = PhieuThueDAO.getPhieuThueByMaPTP(maPTPhong);
				PTPhongDTO ptp = new PTPhongDTO(maPTPhong, maPhong, 
						loaiPhong, phieuThue, rs.getInt("DonGiaThue"), rs.getTimestamp("NgayNhan"), 
						rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"), rs.getInt("ThanhTien"));
				output.add(ptp);
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean deleteAllPTPhong() throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			Statement statement = conn.createStatement();
			String query = "DELETE FROM PT_Phong WHERE MaPhieuThue IS NULL";
			boolean output = statement.execute(query);
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean deletePTPhong(Integer maPTPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "call del_PTPhong(?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maPTPhong);
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static PTPhongDTO getPTPhongById(Integer maPTP) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM PT_Phong WHERE MaPTPhong = " + maPTP;
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			Integer maPTPhong = rs.getInt("MaPTPhong");
			String maPhong = rs.getString("MaPhong");
			LoaiPhongDTO loaiPhong = LoaiPhongDAO.getLoaiPhongById(rs.getInt("MaLoaiPhong"));
			PhieuThueDTO phieuThue = PhieuThueDAO.getPhieuThueByMaPTP(maPTPhong);
			PTPhongDTO output = new PTPhongDTO(maPTPhong, maPhong, 
					loaiPhong, phieuThue, rs.getInt("DonGiaThue"), rs.getTimestamp("NgayNhan"), 
					rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"), rs.getInt("ThanhTien"));
			return output;
		} finally {
			conn.close();
		}
	}

	public static List<PTPhongDTO> getDSPTPhongByMaPhong(String maPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM view_dsptphong WHERE MaPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, maPhong);
			ResultSet rs = statement.executeQuery();
			List<PTPhongDTO> output = new ArrayList<PTPhongDTO>();
			while (rs.next()) {
				Integer maPTPhong = rs.getInt("MaPTPhong");
				LoaiPhongDTO loaiPhong = LoaiPhongDAO.getLoaiPhongById(rs.getInt("MaLoaiPhong"));
				PhieuThueDTO phieuThue = PhieuThueDAO.getPhieuThueByMaPTP(maPTPhong);
				PTPhongDTO ptp = new PTPhongDTO(maPTPhong, maPhong, 
						loaiPhong, phieuThue, rs.getInt("DonGiaThue"), rs.getTimestamp("NgayNhan"), 
						rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"), rs.getInt("ThanhTien"));
				output.add(ptp);
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static List<PTPhongDTO> getDSPTPhongByMaHD(Integer maHD) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM PT_Phong WHERE MaHoaDon = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maHD);
			ResultSet rs = statement.executeQuery();
			List<PTPhongDTO> output = new ArrayList<PTPhongDTO>();
			while (rs.next()) {
				String maPhong = rs.getString("MaPhong");
				Integer maPTPhong = rs.getInt("MaPTPhong");
				LoaiPhongDTO loaiPhong = LoaiPhongDAO.getLoaiPhongById(rs.getInt("MaLoaiPhong"));
				PhieuThueDTO phieuThue = PhieuThueDAO.getPhieuThueByMaPTP(maPTPhong);
				PTPhongDTO ptp = new PTPhongDTO(maPTPhong, maPhong, 
						loaiPhong, phieuThue, rs.getInt("DonGiaThue"), rs.getTimestamp("NgayNhan"), 
						rs.getTimestamp("NgayTra"), rs.getInt("TienCoc"), rs.getInt("ThanhTien"));
				output.add(ptp);
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean checkPTPhong(PTPhongDTO ptPhong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "CALL check_PTPhong(?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, ptPhong.getMaPTPhong());
			statement.setString(2, ptPhong.getMaPhong());
			ResultSet rs = statement.executeQuery();
			rs.next();
			boolean output = rs.getBoolean(1);
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean updateMaHoaDon(Integer maPTP, Integer maHoaDon) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "UPDATE PT_Phong SET MaHoaDon = ? WHERE MaPTPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maHoaDon);
			statement.setInt(2, maPTP);
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean updatePTPhong(PTPhongDTO ptphong) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "UPDATE PT_Phong SET MaPhong = ? WHERE MaPTPhong = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, ptphong.getMaPhong());
			statement.setInt(2, ptphong.getMaPTPhong());
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}
}