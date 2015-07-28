-- phpMyAdmin SQL Dump
-- version 4.3.9
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Июл 28 2015 г., 23:32
-- Версия сервера: 5.5.38-0ubuntu0.14.04.1
-- Версия PHP: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `conferencesystem`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Conference`
--

CREATE TABLE IF NOT EXISTS `Conference` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `organizer_login` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Conference`
--

INSERT INTO `Conference` (`id`, `description`, `endDate`, `name`, `startDate`, `organizer_login`) VALUES
(1, 'desc1', '2015-07-17', 'conf1', '2015-07-16', 'org');

-- --------------------------------------------------------

--
-- Структура таблицы `Conference_Visitor`
--

CREATE TABLE IF NOT EXISTS `Conference_Visitor` (
  `conferences_id` int(11) NOT NULL,
  `visitors_login` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Conference_Visitor`
--

INSERT INTO `Conference_Visitor` (`conferences_id`, `visitors_login`) VALUES
(1, 'vis');

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(4);

-- --------------------------------------------------------

--
-- Структура таблицы `Moderator`
--

CREATE TABLE IF NOT EXISTS `Moderator` (
  `login` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Moderator`
--

INSERT INTO `Moderator` (`login`, `email`, `name`, `phone`) VALUES
('mod', 'mod@mail.ru', 'Moderator', '123456');

-- --------------------------------------------------------

--
-- Структура таблицы `ModeratorRequestsSpeech`
--

CREATE TABLE IF NOT EXISTS `ModeratorRequestsSpeech` (
  `id` int(11) NOT NULL,
  `approved` bit(1) NOT NULL,
  `moderator_login` varchar(255) DEFAULT NULL,
  `speech_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `ModeratorRequestsSpeech`
--

INSERT INTO `ModeratorRequestsSpeech` (`id`, `approved`, `moderator_login`, `speech_id`) VALUES
(1, b'1', 'mod', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `Organizer`
--

CREATE TABLE IF NOT EXISTS `Organizer` (
  `login` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Organizer`
--

INSERT INTO `Organizer` (`login`, `email`, `name`, `phone`) VALUES
('org', 'org@org.ru', 'Organizer', '123');

-- --------------------------------------------------------

--
-- Структура таблицы `Question`
--

CREATE TABLE IF NOT EXISTS `Question` (
  `id` int(11) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `answered` bit(1) DEFAULT NULL,
  `moderated` bit(1) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `speech_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Question`
--

INSERT INTO `Question` (`id`, `answer`, `answered`, `moderated`, `rating`, `text`, `speech_id`) VALUES
(2, NULL, b'0', b'0', 1, 'question', 1),
(3, NULL, b'0', b'0', 1, 'question1', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `Question_Visitor`
--

CREATE TABLE IF NOT EXISTS `Question_Visitor` (
  `questions_id` int(11) NOT NULL,
  `visitorsUpVoteQuestion_login` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Question_Visitor`
--

INSERT INTO `Question_Visitor` (`questions_id`, `visitorsUpVoteQuestion_login`) VALUES
(2, 'vis'),
(3, 'vis');

-- --------------------------------------------------------

--
-- Структура таблицы `Reporter`
--

CREATE TABLE IF NOT EXISTS `Reporter` (
  `login` varchar(255) NOT NULL,
  `busy` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `ReporterRequestsSpeech`
--

CREATE TABLE IF NOT EXISTS `ReporterRequestsSpeech` (
  `id` int(11) NOT NULL,
  `approved` bit(1) NOT NULL,
  `reporter_login` varchar(255) DEFAULT NULL,
  `speech_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `Room`
--

CREATE TABLE IF NOT EXISTS `Room` (
  `number` int(11) NOT NULL,
  `busy` bit(1) NOT NULL,
  `capacity` int(11) DEFAULT NULL,
  `roomOwner_login` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `RoomOrder`
--

CREATE TABLE IF NOT EXISTS `RoomOrder` (
  `id` int(11) NOT NULL,
  `dateFrom` datetime DEFAULT NULL,
  `dateTo` datetime DEFAULT NULL,
  `room_number` int(11) DEFAULT NULL,
  `speech_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `RoomOwner`
--

CREATE TABLE IF NOT EXISTS `RoomOwner` (
  `login` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `Speaker`
--

CREATE TABLE IF NOT EXISTS `Speaker` (
  `login` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Speaker`
--

INSERT INTO `Speaker` (`login`, `email`, `name`, `phone`) VALUES
('sp', 'sp@sp.sp', 'Speaker', '12345');

-- --------------------------------------------------------

--
-- Структура таблицы `Speech`
--

CREATE TABLE IF NOT EXISTS `Speech` (
  `id` int(11) NOT NULL,
  `approved` bit(1) NOT NULL,
  `completed` bit(1) NOT NULL,
  `startDate` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `topic` varchar(255) DEFAULT NULL,
  `conference_id` int(11) DEFAULT NULL,
  `roomOrder_id` int(11) DEFAULT NULL,
  `speaker_login` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Speech`
--

INSERT INTO `Speech` (`id`, `approved`, `completed`, `startDate`, `text`, `topic`, `conference_id`, `roomOrder_id`, `speaker_login`) VALUES
(1, b'1', b'0', '2015-07-17 03:21:00', 'text', 'topic', 1, NULL, 'sp');

-- --------------------------------------------------------

--
-- Структура таблицы `Speech_Visitor`
--

CREATE TABLE IF NOT EXISTS `Speech_Visitor` (
  `speeches_id` int(11) NOT NULL,
  `visitors_login` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Speech_Visitor`
--

INSERT INTO `Speech_Visitor` (`speeches_id`, `visitors_login`) VALUES
(1, 'vis');

-- --------------------------------------------------------

--
-- Структура таблицы `TestEntity`
--

CREATE TABLE IF NOT EXISTS `TestEntity` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `Translation`
--

CREATE TABLE IF NOT EXISTS `Translation` (
  `id` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `speech_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `UserRoles`
--

CREATE TABLE IF NOT EXISTS `UserRoles` (
  `username` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `UserRoles`
--

INSERT INTO `UserRoles` (`username`, `role`) VALUES
('mod', 'moderator'),
('vis', 'visitor');

-- --------------------------------------------------------

--
-- Структура таблицы `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `username` varchar(255) NOT NULL,
  `passwd` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Users`
--

INSERT INTO `Users` (`username`, `passwd`) VALUES
('mod', 'mod'),
('org', 'org'),
('sp', 'sp'),
('vis', 'vis');

-- --------------------------------------------------------

--
-- Структура таблицы `Visitor`
--

CREATE TABLE IF NOT EXISTS `Visitor` (
  `login` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `Visitor`
--

INSERT INTO `Visitor` (`login`, `email`, `name`, `phone`) VALUES
('vis', 'vis@vis.vis', 'Visitor', '123');

-- --------------------------------------------------------

--
-- Структура таблицы `Visitor_Question`
--

CREATE TABLE IF NOT EXISTS `Visitor_Question` (
  `Visitor_login` varchar(255) NOT NULL,
  `questions_id` int(11) NOT NULL,
  `visitorsUpVoteQuestion_login` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `Conference`
--
ALTER TABLE `Conference`
  ADD PRIMARY KEY (`id`), ADD KEY `FK_q4tixu43gq1jkkv4epvpmpmqy` (`organizer_login`);

--
-- Индексы таблицы `Conference_Visitor`
--
ALTER TABLE `Conference_Visitor`
  ADD KEY `FK_112sywrqss2awg680kqdwk29v` (`visitors_login`), ADD KEY `FK_q9cgx1xlobetcb7nxrv3nrfbh` (`conferences_id`);

--
-- Индексы таблицы `Moderator`
--
ALTER TABLE `Moderator`
  ADD PRIMARY KEY (`login`);

--
-- Индексы таблицы `ModeratorRequestsSpeech`
--
ALTER TABLE `ModeratorRequestsSpeech`
  ADD PRIMARY KEY (`id`), ADD KEY `FK_ravndei9rklqme5j9nsllpfyf` (`moderator_login`), ADD KEY `FK_a8k95yl3e29m92w8u2ak4i886` (`speech_id`);

--
-- Индексы таблицы `Organizer`
--
ALTER TABLE `Organizer`
  ADD PRIMARY KEY (`login`);

--
-- Индексы таблицы `Question`
--
ALTER TABLE `Question`
  ADD PRIMARY KEY (`id`), ADD KEY `FK_rjuyhcoeyuud9235xehotnjc7` (`speech_id`);

--
-- Индексы таблицы `Question_Visitor`
--
ALTER TABLE `Question_Visitor`
  ADD KEY `FK_mx1nlcg9mhh448r5rk04h8nn2` (`visitorsUpVoteQuestion_login`), ADD KEY `FK_e0oe4ljgu38kdbpim9t9vo6o9` (`questions_id`);

--
-- Индексы таблицы `Reporter`
--
ALTER TABLE `Reporter`
  ADD PRIMARY KEY (`login`);

--
-- Индексы таблицы `ReporterRequestsSpeech`
--
ALTER TABLE `ReporterRequestsSpeech`
  ADD PRIMARY KEY (`id`), ADD KEY `FK_3qmx22tn0ktx1syvhxlwqrui8` (`reporter_login`), ADD KEY `FK_9kke3aal5a05pdw9qpjvpmotq` (`speech_id`);

--
-- Индексы таблицы `Room`
--
ALTER TABLE `Room`
  ADD PRIMARY KEY (`number`), ADD KEY `FK_ajfi6d4unsrngejtcnuf7oo31` (`roomOwner_login`);

--
-- Индексы таблицы `RoomOrder`
--
ALTER TABLE `RoomOrder`
  ADD PRIMARY KEY (`id`), ADD KEY `FK_eg2vy7rtq6isnheav8j0s0g1f` (`room_number`), ADD KEY `FK_rywwbqurgf89whb3fd4qa1o8w` (`speech_id`);

--
-- Индексы таблицы `RoomOwner`
--
ALTER TABLE `RoomOwner`
  ADD PRIMARY KEY (`login`);

--
-- Индексы таблицы `Speaker`
--
ALTER TABLE `Speaker`
  ADD PRIMARY KEY (`login`);

--
-- Индексы таблицы `Speech`
--
ALTER TABLE `Speech`
  ADD PRIMARY KEY (`id`), ADD KEY `FK_ae10x1lnh6ihy6x7ay74mkf9v` (`conference_id`), ADD KEY `FK_k9sf0aqlgkkx1bn5a657l2lc7` (`roomOrder_id`), ADD KEY `FK_7ap1y0wlpu2fwg0ebbylhoief` (`speaker_login`);

--
-- Индексы таблицы `Speech_Visitor`
--
ALTER TABLE `Speech_Visitor`
  ADD KEY `FK_358sgw0mv79w7ppgb27kdhsxw` (`visitors_login`), ADD KEY `FK_fo9yj71s7f9tkd0dw6u0u8t9h` (`speeches_id`);

--
-- Индексы таблицы `TestEntity`
--
ALTER TABLE `TestEntity`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `Translation`
--
ALTER TABLE `Translation`
  ADD PRIMARY KEY (`id`), ADD KEY `FK_f97bb958gi4s910rbyplq7pd1` (`speech_id`);

--
-- Индексы таблицы `UserRoles`
--
ALTER TABLE `UserRoles`
  ADD PRIMARY KEY (`username`);

--
-- Индексы таблицы `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`username`);

--
-- Индексы таблицы `Visitor`
--
ALTER TABLE `Visitor`
  ADD PRIMARY KEY (`login`);

--
-- Индексы таблицы `Visitor_Question`
--
ALTER TABLE `Visitor_Question`
  ADD KEY `FK_nfoul56mje3ie6l8ml377bugj` (`questions_id`), ADD KEY `FK_ahr9uqlm5p3bqkok24qaivpki` (`Visitor_login`), ADD KEY `FK_7xitjnl13q11uaocjw05qqcap` (`visitorsUpVoteQuestion_login`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `Conference_Visitor`
--
ALTER TABLE `Conference_Visitor`
ADD CONSTRAINT `FK_q9cgx1xlobetcb7nxrv3nrfbh` FOREIGN KEY (`conferences_id`) REFERENCES `Conference` (`id`),
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
-- Ограничения внешнего ключа таблицы `Question_Visitor`
--
ALTER TABLE `Question_Visitor`
ADD CONSTRAINT `FK_e0oe4ljgu38kdbpim9t9vo6o9` FOREIGN KEY (`questions_id`) REFERENCES `Question` (`id`),
ADD CONSTRAINT `FK_mx1nlcg9mhh448r5rk04h8nn2` FOREIGN KEY (`visitorsUpVoteQuestion_login`) REFERENCES `Visitor` (`login`);

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
ADD CONSTRAINT `FK_k9sf0aqlgkkx1bn5a657l2lc7` FOREIGN KEY (`roomOrder_id`) REFERENCES `RoomOrder` (`id`),
ADD CONSTRAINT `FK_ae10x1lnh6ihy6x7ay74mkf9v` FOREIGN KEY (`conference_id`) REFERENCES `Conference` (`id`);

--
-- Ограничения внешнего ключа таблицы `Speech_Visitor`
--
ALTER TABLE `Speech_Visitor`
ADD CONSTRAINT `FK_fo9yj71s7f9tkd0dw6u0u8t9h` FOREIGN KEY (`speeches_id`) REFERENCES `Speech` (`id`),
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
ADD CONSTRAINT `FK_7xitjnl13q11uaocjw05qqcap` FOREIGN KEY (`visitorsUpVoteQuestion_login`) REFERENCES `Visitor` (`login`),
ADD CONSTRAINT `FK_ahr9uqlm5p3bqkok24qaivpki` FOREIGN KEY (`Visitor_login`) REFERENCES `Visitor` (`login`),
ADD CONSTRAINT `FK_nfoul56mje3ie6l8ml377bugj` FOREIGN KEY (`questions_id`) REFERENCES `Question` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
