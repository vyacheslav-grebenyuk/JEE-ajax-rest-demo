CREATE DATABASE `jeepetshop` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `pets` (
  `idpets` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `age` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idpets`),
  UNIQUE KEY `idpets_UNIQUE` (`idpets`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
CREATE TABLE `food` (
  `idfood` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`idfood`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
CREATE TABLE `tools` (
  `idtools` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtools`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
CREATE TABLE `food_pet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pet` int(11) DEFAULT NULL,
  `id_food` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idfood_idx` (`id_food`),
  KEY `idpet_idx` (`id_pet`),
  CONSTRAINT `idfood` FOREIGN KEY (`id_food`) REFERENCES `food` (`idfood`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idpet` FOREIGN KEY (`id_pet`) REFERENCES `pets` (`idpets`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `tool_pet` (
  `idtoolpet` int(11) NOT NULL AUTO_INCREMENT,
  `id_pet` int(11) DEFAULT NULL,
  `id_tool` int(11) DEFAULT NULL,
  PRIMARY KEY (`idtoolpet`),
  KEY `idtool_idx` (`id_tool`),
  CONSTRAINT `idtool` FOREIGN KEY (`id_tool`) REFERENCES `tools` (`idtools`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;