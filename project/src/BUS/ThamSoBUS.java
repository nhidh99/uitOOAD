package BUS;

import java.sql.SQLException;
import DAO.ThamSoDAO;
import DTO.ThamSoDTO;

public class ThamSoBUS {
	public static ThamSoDTO getThamSo() throws SQLException {
		return ThamSoDAO.getThamSo();
	}
}