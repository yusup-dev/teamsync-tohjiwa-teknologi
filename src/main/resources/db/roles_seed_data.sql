INSERT INTO `roles` 
(`id`, `created_date`, `created_by`, `deleted_at`, `description`, `is_deleted`, `last_modified_date`, `last_modified_by`, `role`, `name`, `status`)
VALUES 
(1, now(), 'sys', null, 'user can access the system', false, now(), 'sys', 'ROLE_USER', 'User', 'ACTIVE'),
(2, now(), 'sys', null, 'user can access the system and manage the system and login to admin panel', false, now(), 'sys', 'ROLE_ADMIN', 'Admin', 'ACTIVE');