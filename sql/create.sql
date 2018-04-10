/*DROP DATABASE IF EXISTS news;*/
/*CREATE DATABASE IF NOT EXISTS news;*/
CREATE TABLE roles(
	id BIGSERIAL PRIMARY KEY,
	role VARCHAR(45) NOT NULL UNIQUE
);
CREATE TABLE users(
	id BIGSERIAL PRIMARY KEY,
 	user_name VARCHAR(45) NOT NULL UNIQUE,
  	password VARCHAR(45) NOT NULL,
  	enabled BOOLEAN NOT NULL DEFAULT true
);
CREATE TABLE user_roles(
  	id BIGSERIAL PRIMARY KEY,
  	user_id BIGINT REFERENCES users (id),
  	role_id BIGINT REFERENCES roles (id),
	CONSTRAINT uni_user_role UNIQUE (user_id, role_id)
);