insert into test.lecture (id, name, created_at, updated_at) values (1, '유리상자 콘서트', now(), now());
insert into test.lecture (id, name, created_at, updated_at) values (2, '황영웅 대전콘서트', now(), now());
insert into test.lecture (id, name, created_at, updated_at) values (3, '빌드업 콘서트', now(), now());

insert into test.lecture_detail (id, lecture_id, starts_at, created_at, updated_at) values (1, 1, '2024-02-15 13:00:00', now(), now());
insert into test.lecture_detail (id, lecture_id, starts_at, created_at, updated_at) values (2, 1, '2024-02-16 14:00:00', now(), now());
insert into test.lecture_detail (id, lecture_id, starts_at, created_at, updated_at) values (3, 1, '2024-02-17 15:00:00', now(), now());

insert into test.lecture_detail (id, lecture_id, starts_at, created_at, updated_at) values (4, 2, '2024-03-01 13:00:00', now(), now());
insert into test.lecture_detail (id, lecture_id, starts_at, created_at, updated_at) values (5, 2, '2024-03-02 14:00:00', now(), now());
insert into test.lecture_detail (id, lecture_id, starts_at, created_at, updated_at) values (6, 2, '2024-03-03 15:00:00', now(), now());

insert into test.lecture_detail (id, lecture_id, starts_at, created_at, updated_at) values (7, 3, '2024-03-22 13:00:00', now(), now());
insert into test.lecture_detail (id, lecture_id, starts_at, created_at, updated_at) values (8, 3, '2024-03-23 14:00:00', now(), now());
insert into test.lecture_detail (id, lecture_id, starts_at, created_at, updated_at) values (9, 3, '2024-03-24 15:00:00', now(), now());

insert into test.lecture_reserved_count (id, lecture_detail_id, count, created_at, updated_at) values (1, 1, 0, now(), now());
insert into test.lecture_reserved_count (id, lecture_detail_id, count, created_at, updated_at) values (2, 2, 0, now(), now());
insert into test.lecture_reserved_count (id, lecture_detail_id, count, created_at, updated_at) values (3, 3, 0, now(), now());
insert into test.lecture_reserved_count (id, lecture_detail_id, count, created_at, updated_at) values (4, 4, 0, now(), now());
insert into test.lecture_reserved_count (id, lecture_detail_id, count, created_at, updated_at) values (5, 5, 0, now(), now());
insert into test.lecture_reserved_count (id, lecture_detail_id, count, created_at, updated_at) values (6, 6, 0, now(), now());
insert into test.lecture_reserved_count (id, lecture_detail_id, count, created_at, updated_at) values (7, 7, 0, now(), now());
insert into test.lecture_reserved_count (id, lecture_detail_id, count, created_at, updated_at) values (8, 8, 0, now(), now());
insert into test.lecture_reserved_count (id, lecture_detail_id, count, created_at, updated_at) values (9, 9, 0, now(), now());