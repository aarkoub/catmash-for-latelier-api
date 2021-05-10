create table cat_match(
user_id uuid,
cat_id_1 int8,
cat_id_2 int8,
cat_id_voted int8,
primary key (user_id, cat_id_1, cat_id_2),
constraint fk_user_id
      foreign key(user_id)
	  references "user"(id),
constraint fk_cat_id_1
      foreign key(cat_id_1)
	  references cat(id),
constraint fk_cat_id_2
      foreign key(cat_id_2)
	  references cat(id),
constraint fk_cat_id_voted
      foreign key(cat_id_voted)
	  references cat(id)
);