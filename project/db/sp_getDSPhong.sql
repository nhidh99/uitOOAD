CREATE PROCEDURE `get_DSPhong` ()
BEGIN
	SELECT 	
		MaPhong, Phong.MaLoaiPhong, Phong.MaTinhTrang,
		GhiChu, TenLoaiPhong, DonGia, SoKhachToiDa, TenTinhTrang    
    FROM 
		Phong, LoaiPhong, TinhTrang
    WHERE
		Phong.MaLoaiPhong = LoaiPhong.MaLoaiPhong
        AND Phong.MaTinhTrang = TinhTrang.MaTinhTrang;
END