-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-02-2020 a las 18:14:10
-- Versión del servidor: 5.7.14
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_restaurante`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `platos`
--

CREATE TABLE `platos` (
  `ID` int(11) DEFAULT NULL,
  `NOMBRE` varchar(25) COLLATE utf8_spanish_ci NOT NULL,
  `PRECIO` int(11) NOT NULL,
  `DESCRIPCION` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `IMAGEN` varchar(150) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `platos`
--

INSERT INTO `platos` (`ID`, `NOMBRE`, `PRECIO`, `DESCRIPCION`, `IMAGEN`) VALUES
(1, 'Pasticho', 5, 'Rico Pasticho Con los Mejores Ingredientes', '\\WebRestaurante\\images\\pasticho.jpg'),
(2, 'Pizza Jamon y Queso', 5, 'Pizza Jamon y Queso Excelente Recomendacion ', '\\WebRestaurante\\images\\pizzajamonyqueso.jpg'),
(3, 'Pasta Bechamel', 4, 'Pasta Bechamel Con Vegetales Fresco', '\\WebRestaurante\\images\\pastabechamel.jpg'),
(4, 'Hamburguesa Con Papas ', 4, 'Excelente Hamburguesa Con Papas Fritas Estilo Americano', '/WebRestaurante/images/hamburguesa.jpg'),
(5, 'Reina Pepiada (Arepa)', 2, 'Arepa, Comida Tradicional Venezolana,100% Recomendada', '\\WebRestaurante\\images\\arepa.jpg');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
