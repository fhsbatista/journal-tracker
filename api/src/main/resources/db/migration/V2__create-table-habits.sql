CREATE TABLE habits(
    id bigint not null auto_increment,
    description varchar(200) not null,
    area_id bigint not null,
    current_score double not null,

    foreign key (area_id) references areas(id) on delete cascade,
    primary key (id)
)