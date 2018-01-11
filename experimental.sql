-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 11. Jan 2018 um 14:21
-- Server-Version: 5.7.20-log
-- PHP-Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `experimental`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dozent`
--

CREATE TABLE `dozent` (
  `id` int(11) NOT NULL,
  `fachbereich` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `dozent`
--

INSERT INTO `dozent` (`id`, `fachbereich`) VALUES
(3, 'Informatik'),
(4, 'Statistik'),
(5, 'Mathe');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `person`
--

INSERT INTO `person` (`id`, `name`, `age`) VALUES
(1, 'Udo', 18),
(3, 'Georg', 35),
(4, 'Marvin', 40),
(5, 'Helga', 42),
(6, 'Nadine', 22),
(7, 'Hans', 20),
(10, 'Karl', 25),
(11, 'Lakis', 12),
(12, 'Martin', 21);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `matrikelnummer` int(11) NOT NULL,
  `mensakarte` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `student`
--

INSERT INTO `student` (`id`, `matrikelnummer`, `mensakarte`) VALUES
(1, 1234, '266051|AD'),
(6, 1236, '64415|HG'),
(7, 12355, '1234|ac'),
(10, 2945532, '65491|RV'),
(12, 945765, '16475|OP');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `student_vorlesung`
--

CREATE TABLE `student_vorlesung` (
  `student` int(11) NOT NULL,
  `vorlesung` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `student_vorlesung`
--

INSERT INTO `student_vorlesung` (`student`, `vorlesung`) VALUES
(1, 1),
(6, 1),
(7, 1),
(6, 2),
(12, 2),
(6, 4),
(7, 4),
(12, 4),
(10, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `vorlesung`
--

CREATE TABLE `vorlesung` (
  `id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `dozent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `vorlesung`
--

INSERT INTO `vorlesung` (`id`, `name`, `dozent`) VALUES
(1, 'Informatik', 3),
(2, 'Mathe', 5),
(4, 'Statistik', 4);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `dozent`
--
ALTER TABLE `dozent`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `student_vorlesung`
--
ALTER TABLE `student_vorlesung`
  ADD KEY `student` (`student`),
  ADD KEY `vorlesung` (`vorlesung`);

--
-- Indizes für die Tabelle `vorlesung`
--
ALTER TABLE `vorlesung`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dozent` (`dozent`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `dozent`
--
ALTER TABLE `dozent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT für Tabelle `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT für Tabelle `vorlesung`
--
ALTER TABLE `vorlesung`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `student_vorlesung`
--
ALTER TABLE `student_vorlesung`
  ADD CONSTRAINT `student_vorlesung_ibfk_1` FOREIGN KEY (`student`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_vorlesung_ibfk_2` FOREIGN KEY (`vorlesung`) REFERENCES `vorlesung` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `vorlesung`
--
ALTER TABLE `vorlesung`
  ADD CONSTRAINT `vorlesung_ibfk_1` FOREIGN KEY (`dozent`) REFERENCES `dozent` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
