CREATE TABLE cities (
                        id BIGSERIAL PRIMARY KEY,
                        city_name VARCHAR(255) UNIQUE NOT NULL,
                        city_code VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE sensor (
                        id BIGSERIAL PRIMARY KEY,
                        sensor_name VARCHAR(255) UNIQUE NOT NULL,
                        latitude DECIMAL(9,6) NOT NULL,
                        longitude DECIMAL(9,6) NOT NULL,
                        city_id BIGINT NOT NULL ,
                        CONSTRAINT fk_sensor_to_city FOREIGN KEY (city_id) REFERENCES cities(id) ON DELETE CASCADE
);

CREATE TABLE air_quality(
                            id BIGSERIAL PRIMARY KEY,
                            sensor_id BIGINT NOT NULL,
                            temperature BIGINT NOT NULL,
                            humidity BIGINT NOT NULL,
                            air_pollution BIGINT NOT NULL,
                            air_pressure BIGINT NOT NULL,
                            noise BIGINT NOT NULL ,
                            created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
                            CONSTRAINT fk_air_to_sensor FOREIGN KEY (sensor_id) references sensor(id) ON DELETE CASCADE
);
