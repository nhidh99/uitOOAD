CREATE TABLE `danhmuc` (
  `MaDM` varchar(10) NOT NULL,
  `TenDM` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaDM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sanpham` (
  `MaSP` varchar(10) NOT NULL,
  `TenSP` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` decimal(15,0) NOT NULL,
  `XuatXu` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MaDM` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`MaSP`),
  CONSTRAINT `PK_MaDM` FOREIGN KEY (`MaDM`) REFERENCES `danhmuc` (`MaDM`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO DANHMUC VALUES ('dm01', 'Sách');
INSERT INTO DANHMUC VALUES ('dm02', 'Điện thoại');
INSERT INTO DANHMUC VALUES ('dm03', 'Quần áo');
INSERT INTO DANHMUC VALUES ('dm05', 'Trang sức');

INSERT INTO SANPHAM VALUES ('er','df','5','5','fg','dm05');
INSERT INTO SANPHAM VALUES ('fg','fg','4','4','f','dm01');
INSERT INTO SANPHAM VALUES ('sp01','Lập trình Windows nâng cao','10','10000','HUI','dm01');
INSERT INTO SANPHAM VALUES ('sp02','Cẩm nang làm đẹp','20','20000','Kim Đồng','dm01');
INSERT INTO SANPHAM VALUES ('sp03','iPhone5','17','14000000','US','dm02');
INSERT INTO SANPHAM VALUES ('sp04','Nokia1202','30','300000','Trung Quốc','dm02');
INSERT INTO SANPHAM VALUES ('sp043','Nokia1202','30','300000','Trung Quốc','dm01');
INSERT INTO SANPHAM VALUES ('sp05','Áo sơ mi nam','50','100000','Việt Nam','dm03');
INSERT INTO SANPHAM VALUES ('sp06','Quần Jeans','24','350000','Việt Nam','dm03');
INSERT INTO SANPHAM VALUES ('sp089','Tivi','15','5000000','Sony','dm01');
INSERT INTO SANPHAM VALUES ('sp09','Mắt kính','3','25000000','Việt Nam','dm05');
INSERT INTO SANPHAM VALUES ('sp0999','Cẩm nang làm xau','20','20000','Kim Đồng','dm01');
INSERT INTO SANPHAM VALUES ('sp10','Dây chuyền bạc','9','500000','Việt Nam','dm05');
INSERT INTO SANPHAM VALUES ('sp103','Dây chuyền bạc','9','500000','Việt Nam','dm01');
INSERT INTO SANPHAM VALUES ('sp109','Dây chuyền bạc','9','500000','Việt Nam','dm05');
INSERT INTO SANPHAM VALUES ('sp13','Tivi lg','15','5000000','Sony','dm01');