CREATE DATABASE todo_app;

\c todo_app;

CREATE TABLE todo(
    id SERIAL PRIMARY KEY,
    priority INT CHECK (priority > 0) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    deadline TIMESTAMP CHECK ( deadline > CURRENT_TIMESTAMP ),
    done boolean default false not  null
);