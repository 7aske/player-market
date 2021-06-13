create table `player`
(
    `player_id`          int auto_increment primary key,
    `first_name`         varchar(128) not null,
    `last_name`          varchar(128) not null,
	`team_id`            int              null,
    `months_experience`  int          not null,
    `age`                int          not null,

    -- audit
    `created_date`       timestamp   default CURRENT_TIMESTAMP(),
    `last_modified_date` timestamp   default CURRENT_TIMESTAMP(),
    `last_modified_by`   varchar(64) default 'system',
    `record_status`      int         default 1
);
