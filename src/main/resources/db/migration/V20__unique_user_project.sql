alter table user_projects
    add constraint unique_user_project unique (user_id, project_id);