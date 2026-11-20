CREATE TABLE IF NOT EXISTS `cashregister` (
  `ID` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `User` varchar(20) NOT NULL,
  `SoldProducts` INTEGER NOT NULL,
  `TotalSales` double NOT NULL,
  `PurchasedProducts` INTEGER NOT NULL,
  `TotalPurchases` double NOT NULL,
  `TotalLoans` double NOT NULL
);

CREATE TABLE IF NOT EXISTS `dices` (
  `ID` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `Nickname` text NOT NULL,
  `Winner` text NOT NULL,
  `Round` INTEGER NOT NULL,
  `Date` text NOT NULL
);

CREATE TABLE IF NOT EXISTS `feedback` (
  `ID` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `Username` varchar(255) NOT NULL,
  `Message` text NOT NULL,
  `Date` text NOT NULL
);

CREATE TABLE IF NOT EXISTS `guessnumber` (
  `ID` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `Nickname` text NOT NULL,
  `Limitation` INTEGER NOT NULL,
  `Level` text NOT NULL,
  `Date` text NOT NULL
);

CREATE TABLE IF NOT EXISTS `hangman` (
  `ID` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `Nickname` text NOT NULL,
  `Mistakes` INTEGER NOT NULL,
  `State` text NOT NULL,
  `Category` text DEFAULT NULL,
  `Date` text NOT NULL
);

CREATE TABLE IF NOT EXISTS `inventory` (
  `ID` varchar(20) PRIMARY KEY,
  `State` text NOT NULL,
  `Price` double NOT NULL,
  `Quantity` INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS `loans` (
  `ID` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `User` varchar(20) NOT NULL,
  `Name` text NOT NULL,
  `Document` text NOT NULL,
  `Reference` varchar(20) NOT NULL,
  `Phone` text NOT NULL,
  `TimeLimit` text NOT NULL,
  `Value` double NOT NULL
);

CREATE TABLE IF NOT EXISTS `notes` (
  `ID` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `Nickname` text NOT NULL,
  `Scale` text NOT NULL,
  `Percent` INTEGER NOT NULL,
  `Note` text NOT NULL,
  `State` text NOT NULL,
  `Date` text NOT NULL
);

CREATE TABLE IF NOT EXISTS `purchases` (
  `ID` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `IdProduct` varchar(20) NOT NULL,
  `User` varchar(20) NOT NULL,
  `Document` varchar(11) NOT NULL,
  `Phone` varchar(11) NOT NULL,
  `Date` text NOT NULL,
  `Units` INTEGER NOT NULL,
  `Total` double NOT NULL
);

CREATE TABLE IF NOT EXISTS `puzzle` (
  `ID` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `Nickname` text NOT NULL,
  `State` text NOT NULL,
  `Time` text NOT NULL,
  `Attempts` INTEGER NOT NULL,
  `Date` text NOT NULL
);

CREATE TABLE IF NOT EXISTS `sales` (
  `ID` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `IdProduct` varchar(20) NOT NULL,
  `User` varchar(20) NOT NULL,
  `Document` varchar(10) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `Date` text NOT NULL,
  `Units` INTEGER NOT NULL,
  `Total` double NOT NULL
);

CREATE TABLE IF NOT EXISTS `users` (
  `ID` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `Username` varchar(20) NOT NULL UNIQUE,
  `Password` text NOT NULL,
  `Status` text NOT NULL
);
