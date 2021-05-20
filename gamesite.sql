-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 20, 2021 at 10:59 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gamesite`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `addGame` (IN `in_name` VARCHAR(50) CHARSET utf8, IN `in_category` VARCHAR(30) CHARSET utf8, IN `in_description` VARCHAR(255) CHARSET utf8, IN `in_release` DATE)  NO SQL
INSERT INTO game (game.name, game.category, game.description, game.releasedate) VALUES(in_name, in_category, in_description,in_release)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `dislikeGame` (IN `in_id` INT)  NO SQL
UPDATE game SET game.likes = game.likes - 1 WHERE game.game_id = in_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllActiveGames` ()  NO SQL
SELECT * FROM game WHERE game.isactive =1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `likeGame` (IN `in_id` INT(11))  NO SQL
UPDATE game SET game.likes = game.likes+1 WHERE game.game_id = in_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logicalDeleteGame` (IN `in_id` INT(11))  NO SQL
UPDATE game SET game.isactive = 0 WHERE game.game_id = in_id$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `text` text NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `likes` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `reply_to_id` int(11) NOT NULL,
  `isactive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `coupon`
--

CREATE TABLE `coupon` (
  `coupon_id` int(11) NOT NULL,
  `purchase_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `isactive` tinyint(1) NOT NULL DEFAULT 1,
  `user_id` int(11) NOT NULL,
  `coupon_type_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `coupon_type`
--

CREATE TABLE `coupon_type` (
  `coupon_type_id` int(11) NOT NULL,
  `shop` varchar(20) NOT NULL,
  `value` int(11) NOT NULL,
  `purchaseable` tinyint(1) NOT NULL,
  `lasts` int(11) NOT NULL,
  `isactive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `game_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `category` varchar(30) NOT NULL,
  `likes` int(11) NOT NULL DEFAULT 0,
  `releasedate` date NOT NULL,
  `isactive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `statistics`
--

CREATE TABLE `statistics` (
  `statistics_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `first_played` datetime NOT NULL,
  `last_played` datetime NOT NULL,
  `played_minutes` int(11) NOT NULL,
  `isactive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` text NOT NULL,
  `email` varchar(100) NOT NULL,
  `birth_date` date NOT NULL,
  `isadmin` tinyint(1) NOT NULL,
  `currentpoints` int(11) NOT NULL,
  `isactive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `comment_user` (`user_id`),
  ADD KEY `comment_game` (`game_id`),
  ADD KEY `comment_reply_to` (`reply_to_id`);

--
-- Indexes for table `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`coupon_id`),
  ADD KEY `coupon_user` (`user_id`),
  ADD KEY `coupon_coupon_type` (`coupon_type_id`);

--
-- Indexes for table `coupon_type`
--
ALTER TABLE `coupon_type`
  ADD PRIMARY KEY (`coupon_type_id`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`game_id`);

--
-- Indexes for table `statistics`
--
ALTER TABLE `statistics`
  ADD PRIMARY KEY (`statistics_id`),
  ADD KEY `statistics_user` (`user_id`),
  ADD KEY `statistics_game` (`game_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `coupon`
--
ALTER TABLE `coupon`
  MODIFY `coupon_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `coupon_type`
--
ALTER TABLE `coupon_type`
  MODIFY `coupon_type_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `game_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `statistics`
--
ALTER TABLE `statistics`
  MODIFY `statistics_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_game` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`),
  ADD CONSTRAINT `comment_reply_to` FOREIGN KEY (`reply_to_id`) REFERENCES `comment` (`comment_id`),
  ADD CONSTRAINT `comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `coupon`
--
ALTER TABLE `coupon`
  ADD CONSTRAINT `coupon_coupon_type` FOREIGN KEY (`coupon_type_id`) REFERENCES `coupon_type` (`coupon_type_id`),
  ADD CONSTRAINT `coupon_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `statistics`
--
ALTER TABLE `statistics`
  ADD CONSTRAINT `statistics_game` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`),
  ADD CONSTRAINT `statistics_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
