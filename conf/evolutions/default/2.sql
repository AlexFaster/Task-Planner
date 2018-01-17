# --- !Ups


DROP TABLE IF EXISTS "public"."User";

CREATE TABLE "public"."User" (
	"id" bigserial NOT NULL,
	"name" varchar(255),
	"age" int,
	"accountId" bigint,
	PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE
);


DROP TABLE IF EXISTS "public"."Account";

CREATE TABLE "public"."Account" (
	"id" bigserial NOT NULL,
	"login" varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE
);

ALTER TABLE "public"."User" ADD CONSTRAINT fk_user_account FOREIGN KEY ("accountId") REFERENCES "public"."Account"(id);

# --- !Downs