-- INSERT INTO House (id, version, address) VALUES (1, 0, 'ул.Цюрупы, 16');
--
-- INSERT INTO Person (version, first_name, age, house_id) VALUES (0, 'Пётр', 20, 1);
--
-- INSERT INTO Person (version, first_name, age, house_id) VALUES (0, 'John', 25, 1);

INSERT INTO Docs (doc_name) VALUES ('Свидетельство о рождении');
INSERT INTO Docs (doc_name) VALUES ('Военный билет');
INSERT INTO Docs (doc_name) VALUES ('Временное удостоверение, выданное взамен военного билета');
INSERT INTO Docs (doc_name) VALUES ('Паспорт иностранного гражданина');
INSERT INTO Docs (doc_name) VALUES ('Вид на жительство в Российской Федерации');
INSERT INTO Docs (doc_name) VALUES ('Удостоверение беженца');
INSERT INTO Docs (doc_name) VALUES ('Разрешение на временное проживание в Российской Федерации');
INSERT INTO Docs (doc_name) VALUES ('Свидетельство о предоставлении убежища на территории РФ');
INSERT INTO Docs (doc_name) VALUES ('Паспорт гражданина Российской Федерации');
INSERT INTO Docs (doc_name) VALUES ('Свидетельство о рождении, выданное органом иностранного гос');
INSERT INTO Docs (doc_name) VALUES ('Удостоверение личности военнослужащего Российской Федерации');

INSERT INTO Countries (citizenship_name) VALUES ('Австрия');
INSERT INTO Countries (citizenship_name) VALUES ('Албания');
INSERT INTO Countries (citizenship_name) VALUES ('Андорра');
INSERT INTO Countries (citizenship_name) VALUES ('Белоруссия');
INSERT INTO Countries (citizenship_name) VALUES ('Бельгия');
INSERT INTO Countries (citizenship_name) VALUES ('Болгария');
INSERT INTO Countries (citizenship_name) VALUES ('Босния и Герцеговина');
INSERT INTO Countries (citizenship_name) VALUES ('Ватикан');
INSERT INTO Countries (citizenship_name) VALUES ('Польша');
INSERT INTO Countries (citizenship_name) VALUES ('Российская Федерация');
INSERT INTO Countries (citizenship_name) VALUES ('Чехия');
INSERT INTO Countries (citizenship_name) VALUES ('Швейцария');
INSERT INTO Countries (citizenship_name) VALUES ('Швеция');
INSERT INTO Countries (citizenship_name) VALUES ('Эстония');

INSERT INTO Register (name, login, password)
VALUES ('bell', 'Shebanov', '12345');
INSERT INTO Register (name, login, password)
VALUES ('Сбертех', 'сбер', '112233');

INSERT INTO Organization (name, full_name, inn, kpp, address, phone, is_active)
VALUES ('bell', 'bellintegrator', 1234567890, 0987654321, 'Москва, ул. Арбат, д.1', 567898, 1);
INSERT INTO Organization (name, full_name, inn, kpp, address, phone, is_active)
VALUES ('Сбертех', 'СбербанкТехнологии', 11223344, 66778899, 'Москва, ул. Остоженка, д.2', 876678, 1);

INSERT INTO Office (name, address, phone, is_active, org_id)
VALUES ('belloffice', 'Москва, ул. Арбат, д.1', 567898, 1, 1);
INSERT INTO Office (name, address, phone, is_active, org_id)
VALUES ('Сбертехофис', 'Москва, ул. Остоженка, д.2', 876678, 1, 2);

INSERT INTO User (first_name, second_name, middle_name, position, phone, office_id)
VALUES ('Иван', 'Сидоров', 'Викторович', 'Руководитель отдела', 556677, 1);
INSERT INTO User (first_name, second_name, middle_name, position, phone, office_id)
VALUES ('Иван', 'Инванов', 'Иванович', 'Руководитель отдела кадров', 998877, 2);

INSERT INTO Documentation (user_id, doc_code, doc_number, doc_date, citizenship_code, is_identified)
VALUES (1, 1, 151, '2004-10-21', 1, 1);
INSERT INTO Documentation (user_id, doc_code, doc_number, doc_date, citizenship_code, is_identified)
VALUES (2, 2, 678, '2018-11-11', 5, 1);