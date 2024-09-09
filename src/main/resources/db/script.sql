Create TABLE Users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE Members (
    PRIMARY KEY (user_id),
) INHERITS (Users);

CREATE TABLE Admins (
    PRIMARY KEY (user_id),
) INHERITS (Users);

CREATE TABLE Managers (
    PRIMARY KEY (user_id),
) INHERITS (Users);

