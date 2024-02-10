INSERT INTO `personal_workspace_roles` 
(`id`, `created_date`, `created_by`, `deleted_at`, `description`, `is_deleted`, `last_modified_date`, `last_modified_by`, `role`, `status`) 
VALUES 
(1, now(), 'sys', null, 'owner of the workspace', false, now(), 'sys', 'OWNER', 'ACTIVE'),
(2, now(), 'sys', null, 'member in a workspace', false, now(), 'sys', 'MEMBER', 'ACTIVE');
