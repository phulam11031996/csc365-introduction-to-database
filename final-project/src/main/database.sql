select * from student;
select * from professor;
select * from course;
select * from grade;

drop table if exists grade;
drop table if exists course;
drop table if exists professor;
drop table if exists student;
drop table if exists major;

create table major(
    majorName varchar(255) primary key
);

create table student (
    sid int auto_increment,
    firstName varchar(255),
    lastName varchar(255),
    email varchar(255),
    dob date,
    major varchar(255),
    primary key (sid),
    foreign key (major) references major(majorName) on delete cascade on update no action
);

create table professor (
    pid int auto_increment,
    firstName varchar(255),
    lastName varchar(255),
    email varchar(255),
    dob date,
    major varchar(255),
    foreign key (major) references major(majorName) on delete cascade on update no action,
    primary key (pid)
);

create table course (
    cid int auto_increment,
    courseLabel varchar(255),
    courseName varchar(255),
    quarter enum("Fall", "Winter", "Spring", "Summer"),
    schoolYear int check(schoolyear BETWEEN 1000 AND 2030),
    pid int,
    primary key (cid),
    foreign key (pid) references professor(pid) on delete cascade on update no action
);

create table grade (
    sid int,
    cid int,
    grade enum("A", "B", "C", "D", "F"),
    primary key (sid, cid),
    foreign key (sid) references student(sid) on delete cascade on update no action,
    foreign key (cid) references course(cid) on delete cascade on update no action
);

insert into major (majorName) values ("Computer Science");
insert into major (majorName) values ("Software Engineer");
insert into major (majorName) values ("Hardware Engineer");
insert into major (majorName) values ("Information Technology");
insert into major (majorName) values ("Information Systems Security");
insert into major (majorName) values ("Computer Engineering");