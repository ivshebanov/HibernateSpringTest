-- INSERT INTO House (id, version, address) VALUES (1, 0, 'ул.Цюрупы, 16');
--
-- INSERT INTO Person (version, first_name, age, house_id) VALUES (0, 'Пётр', 20, 1);
--
-- INSERT INTO Person (version, first_name, age, house_id) VALUES (0, 'John', 25, 1);

INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Свидетельство о рождении');
INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Военный билет');
INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Временное удостоверение, выданное взамен военного билета');
INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Паспорт иностранного гражданина');
INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Вид на жительство в Российской Федерации');
INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Удостоверение беженца');
INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Разрешение на временное проживание в Российской Федерации');
INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Свидетельство о предоставлении убежища на территории РФ');
INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Паспорт гражданина Российской Федерации');
INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Свидетельство о рождении, выданное органом иностранного гос');
INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Удостоверение личности военнослужащего Российской Федерации');

INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Австрия');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Албания');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Андорра');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Белоруссия');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Бельгия');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Болгария');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Босния и Герцеговина');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Ватикан');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Польша');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Российская Федерация');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Чехия');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Швейцария');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Швеция');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Эстония');

INSERT INTO Register (name, version, login, password)
VALUES ('bell', 0, 'Shebanov', '12345');
INSERT INTO Register (name, version, login, password)
VALUES ('Сбертех', 0, 'сбер', '112233');

INSERT INTO Organization (version, name, full_name, inn, kpp, address, phone, is_active)
VALUES (0, 'bell', 'bellintegrator', 1234567890, 0987654321, 'Москва, ул. Арбат, д.1', 567898, 1);
INSERT INTO Organization (version, name, full_name, inn, kpp, address, phone, is_active)
VALUES (0, 'Сбертех', 'СбербанкТехнологии', 11223344, 66778899, 'Москва, ул. Остоженка, д.2', 876678, 1);

INSERT INTO Office (version, name, address, phone, is_active, org_id)
VALUES (0, 'belloffice', 'Москва, ул. Арбат, д.1', 567898, 1, 1);
INSERT INTO Office (version, name, address, phone, is_active, org_id)
VALUES (0, 'Сбертехофис', 'Москва, ул. Остоженка, д.2', 876678, 1, 2);

INSERT INTO User (version, first_name, second_name, middle_name, position, phone, office_id)
VALUES (0, 'Иван', 'Сидоров', 'Викторович', 'Руководитель отдела', 556677, 1);
INSERT INTO User (version, first_name, second_name, middle_name, position, phone, office_id)
VALUES (0, 'Иван', 'Инванов', 'Иванович', 'Руководитель отдела кадров', 998877, 2);

INSERT INTO Documentation (version, user_id, doc_id, doc_number, doc_date, country_id, is_identified)
VALUES (0, 1, 1, 151, '2004-10-21', 1, 1);
INSERT INTO Documentation (version, user_id, doc_id, doc_number, doc_date, country_id, is_identified)
VALUES (0, 2, 2, 678, '2018-11-11', 5, 1);