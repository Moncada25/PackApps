-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-01-2019 a las 21:42:36
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mipc_23093085_appsimples`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `adivinar`
--

CREATE TABLE `adivinar` (
  `ID` int(10) NOT NULL,
  `Nickname` text COLLATE ucs2_spanish2_ci NOT NULL,
  `Limite` int(10) NOT NULL,
  `Intentos` text COLLATE ucs2_spanish2_ci NOT NULL,
  `Date` text COLLATE ucs2_spanish2_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=ucs2 COLLATE=ucs2_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ahorcado`
--

CREATE TABLE `ahorcado` (
  `ID` int(10) NOT NULL,
  `Nickname` text COLLATE utf16_spanish2_ci NOT NULL,
  `Mistakes` int(10) NOT NULL,
  `State` text COLLATE utf16_spanish2_ci NOT NULL,
  `Category` text COLLATE utf16_spanish2_ci,
  `Date` text COLLATE utf16_spanish2_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf16 COLLATE=utf16_spanish2_ci;

--
-- Volcado de datos para la tabla `ahorcado`
--

INSERT INTO `ahorcado` (`ID`, `Nickname`, `Mistakes`, `State`, `Category`, `Date`) VALUES
(65, 'dffdgdgf', 7, 'Loser', 'Colores', '30/12/2018 - 2:19'),
(64, 'admin', 7, 'Loser', 'Deportes', '28/12/2018 - 14:24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `ID` int(10) NOT NULL,
  `IDPRODUCTO` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Usuario` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Documento` varchar(11) NOT NULL,
  `Telefono` varchar(11) NOT NULL,
  `Date` text NOT NULL,
  `Unidades` int(11) NOT NULL,
  `Total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `compras`
--

INSERT INTO `compras` (`ID`, `IDPRODUCTO`, `Usuario`, `Documento`, `Telefono`, `Date`, `Unidades`, `Total`) VALUES
(1, 'Celular', 'root', '1235468978', '4567898', '7/01/2019 - 22:34', 6, 1500000),
(2, 'Celular', 'root', '1231231231', '1231232', '16/01/2019 - 22:38', 10, 2500000),
(3, 'Celular', 'root', '534534534', '5345345334', '16/01/2019 - 22:41', 3, 750000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dados`
--

CREATE TABLE `dados` (
  `ID` int(10) NOT NULL,
  `Nickname` text NOT NULL,
  `Winner` text NOT NULL,
  `Round` int(10) NOT NULL,
  `Date` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `dados`
--

INSERT INTO `dados` (`ID`, `Nickname`, `Winner`, `Round`, `Date`) VALUES
(95, 'Third', '49 points', 5, '11/01/2019 - 0:14'),
(94, 'Third', '¡Dados iguales!', 5, '30/12/2018 - 19:35'),
(93, 'Third', '63 points', 5, '30/12/2018 - 19:33'),
(92, 'First', '64 points', 5, '30/12/2018 - 19:33'),
(91, 'Third', '¡Dados iguales!', 4, '30/12/2018 - 19:33'),
(89, 'First', '¡Dados iguales!', 3, '30/12/2018 - 2:15'),
(90, 'Second', '65 points', 5, '30/12/2018 - 19:33'),
(88, 'First & Third', '47 points', 5, '30/12/2018 - 2:15'),
(87, 'Second', '51 points', 5, '30/12/2018 - 2:15'),
(86, 'First', '63 points', 5, '30/12/2018 - 2:15'),
(85, 'Third', '56 points', 5, '30/12/2018 - 2:15'),
(84, 'First', '¡Dados iguales!', 1, '30/12/2018 - 2:15'),
(83, 'Third', '¡Dados iguales!', 2, '30/12/2018 - 2:15'),
(82, 'Third', '¡Dados iguales!', 2, '30/12/2018 - 2:15'),
(81, 'Third', '¡Dados iguales!', 2, '30/12/2018 - 2:15'),
(80, 'First & Second', '52 points', 5, '30/12/2018 - 2:15'),
(77, 'Third', '55 points', 5, '30/12/2018 - 2:13'),
(78, 'Third', '58 points', 5, '30/12/2018 - 2:13'),
(79, 'Third', '67 points', 5, '30/12/2018 - 2:13'),
(76, 'Second & Third', '59 points', 5, '30/12/2018 - 2:13'),
(75, 'First', '56 points', 5, '30/12/2018 - 2:13'),
(73, 'Third', '55 points', 5, '30/12/2018 - 2:12'),
(74, 'Third', '60 points', 5, '30/12/2018 - 2:12'),
(72, 'First & Third', '60 points', 5, '30/12/2018 - 2:12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `feedback`
--

CREATE TABLE `feedback` (
  `ID` int(10) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Mensaje` text NOT NULL,
  `Date` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `ID` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Estado` text CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Precio` double NOT NULL,
  `Cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`ID`, `Estado`, `Precio`, `Cantidad`) VALUES
('Celular', 'Usado', 250000, 13),
('Coca Cola', 'Nuevo', 2000, 0),
('Pelota', 'Nuevo', 5600, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notas`
--

CREATE TABLE `notas` (
  `ID` int(10) NOT NULL,
  `Nickname` text NOT NULL,
  `Scale` text NOT NULL,
  `Percent` int(10) NOT NULL,
  `Note` text NOT NULL,
  `State` text NOT NULL,
  `Date` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `préstamos`
--

CREATE TABLE `préstamos` (
  `ID` int(10) NOT NULL,
  `Usuario` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Nombre` text NOT NULL,
  `Documento` text NOT NULL,
  `Referencia` varchar(20) NOT NULL,
  `Teléfono` text NOT NULL,
  `Plazo` text NOT NULL,
  `Valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registros`
--

CREATE TABLE `registros` (
  `ID` int(10) NOT NULL,
  `Usuario` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Productos_Vendidos` int(11) NOT NULL,
  `Total_Ventas` double NOT NULL,
  `Productos_Comprados` int(11) NOT NULL,
  `Total_Compras` double NOT NULL,
  `Total_Prestamos` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `registros`
--

INSERT INTO `registros` (`ID`, `Usuario`, `Productos_Vendidos`, `Total_Ventas`, `Productos_Comprados`, `Total_Compras`, `Total_Prestamos`) VALUES
(1, 'root', 6, 1500000, 19, 4750000, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rompecabezas`
--

CREATE TABLE `rompecabezas` (
  `ID` int(10) NOT NULL,
  `Nickname` text COLLATE utf16_spanish2_ci NOT NULL,
  `State` text COLLATE utf16_spanish2_ci NOT NULL,
  `Tiempo` text COLLATE utf16_spanish2_ci NOT NULL,
  `Jugadas` int(10) NOT NULL,
  `Date` text COLLATE utf16_spanish2_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf16 COLLATE=utf16_spanish2_ci;

--
-- Volcado de datos para la tabla `rompecabezas`
--

INSERT INTO `rompecabezas` (`ID`, `Nickname`, `State`, `Tiempo`, `Jugadas`, `Date`) VALUES
(54, 'santiago', 'Loser', 'Easy - 02:55', 11, '1/01/2019 - 16:1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID` int(11) NOT NULL,
  `User_Name` varchar(20) COLLATE ucs2_spanish2_ci NOT NULL,
  `Password` text COLLATE ucs2_spanish2_ci NOT NULL,
  `Status` text COLLATE ucs2_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID`, `User_Name`, `Password`, `Status`) VALUES
(4, 'root', 'AiMXHgSmJDo=', 'Offline'),
(6, 'Santiago', 'MRt7tAdZ5k9kZw+ssciRsQ==', 'Offline');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `ID` int(10) NOT NULL,
  `IDPRODUCTO` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Usuario` varchar(20) CHARACTER SET ucs2 COLLATE ucs2_spanish2_ci NOT NULL,
  `Documento` varchar(10) NOT NULL,
  `Telefono` varchar(10) NOT NULL,
  `Date` text NOT NULL,
  `Unidades` int(10) NOT NULL,
  `Total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`ID`, `IDPRODUCTO`, `Usuario`, `Documento`, `Telefono`, `Date`, `Unidades`, `Total`) VALUES
(1, 'Celular', 'root', '123546879', '4567898', '7/01/2019 - 22:35', 6, 1500000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `adivinar`
--
ALTER TABLE `adivinar`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `ahorcado`
--
ALTER TABLE `ahorcado`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `IDPRODUCTO` (`IDPRODUCTO`),
  ADD KEY `Usuario` (`Usuario`);

--
-- Indices de la tabla `dados`
--
ALTER TABLE `dados`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `notas`
--
ALTER TABLE `notas`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `préstamos`
--
ALTER TABLE `préstamos`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Usuario` (`Usuario`),
  ADD KEY `Usuario_2` (`Usuario`),
  ADD KEY `Usuario_3` (`Usuario`);

--
-- Indices de la tabla `registros`
--
ALTER TABLE `registros`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Usuario` (`Usuario`);

--
-- Indices de la tabla `rompecabezas`
--
ALTER TABLE `rompecabezas`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `User_Name` (`User_Name`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `IDPRODUCTO` (`IDPRODUCTO`),
  ADD KEY `IDUSUARIO` (`Usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `adivinar`
--
ALTER TABLE `adivinar`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=133;

--
-- AUTO_INCREMENT de la tabla `ahorcado`
--
ALTER TABLE `ahorcado`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `dados`
--
ALTER TABLE `dados`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- AUTO_INCREMENT de la tabla `feedback`
--
ALTER TABLE `feedback`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `notas`
--
ALTER TABLE `notas`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `préstamos`
--
ALTER TABLE `préstamos`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `registros`
--
ALTER TABLE `registros`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `rompecabezas`
--
ALTER TABLE `rompecabezas`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`IDPRODUCTO`) REFERENCES `inventario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `compras_ibfk_3` FOREIGN KEY (`Usuario`) REFERENCES `usuarios` (`User_Name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `compras_ibfk_4` FOREIGN KEY (`Usuario`) REFERENCES `registros` (`Usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `préstamos`
--
ALTER TABLE `préstamos`
  ADD CONSTRAINT `préstamos_ibfk_1` FOREIGN KEY (`Usuario`) REFERENCES `usuarios` (`User_Name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `préstamos_ibfk_2` FOREIGN KEY (`Usuario`) REFERENCES `registros` (`Usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `registros`
--
ALTER TABLE `registros`
  ADD CONSTRAINT `registros_ibfk_1` FOREIGN KEY (`Usuario`) REFERENCES `usuarios` (`User_Name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`IDPRODUCTO`) REFERENCES `inventario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ventas_ibfk_3` FOREIGN KEY (`Usuario`) REFERENCES `usuarios` (`User_Name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ventas_ibfk_4` FOREIGN KEY (`Usuario`) REFERENCES `registros` (`Usuario`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
