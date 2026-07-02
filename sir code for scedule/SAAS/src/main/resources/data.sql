INSERT IGNORE INTO countries (id, active, code, name)
VALUES (1, b'1', 'BD', 'Bangladesh');
INSERT IGNORE INTO location_label_config (country_code, level, label) VALUES
('BD', 'ADMIN_LEVEL_1', 'Division'),
('BD', 'ADMIN_LEVEL_2', 'District'),
('BD', 'ADMIN_LEVEL_3', 'Police Station');
INSERT IGNORE INTO locations (id, name, level, parent_id, country_id, active) VALUES
(1,'Dhaka','ADMIN_LEVEL_1',NULL,1,b'1'),
(2,'Chattogram','ADMIN_LEVEL_1',NULL,1,b'1'),
(3,'Rajshahi','ADMIN_LEVEL_1',NULL,1,b'1'),
(4,'Khulna','ADMIN_LEVEL_1',NULL,1,b'1'),
(5,'Barishal','ADMIN_LEVEL_1',NULL,1,b'1'),
(6,'Sylhet','ADMIN_LEVEL_1',NULL,1,b'1'),
(7,'Rangpur','ADMIN_LEVEL_1',NULL,1,b'1'),
(8,'Mymensingh','ADMIN_LEVEL_1',NULL,1,b'1');
INSERT IGNORE INTO locations (id, name, level, parent_id, country_id, active) VALUES
(101,'Dhaka','ADMIN_LEVEL_2',1,1,b'1'),
(102,'Gazipur','ADMIN_LEVEL_2',1,1,b'1'),
(103,'Narayanganj','ADMIN_LEVEL_2',1,1,b'1'),
(104,'Narsingdi','ADMIN_LEVEL_2',1,1,b'1'),
(105,'Tangail','ADMIN_LEVEL_2',1,1,b'1'),
(106,'Kishoreganj','ADMIN_LEVEL_2',1,1,b'1'),
(107,'Manikganj','ADMIN_LEVEL_2',1,1,b'1'),
(108,'Munshiganj','ADMIN_LEVEL_2',1,1,b'1'),
(109,'Rajbari','ADMIN_LEVEL_2',1,1,b'1'),
(110,'Faridpur','ADMIN_LEVEL_2',1,1,b'1'),
(111,'Gopalganj','ADMIN_LEVEL_2',1,1,b'1'),
(112,'Madaripur','ADMIN_LEVEL_2',1,1,b'1'),
(113,'Shariatpur','ADMIN_LEVEL_2',1,1,b'1');
INSERT IGNORE INTO locations (id, name, level, parent_id, country_id, active) VALUES
(201,'Chattogram','ADMIN_LEVEL_2',2,1,b'1'),
(202,'Cox''s Bazar','ADMIN_LEVEL_2',2,1,b'1'),
(203,'Cumilla','ADMIN_LEVEL_2',2,1,b'1'),
(204,'Noakhali','ADMIN_LEVEL_2',2,1,b'1'),
(205,'Feni','ADMIN_LEVEL_2',2,1,b'1'),
(206,'Lakshmipur','ADMIN_LEVEL_2',2,1,b'1'),
(207,'Brahmanbaria','ADMIN_LEVEL_2',2,1,b'1'),
(208,'Chandpur','ADMIN_LEVEL_2',2,1,b'1'),
(209,'Khagrachari','ADMIN_LEVEL_2',2,1,b'1'),
(210,'Rangamati','ADMIN_LEVEL_2',2,1,b'1'),
(211,'Bandarban','ADMIN_LEVEL_2',2,1,b'1');
INSERT IGNORE INTO locations (id, name, level, parent_id, country_id, active) VALUES
(1001,'Dhanmondi','ADMIN_LEVEL_3',101,1,b'1'),
(1002,'Gulshan','ADMIN_LEVEL_3',101,1,b'1'),
(1003,'Mirpur','ADMIN_LEVEL_3',101,1,b'1'),
(1004,'Uttara','ADMIN_LEVEL_3',101,1,b'1'),
(1005,'Mohammadpur','ADMIN_LEVEL_3',101,1,b'1');

--        For US
INSERT INTO countries (id, active, code, name)
VALUES (2, b'1', 'US', 'United States');

INSERT INTO location_label_config (country_code, level, label) VALUES
                                                                   ('US', 'ADMIN_LEVEL_1', 'State'),
                                                                   ('US', 'ADMIN_LEVEL_2', 'County'),
                                                                   ('US', 'ADMIN_LEVEL_3', 'City');

INSERT INTO locations (id, name, level, parent_id, country_id, active) VALUES
                                                                           (212,'Alabama','ADMIN_LEVEL_1',NULL,2,b'1'),
                                                                           (213,'Alaska','ADMIN_LEVEL_1',NULL,2,b'1'),
                                                                           (214,'Arizona','ADMIN_LEVEL_1',NULL,2,b'1'),
                                                                           (215,'Arkansas','ADMIN_LEVEL_1',NULL,2,b'1'),
                                                                           (216,'California','ADMIN_LEVEL_1',NULL,2,b'1'),
                                                                           (217,'Colorado','ADMIN_LEVEL_1',NULL,2,b'1'),
                                                                           (218,'Connecticut','ADMIN_LEVEL_1',NULL,2,b'1'),
                                                                           (219,'Delaware','ADMIN_LEVEL_1',NULL,2,b'1'),
                                                                           (220,'Florida','ADMIN_LEVEL_1',NULL,2,b'1'),
                                                                           (221,'Georgia','ADMIN_LEVEL_1',NULL,2,b'1');
INSERT INTO locations (id, name, level, parent_id, country_id, active) VALUES
                                                                           (20501,'Los Angeles County','ADMIN_LEVEL_2',216,2,b'1'),
                                                                           (20502,'San Diego County','ADMIN_LEVEL_2',216,2,b'1'),
                                                                           (20503,'Orange County','ADMIN_LEVEL_2',216,2,b'1'),
                                                                           (20504,'Riverside County','ADMIN_LEVEL_2',216,2,b'1'),
                                                                           (20505,'San Bernardino County','ADMIN_LEVEL_2',216,2,b'1');
INSERT INTO locations (id, name, level, parent_id, country_id, active) VALUES
                                                                           (20301,'Harris County','ADMIN_LEVEL_2',214,2,b'1'),
                                                                           (20302,'Dallas County','ADMIN_LEVEL_2',214,2,b'1'),
                                                                           (20303,'Tarrant County','ADMIN_LEVEL_2',214,2,b'1'),
                                                                           (20304,'Bexar County','ADMIN_LEVEL_2',214,2,b'1'),
                                                                           (20305,'Travis County','ADMIN_LEVEL_2',214,2,b'1');
INSERT INTO locations (id, name, level, parent_id, country_id, active) VALUES
                                                                           (2050101,'Los Angeles','ADMIN_LEVEL_3',20501,2,b'1'),
                                                                           (2050102,'Long Beach','ADMIN_LEVEL_3',20501,2,b'1'),
                                                                           (2050103,'Glendale','ADMIN_LEVEL_3',20501,2,b'1');
INSERT INTO locations (id, name, level, parent_id, country_id, active) VALUES
                                                                           (2050301,'Anaheim','ADMIN_LEVEL_3',20503,2,b'1'),
                                                                           (2050302,'Santa Ana','ADMIN_LEVEL_3',20503,2,b'1'),
                                                                           (2050303,'Irvine','ADMIN_LEVEL_3',20503,2,b'1');
