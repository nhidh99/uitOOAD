CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `view_dsphieuthue` AS
    SELECT 
        `pt`.`MaPhieuThue` AS `MaPhieuThue`,
        `pt`.`MaNhanVien` AS `MaNhanVien`,
        `nv`.`TenNhanVien` AS `TenNhanVien`,
        `pt`.`NgayLapPhieu` AS `NgayLapPhieu`,
        `pt`.`TenKhachThue` AS `TenKhachThue`,
        `pt`.`SoDienThoai` AS `SoDienThoai`,
        `pt`.`CMND` AS `CMND`,
        `pt`.`Email` AS `Email`,
        `pt`.`TongTienCoc` AS `TongTienCoc`,
        `pt`.`ThanhToanCoc` AS `ThanhToanCoc`,
        `pt`.`GhiChu` AS `GhiChu`
    FROM
        ((`phieuthue` `pt`
        JOIN `nhanvien` `nv` ON ((`pt`.`MaNhanVien` = `nv`.`MaNhanVien`)))
        JOIN `pt_phong` `ptp` ON ((`pt`.`MaPhieuThue` = `ptp`.`MaPhieuThue`)))
    GROUP BY `pt`.`MaPhieuThue`;

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

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `view_dsptphong` AS
    SELECT 
        `ptp`.`MaPTPhong` AS `MaPTPhong`,
        `ptp`.`MaPhong` AS `MaPhong`,
        `pt`.`TenKhachThue` AS `TenKhachThue`,
        `pt`.`SoDienThoai` AS `SoDienThoai`,
        `ptp`.`NgayNhan` AS `NgayNhan`,
        `ptp`.`NgayTra` AS `NgayTra`,
        `ptp`.`TienCoc` AS `TienCoc`,
        `ptp`.`ThanhTien` AS `ThanhTien`
    FROM
        (`pt_phong` `ptp`
        JOIN `phieuthue` `pt` ON ((`pt`.`MaPhieuThue` = `ptp`.`MaPhieuThue`)))
    WHERE
        ((`ptp`.`MaHoaDon` IS NULL)
            AND (`pt`.`ThanhToanCoc` = TRUE))
    ORDER BY `ptp`.`NgayNhan` DESC

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `view_tkdoanhthunam` AS
    SELECT 
        SUM(`hoadon`.`GiaTri`) AS `DoanhThu`,
        MONTH(`hoadon`.`NgayHoaDon`) AS `Thang`,
        YEAR(`hoadon`.`NgayHoaDon`) AS `Nam`
    FROM
        `hoadon`
    GROUP BY `Thang` , `Nam`
    ORDER BY `Nam` , `Thang`;

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `view_tkloaidichvuthang` AS
    SELECT 
        `ldv`.`TenLoaiDichVu` AS `TenLoaiDichVu`,
        SUM(`ptp_dv`.`ThanhTien`) AS `DoanhThu`,
        MONTH(`hd`.`NgayHoaDon`) AS `Thang`,
        YEAR(`hd`.`NgayHoaDon`) AS `Nam`
    FROM
        ((((`ptp_dv`
        JOIN `pt_phong` `ptp` ON ((`ptp_dv`.`MaPTPhong` = `ptp`.`MaPTPhong`)))
        JOIN `hoadon` `hd` ON ((`hd`.`MaHoaDon` = `ptp`.`MaHoaDon`)))
        JOIN `dichvu` `dv` ON ((`dv`.`MaDichVu` = `ptp_dv`.`MaDichVu`)))
        JOIN `loaidichvu` `ldv` ON ((`dv`.`MaLoaiDichVu` = `ldv`.`MaLoaiDichVu`)))
    GROUP BY `ldv`.`TenLoaiDichVu` , `Thang` , `Nam`
    ORDER BY `Nam` , `Thang`;

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `view_tkloaiphongthang` AS
    SELECT 
        `pt_phong`.`LoaiPhongThue` AS `LoaiPhongThue`,
        SUM(`pt_phong`.`ThanhTien`) AS `TienPhong`,
        MONTH(`pt_phong`.`NgayTra`) AS `Thang`,
        YEAR(`pt_phong`.`NgayTra`) AS `Nam`
    FROM
        `pt_phong`
    WHERE
        (`pt_phong`.`MaHoaDon` IS NOT NULL)
    GROUP BY `pt_phong`.`LoaiPhongThue` , `Thang` , `Nam`
    ORDER BY `Nam` , `Thang`;

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `view_tkluongkhachnam` AS
    SELECT 
        COUNT(0) AS `LuongKhach`,
        MONTH(`ptp`.`NgayNhan`) AS `Thang`,
        YEAR(`ptp`.`NgayNhan`) AS `Nam`
    FROM
        (`pt_phong` `ptp`
        JOIN `khachhang` `kh` ON ((`ptp`.`MaPTPhong` = `kh`.`MaPTPhong`)))
    GROUP BY `Thang` , `Nam`
    ORDER BY `Nam` , `Thang`;
