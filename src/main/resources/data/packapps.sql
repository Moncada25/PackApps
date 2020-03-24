-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-03-2020 a las 20:26:35
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `packapps`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin`
--

CREATE TABLE `admin` (
  `id` int(10) NOT NULL,
  `AdminNombre` varchar(70) COLLATE utf8_spanish2_ci NOT NULL,
  `AdminApellido` varchar(70) COLLATE utf8_spanish2_ci NOT NULL,
  `AdminTelefono` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `AdminDireccion` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `CuentaCodigo` varchar(70) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `admin`
--

INSERT INTO `admin` (`id`, `AdminNombre`, `AdminApellido`, `AdminTelefono`, `AdminDireccion`, `CuentaCodigo`) VALUES
(1, 'Santiago', 'Moncada', '+573012499315', 'Colombia, MedellÃ­n', 'AC750702071');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bitacora`
--

CREATE TABLE `bitacora` (
  `id` int(10) NOT NULL,
  `BitacoraCodigo` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `BitacoraFecha` date NOT NULL,
  `BitacoraHoraInicio` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `BitacoraHoraFinal` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `BitacoraTipo` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `BitacoraYear` int(4) NOT NULL,
  `CuentaCodigo` varchar(70) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `bitacora`
--

INSERT INTO `bitacora` (`id`, `BitacoraCodigo`, `BitacoraFecha`, `BitacoraHoraInicio`, `BitacoraHoraFinal`, `BitacoraTipo`, `BitacoraYear`, `CuentaCodigo`) VALUES
(84, 'CB247340154', '2019-05-11', '11:31:26 am', '12:32:36 pm', 'Administrador', 2019, 'AC750702071'),
(85, 'CB182791852', '2019-05-11', '12:33:14 pm', '12:35:20 pm', 'Administrador', 2019, 'AC750702071'),
(86, 'CB53079863', '2019-05-11', '01:37:23 pm', '01:37:35 pm', 'Administrador', 2019, 'AC750702071'),
(87, 'CB01408864', '2019-05-11', '01:50:30 pm', '02:10:04 pm', 'Administrador', 2019, 'AC750702071'),
(88, 'CB83101695', '2019-05-11', '02:23:39 pm', '02:23:48 pm', 'Administrador', 2019, 'AC750702071'),
(89, 'CB45132546', '2019-05-11', '02:25:59 pm', '02:27:37 pm', 'Administrador', 2019, 'AC750702071'),
(90, 'CB57175827', '2019-05-11', '02:28:19 pm', '02:28:22 pm', 'Administrador', 2019, 'AC750702071'),
(91, 'CB88864848', '2019-05-11', '02:29:27 pm', '02:29:30 pm', 'Administrador', 2019, 'AC750702071'),
(92, 'CB04584779', '2019-05-11', '02:33:37 pm', '02:49:14 pm', 'Administrador', 2019, 'AC750702071'),
(93, 'CB273594510', '2019-05-11', '02:49:18 pm', '02:49:26 pm', 'Administrador', 2019, 'AC750702071'),
(94, 'CB197369311', '2019-05-11', '02:49:31 pm', '05:50:58 pm', 'Administrador', 2019, 'AC750702071'),
(129, 'CB113991846', '2019-05-11', '07:42:47 pm', '07:43:50 pm', 'Administrador', 2019, 'AC750702071'),
(130, 'CB103792947', '2019-05-11', '07:43:54 pm', '07:43:59 pm', 'Administrador', 2019, 'AC750702071'),
(131, 'CB149381348', '2019-05-11', '07:44:03 pm', '07:44:52 pm', 'Administrador', 2019, 'AC750702071'),
(132, 'CB743547249', '2019-05-11', '07:44:59 pm', '07:45:03 pm', 'Administrador', 2019, 'AC750702071'),
(134, 'CB455295351', '2019-05-11', '07:46:28 pm', '07:46:32 pm', 'Administrador', 2019, 'AC750702071'),
(141, 'CB383332158', '2019-05-11', '07:56:07 pm', '07:56:11 pm', 'Administrador', 2019, 'AC750702071'),
(145, 'CB719383562', '2019-05-11', '07:58:09 pm', '07:58:20 pm', 'Administrador', 2019, 'AC750702071'),
(149, 'CB691727166', '2019-05-11', '07:59:52 pm', '07:59:57 pm', 'Administrador', 2019, 'AC750702071'),
(156, 'CB108430773', '2019-05-11', '08:02:10 pm', '08:02:14 pm', 'Administrador', 2019, 'AC750702071'),
(161, 'CB865047378', '2019-05-11', '08:06:18 pm', '08:06:22 pm', 'Administrador', 2019, 'AC750702071'),
(162, 'CB732201879', '2019-05-11', '08:09:05 pm', '08:09:10 pm', 'Administrador', 2019, 'AC750702071'),
(163, 'CB480268480', '2019-05-11', '08:15:00 pm', '08:15:04 pm', 'Administrador', 2019, 'AC750702071'),
(164, 'CB051428181', '2019-05-11', '08:16:45 pm', '08:16:49 pm', 'Administrador', 2019, 'AC750702071'),
(165, 'CB272098782', '2019-05-11', '08:29:08 pm', '08:29:14 pm', 'Administrador', 2019, 'AC750702071'),
(166, 'CB751645783', '2019-05-11', '08:30:58 pm', '08:31:27 pm', 'Administrador', 2019, 'AC750702071'),
(167, 'CB514107884', '2019-05-11', '08:32:14 pm', '08:36:20 pm', 'Administrador', 2019, 'AC750702071'),
(168, 'CB581591585', '2019-05-11', '08:37:40 pm', '08:42:52 pm', 'Administrador', 2019, 'AC750702071'),
(169, 'CB589421886', '2019-05-11', '08:50:29 pm', '08:51:11 pm', 'Administrador', 2019, 'AC750702071'),
(170, 'CB546660387', '2019-05-11', '09:29:02 pm', '09:32:19 pm', 'Administrador', 2019, 'AC750702071'),
(171, 'CB252731588', '2019-05-11', '09:32:31 pm', '09:35:28 pm', 'Administrador', 2019, 'AC750702071'),
(178, 'CB567843395', '2019-05-12', '02:03:00 pm', '02:05:37 pm', 'Administrador', 2019, 'AC750702071'),
(179, 'CB631964396', '2019-05-12', '02:07:25 pm', '02:07:31 pm', 'Administrador', 2019, 'AC750702071'),
(181, 'CB833926198', '2019-05-12', '02:08:13 pm', '02:08:17 pm', 'Administrador', 2019, 'AC750702071'),
(183, 'CB6154801100', '2019-05-12', '02:09:05 pm', '02:11:07 pm', 'Administrador', 2019, 'AC750702071'),
(184, 'CB9180698101', '2019-05-12', '02:11:25 pm', '02:11:31 pm', 'Administrador', 2019, 'AC750702071'),
(185, 'CB0933953102', '2019-05-12', '02:17:58 pm', '02:18:19 pm', 'Administrador', 2019, 'AC750702071'),
(186, 'CB4080410103', '2019-05-12', '02:23:33 pm', '11:12:51 am', 'Administrador', 2019, 'AC750702071'),
(187, 'CB5619190104', '2019-05-13', '11:14:19 am', '11:20:55 am', 'Administrador', 2019, 'AC750702071'),
(188, 'CB2618118105', '2019-05-13', '11:21:02 am', '11:21:09 am', 'Administrador', 2019, 'AC750702071'),
(190, 'CB4085811107', '2019-05-13', '11:21:28 am', '11:21:32 am', 'Administrador', 2019, 'AC750702071'),
(191, 'CB2996001108', '2019-05-13', '11:23:10 am', '11:23:16 am', 'Administrador', 2019, 'AC750702071'),
(192, 'CB9720348109', '2019-05-13', '11:25:12 am', '11:27:56 am', 'Administrador', 2019, 'AC750702071'),
(194, 'CB3034253111', '2019-05-13', '11:40:51 am', '11:50:54 am', 'Administrador', 2019, 'AC750702071'),
(195, 'CB8897697112', '2019-05-13', '11:52:29 am', '12:00:54 pm', 'Administrador', 2019, 'AC750702071'),
(197, 'CB3326604114', '2019-05-13', '12:01:50 pm', '12:20:56 pm', 'Administrador', 2019, 'AC750702071'),
(199, 'CB8759167116', '2019-05-13', '01:39:39 pm', '01:44:07 pm', 'Administrador', 2019, 'AC750702071'),
(201, 'CB7722872118', '2019-05-14', '10:58:11 am', '11:03:02 am', 'Administrador', 2019, 'AC750702071'),
(211, 'CB0601680128', '2019-05-19', '09:29:50 am', '12:54:39 pm', 'Administrador', 2019, 'AC750702071'),
(212, 'CB6554079129', '2019-05-19', '12:54:43 pm', '05:59:27 pm', 'Administrador', 2019, 'AC750702071'),
(216, 'CB5647061133', '2019-05-20', '09:56:05 am', '01:54:54 pm', 'Administrador', 2019, 'AC750702071'),
(218, 'CB0414343135', '2019-05-20', '03:45:43 pm', '03:45:53 pm', 'Administrador', 2019, 'AC750702071'),
(220, 'CB6908428137', '2019-05-20', '05:09:06 pm', '05:21:27 pm', 'Administrador', 2019, 'AC750702071'),
(221, 'CB8756826138', '2019-05-20', '05:22:22 pm', '05:25:54 pm', 'Administrador', 2019, 'AC750702071'),
(222, 'CB7267964139', '2019-05-20', '05:26:39 pm', '05:26:54 pm', 'Administrador', 2019, 'AC750702071'),
(225, 'CB1884461142', '2019-05-20', '05:29:43 pm', '05:37:53 pm', 'Administrador', 2019, 'AC750702071'),
(227, 'CB5157544144', '2019-05-20', '05:38:22 pm', '05:38:32 pm', 'Administrador', 2019, 'AC750702071'),
(228, 'CB5463650145', '2019-05-20', '05:50:49 pm', '05:51:03 pm', 'Administrador', 2019, 'AC750702071'),
(229, 'CB2599188146', '2019-05-20', '05:52:44 pm', '05:53:03 pm', 'Administrador', 2019, 'AC750702071'),
(230, 'CB2789454147', '2019-05-20', '06:00:02 pm', '09:07:36 pm', 'Administrador', 2019, 'AC750702071'),
(231, 'CB2555053148', '2019-05-20', '09:07:41 pm', '10:02:10 pm', 'Administrador', 2019, 'AC750702071'),
(234, 'CB902032867', '2019-05-21', '08:52:22 am', '08:52:26 am', 'Administrador', 2019, 'AC750702071'),
(235, 'CB157778868', '2019-05-21', '09:01:02 am', '09:03:06 am', 'Administrador', 2019, 'AC750702071'),
(236, 'CB259797169', '2019-05-21', '09:03:51 am', '09:06:14 am', 'Administrador', 2019, 'AC750702071'),
(237, 'CB580816170', '2019-05-21', '09:06:35 am', '10:11:43 am', 'Administrador', 2019, 'AC750702071'),
(238, 'CB923072771', '2019-05-21', '10:12:04 am', '10:18:25 am', 'Administrador', 2019, 'AC750702071'),
(239, 'CB329039772', '2019-05-21', '10:18:37 am', '10:22:52 am', 'Administrador', 2019, 'AC750702071'),
(241, 'CB434597574', '2019-05-21', '10:23:54 am', '01:15:26 pm', 'Administrador', 2019, 'AC750702071'),
(242, 'CB038846875', '2019-05-21', '01:16:12 pm', '01:19:22 pm', 'Administrador', 2019, 'AC750702071'),
(243, 'CB268450176', '2019-05-21', '01:19:26 pm', '02:17:21 pm', 'Administrador', 2019, 'AC750702071'),
(244, 'CB603291477', '2019-05-21', '02:28:31 pm', '02:41:50 pm', 'Administrador', 2019, 'AC750702071'),
(245, 'CB947098278', '2019-05-21', '04:43:48 pm', '04:57:04 pm', 'Administrador', 2019, 'AC750702071'),
(246, 'CB777818579', '2019-05-21', '04:59:17 pm', '05:00:06 pm', 'Administrador', 2019, 'AC750702071'),
(248, 'CB502526581', '2019-05-22', '10:13:59 am', '06:17:58 pm', 'Administrador', 2019, 'AC750702071'),
(249, 'CB138213382', '2019-05-22', '06:50:53 pm', '07:53:22 am', 'Administrador', 2019, 'AC750702071'),
(250, 'CB360439783', '2019-05-24', '08:01:49 am', '08:10:16 am', 'Administrador', 2019, 'AC750702071'),
(251, 'CB235257184', '2019-05-24', '08:20:06 am', '08:36:50 am', 'Administrador', 2019, 'AC750702071'),
(252, 'CB321581785', '2019-05-24', '08:37:17 am', '09:03:26 am', 'Administrador', 2019, 'AC750702071'),
(253, 'CB993113286', '2019-05-25', '03:27:23 pm', '03:27:51 pm', 'Administrador', 2019, 'AC750702071'),
(254, 'CB452013387', '2019-05-25', '03:30:45 pm', '11:17:45 am', 'Administrador', 2019, 'AC750702071'),
(255, 'CB898695788', '2019-05-26', '11:20:21 am', 'Sin registro', 'Administrador', 2019, 'AC750702071'),
(256, 'CB233630089', '2019-05-27', '06:31:52 pm', '06:36:26 pm', 'Administrador', 2019, 'AC750702071'),
(257, 'CB479677690', '2019-05-28', '05:26:50 pm', 'Sin registro', 'Administrador', 2019, 'AC750702071'),
(258, 'CB921081491', '2019-11-20', '10:42:33 am', 'Sin registro', 'Administrador', 2019, 'AC750702071'),
(259, 'CB105264092', '2020-01-20', '08:55:38 pm', '08:55:51 pm', 'Administrador', 2020, 'AC750702071'),
(260, 'CB285985293', '2020-01-20', '09:16:34 pm', '09:18:55 pm', 'Administrador', 2020, 'AC750702071'),
(261, 'CB351218194', '2020-01-20', '09:19:10 pm', '09:05:26 am', 'Administrador', 2020, 'AC750702071'),
(262, 'CB109902695', '2020-01-25', '06:39:18 pm', '06:39:22 pm', 'Administrador', 2020, 'AC750702071'),
(263, 'CB924520196', '2020-03-22', '12:13:44 pm', 'Sin registro', 'Administrador', 2020, 'AC750702071'),
(264, 'CB125579790', '2020-03-23', '01:36:35 pm', 'Sin registro', 'Administrador', 2020, 'AC750702071'),
(265, 'CB894597291', '2020-03-23', '01:41:52 pm', 'Sin registro', 'Administrador', 2020, 'AC750702071');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cashregister`
--

CREATE TABLE `cashregister` (
  `ID` int(10) NOT NULL,
  `User` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `SoldProducts` int(11) NOT NULL,
  `TotalSales` double NOT NULL,
  `PurchasedProducts` int(11) NOT NULL,
  `TotalPurchases` double NOT NULL,
  `TotalLoans` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cashregister`
--

INSERT INTO `cashregister` (`ID`, `User`, `SoldProducts`, `TotalSales`, `PurchasedProducts`, `TotalPurchases`, `TotalLoans`) VALUES
(7, 'root', 2, 1000000, 5, 2500000, 999189);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(10) NOT NULL,
  `CategoriaCodigo` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `CategoriaNombre` varchar(100) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `CategoriaCodigo`, `CategoriaNombre`) VALUES
(5, 'LC367034405', 'Science'),
(6, 'LC470446156', 'Programming');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(10) NOT NULL,
  `ClienteNombre` varchar(70) COLLATE utf8_spanish2_ci NOT NULL,
  `ClienteApellido` varchar(70) COLLATE utf8_spanish2_ci NOT NULL,
  `ClienteTelefono` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `ClienteOcupacion` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `ClienteDireccion` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `CuentaCodigo` varchar(70) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `id` int(10) NOT NULL,
  `CuentaCodigo` varchar(70) COLLATE utf8_spanish2_ci NOT NULL,
  `CuentaPrivilegio` int(1) NOT NULL,
  `CuentaUsuario` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `CuentaClave` varchar(535) COLLATE utf8_spanish2_ci NOT NULL,
  `CuentaEmail` varchar(70) COLLATE utf8_spanish2_ci NOT NULL,
  `CuentaEstado` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `CuentaTipo` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `CuentaGenero` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `CuentaFoto` varchar(535) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`id`, `CuentaCodigo`, `CuentaPrivilegio`, `CuentaUsuario`, `CuentaClave`, `CuentaEmail`, `CuentaEstado`, `CuentaTipo`, `CuentaGenero`, `CuentaFoto`) VALUES
(1, 'AC750702071', 1, 'root', 'enl3OHVSRXgwd1BNVlkzMU9NdXo2UT09', 'zanti4020@gmail.com', 'Activo', 'Administrador', 'Masculino', 'U.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dices`
--

CREATE TABLE `dices` (
  `ID` int(10) NOT NULL,
  `Nickname` text NOT NULL,
  `Winner` text NOT NULL,
  `Round` int(10) NOT NULL,
  `Date` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `dices`
--

INSERT INTO `dices` (`ID`, `Nickname`, `Winner`, `Round`, `Date`) VALUES
(99, 'Sebas & Juan', '58 points', 5, 'Mar 10, 2020 - 15:2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `feedback`
--

CREATE TABLE `feedback` (
  `ID` int(10) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Message` text NOT NULL,
  `Date` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `guessnumber`
--

CREATE TABLE `guessnumber` (
  `ID` int(10) NOT NULL,
  `Nickname` text COLLATE ucs2_spanish2_ci NOT NULL,
  `Limitation` int(10) NOT NULL,
  `Level` text COLLATE ucs2_spanish2_ci NOT NULL,
  `Date` text COLLATE ucs2_spanish2_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=ucs2 COLLATE=ucs2_spanish2_ci;

--
-- Volcado de datos para la tabla `guessnumber`
--

INSERT INTO `guessnumber` (`ID`, `Nickname`, `Limitation`, `Level`, `Date`) VALUES
(143, 'Juan', 20, 'Easy - 4', 'Mar 10, 2020 - 14:41'),
(142, 'Sebas', 20, 'Easy - 4', 'Mar 10, 2020 - 14:41'),
(144, 'Andres', 20, 'Easy - 7', 'Mar 10, 2020 - 14:42'),
(145, 'Yo', 20, 'Easy - 4', 'Mar 10, 2020 - 14:43'),
(146, 'Yope', 100, 'Easy - 3', 'Mar 10, 2020 - 14:44'),
(147, 'Sebas', 100, 'Easy - 7', 'Mar 10, 2020 - 14:45');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hangman`
--

CREATE TABLE `hangman` (
  `ID` int(10) NOT NULL,
  `Nickname` text COLLATE utf16_spanish2_ci NOT NULL,
  `Mistakes` int(10) NOT NULL,
  `State` text COLLATE utf16_spanish2_ci NOT NULL,
  `Category` text COLLATE utf16_spanish2_ci DEFAULT NULL,
  `Date` text COLLATE utf16_spanish2_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf16 COLLATE=utf16_spanish2_ci;

--
-- Volcado de datos para la tabla `hangman`
--

INSERT INTO `hangman` (`ID`, `Nickname`, `Mistakes`, `State`, `Category`, `Date`) VALUES
(78, 'Santiago', 5, 'Winner', 'Fruits', 'Mar 10, 2020 - 14:48'),
(79, 'Juan', 1, 'Winner', 'Fruits', 'Mar 10, 2020 - 14:48'),
(77, 'Sebas', 3, 'Loser', 'Sports', 'Mar 10, 2020 - 14:47'),
(76, 'Santiago', 3, 'Winner', 'Sports', 'Mar 10, 2020 - 14:46'),
(80, 'Sebas', 2, 'Winner', 'Fruits', 'Mar 10, 2020 - 14:49');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventory`
--

CREATE TABLE `inventory` (
  `ID` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `State` text CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Price` double NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `inventory`
--

INSERT INTO `inventory` (`ID`, `State`, `Price`, `Quantity`) VALUES
('Laptop', 'New', 2000, 3),
('Smartphone', 'New', 500000, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `id` int(10) NOT NULL,
  `LibroCodigo` varchar(70) COLLATE utf8_spanish2_ci NOT NULL,
  `LibroTitulo` varchar(170) COLLATE utf8_spanish2_ci NOT NULL,
  `LibroAutor` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `LibroPais` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `LibroYear` int(4) NOT NULL,
  `LibroEditorial` varchar(70) COLLATE utf8_spanish2_ci NOT NULL,
  `LibroEdicion` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `LibroResumen` text COLLATE utf8_spanish2_ci NOT NULL,
  `LibroImagen` varchar(535) COLLATE utf8_spanish2_ci NOT NULL,
  `LibroPDF` varchar(535) COLLATE utf8_spanish2_ci NOT NULL,
  `LibroDescarga` varchar(5) COLLATE utf8_spanish2_ci NOT NULL,
  `CategoriaCodigo` varchar(30) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`id`, `LibroCodigo`, `LibroTitulo`, `LibroAutor`, `LibroPais`, `LibroYear`, `LibroEditorial`, `LibroEdicion`, `LibroResumen`, `LibroImagen`, `LibroPDF`, `LibroDescarga`, `CategoriaCodigo`) VALUES
(7, 'LB976837999', 'Codificación Y Criptografía', 'Joan Gómez', 'España', 2012, 'EDITEC', 'Primera', 'Un juego muy habitual en el patio de cualquier colegio es el de idear un alfabeto\r\nsecreto con el que enviar y recibir mensajes confidenciales. El esfuerzo dedicado a\r\nestos cÃ³digos primerizos responde mÃ¡s al entusiasmo de los jÃ³venes espÃ­as que al\r\ninterÃ©s de algÃºn tercero por fisgonear la informaciÃ³n asÃ­ transmitida. En el m undo\r\nde los adultos, sin embargo, este interÃ©s existe, y la confidencialidad de las com unicaciones constituye un tema de extraordinaria importancia.\r\nAntaÃ±o circunscritos a las actividades de una Ã©lite polÃ­tica y social, la llegada de\r\nla era de la informaciÃ³n ha hecho de la los cÃ³digos y las cifras una necesidad de la\r\nsociedad en su conjunto. Este libro se propone explicar la historia de los cÃ³digos\r\nsecretos de la mano del guÃ­a mÃ¡s cualificado para ello: las matemÃ¡ticas.', '1.png', '1.pdf', 'Yes', 'LC470446156'),
(8, 'LB881693613', 'Cómo Explicar Física Cuántica', 'Helena González', '', 0, '', '', '', '2.png', '2.pdf', 'Yes', 'LC367034405'),
(9, 'LB867323475', 'Curso JAVA', 'Jorge Sánchez', '', 2004, '', '', 'En 1991, la empresa Sun Microsystems crea el lenguaje Oak (de la mano del llamado\r\nproyecto Green). En 1995 pasa a llamarse Java y se da a conocer al pÃºblico y adquiere notoriedad\r\nrÃ¡pidamente. Java pasa a ser un lenguaje totalmente independiente de la plataforma y a la\r\nvez potente y orientado a objetos. Esa filosofÃ­a y su facilidad para crear aplicaciones para\r\nredes TCP/IP ha hecho que sea uno de los lenguajes mÃ¡s utilizados en la actualidad.', '3.png', '3.pdf', 'Yes', 'LC470446156'),
(10, 'LB138216629', 'Curso Angular 4', 'Pedro Jiménez Castela', '', 0, '', '', 'A lo largo de todo el libro aprenderemos las tÃ©cnicas Angular para crear los\r\ndiferentes componentes de un proyecto y para ponerlo en prÃ¡ctica, vamos a crear una aplicaciÃ³n partiendo desde cero para llegar a su despliegue en la\r\nnube.', '10.png', '10.pdf', 'Yes', 'LC470446156'),
(11, 'LB329644803', 'Curso HTML', 'Jorge Sánchez Asenjo', '', 2012, '', 'Segunda', 'HTML significa HyperText Markup Language. Es el lenguaje en que se escriben\r\nlos millones de documentos que hoy existen en el World Wide Web. Cuando accedemos a uno de estos documentos, el cliente (Netscape, IE, Mosaic, Lynx, IBrowse) los interpreta y los despliega. Existen clientes grÃ¡ficos como Netscape, y otros como el Lynx que solo despliegan texto.', '9.png', '9.pdf', 'Yes', 'LC470446156'),
(12, 'LB166924981', 'Curso PHP MySQL', 'Francisco Minera', '', 0, '', '', 'Lo que distingue a PHP de algo del lado del cliente como Javascript es que el cÃ³digo es ejecutado en el servidor, generando HTML y enviÃ¡ndolo al cliente. El cliente recibirÃ¡ el resultado de ejecutar el script, aunque no se sabrÃ¡ el cÃ³digo subyacente que era. El servidor web puede ser configurado incluso para que procese todos los ficheros HTML con PHP, por lo que no hay manera de que los usuarios puedan saber quÃ© se tiene debajo de la manga.', '11.png', '11.pdf', 'Yes', 'LC470446156'),
(13, 'LB688631474', 'Curso CSS', 'Javier Pérez', '', 0, '', '', 'CSS es un lenguaje de hojas de estilos creado para controlar el aspecto o presentaciÃ³n de los documentos electrÃ³nicos definidos con HTML y XHTML. CSS es la mejor forma de separar los contenidos y su presentaciÃ³n y es imprescindible para crear pÃ¡ginas web\r\ncomplejas.', '12.png', '12.pdf', 'Yes', 'LC470446156'),
(14, 'LB952701837', 'Curso Bootstrap 4', 'Desconocido', '', 0, '', '', 'Bootstrap 4 es la versiÃ³n mÃ¡s nueva de Bootstrap, que es el marco de HTML, CSS y\r\nJavaScript mÃ¡s popular para desarrollar sitios web responsivos y con prioridad para\r\ndispositivos mÃ³viles.\r\nÂ¡Bootstrap 4 se puede descargar y usar completamente gratis!', '14.png', '14.pdf', 'Yes', 'LC470446156'),
(15, 'LB789881091', 'Curso CSS3', 'Antonio Navajas Ojeda', '', 0, '', '', 'DespuÃ©s de HTML5, CSS3 es el segundo lenguaje que deberÃ­as aprender si piensas dedicarte al diseÃ±o y desarrollo web. Aunque HTML5 sirve para definir la estructura, CSS3 te permite darle un aspecto Ãºnico a tu sitio. Al finalizar de leer este artÃ­culo no te quedarÃ¡ duda alguna sobre el por quÃ© debes aprenderlo.', '13.png', '13.pdf', 'Yes', 'LC470446156'),
(16, 'LB873620304', 'Curso JavaScript', 'César Krall', '', 0, '', '', 'JavaScript (JS) es un lenguaje de programaciÃ³n cuyo uso principal ha venido siendo dotar de dinamismo, rapidez y efectos atractivos a las pÃ¡ginas web, mediante su uso combinado junto a HTML, CSS y otros lenguajes. Este curso permite aprender los fundamentos de JavaScript, imprescindible para trabajar con pÃ¡ginas web hoy dÃ­a.', '15.png', '15.pdf', 'Yes', 'LC470446156'),
(17, 'LB627134169', 'Curso Laravel 5', 'Desconocido', '', 0, '', '', 'Laravel es un framework de cÃ³digo abierto para desarrollar aplicaciones y servicios web con PHP 5 y PHP 7. Su filosofÃ­a es desarrollar cÃ³digo PHP de forma elegante y simple, evitando el cÃ³digo espagueti. Fue creado en 2011 y tiene una gran influencia de frameworks como Ruby on Rails, Sinatra y ASP.NET MVC.', '20.png', '20.pdf', 'Yes', 'LC470446156'),
(18, 'LB032459521', 'Curso Vue JS', 'Desconocido', '', 0, '', 'Segunda', 'Vue (en inglÃ©s, como view) es un framework progresivo para construir interfaces de usuario. A diferencia de otros frameworks monolÃ­ticos, Vue estÃ¡ diseÃ±ado desde el inicio para ser adoptado incrementalmente. La biblioteca principal se enfoca solo en la capa de la vista, y es muy simple de utilizar e integrar con otros proyectos o bibliotecas existentes. Por otro lado, Vue tambiÃ©n es perfectamente capaz de soportar aplicaciones sofisticadas de una sola pÃ¡gina (en inglÃ©s single-page-application o SPA) cuando se utiliza en combinaciÃ³n con herramientas modernas y librerÃ­as compatibles.', '21.png', '21.pdf', 'Yes', 'LC470446156'),
(19, 'LB230901535', 'Curso Python', 'Raúl González Duque', '', 0, '', '', 'Python es un lenguaje de programaciÃ³n creado por Guido van Rossum a principios de los aÃ±os 90 cuyo nombre estÃ¡ inspirado en el grupo de cÃ³micos ingleses â€œMonty Pythonâ€. Es un lenguaje similar a Perl, pero con una sintaxis muy limpia y que favorece un cÃ³digo legible. Se trata de un lenguaje interpretado o de script, con tipado dinÃ¡mico, fuertemente tipado, multiplataforma y orientado a objetos.', '22.png', '22.pdf', 'Yes', 'LC470446156'),
(20, 'LB195316238', 'Curso Android', 'Ricardo Moya García', '', 0, '', 'Primera', 'Antes de empezar con este proyecto reflexionÃ© acerca de la manera de obtener el conocimiento desde los inicios de la escritura hasta nuestra manera actual de enfocar el aprendizaje. Me hizo pensar en cÃ³mo enfocÃ¡bamos las cosas, la poca motivaciÃ³n que habÃ­a al estudiar tecnicismos, y que el mÃ©todo tradicional estaba rotundamente desfasado.  No soy un experto en psicologÃ­a, pero todos somos buenos conocedores de todo cuanto nos ocurre y tenemos perspectiva. Pronto me di cuenta que lo que mÃ¡s me interesaba, me\r\nmotivaba, me apasionaba, era aquello que podÃ­a entender desde la base y podÃ­a llevar al mundo real.', '23.png', '23.pdf', 'Yes', 'LC470446156'),
(21, 'LB864765062', 'Curso React Native', 'Desconocido', '', 0, '', '', 'Para los que no sepan quÃ© es React Native, os dirÃ© que es una de las mejores plataformas de desarrollo de aplicaciones mÃ³viles multiplataforma que existen en la actualidad junto con NativeScript. Se trata de un software de desarrollo multiplataforma donde podemos compilar con el mismo cÃ³digo fuente las respectivas aplicaciones para iOs, Android y Windows Phone. El lenguaje de programaciÃ³n que utiliza es el tan querido por todos JavaScript junto con la librerÃ­a ReactJS, tambiÃ©n desarrollada con anterioridad por los chicos de Facebook para el desarrollo web.', '24.png', '24.pdf', 'Yes', 'LC470446156'),
(22, 'LB048125121', 'Curso TypeScript', 'Emmanuel Valverde Ramos', '', 0, '', '', 'TypeScript es un lenguaje de programaciÃ³n moderno que permite crear aplicaciones web\r\nrobustas en JavaScript. TypeScript no requiere de ningÃºn tipo de plugin, puesto que lo que hace es generar cÃ³digo JavaScript que se ejecuta en cualquier navegador, plataforma o sistema operativo.', '25.png', '25.pdf', 'Yes', 'LC470446156'),
(23, 'LB282788839', 'Curso Angular JS', 'Maikel Rivero Dorta', '', 2016, '', 'Segunda', 'AngularJs paso a paso cubre el desarrollo de aplicaciones con el framework AngularJs. En este libro se tratarÃ¡n temas esenciales para el desarrollo de aplicaciones web del lado del cliente. AdemÃ¡s, trabajaremos con peticiones al servidor, consumiendo servicios\r\nREST y haciendo que nuestro sistema funcione en tiempo real sin tener que recargar la pÃ¡gina de nuestro navegador.', '26.png', '26.pdf', 'Yes', 'LC470446156'),
(24, 'LB156524955', 'El Mundo Y Sus Demonios', 'Carl Sagan', 'España', 1995, 'Planeta', 'Primera', 'Es mucho lo que la ciencia no entiende, quedan muchos misterios\r\ntodavÃ­a por resolver. En un universo que abarca decenas de miles de millones\r\nde aÃ±os luz y de unos diez o quince miles de millones de aÃ±os de antigÃ¼edad,\r\nquizÃ¡ siempre serÃ¡ asÃ­. Tropezamos constantemente con sorpresas. Sin\r\nembargo, algunos escritores y religiosos de la Â«Nueva EraÂ» afirman que los\r\ncientÃ­ficos creen que Â«lo que ellos encuentran es todo lo que existeÂ».', '5.png', '5.pdf', 'Yes', 'LC367034405'),
(25, 'LB529695597', 'Cosmos', 'Carl Sagan', 'España', 0, 'Planeta', 'SÃ©ptima', 'LlegarÃ¡ una Ã©poca en la que una investigaciÃ³n diligente y prolongada sacarÃ¡ a la luz cosas que hoy estÃ¡n ocultas. La vida de una sola persona, aunque estuviera toda ella dedicada al cielo, serÃ­a insuficiente para investigar una materia tan vasta... Por lo tanto este conocimiento sÃ³lo se podrÃ¡ desarrollar a lo largo de sucesivas edades.', '6.png', '6.pdf', 'Yes', 'LC367034405'),
(26, 'LB240506919', 'Historia Del Tiempo', 'Stephen Hawking', '', 0, '', '', 'Si se mira el cielo en una clara noche sin luna, los objetos mÃ¡s brillantes que uno ve\r\nson los planetas Venus, Marte, JÃºpiter y Saturno. TambiÃ©n se ve un gran nÃºmero de\r\nestrellas, que son como nuestro Sol, pero situadas a mucha mÃ¡s distancia de\r\nnosotros. Algunas de estas estrellas llamadas fijas cambian, de hecho, muy\r\nligeramente sus posiciones con respecto a las otras estrellas, cuando la Tierra gira\r\nalrededor del Sol: Â¡pero no estÃ¡n fijas en absoluto!', '7.png', '7.pdf', 'Yes', 'LC367034405'),
(27, 'LB293256116', 'Teoría De La Relatividad', 'Albert Einstein', '', 1916, '', '', 'El presente librito pretende dar una idea lo mÃ¡s exacta posible de la teorÃ­a de la\r\nrelatividad, pensando en aquellos que, sin dominar el aparato matemÃ¡tico de la\r\nfÃ­sica teÃ³rica, tienen interÃ©s en la teorÃ­a desde el punto de vista cientÃ­fico o filosÃ³fico\r\ngeneral. La lectura exige una formaciÃ³n de bachillerato aproximadamente y -pese a\r\nla brevedad del librito- no poca paciencia y voluntad por parte del lector.', '8.png', '8.pdf', 'Yes', 'LC367034405'),
(28, 'LB457034117', 'El Universo En Una Cáscara De Nuez', 'Stephen Hawking', '', 2001, '', '', 'No habÃ­a esperado que mi libro de divulgaciÃ³n, Historia del tiempo, tuviera tanto Ã©xito. Se mantuvo durante cuatro aÃ±os en la lista de superventas del London\r\nSunday Times, un perÃ­odo mÃ¡s largo que cualquier otro libro, lo cual resulta\r\nespecialmente notable para una obra cientÃ­fica que no era fÃ¡cil. Desde entonces, la gente me estuvo preguntando cuÃ¡ndo escribirÃ­a una continuaciÃ³n.', '16.png', '16.pdf', 'Yes', 'LC367034405'),
(29, 'LB601907040', 'Un Punto Azul Pálido', 'Carl Sagan', 'España', 2003, 'Planeta', 'Cuarta', 'Fuimos nÃ³madas desde los comienzos. ConocÃ­amos la posiciÃ³n de cada Ã¡rbol en\r\ncien millas a la redonda. Cuando sus frutos o nueces habÃ­an madurado, estÃ¡bamos\r\nallÃ­. SeguÃ­amos a los rebaÃ±os en sus migraciones anuales. DisfrutÃ¡bamos con la\r\ncarne fresca, con sigilo, haciendo amagos, organizando emboscadas y asaltos a\r\nfuerza viva, cooperando unos cuantos conseguÃ­amos lo que muchos de nosotros,\r\ncazando por separado, nunca habrÃ­amos logrado. DependÃ­amos los unos de los\r\notros.', '17.png', '17.pdf', 'Yes', 'LC367034405'),
(30, 'LB335558908', 'El Mundo Tal Como Yo Lo Veo', 'Albert Einstein', '', 0, '', '', 'Â¡QuÃ© mala suerte la de nosotros los mortales! Estamos aquÃ­ por un breve perÃ­odo, no sabemos con quÃ© propÃ³sito, aunque a veces creemos percibirlo. Pero no hace falta reflexionar mucho para saber, en contacto con la realidad cotidiana, que uno existe para otras personas: en primer lugar para aquellos de cuyas sonrisas\r\ny de cuyo bienestar depende totalmente nuestra propia felicidad, y luego para los muchos, para nuestros desconocidos, a cuyos destinos estamos ligados por lazos de afinidad.', '18.png', '18.pdf', 'Yes', 'LC367034405'),
(31, 'LB052927635', 'El Cerebro De Broca', 'Carl Sagan', 'España', 1979, 'Gribaldo', '', 'Vivimos en una Ã©poca extraordinaria. Son tiempos de cambios pasmosos en la organizaciÃ³n\r\nsocial, el bienestar econÃ³mico, los preceptos morales y Ã©ticos, las perspectivas filosÃ³ficas y\r\nreligiosas y el conocimiento que tiene el hombre de sÃ­ mismo, asÃ­ como en nuestra\r\ncomprensi6n de este inmenso universo que nos acoge como grano de arena dentro de un\r\nocÃ©ano cÃ³smico. Desde que el hombre es hombre se ha venido interrogando acerca de una\r\nserie de cuestiones profundas y fundamentales, que nos evocan maravillas y, cuando\r\nmenos, estimulan un conocimiento provisional y dubitativo. Son preguntas sobre los\r\norÃ­genes de la conciencia, la vida sobre nuestro planeta, los primeros tiempos de la Tierra,\r\nla formaciÃ³n del Sol, la posibilidad de que existan seres inteligentes en alguna otra parte de\r\nla inmensidad celeste. Y la mas ambiciosa e inquietante de todas, Â¿cual es el origen,\r\nnaturaleza y destino ultimo del universo?', '27.png', '27.pdf', 'Yes', 'LC367034405'),
(32, 'LB710885037', 'La Conexión Cósmica', 'Carl Sagan', '', 0, '', '', 'Cuando yo tenÃ­a doce aÃ±os, mi abuelo me preguntÃ³ â€“mediante un traductor, pues\r\nnunca habÃ­a aprendido bien el inglÃ©sâ€“ quÃ© querÃ­a ser cuando fuese mayor. Le\r\nrespondÃ­ que Â«astrÃ³nomoÂ», palabra que al cabo de unos momentos le tradujeron.\r\nÂ«SÃ­ â€“respondiÃ³â€“, Â¿pero cÃ³mo te ganarÃ¡s la vida?Â»', '28.png', '28.pdf', 'Yes', 'LC367034405');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `loans`
--

CREATE TABLE `loans` (
  `ID` int(10) NOT NULL,
  `User` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Name` text NOT NULL,
  `Document` text NOT NULL,
  `Reference` varchar(20) NOT NULL,
  `Phone` text NOT NULL,
  `TimeLimit` text NOT NULL,
  `Value` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `loans`
--

INSERT INTO `loans` (`ID`, `User`, `Name`, `Document`, `Reference`, `Phone`, `TimeLimit`, `Value`) VALUES
(7, 'root', 'Santiago', '123912839', 'Noooooooo', '1312839121', 'Oct 11, 2020', 998000),
(8, 'root', 'fgdfddgfdfg', '223423424', '232342342r', '2323423423', 'Mar 28, 2020', 99),
(9, 'root', 'fgfghfghfgf', '4556456456', 'fgfghfhfgf', '5654545451', 'Jun 21, 2020', 1090);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notes`
--

CREATE TABLE `notes` (
  `ID` int(10) NOT NULL,
  `Nickname` text NOT NULL,
  `Scale` text NOT NULL,
  `Percent` int(10) NOT NULL,
  `Note` text NOT NULL,
  `State` text NOT NULL,
  `Date` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `notes`
--

INSERT INTO `notes` (`ID`, `Nickname`, `Scale`, `Percent`, `Note`, `State`, `Date`) VALUES
(13, 'desarrollo', '0 to 5', 60, '2.20', 'Maybe', 'Mar 17, 2020 - 21:16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `purchases`
--

CREATE TABLE `purchases` (
  `ID` int(10) NOT NULL,
  `IdProduct` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `User` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Document` varchar(11) NOT NULL,
  `Phone` varchar(11) NOT NULL,
  `Date` text NOT NULL,
  `Units` int(11) NOT NULL,
  `Total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `purchases`
--

INSERT INTO `purchases` (`ID`, `IdProduct`, `User`, `Document`, `Phone`, `Date`, `Units`, `Total`) VALUES
(12, 'Smartphone', 'root', '1146441678', '3012499315', 'Mar 21, 2020 - 12:47', 5, 2500000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puzzle`
--

CREATE TABLE `puzzle` (
  `ID` int(10) NOT NULL,
  `Nickname` text COLLATE utf16_spanish2_ci NOT NULL,
  `State` text COLLATE utf16_spanish2_ci NOT NULL,
  `Time` text COLLATE utf16_spanish2_ci NOT NULL,
  `Attempts` int(10) NOT NULL,
  `Date` text COLLATE utf16_spanish2_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf16 COLLATE=utf16_spanish2_ci;

--
-- Volcado de datos para la tabla `puzzle`
--

INSERT INTO `puzzle` (`ID`, `Nickname`, `State`, `Time`, `Attempts`, `Date`) VALUES
(60, 'Santiago', 'Winner', 'Easy - 01:40', 225, 'Mar 10, 2020 - 14:39');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sales`
--

CREATE TABLE `sales` (
  `ID` int(10) NOT NULL,
  `IdProduct` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `User` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Document` varchar(10) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `Date` text NOT NULL,
  `Units` int(10) NOT NULL,
  `Total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sales`
--

INSERT INTO `sales` (`ID`, `IdProduct`, `User`, `Document`, `Phone`, `Date`, `Units`, `Total`) VALUES
(11, 'Smartphone', 'root', '1454154154', '5415415641', 'Mar 21, 2020 - 12:48', 2, 1000000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `Username` varchar(20) COLLATE ucs2_spanish2_ci NOT NULL,
  `Password` text COLLATE ucs2_spanish2_ci NOT NULL,
  `Status` text COLLATE ucs2_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_spanish2_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`ID`, `Username`, `Password`, `Status`) VALUES
(4, 'root', 'AiMXHgSmJDo=', 'Online'),
(15, 'admin', 'tKb+6mQlB1M=', 'Offline');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `CuentaCodigo` (`CuentaCodigo`);

--
-- Indices de la tabla `bitacora`
--
ALTER TABLE `bitacora`
  ADD PRIMARY KEY (`id`),
  ADD KEY `CuentaCodigo` (`CuentaCodigo`);

--
-- Indices de la tabla `cashregister`
--
ALTER TABLE `cashregister`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Usuario` (`User`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `CategoriaCodigo` (`CategoriaCodigo`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `CuentaCodigo` (`CuentaCodigo`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `CuentaCodigo` (`CuentaCodigo`);

--
-- Indices de la tabla `dices`
--
ALTER TABLE `dices`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `guessnumber`
--
ALTER TABLE `guessnumber`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `hangman`
--
ALTER TABLE `hangman`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `LibroCodigo` (`LibroCodigo`),
  ADD KEY `CategoriaCodigo` (`CategoriaCodigo`);

--
-- Indices de la tabla `loans`
--
ALTER TABLE `loans`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Usuario` (`User`),
  ADD KEY `Usuario_2` (`User`),
  ADD KEY `Usuario_3` (`User`);

--
-- Indices de la tabla `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `IDPRODUCTO` (`IdProduct`),
  ADD KEY `Usuario` (`User`);

--
-- Indices de la tabla `puzzle`
--
ALTER TABLE `puzzle`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `IDPRODUCTO` (`IdProduct`),
  ADD KEY `IDUSUARIO` (`User`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `User_Name` (`Username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `bitacora`
--
ALTER TABLE `bitacora`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=266;

--
-- AUTO_INCREMENT de la tabla `cashregister`
--
ALTER TABLE `cashregister`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `dices`
--
ALTER TABLE `dices`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT de la tabla `feedback`
--
ALTER TABLE `feedback`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `guessnumber`
--
ALTER TABLE `guessnumber`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=148;

--
-- AUTO_INCREMENT de la tabla `hangman`
--
ALTER TABLE `hangman`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `loans`
--
ALTER TABLE `loans`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `notes`
--
ALTER TABLE `notes`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `purchases`
--
ALTER TABLE `purchases`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `puzzle`
--
ALTER TABLE `puzzle`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de la tabla `sales`
--
ALTER TABLE `sales`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`CuentaCodigo`) REFERENCES `cuenta` (`CuentaCodigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `bitacora`
--
ALTER TABLE `bitacora`
  ADD CONSTRAINT `bitacora_ibfk_1` FOREIGN KEY (`CuentaCodigo`) REFERENCES `cuenta` (`CuentaCodigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cashregister`
--
ALTER TABLE `cashregister`
  ADD CONSTRAINT `cashregister_ibfk_1` FOREIGN KEY (`User`) REFERENCES `users` (`UserName`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`CuentaCodigo`) REFERENCES `cuenta` (`CuentaCodigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`CategoriaCodigo`) REFERENCES `categoria` (`CategoriaCodigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `loans`
--
ALTER TABLE `loans`
  ADD CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`User`) REFERENCES `users` (`UserName`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`User`) REFERENCES `cashregister` (`User`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `purchases`
--
ALTER TABLE `purchases`
  ADD CONSTRAINT `purchases_ibfk_2` FOREIGN KEY (`IdProduct`) REFERENCES `inventory` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `purchases_ibfk_3` FOREIGN KEY (`User`) REFERENCES `users` (`UserName`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `purchases_ibfk_4` FOREIGN KEY (`User`) REFERENCES `cashregister` (`User`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`IdProduct`) REFERENCES `inventory` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sales_ibfk_3` FOREIGN KEY (`User`) REFERENCES `users` (`UserName`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sales_ibfk_4` FOREIGN KEY (`User`) REFERENCES `cashregister` (`User`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
