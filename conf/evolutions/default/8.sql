# --- !Ups

ALTER TABLE "user" DROP CONSTRAINT fk_user_account;
ALTER TABLE "task" DROP CONSTRAINT fk_task_user;


ALTER TABLE "user" ADD CONSTRAINT fk_user_account FOREIGN KEY ("accountid")
REFERENCES "account"(id);

ALTER TABLE "task" ADD CONSTRAINT fk_task_user FOREIGN KEY ("userid")
REFERENCES "user"(id);
