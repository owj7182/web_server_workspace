create table student (
    id number
    , name varchar2(100)
    , constraint pk_student_id primary key(id)
);
create sequence seq_student_id;

insert into student values(seq_student_id.nextval, '고혜진');
insert into student values(seq_student_id.nextval, '김명준');
insert into student values(seq_student_id.nextval, '김정효');
insert into student values(seq_student_id.nextval, '성민준');
insert into student values(seq_student_id.nextval, '오승현');
insert into student values(seq_student_id.nextval, '오우진');
insert into student values(seq_student_id.nextval, '유정호');
insert into student values(seq_student_id.nextval, '이민정');
insert into student values(seq_student_id.nextval, '이재준');
insert into student values(seq_student_id.nextval, '임초임');
insert into student values(seq_student_id.nextval, '정승범');
insert into student values(seq_student_id.nextval, '정용준');
insert into student values(seq_student_id.nextval, '정진우');
insert into student values(seq_student_id.nextval, '천무진');
insert into student values(seq_student_id.nextval, '한보경');
insert into student values(seq_student_id.nextval, '한승훈');
insert into student values(seq_student_id.nextval, '한준희');

commit;

select * from student;

-- celeb 테이블
create table celeb(
    id number
    , name varchar2(100) not null
    , profile varchar2(200) default 'default.png'
    , type varchar2(50) -- ACTOR, MODEL, SINGER, ...
    , constraint pk_celeb_id primary key(id)
    , constraint ck_celeb_type check(type in ('ACTOR', 'SINGER', 'MODEL', 'ENTERTAINER', 'COMEDIAN'))
);
create sequence seq_celeb_id;



