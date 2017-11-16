# --- !Ups

DROP TABLE IF EXISTS "public"."Task";

CREATE TABLE "public"."Task" (
	"id" bigserial NOT NULL,
	"title" varchar(255) NOT NULL,
	"description" text NOT NULL,
	"status" varchar(50) NOT NULL,
	PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE
)

# --- !Downs