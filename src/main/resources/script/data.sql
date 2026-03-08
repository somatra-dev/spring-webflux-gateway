CREATE SEQUENCE IF NOT EXISTS api_route_id_seq START WITH 1;

CREATE TABLE IF NOT EXISTS api_routes(
    id BIGINT PRIMARY KEY DEFAULT nextval ('api_route_id_seq'),
    uri VARCHAR(255) NOT NULL,
    path VARCHAR(255) NOT NULL,
    method VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    group_code VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,

    -- match Auditable field names (camelCase → snake_case)
    created_by        VARCHAR(255),
    created_date      TIMESTAMP,
    last_modified_by  VARCHAR(255),
    last_modified_date TIMESTAMP
);