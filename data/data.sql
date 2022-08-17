DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS permissao CASCADE;
DROP TABLE IF EXISTS galeria CASCADE;
DROP TABLE IF EXISTS postagem CASCADE;
DROP TABLE IF EXISTS amizade CASCADE;
DROP TABLE IF EXISTS curtida CASCADE;
DROP TABLE IF EXISTS comentario CASCADE;

CREATE TABLE usuario (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	nome VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	senha VARCHAR(100) NOT NULL,
	data_nascimento DATE NOT NULL,
	apelido VARCHAR(50),
	imagem_perfil VARCHAR,
	ativo BOOLEAN NOT NULL
);
ALTER TABLE usuario ADD CONSTRAINT pk_usuario PRIMARY KEY (id);
ALTER TABLE usuario ADD CONSTRAINT uk_usuario_email UNIQUE (email);

CREATE TABLE permissao (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	funcao VARCHAR(100) NOT NULL,
	usuario_id BIGINT NOT NULL
);
ALTER TABLE permissao ADD CONSTRAINT pk_permissao PRIMARY KEY (id);
ALTER TABLE permissao ADD CONSTRAINT uk_funcao_usuario UNIQUE (funcao, usuario_id);
ALTER TABLE permissao ADD CONSTRAINT fk_permissao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;

CREATE TABLE galeria (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	usuario_id BIGINT NOT NULL,
	imagem_salva VARCHAR NOT NULL
);
ALTER TABLE galeria ADD CONSTRAINT pk_galeria PRIMARY KEY (id);
ALTER TABLE galeria ADD CONSTRAINT fk_galeria_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;

CREATE TABLE postagem (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	usuario_id BIGINT NOT NULL,
	data_postagem DATE NOT NULL,
	imagem_postagem VARCHAR,
	texto_postagem VARCHAR(100),
	restricao_postagem VARCHAR(50) NOT NULL
);
ALTER TABLE postagem ADD CONSTRAINT pk_postagem PRIMARY KEY (id);
ALTER TABLE postagem ADD CONSTRAINT ck_postagem_restricao_postagem CHECK (restricao_postagem IN ('PUBLICA', 'PRIVADA'));
ALTER TABLE postagem ADD CONSTRAINT fk_postagem_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;

CREATE TABLE amizade (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	usuario_id BIGINT NOT NULL,
	amigo_id BIGINT NOT NULL,
	status_amizade VARCHAR(10) NOT NULL
);
ALTER TABLE amizade ADD CONSTRAINT pk_amizade PRIMARY KEY (id);
ALTER TABLE amizade ADD CONSTRAINT uk_amizade UNIQUE (usuario_id, amigo_id);
ALTER TABLE amizade ADD CONSTRAINT ck_status_amizade CHECK (status_amizade IN ('AMIGO', 'PENDENTE'));
ALTER TABLE amizade ADD CONSTRAINT fk_amizade_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;

CREATE TABLE curtida (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	postagem_id BIGINT NOT NULL,
	usuario_id BIGINT NOT NULL
);
ALTER TABLE curtida ADD CONSTRAINT pk_curtida PRIMARY KEY (id);
ALTER TABLE curtida ADD CONSTRAINT fk_curtida_postagem FOREIGN KEY (postagem_id) REFERENCES postagem;
ALTER TABLE curtida ADD CONSTRAINT fk_curtida_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;

CREATE TABLE comentario (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	postagem_id BIGINT NOT NULL,
	usuario_id BIGINT NOT NULL,
	comentario VARCHAR(100) NOT NULL
);
ALTER TABLE comentario ADD CONSTRAINT pk_comentario PRIMARY KEY (id);
ALTER TABLE comentario ADD CONSTRAINT fk_comentario_postagem FOREIGN KEY (postagem_id) REFERENCES postagem;
ALTER TABLE comentario ADD CONSTRAINT fk_comentario_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;

/*INSERTS*/

insert into usuario (nome, email, senha, data_nascimento, apelido, imagem_perfil, ativo)
	values ('Bumblebee', 'Bumblebee@gmail.com.br', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', '2007/01/01', 'Bee', 'https://observatoriodocinema.uol.com.br/wp-content/uploads/2021/06/bumblebee-movie.jpg', true);
insert into usuario (nome, email, senha, data_nascimento, apelido, imagem_perfil, ativo)
	values ('Optimus Prime', 'Optimusprime@gmail.com.br', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', '1950/01/01', 'Prime', 'http://pm1.narvii.com/7141/fc84be2c79bf9ddac6445e3d05a1e3ac8bfb933er1-682-1010v2_uhq.jpg', true);
insert into usuario (nome, email, senha, data_nascimento, imagem_perfil, ativo)
	values ('Megatron', 'Megatron@gmail.com.br', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', '1940/01/01', 'https://i.pinimg.com/550x/c7/a1/90/c7a190b4b4e156d8bba4869395bc61a8.jpg', true);
insert into usuario (nome, email, senha, data_nascimento, apelido, imagem_perfil, ativo)
	values ('Jazz', 'Jazz@gmail.com.br', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', '2008/01/01', 'Jazz', 'https://i.pinimg.com/736x/c7/69/51/c76951ffbf0b770a41182823b3f9f0b3.jpg', true);

insert into permissao (funcao, usuario_id) values ('USUARIO', 1);
insert into permissao (funcao, usuario_id) values ('USUARIO', 2);
insert into permissao (funcao, usuario_id) values ('USUARIO', 3);
insert into permissao (funcao, usuario_id) values ('USUARIO', 4);

insert into galeria (usuario_id, imagem_salva) values (1, 'https://br.web.img3.acsta.net/newsv7/16/12/05/19/40/418489.jpg');
insert into galeria (usuario_id, imagem_salva) values (1, 'https://poltronanerd.com.br/wp-content/uploads/2018/12/dfdfdg.jpg');

insert into postagem (usuario_id, data_postagem, imagem_postagem, texto_postagem, restricao_postagem)
	values (1, '2022/05/5','https://i2.wp.com/sitedosgeeks.com/wp-content/uploads/2018/11/transformers-the-last-knight-8.jpg?fit=1000%2C562&ssl=1', 'esfriando o motor kkk', 'PUBLICA');
insert into postagem (usuario_id, data_postagem, imagem_postagem, texto_postagem, restricao_postagem)
	values (3, '2013/08/10','https://i.ytimg.com/vi/fNqq9dRcEKQ/maxresdefault.jpg', 'Só brincadeira kkk', 'PRIVADA');
insert into postagem (usuario_id, data_postagem, imagem_postagem, texto_postagem, restricao_postagem)
	values (3, '2013/08/10','https://i.ytimg.com/vi/fNqq9dRcEKQ/maxresdefault.jpg', 'papo sério com o Optimos Prime', 'PUBLICA');

insert into amizade (usuario_id, amigo_id, status_amizade) values (1, 2, 'AMIGO');
insert into amizade (usuario_id, amigo_id, status_amizade) values (3, 1, 'PENDENTE');
insert into amizade (usuario_id, amigo_id, status_amizade) values (4, 3, 'PENDENTE');
insert into amizade (usuario_id, amigo_id, status_amizade) values (4, 1, 'PENDENTE');

insert into curtida (postagem_id, usuario_id) values (1, 2);
insert into curtida (postagem_id, usuario_id) values (1, 1);
insert into curtida (postagem_id, usuario_id) values (3, 1);
insert into curtida (postagem_id, usuario_id) values (2, 1);

insert into comentario (postagem_id, usuario_id, comentario) values (1, 2, 'Que vidão kk');
insert into comentario (postagem_id, usuario_id, comentario) values (1, 4, 'Tambem to precisando esfriar o motor');
