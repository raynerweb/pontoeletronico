INSERT INTO usuario (nome, matricula, senha, perfil, status) VALUES ('root', 'root', '123456', 'ROOT', 'ATIVO');
INSERT INTO usuario (nome, matricula, senha, perfil, status) VALUES ('super', '123456', '123456', 'SUPERVISOR', 'ATIVO');
INSERT INTO usuario (nome, matricula, senha, perfil, status) VALUES ('operador', '456789', '123456', 'OPERADOR', 'ATIVO');
INSERT INTO usuario (nome, matricula, senha, perfil, status) VALUES ('inativo', '789123', '123456', 'SUPERVISOR', 'INATIVO');

INSERT INTO ponto (usuario_id, data_registro, entrada_primeiro_turno, permitido_hora_extra) VALUES (1, '2015-09-23', '2015-09-23 08:01:01', 0);