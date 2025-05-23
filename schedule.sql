CREATE TABLE members
(
    member_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    password    VARCHAR(100) NOT NULL,
    member_name VARCHAR(20)  NOT NULL,
    email       VARCHAR(255),
    create_at   TIMESTAMP,
    modified_at TIMESTAMP
);

CREATE TABLE schedules
(
    schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_name VARCHAR(20) NOT NULL,
    title       VARCHAR(20) NOT NULL,
    content     LONGTEXT    NOT NULL,
    create_at   TIMESTAMP,
    modified_at TIMESTAMP
);

CREATE TABLE comments
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    schedule_id BIGINT NOT NULL,
    member_id   BIGINT NOT NULL,
    message     TEXT   NOT NULL,
    create_at   TIMESTAMP,
    modified_at TIMESTAMP,
    CONSTRAINT fk_comment_schedule FOREIGN KEY (schedule_id) REFERENCES schedules (schedule_id),
    CONSTRAINT fk_comment_member FOREIGN KEY (member_id) REFERENCES members (member_id)
);
