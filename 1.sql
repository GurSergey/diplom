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
	dataset_id int NOT NULL REFERENCES dataset(id),
	created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE key(
	id serial PRIMARY KEY,
	name varchar(255) NOT NULL,
	key_str varchar(255) NOT NULL,
	created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE check_dataset(
    id serial PRIMARY KEY,
    completed_task boolean DEFAULT false,
    normalize boolean DEFAULT false,
    in_work boolean DEFAULT false,
    is_correct boolean DEFAULT false,
    dataset_id int REFERENCES dataset(id) ON DELETE CASCADE,
    created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE merge_dataset(
    id serial PRIMARY KEY,
    completed_task boolean DEFAULT false,
    dataset_id int REFERENCES dataset(id) ON DELETE CASCADE,
    in_work boolean DEFAULT false,
    source_datasets varchar(255),
    created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE queue_task_admin_file(
    id serial PRIMARY KEY,
    title VARCHAR(255),
    filename VARCHAR(255),
    completed_task boolean DEFAULT false,
    progress int DEFAULT 0,
    in_work boolean DEFAULT false,
    model_id int REFERENCES model(id) ON DELETE CASCADE ,
    created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP)

CREATE TABLE queue_task_user_file(
    id serial PRIMARY KEY,
    title VARCHAR(255),
    filename VARCHAR(255),
    completed_task boolean DEFAULT false,
    progress int DEFAULT 0,
    in_work boolean DEFAULT false,
    user_id int REFERENCES "user"(id) ON DELETE CASCADE ,
    model_id int REFERENCES model(id) ON DELETE CASCADE ,
    created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP)

CREATE TABLE queue_task_ml(
    id serial PRIMARY KEY,
    progress int DEFAULT 0,
    n_workers int DEFAULT 1,
    in_work boolean DEFAULT false,
    completed_task boolean DEFAULT false,
    model_id int REFERENCES model(id) ON DELETE CASCADE,
    created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
)