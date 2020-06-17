-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- 主機: 127.0.0.1
-- 產生時間： 2016-10-02 02:51:20
-- 伺服器版本: 10.1.13-MariaDB
-- PHP 版本： 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `ceo_db`
--
DROP DATABASE `ceo_db`;
CREATE DATABASE IF NOT EXISTS `ceo_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `ceo_db`;

-- --------------------------------------------------------

--
-- 資料表結構 `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `AdminID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `LoginAccount` varchar(100) NOT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `admin`
--

INSERT INTO `admin` (`AdminID`, `Name`, `LoginAccount`) VALUES
(1, 'admin', 'publicstatics@gmail.com');

-- --------------------------------------------------------

--
-- 資料表結構 `blacklist`
--

CREATE TABLE IF NOT EXISTS `blacklist` (
  `BlacklistID` int(11) NOT NULL AUTO_INCREMENT,
  `Account` varchar(100) NOT NULL,
  `Status` int(11) NOT NULL,
  `BlackListDate` date DEFAULT NULL,
  `Admin` int(11) NOT NULL,
  PRIMARY KEY (`BlacklistID`),
  KEY `blacklist_fk1` (`Admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表結構 `boardgame`
--

CREATE TABLE IF NOT EXISTS `boardgame` (
  `BoardGameID` int(11) NOT NULL AUTO_INCREMENT,
  `BoardGameName` varchar(100) NOT NULL,
  `BoardGameDetail` varchar(1000) DEFAULT NULL,
  `BoardGameType` int(11) DEFAULT NULL,
  `Year` year(4) NOT NULL,
  `Price` double(10,2) NOT NULL DEFAULT '0.00',
  `Quantity` int(11) NOT NULL DEFAULT '0',
  `Player_Minimum` int(11) NOT NULL DEFAULT '1',
  `Player_Maximum` int(11) DEFAULT NULL,
  `LimitationAge` int(3) DEFAULT NULL,
  `Photo` varchar(100) DEFAULT NULL,
  `Status` int(11) NOT NULL,
  PRIMARY KEY (`BoardGameID`),
  KEY `boardgame_fk1` (`BoardGameType`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `boardgame`
--

INSERT INTO `boardgame` (`BoardGameID`, `BoardGameName`, `BoardGameDetail`, `BoardGameType`, `Year`, `Price`, `Quantity`, `Player_Minimum`, `Player_Maximum`, `LimitationAge`, `Photo`, `Status`) VALUES
(1, '金象牌 - 頂上茉莉香米', '未有詳細資料', 1, 2012, 130.00, 10, 1, 4, 12, '6.jpg', 0),
(2, '三花 - 火腿餐肉', '未有詳細資料', 2, 2015, 480.00, 10, 2, 4, 12, '5.jpg', 0),
(3, '刀嘜 - 純正粟米油', '成分 : \n純正粟米油 : \n\n用途 : \n適合煎炒炸等各種用途', 14, 1901, 100.00, 10, 2, 6, 8, '2.jpg', 0),
(4, '太古 - 原蔗冰糖', '選用優質原蔗糖製造，帶有獨特濃郁的甘蔗甜味及色澤，更保留了甘蔗原有之天然礦物質，健康有益。是製作各類中式甜品及巧燉各式佳餚的天然健康之選。', 13, 2013, 215.00, 10, 2, 5, 8, '3.jpg', 0),
(5, '保利 - 純牛奶', '原裝澳洲進口\n\n100%無添加純鮮牛奶\n\n純牛奶最天然美味，提供豐富的蛋白質、維生素和礦物質，非常適合一家人享用\n\n通過ISO 9001 及 HACCP 品質檢定\n\n容量 : 1 升', 15, 2014, 285.00, 10, 2, 4, 9, '4.jpg', 0),
(6, '家樂牌 - 家樂牌雞粉', '用上等雞肉製成，鮮雞味十足，至能帶出食物鮮味；無論醃肉、炒菜，樣樣皆能，啟發無限煮食靈感。有家樂牌雞粉，自然有至高味覺享受。用家樂牌雞粉代替鹽作炒菜調味，能使炒出來的菜蔬更鮮甜翠綠。', 13, 1901, 30.00, 10, 2, 6, 8, '1.jpg', 0);

-- --------------------------------------------------------

--
-- 資料表結構 `boardgamebooking`
--

CREATE TABLE IF NOT EXISTS `boardgamebooking` (
  `BookingID` int(11) NOT NULL AUTO_INCREMENT,
  `BoardGameID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL DEFAULT '1',
  `TotalPrice` double(20,2) NOT NULL DEFAULT '0.00',
  `MemberName` varchar(100) NOT NULL,
  `Contact` char(8) NOT NULL,
  `OrderDate` date NOT NULL,
  `OrderTime` time NOT NULL,
  `ReceiptDate` date DEFAULT NULL,
  `ReceiptTime` time DEFAULT NULL,
  `Status` int(11) NOT NULL,
  PRIMARY KEY (`BookingID`),
  KEY `boardgamebooking_fk1` (`BoardGameID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表結構 `boardgametype`
--

CREATE TABLE IF NOT EXISTS `boardgametype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `boardgametype`
--

INSERT INTO `boardgametype` (`ID`, `Type`) VALUES
(1, '米'),
(2, '罐頭'),
(3, '水果'),
(4, '熱食'),
(5, '嬰兒食品'),
(6, '營養餐'),
(7, '副糧'),
(8, '凍肉'),
(9, '蔬菜'),
(10, '餐劵'),
(11, '新鮮食物劵'),
(12, '麵'),
(13, '調味料'),
(14, '糧油'),
(15, '飲品');

-- --------------------------------------------------------

--
-- 資料表結構 `calendar_event`
--

CREATE TABLE IF NOT EXISTS `calendar_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event` varchar(300) DEFAULT NULL,
  `description` text NOT NULL,
  `day` int(8) DEFAULT NULL,
  `month` int(8) DEFAULT NULL,
  `year` int(8) DEFAULT NULL,
  `time_from` varchar(10) NOT NULL,
  `time_until` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表結構 `coupon`
--

CREATE TABLE IF NOT EXISTS `coupon` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CouponName` varchar(200) NOT NULL,
  `PhotoName` varchar(200) NOT NULL,
  `Status` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `coupon`
--

INSERT INTO `coupon` (`ID`, `CouponName`, `PhotoName`, `Status`) VALUES
(1, 'Sports Authority', '10001.jpg', 1),
(2, 'Dotdot..TM', '10002.jpg', 1),
(3, '屈臣氏', '10003.jpg', 1),
(4, '媽媽畫室', '10004.jpg', 1),
(5, '萬寧 Mannings', '10005.png', 1),
(6, '免費換', '10006.jpg', 1);

-- --------------------------------------------------------

--
-- 資料表結構 `gatheringbattle`
--

CREATE TABLE IF NOT EXISTS `gatheringbattle` (
  `EventID` int(11) NOT NULL AUTO_INCREMENT,
  `BoardGameID` int(11) NOT NULL,
  `EventName` varchar(100) NOT NULL,
  `MemberName` varchar(100) NOT NULL,
  `Account` varchar(200) NOT NULL,
  `Contact` char(8) NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `EndTime` time NOT NULL,
  `Place` int(11) NOT NULL,
  `ParticipantRequirement` int(11) NOT NULL DEFAULT '1',
  `Status` int(11) NOT NULL,
  `JoinedParticipant` varchar(500) DEFAULT '',
  `JoinedParticipantToken` varchar(200) DEFAULT '',
  `Photo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`EventID`),
  KEY `gatheringbattle_fk1` (`BoardGameID`),
  KEY `gatheringbattle_fk2` (`Place`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `gatheringbattle`
--

INSERT INTO `gatheringbattle` (`EventID`, `BoardGameID`, `EventName`, `MemberName`, `Account`, `Contact`, `Date`, `Time`, `EndTime`, `Place`, `ParticipantRequirement`, `Status`, `JoinedParticipant`, `JoinedParticipantToken`, `Photo`) VALUES
(1, 1, '糧友行動食物助人計劃', '陳姑娘', 'Tom@gmail.com', '12345678', '2016-10-25', '16:00:00', '19:00:00', 1, 20, -1, '[{"nickName":"Tom","extraPeople":""}, {"nickName":"Ken","extraPeople":"2"}]', '[{"token":"Tom@gmail.com"}, {"token":"Ken@gmail.com"}]', 'event1.png'),
(2, 1, '食德好食物回收', '李姑娘', 'Peter@yahoo.com.hk', '12345678', '2016-10-28', '16:00:00', '20:00:00', 1, 15, -1, '[{"nickName":"Peter","extraPeople":""}, {"nickName":"David","extraPeople":"1"}, {"nickName":"John","extraPeople":""}]', '[{"token":"Peter@yahoo.com.hk"}, {"token":"David@gmail.com"}, {"token":"John@gmail.com"}]', 'event2.png');

-- --------------------------------------------------------

--
-- 資料表結構 `gatheringbattleprice`
--

CREATE TABLE IF NOT EXISTS `gatheringbattleprice` (
  `PriceID` int(11) NOT NULL AUTO_INCREMENT,
  `Monday` double(11,2) NOT NULL,
  `Tuesday` double(11,2) NOT NULL,
  `Wednesday` double(11,2) NOT NULL,
  `Thursday` double(11,2) NOT NULL,
  `Friday` double(11,2) NOT NULL,
  `Saturday` double(11,2) NOT NULL,
  `Sunday` double(11,2) NOT NULL,
  PRIMARY KEY (`PriceID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `gatheringbattleprice`
--

INSERT INTO `gatheringbattleprice` (`PriceID`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`, `Saturday`, `Sunday`) VALUES
(1, 45.00, 45.00, 45.00, 45.00, 50.00, 50.00, 50.00);

-- --------------------------------------------------------

--
-- 資料表結構 `indexsetting`
--

CREATE TABLE IF NOT EXISTS `indexsetting` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `ShowItem` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `indexsetting`
--

INSERT INTO `indexsetting` (`ID`, `Name`, `ShowItem`) VALUES
(1, 'boardgame', 6),
(2, 'gatheringbattle', 5);

-- --------------------------------------------------------

--
-- 資料表結構 `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `LocationID` int(11) NOT NULL AUTO_INCREMENT,
  `Place` varchar(100) NOT NULL,
  PRIMARY KEY (`LocationID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `location`
--

INSERT INTO `location` (`LocationID`, `Place`) VALUES
(1, '香港油麻地廣東道 831A-H 號金華大廈 D2F 號地下');

-- --------------------------------------------------------

--
-- 資料表結構 `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SuccessTitle` varchar(100) NOT NULL,
  `CancelTitle` varchar(100) NOT NULL,
  `SuccessBody` varchar(300) NOT NULL,
  `CancelBody` varchar(300) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表結構 `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
  `NotificationID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) NOT NULL,
  `Body` varchar(300) NOT NULL,
  `Uid` varchar(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`NotificationID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表結構 `photo`
--

CREATE TABLE IF NOT EXISTS `photo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PhotoName` varchar(200) NOT NULL,
  `Status` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `photo`
--

INSERT INTO `photo` (`ID`, `PhotoName`, `Status`) VALUES
(1, '1.png', 1),
(2, '2.png', 1),
(3, '3.png', 1),
(4, '4.png', 1),
(5, '5.png', 1);

-- --------------------------------------------------------

--
-- 資料表結構 `privatebooking`
--

CREATE TABLE IF NOT EXISTS `privatebooking` (
  `BookingID` int(11) NOT NULL AUTO_INCREMENT,
  `MemberName` varchar(100) NOT NULL,
  `Account` varchar(200) NOT NULL,
  `Contact` char(8) NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `EndTime` time NOT NULL,
  `Place` int(11) NOT NULL,
  `NumberOfPeople` int(11) NOT NULL DEFAULT '1',
  `TotalPrice` double(11,2) NOT NULL DEFAULT '0.00',
  `Discount` double(11,2) DEFAULT '0.00',
  `Remark` varchar(200) DEFAULT NULL,
  `Photo` varchar(40) DEFAULT NULL,
  `Status` int(11) NOT NULL,
  PRIMARY KEY (`BookingID`),
  KEY `privatebooking_fk1` (`Place`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表結構 `privatebookingprice`
--

CREATE TABLE IF NOT EXISTS `privatebookingprice` (
  `PriceID` int(11) NOT NULL AUTO_INCREMENT,
  `BasicPrice` double(11,2) NOT NULL,
  `BasicPeople` int(11) NOT NULL,
  `BasicHour` int(11) NOT NULL,
  `ExtraFoodPricePerPeople` double(11,2) NOT NULL,
  `ExtraPricePerHour` double(11,2) NOT NULL,
  `ExtraPricePerPeople` double(11,2) NOT NULL,
  PRIMARY KEY (`PriceID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `privatebookingprice`
--

INSERT INTO `privatebookingprice` (`PriceID`, `BasicPrice`, `BasicPeople`, `BasicHour`, `ExtraFoodPricePerPeople`, `ExtraPricePerHour`, `ExtraPricePerPeople`) VALUES
(1, 600.00, 6, 4, 60.00, 100.00, 100.00);

-- --------------------------------------------------------

--
-- 資料表結構 `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `Token` varchar(200) NOT NULL,
  `Account` varchar(200) DEFAULT NULL,
  `ReceiveNotification` char(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- 已匯出資料表的限制(Constraint)
--

--
-- 資料表的 Constraints `blacklist`
--
ALTER TABLE `blacklist`
  ADD CONSTRAINT `blacklist_fk1` FOREIGN KEY (`Admin`) REFERENCES `admin` (`AdminID`);

--
-- 資料表的 Constraints `boardgame`
--
ALTER TABLE `boardgame`
  ADD CONSTRAINT `boardgame_fk1` FOREIGN KEY (`BoardGameType`) REFERENCES `boardgametype` (`ID`);

--
-- 資料表的 Constraints `boardgamebooking`
--
ALTER TABLE `boardgamebooking`
  ADD CONSTRAINT `boardgamebooking_fk1` FOREIGN KEY (`BoardGameID`) REFERENCES `boardgame` (`BoardGameID`);

--
-- 資料表的 Constraints `gatheringbattle`
--
ALTER TABLE `gatheringbattle`
  ADD CONSTRAINT `gatheringbattle_fk1` FOREIGN KEY (`BoardGameID`) REFERENCES `boardgame` (`BoardGameID`),
  ADD CONSTRAINT `gatheringbattle_fk2` FOREIGN KEY (`Place`) REFERENCES `location` (`LocationID`);

--
-- 資料表的 Constraints `privatebooking`
--
ALTER TABLE `privatebooking`
  ADD CONSTRAINT `privatebooking_fk1` FOREIGN KEY (`Place`) REFERENCES `location` (`LocationID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
