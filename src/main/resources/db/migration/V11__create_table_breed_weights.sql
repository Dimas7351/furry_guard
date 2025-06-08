CREATE TABLE IF NOT EXISTS breed_weights (
    id BIGSERIAL PRIMARY KEY,
    breed_id BIGSERIAL NOT NULL,
    min_age_months INT NOT NULL,
    max_age_months INT NOT NULL,
    min_weight_kg FLOAT NOT NULL,
    max_weight_kg FLOAT NOT NULL,
    FOREIGN KEY (breed_id) REFERENCES breeds(id) ON DELETE SET NULL
);