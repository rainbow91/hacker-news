use movies;
CREATE TABLE hacker_news_story (
  id         BIGINT(20) NOT NULL AUTO_INCREMENT,
  story_id   BIGINT(20),
  title      varchar(100),
  url        varchar(300),
  time       DATE,
  PRIMARY KEY (id)
)
  ENGINE = innodb
  DEFAULT CHARSET = utf8;
