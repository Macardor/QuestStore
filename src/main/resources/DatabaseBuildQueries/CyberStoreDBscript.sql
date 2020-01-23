create table user_types
(
    id        serial not null
        constraint "User_Type_pkey"
            primary key,
    user_type text   not null
);

alter table user_types
    owner to postgres;

create table items
(
    id          serial  not null
        constraint items_pk
            primary key,
    name        text    not null,
    price       integer not null,
    description text,
    is_active   boolean
);

alter table items
    owner to postgres;

create table quests
(
    id          serial not null
        constraint quests_pk
            primary key,
    name        text   not null,
    description text,
    reward      integer,
    is_active   boolean
);

alter table quests
    owner to postgres;

create table coincubators
(
    name             text    not null,
    description      text,
    current_donation integer,
    target_donation  integer,
    is_active        boolean not null,
    id               serial  not null
        constraint coincubator_pk
            primary key
);

alter table coincubators
    owner to postgres;

create table user_details
(
    id         serial not null
        constraint user_details_pk
            primary key,
    login      text   not null,
    password   text   not null,
    first_name text,
    last_name  text
);

alter table user_details
    owner to postgres;

create table users
(
    id              serial  not null
        constraint users_pk
            primary key,
    user_type_id    integer not null
        constraint users_user_types_id_fk
            references user_types,
    is_active       boolean,
    user_details_id integer not null
        constraint users_user_details_id_fk
            references user_details
);

alter table users
    owner to postgres;

create table students
(
    id      serial  not null
        constraint equipment_pk
            primary key,
    coins   integer,
    user_id integer not null
        constraint equipment_users_id_fk
            references users
);

alter table students
    owner to postgres;

create table user_items
(
    id           serial  not null,
    item_id      integer not null
        constraint user_items_items_id_fk
            references items
        constraint user_items_items_id_fk_2
            references items,
    is_available boolean,
    bought_date  date,
    used_date    date,
    student_id   integer
        constraint user_items_equipment_id_fk
            references students
);

alter table user_items
    owner to postgres;

create table user_quests
(
    student_id      integer not null
        constraint user_quests_equipment_id_fk
            references students,
    quest_id        integer not null
        constraint user_quests_quests_id_fk
            references quests,
    is_available    boolean,
    completion_date date,
    id              serial  not null
);

alter table user_quests
    owner to postgres;

create table donators
(
    id             serial not null,
    coincubator_id integer
        constraint donators_coincubator_id_fk
            references coincubators,
    user_id        integer
        constraint donators_users_id_fk
            references users,
    donation       integer,
    donation_date  date
);

alter table donators
    owner to postgres;

create table mentors
(
    id      serial not null,
    user_id integer
        constraint mentors_users_id_fk
            references users
        constraint mentors_users_id_fk_2
            references users
);

alter table mentors
    owner to postgres;

create table creeps
(
    id      serial not null,
    user_id integer
        constraint creeps_users_id_fk
            references users
);

alter table creeps
    owner to postgres;

create table cookies
(
    id          serial not null
        constraint cookies_pk
            primary key,
    sesion_id   text,
    expire_date date,
    user_id     integer
        constraint cookies_users_id_fk
            references users
);

alter table cookies
    owner to postgres;

