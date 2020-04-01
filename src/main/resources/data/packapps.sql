-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-03-2020 a las 07:13:45
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
(1, 'Santiago', 'Moncada', '+573012499315', 'Colombia, Medellín', 'AC750702071');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bitacora`
--

CREATE TABLE `bitacora` (
  `id` int(10) NOT NULL,
  `BitacoraCodigo` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `BitacoraFecha` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
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
(346, 'CB29755651', 'Mar 29, 2020', '23:36:16', '00:12:20', 'Administrador', 2020, 'AC750702071'),
(347, 'CB93870762', 'Mar 30, 2020', '00:12:39', '00:16:57', 'Administrador', 2020, 'AC750702071'),
(348, 'CB76322283', 'Mar 30, 2020', '00:26:27', 'Sin registro', 'Administrador', 2020, 'AC750702071'),
(349, 'CB32883024', 'Mar 30, 2020', '11:57:55', 'Sin registro', 'Administrador', 2020, 'AC750702071'),
(350, 'CB61103585', 'Mar 30, 2020', '15:57:14', '16:04:07', 'Administrador', 2020, 'AC750702071'),
(351, 'CB66843136', 'Mar 30, 2020', '16:04:15', '16:30:18', 'Cliente', 2020, 'CC810442852'),
(352, 'CB80978787', 'Mar 30, 2020', '16:30:21', 'Sin registro', 'Administrador', 2020, 'AC750702071');

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
(5, 'LC367034405', 'Ciencia'),
(6, 'LC470446156', 'Programación');

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

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `ClienteNombre`, `ClienteApellido`, `ClienteTelefono`, `ClienteOcupacion`, `ClienteDireccion`, `CuentaCodigo`) VALUES
(31, 'Santiago', 'Moncada', '3012499315', 'Otro', 'CR 62 #62-b11', 'CC810442852');

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
(1, 'AC750702071', 1, 'root', 'enl3OHVSRXgwd1BNVlkzMU9NdXo2UT09', 'zanti4020@gmail.com', 'Activo', 'Administrador', 'Masculino', 'U.png'),
(39, 'CC810442852', 4, 'PackApps', 'YytvcW1nY08ySUV3YUs0QUZOUi9udz09', 'packapps@gmail.com', 'Activo', 'Cliente', 'Masculino', 'Male2Avatar.png');

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

--
-- Volcado de datos para la tabla `guessnumber`
--

INSERT INTO `guessnumber` (`ID`, `Nickname`, `Limitation`, `Level`, `Date`) VALUES
(149, 'ooooooooo', 4, 'Easy - 2', 'Mar 27, 2020 - 13:24');

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
(80, 'Sebas', 2, 'Winner', 'Fruits', 'Mar 10, 2020 - 14:49'),
(81, 'Hoy', 1, 'Winner', 'Sports', 'Mar 24, 2020 - 22:56');

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
(10, 'LB138216629', 'Curso Angular 4', 'Pedro Jiménez Castela', '', 0, '', '', 'A lo largo de todo el libro aprenderemos las técnicas Angular para crear los\r\ndiferentes componentes de un proyecto y para ponerlo en prÃ¡ctica, vamos a crear una aplicaciÃ³n partiendo desde cero para llegar a su despliegue en la\r\nnube.', '10.png', '10.pdf', 'Yes', 'LC470446156'),
(11, 'LB329644803', 'Curso HTML', 'Jorge Sánchez Asenjo', '', 2012, '', 'Segunda', 'HTML significa HyperText Markup Language. Es el lenguaje en que se escriben\r\nlos millones de documentos que hoy existen en el World Wide Web. Cuando accedemos a uno de estos documentos, el cliente (Netscape, IE, Mosaic, Lynx, IBrowse) los interpreta y los despliega. Existen clientes grÃ¡ficos como Netscape, y otros como el Lynx que solo despliegan texto.', '9.png', '9.pdf', 'Yes', 'LC470446156'),
(12, 'LB166924981', 'Curso PHP Y MySQL', 'José Mariano González Romano', '', 0, '', '', 'PHP es un lenguaje de script del lado del servidor. Otros lenguajes similares son ASP, JSP o ColdFusion. Los scripts PHP están incrustados en los documentos HTML y el servidor los interpreta y ejecuta antes de servir las páginas al cliente. El cliente no ve el código PHP sino los resultados que produce.', '11.png', '11.pdf', 'Yes', 'LC470446156'),
(13, 'LB688631474', 'Curso CSS', 'Javier Pérez', '', 0, '', '', 'CSS es un lenguaje de hojas de estilos creado para controlar el aspecto o presentaciÃ³n de los documentos electrÃ³nicos definidos con HTML y XHTML. CSS es la mejor forma de separar los contenidos y su presentaciÃ³n y es imprescindible para crear pÃ¡ginas web\r\ncomplejas.', '12.png', '12.pdf', 'Yes', 'LC470446156'),
(14, 'LB952701837', 'Curso Bootstrap 4', 'Desconocido', '', 0, '', '', 'Bootstrap 4 es la versiÃ³n mÃ¡s nueva de Bootstrap, que es el marco de HTML, CSS y\r\nJavaScript más popular para desarrollar sitios web responsivos y con prioridad para\r\ndispositivos móviles.\r\n¡Bootstrap 4 se puede descargar y usar completamente gratis!', '14.png', '14.pdf', 'Yes', 'LC470446156'),
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
(62, 'Hoy', 'Loser', 'Hard - 09:50', 18, 'Mar 24, 2020 - 22:57'),
(61, 'Hoy', 'Loser', 'Easy - 02:56', 5, 'Mar 24, 2020 - 22:57'),
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `task`
--

CREATE TABLE `task` (
  `ID` int(11) NOT NULL,
  `UserAssigned` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `Task` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Points` int(11) NOT NULL,
  `Status` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `Date` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `AccountCode` varchar(60) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `task`
--

INSERT INTO `task` (`ID`, `UserAssigned`, `Task`, `Description`, `Points`, `Status`, `Date`, `AccountCode`) VALUES
(11, 'Santiago Moncada (root)', 'Ejemplo', 'Esto es una prueba', 12, 'Nueva', 'Mar 31, 2020 - 00:12', 'AC750702071');

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
(15, 'admin', 'tKb+6mQlB1M=', 'Offline'),
(18, 'djdhsadjh', 'vs7N3WgQlIDTflVHoy1sdw==', 'Offline'),
(19, 'root', 'zfmxAPNbwdM=', 'Offline');

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
-- Indices de la tabla `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CuentaCodigo` (`AccountCode`);

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
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=353;

--
-- AUTO_INCREMENT de la tabla `cashregister`
--
ALTER TABLE `cashregister`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `dices`
--
ALTER TABLE `dices`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- AUTO_INCREMENT de la tabla `feedback`
--
ALTER TABLE `feedback`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `guessnumber`
--
ALTER TABLE `guessnumber`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=150;

--
-- AUTO_INCREMENT de la tabla `hangman`
--
ALTER TABLE `hangman`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

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
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT de la tabla `sales`
--
ALTER TABLE `sales`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `task`
--
ALTER TABLE `task`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

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

--
-- Filtros para la tabla `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`AccountCode`) REFERENCES `cuenta` (`CuentaCodigo`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
