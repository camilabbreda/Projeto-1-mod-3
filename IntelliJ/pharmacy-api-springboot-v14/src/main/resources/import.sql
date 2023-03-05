-- Arquivo que contêm inserts para carga inicial de dados para a aplicação

-- Insert na tabela de roles
INSERT INTO public.role(role) VALUES ('ROLE_ADMINISTRADOR');
INSERT INTO public.role(role) VALUES ('ROLE_GERENTE');
INSERT INTO public.role(role) VALUES ('ROLE_COLABORADOR');

-- Insert na tabela de usuários
INSERT INTO public.usuario(login, senha) VALUES ('camilabbreda',  '$2a$10$pFrtqxDwOYw0rFq1VQtEx.pBgN6A8tUlVzfPnCs3Aior5NXmMes1m');
INSERT INTO public.usuario(login, senha) VALUES ('millinha16',  '$2a$10$pFrtqxDwOYw0rFq1VQtEx.pBgN6A8tUlVzfPnCs3Aior5NXmMes1m');
INSERT INTO public.usuario(login, senha) VALUES ('joana26',  '$2a$10$pFrtqxDwOYw0rFq1VQtEx.pBgN6A8tUlVzfPnCs3Aior5NXmMes1m');
INSERT INTO public.usuario(login, senha) VALUES ('liciana12',  '$2a$10$pFrtqxDwOYw0rFq1VQtEx.pBgN6A8tUlVzfPnCs3Aior5NXmMes1m');

-- Insert na tabela de usuarios_role (associa o(s) usuário(s) com suas permissões)
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (1, 1);
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (2, 2);
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (4, 2);
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (3, 3);
