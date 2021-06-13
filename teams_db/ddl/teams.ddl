create table `team`
(
    `team_id`            int auto_increment primary key,
    `name`               varchar(128) not null,
    `team_commission`    int          not null,

    -- audit
    `created_date`       timestamp   default CURRENT_TIMESTAMP(),
    `last_modified_date` timestamp   default CURRENT_TIMESTAMP(),
    `last_modified_by`   varchar(64) default 'system',
    `record_status`      int         default 1
);

create table `team_player`
(
    `team_fk`            int not null,
    `player_id`          int not null,
    primary key (`team_fk`, `player_id`),

    -- audit
    `created_date`       timestamp   default CURRENT_TIMESTAMP(),
    `last_modified_date` timestamp   default CURRENT_TIMESTAMP(),
    `last_modified_by`   varchar(64) default 'system',
    `record_status`      int         default 1,

    constraint foreign key `fk_team_player_team` (`team_fk`) references `team` (`team_id`)
        on update cascade on delete cascade
)

