CREATE TABLE IF NOT EXISTS student (
  studentId serial NOT NULL,
  firstName varchar(100) DEFAULT NULL,
  lastName varchar(100) DEFAULT NULL,
  course varchar(100) DEFAULT NULL,
  year int DEFAULT NULL,
 PRIMARY KEY (studentId));