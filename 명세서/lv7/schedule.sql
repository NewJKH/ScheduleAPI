CREATE TABLE members
(
    member_id   BIGINT      NOT NULL AUTO_INCREMENT,
    member_name VARCHAR(20) NOT NULL,
    password    VARCHAR(20) NOT NULL,
    email       VARCHAR(255),
    created_at  DATETIME    NOT NULL,
    modified_at DATETIME    NOT NULL,
    PRIMARY KEY (member_id)
);

CREATE TABLE schedules
(
    schedule_id BIGINT      NOT NULL AUTO_INCREMENT,
    member_id   VARCHAR(20) NOT NULL,
    title       VARCHAR(20) NOT NULL,
    content     TEXT        NOT NULL,
    created_at  DATETIME    NOT NULL,
    modified_at DATETIME    NOT NULL,
    PRIMARY KEY (schedule_id),
    FOREIGN KEY (member_id) REFERENCES members (member_id)
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
    FOREIGN KEY (member_id) REFERENCES members (member_id),
    FOREIGN KEY (schedule_id) REFERENCES schedules (schedule_id)
);

