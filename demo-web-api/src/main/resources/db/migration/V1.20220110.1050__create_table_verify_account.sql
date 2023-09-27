DROP TABLE IF EXISTS `verify_account`;

CREATE TABLE verify_account
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    email          VARCHAR(255) NOT NULL,
    type           VARCHAR(255) NULL,
    checksum       VARCHAR(255) NULL,
    `created_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    `updated_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    CONSTRAINT pk_account PRIMARY KEY (id)
);