--DROP TABLE contacts IF EXISTS;

CREATE TABLE contacts (
  id         INTEGER PRIMARY KEY,
  first_name VARCHAR(50),
  last_name  VARCHAR(50),
  number VARCHAR(20)
);