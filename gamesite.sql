-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2021. Máj 18. 21:35
-- Kiszolgáló verziója: 10.4.19-MariaDB
-- PHP verzió: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `gamesite`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `comment`
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
-- Tábla szerkezet ehhez a táblához `coupon`
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
-- Tábla szerkezet ehhez a táblához `coupon_type`
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
-- Tábla szerkezet ehhez a táblához `game`
--

CREATE TABLE `game` (
  `game_id` int(11) NOT NULL,
  `name` int(11) NOT NULL,
  `category` varchar(30) NOT NULL,
  `likes` int(11) NOT NULL,
  `releasedate` date NOT NULL,
  `isactive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `statistics`
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
-- Tábla szerkezet ehhez a táblához `user`
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
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `comment_user` (`user_id`),
  ADD KEY `comment_game` (`game_id`),
  ADD KEY `comment_reply_to` (`reply_to_id`);

--
-- A tábla indexei `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`coupon_id`),
  ADD KEY `coupon_user` (`user_id`),
  ADD KEY `coupon_coupon_type` (`coupon_type_id`);

--
-- A tábla indexei `coupon_type`
--
ALTER TABLE `coupon_type`
  ADD PRIMARY KEY (`coupon_type_id`);

--
-- A tábla indexei `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`game_id`);

--
-- A tábla indexei `statistics`
--
ALTER TABLE `statistics`
  ADD PRIMARY KEY (`statistics_id`),
  ADD KEY `statistics_user` (`user_id`),
  ADD KEY `statistics_game` (`game_id`);

--
-- A tábla indexei `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `coupon`
--
ALTER TABLE `coupon`
  MODIFY `coupon_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `coupon_type`
--
ALTER TABLE `coupon_type`
  MODIFY `coupon_type_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `game`
--
ALTER TABLE `game`
  MODIFY `game_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `statistics`
--
ALTER TABLE `statistics`
  MODIFY `statistics_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_game` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`),
  ADD CONSTRAINT `comment_reply_to` FOREIGN KEY (`reply_to_id`) REFERENCES `comment` (`comment_id`),
  ADD CONSTRAINT `comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Megkötések a táblához `coupon`
--
ALTER TABLE `coupon`
  ADD CONSTRAINT `coupon_coupon_type` FOREIGN KEY (`coupon_type_id`) REFERENCES `coupon_type` (`coupon_type_id`),
  ADD CONSTRAINT `coupon_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Megkötések a táblához `statistics`
--
ALTER TABLE `statistics`
  ADD CONSTRAINT `statistics_game` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`),
  ADD CONSTRAINT `statistics_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
