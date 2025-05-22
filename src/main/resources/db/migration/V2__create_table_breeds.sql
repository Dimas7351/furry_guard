CREATE TABLE breeds (
       id BIGSERIAL PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       size VARCHAR(50) NOT NULL,
--        avg_activity INT NOT NULL CHECK (avg_activity BETWEEN 1 AND 10)
       avg_activity VARCHAR(50) NOT NULL
);
