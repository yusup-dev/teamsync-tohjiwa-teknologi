INSERT INTO `workspace_types` 
(`id`, `created_date`, `created_by`, `deleted_at`, `description`, `is_deleted`, `last_modified_date`, `last_modified_by`, `name`, `status`)
VALUES 
(1, now(), 'sys', NULL, 'Personal workspace', false, now(), 'sys', 'Personal', 'ACTIVE'),
(2, now(), 'sys', NULL, 'Team workspace', false, now(), 'sys', 'Team', 'ACTIVE');

