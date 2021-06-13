create table `transfer`
(
    `transfer_id`        int auto_increment primary key,
    `from_team_id`       int   not null,
    `to_team_id`         int   not null,
    `player_id`          int   not null,
    `transfer_fee`       float     null,
    `commission`    	 float not null,

    -- audit
    `created_date`       timestamp   default CURRENT_TIMESTAMP(),
    `last_modified_date` timestamp   default CURRENT_TIMESTAMP(),
    `last_modified_by`   varchar(64) default 'system',
    `record_status`      int         default 1
)
