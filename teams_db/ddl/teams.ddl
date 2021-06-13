create table `team`
(
    `team_id`            int auto_increment primary key,
    `name`               varchar(128) not null,
    `commission`    	 float        not null,

    -- audit
    `created_date`       timestamp   default CURRENT_TIMESTAMP(),
    `last_modified_date` timestamp   default CURRENT_TIMESTAMP(),
    `last_modified_by`   varchar(64) default 'system',
    `record_status`      int         default 1
);

