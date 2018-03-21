CREATE TABLE IF NOT EXISTS Organization (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  version     INTEGER      NOT NULL,
  name        VARCHAR(50)  NOT NULL,
  full_name   VARCHAR(50)  NOT NULL,
  login       VARCHAR(100) NOT NULL,
  password    VARCHAR(100) NOT NULL,
  inn         INTEGER      NOT NULL,
  kpp         INTEGER      NOT NULL,
  address     VARCHAR(50)  NOT NULL,
  phone       INTEGER      NOT NULL,
  is_active   BIT          NOT NULL,
  hash_active VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Office (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  version   INTEGER     NOT NULL,
  name      VARCHAR(50) NOT NULL,
  address   VARCHAR(50) NOT NULL,
  phone     INTEGER     NOT NULL,
  is_active BIT         NOT NULL,
  org_id    INTEGER
);

CREATE TABLE IF NOT EXISTS User (
  id               INTEGER PRIMARY KEY AUTO_INCREMENT,
  version          INTEGER     NOT NULL,
  first_name       VARCHAR(50) NOT NULL,
  second_name      VARCHAR(50) NOT NULL,
  middle_name      VARCHAR(50) NOT NULL,
  position         VARCHAR(50) NOT NULL,
  phone            INTEGER     NOT NULL,
  doc_code         INTEGER     NOT NULL,
  doc_name         VARCHAR(60) NOT NULL,
  doc_number       INTEGER     NOT NULL,
  doc_date         DATE        NOT NULL,
  citizenship_code INTEGER     NOT NULL,
  citizenship_name VARCHAR(50) NOT NULL,
  is_identified    BIT         NOT NULL,
  office_id        INTEGER
);

CREATE TABLE IF NOT EXISTS Doc_type (
  id       INTEGER PRIMARY KEY AUTO_INCREMENT,
  code     INTEGER     NOT NULL,
  version  INTEGER     NOT NULL,
  doc_name VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS Country (
  id               INTEGER PRIMARY KEY AUTO_INCREMENT,
  code             INTEGER     NOT NULL,
  version          INTEGER     NOT NULL,
  citizenship_name VARCHAR(50) NOT NULL
);

ALTER TABLE Office
  ADD FOREIGN KEY (org_id) REFERENCES Organization (id);
ALTER TABLE User
  ADD FOREIGN KEY (office_id) REFERENCES Office (id);

CREATE INDEX IX_Office_org_id
  ON Office (org_id);
CREATE INDEX IX_User_office_id
  ON User (office_id);