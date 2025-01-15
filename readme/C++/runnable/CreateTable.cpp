#include <mysql/mysql.h>
#include <iostream>
#include "PropertiesRead.cpp"

using namespace std;
void insertString(MYSQL* conn,const char * ch) {

    // ÷¥––≤Â»Î”Ôæ‰
    if (mysql_query(conn, ch)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(conn));
    }
    else {
        printf("Record inserted successfully.\n");
    }
}
int main() {
    MYSQL* conn;
    MYSQL_RES* res;
    MYSQL_ROW row;


    Properties prop;
    prop.load("conf.properties");
    
    string url=prop.get("mysql.url");
    int port= stoi(prop.get("mysql.port"));
    string username= prop.get("mysql.username");
    string password= prop.get("mysql.password");


    conn = mysql_init(0);
    if (conn == NULL) {
        std::cerr << "mysql_init() failed\n";
        system("pause");
        return 1;
    }

    conn = mysql_real_connect(conn, url.c_str(), username.c_str(), username.c_str(), NULL, port, NULL, 0);
    if (conn == NULL) {
        std::cerr << "mysql_real_connect() failed\n";
        system("pause");
        return 1;
    }
    const char* create_db_query = "create database schoolsystem";
    if (mysql_query(conn, create_db_query)) {
        std::cerr << "CREATE DATABASE failed. Error: " << mysql_error(conn) << std::endl;
    }
    else {
        std::cout << "Database 'homework' created successfully!" << std::endl;
    }

    if (mysql_select_db(conn, "schoolsystem")) {
        fprintf(stderr, "=============mysql_select_db() failed. Error: %s\n", mysql_error(conn));
        system("pause");
        return 1;
    }
    else {
        printf("Switched to database 'homework_mybatis' successfully!\n");
        
        const char* create_table = "create table user(id int primary key auto_increment,username varchar(40),password varchar(40),role char(10) check(role = 'student' or role = 'teacher'))";
        if (mysql_query(conn, create_table)) {
            std::cerr << "CREATE table failed. Error: " << mysql_error(conn) << std::endl;
            system("pause");
            return 1;
        }
        else {
            std::cout << "table 'user' created successfully!" << std::endl;

            insertString(conn,"insert into user  values(null,'aoligei','aoligei','teacher')");
            insertString(conn,"insert into user  values(null,'xuesheng','xuesheng','student')");
            insertString(conn,"insert into user  values(null,'root','root','teacher')");
            insertString(conn,"insert into user  values(null,'xuesheng2','xuesheng2','student')");

            insertString(conn,"create table student (id int primary key auto_increment,\
                uid int,name varchar(20),class varchar(10) not null,chinese double,\
                math double,\
                english double); ");
            insertString(conn,"insert into student values(null,2,'xuesheng','c2302',98.0,97.0,96.0)");
            insertString(conn,"insert into student values(null,4,'xuesheng2','c2301',58.0,67.0,76.0)");
            insertString(conn,"create table teacher(id int primary key auto_increment,uid int, name varchar(20),salary double);");
            insertString(conn,"insert into teacher values(null,1,'aoligei',2000.0)");
            insertString(conn,"insert into teacher values(null,3,'root',4000.0)");
            insertString(conn,"create table class(id int primary key auto_increment,tid int,class varchar(10) unique not null)");
            insertString(conn,"insert into class values(null,1,'c2301')");
            insertString(conn,"insert into class values(null,2,'c2302')");
        }
    }
    
    std::printf("table create successful");


    system("pause");

    mysql_close(conn);




    return 0;
}