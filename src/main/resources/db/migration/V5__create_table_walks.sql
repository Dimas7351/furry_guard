CREATE TABLE walks (
     id BIGSERIAL PRIMARY KEY,
     pet_id BIGSERIAL NOT NULL,
     date_time timestamptz,
     duration integer,
     FOREIGN KEY (pet_id) REFERENCES pets(id) ON DELETE CASCADE
);