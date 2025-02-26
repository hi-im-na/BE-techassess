ALTER TABLE department_criterias
DROP FOREIGN KEY FK_DEPARTMENT_CRITERIAS_ON_CRITERIAS;

ALTER TABLE department_criterias
DROP FOREIGN KEY FK_DEPARTMENT_CRITERIAS_ON_DEPARTMENT;

ALTER TABLE department_criterias
DROP FOREIGN KEY FK_DEPARTMENT_CRITERIAS_ON_QUESTION;

DROP TABLE  department_criterias;

ALTER TABLE criterias
ADD COLUMN department_id BIGINT NOT NULL;

UPDATE criterias SET department_id = 1 WHERE id = 1;
UPDATE criterias SET department_id = 1 WHERE id = 2;
UPDATE criterias SET department_id = 1 WHERE id = 3;
UPDATE criterias SET department_id = 1 WHERE id = 4;
UPDATE criterias SET department_id = 1 WHERE id = 5;
UPDATE criterias SET department_id = 1 WHERE id = 6;
UPDATE criterias SET department_id = 1 WHERE id = 7;
UPDATE criterias SET department_id = 1 WHERE id = 8;
UPDATE criterias SET department_id = 2 WHERE id = 9;
UPDATE criterias SET department_id = 2 WHERE id = 10;
UPDATE criterias SET department_id = 2 WHERE id = 11;
UPDATE criterias SET department_id = 2 WHERE id = 12;
UPDATE criterias SET department_id = 2 WHERE id = 13;
UPDATE criterias SET department_id = 2 WHERE id = 14;
UPDATE criterias SET department_id = 2 WHERE id = 15;

ALTER TABLE criterias
ADD CONSTRAINT FK_DEPARTMENT_ID FOREIGN KEY(department_id) REFERENCES department(id);
