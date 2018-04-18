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

CREATE TABLE persons(
	id  BIGSERIAL PRIMARY KEY,
	user_id BIGINT REFERENCES users (id),
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	screen_name VARCHAR(100) NOT NULL,
	image VARCHAR(255) DEFAULT 'hamster.png'
);

CREATE TABLE tags(
	id  BIGSERIAL PRIMARY KEY,
	tag VARCHAR(255) NOT NULL
);

CREATE TABLE news(
	id  BIGSERIAL PRIMARY KEY,
	author BIGINT REFERENCES persons (id),
	title TEXT NOT NULL,
	article TEXT NOT NULL,
	post TEXT NOT NULL,
	image VARCHAR(255) DEFAULT 'news_paper.png',
	publication_date DATE NOT NULL,
	views INT DEFAULT 0,
	likes INT DEFAULT 0,
	published BOOLEAN DEFAULT FALSE
);

CREATE TABLE news_tags(
	id  BIGSERIAL PRIMARY KEY,
	news_id BIGINT REFERENCES news (id),
  	tag_id BIGINT REFERENCES tags (id)
);

CREATE TABLE comments(
	id  BIGSERIAL PRIMARY KEY,
	person_id BIGINT REFERENCES persons (id),
  news_id BIGINT REFERENCES news (id),
	comment TEXT NOT NULL
);