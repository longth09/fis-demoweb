DROP TABLE IF EXISTS `tickets`;

CREATE TABLE tickets
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    name           VARCHAR(255) NOT NULL,
    address        VARCHAR(255) NOT NULL,
    `rank`          CHAR(10) NOT NULL,
    price          decimal(12,0) NULL,
    note           VARCHAR(255)  NULL,
    meetings           VARCHAR(255)  NULL,
    ticket_type           VARCHAR(255)  NULL,
    event_id        BIGINT NULL,
    `start_time`     datetime(3) NULL     DEFAULT CURRENT_TIMESTAMP (3),
    `created_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    `updated_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    CONSTRAINT pk_ticket PRIMARY KEY (id),
    CONSTRAINT fk_tickets_event FOREIGN KEY (event_id) REFERENCES events(id)

);