ALTER TABLE projects
ADD COLUMN leader_id BIGINT;

ALTER TABLE projects
ADD CONSTRAINT FK_leader_id FOREIGN KEY (leader_id) REFERENCES users(id);