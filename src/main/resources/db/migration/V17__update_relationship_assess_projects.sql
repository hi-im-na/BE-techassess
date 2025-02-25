ALTER TABLE assesses
ADD COLUMN project_id BIGINT NOT NULL;

ALTER TABLE assesses
ADD CONSTRAINT FK_projects_id FOREIGN KEY (project_id) REFERENCES projects(id);