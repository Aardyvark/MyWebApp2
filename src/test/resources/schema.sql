create schema publica authorization dba
create memory table events(event_id integer, event_date datetime, title varchar(255))
create user sa password ""
grant dba to sa;

  