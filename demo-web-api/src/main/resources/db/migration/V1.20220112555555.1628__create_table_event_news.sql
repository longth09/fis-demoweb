DROP TABLE IF EXISTS `event_news`;

CREATE TABLE event_news
(
    title  VARCHAR(255) NOT NULL,
    `desc` VARCHAR(255) NOT NULL,
    imgUrl VARCHAR(255) NOT NULL,
    `rank` INT          NOT NULL,
    event  BIGINT       NOT NULL,
    type   VARCHAR(255) NOT NULL,

    CONSTRAINT fk_event_news_event FOREIGN KEY (event) REFERENCES events (id)
);