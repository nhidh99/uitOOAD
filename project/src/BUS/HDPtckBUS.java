package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.HDPtckDAO;
import DTO.HDPtckDTO;

public class HDPtckBUS {

	public static boolean insertHDPtck(HDPtckDTO hdPtck) throws SQLException {
		return HDPtckDAO.insertHDPtck(hdPtck);
	}

	public static List<HDPtckDTO> getDSPtckByMaHD(Integer maHD) throws SQLException {
		return HDPtckDAO.getDSHDPtckByMaHD(maHD);
	}

}
