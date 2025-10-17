-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2025 at 11:08 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `product_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `harga` double NOT NULL,
  `kategori` varchar(255) NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `nama`, `harga`, `kategori`, `rating`) VALUES
('P016', 'Laptop ASUS', 8500000, 'Elektronik', 5),
('P017', 'Mouse Logitech', 350000, 'Elektronik', 4),
('P018', 'Keyboard Mechanical', 750000, 'Elektronik', 4),
('P019', 'Roti Tawar', 15000, 'Makanan', 5),
('P020', 'Susu UHT', 12000, 'Minuman', 4),
('P021', 'Kemeja Putih', 125000, 'Pakaian', 5),
('P022', 'Celana Jeans', 200000, 'Pakaian', 4),
('P023', 'Pensil 2B', 3000, 'Alat Tulis', 5),
('P024', 'Buku Tulis', 8000, 'Alat Tulis', 4),
('P025', 'Air Mineral', 5000, 'Minuman', 4),
('P026', 'Smartphone Samsung', 4500000, 'Elektronik', 5),
('P027', 'Kue Brownies', 25000, 'Makanan', 4),
('P028', 'Jaket Hoodie', 180000, 'Pakaian', 5),
('P029', 'Pulpen Gel', 5000, 'Alat Tulis', 4),
('P030', 'Teh Botol', 8000, 'Minuman', 5),
('P031', 'Snack Kentang', 12000, 'Makanan', 4),
('P032', 'Blazer Hitam', 210000, 'Pakaian', 5),
('P033', 'Notebook Spiral', 9000, 'Alat Tulis', 4),
('P034', 'Earphone Bluetooth', 300000, 'Elektronik', 5),
('P035', 'Jus Apel', 10000, 'Minuman', 4),
('P036', 'pensil', 2000, 'Alat Tulis', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
