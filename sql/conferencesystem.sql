-- phpMyAdmin SQL Dump
-- version 4.0.8
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Июл 22 2015 г., 15:23
-- Версия сервера: 5.5.40-0ubuntu0.14.04.1
-- Версия PHP: 5.5.9-1ubuntu4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `conferencesystem`
--
CREATE DATABASE IF NOT EXISTS `conferencesystem` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `conferencesystem`;

-- --------------------------------------------------------

--
-- Структура таблицы `Conference`
--

DROP TABLE IF EXISTS `Conference`;
CREATE TABLE IF NOT EXISTS `Conference` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `organizer_login` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_q4tixu43gq1jkkv4epvpmpmqy` (`organizer_login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Conference`
--

TRUNCATE TABLE `Conference`;
--
-- Дамп данных таблицы `Conference`
--

INSERT INTO `Conference` (`id`, `description`, `endDate`, `name`, `startDate`, `organizer_login`) VALUES
(1, 'conf', '2015-02-02', 'conf', '2015-01-01', 'org');

-- --------------------------------------------------------

--
-- Структура таблицы `Conference_Visitor`
--

DROP TABLE IF EXISTS `Conference_Visitor`;
CREATE TABLE IF NOT EXISTS `Conference_Visitor` (
  `Conference_id` int(11) NOT NULL,
  `visitors_login` varchar(255) NOT NULL,
  KEY `FK_112sywrqss2awg680kqdwk29v` (`visitors_login`),
  KEY `FK_k5lqyq7uipnumdgy3k1a2loxr` (`Conference_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Conference_Visitor`
--

TRUNCATE TABLE `Conference_Visitor`;
-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `hibernate_sequence`
--

TRUNCATE TABLE `hibernate_sequence`;
--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(4);

-- --------------------------------------------------------

--
-- Структура таблицы `Moderator`
--

DROP TABLE IF EXISTS `Moderator`;
CREATE TABLE IF NOT EXISTS `Moderator` (
  `login` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Moderator`
--

TRUNCATE TABLE `Moderator`;
--
-- Дамп данных таблицы `Moderator`
--

INSERT INTO `Moderator` (`login`, `email`, `name`, `phone`) VALUES
('mod', 'org@org.org', 'Mod', '12345');

-- --------------------------------------------------------

--
-- Структура таблицы `ModeratorRequestsSpeech`
--

DROP TABLE IF EXISTS `ModeratorRequestsSpeech`;
CREATE TABLE IF NOT EXISTS `ModeratorRequestsSpeech` (
  `id` int(11) NOT NULL,
  `approved` bit(1) NOT NULL,
  `moderator_login` varchar(255) DEFAULT NULL,
  `speech_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ravndei9rklqme5j9nsllpfyf` (`moderator_login`),
  KEY `FK_a8k95yl3e29m92w8u2ak4i886` (`speech_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `ModeratorRequestsSpeech`
--

TRUNCATE TABLE `ModeratorRequestsSpeech`;
-- --------------------------------------------------------

--
-- Структура таблицы `Organizer`
--

DROP TABLE IF EXISTS `Organizer`;
CREATE TABLE IF NOT EXISTS `Organizer` (
  `login` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Organizer`
--

TRUNCATE TABLE `Organizer`;
--
-- Дамп данных таблицы `Organizer`
--

INSERT INTO `Organizer` (`login`, `email`, `name`, `phone`) VALUES
('org', 'org@org.org', 'Org', '12345');

-- --------------------------------------------------------

--
-- Структура таблицы `Question`
--

DROP TABLE IF EXISTS `Question`;
CREATE TABLE IF NOT EXISTS `Question` (
  `id` int(11) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `answered` bit(1) DEFAULT NULL,
  `moderated` bit(1) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `speech_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rjuyhcoeyuud9235xehotnjc7` (`speech_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Question`
--

TRUNCATE TABLE `Question`;
-- --------------------------------------------------------

--
-- Структура таблицы `Reporter`
--

DROP TABLE IF EXISTS `Reporter`;
CREATE TABLE IF NOT EXISTS `Reporter` (
  `login` varchar(255) NOT NULL,
  `busy` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Reporter`
--

TRUNCATE TABLE `Reporter`;
--
-- Дамп данных таблицы `Reporter`
--

INSERT INTO `Reporter` (`login`, `busy`, `email`, `name`, `phone`) VALUES
('rep', b'0', 'org@org.org', 'Reporter', '12345');

-- --------------------------------------------------------

--
-- Структура таблицы `ReporterRequestsSpeech`
--

DROP TABLE IF EXISTS `ReporterRequestsSpeech`;
CREATE TABLE IF NOT EXISTS `ReporterRequestsSpeech` (
  `id` int(11) NOT NULL,
  `approved` bit(1) NOT NULL,
  `reporter_login` varchar(255) DEFAULT NULL,
  `speech_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3qmx22tn0ktx1syvhxlwqrui8` (`reporter_login`),
  KEY `FK_9kke3aal5a05pdw9qpjvpmotq` (`speech_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `ReporterRequestsSpeech`
--

TRUNCATE TABLE `ReporterRequestsSpeech`;
-- --------------------------------------------------------

--
-- Структура таблицы `Room`
--

DROP TABLE IF EXISTS `Room`;
CREATE TABLE IF NOT EXISTS `Room` (
  `number` int(11) NOT NULL,
  `busy` bit(1) NOT NULL,
  `capacity` int(11) DEFAULT NULL,
  `roomOwner_login` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`number`),
  KEY `FK_ajfi6d4unsrngejtcnuf7oo31` (`roomOwner_login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Room`
--

TRUNCATE TABLE `Room`;
-- --------------------------------------------------------

--
-- Структура таблицы `RoomOrder`
--

DROP TABLE IF EXISTS `RoomOrder`;
CREATE TABLE IF NOT EXISTS `RoomOrder` (
  `id` int(11) NOT NULL,
  `dateFrom` datetime DEFAULT NULL,
  `dateTo` datetime DEFAULT NULL,
  `room_number` int(11) DEFAULT NULL,
  `speech_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_eg2vy7rtq6isnheav8j0s0g1f` (`room_number`),
  KEY `FK_rywwbqurgf89whb3fd4qa1o8w` (`speech_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `RoomOrder`
--

TRUNCATE TABLE `RoomOrder`;
-- --------------------------------------------------------

--
-- Структура таблицы `RoomOwner`
--

DROP TABLE IF EXISTS `RoomOwner`;
CREATE TABLE IF NOT EXISTS `RoomOwner` (
  `login` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `RoomOwner`
--

TRUNCATE TABLE `RoomOwner`;
--
-- Дамп данных таблицы `RoomOwner`
--

INSERT INTO `RoomOwner` (`login`, `email`, `name`, `phone`) VALUES
('ro', 'org@org.org', 'RO', '12345');

-- --------------------------------------------------------

--
-- Структура таблицы `Speaker`
--

DROP TABLE IF EXISTS `Speaker`;
CREATE TABLE IF NOT EXISTS `Speaker` (
  `login` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Speaker`
--

TRUNCATE TABLE `Speaker`;
--
-- Дамп данных таблицы `Speaker`
--

INSERT INTO `Speaker` (`login`, `email`, `name`, `phone`) VALUES
('sp', 'org@org.org', 'Speaker', '12345');

-- --------------------------------------------------------

--
-- Структура таблицы `Speech`
--

DROP TABLE IF EXISTS `Speech`;
CREATE TABLE IF NOT EXISTS `Speech` (
  `id` int(11) NOT NULL,
  `approved` bit(1) NOT NULL,
  `completed` bit(1) NOT NULL,
  `startDate` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `topic` varchar(255) DEFAULT NULL,
  `conference_id` int(11) DEFAULT NULL,
  `roomOrder_id` int(11) DEFAULT NULL,
  `speaker_login` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ae10x1lnh6ihy6x7ay74mkf9v` (`conference_id`),
  KEY `FK_k9sf0aqlgkkx1bn5a657l2lc7` (`roomOrder_id`),
  KEY `FK_7ap1y0wlpu2fwg0ebbylhoief` (`speaker_login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Speech`
--

TRUNCATE TABLE `Speech`;
--
-- Дамп данных таблицы `Speech`
--

INSERT INTO `Speech` (`id`, `approved`, `completed`, `startDate`, `text`, `topic`, `conference_id`, `roomOrder_id`, `speaker_login`) VALUES
(2, b'0', b'0', '2015-01-01 10:10:10', '', 'Startaps', 1, NULL, 'sp');

-- --------------------------------------------------------

--
-- Структура таблицы `Speech_Visitor`
--

DROP TABLE IF EXISTS `Speech_Visitor`;
CREATE TABLE IF NOT EXISTS `Speech_Visitor` (
  `Speech_id` int(11) NOT NULL,
  `visitors_login` varchar(255) NOT NULL,
  KEY `FK_358sgw0mv79w7ppgb27kdhsxw` (`visitors_login`),
  KEY `FK_p3s3n14yrgi03mubx36n4n9hs` (`Speech_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Speech_Visitor`
--

TRUNCATE TABLE `Speech_Visitor`;
-- --------------------------------------------------------

--
-- Структура таблицы `TestEntity`
--

DROP TABLE IF EXISTS `TestEntity`;
CREATE TABLE IF NOT EXISTS `TestEntity` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Очистить таблицу перед добавлением данных `TestEntity`
--

TRUNCATE TABLE `TestEntity`;
-- --------------------------------------------------------

--
-- Структура таблицы `Translation`
--

DROP TABLE IF EXISTS `Translation`;
CREATE TABLE IF NOT EXISTS `Translation` (
  `id` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `speech_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f97bb958gi4s910rbyplq7pd1` (`speech_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Translation`
--

TRUNCATE TABLE `Translation`;
--
-- Дамп данных таблицы `Translation`
--

INSERT INTO `Translation` (`id`, `text`, `timestamp`, `speech_id`) VALUES
(3, 'taks-tas', '2015-04-04 10:10:10', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `UserRoles`
--

DROP TABLE IF EXISTS `UserRoles`;
CREATE TABLE IF NOT EXISTS `UserRoles` (
  `username` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `UserRoles`
--

TRUNCATE TABLE `UserRoles`;
--
-- Дамп данных таблицы `UserRoles`
--

INSERT INTO `UserRoles` (`username`, `role`) VALUES
('mod', 'moderator'),
('org', 'organizer'),
('rep', 'reporter'),
('ro', 'roomOwner'),
('sp', 'speaker'),
('vis', 'visitor');

-- --------------------------------------------------------

--
-- Структура таблицы `Users`
--

DROP TABLE IF EXISTS `Users`;
CREATE TABLE IF NOT EXISTS `Users` (
  `username` varchar(255) NOT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Users`
--

TRUNCATE TABLE `Users`;
--
-- Дамп данных таблицы `Users`
--

INSERT INTO `Users` (`username`, `passwd`) VALUES
('mod', 'mod'),
('org', 'org'),
('rep', 'rep'),
('ro', 'ro'),
('sp', 'sp'),
('vis', 'vis');

-- --------------------------------------------------------

--
-- Структура таблицы `Visitor`
--

DROP TABLE IF EXISTS `Visitor`;
CREATE TABLE IF NOT EXISTS `Visitor` (
  `login` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Visitor`
--

TRUNCATE TABLE `Visitor`;
--
-- Дамп данных таблицы `Visitor`
--

INSERT INTO `Visitor` (`login`, `email`, `name`, `phone`) VALUES
('vis', 'org@org.org', 'Visitor', '12345');

-- --------------------------------------------------------

--
-- Структура таблицы `Visitor_Question`
--

DROP TABLE IF EXISTS `Visitor_Question`;
CREATE TABLE IF NOT EXISTS `Visitor_Question` (
  `Visitor_login` varchar(255) NOT NULL,
  `questions_id` int(11) NOT NULL,
  KEY `FK_nfoul56mje3ie6l8ml377bugj` (`questions_id`),
  KEY `FK_ahr9uqlm5p3bqkok24qaivpki` (`Visitor_login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Очистить таблицу перед добавлением данных `Visitor_Question`
--

TRUNCATE TABLE `Visitor_Question`;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `Conference`
--
ALTER TABLE `Conference`
  ADD CONSTRAINT `FK_q4tixu43gq1jkkv4epvpmpmqy` FOREIGN KEY (`organizer_login`) REFERENCES `Organizer` (`login`);

--
-- Ограничения внешнего ключа таблицы `Conference_Visitor`
--
ALTER TABLE `Conference_Visitor`
  ADD CONSTRAINT `FK_k5lqyq7uipnumdgy3k1a2loxr` FOREIGN KEY (`Conference_id`) REFERENCES `Conference` (`id`),
  ADD CONSTRAINT `FK_112sywrqss2awg680kqdwk29v` FOREIGN KEY (`visitors_login`) REFERENCES `Visitor` (`login`);

--
-- Ограничения внешнего ключа таблицы `ModeratorRequestsSpeech`
--
ALTER TABLE `ModeratorRequestsSpeech`
  ADD CONSTRAINT `FK_a8k95yl3e29m92w8u2ak4i886` FOREIGN KEY (`speech_id`) REFERENCES `Speech` (`id`),
  ADD CONSTRAINT `FK_ravndei9rklqme5j9nsllpfyf` FOREIGN KEY (`moderator_login`) REFERENCES `Moderator` (`login`);

--
-- Ограничения внешнего ключа таблицы `Question`
--
ALTER TABLE `Question`
  ADD CONSTRAINT `FK_rjuyhcoeyuud9235xehotnjc7` FOREIGN KEY (`speech_id`) REFERENCES `Speech` (`id`);

--
-- Ограничения внешнего ключа таблицы `ReporterRequestsSpeech`
--
ALTER TABLE `ReporterRequestsSpeech`
  ADD CONSTRAINT `FK_9kke3aal5a05pdw9qpjvpmotq` FOREIGN KEY (`speech_id`) REFERENCES `Speech` (`id`),
  ADD CONSTRAINT `FK_3qmx22tn0ktx1syvhxlwqrui8` FOREIGN KEY (`reporter_login`) REFERENCES `Reporter` (`login`);

--
-- Ограничения внешнего ключа таблицы `Room`
--
ALTER TABLE `Room`
  ADD CONSTRAINT `FK_ajfi6d4unsrngejtcnuf7oo31` FOREIGN KEY (`roomOwner_login`) REFERENCES `RoomOwner` (`login`);

--
-- Ограничения внешнего ключа таблицы `RoomOrder`
--
ALTER TABLE `RoomOrder`
  ADD CONSTRAINT `FK_rywwbqurgf89whb3fd4qa1o8w` FOREIGN KEY (`speech_id`) REFERENCES `Speech` (`id`),
  ADD CONSTRAINT `FK_eg2vy7rtq6isnheav8j0s0g1f` FOREIGN KEY (`room_number`) REFERENCES `Room` (`number`);

--
-- Ограничения внешнего ключа таблицы `Speech`
--
ALTER TABLE `Speech`
  ADD CONSTRAINT `FK_7ap1y0wlpu2fwg0ebbylhoief` FOREIGN KEY (`speaker_login`) REFERENCES `Speaker` (`login`),
  ADD CONSTRAINT `FK_ae10x1lnh6ihy6x7ay74mkf9v` FOREIGN KEY (`conference_id`) REFERENCES `Conference` (`id`),
  ADD CONSTRAINT `FK_k9sf0aqlgkkx1bn5a657l2lc7` FOREIGN KEY (`roomOrder_id`) REFERENCES `RoomOrder` (`id`);

--
-- Ограничения внешнего ключа таблицы `Speech_Visitor`
--
ALTER TABLE `Speech_Visitor`
  ADD CONSTRAINT `FK_p3s3n14yrgi03mubx36n4n9hs` FOREIGN KEY (`Speech_id`) REFERENCES `Speech` (`id`),
  ADD CONSTRAINT `FK_358sgw0mv79w7ppgb27kdhsxw` FOREIGN KEY (`visitors_login`) REFERENCES `Visitor` (`login`);

--
-- Ограничения внешнего ключа таблицы `Translation`
--
ALTER TABLE `Translation`
  ADD CONSTRAINT `FK_f97bb958gi4s910rbyplq7pd1` FOREIGN KEY (`speech_id`) REFERENCES `Speech` (`id`);

--
-- Ограничения внешнего ключа таблицы `Visitor_Question`
--
ALTER TABLE `Visitor_Question`
  ADD CONSTRAINT `FK_ahr9uqlm5p3bqkok24qaivpki` FOREIGN KEY (`Visitor_login`) REFERENCES `Visitor` (`login`),
  ADD CONSTRAINT `FK_nfoul56mje3ie6l8ml377bugj` FOREIGN KEY (`questions_id`) REFERENCES `Question` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
