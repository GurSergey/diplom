CREATE TABLE "user" (
	id serial PRIMARY KEY,
	registration_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	password varchar(255) NOT NULL,
	login varchar(255) NOT NULL UNIQUE
);

CREATE TABLE dataset (
	id serial PRIMARY KEY,
	title varchar(255) NOT NULL,
	filename varchar(255) NOT NULL,
	checking boolean DEFAULT false ,
	created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE model(
	id serial PRIMARY KEY,
	title varchar(255) NOT NULL,
	test_accuracy real DEFAULT 0,
	dataset_id int NOT NULL REFERENCES dataset(id) ON DELETE CASCADE,
	created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE key(
	id serial PRIMARY KEY,
	name varchar(255) NOT NULL,
	key_str varchar(255) NOT NULL,
	created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE type_task(
    id serial PRIMARY KEY,
    name varchar (255) NOT NULL
);

INSERT INTO type_task VALUES(1, 'check_dataset'),
(2, 'merge_dataset'),
(3, 'ml_learning'),
(4, 'user_file'),
(5, 'admin_file');


CREATE TABLE check_dataset(
    id serial PRIMARY KEY,
    completed_task boolean DEFAULT false,
    dataset_id int REFERENCES dataset(id) ON DELETE CASCADE,
    created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE merge_dataset(
    id serial PRIMARY KEY,
    completed_task boolean DEFAULT false,
    dataset_id int REFERENCES dataset(id) ON DELETE CASCADE,
    source_datasets varchar(255),
    created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE queue_task_ml(
    id serial PRIMARY KEY,
    progress int DEFAULT 0,
    n_workers int DEFAULT 1,
    completed_task boolean DEFAULT false,
    model_id int REFERENCES model(id) ON DELETE CASCADE,
    created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
)