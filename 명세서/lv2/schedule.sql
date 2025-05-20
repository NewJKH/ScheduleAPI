/* LV 2 테이블 생성하기 */
CREATE TABLE `schedule`.`schedules`
(
    `member_name` VARCHAR(20) NOT NULL,
    `title`       VARCHAR(20) NOT NULL,
    `content`     TEXT        NOT NULL,
    `created_at`  DATETIME    NOT NULL,
    `modified_at` DATETIME    NOT NULL
);

CREATE TABLE `schedule`.`members`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT,
    `member_name` VARCHAR(20) NOT NULL,
    `password`    VARCHAR(20) NOT NULL,
    `email`       TEXT,
    `created_at`  DATETIME    NOT NULL,
    `modified_at` DATETIME    NOT NULL,
    PRIMARY KEY (`id`)
);