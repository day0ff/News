INSERT INTO users(user_name,password,enabled)
VALUES ('denis','1234', true);
INSERT INTO users(user_name,password,enabled)
VALUES ('andrej','1234', true);
INSERT INTO users(user_name,password,enabled)
VALUES ('maxim','1234', true);

INSERT INTO user_roles (user_name, role)
VALUES ('denis', 'USER');
INSERT INTO user_roles (user_name, role)
VALUES ('denis', 'EDITOR');
INSERT INTO user_roles (user_name, role)
VALUES ('denis', 'ADMIN');
INSERT INTO user_roles (user_name, role)
VALUES ('andrej', 'USER');
INSERT INTO user_roles (user_name, role)
VALUES ('andrej', 'ADMIN');
INSERT INTO user_roles (user_name, role)
VALUES ('maxim', 'EDITOR');