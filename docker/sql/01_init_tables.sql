CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL       NOT NULL PRIMARY KEY,
    email      VARCHAR(256) NOT NULL,
    password   VARCHAR(256) NOT NULL,
    first_name VARCHAR(256) NOT NULL,
    last_name  VARCHAR(256) NOT NULL,

    CONSTRAINT uq_users_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS applications
(
    id            SERIAL      NOT NULL PRIMARY KEY,
    name          VARCHAR(64) NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description   TEXT        NOT NULL,

    user_id       INT         NOT NULL,

    CONSTRAINT fk_database_config_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT uq_applications_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS database_configs
(
    id             SERIAL       NOT NULL PRIMARY KEY,
    url            VARCHAR(512) NOT NULL,
    database_type  VARCHAR(32)  NOT NULL,
    data_query     TEXT         NOT NULL,
    credentials    TEXT         NOT NULL,

    application_id INT          NOT NULL,

    CONSTRAINT fk_database_config_application_id FOREIGN KEY (application_id) REFERENCES applications (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reports
(
    id              SERIAL       NOT NULL PRIMARY KEY,
    name            VARCHAR(128) NOT NULL,
    data_studio_url VARCHAR(256) NOT NULL,

    application_id  INT          NOT NULL,

    CONSTRAINT fk_reports_application_id FOREIGN KEY (application_id) REFERENCES applications (id) ON DELETE CASCADE
);
