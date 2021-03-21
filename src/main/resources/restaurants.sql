create table if not exists project.restaurants
(
    id              int auto_increment,
    name            varchar(45) not null,
    address_id      int         not null,
    specialities_id int         not null,
    details_id      int         not null,
    booking_id      int         not null,
    constraint id_UNIQUE
        unique (id),
    constraint address_id
        foreign key (address_id) references project.address (id),
    constraint booking_id
        foreign key (booking_id) references project.booking (id),
    constraint details_id
        foreign key (details_id) references project.details (id)
);

