CREATE TABLE contacts (
  id IDENTITY,
  name VARCHAR(50),
  CONSTRAINT contacts_id PRIMARY KEY (id)
);

INSERT INTO contacts (name) VALUES ('Andrew');
INSERT INTO contacts (name) VALUES ('Vasya');
INSERT INTO contacts (name) VALUES ('Mike');
INSERT INTO contacts (name) VALUES ('Teresa');
INSERT INTO contacts (name) VALUES ('Bob');