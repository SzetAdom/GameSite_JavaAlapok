-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 23, 2021 at 03:03 PM
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `addComment` (IN `in_text` VARCHAR(255) CHARSET utf8mb4, IN `in_likes` INT(11), IN `in_user_id` INT(11), IN `in_game_id` INT(11))  NO SQL
INSERT INTO comment (comment.`text`, comment.`likes`, comment.`user_id`, comment.`game_id`) VALUES(in_text, in_likes, in_user_id, in_game_id)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addCoupon` (IN `in_user_id` INT(11), IN `in_coupon_type_id` INT(11))  INSERT INTO coupon (coupon.user_id, coupon.coupon_type_id) VALUES(in_user_id, in_coupon_type_id)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addCouponType` (IN `in_shop` VARCHAR(20), IN `in_value` INT(11), IN `in_purchaseable` TINYINT(1), IN `in_lasts` INT(11))  INSERT INTO coupon_type (coupon_type.shop, coupon_type.value, coupon_type.purchaseable, coupon_type.lasts) VALUES(in_shop, in_value, in_purchaseable, in_lasts)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addGame` (IN `in_name` VARCHAR(50) CHARSET utf8, IN `in_category` VARCHAR(30) CHARSET utf8, IN `in_description` VARCHAR(255) CHARSET utf8, IN `in_release` DATE)  NO SQL
INSERT INTO game (game.name, game.category, game.description, game.releasedate) VALUES(in_name, in_category, in_description,in_release)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addStatistics` (IN `in_game_id` INT(11), IN `in_user_id` INT(11), IN `in_first_played` DATETIME, IN `in_last_played` DATETIME, IN `in_played_minutes` INT(11))  NO SQL
INSERT INTO statistics (statistics.game_id, statistics.user_id, statistics.first_played, statistics.last_played,statistics.played_minutes) VALUES(in_game_id, in_user_id, in_first_played,in_last_played,in_played_minutes)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addUser` (IN `in_username` VARCHAR(20) CHARSET utf8mb4, IN `in_password` TEXT CHARSET utf8mb4, IN `in_email` VARCHAR(100) CHARSET utf8mb4, IN `in_birth_date` DATE, IN `in_is_admin` BOOLEAN, IN `in_current_points` INT(11))  NO SQL
INSERT INTO user(user.username, user.password, user.email, user.birth_date,user.isadmin,user.currentpoints) VALUES (in_username, SHA1(in_password), in_email, in_birth_date, in_is_admin, in_current_points)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `dislikeGame` (IN `in_id` INT)  NO SQL
UPDATE game SET game.likes = game.likes - 1 WHERE game.game_id = in_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllActiveComment` ()  NO SQL
SELECT * FROM comment WHERE comment.isactive = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllActiveCoupons` ()  SELECT * FROM coupon WHERE coupon.isactive = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllActiveCouponTypes` ()  SELECT * FROM coupon_type WHERE coupon_type.isactive = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllActiveGames` ()  NO SQL
SELECT * FROM game WHERE game.isactive =1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllActiveStatistics` ()  NO SQL
SELECT * FROM statistics WHERE statistics.isactive = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllActiveUser` ()  NO SQL
SELECT * FROM user WHERE user.isactive =1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllPurchaseableCouponTypes` ()  SELECT * FROM coupon_type WHERE coupon_type.purchaseable = 1 AND coupon_type.isactive = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getMostActivePlayer` ()  NO SQL
SELECT user.user_id FROM user WHERE user.user_id IN (SELECT statistics.user_id FROM statistics WHERE statistics.played_minutes IN (SELECT MAX(statistics.played_minutes) FROM statistics) ORDER BY statistics.played_minutes DESC)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getStatisticsById` (IN `in_id` INT(11))  NO SQL
SELECT * FROM statistics WHERE statistics.isactive = 1 AND statistics.statistics_id = in_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getTotalPlayedMinutes` (OUT `out_total_played_minutes` INT)  NO SQL
SELECT SUM(statistics.played_minutes) INTO out_total_played_minutes FROM statistics$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUsersCoupons` (IN `in_user_id` INT(11))  SELECT * FROM coupon WHERE coupon.user_id = in_user_id AND coupon.isactive = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `likeGame` (IN `in_id` INT(11))  NO SQL
UPDATE game SET game.likes = game.likes+1 WHERE game.game_id = in_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logicalDeleteComment` (IN `in_id` INT(11))  NO SQL
UPDATE comment SET comment.isactive = 0 WHERE comment.comment_id = in_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logicalDeleteCoupon` (IN `in_id` INT(11))  UPDATE coupon SET coupon.isactive = 0 WHERE coupon.coupon_id = in_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logicalDeleteCouponType` (IN `in_id` INT(11))  UPDATE coupon_type SET coupon_type.isactive = 0 WHERE coupon_type.coupon_type_id = in_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logicalDeleteGame` (IN `in_id` INT(11))  NO SQL
UPDATE game SET game.isactive = 0 WHERE game.game_id = in_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logicalDeleteStatistics` (IN `in_id` INT(11))  NO SQL
UPDATE statistics SET statistics.isactive = 0 WHERE statistics.statistics_id = in_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logicalDeleteUser` (IN `in_id` INT(11))  NO SQL
UPDATE user SET user.isactive = 0 WHERE user.user_id = in_id$$

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
  `isactive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `text`, `time`, `likes`, `user_id`, `game_id`, `isactive`) VALUES
(2, 'Nagyon jó game', '2021-05-23 13:01:41', 100, 3, 23, 1);

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

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`game_id`, `name`, `description`, `category`, `likes`, `releasedate`, `isactive`) VALUES
(23, 'Game name', 'Boom boom', 'Shooter', 0, '2021-05-31', 1);

-- --------------------------------------------------------

--
-- Table structure for table `statistics`
--

CREATE TABLE `statistics` (
  `statistics_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `first_played` date NOT NULL,
  `last_played` date NOT NULL,
  `played_minutes` int(11) NOT NULL,
  `isactive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `statistics`
--

INSERT INTO `statistics` (`statistics_id`, `game_id`, `user_id`, `first_played`, `last_played`, `played_minutes`, `isactive`) VALUES
(15, 23, 3, '2021-03-27', '2021-04-04', 10, 1);

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
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `email`, `birth_date`, `isadmin`, `currentpoints`, `isactive`) VALUES
(3, 'Username', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'email@mail.com', '2001-04-05', 1, 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `comment_user` (`user_id`),
  ADD KEY `comment_game` (`game_id`);

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
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
  MODIFY `game_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `statistics`
--
ALTER TABLE `statistics`
  MODIFY `statistics_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_game` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`),
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
