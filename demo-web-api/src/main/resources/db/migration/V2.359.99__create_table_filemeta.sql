DROP TABLE IF EXISTS `file_meta`;

CREATE TABLE file_meta
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    file_name VARCHAR(255) NULL,
    file_path VARCHAR(255) NULL,
    version   VARCHAR(255) NULL,
    CONSTRAINT pk_file_meta PRIMARY KEY (id)
);
