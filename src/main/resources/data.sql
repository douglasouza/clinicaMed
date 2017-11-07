-- Administrador
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario, us_ativado) VALUES ('admin', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'ADMINISTRADOR', true);
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario, us_ativado) VALUES ('dsouza', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'ADMINISTRADOR', false);
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario, us_ativado) VALUES ('apostingher', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'ADMINISTRADOR', false);

-- Medicamentos
INSERT INTO medicamento (me_nome_generico, me_nome_fabrica, me_fabricante) VALUES ('Paracetamol', 'Paracetamol', 'FARMACÊUTICA LTDA');
INSERT INTO medicamento (me_nome_generico, me_nome_fabrica, me_fabricante) VALUES ('Dipirona', 'Dipirona Monoidratada', 'FARMACÊUTICA LTDA');

-- Médicos
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario, us_ativado) VALUES ('jsantos', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'MEDICO', false);
INSERT INTO medico (md_nome, md_crm, md_especialidade, us_id)
    VALUES ('João da Silva Costa Santos', '5896', 'CLINICO_GERAL', (SELECT us_id FROM usuario WHERE us_login = 'jsantos'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario, us_ativado) VALUES ('ibasso', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'MEDICO', false);
INSERT INTO medico (md_nome, md_crm, md_especialidade, us_id)
    VALUES ('Igor Fernando Basso', '5596', 'CLINICO_GERAL', (SELECT us_id FROM usuario WHERE us_login = 'ibasso'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario, us_ativado) VALUES ('ssantos', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'MEDICO', false);
INSERT INTO medico (md_nome, md_crm, md_especialidade, us_id)
    VALUES ('Susy Ataide dos Santos', '6605', 'CLINICO_GERAL', (SELECT us_id FROM usuario WHERE us_login = 'ssantos'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario, us_ativado) VALUES ('talbring', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'MEDICO', false);
INSERT INTO medico (md_nome, md_crm, md_especialidade, us_id)
    VALUES ('Taciana Dieminger Albring', '5891', 'CLINICO_GERAL', (SELECT us_id FROM usuario WHERE us_login = 'talbring'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario, us_ativado) VALUES ('lpassos', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'MEDICO', false);
INSERT INTO medico (md_nome, md_crm, md_especialidade, us_id)
    VALUES ('Luisy Caroline Gomes Passos', '5234', 'CLINICO_GERAL', (SELECT us_id FROM usuario WHERE us_login = 'lpassos'));

-- Pacientes
INSERT INTO paciente (pa_nome, pa_cpf, pa_sexo) VALUES ('Amanda Cristina Venanci', '28515743370', 'FEMININO');
INSERT INTO paciente (pa_nome, pa_cpf, pa_sexo) VALUES ('Diogo Fernando de Almeida Ronnau', '04836438748', 'MASCULINO');

-- Recepcionistas
INSERT INTO usuario (us_login, us_senha, us_tipo_usuario, us_ativado) VALUES ('wbarros', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'RECEPCIONISTA', false);
INSERT INTO recepcionista (re_nome, re_cpf, us_id) VALUES ('Willian dos Santos de Barros', '51386597821', (SELECT us_id FROM usuario WHERE us_login = 'wbarros'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario, us_ativado) VALUES ('bcarlesso', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'RECEPCIONISTA', false);
INSERT INTO recepcionista (re_nome, re_cpf, us_id) VALUES ('Bruno Sangoi Carlesso', '39311580805', (SELECT us_id FROM usuario WHERE us_login = 'bcarlesso'));

INSERT INTO usuario (us_login, us_senha, us_tipo_usuario, us_ativado) VALUES ('garaujo', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'RECEPCIONISTA', false);
INSERT INTO recepcionista (re_nome, re_cpf, us_id) VALUES ('Giovana Oliveira de Araújo', '89615159778', (SELECT us_id FROM usuario WHERE us_login = 'garaujo'));