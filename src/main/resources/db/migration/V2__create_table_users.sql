CREATE TABLE users(
    id       BIGSERIAL PRIMARY KEY,
    metro_id BIGSERIAL,
    first_name          VARCHAR(100) NOT NULL,
    last_name          VARCHAR(100) NOT NULL,
    surname          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password          VARCHAR(100) NOT NULL,
    date_of_birth DATE,
    FOREIGN KEY (metro_id) REFERENCES metro(id) ON DELETE CASCADE
);

