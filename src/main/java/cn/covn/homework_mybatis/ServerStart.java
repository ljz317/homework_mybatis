package cn.covn.homework_mybatis;

import cn.covn.homework_mybatis.controller.Server;
import cn.covn.homework_mybatis.repository.dao.ClassDao;
import cn.covn.homework_mybatis.repository.dao.StudentDao;
import cn.covn.homework_mybatis.repository.dao.TeacherDao;
import cn.covn.homework_mybatis.repository.dao.UserDao;
import cn.covn.homework_mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * SchoolSystemServer
 * 単独で起動してください
 */
@SpringBootApplication(scanBasePackages = "cn.covn.homework_mybatis")
public class ServerStart implements ApplicationRunner {
    private static Scanner scanner;
    @Autowired
    UserService userService;
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    ClassDao classDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private Server server;

    static{
        scanner= new Scanner(System.in);
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerStart.class, args);
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("准备开始=============");

        server.createClient(userService,userDao,studentDao,teacherDao,classDao);
        Thread thread=new Thread(server);
        thread.start();

        //start();
    }
}
