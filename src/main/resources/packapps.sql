-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-03-2020 a las 05:13:52
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
(1, 'root', 19, 4750000, 19, 4750000, 0);

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
('Celular', 'Usado', 250000, 0),
('Coca Cola', 'Nuevo', 2000, 0),
('Pelota', 'Nuevo', 5600, 0);

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
(1, 'Celular', 'root', '1235468978', '4567898', '7/01/2019 - 22:34', 6, 1500000),
(2, 'Celular', 'root', '1231231231', '1231232', '16/01/2019 - 22:38', 10, 2500000),
(3, 'Celular', 'root', '534534534', '5345345334', '16/01/2019 - 22:41', 3, 750000);

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
(55, 'perdedor', 'Loser', 'Easy - 00:00', 247, 'Nov 28, 2019 - 17:25'),
(56, 'Santiago', 'Winner', 'Easy - 00:32', 275, 'Jan 14, 2020 - 10:22');

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
(1, 'Celular', 'root', '123546879', '4567898', '7/01/2019 - 22:35', 6, 1500000),
(2, 'Celular', 'root', '3213211212', '1231231212', 'Dec 9, 2019 - 11:22', 13, 3250000);

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
(4, 'root', 'AiMXHgSmJDo=', 'Offline'),
(7, 'santiago', 'MRt7tAdZ5k9kZw+ssciRsQ==', 'Offline'),
(8, 'yoyoyohjhu', 'J3lUKjFV6CZwW9vWrZei3Q==', 'Offline');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cashregister`
--
ALTER TABLE `cashregister`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Usuario` (`User`);

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
-- AUTO_INCREMENT de la tabla `cashregister`
--
ALTER TABLE `cashregister`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `dices`
--
ALTER TABLE `dices`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT de la tabla `feedback`
--
ALTER TABLE `feedback`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `guessnumber`
--
ALTER TABLE `guessnumber`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=140;

--
-- AUTO_INCREMENT de la tabla `hangman`
--
ALTER TABLE `hangman`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT de la tabla `loans`
--
ALTER TABLE `loans`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `notes`
--
ALTER TABLE `notes`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `purchases`
--
ALTER TABLE `purchases`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `puzzle`
--
ALTER TABLE `puzzle`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT de la tabla `sales`
--
ALTER TABLE `sales`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cashregister`
--
ALTER TABLE `cashregister`
  ADD CONSTRAINT `cashregister_ibfk_1` FOREIGN KEY (`User`) REFERENCES `users` (`UserName`) ON DELETE CASCADE ON UPDATE CASCADE;

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
