INSERT INTO `app_categories` 
(`id`, `created_date`, `created_by`, `is_deleted`, `last_modified_date`, `last_modified_by`, `status`, `description`, `external_link`, `name`)
VALUES
(1, now(), 'sys', false, now(), 'sys', 'ACTIVE', 'E-Commerce', 'https://www.google.com', 'E-Commerce');


INSERT INTO `app_news` 
(`id`, `created_date`, `created_by`, `is_deleted`, `last_modified_date`, `last_modified_by`, `status`, `is_publish`, `name`, `process`, `productivity_status`)
VALUES
(1, now(), 'sys', false, now(), 'sys', 'ACTIVE', false, NULL, 'explorer.exe', 'UNKNOWN');


INSERT INTO `apps`
(`id`, `created_date`, `created_by`, `is_deleted`, `last_modified_date`, `last_modified_by`, `status`, `process`, `productivity_status`, `name`, `term`, `app_category_id`)
VALUES
(1, now(), 'sys', false, now(), 'sys', 'ACTIVE', 'edge.exe', 'UNKNOWN', 'Microsoft Edge', 'edge.exe', 1);


INSERT INTO `currencies` 
(`id`, `created_date`, `created_by`, `currency_code`, `deleted_at`, `is_deleted`, `last_modified_date`, `last_modified_by`, `status`) 
VALUES 
(1, now(), 'sys', 'usd', null, false, now(), 'sys', 'ACTIVE');


INSERT INTO `personal_workspace_activities` 
(`id`, `activity`, `created_date`, `created_by`, `deleted_at`, `description`, `is_deleted`, `last_modified_date`, `last_modified_by`, `name`, `status`)
VALUES 
(1, 'active', now(), 'sys', null, null, false, now(), 'sys', 'Active Status', 'ACTIVE');


INSERT INTO `personal_workspace_roles` 
(`id`, `created_date`, `created_by`, `deleted_at`, `description`, `is_deleted`, `last_modified_date`, `last_modified_by`, `role`, `status`) 
VALUES 
(1, now(), 'sys', null, 'owner of the workspace', false, now(), 'sys', 'OWNER', 'ACTIVE'),
(2, now(), 'sys', null, 'member in a workspace', false, now(), 'sys', 'MEMBER', 'ACTIVE');


INSERT INTO `plan_features` 
(`id`, `created_date`, `created_by`, `deleted_at`, `description`, `external_link`, `feature_code`, `is_deleted`, `last_modified_date`, `last_modified_by`, `name`, `price`, `requirement_feature_codes`, `currency_id`, `status`)
VALUES
(1, now(), 'sys', null, 'sasas', null, 'core', false, now(), 'sys', 'Core', 0, '["core"]', 1, 'ACTIVE');


INSERT INTO `roles` 
(`id`, `created_date`, `created_by`, `deleted_at`, `description`, `is_deleted`, `last_modified_date`, `last_modified_by`, `role`, `name`, `status`)
VALUES 
(1, now(), 'sys', null, 'user can access the system', false, now(), 'sys', 'ROLE_USER', 'User', 'ACTIVE'),
(2, now(), 'sys', null, 'user can access the system and manage the system and login to admin panel', false, now(), 'sys', 'ROLE_ADMIN', 'Admin', 'ACTIVE');


INSERT INTO 
`url_categories` (`id`, `created_date`, `created_by`, `is_deleted`, `last_modified_date`, `last_modified_by`, `status`, `description`, `external_link`, `name`)
VALUES
(1, now(), 'sys', false, now(), 'sys', 'ACTIVE', 'ini e-commerce', 'https://www.google.com', 'E-Commerce');


INSERT INTO `url_news`
(`id`, `created_date`, `created_by`, `is_deleted`, `last_modified_date`, `last_modified_by`, `status`, `domain`, `is_publish`, `productivity_status`, `url`)
VALUES
(1, now(), 'sys', false, now(), 'sys', 'ACTIVE', 'www.amazon.com', false, 'UNKNOWN', 'www.amazon.com');


INSERT INTO `urls`
(`id`, `created_date`, `created_by`, `is_deleted`, `last_modified_date`, `last_modified_by`, `status`, `domain`, `name`, `productivity_status`, `url`, `url_category_id`)
VALUES
(1, now(), 'sys', false, now(), 'sys', 'ACTIVE', 'tokopedia.com', 'Tokopedia', 'UNPRODUCTIVE', 'www.tokopedia.com', 1);


INSERT INTO `workspace_types` 
(`id`, `created_date`, `created_by`, `deleted_at`, `description`, `is_deleted`, `last_modified_date`, `last_modified_by`, `name`, `status`)
VALUES 
(1, now(), 'sys', NULL, 'Personal workspace', false, now(), 'sys', 'Personal', 'ACTIVE'),
(2, now(), 'sys', NULL, 'Team workspace', false, now(), 'sys', 'Team', 'ACTIVE');

