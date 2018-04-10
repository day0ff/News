INSERT INTO roles(role)
VALUES ('USER');
INSERT INTO roles(role)
VALUES ('EDITOR');
INSERT INTO roles(role)
VALUES ('ADMIN');

INSERT INTO users(user_name,password,enabled)
VALUES ('denis','1234', true);
INSERT INTO users(user_name,password,enabled)
VALUES ('andrej','1234', true);
INSERT INTO users(user_name,password,enabled)
VALUES ('michail','1234', true);

INSERT INTO user_roles (user_id , role_id)
VALUES ('1', '1');
INSERT INTO user_roles (user_id , role_id)
VALUES ('1', '2');
INSERT INTO user_roles (user_id , role_id)
VALUES ('1', '3');
INSERT INTO user_roles (user_id , role_id)
VALUES ('2', '1');
INSERT INTO user_roles (user_id , role_id)
VALUES ('2', '2');
INSERT INTO user_roles (user_id , role_id)
VALUES ('3', '1');
	