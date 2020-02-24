-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 23, 2020 at 06:56 AM
-- Server version: 5.7.24
-- PHP Version: 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pdam`
--
CREATE DATABASE IF NOT EXISTS `pdam` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `pdam`;

-- --------------------------------------------------------

--
-- Table structure for table `carousel`
--

CREATE TABLE `carousel` (
  `id` int(10) UNSIGNED NOT NULL,
  `nama_file` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `keterangan` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `carousel`
--

INSERT INTO `carousel` (`id`, `nama_file`, `keterangan`) VALUES
(2, 'carousel/20200117011909.jpg', 'Gambar 1'),
(3, 'carousel/20200117011915.jpg', 'Gambar 2'),
(4, 'carousel/20200117011923.jpg', 'Gambar 3');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id` int(11) NOT NULL,
  `id_plg` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id`, `id_plg`, `nama`, `alamat`, `email`, `mobile`, `latitude`, `longitude`) VALUES
(1, '001', 'nama 1', 'alamat 1', 'email@email.com', '0877', '-6.889836', '109.674591'),
(7, '0028', 'nama pelanggan', 'alamat pelanggan', 'rozaqabdur.rr@gmail.com', '08', '-6.889819206838533', '109.67391896992922'),
(8, '0281', 'amat', 'batang', 'rozaqabdur.rr@gmail.com', '08754', '-6.896360095086931', '109.66789271682501'),
(9, '002912', 'abdur rozaq', 'wonopringgo', 'rozaqabdur.rr@gmail.com', '085741', '-6.879216658419292', '109.67542737722397'),
(10, '0004', 'asdjkasjd kajs', 'Wonopringgo, Pekalongan', 'rozaqabdur.rr@gmail.com', '085741880658', '-6.984836', '109.678591');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(10) NOT NULL,
  `username` varchar(200) NOT NULL,
  `password` text NOT NULL,
  `nama` varchar(50) NOT NULL,
  `level_user` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `nama`, `level_user`) VALUES
(1, 'oik', 'c515adfb4489d8e2766aa3aa01a63e7e7009d9c9', 'Abdur Rozaq', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carousel`
--
ALTER TABLE `carousel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carousel`
--
ALTER TABLE `carousel`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
