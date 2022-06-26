CREATE TABLE `car`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `car_model`   varchar(50) DEFAULT NULL,
    `in_stock`    int(11)     DEFAULT NULL,
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `car_rental`.`car`(`id`, `car_model`, `in_stock`, `create_time`, `update_time`)
VALUES (1, 'Toyota Camry', 2, now(), now());
INSERT INTO `car_rental`.`car`(`id`, `car_model`, `in_stock`, `create_time`, `update_time`)
VALUES (2, 'BMW 650', 2, now(), now());

CREATE TABLE `rent_info`
(
    `id`                 int(11) NOT NULL AUTO_INCREMENT,
    `user_name`          varchar(50) DEFAULT NULL,
    `car_id`             int(11)     DEFAULT NULL,
    `car_model`          varchar(50) DEFAULT NULL,
    `num`                int(11)     DEFAULT NULL,
    `state`              int(11)     DEFAULT NULL,
    `start_time`         datetime    DEFAULT NULL,
    `end_time`           datetime    DEFAULT NULL,
    `actual_return_time` datetime    DEFAULT NULL,
    `create_time`        datetime    DEFAULT CURRENT_TIMESTAMP,
    `update_time`        datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;