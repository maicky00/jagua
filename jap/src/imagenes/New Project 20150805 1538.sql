-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sistemaagua
--

CREATE DATABASE IF NOT EXISTS sistemaagua;
USE sistemaagua;

--
-- Definition of table `corte`
--

DROP TABLE IF EXISTS `corte`;
CREATE TABLE `corte` (
  `IDCORTE` int(11) NOT NULL auto_increment,
  `IDMEDIDOR` int(11) default NULL,
  `CORTE` varchar(2) default NULL,
  `FECHA` date default NULL,
  `OBSERVACION` text,
  `MULTA` float(8,2) default NULL,
  `MORA` int(11) default NULL,
  PRIMARY KEY  (`IDCORTE`),
  KEY `FK_REFERENCE_14` (`IDMEDIDOR`),
  CONSTRAINT `FK_REFERENCE_14` FOREIGN KEY (`IDMEDIDOR`) REFERENCES `medidor` (`IDMEDIDOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `corte`
--

/*!40000 ALTER TABLE `corte` DISABLE KEYS */;
/*!40000 ALTER TABLE `corte` ENABLE KEYS */;


--
-- Definition of table `detallefactura`
--

DROP TABLE IF EXISTS `detallefactura`;
CREATE TABLE `detallefactura` (
  `IDDETALLEFAC` int(11) NOT NULL auto_increment,
  `IDTARIFAS` int(11) default NULL,
  `IDMEDIDOR` int(11) default NULL,
  `MEDIDAANT` int(11) default NULL,
  `MEDIDAACT` int(11) default NULL,
  `CONSUMO` int(11) default NULL,
  `MEDEXCEDIDO` int(11) default NULL,
  `TAREXCEDIDO` float(8,2) default NULL,
  `SUBTOTAL` float(8,2) default NULL,
  `TOTAL` float(8,2) default NULL,
  PRIMARY KEY  (`IDDETALLEFAC`),
  KEY `FK_REFERENCE_4` (`IDTARIFAS`),
  KEY `FK_REFERENCE_7` (`IDMEDIDOR`),
  CONSTRAINT `FK_REFERENCE_7` FOREIGN KEY (`IDMEDIDOR`) REFERENCES `medidor` (`IDMEDIDOR`),
  CONSTRAINT `FK_REFERENCE_4` FOREIGN KEY (`IDTARIFAS`) REFERENCES `tarifas` (`IDTARIFAS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detallefactura`
--

/*!40000 ALTER TABLE `detallefactura` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallefactura` ENABLE KEYS */;


--
-- Definition of table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
CREATE TABLE `facturas` (
  `IDFACTURA` int(11) NOT NULL auto_increment,
  `IDDETALLEFAC` int(11) default NULL,
  `NUMFACTURA` int(11) default NULL,
  `FECHAEMISION` date default NULL,
  `MES` varchar(20) default NULL,
  `VALORBASE` float(8,2) default NULL,
  `EXCESO` float(8,2) default NULL,
  `SUBTOTAL` float(8,2) default NULL,
  `IVA` float(8,2) default NULL,
  `TOTAL` float(8,2) default NULL,
  `USUARIOACTUAL` text,
  PRIMARY KEY  (`IDFACTURA`),
  KEY `FK_REFERENCE_10` (`IDDETALLEFAC`),
  CONSTRAINT `FK_REFERENCE_10` FOREIGN KEY (`IDDETALLEFAC`) REFERENCES `detallefactura` (`IDDETALLEFAC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `facturas`
--

/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;


--
-- Definition of table `institucion`
--

DROP TABLE IF EXISTS `institucion`;
CREATE TABLE `institucion` (
  `IDINSTITUCION` int(11) NOT NULL auto_increment,
  `NOMBREINST` text,
  `DIRECCION` text,
  `TELEFONO` varchar(10) default NULL,
  `EMAIL` varchar(60) default NULL,
  `RUC` varchar(15) default NULL,
  `CELULAR` varchar(11) default NULL,
  PRIMARY KEY  (`IDINSTITUCION`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `institucion`
--

/*!40000 ALTER TABLE `institucion` DISABLE KEYS */;
INSERT INTO `institucion` (`IDINSTITUCION`,`NOMBREINST`,`DIRECCION`,`TELEFONO`,`EMAIL`,`RUC`,`CELULAR`) VALUES 
 (1,'JUNTA ADMINISTRADORA DE AGUA\nPOTABLE \"POTABLE\"','PARRQUIA SAN RAFAEL - COMUNIDAD TOCAGON','','','','0997981255');
/*!40000 ALTER TABLE `institucion` ENABLE KEYS */;


--
-- Definition of table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `IDLOGIN` int(11) NOT NULL auto_increment,
  `NOMBRES` varchar(80) default NULL,
  `APELLIDOS` varchar(80) default NULL,
  `CEDULA` varchar(15) default NULL,
  `USUARIO` varchar(50) default NULL,
  `CLAVE` varchar(50) default NULL,
  `TIPO` varchar(30) default NULL,
  `ESTADO` varchar(10) default NULL,
  PRIMARY KEY  (`IDLOGIN`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`IDLOGIN`,`NOMBRES`,`APELLIDOS`,`CEDULA`,`USUARIO`,`CLAVE`,`TIPO`,`ESTADO`) VALUES 
 (1,'juan','campos','Cedula:','123','123','ADMINISTRADOR','ACTIVO'),
 (2,'hjhjk','hkh','','hjkhj','hjh','ADMINISTRADOR','ACTIVO');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Definition of table `medidor`
--

DROP TABLE IF EXISTS `medidor`;
CREATE TABLE `medidor` (
  `IDMEDIDOR` int(11) NOT NULL auto_increment,
  `IDUSUARIO` int(11) default NULL,
  `SERIE` varchar(20) default NULL,
  `NUMMEDIDOR` int(11) default NULL,
  PRIMARY KEY  (`IDMEDIDOR`),
  KEY `FK_REFERENCE_1` (`IDUSUARIO`),
  CONSTRAINT `FK_REFERENCE_1` FOREIGN KEY (`IDUSUARIO`) REFERENCES `usuarios` (`IDUSUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medidor`
--

/*!40000 ALTER TABLE `medidor` DISABLE KEYS */;
/*!40000 ALTER TABLE `medidor` ENABLE KEYS */;


--
-- Definition of table `mingas`
--

DROP TABLE IF EXISTS `mingas`;
CREATE TABLE `mingas` (
  `IDMINGA` int(11) NOT NULL auto_increment,
  `IDMEDIDOR` int(11) default NULL,
  `LUGAR` text,
  `FECHA` date default NULL,
  `ASISTENCIA` varchar(2) default NULL,
  `VALORMULTA` float(8,2) default NULL,
  `DESCRIPCION` text,
  PRIMARY KEY  (`IDMINGA`),
  KEY `FK_REFERENCE_3` (`IDMEDIDOR`),
  CONSTRAINT `FK_REFERENCE_3` FOREIGN KEY (`IDMEDIDOR`) REFERENCES `medidor` (`IDMEDIDOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mingas`
--

/*!40000 ALTER TABLE `mingas` DISABLE KEYS */;
/*!40000 ALTER TABLE `mingas` ENABLE KEYS */;


--
-- Definition of table `otrospagos`
--

DROP TABLE IF EXISTS `otrospagos`;
CREATE TABLE `otrospagos` (
  `IDOTPAGOS` int(11) NOT NULL auto_increment,
  `IDCORTE` int(11) default NULL,
  `DERCONX` text,
  `MULRECX` float(8,2) default NULL,
  `MULTMS` float(8,2) default NULL,
  `INTERES` float(8,2) default NULL,
  `SERIE` varchar(10) default NULL,
  PRIMARY KEY  (`IDOTPAGOS`),
  KEY `FK_REFERENCE_11` (`IDCORTE`),
  CONSTRAINT `FK_REFERENCE_11` FOREIGN KEY (`IDCORTE`) REFERENCES `corte` (`IDCORTE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `otrospagos`
--

/*!40000 ALTER TABLE `otrospagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `otrospagos` ENABLE KEYS */;


--
-- Definition of table `pagosmingas`
--

DROP TABLE IF EXISTS `pagosmingas`;
CREATE TABLE `pagosmingas` (
  `IDPAGOMINGA` int(11) NOT NULL auto_increment,
  `IDMINGA` int(11) default NULL,
  `FECHAPAGO` date default NULL,
  `NUMMINGAS` int(11) default NULL,
  `VALORMINGAS` float(8,2) default NULL,
  `USUARIOACTUAL` text,
  `OBSERVACION` varchar(20) default NULL,
  PRIMARY KEY  (`IDPAGOMINGA`),
  KEY `FK_REFERENCE_12` (`IDMINGA`),
  CONSTRAINT `FK_REFERENCE_12` FOREIGN KEY (`IDMINGA`) REFERENCES `mingas` (`IDMINGA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pagosmingas`
--

/*!40000 ALTER TABLE `pagosmingas` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagosmingas` ENABLE KEYS */;


--
-- Definition of table `tarifas`
--

DROP TABLE IF EXISTS `tarifas`;
CREATE TABLE `tarifas` (
  `IDTARIFAS` int(11) NOT NULL auto_increment,
  `BASE` int(11) default NULL,
  `TARBASE` float(8,2) default NULL,
  `DESCRIPCION` text,
  PRIMARY KEY  (`IDTARIFAS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tarifas`
--

/*!40000 ALTER TABLE `tarifas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarifas` ENABLE KEYS */;


--
-- Definition of table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `IDUSUARIO` int(11) NOT NULL auto_increment,
  `IDINSTITUCION` int(11) default NULL,
  `RUCCI` varchar(15) default NULL,
  `PRIMERNOMBRE` varchar(50) default NULL,
  `SEGUNDONOMBRE` varchar(50) default NULL,
  `PRIMERAPELLIDO` varchar(50) default NULL,
  `SEGUNDOAPELLIDO` varchar(50) default NULL,
  `APADOSN` varchar(50) default NULL,
  `DIRECCION` text,
  `TELEFONO` varchar(10) default NULL,
  `CELULAR` varchar(10) default NULL,
  `SECTOR` text,
  `REFERENCIA` text,
  `FOTO` longblob,
  `OBSERVACION` text,
  PRIMARY KEY  (`IDUSUARIO`),
  KEY `FK_REFERENCE_9` (`IDINSTITUCION`),
  CONSTRAINT `FK_REFERENCE_9` FOREIGN KEY (`IDINSTITUCION`) REFERENCES `institucion` (`IDINSTITUCION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuarios`
--

/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
