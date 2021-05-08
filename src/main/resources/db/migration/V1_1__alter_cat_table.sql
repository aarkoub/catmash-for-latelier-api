drop table cat;
create table cat (
id serial not null,
nb_votes int4 not null,
picture_url varchar(255),
primary key (id)
)