create table usuario (
	id serial primary key,
	email varchar(100)not null,
	senha varchar (16) not null
)



create table endereco(
	id serial primary key,
	cep varchar(8)not null,
	logradouro varchar(150)not null,
	numero varchar(10)not null,
	bairro varchar(150)not null,
	cidade varchar (150) not null,
	estado varchar (150) not null,
	complemento varchar(150),
	Latitude varchar (50) not null,
	longitude varchar (50) not null
)
create table farmacias (
	id serial primary key,
	razaoSocial varchar(150)not null,
	cnpj varchar(18)not null,
	nomeFantasia varchar(150)not null,
	email varchar(100)not null,
	telefone varchar (16),
	celular varchar (16) not null,
	fk_id_endereco integer not null,
	foreign key (fk_id_endereco)
		references endereco (id)
)

create table medicamento(
	id serial primary key,
	nome varchar(150)not null,
	laboratorio varchar(150)not null,
	dosagem varchar(50)not null,
	descricao varchar(150),
	preco varchar (150) not null,
	tipo varchar (150) not null	
)





