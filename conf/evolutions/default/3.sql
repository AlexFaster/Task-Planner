# --- !Ups

ALTER TABLE "public"."Task" ADD COLUMN IF NOT EXISTS "userId" INTEGER;
ALTER TABLE "public"."Task" ADD CONSTRAINT fk_task_user FOREIGN KEY ("userId") REFERENCES "public"."User"(id);

# --- !Downs