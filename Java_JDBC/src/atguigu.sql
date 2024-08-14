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