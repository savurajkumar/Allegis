# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table app_user (
  id                        bigserial not null,
  first_name                varchar(255),
  middle_name               varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  password                  TEXT,
  salt                      TEXT,
  createdon                 timestamp not null,
  last_update               timestamp not null,
  constraint pk_app_user primary key (id))
;




# --- !Downs

drop table if exists app_user cascade;

