CREATE TABLE members
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    member_name VARCHAR(20) NOT NULL,
    password    VARCHAR(20) NOT NULL,
    email       VARCHAR(255),
    created_at  DATETIME    NOT NULL,
    modified_at DATETIME    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE schedules
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    member_name VARCHAR(20) NOT NULL,
    title       VARCHAR(20) NOT NULL,
    content     TEXT        NOT NULL,
    created_at  DATETIME    NOT NULL,
    modified_at DATETIME    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (member_name) REFERENCES members (member_name)
);

CREATE TABLE comments
(
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    member_id   BIGINT   NOT NULL,
    schedule_id BIGINT   NOT NULL,
    message     VARCHAR(255),
    created_at  DATETIME NOT NULL,
    modified_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES members (id),
    FOREIGN KEY (schedule_id) REFERENCES schedules (id)
);

