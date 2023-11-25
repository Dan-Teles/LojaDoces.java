CREATE TABLE LOGIN (
	LOGIN VARCHAR(30),
    SENHA VARCHAR(20),
    CONSTRAINT PK_Login PRIMARY KEY (LOGIN,SENHA)
);

CREATE TABLE FORNECEDOR (
	CNPJ INT PRIMARY KEY,
    RazaoSocial VARCHAR(40),
    NomeFantasia VARCHAR(40),
    EMAIL VARCHAR(50),
    TELEFONE VARCHAR(14),
    CEP VARCHAR(16),
    ESTADO VARCHAR(20),
    CIDADE VARCHAR(50),
    BAIRRO VARCHAR(50),
    LOGRADOURO VARCHAR(50),
    NUMERO INT
);

CREATE TABLE CLIENTE (
	IdCLIENTE INTEGER PRIMARY KEY auto_increment,
	NOME VARCHAR(60),
    CPF VARCHAR(14),
    TELEFONE VARCHAR(14),
    EMAIL VARCHAR(50),
    DATANASCIMENTO VARCHAR(12),
    GENERO VARCHAR(25),
    LOGIN VARCHAR(20),
    SENHA VARCHAR(20)
);

CREATE TABLE PRODUTO (
	CODIGO VARCHAR(20) PRIMARY KEY,
    CATEGORIA VARCHAR(20),
    NomeFantasiacliente VARCHAR(40),
    DESCRICAO VARCHAR(80),
    ESTOQUE FLOAT,
    PRECO FLOAT
);

CREATE TABLE carrinho (
	idPedido int primary key auto_increment,
    produto varchar (50),
    login varchar (50),
    quantidade float,
    valor float,
    valorTotal float,
    fornecedor varchar(40)
);

CREATE TABLE pedido (
	categoria varchar(20),
    produto varchar(50),
    estoque float,
    valor float,
    fornecedor varchar(40),
    CONSTRAINT PK_Pedido PRIMARY KEY (produto,fornecedor)
);