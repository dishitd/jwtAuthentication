-- DROP SCHEMA pms;

CREATE SCHEMA pms AUTHORIZATION postgres;

CREATE ROLE "localUser" NOSUPERUSER NOCREATEDB CREATEROLE NOINHERIT LOGIN PASSWORD 'localUser';
GRANT CREATE ON SCHEMA pms TO "localUser";
GRANT USAGE ON SCHEMA pms TO "localUser";

GRANT ALL ON TABLE pms.user_profile TO "localUser";
GRANT ALL ON TABLE pms."user" TO "localUser";

CREATE TABLE pms."user" (
	id smallint NOT NULL GENERATED ALWAYS AS IDENTITY,
	username varchar NOT NULL,
	"password" varchar NOT NULL
);

ALTER TABLE pms."user" ADD CONSTRAINT user_pk PRIMARY KEY (id);
