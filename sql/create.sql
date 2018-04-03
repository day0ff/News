CREATE TABLE users(
  user_name VARCHAR(45) NOT NULL UNIQUE PRIMARY KEY,
  password VARCHAR(45) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT true
);
CREATE TABLE user_roles(
  	user_role_id SERIAL PRIMARY KEY,
  	user_name varchar(45) NOT NULL REFERENCES users (user_name),
  	role varchar(45) NOT NULL,
	CONSTRAINT uni_username_role UNIQUE (role, user_name)
);

