CREATE TABLE "user" (
	id serial PRIMARY KEY,
	registration_date date NOT NULL DEFAULT CURRENT_TIMESTAMP,
	password varchar(255) NOT NULL,
	login varchar(255) NOT NULL UNIQUE
);

CREATE TABLE dataset (
	id serial PRIMARY KEY,
	title varchar(255) NOT NULL,
	filename varchar(255) NOT NULL,
	created_date date NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE model(
	id serial PRIMARY KEY,
	title varchar(255) NOT NULL,
	dataset_id int NOT NULL REFERENCES dataset(id) ON DELETE CASCADE,
	created_date date NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE key(
	id serial PRIMARY KEY,
	name varchar(255) NOT NULL,
	key_str varchar(255) NOT NULL,
	created_date date NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE queue_task(
    id serial PRIMARY KEY,
    completed_learn boolean NOT NULL,
    model_id int REFERENCES model(id) ON DELETE CASCADE,
    created_date date NOT NULL DEFAULT CURRENT_TIMESTAMP
)
