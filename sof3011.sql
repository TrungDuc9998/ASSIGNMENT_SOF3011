-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2022 at 02:41 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sof3011`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `ten` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `ten`, `user_id`) VALUES
(15, 'quần áo khoác', 12),
(17, 'Váy', 12),
(20, 'quần âu', 12),
(21, 'áo phông', 12),
(23, 'Áo thun', 12);

-- --------------------------------------------------------

--
-- Table structure for table `invoices`
--

CREATE TABLE `invoices` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `ThanhTien` int(20) NOT NULL,
  `ThoiGian` datetime DEFAULT NULL,
  `DiaChi` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `invoices`
--

INSERT INTO `invoices` (`id`, `user_id`, `product_id`, `soLuong`, `ThanhTien`, `ThoiGian`, `DiaChi`, `TrangThai`) VALUES
(47, 41, 11, 1, 100, '2022-04-12 22:14:53', 'Sơn Tây,Hà Nội', 2),
(48, 41, 1, 1, 2500000, '2022-04-12 22:14:53', 'Sơn Tây,Hà Nội', 1),
(49, 41, 19, 4, 380000, '2022-04-12 22:14:53', 'Sơn Tây,Hà Nội', 2),
(50, 41, 20, 4, 500000, '2022-04-12 22:14:53', 'Sơn Tây,Hà Nội', 1),
(51, 50, 4, 1, 1000000, '2022-04-12 23:16:00', 'Phú Thọ', 2),
(52, 50, 8, 1, 100, '2022-04-12 23:16:00', 'Phú Thọ', 1),
(53, 50, 1, 1, 2500000, '2022-04-12 23:16:00', 'Phú Thọ', 2),
(54, 50, 17, 1, 100000, '2022-04-12 23:16:00', 'Phú Thọ', 0),
(55, 12, 1, 12, 30000000, '2022-04-14 16:40:39', 'Thanh Hoá', 0),
(56, 12, 4, 1, 1000000, '2022-04-14 16:40:39', 'Thanh Hoá', 0),
(57, 12, 8, 1, 100, '2022-04-14 16:40:39', 'Thanh Hoá', 0),
(58, 12, 1, 1, 2500000, '2022-04-14 16:40:39', 'Thanh Hoá', 0),
(59, 12, 1, 1, 2500000, '2022-04-14 16:40:39', 'Thanh Hoá', 0),
(60, 12, 1, 1, 2500000, '2022-04-14 20:55:19', 'Quang Trung,Hà Đông,Hà Nội', 0),
(61, 12, 8, 1, 100, '2022-04-14 20:55:19', 'Quang Trung,Hà Đông,Hà Nội', 0),
(62, 41, 1, 1, 2500000, '2022-04-14 21:58:04', 'Sơn Tây,Hà Nội', 0),
(63, 41, 4, 1, 1000000, '2022-04-14 21:58:04', 'Sơn Tây,Hà Nội', 0),
(64, 41, 1, 1, 2500000, '2022-04-14 22:00:56', 'Sơn Tây,Hà Nội', 0);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `ten` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `so_luong` int(11) NOT NULL DEFAULT 0,
  `don_gia` int(15) NOT NULL DEFAULT 0,
  `mau_sac` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `kich_thuoc` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `ten`, `so_luong`, `don_gia`, `mau_sac`, `kich_thuoc`, `img`, `category_id`) VALUES
(1, 'VÁY BODY chất len tăm dày dặn ', 1, 2500000, 'trắng', 'L', '48bae39282529be73d275580e197bfa8_tn.jfif', 17),
(4, 'Áo sơ mi nam tay ngắn SIze S', 30, 1000000, 'xanh', 'XL', '1a2a52c4b383226b532846c499181ee0_tn.jfif', 15),
(8, 'áo hiệu víp hàng hiếm ', 100, 100, 'bạc', 'M', '4f855c2dfa74685912a296c0d07b69f6_tn.jfif', 15),
(9, 'Khoác hiệu Billabong xách tay', 30, 100000000, 'đỏ', 'L', '36eb6250ced696085a642c63c1a88a4b_tn.jfif', 15),
(10, 'Áo khoác nỉ ngoại chống nắng ', 1000, 200000, 'xam', 'XL', '62aadcc844d6b89319e7d1442ab93d93_tn.jfif', 15),
(11, 'Áo khoác gió nam nữ, áo dù 2 lớp ', 30, 100, 'trang', 'M', '695e9dc9a04bb44abbdb4ed43764f940_tn.jfif', 15),
(12, 'Áo Khoác Dù TMS LOGO MIDSIDE', 100, 10000, 'xanh', 'S', '8648fa9dbb866fa492c2a27a0e01b115_tn.jfif', 15),
(16, 'Áo Khoác Nike NSW Windrunner ', 30, 1000000, 'trang', 'XXL', '856c5f6856b8b335d29132b868e7bcb0_tn.jfif', 15),
(17, 'Áo khoác 2hand tuyển Crocodile 42', 30, 100000, 'đỏ', 'XL', '8873b313a55338a42908d0cc3d3c88a4_tn.jfif', 15),
(18, 'Áo Phông Unisex Form Rộng Basic', 100, 85000, 'Đen', 'S', '917efae99109a60f70501c58ea7d8fda_tn.jfif', 21),
(19, 'Áo thun sad boiz sadtagram tee', 100, 95000, 'Đen', 'M', 'f37e2f3762bbb5efe6612bdf258c6392_tn.jfif', 23),
(20, 'Áo thun sad boiz sadtagram tee', 50, 125000, 'Xám', 'XL', '85e453a562e2c5248393c623cd0386d1_tn.jfif', 20),
(25, 'hoa hoe1', 30, 100000, 'trang,xanh,do,tim,vang,den,nau,tim', 'XL', 'bf5fff0da1d8d8bdc176de71748421fe_tn.jfif', 15);

-- --------------------------------------------------------

--
-- Table structure for table `userproducts`
--

CREATE TABLE `userproducts` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `soLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `userproducts`
--

INSERT INTO `userproducts` (`id`, `user_id`, `product_id`, `soLuong`) VALUES
(9, NULL, 1, 1),
(22, 17, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `ho_ten` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gioi_tinh` int(11) NOT NULL DEFAULT 1,
  `sdt` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dia_chi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `admin` int(11) NOT NULL DEFAULT 1,
  `avatar` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `ho_ten`, `gioi_tinh`, `sdt`, `dia_chi`, `email`, `password`, `admin`, `avatar`) VALUES
(12, 'DucNT', 1, '0947564383', 'Quang Trung,Hà Đông,Hà Nội', 'ducntph17741@fpt.edu.vn', '$2a$10$xssrNcQxRsTyqDazzhENY.AZmW8KKG1gmpYYX/9/ZGBqCATIyoKVe', 0, 'Form Login CSS.png'),
(41, 'DucNT', 1, '0342873614', 'Sơn Tây,Hà Nội', 'ducntph17745@fpt.edu.vn', '$2a$10$dClSbMAJwLV0jdaIhZ./uO7Au2g2ch5hHlJNnfI3KOeh6fAeC.Fme', 1, 'de_kt.png'),
(43, 'LucNT', 1, '0947564382', 'Sầm Sơn,Thanh Hoá', 'lucnt98@fpt.edu.vn', '$2a$10$34Zm3guqqeDZHFFdlaxMteMs6DPmKTz2pcT2znkC4GhQ1rn4POq1a', 0, 'anh_lop.jpg'),
(50, 'Trung Đức', 1, '0909875125', 'Phú Thọ', 'okpro@gmail.com', '$2a$10$XirB5ldrDsLCa.tA/nQ1MeGWOCGB342r9Sb3LbG2Sy8Rvr.gf.xmK', 1, 'd44d38acb91580fe22727182034d26df_tn.jfif'),
(61, 'NgocNT', 1, '0909875642', 'Phú Thọ', 'ngocnt@gmail.com', '$2a$10$BUFW2Zfv5s1pFbyzt.A8uO2mhEvJir8/OjlyWKAT1RJ/dSQwpzPl.', 1, 'de_kt.png'),
(66, 'Cho Doan', 1, '0909875698', 'Ninh Binh', 'nguyettrinh260802@gmail.com', '$2a$10$HM2vxoxKd8zyuPRANrDn7.tO4Lt7iEcxUcHgCgBTjzPfqnfogSj7G', 1, '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userproducts`
--
ALTER TABLE `userproducts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `sdt` (`sdt`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `invoices`
--
ALTER TABLE `invoices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `userproducts`
--
ALTER TABLE `userproducts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
