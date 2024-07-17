use test_sql;

drop table if exists students ;
create table students(
                         `stu_id` int primary key,
                         `stu_name` varchar(100) not null ,
                         `stu_age` int not null
) character set utf8;

select * from students;

insert into students(stu_id, stu_name, stu_age) values (230321165,'刘宇阳',22);
insert into students(stu_id, stu_name, stu_age) values (230321167,'张嘉祺',23);


select * from students order by  stu_age desc ,stu_name desc;

select avg(stu_age) from students;

alter table students add stu_enrollment datetime;

insert into students(stu_id, stu_name, stu_age, stu_enrollment) values (230321168,'刘璇',23,'2023/09/01');
insert into students(stu_id, stu_name, stu_age, stu_enrollment,stu_gender) values (230321169,'王五',23,'2023/09/01','女');

update students set stu_enrollment = now() where stu_id = 230321165;

select concat(substring(stu_id,7,3),'-',stu_name) from students;


alter table students add stu_gender varchar(20);

update students set stu_gender = '女' where stu_id = 230321168;

select * from students;

select students.stu_gender,count(students.stu_name) as shuliang from students group by students.stu_gender;

select * from students limit 0,3;

drop table if exists classes ;
create table classes(
                        class_id int primary key auto_increment,
                        class_name varchar(40) not null unique ,
                        class_remark varchar(200)
) character set utf8;

insert into classes(class_name, class_remark) values('Java2401','.......');
insert into classes(class_name, class_remark) values('Java2402','.......');
insert into classes(class_name, class_remark) values('Java2403','.......');
insert into classes(class_name, class_remark) values('Java2404','.......');
insert into classes(class_name, class_remark) values('Java2405','.......');
insert into classes(class_name, class_remark) values('Java2406','.......');
insert into classes(class_name, class_remark) values('Java2407','.......');

drop table if exists kids;
create table kids(
                     kid_id char(8) primary key,
                     kid_name varchar(20) not null ,
                     kid_gender char(2) not null ,
                     kid_age int not null ,
                     c_id int not null,
                     constraint FK_kids_classes foreign key (c_id) references classes(class_id)
) character set utf8;

insert into kids(kid_id, kid_name, kid_gender, kid_age, c_id) values('04193013','FJW','男',24,1);
insert into kids(kid_id, kid_name, kid_gender, kid_age, c_id) values('04193014','ZJQ','男',22,2);
insert into kids(kid_id, kid_name, kid_gender, kid_age, c_id) values('04193015','ZRP','男',25,3);
insert into kids(kid_id, kid_name, kid_gender, kid_age, c_id) values('04193016','LYY','男',22,4);
insert into kids(kid_id, kid_name, kid_gender, kid_age, c_id) values('04193017','XZL','男',24,5);
insert into kids(kid_id, kid_name, kid_gender, kid_age, c_id) values('04193018','QPY','男',24,6);
insert into kids(kid_id, kid_name, kid_gender, kid_age, c_id) values('04193026','LX','女',23,7);

alter table kids drop foreign key FK_KIDS_CLASSES;

alter table kids add constraint FK_KIDS_CLASSES
    foreign key(c_id) references classes(class_id)
        on update cascade on delete cascade ;

update classes set class_id = 8 where class_name='Java2404';


#创建存储过程
create procedure proc_test1(in A int,in B int,out SUM int)
begin
    # set 定义变量
    set SUM = A + B;
end;

# 调用存储过程
set @m = 0;
call proc_test1(3,2,@m);
select @m from dual;


#创建存储过程
create procedure proc_test2(in a int,out r int)
begin
    # set 定义变量
    declare x int default 0;
    declare y int default 1;
    set x = a*a;
    set y = a/2;
    set r = x+y;
end;

set @n = 1;
call proc_test2(6,@n);
# 用户变量是通过set进行定义，同时要以@开头
# 存储在MySQL的数据字典中

select @n from dual;

drop procedure if exists proc_test3;
create procedure proc_test3(in snum char(8),in sname varchar(20),
                            in gender char(2) character set utf8,in age int,in cid int)
begin
insert into kids(kid_id, kid_name, kid_gender, kid_age, c_id)
VALUES(snum,sname,gender,age,cid);
end;

call proc_test3('20240605','LYYYYY','女',20,8);
select @n from dual;

drop procedure if exists proc_test4;
create procedure proc_test4(in snum char(9),out sname varchar(20))
begin
select stu_name into sname from students where stu_id = snum;
end;

set @name = '';
call proc_test4('230321165',@name);
select @name from dual;

ALTER TABLE `classes` CONVERT TO CHARACTER SET  utf8mb4;


select stu_name  from students where stu_id = '230321165';

create procedure proc_test5(inout str varchar(20))
begin
select stu_name into str from students where stu_id = str;
end;

set @name1 = '230321165';
call proc_test5(@name1);
select @name1 from dual;

drop procedure if exists proc_test6;
create procedure proc_test6(in a int)
begin
    if a = 1 then
        insert into classes(class_name, class_remark) values ('Java2410','测试');
end if;
end;

call proc_test6(1);



drop procedure if exists proc_test7;
create procedure proc_test7(in a int)
begin
    if a = 1 then
        insert into classes(class_name, class_remark) values ('Java2411','测试2');
else
        insert into students(stu_id, stu_name, stu_age) values ('230321166','LSC','23');
end if;
end;

call proc_test7(6);

drop procedure if exists proc_test8;
create procedure proc_test8(in num int)
begin
    declare i int;
    set i = 0;
    while i < num do
        insert into classes(class_name, class_remark) values (concat('java',i),'...');
        set i = i + 1;
end while;
end;

call proc_test8(3);

show procedure status ;

create procedure proc_test12(out result varchar(200))
begin
    # 定义游标变量
    declare cid int;
    declare cname varchar(20);
    #计数变量
    declare num int;
    declare  i int;
    declare str varchar(100);
    # 定义游标
    declare myCursor cursor for
select classes.class_id,classes.class_name from classes;
select count(*) into num from classes;

open myCursor;
set i = 0;

    #开始遍历
while i < num do
        fetch myCursor into cid,cname;
        set i = i+1;
#         set str = concat_ws('~',cid,cname);
select concat_ws('~',cid,cname)into str;
set result = concat_ws(',',result,str);
end while;

    #关闭游标
close myCursor;
end;

set @r = '';
call proc_test12(@r);
select @r from dual;

create table stulogs(
                        id int primary key auto_increment,
                        time timestamp,
                        log_text varchar(200)
) character set utf8;

insert into kids(kid_id, kid_name, kid_gender, kid_age, c_id)
values('20240716','刘璇','女',20,2);

#手动进行日志的记录
insert into stulogs(time, log_text) values (now(),'添加20240716学生信息');

drop trigger if exists tri_test1;
create trigger tri_test1
    after insert on kids
    for each row
    insert into stulogs(time, log_text) values (now(),concat('添加',new.kid_id,'学生信息'));

show triggers ;

insert into kids(kid_id, kid_name, kid_gender, kid_age, c_id)
values('20240768','刘小璇2','女',23,2);

drop trigger if exists tri_test2;
create trigger tri_test2
    after update on kids
    for each row
    insert into stulogs(time, log_text)
    values(now(),concat(old.kid_name,'的学生信息被修改为：',concat_ws('-',new.kid_id,new.kid_name)));

update kids set kid_name = '刘刘璇璇' where kid_id = '20240769';

create view v_test1
as
select *
from kids where kid_gender = '男';

select * from v_test1;

create view v_test2
as
select  kid_id, kid_name, kid_gender, kid_age,c.class_name
from kids k left join classes c on k.c_id = c.class_id where c.class_id = 2;

select * from v_test2;