/* LV 0 테이블 생성하기 */
CREATE TABLE `schedule`.`member` (
    `member_id` INT NOT NULL AUTO_INCREMENT ,
    `name` VARCHAR(20) NOT NULL ,
    `password` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`member_id`)
);

CREATE TABLE `schedule`.`schedules` (
    `schedule_id` INT NOT NULL AUTO_INCREMENT ,
    `member_id` INT NOT NULL ,
    `title` VARCHAR(20) NOT NULL ,
    `content` TEXT NOT NULL ,
    `created_at` DATETIME NOT NULL,
    `modified_at` DATETIME NOT NULL,
    PRIMARY KEY (`schedule_id`),
    FOREIGN KEY (`member_id`) REFERENCES `member`(`member_id`)
);