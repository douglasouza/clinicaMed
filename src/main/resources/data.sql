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











INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros0', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros0'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros1', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros1'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros2', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros2'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros3', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros3'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros4', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros4'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros5', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros5'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros6', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros6'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros7', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros7'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros8', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros8'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros9', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros9'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros10', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros10'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros11', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros11'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros12', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros12'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros13', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros13'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros14', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros14'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros15', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros15'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros16', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros16'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros17', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros17'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros18', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros18'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros19', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros19'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros20', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros20'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros21', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros21'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros22', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros22'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros23', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros23'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros24', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros24'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros25', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros25'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros26', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros26'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros27', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros27'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros28', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros28'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros29', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros29'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros30', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros30'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros31', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros31'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros32', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros32'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros33', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros33'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros34', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros34'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros35', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros35'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros36', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros36'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros37', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros37'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros38', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros38'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros39', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros39'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros40', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros40'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros41', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros41'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros42', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros42'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros43', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros43'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros44', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros44'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros45', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros45'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros46', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros46'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros47', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros47'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros48', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros48'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros49', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros49'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros50', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros50'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros51', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros51'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros52', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros52'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros53', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros53'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros54', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros54'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros55', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros55'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros56', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros56'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros57', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros57'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros58', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros58'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros59', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros59'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros60', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros60'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros61', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros61'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros62', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros62'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros63', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros63'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros64', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros64'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros65', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros65'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros66', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros66'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros67', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros67'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros68', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros68'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros69', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros69'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros70', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros70'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros71', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros71'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros72', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros72'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros73', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros73'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros74', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros74'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros75', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros75'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros76', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros76'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros77', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros77'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros78', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros78'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros79', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros79'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros80', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros80'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros81', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros81'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros82', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros82'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros83', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros83'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros84', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros84'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros85', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros85'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros86', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros86'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros87', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros87'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros88', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros88'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros89', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros89'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros90', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros90'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros91', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros91'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros92', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros92'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros93', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros93'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros94', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros94'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros95', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros95'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros96', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros96'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros97', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros97'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros98', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros98'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros99', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros99'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros100', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros100'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros101', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros101'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros102', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros102'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros103', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros103'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros104', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros104'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros105', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros105'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros106', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros106'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros107', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros107'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros108', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros108'));
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario) VALUES ('wbarros109', 'senha123', 'RECEPCIONISTA'); INSERT INTO recepcionista (re_nome, us_id) VALUES ('Willian dos Santos de Barros', (SELECT us_id FROM usuario WHERE us_login = 'wbarros109'));
