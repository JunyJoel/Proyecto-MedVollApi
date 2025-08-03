create table pacientes(

    id bigint not null auto_increment,
    status tinyint not null,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento varchar(12) not null unique,
    calle varchar(100) not null,
    barrio varchar(100) not null,
    codigo_postal varchar(100) not null,
    complemento varchar(100) not null,
    numero varchar(20),
    estado varchar(100) not null,
    ciudad varchar(100) not null,

    primary key(id)
);