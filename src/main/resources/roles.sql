INSERT INTO role (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO role (role_name) VALUES ('ROLE_USER');
INSERT INTO role (role_name) VALUES ('ROLE_VISITOR');

INSERT INTO account (id, login, password, email, is_enabled) VALUES (1, 'admin', 'admin', 'admin@example.com', 1);
INSERT INTO account (id, login, password, email, is_enabled) VALUES (2, 'user', 'user', 'user@example.com', 1);
INSERT INTO account (id, login, password, email, is_enabled) VALUES (3, 'patrick', 'pass', 'patrick@example.com', 1);

INSERT INTO account_role (account_id, role_id) VALUES (1, 9);
INSERT INTO account_role (account_id, role_id) VALUES (2, 10);
INSERT INTO account_role (account_id, role_id) VALUES (3,11);


