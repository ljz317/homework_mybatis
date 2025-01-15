------

```c
1.Maradb、JDK、Springのバージョンを確認すること ,MariaDBをインストールすること
2.これから使うプロジェクトはMySQL DriverとMabatis　Driver使っています
3.homework_mybatis_dir\C++\runnableの下にあるconf.propertiesの配置を確かめること
4.homework_mybatis_dir\Homework_mybatis\homework_mybatis\src\main\resources
	下にあるapplication.propertiesの配置も確かめること
5.プロジェクトを起動する前に必ずデータベースの構築は終わってからやること 
    MariaDBをインストールしたrun.datクリックだけで済む
    あるいはZ_createTableSQL.txt中のSQL言語を実行する
    
6.起動
	
```

```c++
1.确认 Maradb、JDK 和 Spring 的版本。
2.这个项目将使用 MySQL 驱动和 Mybatis 驱动。
3.检查 homework_mybatis_dir\C++\runnable 下的 conf.properties 配置文件是否正确。
4.检查 homework_mybatis_dir\Homework_mybatis\homework_mybatis\src\main\resources 		下的 application.properties 配置文件是否正确。
5.在启动项目之前，务必先完成数据库的构建。run.dat
```

```

create database schoolsystem;

create table user(id int primary key auto_increment,username varchar(40),password varchar(40),ro
le char(10) check(role='student' or role='teacher'));

insert into user  values(null,'aoligei','aoligei','teacher');
insert into user  values(null,'xuesheng','xuesheng','student');
insert into user  values(null,'root','root','teacher');
insert into user  values(null,'xuesheng2','xuesheng2','student');

create table student (
    id int primary key auto_increment,
    uid int,
    name varchar(20),
    class varchar(10) not null,
    chinese double,
    math double,
    english double);

insert into student values(null,2,'xuesheng','c2302',98.0,97.0,96.0);
insert into student values(null,4,'xuesheng2','c2301',58.0,67.0,76.0);

create table teacher(id int primary key auto_increment,uid int, name varchar(20),salary double);
insert into teacher values(null,1,'aoligei',2000.0);
insert into teacher values(null,3,'root',4000.0);

create table class(id int primary key auto_increment,tid int,class varchar(10) unique not null);
insert into class(null,1,'c2301');
insert into class(null,2,'c2302');

```

