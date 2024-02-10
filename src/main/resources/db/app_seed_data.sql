INSERT INTO `apps`
(`id`, `created_date`, `created_by`, `is_deleted`, `last_modified_date`, `last_modified_by`, `status`, `process`, `productivity_status`, `name`, `term`, `app_category_id`)
VALUES
(1, now(), 'sys', false, now(), 'sys', 'ACTIVE', 'edge.exe', 'UNKNOWN', 'Microsoft Edge', 'edge.exe', 1);