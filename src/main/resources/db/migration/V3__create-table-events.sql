CREATE TABLE events(
    id bigint not null auto_increment,
    time Date not null,
    habit_id bigint not null,
    score double not null,

    foreign key (habit_id) references habits(id) on delete cascade,
    primary key (id)
)