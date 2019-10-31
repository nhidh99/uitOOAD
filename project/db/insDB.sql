INSERT INTO ThamSo (TiLeTienCoc, TiLeThueVAT, PhuThuQuaKhach, PhuThuTraPhongTre)
VALUES (0.5, 0.1, 0.3, 0.3);

INSERT INTO TinhTrang (TenTinhTrang) VALUES ('Thuê'), ('Trống'), ('Hỏng'), ('Sửa'), ('Bẩn');

INSERT INTO LoaiPhong (TenLoaiPhong, SoKhachToiDa, DonGia) VALUES
('Single', 1, 150000),
('Double', 2, 200000),
('Triple', 3, 250000),
('Quad', 4, 300000),
('Double-Double', 4, 400000);

INSERT INTO LoaiDichVu (TenLoaiDichVu) VALUES
('Ăn uống'), ('Giặt ủi'), ('Giải trí'), ('Làm đẹp');

INSERT INTO NhaCungCap (TenNhaCungCap, SoDienThoai) VALUES
('Nhà xe Ba Minh', '0352411876'),
('Đại lí nước Cô Tư', '0369552114'),
('Đại lí nước Sài Gòn', '0902551206'),
('Bar New Land', '0493225394');

INSERT INTO NhanVien (TenTaiKhoan, MatKhau, TenNhanVien, CMND, SoDienThoai, Email, DiaChi, ChucVu) VALUES
('nhidh99', 'nhidh99', 'Đinh Hoàng Nhi', '17520853', '0123456789', 'nhidh99@gmail.com', 'Cần Giờ', 'Quản lí'),
('truongpt99', 'truongpt99', 'Phạm Trung Trường', '17520186', '0987654321', 'truongpt99@gmail.com', 'Nam Định', 'Kế toán'),
('datvt99', 'datvt99', 'Vương Thịnh Đạt', '17520343', '0246813579', 'datvt99@gmail.com', 'Bình Dương', 'Lễ tân'),
('thangvm99', 'thangvm99', 'Vũ Minh Thắng', '17521041', '0135792468', 'thangvm99@gmail.com', 'Kon Tum', 'Lễ tân');

INSERT INTO Phong VALUES ('101', 10001, 11002, NULL);
INSERT INTO Phong VALUES ('102', 10002, 11001, 'Thiếu 1 gối');
INSERT INTO Phong VALUES ('103', 10003, 11003, 'Hỏng giường');