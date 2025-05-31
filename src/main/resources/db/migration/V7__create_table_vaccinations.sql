CREATE TABLE vaccinations (
     id BIGSERIAL PRIMARY KEY,
     vaccination_type VARCHAR(100),
     start_weeks INTEGER,
     end_weeks INTEGER,
     revactination_weeks VARCHAR
);