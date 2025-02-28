ALTER TABLE assesses
    ADD CONSTRAINT UNIQUE (user_id, to_user_id, project_id);