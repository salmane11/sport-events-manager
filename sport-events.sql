-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 05 fév. 2022 à 17:33
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `sport-events`
--

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `event_id` int(11) NOT NULL,
  `event_name` varchar(20) NOT NULL,
  `event_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`event_id`, `event_name`, `event_date`) VALUES
(1, 'mas vs rca', '2022-02-02 15:34:30'),
(2, 'fcb vs rma', '2022-02-04 10:57:41'),
(3, 'mas vs kac', '2022-02-04 11:08:18'),
(4, 'rma vs mas', '2022-02-04 20:53:14');

-- --------------------------------------------------------

--
-- Structure de la table `eventteam`
--

CREATE TABLE `eventteam` (
  `eventteam_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `eventteam`
--

INSERT INTO `eventteam` (`eventteam_id`, `event_id`, `team_id`) VALUES
(1, 1, 1),
(2, 1, 4),
(3, 2, 2),
(4, 2, 3),
(5, 3, 1),
(6, 3, 5),
(11, 4, 1),
(12, 4, 3);

-- --------------------------------------------------------

--
-- Structure de la table `player`
--

CREATE TABLE `player` (
  `player_id` int(11) NOT NULL,
  `player_name` varchar(20) NOT NULL,
  `player_age` int(11) NOT NULL,
  `player_position` varchar(20) NOT NULL,
  `team_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `player`
--

INSERT INTO `player` (`player_id`, `player_name`, `player_age`, `player_position`, `team_id`) VALUES
(1, 'salmane', 21, 'defense', 1),
(2, 'oulhaj', 32, 'RB', 1),
(3, 'bamaammer', 30, 'RG', 1),
(4, 'tiga', 32, 'CB', 1),
(5, 'Bourezouk', 32, 'RE', 1),
(6, 'messi', 32, 'attack', 2),
(7, 'neymar', 32, 'attack', 2),
(8, 'suarez', 32, 'attack', 2),
(9, 'TerSteigen', 30, 'GK', 2);

-- --------------------------------------------------------

--
-- Structure de la table `team`
--

CREATE TABLE `team` (
  `team_id` int(11) NOT NULL,
  `team_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `team`
--

INSERT INTO `team` (`team_id`, `team_name`) VALUES
(1, 'mas'),
(2, 'fcb'),
(3, 'rma'),
(4, 'rca'),
(5, 'kac'),
(6, 'atm');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`event_id`);

--
-- Index pour la table `eventteam`
--
ALTER TABLE `eventteam`
  ADD PRIMARY KEY (`eventteam_id`);

--
-- Index pour la table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`player_id`);

--
-- Index pour la table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`team_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `eventteam`
--
ALTER TABLE `eventteam`
  MODIFY `eventteam_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
