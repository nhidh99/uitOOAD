CREATE TABLE `loaiphong` (
  `MaLoaiPhong` int(11) NOT NULL AUTO_INCREMENT,
  `TenLoaiPhong` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SoKhachToiDa` int(10) unsigned NOT NULL,
  `DonGia` decimal(15,0) unsigned NOT NULL,
  PRIMARY KEY (`MaLoaiPhong`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tinhtrang` (
  `MaTinhTrang` int(11) NOT NULL AUTO_INCREMENT,
  `TenTinhTrang` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`MaTinhTrang`)
) ENGINE=InnoDB AUTO_INCREMENT=11001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `phong` (
  `MaPhong` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `MaLoaiPhong` int(11) NOT NULL,
  `MaTinhTrang` int(11) NOT NULL,
  `MaPTPHienTai` int(11) DEFAULT NULL,
  `GhiChu` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaPhong`),
  KEY `fk_phong_loaiphong_idx` (`MaLoaiPhong`),
  KEY `fk_phong_tinhtrang_idx` (`MaTinhTrang`),
  CONSTRAINT `fk_phong_loaiphong` FOREIGN KEY (`MaLoaiPhong`) REFERENCES `loaiphong` (`MaLoaiPhong`),
  CONSTRAINT `fk_phong_tinhtrang` FOREIGN KEY (`MaTinhTrang`) REFERENCES `tinhtrang` (`MaTinhTrang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `nhanvien` (
  `MaNhanVien` int(11) NOT NULL AUTO_INCREMENT,
  `TenTaiKhoan` varchar(45) NOT NULL,
  `MatKhau` varchar(45) NOT NULL,
  `TenNhanVien` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CMND` varchar(45) NOT NULL,
  `SoDienThoai` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `DiaChi` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ChucVu` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaNhanVien`),
  UNIQUE KEY `TenTaiKhoan_UNIQUE` (`TenTaiKhoan`)
) ENGINE=InnoDB AUTO_INCREMENT=12001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hoadon` (
  `MaHoaDon` int(11) NOT NULL AUTO_INCREMENT,
  `MaNhanVien` int(11) NOT NULL,
  `NgayHoaDon` date NOT NULL,
  `TenKhachTra` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CMND` varchar(45) NOT NULL,
  `SoDienThoai` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `TongTienPhong` decimal(15,0) unsigned NOT NULL,
  `TongTienPTCK` decimal(15,0) NOT NULL,
  `TongTienDichVu` decimal(15,0) unsigned NOT NULL,
  `GiaTri` decimal(15,0) unsigned NOT NULL,
  `GhiChu` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaHoaDon`),
  KEY `fk_hd_nv_idx` (`MaNhanVien`),
  CONSTRAINT `fk_hd_nv` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB AUTO_INCREMENT=13001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pt_phong` (
  `MaPTPhong` int(11) NOT NULL AUTO_INCREMENT,
  `MaPhieuThue` int(11) DEFAULT NULL,
  `MaPhong` varchar(10) NOT NULL,
  `LoaiPhongThue` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SoKhachToiDa` int(11) NOT NULL,
  `NgayNhan` datetime NOT NULL,
  `NgayTra` datetime NOT NULL,
  `DonGiaThue` decimal(15,0) unsigned NOT NULL,
  `TienCoc` decimal(15,0) unsigned NOT NULL,
  `MaHoaDon` int(11) DEFAULT NULL,
  `ThanhTien` decimal(15,0) unsigned DEFAULT NULL,
  PRIMARY KEY (`MaPTPhong`),
  KEY `fk_ptp_hd_idx` (`MaHoaDon`,`MaPhieuThue`),
  KEY `fk_ptp_pt_idx` (`MaPhieuThue`),
  CONSTRAINT `fk_ptp_hd` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`),
  CONSTRAINT `fk_ptp_pt` FOREIGN KEY (`MaPhieuThue`) REFERENCES `phieuthue` (`MaPhieuThue`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `khachhang` (
  `MaKhachHang` int(11) NOT NULL AUTO_INCREMENT,
  `MaPTPhong` int(11) NOT NULL,
  `HoTen` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CMND` varchar(45) NOT NULL,
  `SoDienThoai` varchar(45) NOT NULL,
  `GioiTinh` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `QuocTich` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`MaKhachHang`),
  KEY `fk_kh_ptp_idx` (`MaPTPhong`),
  CONSTRAINT `fk_kh_ptp` FOREIGN KEY (`MaPTPhong`) REFERENCES `pt_phong` (`MaPTPhong`)
) ENGINE=InnoDB AUTO_INCREMENT=15001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `phieuthue` (
  `MaPhieuThue` int(11) NOT NULL AUTO_INCREMENT,
  `MaNhanVien` int(11) NOT NULL,
  `NgayLapPhieu` date NOT NULL,
  `TenKhachThue` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CMND` varchar(45) NOT NULL,
  `SoDienThoai` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `ThanhToanCoc` tinyint(1) NOT NULL DEFAULT '0',
  `TongTienCoc` decimal(15,0) unsigned NOT NULL DEFAULT '0',
  `GhiChu` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaPhieuThue`),
  KEY `fk_pt_nv_idx` (`MaNhanVien`),
  CONSTRAINT `fk_pt_nv` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB AUTO_INCREMENT=16001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `loaidichvu` (
  `MaLoaiDichVu` int(11) NOT NULL AUTO_INCREMENT,
  `TenLoaiDichVu` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `KhaDung` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaLoaiDichVu`)
) ENGINE=InnoDB AUTO_INCREMENT=17001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `nhacungcap` (
  `MaNhaCungCap` int(11) NOT NULL AUTO_INCREMENT,
  `TenNhaCungCap` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SoDienThoai` varchar(45) NOT NULL,
  PRIMARY KEY (`MaNhaCungCap`)
) ENGINE=InnoDB AUTO_INCREMENT=18001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `dichvu` (
  `MaDichVu` int(11) NOT NULL AUTO_INCREMENT,
  `TenDichVu` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DonViTinh` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SoLuongTon` int(10) DEFAULT NULL,
  `DonGia` decimal(15,0) NOT NULL,
  `MaLoaiDichVu` int(11) NOT NULL,
  `MaNhaCungCap` int(11) DEFAULT NULL,
  `KhaDung` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MaDichVu`),
  KEY `fk_dv_ldv_idx` (`MaLoaiDichVu`),
  KEY `fk_dv_ncc_idx` (`MaNhaCungCap`),
  CONSTRAINT `fk_dv_ldv` FOREIGN KEY (`MaLoaiDichVu`) REFERENCES `loaidichvu` (`MaLoaiDichVu`) ON DELETE RESTRICT,
  CONSTRAINT `fk_dv_ncc` FOREIGN KEY (`MaNhaCungCap`) REFERENCES `nhacungcap` (`MaNhaCungCap`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=19001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ptck_phong` (
  `MaPTCKPhong` int(11) NOT NULL AUTO_INCREMENT,
  `MaPTPhong` int(11) NOT NULL,
  `NoiDung` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SoLuong` int(10) unsigned NOT NULL,
  `DonGia` decimal(15,0) NOT NULL,
  `TriGia` decimal(15,0) NOT NULL,
  PRIMARY KEY (`MaPTCKPhong`),
  KEY `fk_ptck_phong_idx` (`MaPTPhong`),
  CONSTRAINT `fk_ptck_ptp` FOREIGN KEY (`MaPTPhong`) REFERENCES `pt_phong` (`MaPTPhong`)
) ENGINE=InnoDB AUTO_INCREMENT=20001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ptp_dv` (
  `MaPTPDV` int(11) NOT NULL AUTO_INCREMENT,
  `MaPTPhong` int(11) NOT NULL,
  `MaDichVu` int(11) NOT NULL,
  `SoLuong` int(10) unsigned NOT NULL,
  `GiaDichVu` decimal(15,0) unsigned NOT NULL,
  `ThanhTien` decimal(15,0) unsigned NOT NULL,
  PRIMARY KEY (`MaPTPDV`)
) ENGINE=InnoDB AUTO_INCREMENT=21001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ptck_hoadon` (
  `MaPTCKHD` int(11) NOT NULL AUTO_INCREMENT,
  `MaHoaDon` int(11) NOT NULL,
  `NoiDung` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TriGia` decimal(15,0) NOT NULL,
  PRIMARY KEY (`MaPTCKHD`),
  KEY `fk_ptck_hd_idx` (`MaHoaDon`),
  CONSTRAINT `fk_ptck_hd` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`)
) ENGINE=InnoDB AUTO_INCREMENT=22001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `thamso` (
  `TiLeTienCoc` float unsigned NOT NULL,
  `TiLeThueVAT` float unsigned NOT NULL,
  `PhuThuQuaKhach` float unsigned NOT NULL,
  `PhuThuTraPhongTre` float unsigned NOT NULL,
  `SoNgayTraCoc` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

