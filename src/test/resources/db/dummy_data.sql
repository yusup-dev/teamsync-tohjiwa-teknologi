INSERT INTO `users` 
(`id`, `created_date`, `created_by`, `deleted_at`, `email`, `first_name`, `is_deleted`, `last_name`, `last_modified_date`, `last_modified_by`, `password`, `phone_number`, `phone_number_country_code`, `username`, `user_status`) 
VALUES 
(-1, now(), 'sys', null, 'user@tmsync.co', 'Eric', false, 'Sanjaya', now(), 'sys', '$2a$10$x7D7ZrUozIdeUvYT9./Vf.q.Vzqr4mEN7869O1wwqzBUdOn7JRQUu', '87860408682', '+62', 'ericsanjaya', 'ACTIVE');


INSERT INTO `users_roles`
(`id`, `created_date`, `created_by`, `deleted_at`, `is_deleted`, `last_modified_date`, `last_modified_by`, `role_id`, `user_id`, `status`)
VALUES
(-1, now(), 'sys', null, false, now(), 'sys', 1, -1, 'ACTIVE');


INSERT INTO `personal_workspaces` 
(`id`, `created_date`, `created_by`, `deleted_at`, `description`, `is_deleted`, `last_modified_date`, `Last_modified_by`, `name`, `workspace_type_id`, `status`)
VALUES 
(-1, now(), 'sys', null, 'Dummy', false, now(), 'sys', 'Eric Personal', 1, 'ACTIVE');


INSERT INTO `personal_workspace_plan_subscriptions`
(`id`, `active_from`, `active_until`, `auto_renew`, `created_date`, `created_by`, `deleted_at`, `description`, `feature_codes`, `is_deleted`, `is_free_plan`, `last_modified_date`, `last_modified_by`, `name`, `note`, `number_of_users`, `subscription_period`, `tag_ids`, `personal_workspace_id`, `plan_subscription_status`, `user_payment_method_id`, `status`)
VALUES
(-1, now(), null, false, now(), 'sys', null, 'sasa', '["core"]', false, true, now(), 'sys', 'Personal Free', 'Free Forever', 1, 0, null, -1, 'ACTIVE', null, 'ACTIVE');


INSERT INTO `personal_workspace_users`
(`id`, `created_date`, `created_by`, `deleted_at`, `is_deleted`, `last_modified_date`, `last_modified_by`, `personal_workspace_id`, `personal_workspace_plan_subscription_id`, `personal_workspace_role_id`, `user_id`, `user_status`)
VALUES
(-1, now(), 'sys', null, false, now(), 'sys', -1, -1, 1, -1, 'ACTIVE');


INSERT INTO `personal_workspace_user_configs`
(`id`, `created_by`, `created_date`, `is_deleted`, `last_modified_by`, `last_modified_date`, `status`, `user_configuration`, `personal_workspace_id`, `user_id`)
VALUES
(-1, 'sys', now(), false, 'sys', now(), 'ACTIVE', '{"specificApp": false, "specificUrl": true, "specificPwsSetting": true}', -1, -1);

INSERT INTO `users_payment_methods`
(`id`, `created_date`, `created_by`, `is_deleted`, `last_modified_date`, `last_modified_by`, `status`, `payment_method`, `user_id`)
VALUES
(-1, now(), 'sys', false, now(), 'sys', 'ACTIVE', '{"type":"paypal"}', -1);


INSERT INTO `personal_workspace_projects` 
(`id`, `created_by`, `created_date`, `is_deleted`, `last_modified_by`, `last_modified_date`, `status`, `description`, `last_operation`, `name`, `source`, `personal_workspace_id`, `user_id`) 
VALUES 
(-1, now(), now(), false, 'sys', now(), 'ACTIVE', '-', 'CREATE', 'Default', 'AUTO_DEFAULT', -1, -1);


INSERT INTO`personal_workspace_tasks` 
(`id`, `created_by`, `created_date`, `is_deleted`, `last_modified_by`, `last_modified_date`, `status`, `description`, `is_flagged`, `last_operation`, `name`, `source`, `personal_workspace_id`, `personal_workspace_project_id`, `user_id`)
VALUES
(-1, 'sys', now(), false, 'sys', now(), 'ACTIVE', '-', false, 'CREATE', 'Default', 'AUTO_DEFAULT', -1, -1, -1);


INSERT INTO `personal_workspace_urls` 
(`id`, `created_by`, `created_date`, `is_deleted`, `last_modified_by`, `status`, `urls`, `personal_workspace_id`)
VALUES 
(-1, 'sys', now(), false, 'sys', 'ACTIVE', '[{"id":1,"name":"Facebook","domain":"facebook.com","url":"","urlCategoryId":1,"productivityStatus":"UNPRODUCTIVE"}]', -1);


INSERT INTO `personal_workspace_user_urls` 
(`id`, `created_by`, `created_date`, `is_deleted`, `last_modified_by`, `last_modified_date`, `status`, `urls`, `personal_workspace_id`, `user_id`) 
VALUES 
(-1, 'sys', now(), false, 'sys', now(), 'ACTIVE', '[{"id":1,"name":"Facebook","domain":"facebook.com","url":"","urlCategoryId":1,"productivityStatus":"PRODUCTIVE"}]', -1, -1);


INSERT INTO `personal_workspace_apps` 
(`id`, `created_by`, `created_date`, `is_deleted`, `last_modified_by`, `last_modified_date`, `status`, `apps`, `personal_workspace_id`)
VALUES 
(-1, 'sys', now(), false, 'sys', now(), 'ACTIVE', '[{"id":1,"name":"Microsoft Edge","process":"msedge","term":"msedge.exe, mikocok","appCategoryId":1,"productivityStatus":"PRODUCTIVE"}]', -1);


INSERT INTO `personal_workspace_user_apps`
(`id`, `created_by`, `created_date`, `is_deleted`, `last_modified_by`, `last_modified_date`, `status`, `apps`, `personal_workspace_id`, `user_id`)
VALUES
(-1, 'sys', now(), false, 'sys', now(), 'ACTIVE', '[{"id":1,"name":"Microsoft Edge","process":"msedge","term":"msedge.exe, mikocok","appCategoryId":1,"productivityStatus":"UNPRODUCTIVE"}]', -1, -1);