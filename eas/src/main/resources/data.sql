INSERT INTO Docs (code, version, doc_name) VALUES (1, 0, 'Свидетельство о рождении');
INSERT INTO Docs (code, version, doc_name) VALUES (2, 0, 'Военный билет');
INSERT INTO Docs (code, version, doc_name) VALUES (3, 0, 'Временное удостоверение, выданное взамен военного билета');
INSERT INTO Docs (code, version, doc_name) VALUES (4, 0, 'Паспорт иностранного гражданина');
INSERT INTO Docs (code, version, doc_name) VALUES (5, 0, 'Вид на жительство в Российской Федерации');
INSERT INTO Docs (code, version, doc_name) VALUES (6, 0, 'Удостоверение беженца');
INSERT INTO Docs (code, version, doc_name) VALUES (7, 0, 'Разрешение на временное проживание в Российской Федерации');
INSERT INTO Docs (code, version, doc_name) VALUES (8, 0, 'Свидетельство о предоставлении убежища на территории РФ');
INSERT INTO Docs (code, version, doc_name) VALUES (9, 0, 'Паспорт гражданина Российской Федерации');
INSERT INTO Docs (code, version, doc_name) VALUES (10, 0, 'Свидетельство о рождении, выданное органом иностранного гос');
INSERT INTO Docs (code, version, doc_name) VALUES (11, 0, 'Удостоверение личности военнослужащего Российской Федерации');

INSERT INTO Countries (code, version, citizenship_name) VALUES (1, 0, 'Австрия');
INSERT INTO Countries (code, version, citizenship_name) VALUES (2, 0, 'Албания');
INSERT INTO Countries (code, version, citizenship_name) VALUES (3, 0, 'Андорра');
INSERT INTO Countries (code, version, citizenship_name) VALUES (4, 0, 'Белоруссия');
INSERT INTO Countries (code, version, citizenship_name) VALUES (5, 0, 'Бельгия');
INSERT INTO Countries (code, version, citizenship_name) VALUES (6, 0, 'Болгария');
INSERT INTO Countries (code, version, citizenship_name) VALUES (7, 0, 'Босния и Герцеговина');
INSERT INTO Countries (code, version, citizenship_name) VALUES (8, 0, 'Ватикан');
INSERT INTO Countries (code, version, citizenship_name) VALUES (9, 0, 'Польша');
INSERT INTO Countries (code, version, citizenship_name) VALUES (10, 0, 'Российская Федерация');
INSERT INTO Countries (code, version, citizenship_name) VALUES (11, 0, 'Чехия');
INSERT INTO Countries (code, version, citizenship_name) VALUES (12, 0, 'Швейцария');
INSERT INTO Countries (code, version, citizenship_name) VALUES (13, 0, 'Швеция');
INSERT INTO Countries (code, version, citizenship_name) VALUES (14, 0, 'Эстония');

INSERT INTO Register (name, version, login, password, hash_active)
VALUES ('bell', 0, 'Shebanov', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', '12345hashcode');
INSERT INTO Register (name, version, login, password, hash_active)
VALUES ('Сбертех', 0, 'сбер', 'e0bc60c82713f64ef8a57c0c40d02ce24fd0141d5cc3086259c19b1e62a62bea', '112233hashcode');

INSERT INTO Organization (version, name, full_name, inn, kpp, address, phone, is_active)
VALUES (0, 'bell', 'bellintegrator', 1234567890, 0987654321, 'Москва, ул. Арбат, д.1', 567898, 1);
INSERT INTO Organization (version, name, full_name, inn, kpp, address, phone, is_active)
VALUES (0, 'Сбертех', 'СбербанкТехнологии', 11223344, 66778899, 'Москва, ул. Остоженка, д.2', 876678, 0);

INSERT INTO Office (version, name, address, phone, is_active, org_id)
VALUES (0, 'belloffice', 'Москва, ул. Арбат, д.1', 567898, 1, 1);
INSERT INTO Office (version, name, address, phone, is_active, org_id)
VALUES (0, 'Сбертехофис', 'Москва, ул. Остоженка, д.2', 876678, 1, 2);

INSERT INTO User (version, first_name, second_name, middle_name, position, phone, office_id)
VALUES (0, 'Сергей', 'Сидоров', 'Викторович', 'Руководитель отдела', 556677, 1);
INSERT INTO User (version, first_name, second_name, middle_name, position, phone, office_id)
VALUES (0, 'Иван', 'Инванов', 'Иванович', 'Руководитель отдела кадров', 998877, 2);

INSERT INTO Documentation (version, user_id, doc_code, doc_number, doc_date, country_code, is_identified)
VALUES (0, 1, 5, 151, '2004-10-21', 1, 1);
INSERT INTO Documentation (version, user_id, doc_code, doc_number, doc_date, country_code, is_identified)
VALUES (0, 2, 1, 678, '2018-11-11', 5, 1);