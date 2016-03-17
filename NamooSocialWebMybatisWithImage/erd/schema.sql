drop table if exists user_tb restrict;
drop table if exists message_tb restrict;
drop table if exists usertouser_tb restrict;

create table user_tb (
	userid   varchar(30) not null,
	name     varchar(30) not null,
	email    varchar(50) not null,
	password varchar(30) not null,
	introduction varchar(300) null,
	region       varchar(50)  null,
	homepage     varchar(80)  null,
	img_file     varchar(100) null,
	img_type     varchar(50)  null 
);

alter table user_tb
	add constraint pk_user_tb
		primary key (
			userid
		);

create table message_tb (
	msg_no   integer      not null,
	contents varchar(500) not null,
	writer   varchar(30)  not null,
	reg_dt   timestamp    null
);

alter table message_tb
	add constraint pk_message_tb
		primary key (
			msg_no
		);

alter table message_tb
	modify column msg_no integer not null auto_increment;

create table usertouser_tb (
	who  varchar(30) not null,
	whom varchar(30) not null
);

alter table usertouser_tb
	add constraint pk_usertouser_tb
		primary key (
			who,
			whom
		);

alter table message_tb
	add constraint fk_user_tb_to_message_tb
		foreign key (
			writer
		)
		references user_tb (
			userid
		);