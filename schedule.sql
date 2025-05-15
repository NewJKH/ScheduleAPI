/* LV 0 테이블 생성하기 */
CREATE TABLE `schedule`.`schedules` (
    `member_name` VARCHAR(20) NOT NULL ,
    `title` VARCHAR(20) NOT NULL ,
    `content` TEXT NOT NULL ,
    `created_at` DATETIME NOT NULL,
    `modified_at` DATETIME NOT NULL
);