CREATE SEQUENCE contacts_id_seq;
CREATE TABLE contacts (
    id smallint NOT NULL DEFAULT nextval('contacts_id_seq'),
    name CHAR(50)
);
ALTER SEQUENCE contacts_id_seq OWNED BY contacts.id;

INSERT INTO contacts (name) VALUES ('Andrew');
INSERT INTO contacts (name) VALUES ('Vasya');
INSERT INTO contacts (name) VALUES ('Mike');
INSERT INTO contacts (name) VALUES ('Teresa');
INSERT INTO contacts (name) VALUES ('Bob');