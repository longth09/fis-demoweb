DROP TABLE IF EXISTS `speackers`;

CREATE TABLE speackers
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    title        VARCHAR(255) NULL,
    img_url      VARCHAR(255) NULL,
    description  VARCHAR(255) NULL,
    phone_number VARCHAR(255) NULL,
    contact      VARCHAR(255) NULL,
    event        BIGINT NOT NULL,
    company      VARCHAR(255) NULL,
    major        VARCHAR(255) NULL,
    level        VARCHAR(255) NULL,

    CONSTRAINT pk_speackers PRIMARY KEY (id),
    CONSTRAINT fk_speackers_event FOREIGN KEY (event) REFERENCES events (id)
);