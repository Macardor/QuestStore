create table user_types
(
    id        integer not null
        constraint "User_Type_pkey"
            primary key,
    user_type text    not null
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
    id          serial  not null
        constraint quests_pk
            primary key,
    name        text    not null,
    description text,
    reward      integer,
    is_active   boolean not null
);

alter table quests
    owner to postgres;

create table coincubator
(
    name             text    not null,
    description      text,
    current_donation integer,
    max_donation     integer,
    is_active        boolean not null,
    id               serial  not null
        constraint coincubator_pk
            primary key
);

alter table coincubator
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

create table equipment
(
    id      serial  not null
        constraint equipment_pk
            primary key,
    coins   integer,
    user_id integer not null
        constraint equipment_users_id_fk
            references users
);

alter table equipment
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
    equipment_id integer
        constraint user_items_equipment_id_fk
            references equipment
);

alter table user_items
    owner to postgres;

create table user_quests
(
    equipment_id    integer not null
        constraint user_quests_equipment_id_fk
            references equipment,
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
            references coincubator,
    user_id        integer,
    donation       integer,
    donation_date  date
);

alter table donators
    owner to postgres;


