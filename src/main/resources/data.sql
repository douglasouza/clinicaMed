INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('admin', 'senha123', 'ADMINISTRADOR');
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('jsantos', 'senha123', 'MEDICO');
INSERT INTO medico (md_nome, md_crm, md_especialidade, us_id) VALUES ('João da Silva Costa Santos', '5896', 'CLINICO_GERAL', (SELECT us_id FROM usuario WHERE us_login = 'jsantos'));