INSERT INTO `plan_features` 
(`id`, `created_date`, `created_by`, `deleted_at`, `description`, `external_link`, `feature_code`, `is_deleted`, `last_modified_date`, `last_modified_by`, `name`, `price`, `requirement_feature_codes`, `currency_id`, `status`)
VALUES
(1, now(), 'sys', null, 'sasas', null, 'core', false, now(), 'sys', 'Core', 0, '["core"]', 1, 'ACTIVE');