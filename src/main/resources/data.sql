-- Sequence iniciar a partir do 6. 
--ALTER TABLE USUARIO ALTER COLUMN user_id RESTART WITH 6;

-- Inserindo alguns dados na tabela User
--INSERT INTO USUARIO (user_id, user_password, user_name, user_birth, user_email, user_accessibility) 
--VALUES 
--(1, 'senha123', 'Carlos Silva', '1990-05-15', 'carlos.silva@email.com', 'S'),
--(2, '1234abcd', 'Maria Oliveira', '1985-08-20', 'maria.oliveira@email.com', 'N'),
--(3, 'mypassword', 'João Pereira', '2000-11-10', 'joao.pereira@email.com', 'S'),
--(4, 'qwerty!2020', 'Ana Costa', '1995-02-12', 'ana.costa@email.com', 'S'),
--(5, 'senhaSegura!', 'Lucas Almeida', '1992-09-25', 'lucas.almeida@email.com', 'N');

-- Crianda a tabela Usuario
CREATE TABLE USUARIO (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_password VARCHAR(250) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    user_birth DATE NOT NULL,
    user_email VARCHAR(50) NOT NULL,
    user_icon BLOB NOT NULL,
    user_bio VARCHAR(250) NULL,
    user_accessibility VARCHAR(1) NOT NULL,
    CONSTRAINT chk_user_accessibility CHECK (user_accessibility IN ('S', 'N'))
);

CREATE TABLE ACESSO (
    acess_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

-- Criando a tabela de junção 'USUARIOS_ACESSOS' para o relacionamento many-to-many
CREATE TABLE USUARIOS_ACESSOS (
    user_id INT, 
    acess_id BIGINT,
    PRIMARY KEY (user_id, acess_id),
    FOREIGN KEY (user_id) REFERENCES USUARIO(user_id) ON DELETE CASCADE,
    FOREIGN KEY (acess_id) REFERENCES ACESSO(acess_id) ON DELETE CASCADE
);

CREATE TABLE GAMES (
    game_id INT NOT NULL AUTO_INCREMENT,
    game_name VARCHAR(100) NOT NULL,
    game_release INT NOT NULL,
    game_plataform VARCHAR(50) NOT NULL,
    game_synopsis CLOB NOT NULL,
    game_image BLOB NOT NULL,
    is_adult BOOLEAN NOT NULL,
    is_true BOOLEAN NOT NULL,
    PRIMARY KEY (game_id)
);


CREATE TABLE CATEGORY (
    category_id INT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL,
    is_adult BOOLEAN NOT NULL,
    PRIMARY KEY (category_id)
);


CREATE TABLE GAME_CATEGORY (
    category_id INT NOT NULL,
    game_id INT NOT NULL,
    PRIMARY KEY (category_id, game_id),
    FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id) 
        ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (game_id) REFERENCES GAMES(game_id) 
        ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE USUARIO_GAMES (
    user_id INT NOT NULL,
    game_id INT NOT NULL,
    rating DECIMAL(3,1) CHECK (rating >= 0.0 AND rating <= 5.0),
    owned BOOLEAN,
    comment CLOB,
    PRIMARY KEY (user_id, game_id),
    FOREIGN KEY (user_id) REFERENCES USUARIO(user_id) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (game_id) REFERENCES GAMES(game_id) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO CATEGORY (category_name, is_adult) VALUES ('RPG', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Aventura', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Ação', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Estratégia', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Simulação', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Plataforma', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Mundo Aberto', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Terror', true);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Luta', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Corrida', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('FPS', true);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Multiplayer', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Indie', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Simulador de Vida', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Shooter', true);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('RPG de Ação', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Puzzle', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('Hack and Slash', false);
INSERT INTO CATEGORY (category_name, is_adult) VALUES ('MOBA', false);



