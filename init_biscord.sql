-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : mar. 18 mai 2021 à 15:45
-- Version du serveur :  10.4.13-MariaDB
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `biscord`
--

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(2048) COLLATE utf8_unicode_ci NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `author_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_AUTHOR_ID` (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id`, `content`, `timestamp`, `author_id`) VALUES
(1, 'Salut tout le monde !', '2021-05-14 12:19:08', 3),
(2, 'Salut Michel :)', '2021-05-14 12:19:43', 2),
(3, 'https://media.tenor.com/images/acc4116372dcc4b342cb1a00ae657151/tenor.gif', '2021-05-14 12:19:56', 2),
(4, 'Bonsoir, je suis le modérateur, je ferai régner l\'ordre sur cette discussion !', '2021-05-18 15:44:31', 1),
(5, 'https://data.photofunky.net/output/image/f/b/c/7/fbc759/photofunky.gif', '2021-05-18 15:44:34', 2);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `moderator` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `moderator`) VALUES
(1, 'admin', '$2a$10$F5X4RbEmrgiYT8e8/CPQuO66ZVBLi3EyFl4KOVdhArvqVLHLrOtoq', 1),
(2, 'Bobby', '$2a$10$RQD8LZ.u58YvVre1b5s71uBI5X/KFbdJ8xRz81c5cq6AvyGBh77yS', 0),
(3, 'Michel', '$2a$10$8FuSMPBICKzVflixEp/ifebcfkEe2gBXAGLKH4WnIzCot7Wb59RMK', 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_AUTHOR_ID` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
