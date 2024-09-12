CREATE TYPE UserRole AS ENUM ('MEMBER', 'ADMIN', 'MANAGER');

Create TABLE Users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    encodedSalt VARCHAR(255) NOT NULL,
    role UserRole NOT NULL
);

CREATE TABLE Members (
    PRIMARY KEY (id)
) INHERITS (Users);

CREATE TABLE Admins (
    PRIMARY KEY (id)
) INHERITS (Users);

CREATE TABLE Managers (
    PRIMARY KEY (id)
) INHERITS (Users);
