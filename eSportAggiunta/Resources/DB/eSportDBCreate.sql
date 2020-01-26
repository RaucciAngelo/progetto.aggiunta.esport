create schema esportdb;

use esportdb;

create table utente(
	username varchar(36) primary key,
    pwd varchar(36) not null, 
    nome	varchar(36),
    cognome	varchar(36),
    email varchar(128),
    piva char(11),
    telefono char(10)
);

use esportdb;

create table ruolo(
	usr varchar(36),
    permesso varchar(36),
    foreign key(usr) references utente(username)
		on update cascade
        on delete cascade
);

use esportdb;

create table indirizzo(
	codice int primary key auto_increment,
	usr varchar(36),
    citta varchar(36),
    via varchar(36),
    civico varchar(12),
    cap varchar(5),
    foreign key(usr) references utente(username)
		on update cascade
        on delete cascade
);

use esportdb;

create table metodopagamento(
	codice int primary key auto_increment,
	usr varchar(36),
    tipo varchar(36),
    numero varchar(36),
    foreign key(usr) references utente(username)
		on update cascade
        on delete cascade
);

use esportdb;

create table prodotto(
	codice char(3) primary key,
    nome varchar(36) not null,
    tipo varchar(36) not null,
    marca varchar(36),
    qt int,
    prezzo double,
    iva int,
    descrizione varchar(512)
);

use esportdb;

create table taglia(
	prodotto char(3),
    misura varchar(3),
	foreign key(prodotto) references prodotto(codice)
		on update cascade
        on delete cascade
);

use esportdb;

create table ordine(
	numero char(6) primary key,
    stato varchar(36),
    pagamento int not null,
    indirizzo int not null,
    totale double not null,
    sottomissione date not null,
    consegna date not null,
    usr varchar(36) not null ,
    foreign key(usr) references utente(username)
		on update cascade
        on delete cascade,
	foreign key(pagamento) references metodopagamento(codice)
		on update cascade
        on delete cascade,
	foreign key(indirizzo) references indirizzo(codice)
		on update cascade
        on delete cascade
);

use esportdb;

create table composizione(
	ordine char(6) not null,
    prodotto char(3),
    nomeprodotto varchar(36) not null,
    prezzoven double not null,
    ivaven int not null,
    qt int default 1,
    taglia varchar(3) not null,
    foreign key(ordine) references ordine(numero)
		on update cascade
        on delete cascade,
	foreign key(prodotto) references prodotto(codice)
		on update cascade
        on delete set null
);

use esportdb;

create table recensione(
	codice int primary key auto_increment,
    voto int not null,
    commento varchar(512) not null,
    usr varchar(36),
    prodotto char(3) not null,
    foreign key(usr) references utente(username)
		on update cascade
		on delete cascade,
	foreign key(prodotto) references prodotto(codice)
		on update cascade
        on delete cascade
);