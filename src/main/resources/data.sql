-- Administrador
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('admin', 'senha123', 'ADMINISTRADOR');

-- Médicos
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('jsantos', 'senha123', 'MEDICO');
INSERT INTO medico (md_nome, md_crm, md_especialidade, us_id)
    VALUES ('João da Silva Costa Santos', '5896', 'CLINICO_GERAL', (SELECT us_id FROM usuario WHERE us_login = 'jsantos'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('ibasso', 'senha123', 'MEDICO');
INSERT INTO medico (md_nome, md_crm, md_especialidade, us_id)
    VALUES ('Igor Fernando Basso', '5596', 'CLINICO_GERAL', (SELECT us_id FROM usuario WHERE us_login = 'ibasso'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('ssantos', 'senha123', 'MEDICO');
INSERT INTO medico (md_nome, md_crm, md_especialidade, us_id)
    VALUES ('Susy Ataide dos Santos', '6605', 'CLINICO_GERAL', (SELECT us_id FROM usuario WHERE us_login = 'ssantos'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('talbring', 'senha123', 'MEDICO');
INSERT INTO medico (md_nome, md_crm, md_especialidade, us_id)
    VALUES ('Taciana Dieminger Albring', '5891', 'CLINICO_GERAL', (SELECT us_id FROM usuario WHERE us_login = 'talbring'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('lpassos', 'senha123', 'MEDICO');
INSERT INTO medico (md_nome, md_crm, md_especialidade, us_id)
    VALUES ('Luisy Caroline Gomes Passos', '5234', 'CLINICO_GERAL', (SELECT us_id FROM usuario WHERE us_login = 'lpassos'));

-- Recepcionistas
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros', 'senha123', 'RECEPCIONISTA');
INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('bcarlesso', 'senha123', 'RECEPCIONISTA');
INSERT INTO recepcionista (re_nome, us_id) VALUES ('Bruno Sangoi Carlesso', (SELECT us_id FROM usuario WHERE us_login = 'bcarlesso'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('garaujo', 'senha123', 'RECEPCIONISTA');
INSERT INTO recepcionista (re_nome, us_id) VALUES ('Giovana Oliveira de Araújo', (SELECT us_id FROM usuario WHERE us_login = 'garaujo'));