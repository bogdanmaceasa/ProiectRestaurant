create table project.restaurantspecialities
(
    id            int auto_increment
        primary key,
    restaurant_id int null,
    speciality_id int null,
    constraint restaurant_id
        foreign key (restaurant_id) references project.restaurants (id),
    constraint speciality_id
        foreign key (speciality_id) references project.specialities (id)
);

create index restaurant_id_idx
    on project.restaurantspecialities (restaurant_id);

create index speciality_id_idx
    on project.restaurantspecialities (speciality_id);

