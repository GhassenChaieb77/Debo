-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 09 fév. 2020 à 21:15
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `deboodb`
--

-- --------------------------------------------------------

--
-- Structure de la table `absence`
--

DROP TABLE IF EXISTS `absence`;
CREATE TABLE IF NOT EXISTS `absence` (
  `idAbsence` int(11) NOT NULL,
  `date` date NOT NULL,
  `idEmp` int(11) NOT NULL,
  PRIMARY KEY (`idAbsence`),
  KEY `idEmp` (`idEmp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
CREATE TABLE IF NOT EXISTS `assignment` (
  `dateP` date NOT NULL,
  `dateR` date NOT NULL,
  `state` varchar(255) NOT NULL,
  `idEmp` int(11) NOT NULL,
  `idEquipment` int(11) NOT NULL,
  PRIMARY KEY (`dateP`),
  KEY `idEmp` (`idEmp`),
  KEY `idEquipment` (`idEquipment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `idCategory` int(11) NOT NULL,
  `categoryName` varchar(20) NOT NULL,
  PRIMARY KEY (`idCategory`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `claim`
--

DROP TABLE IF EXISTS `claim`;
CREATE TABLE IF NOT EXISTS `claim` (
  `idRec` int(8) NOT NULL AUTO_INCREMENT,
  `message` varchar(100) NOT NULL,
  `answer` varchar(100) NOT NULL,
  `status` varchar(10) NOT NULL,
  `cin` int(8) NOT NULL,
  PRIMARY KEY (`idRec`),
  KEY `cin` (`cin`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `claim`
--

INSERT INTO `claim` (`idRec`, `message`, `answer`, `status`, `cin`) VALUES
(5, 'mriguel', 'pas mal', 'solved', 123654789),
(6, 'service ki **', 'lll', 'en attente', 12345678);

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `idEmp` int(11) NOT NULL,
  `lastNameEmp` varchar(20) NOT NULL,
  `firstNameEmp` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  `phone` int(8) NOT NULL,
  `nbAbsences` int(11) NOT NULL,
  `salary` float NOT NULL,
  `emailAddress` varchar(255) NOT NULL,
  PRIMARY KEY (`idEmp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
CREATE TABLE IF NOT EXISTS `equipment` (
  `idEquipment` int(11) NOT NULL,
  `equipmentName` varchar(255) NOT NULL,
  `fabricationDate` date NOT NULL,
  `rawMaterial` varchar(255) NOT NULL,
  PRIMARY KEY (`idEquipment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `opinion`
--

DROP TABLE IF EXISTS `opinion`;
CREATE TABLE IF NOT EXISTS `opinion` (
  `idOpinion` int(8) NOT NULL,
  `opinion` int(255) NOT NULL,
  `rating` int(4) NOT NULL,
  `cin` int(8) NOT NULL,
  KEY `cin` (`cin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `order`
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `idOrder` int(11) NOT NULL,
  `orderDate` date NOT NULL,
  `transporterChoiceDate` date NOT NULL,
  `orderState` varchar(255) NOT NULL,
  `total` float NOT NULL,
  `transporterState` varchar(255) NOT NULL,
  `paymentState` varchar(255) NOT NULL,
  `cin` int(8) NOT NULL,
  `idEmp` int(11) NOT NULL,
  PRIMARY KEY (`idOrder`),
  KEY `cin` (`cin`),
  KEY `idEmp` (`idEmp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `order_line`
--

DROP TABLE IF EXISTS `order_line`;
CREATE TABLE IF NOT EXISTS `order_line` (
  `idOrderLine` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `idOrder` int(11) NOT NULL,
  `idProduct` int(11) NOT NULL,
  PRIMARY KEY (`idOrderLine`),
  KEY `idOrder` (`idOrder`),
  KEY `idProduct` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `idProduct` int(11) NOT NULL,
  `productName` varchar(20) NOT NULL,
  `productPrice` float NOT NULL,
  `productPicture` blob NOT NULL,
  `idCategory` int(11) NOT NULL,
  `idNote` int(11) NOT NULL,
  PRIMARY KEY (`idProduct`),
  KEY `idCategory` (`idCategory`),
  KEY `idNote` (`idNote`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rating`
--

DROP TABLE IF EXISTS `rating`;
CREATE TABLE IF NOT EXISTS `rating` (
  `idNote` int(11) NOT NULL,
  `note` int(11) NOT NULL,
  PRIMARY KEY (`idNote`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `cin` int(8) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `phone1` int(11) NOT NULL,
  `phone2` int(11) NOT NULL,
  `login` varchar(8) NOT NULL,
  `password` varchar(10) NOT NULL,
  `role` varchar(10) NOT NULL,
  PRIMARY KEY (`cin`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`cin`, `lastName`, `firstName`, `phone1`, `phone2`, `login`, `password`, `role`) VALUES
(12345678, 'laribi', 'mohamed amine', 58891853, 0, 'amine22', 'amine227', 'client'),
(14764402, 'null', 'null', 0, 0, 'admin', 'system', 'admin'),
(123654789, 'nounou', 'douda', 24938486, 24938486, 'nounou', '123', 'client');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `absence_ibfk_1` FOREIGN KEY (`idEmp`) REFERENCES `employee` (`idEmp`);

--
-- Contraintes pour la table `assignment`
--
ALTER TABLE `assignment`
  ADD CONSTRAINT `assignment_ibfk_1` FOREIGN KEY (`idEmp`) REFERENCES `employee` (`idEmp`),
  ADD CONSTRAINT `assignment_ibfk_2` FOREIGN KEY (`idEquipment`) REFERENCES `equipment` (`idEquipment`);

--
-- Contraintes pour la table `claim`
--
ALTER TABLE `claim`
  ADD CONSTRAINT `claim_ibfk_1` FOREIGN KEY (`cin`) REFERENCES `user` (`cin`);

--
-- Contraintes pour la table `opinion`
--
ALTER TABLE `opinion`
  ADD CONSTRAINT `opinion_ibfk_1` FOREIGN KEY (`cin`) REFERENCES `user` (`cin`);

--
-- Contraintes pour la table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`cin`) REFERENCES `user` (`cin`),
  ADD CONSTRAINT `order_ibfk_2` FOREIGN KEY (`idEmp`) REFERENCES `employee` (`idEmp`);

--
-- Contraintes pour la table `order_line`
--
ALTER TABLE `order_line`
  ADD CONSTRAINT `order_line_ibfk_1` FOREIGN KEY (`idOrder`) REFERENCES `order` (`idOrder`),
  ADD CONSTRAINT `order_line_ibfk_2` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`);

--
-- Contraintes pour la table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`idCategory`) REFERENCES `category` (`idCategory`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`idNote`) REFERENCES `rating` (`idNote`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
