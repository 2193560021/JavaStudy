create database atguigu;


drop table if exists t_emp ;
create table t_emp
(
    emp_id int auto_increment PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    emp_salary DOUBLE(10,5) NOT NULL,
    emp_age int NOT NULL
)character set utf8;
INSERT INTO `t_emp` (`emp_id`, `emp_name`, `emp_salary`, `emp_age`) VALUES (1, 'andy', 777.77000, 32);
INSERT INTO `t_emp` (`emp_id`, `emp_name`, `emp_salary`, `emp_age`) VALUES (2, '大风哥', 111.00000, 23);
INSERT INTO `t_emp` (`emp_id`, `emp_name`, `emp_salary`, `emp_age`) VALUES (3, 'GAVIN', 123.00000, 23);
INSERT INTO `t_emp` (`emp_id`, `emp_name`, `emp_salary`, `emp_age`) VALUES (4, '小鱼儿', 123.00000, 28);

select * from t_emp;

select emp_id empId,emp_name empName,emp_salary empSalary,emp_age empAge  from t_emp;

select * from t_emp where emp_id = 1;
select * from t_emp where emp_age > 25;

select count(*) from t_emp;

INSERT INTO t_emp(emp_name, emp_salary, emp_age) values ();

update t_emp set emp_salary = 2333 where emp_name = '刘璇';

delete from t_emp where emp_id = 2 ;


set autocommit = false;


drop table if exists t_bank ;
create table t_bank(
    id int primary key auto_increment ,
    account varchar(20) not null unique ,
    money int unsigned
)character set utf8;

insert into `t_bank`(account, money) values ('zhangsan',1000),('lisi',1000);

select * from t_bank;

update t_bank set money = money - 100 where id = 1;

update t_bank set money = money + 100 where id = 2;

commit ;
rollback ;