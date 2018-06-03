-- Project Name : wordnet
-- Date/Time    : 2018/04/17 11:42:58
-- Author       : 003418
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

-- galley_comment
drop table if exists "galley_comment" cascade;

create table "galley_comment" (
  "id" serial not null
  , "gallery_id" uuid not null
  , "user_id" uuid not null
  , "img_file_name" text not null
  , "memo" text not null
  , "to_user_id" uuid
  , "created" time with time zone not null
  , "modified" time with time zone not null
) ;

-- gallery
drop table if exists "gallery" cascade;

create table "gallery" (
  "id" uuid not null
  , "comment" text
  , "user_id" uuid not null
  , "created" time with time zone not null
  , "modified" time with time zone not null
) ;

-- user
drop table if exists "user" cascade;

create table "user" (
  "id" uuid
  , "username" varchar(150) not null
  , "password" varchar(250) not null
  , "email" varchar(250) not null
  , "sex" varchar(1)
  , "avatar" varchar(250)
  , "nickname" varchar(250)
  , "introduction" varchar(250)
  , "withdrawal" boolean default false
  , "created" timestamp with time zone not null
  , "modified" timestamp with time zone not null
  , constraint "user_PKC" primary key ("id")
) ;

comment on table "galley_comment" is 'galley_comment';
comment on column "galley_comment"."id" is 'id';
comment on column "galley_comment"."gallery_id" is 'gallery_id';
comment on column "galley_comment"."user_id" is '作成者';
comment on column "galley_comment"."img_file_name" is 'image';
comment on column "galley_comment"."memo" is 'メモ';
comment on column "galley_comment"."to_user_id" is '返信者';
comment on column "galley_comment"."created" is '作成日時';
comment on column "galley_comment"."modified" is '更新日時';

comment on table "gallery" is 'gallery';
comment on column "gallery"."id" is 'id';
comment on column "gallery"."comment" is 'commet';
comment on column "gallery"."user_id" is '作成者';
comment on column "gallery"."created" is '作成日時';
comment on column "gallery"."modified" is '更新日時';

comment on table "user" is 'user';
comment on column "user"."id" is 'ID';
comment on column "user"."username" is 'ユーザー名';
comment on column "user"."password" is 'パスワード';
comment on column "user"."email" is 'メール';
comment on column "user"."sex" is '性別:1男２女';
comment on column "user"."avatar" is 'アバター';
comment on column "user"."nickname" is 'ニックネーム';
comment on column "user"."introduction" is '自己紹介';
comment on column "user"."withdrawal" is '退会';
comment on column "user"."created" is '作成日時';
comment on column "user"."modified" is '更新日時';
