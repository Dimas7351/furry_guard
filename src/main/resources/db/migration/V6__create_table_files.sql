CREATE TABLE files (
     id BIGSERIAL PRIMARY KEY,
     pet_id BIGSERIAL NOT NULL,
     file_name VARCHAR(100),
     file_type VARCHAR(20),
     create_date timestamptz,
     content VARCHAR,
     FOREIGN KEY (pet_id) REFERENCES pets(id) ON DELETE CASCADE
);