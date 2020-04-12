-- DROP SCHEMA pms;

CREATE SCHEMA pms AUTHORIZATION postgres;

CREATE ROLE "localUser" NOSUPERUSER NOCREATEDB CREATEROLE NOINHERIT LOGIN PASSWORD 'localUser';
GRANT CREATE ON SCHEMA pms TO "localUser";
GRANT USAGE ON SCHEMA pms TO "localUser";
ß
ALTER ROLE "localUser" NOSUPERUSER NOCREATEDB CREATEROLE NOINHERIT LOGIN;

CREATE TABLE pms."user" (
	id smallint NOT NULL GENERATED ALWAYS AS IDENTITY,
	username varchar NOT NULL,
	"password" varchar NOT NULL
);

ALTER TABLE pms."user" ADD CONSTRAINT user_pk PRIMARY KEY (id);

CREATE TABLE pms.user_profile (
	user_id smallint NOT NULL,
	"role" varchar NOT NULL,
	CONSTRAINT user_profile_fk FOREIGN KEY (user_id) REFERENCES pms."user"(id) ON DELETE CASCADE
);

-- Column comments

COMMENT ON COLUMN pms.user_profile."role" IS 'user role such as admin, user';

