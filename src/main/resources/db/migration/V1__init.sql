CREATE TABLE greetings
(
  id       SERIAL UNIQUE,
  name     VARCHAR(45) UNIQUE NOT NULL,

  PRIMARY KEY (id),
  UNIQUE (name)
);
