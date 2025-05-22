CREATE TABLE pets (
     id BIGSERIAL PRIMARY KEY,
     owner_id BIGSERIAL NOT NULL,
     breed_id BIGSERIAL NOT NULL,
     name VARCHAR(100) NOT NULL,
     gender VARCHAR(100),
     weight DECIMAL(5, 2),
     age int,
     activity_level INT CHECK (activity_level BETWEEN 1 AND 10),
     recommendations VARCHAR,
     FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE,
     FOREIGN KEY (breed_id) REFERENCES breeds(id) ON DELETE SET NULL
);