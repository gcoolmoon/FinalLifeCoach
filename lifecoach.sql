-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2015 at 05:51 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `lifecoach`
--

-- --------------------------------------------------------

--
-- Table structure for table `awarenessinformation`
--

CREATE TABLE IF NOT EXISTS `awarenessinformation` (
  `awrenessinfoId` int(11) NOT NULL AUTO_INCREMENT,
  `awarenessInformation` varchar(1000) NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`awrenessinfoId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `caregiiver`
--

CREATE TABLE IF NOT EXISTS `caregiiver` (
  `careGiverId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(300) NOT NULL,
  `lastName` varchar(300) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`careGiverId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `dailysuggestions`
--

CREATE TABLE IF NOT EXISTS `dailysuggestions` (
  `dailysuggId` int(11) NOT NULL AUTO_INCREMENT,
  `lifestyleDefId` int(11) DEFAULT NULL,
  `motivationalSuggestion` varchar(300) DEFAULT NULL,
  `improvemetSuggestions` varchar(300) DEFAULT NULL,
  `date` int(11) DEFAULT NULL,
  PRIMARY KEY (`dailysuggId`),
  KEY `lifestyleDefId` (`lifestyleDefId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- RELATIONS FOR TABLE `dailysuggestions`:
--   `lifestyleDefId`
--       `lifestylemeasuremnt` -> `lifestyleDefid`
--

-- --------------------------------------------------------

--
-- Table structure for table `healthgoal`
--

CREATE TABLE IF NOT EXISTS `healthgoal` (
  `goalId` int(11) NOT NULL AUTO_INCREMENT,
  `healthmeasureDefid` int(11) DEFAULT NULL,
  `measuredBy` varchar(300) NOT NULL,
  `goalValue` float NOT NULL,
  `duration` date DEFAULT NULL,
  PRIMARY KEY (`goalId`),
  KEY `lifestylemeasureDefid` (`healthmeasureDefid`),
  KEY `healthmeasureDefid` (`healthmeasureDefid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- RELATIONS FOR TABLE `healthgoal`:
--   `healthmeasureDefid`
--       `healthmeasurement` -> `healthmeasureDefId`
--

-- --------------------------------------------------------

--
-- Table structure for table `healthmeasurehistory`
--

CREATE TABLE IF NOT EXISTS `healthmeasurehistory` (
  `healthmeasureHistoryid` int(11) NOT NULL AUTO_INCREMENT,
  `personId` int(11) DEFAULT NULL,
  `healthmeasureDefid` int(11) DEFAULT NULL,
  `healthValue` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `isCurrent` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`healthmeasureHistoryid`),
  KEY `healthmeasureDefid` (`healthmeasureDefid`),
  KEY `personId` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- RELATIONS FOR TABLE `healthmeasurehistory`:
--   `healthmeasureDefid`
--       `healthmeasurement` -> `healthmeasureDefId`
--   `personId`
--       `person` -> `perosnId`
--

-- --------------------------------------------------------

--
-- Table structure for table `healthmeasurement`
--

CREATE TABLE IF NOT EXISTS `healthmeasurement` (
  `healthmeasureDefId` int(11) NOT NULL AUTO_INCREMENT,
  `healthmeasureDefinition` varchar(300) DEFAULT NULL,
  `measuredBy` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`healthmeasureDefId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lifestylegoal`
--

CREATE TABLE IF NOT EXISTS `lifestylegoal` (
  `goalId` int(11) NOT NULL AUTO_INCREMENT,
  `lifestylemeasureDefid` int(11) DEFAULT NULL,
  `measuredBy` varchar(300) DEFAULT NULL,
  `goalValue` float DEFAULT NULL,
  `duration` date DEFAULT NULL,
  PRIMARY KEY (`goalId`),
  KEY `lifestylemeasureDefid` (`lifestylemeasureDefid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lifestylemeasurehistory`
--

CREATE TABLE IF NOT EXISTS `lifestylemeasurehistory` (
  `lifestylemeasureId` int(11) NOT NULL AUTO_INCREMENT,
  `lifestyleDefid` int(11) DEFAULT NULL,
  `personId` int(11) DEFAULT NULL,
  `lifestylevalue` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  `IsCurrent` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`lifestylemeasureId`),
  KEY `lifestyleDefid` (`lifestyleDefid`),
  KEY `personId` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- RELATIONS FOR TABLE `lifestylemeasurehistory`:
--   `lifestyleDefid`
--       `lifestylemeasuremnt` -> `lifestyleDefid`
--   `personId`
--       `person` -> `perosnId`
--

-- --------------------------------------------------------

--
-- Table structure for table `lifestylemeasuremnt`
--

CREATE TABLE IF NOT EXISTS `lifestylemeasuremnt` (
  `lifestyleDefid` int(11) NOT NULL AUTO_INCREMENT,
  `lifestyleDefinition` varchar(300) NOT NULL,
  `measuredBy` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`lifestyleDefid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `perosnId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(300) NOT NULL,
  `lastName` varchar(300) NOT NULL,
  `email` varchar(300) DEFAULT NULL,
  `userName` varchar(300) DEFAULT NULL,
  `password` varchar(300) DEFAULT NULL,
  `createdDate` date DEFAULT NULL,
  `careGiverId` int(11) DEFAULT NULL,
  PRIMARY KEY (`perosnId`),
  KEY `careGiverId_idx` (`careGiverId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- RELATIONS FOR TABLE `person`:
--   `careGiverId`
--       `caregiiver` -> `careGiverId`
--

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`perosnId`, `firstName`, `lastName`, `email`, `userName`, `password`, `createdDate`, `careGiverId`) VALUES
(1, 'Gebrem', 'dedhen', 'sd@g.com', 'gc', 'mno', '2015-06-02', NULL),
(2, 'Tg', 'Pallino', NULL, NULL, NULL, '1984-07-21', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `reminder`
--

CREATE TABLE IF NOT EXISTS `reminder` (
  `reminderId` int(11) NOT NULL AUTO_INCREMENT,
  `personId` int(11) DEFAULT NULL,
  `appointmentType` varchar(300) DEFAULT NULL,
  `appointmentDescription` varchar(300) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`reminderId`),
  KEY `personId` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- RELATIONS FOR TABLE `reminder`:
--   `personId`
--       `person` -> `perosnId`
--

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dailysuggestions`
--
ALTER TABLE `dailysuggestions`
  ADD CONSTRAINT `dailysuggestions_ibfk_1` FOREIGN KEY (`lifestyleDefId`) REFERENCES `lifestylemeasuremnt` (`lifestyleDefid`);

--
-- Constraints for table `healthgoal`
--
ALTER TABLE `healthgoal`
  ADD CONSTRAINT `healthgoal_ibfk_1` FOREIGN KEY (`healthmeasureDefid`) REFERENCES `healthmeasurement` (`healthmeasureDefId`);

--
-- Constraints for table `healthmeasurehistory`
--
ALTER TABLE `healthmeasurehistory`
  ADD CONSTRAINT `healthmeasurehistory_ibfk_1` FOREIGN KEY (`healthmeasureDefid`) REFERENCES `healthmeasurement` (`healthmeasureDefId`),
  ADD CONSTRAINT `healthmeasurehistory_ibfk_2` FOREIGN KEY (`personId`) REFERENCES `person` (`perosnId`);

--
-- Constraints for table `lifestylemeasurehistory`
--
ALTER TABLE `lifestylemeasurehistory`
  ADD CONSTRAINT `lifestylemeasurehistory_ibfk_1` FOREIGN KEY (`lifestyleDefid`) REFERENCES `lifestylemeasuremnt` (`lifestyleDefid`),
  ADD CONSTRAINT `lifestylemeasurehistory_ibfk_2` FOREIGN KEY (`personId`) REFERENCES `person` (`perosnId`);

--
-- Constraints for table `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `careGiverId` FOREIGN KEY (`careGiverId`) REFERENCES `caregiiver` (`careGiverId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `reminder`
--
ALTER TABLE `reminder`
  ADD CONSTRAINT `reminder_ibfk_1` FOREIGN KEY (`personId`) REFERENCES `person` (`perosnId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
