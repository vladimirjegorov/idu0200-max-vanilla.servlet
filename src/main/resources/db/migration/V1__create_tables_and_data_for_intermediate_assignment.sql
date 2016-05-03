CREATE TABLE classroom (
  id             SERIAL       NOT NULL,
  title           VARCHAR(100) NOT NULL,
  seating_capacity INTEGER DEFAULT 0 ,
  description        TEXT,
  PRIMARY KEY (id)
);

INSERT INTO classroom (title, seating_capacity, description)
VALUES ('IT-213C', 20, 'Room with Windows PCs');

INSERT INTO classroom (title, seating_capacity, description)
VALUES ('IT-213B', 15, 'Room with Linux-based machines');

INSERT INTO classroom (title, seating_capacity, description)
VALUES ('IT-213F', 10, 'Room with Sun Computers');
