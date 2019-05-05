-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 05-Maio-2019 às 21:18
-- Versão do servidor: 10.1.38-MariaDB
-- versão do PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cv_servicos`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `api_clientes`
--

CREATE TABLE `api_clientes` (
  `id` int(11) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cpf_cnpj` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefone` int(20) DEFAULT NULL,
  `descricao` text,
  `data_cadastro` date NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `api_clientes`
--

INSERT INTO `api_clientes` (`id`, `tipo`, `nome`, `cpf_cnpj`, `email`, `telefone`, `descricao`, `data_cadastro`, `status`) VALUES
(1, '1', 'Bruno 1', '123456789709', 'teste@teste.com', 789789, 'Primeiro cliente.', '2019-05-05', 'SIM');

-- --------------------------------------------------------

--
-- Estrutura da tabela `api_produtos`
--

CREATE TABLE `api_produtos` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `sku` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `preco` double NOT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `descricao` text,
  `data_cadastro` date NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `api_produtos`
--

INSERT INTO `api_produtos` (`id`, `id_categoria`, `sku`, `nome`, `preco`, `quantidade`, `descricao`, `data_cadastro`, `status`) VALUES
(6, 1, '123456456', 'Biscoito', 2, 5, '', '2019-05-05', 'SIM'),
(7, 1, '789456456', 'Suco', 5, 10, '', '2019-05-05', 'SIM');

-- --------------------------------------------------------

--
-- Estrutura da tabela `sys_usuario`
--

CREATE TABLE `sys_usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `sys_usuario`
--

INSERT INTO `sys_usuario` (`id`, `nome`, `email`, `senha`, `status`) VALUES
(1, 'adm', 'adm@teste.com', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'SIM');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `api_clientes`
--
ALTER TABLE `api_clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `api_produtos`
--
ALTER TABLE `api_produtos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sys_usuario`
--
ALTER TABLE `sys_usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `api_clientes`
--
ALTER TABLE `api_clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `api_produtos`
--
ALTER TABLE `api_produtos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `sys_usuario`
--
ALTER TABLE `sys_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
