DROP TABLE IF EXISTS `events`;
CREATE TABLE events
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    name           VARCHAR(255) NOT NULL,
    location       VARCHAR(255) NOT NULL,
    slug           VARCHAR(500) NULL,
    `img_Url`      VARCHAR(255) NULL,
    is_active      tinyint(1) Null default 1,
    `desc`         VARCHAR(255) NULL,
    `created_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    `updated_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    `start_time`   datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    CONSTRAINT pk_events PRIMARY KEY (id)
);