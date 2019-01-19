CREATE TABLE users
(
  id       SERIAL UNIQUE,
  username VARCHAR(45) UNIQUE NOT NULL,
  password VARCHAR(255)       NOT NULL,
  enabled  BOOLEAN            NOT NULL DEFAULT TRUE,

  PRIMARY KEY (id),
  UNIQUE (username)
);

CREATE TABLE authorities
(
  id        SERIAL UNIQUE,
  username  VARCHAR(45) NOT NULL,
  authority VARCHAR(45) NOT NULL,

  PRIMARY KEY (id),
  UNIQUE (authority, username),
  FOREIGN KEY (username) REFERENCES users (username)
);
