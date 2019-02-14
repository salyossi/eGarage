-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 14, 2019 at 04:19 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `egarage`
--

-- --------------------------------------------------------

--
-- Table structure for table `egarageusage`
--

DROP TABLE IF EXISTS `egarageusage`;
CREATE TABLE IF NOT EXISTS `egarageusage` (
  `CarID` int(11) NOT NULL,
  `GarageEnterance` datetime NOT NULL,
  `ParkingSlotEnterance` datetime DEFAULT NULL,
  `TiketPaid` datetime DEFAULT NULL,
  `ParkingSlotExit` datetime DEFAULT NULL,
  `GarageExit` datetime DEFAULT NULL,
  `Authorized` tinyint(1) NOT NULL DEFAULT '0',
  `Level` int(11) DEFAULT NULL,
  `Slot` int(11) DEFAULT NULL,
  PRIMARY KEY (`CarID`),
  KEY `IXFK_EgarageUsage_ParkingList` (`Level`,`Slot`),
  KEY `IXFK_EgarageUsage_UserList` (`CarID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `parkinglist`
--

DROP TABLE IF EXISTS `parkinglist`;
CREATE TABLE IF NOT EXISTS `parkinglist` (
  `Level` int(11) NOT NULL,
  `Slot` int(11) NOT NULL,
  `Type` int(11) NOT NULL,
  `SlotUsed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Level`,`Slot`),
  KEY `IXFK_ParkingList_Types` (`Type`),
  KEY `Usage` (`SlotUsed`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `parkinglist`
--

INSERT INTO `parkinglist` (`Level`, `Slot`, `Type`, `SlotUsed`) VALUES
(1, 1, 3, 0),
(1, 2, 3, 0),
(1, 3, 1, 0),
(1, 4, 1, 0),
(1, 5, 1, 0),
(1, 6, 1, 0),
(1, 7, 1, 0),
(1, 8, 1, 0),
(1, 9, 1, 0),
(1, 10, 2, 0),
(2, 1, 3, 0),
(2, 2, 3, 0),
(2, 3, 1, 0),
(2, 4, 1, 0),
(2, 5, 1, 0),
(2, 6, 1, 0),
(2, 7, 1, 0),
(2, 8, 1, 0),
(2, 9, 1, 0),
(2, 10, 2, 0),
(3, 1, 3, 0),
(3, 2, 3, 0),
(3, 3, 1, 0),
(3, 4, 1, 0),
(3, 5, 1, 0),
(3, 6, 1, 0),
(3, 7, 1, 0),
(3, 8, 1, 0),
(3, 9, 1, 0),
(3, 10, 2, 0),
(0, 1, 3, 0),
(0, 2, 3, 0),
(0, 3, 1, 0),
(0, 4, 1, 0),
(0, 5, 1, 0),
(0, 6, 1, 0),
(0, 7, 1, 0),
(0, 8, 1, 0),
(0, 9, 1, 0),
(0, 10, 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
CREATE TABLE IF NOT EXISTS `types` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TymeName` mediumtext COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `types`
--

INSERT INTO `types` (`ID`, `TymeName`) VALUES
(1, 'General'),
(2, 'VIP'),
(3, 'Handicaps');

-- --------------------------------------------------------

--
-- Table structure for table `userlist`
--

DROP TABLE IF EXISTS `userlist`;
CREATE TABLE IF NOT EXISTS `userlist` (
  `CarID` int(11) NOT NULL,
  `Type` int(11) NOT NULL,
  PRIMARY KEY (`CarID`),
  KEY `IXFK_UserList_Types` (`Type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `userlist`
--

INSERT INTO `userlist` (`CarID`, `Type`) VALUES
(22642201, 1),
(12345678, 1),
(12345677, 1),
(12345676, 1),
(12345675, 1),
(12345674, 1),
(12345673, 1),
(22222222, 2),
(33333333, 3),
(12345671, 1),
(12312312, 1),
(16948925, 1),
(46791345, 1),
(123456784, 1),
(12121212, 1),
(31313131, 3),
(12345672, 1),
(14141414, 1),
(24242424, 1),
(34343434, 1),
(97979797, 1),
(15151515, 1),
(18181818, 1),
(98989898, 1),
(16161616, 1),
(36363636, 1),
(45454545, 1),
(46464646, 1),
(91919191, 1),
(64646464, 1),
(44444444, 1),
(55555555, 1),
(96969696, 1),
(79797979, 1),
(52525252, 1),
(11111111, 1),
(46467979, 1),
(37373737, 1),
(333333333, 1),
(46793164, 1),
(46798255, 1),
(545454656, 1),
(1916141718, 1),
(649522548, 1),
(464971684, 1),
(66666666, 1),
(99999999, 1),
(88888888, 1),
(77777777, 1),
(123456789, 1),
(12344678, 1),
(21212121, 2),
(12356788, 1),
(125412874, 1),
(15427874, 1),
(11111112, 1),
(121212121, 1),
(12345786, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
