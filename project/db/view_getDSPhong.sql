CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `view_dsphong` AS
    SELECT 
        `phong`.`MaPhong` AS `MaPhong`,
        `phong`.`MaLoaiPhong` AS `MaLoaiPhong`,
        `phong`.`MaTinhTrang` AS `MaTinhTrang`,
        `phong`.`GhiChu` AS `GhiChu`,
        `loaiphong`.`TenLoaiPhong` AS `TenLoaiPhong`,
        `loaiphong`.`DonGia` AS `DonGia`,
        `loaiphong`.`SoKhachToiDa` AS `SoKhachToiDa`,
        `tinhtrang`.`TenTinhTrang` AS `TenTinhTrang`
    FROM
        ((`phong`
        JOIN `loaiphong`)
        JOIN `tinhtrang`)
    WHERE
        ((`phong`.`MaLoaiPhong` = `loaiphong`.`MaLoaiPhong`)
            AND (`phong`.`MaTinhTrang` = `tinhtrang`.`MaTinhTrang`));
