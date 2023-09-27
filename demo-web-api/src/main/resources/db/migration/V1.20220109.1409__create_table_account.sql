DROP TABLE IF EXISTS `account`;

CREATE TABLE account
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    name           VARCHAR(255) NOT NULL,
    email          VARCHAR(255) NOT NULL,
    image_url      VARCHAR(255) NULL,
    email_verified BIT(1)       NOT NULL,
    password       VARCHAR(255) NULL,
    provider       VARCHAR(255) NULL,
    provider_id    VARCHAR(255) NULL,
    `created_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    `updated_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    CONSTRAINT pk_account PRIMARY KEY (id)
);