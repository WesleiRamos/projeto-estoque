/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE IF NOT EXISTS `bd_estoque` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bd_estoque`;

CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id_cliente`, `nome`, `cpf`, `email`, `telefone`) VALUES
	(1, 'Super Sony', '123.456.789-10', 'super@sony.gg', '(42) 9999-9999'),
	(14, 'Ronaldo Nazario', '094.199.400-12', 'ronaldo@nazario.com', '(11) 9999-0000');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `compra` (
  `id_compra` int(11) NOT NULL AUTO_INCREMENT,
  `data` timestamp NULL DEFAULT NULL,
  `id_fornecedor` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_compra`),
  KEY `fk_compra_fornecedor_idx` (`id_fornecedor`),
  KEY `fk_compra_usuario1_idx` (`id_usuario`),
  CONSTRAINT `fk_compra_fornecedor` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id_fornecedor`),
  CONSTRAINT `fk_compra_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` (`id_compra`, `data`, `id_fornecedor`, `id_usuario`) VALUES
	(9, '2022-03-03 15:22:22', 1, 25),
	(10, '2022-03-03 15:39:36', 12, 26);
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `compra_produto` (
  `id_compra` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `valor_unitario_compra` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_compra`,`id_produto`),
  KEY `fk_compra_has_produto_produto1_idx` (`id_produto`),
  KEY `fk_compra_has_produto_compra1_idx` (`id_compra`),
  CONSTRAINT `fk_compra_has_produto_compra1` FOREIGN KEY (`id_compra`) REFERENCES `compra` (`id_compra`),
  CONSTRAINT `fk_compra_has_produto_produto1` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `compra_produto` DISABLE KEYS */;
INSERT INTO `compra_produto` (`id_compra`, `id_produto`, `quantidade`, `valor_unitario_compra`) VALUES
	(9, 3, 1, 620.68),
	(9, 5, 1, 1820.90),
	(10, 4, 1, 590.20);
/*!40000 ALTER TABLE `compra_produto` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `fornecedor` (
  `id_fornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(45) DEFAULT NULL,
  `razao_social` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_fornecedor`),
  UNIQUE KEY `cnpj_UNIQUE` (`cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` (`id_fornecedor`, `cnpj`, `razao_social`, `email`, `telefone`) VALUES
	(1, '00.000.000/0000-00', 'Amd Brasil Ltda', 'amd@brasil.com', '(42) 7070707070'),
	(12, '11.111.111/1111-11', 'Intel Brasil Ltda', 'intel@brasil.com', '(41) 40028922'),
	(13, '22.222.222/2222-22', 'Nvidia Brasil Ltda', 'nvidia@brasil.com', '(41) 123456789');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `produto` (
  `id_produto` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  `valor_unitario` decimal(10,2) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `id_tipo_produto` int(11) NOT NULL,
  PRIMARY KEY (`id_produto`),
  KEY `fk_produto_tipo_produto1_idx` (`id_tipo_produto`),
  CONSTRAINT `fk_produto_tipo_produto1` FOREIGN KEY (`id_tipo_produto`) REFERENCES `tipo_produto` (`id_tipo_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` (`id_produto`, `descricao`, `valor_unitario`, `quantidade`, `id_tipo_produto`) VALUES
	(3, 'Ryzen 5 1600AF', 620.68, 5, 5),
	(4, 'Intel i7 7700k', 590.20, 2, 5),
	(5, 'RX 580', 1820.90, 1, 6),
	(6, 'GTX 1660', 2300.50, 2, 6);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `tipo_produto` (
  `id_tipo_produto` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `tipo_produto` DISABLE KEYS */;
INSERT INTO `tipo_produto` (`id_tipo_produto`, `descricao`) VALUES
	(5, 'Processador'),
	(6, 'Placa de vídeo');
/*!40000 ALTER TABLE `tipo_produto` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `senha` char(76) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`, `email`, `senha`) VALUES
	(25, 'weslei@ramos.com', '$2a$10$GeaktZpPqfRZlk3WsqKurucYg9OJFU4d923y7/F486TWAY0h6g7zC'),
	(26, 'adalton@enerto.com', '$2a$10$GHtxKzUT0QrTtMs3i8Wsde624MYxHpYGhWI3l4YbMpT2.Zho8CP9W');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `venda` (
  `id_venda` int(11) NOT NULL AUTO_INCREMENT,
  `data` timestamp NULL DEFAULT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_venda`),
  KEY `fk_venda_cliente1_idx` (`id_cliente`),
  KEY `fk_venda_usuario1_idx` (`id_usuario`),
  CONSTRAINT `fk_venda_cliente1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `fk_venda_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` (`id_venda`, `data`, `id_cliente`, `id_usuario`) VALUES
	(6, '2022-03-03 16:00:25', 14, 25);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `venda_produto` (
  `id_venda` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `valor_unitario_venda` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_venda`,`id_produto`),
  KEY `fk_venda_has_produto_produto1_idx` (`id_produto`),
  KEY `fk_venda_has_produto_venda1_idx` (`id_venda`),
  CONSTRAINT `fk_venda_has_produto_produto1` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`),
  CONSTRAINT `fk_venda_has_produto_venda1` FOREIGN KEY (`id_venda`) REFERENCES `venda` (`id_venda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `venda_produto` DISABLE KEYS */;
INSERT INTO `venda_produto` (`id_venda`, `id_produto`, `quantidade`, `valor_unitario_venda`) VALUES
	(6, 3, 1, 620.68),
	(6, 6, 1, 2300.50);
/*!40000 ALTER TABLE `venda_produto` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
