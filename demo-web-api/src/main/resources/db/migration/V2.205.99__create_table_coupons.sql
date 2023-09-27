DROP TABLE IF EXISTS `coupons`;

CREATE TABLE coupons
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    name                VARCHAR(255) NOT NULL,
    code                VARCHAR(255) NOT NULL,
    description         VARCHAR(255) NOT NULL,
    expire_time         DATETIME     NOT NULL,
    quantity            INT          NOT NULL,
    ticket_id           BIGINT       NOT NULL,
    event_id            BIGINT       NOT NULL,
    discount            DOUBLE       NOT NULL,
    discount_percentage DOUBLE       NOT NULL,
    max_discount        DOUBLE       NOT NULL,
    current_quantity    INT          NOT NULL,

    CONSTRAINT pk_coupons PRIMARY KEY (id),
    CONSTRAINT pk_coupons_event FOREIGN KEY (event_id) REFERENCES events (id)
);