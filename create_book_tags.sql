drop table if exists book_tags;

create table book_tags(
	isbn_13 varchar(13) references books(isbn_13),
	tag_name varchar(100),
	primary key(isbn_13,tag_name)
);

INSERT INTO book_tags
        (isbn_13, tag_name)
		VALUES
		('1111111111111', 'fiction');