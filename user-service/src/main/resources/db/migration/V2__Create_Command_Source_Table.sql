CREATE TABLE `command_source` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `action_name` varchar(100) DEFAULT NULL,
 `checked_on_date` datetime DEFAULT NULL,
 `command_as_json` varchar(1000) DEFAULT NULL,
 `created_on_date` datetime NOT NULL,
 `entity_name` varchar(100) DEFAULT NULL,
 `processing_result_enum` int(11) NOT NULL,
 `api_get_url` varchar(100) DEFAULT NULL,
 `resource_id` bigint(20) DEFAULT NULL,
 `user_id` bigint(20) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARACTER SET=utf8