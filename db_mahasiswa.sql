-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 24 Mar 2024 pada 05.35
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mahasiswa`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(255) NOT NULL,
  `sarapan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nim`, `nama`, `jenis_kelamin`, `sarapan`) VALUES
(1, '2203999', 'Amelia Zalfa Julianti', 'Perempuan', 'Ya'),
(2, '2202292', 'Muhammad Iqbal Fadhilah', 'Laki-laki', 'Tidak'),
(3, '2202346', 'Muhammad Rifky Afandi', 'Laki-laki', 'Tidak'),
(4, '2210239', 'Muhammad Hanif Abdillah', 'Laki-laki', 'Ya'),
(5, '2202046', 'Nurainun', 'Perempuan', 'Ya'),
(6, '2205101', 'Kelvin Julian Putra', 'Laki-laki', 'Tidak'),
(7, '2200163', 'Rifanny Lysara Annastasya', 'Perempuan', 'Tidak'),
(8, '2202869', 'Revana Faliha Salma', 'Perempuan', 'Ya'),
(9, '2209489', 'Rakha Dhifiargo Hariadi', 'Laki-laki', 'Ya'),
(10, '2203142', 'Roshan Syalwan Nurilham', 'Laki-laki', 'Ya'),
(11, '2200311', 'Raden Rahman Ismail', 'Laki-laki', 'Ya'),
(12, '2200978', 'Ratu Syahirah Khairunnisa', 'Perempuan', 'Tidak'),
(13, '2204509', 'Muhammad Fahreza Fauzan', 'Laki-laki', 'Tidak'),
(14, '2205027', 'Muhammad Rizki Revandi', 'Laki-laki', 'Ya'),
(15, '2203484', 'Arya Aydin Margono', 'Laki-laki', 'Ya'),
(16, '2200481', 'Marvel Ravindra Dioputra', 'Laki-laki', 'Ya'),
(17, '2209889', 'Muhammad Fadlul Hafiizh', 'Laki-laki', 'Tidak'),
(18, '2206697', 'Rifa Sania', 'Perempuan', 'Tidak'),
(19, '2207260', 'Imam Chalish Rafidhul Haque', 'Laki-laki', 'Tidak'),
(20, '2204343', 'Meiva Labibah Putri', 'Perempuan', 'Ya'),
(24, '2205714', 'Raya Cahya', 'Perempuan', 'Ya');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
