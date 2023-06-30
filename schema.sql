SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS ranking;
DROP TABLE IF EXISTS result;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE questions
(
	id int NOT NULL AUTO_INCREMENT,
	sentence text NOT NULL,
	genre varchar(64) NOT NULL,
	correct text NOT NULL,
	choice2 text NOT NULL,
	choice3 text NOT NULL,
	choice4 text NOT NULL,
	imagec mediumblob,
	image2 mediumblob,
	image3 mediumblob,
	image4 mediumblob,
	PRIMARY KEY (id)
);


CREATE TABLE ranking
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	correctValue int NOT NULL,
	questionValue int NOT NULL,
	time bigint NOT NULL,
	created_at timestamp NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE result
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	score int NOT NULL,
	created_at timestamp NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE users
(
	id int NOT NULL AUTO_INCREMENT,
	email varchar(64) NOT NULL,
	name varchar(64) NOT NULL,
	password varchar(256) NOT NULL,
	is_admin boolean NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (email),
	UNIQUE (name)
);



/* Create Foreign Keys */

ALTER TABLE ranking
	ADD FOREIGN KEY (user_id)
	REFERENCES users (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE result
	ADD FOREIGN KEY (user_id)
	REFERENCES users (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



