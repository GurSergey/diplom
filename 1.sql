CREATE TABLE voter (
	id serial PRIMARY KEY,
	registration_date date NOT NULL,
	name varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	login varchar(255) NOT NULL,
	phone varchar(11) UNIQUE
);

CREATE TABLE poll(
	id serial PRIMARY KEY,
	title varchar(255) NOT NULL,
	visible boolean NOT NULL,
	date_to date NOT NULL,
	start_date date NOT NULL CHECK (date_to > start_date ),
	create_date date NOT NULL
);

CREATE TABLE question(
	id serial PRIMARY KEY,
	poll_id int NOT NULL REFERENCES poll(id) ON DELETE CASCADE,
	question varchar(255) NOT NULL,
	created_date date NOT NULL
);

CREATE TABLE variants(
	id serial PRIMARY KEY,
	question_id int NOT NULL REFERENCES question(id) ON DELETE CASCADE,
	text varchar(255) NOT NULL
);

CREATE TABLE answer(
	id serial PRIMARY KEY ,
	voter_id int NOT NULL REFERENCES voter(id) ON DELETE CASCADE,
	variant_id int NOT NULL REFERENCES variants(id) ON DELETE CASCADE,
	answer_date date NOT NULL
);


