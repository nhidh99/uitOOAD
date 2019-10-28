package BUS;

import java.sql.SQLException;

import DAO.HDPtckDAO;
import DTO.HDPtckDTO;

public class HDPtckBUS {

	public static boolean insertHDPtck(HDPtckDTO hdPtck) throws SQLException {
		return HDPtckDAO.insertHDPtck(hdPtck);
	}

}
