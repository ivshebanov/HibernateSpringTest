# CREATE TABLE IF NOT EXISTS Person (
#     id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
#     version    INTEGER NOT NULL,
#     first_name VARCHAR(50) NOT NULL,
#     house_id   INTEGER,
#     age        INTEGER  NOT NULL
# );
#
# CREATE TABLE IF NOT EXISTS House (
#     id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
#     version    INTEGER NOT NULL,
#     address    VARCHAR(50) NOT NULL
# );
#
# CREATE INDEX IX_Person_House_Id ON Person (house_id);
# ALTER TABLE Person ADD FOREIGN KEY (house_id) REFERENCES House(id);

CREATE TABLE IF NOT EXISTS Register (
  id       INTEGER PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(50) NOT NULL,
  version  INTEGER     NOT NULL,
  login    VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Organization (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  version   INTEGER     NOT NULL,
  name      VARCHAR(50),
  full_name VARCHAR(50) NOT NULL,
  inn       INTEGER     NOT NULL,
  kpp       INTEGER     NOT NULL,
  address   VARCHAR(50) NOT NULL,
  phone     INTEGER     NOT NULL,
  is_active BIT         NOT NULL
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
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  version     INTEGER     NOT NULL,
  first_name  VARCHAR(50) NOT NULL,
  second_name VARCHAR(50) NOT NULL,
  middle_name VARCHAR(50) NOT NULL,
  position    VARCHAR(50) NOT NULL,
  phone       INTEGER     NOT NULL,
  office_id   INTEGER
);

CREATE TABLE IF NOT EXISTS Documentation (
  id            INTEGER PRIMARY KEY AUTO_INCREMENT,
  version       INTEGER NOT NULL,
  user_id       INTEGER,
  doc_code      INTEGER,
  doc_number    INTEGER NOT NULL,
  doc_date      DATE    NOT NULL,
  country_code  INTEGER,
  is_identified BIT     NOT NULL
);

CREATE TABLE IF NOT EXISTS Docs (
  id       INTEGER PRIMARY KEY AUTO_INCREMENT,
  code     INTEGER     NOT NULL,
  version  INTEGER     NOT NULL,
  doc_name VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS Countries (
  id               INTEGER PRIMARY KEY AUTO_INCREMENT,
  code             INTEGER     NOT NULL,
  version          INTEGER     NOT NULL,
  citizenship_name VARCHAR(50) NOT NULL
);

ALTER TABLE Organization
  ADD FOREIGN KEY (name) REFERENCES Register (name);
ALTER TABLE Office
  ADD FOREIGN KEY (org_id) REFERENCES Organization (id);
ALTER TABLE User
  ADD FOREIGN KEY (office_id) REFERENCES Office (id);
ALTER TABLE Documentation
  ADD FOREIGN KEY (user_id) REFERENCES User (id);
ALTER TABLE Documentation
  ADD FOREIGN KEY (doc_code) REFERENCES Docs (code);
ALTER TABLE Documentation
  ADD FOREIGN KEY (country_code) REFERENCES Countries (code);

CREATE INDEX IX_Organization_name
  ON Organization (name);
CREATE INDEX IX_Office_org_id
  ON Office (org_id);
CREATE INDEX IX_User_office_id
  ON User (office_id);
CREATE INDEX IX_Documentation_user_id_doc_code_country_code
  ON Documentation (user_id, doc_code, country_code);