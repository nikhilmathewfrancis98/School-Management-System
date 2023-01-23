--INSERT INTO holidays (holiday,reason,type)VALUES('Jan 1','New-Year','FESTIVAL');
--INSERT INTO holidays (holiday,reason,type)VALUES(' Oct 31 ','Halloween','FESTIVAL');
--INSERT INTO holidays (holiday,reason,type)VALUES(' Nov 24 ','Thanksgiving Day','FESTIVAL');
--INSERT INTO holidays (holiday,reason,type)VALUES(' Dec 25 ','Christmas Eve','FESTIVAL');
--INSERT INTO holidays (holiday,reason,type)VALUES(' Jan 17 ','Sr Martin Luther King Day','FEDERAL');
--INSERT INTO holidays (holiday,reason,type)VALUES(' July 4 ','Independence Day','FEDERAL');
--INSERT INTO holidays (holiday,reason,type)VALUES(' Sep 5 ','Labor Day','FEDERAL');
--INSERT INTO holidays (holiday,reason,type)VALUES(' Nov 11 ','Veterans Day','FEDERAL');
--

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
  VALUES ('ADMIN',CURDATE(),'DBA');

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
  VALUES ('STUDENT',CURDATE(),'DBA');


INSERT INTO `person` (`name`,`email`,`mobile_number`,`pwd`,`role_id`,`created_at`, `created_by`)
  VALUES ('Admin','admin@eazyschool.com','3443434343','$2a$10$XhU4UcSxDPb5G0I0fT/CZ.Lfj2VW2fkLkUP5cOEM.xM8EzyUQXaD2', 1 ,CURDATE(),'DBA');