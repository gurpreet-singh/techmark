DROP SEQUENCE IF EXISTS seq_post;

CREATE SEQUENCE seq_post
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;
COMMIT;