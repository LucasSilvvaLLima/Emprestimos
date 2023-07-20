DROP DATABASE consignado;
CREATE DATABASE consignado;
USE consignado;
CREATE TABLE cliente(
	idCliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    whatsApp VARCHAR(45) NOT NULL,
    dataEmprestimo VARCHAR(45) NOT NULL,
    valorTotalDivida DOUBLE NOT NULL,
    dataPagamentoMensal VARCHAR(45),
    valorPagamentoMensal DOUBLE,
    porcentagem int,
    imagem VARCHAR(245),
    valorTotalEmprestimo DOUBLE,
     rendimento DOUBLE
);

ALTER TABLE cliente ADD COLUMN rendimento DOUBLE;
Create TABLE historicoPagamento(
	idHistorico INT PRIMARY KEY AUTO_INCREMENT,
    fkCliente INT,
    FOREIGN KEY (fkCliente) REFERENCES cliente(idCliente),
    dataPagamentoMensal VARCHAR(45),
    valorPagamentoMensal DOUBLE
);

select * from cliente;
select * from historicopagamento;