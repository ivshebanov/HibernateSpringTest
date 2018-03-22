INSERT INTO Doc_type (code, version, doc_name) VALUES (1, 0, 'Свидетельство о рождении');
INSERT INTO Doc_type (code, version, doc_name) VALUES (2, 0, 'Военный билет');
INSERT INTO Doc_type (code, version, doc_name)
VALUES (3, 0, 'Временное удостоверение, выданное взамен военного билета');
INSERT INTO Doc_type (code, version, doc_name) VALUES (4, 0, 'Паспорт иностранного гражданина');
INSERT INTO Doc_type (code, version, doc_name) VALUES (5, 0, 'Вид на жительство в Российской Федерации');
INSERT INTO Doc_type (code, version, doc_name) VALUES (6, 0, 'Удостоверение беженца');
INSERT INTO Doc_type (code, version, doc_name)
VALUES (7, 0, 'Разрешение на временное проживание в Российской Федерации');
INSERT INTO Doc_type (code, version, doc_name) VALUES (8, 0, 'Свидетельство о предоставлении убежища на территории РФ');
INSERT INTO Doc_type (code, version, doc_name) VALUES (9, 0, 'Паспорт гражданина Российской Федерации');
INSERT INTO Doc_type (code, version, doc_name)
VALUES (10, 0, 'Свидетельство о рождении, выданное органом иностранного гос');
INSERT INTO Doc_type (code, version, doc_name)
VALUES (11, 0, 'Удостоверение личности военнослужащего Российской Федерации');

INSERT INTO Country (code, version, citizenship_name) VALUES (1, 0, 'Австрия');
INSERT INTO Country (code, version, citizenship_name) VALUES (2, 0, 'Албания');
INSERT INTO Country (code, version, citizenship_name) VALUES (3, 0, 'Андорра');
INSERT INTO Country (code, version, citizenship_name) VALUES (4, 0, 'Белоруссия');
INSERT INTO Country (code, version, citizenship_name) VALUES (5, 0, 'Бельгия');
INSERT INTO Country (code, version, citizenship_name) VALUES (6, 0, 'Болгария');
INSERT INTO Country (code, version, citizenship_name) VALUES (7, 0, 'Босния и Герцеговина');
INSERT INTO Country (code, version, citizenship_name) VALUES (8, 0, 'Ватикан');
INSERT INTO Country (code, version, citizenship_name) VALUES (9, 0, 'Польша');
INSERT INTO Country (code, version, citizenship_name) VALUES (10, 0, 'Российская Федерация');
INSERT INTO Country (code, version, citizenship_name) VALUES (11, 0, 'Чехия');
INSERT INTO Country (code, version, citizenship_name) VALUES (12, 0, 'Швейцария');
INSERT INTO Country (code, version, citizenship_name) VALUES (13, 0, 'Швеция');
INSERT INTO Country (code, version, citizenship_name) VALUES (14, 0, 'Эстония');

INSERT INTO Organization (version, name, full_name, login, password, inn, kpp, address, phone, is_active, hash_active)
VALUES (0, 'bell', 'bellintegrator', 'Shebanov', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5',
           1234567890, 0987654321, 'Москва, ул. Арбат, д.1', 567898, 1, '12345hashcode');
INSERT INTO Organization (version, name, full_name, login, password, inn, kpp, address, phone, is_active, hash_active)
VALUES (0, 'Сбертех', 'СбербанкТехнологии', 'сбер', 'e0bc60c82713f64ef8a57c0c40d02ce24fd0141d5cc3086259c19b1e62a62bea',
           11223344, 66778899, 'Москва, ул. Остоженка, д.2', 876678, 0, '112233hashcode');

INSERT INTO Office (version, name, address, phone, is_active, org_id)
VALUES (0, 'belloffice', 'Москва, ул. Арбат, д.1', 567898, 1, 1);
INSERT INTO Office (version, name, address, phone, is_active, org_id)
VALUES (0, 'Сбертехофис', 'Москва, ул. Остоженка, д.2', 876678, 1, 2);

INSERT INTO User (version, first_name, second_name, middle_name, position, phone, doc_type_id, doc_number, doc_date, country_id, is_identified, office_id)
VALUES (0, 'Сергей', 'Сидоров', 'Викторович', 'Руководитель отдела', 556677, 5, 151, '2004-10-21', 1, 1, 1);
INSERT INTO User (version, first_name, second_name, middle_name, position, phone, doc_type_id, doc_number, doc_date, country_id, is_identified, office_id)
VALUES (0, 'Иван', 'Инванов', 'Иванович', 'Руководитель отдела кадров', 998877, 1, 678, '2018-11-11', 8, 1, 2);
