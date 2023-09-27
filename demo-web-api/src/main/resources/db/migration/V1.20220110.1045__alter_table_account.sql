alter table account
    add column first_name varchar(500) null;
alter table account
    add column last_name varchar(500) null;
alter table account
    add column is_active tinyint(1) default 1 null;
alter table account
    add column want_to_join_workshop tinyint(1) default 1 null;
alter table account
    add column roles varchar(500) null;
