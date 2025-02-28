ALTER TABLE assesses
    ADD CONSTRAINT unique_assess UNIQUE (user_id, to_user_id, project_id);