package cn.covn.homework_mybatis;

import cn.covn.homework_mybatis.dao.ClassDao;
import cn.covn.homework_mybatis.dao.StudentDao;
import cn.covn.homework_mybatis.dao.TeacherDao;
import cn.covn.homework_mybatis.dao.UserDao;
import cn.covn.homework_mybatis.pojo.entity.Student;
import cn.covn.homework_mybatis.pojo.entity.Teacher;
import cn.covn.homework_mybatis.pojo.entity.User;
import cn.covn.homework_mybatis.pojo.vo.Score;
import cn.covn.homework_mybatis.roles.Role;
import cn.covn.homework_mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@SpringBootApplication(scanBasePackages = "cn.covn.homework_mybatis")
public class HomeworkMybatisApplication implements ApplicationRunner {
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

    static{
        scanner= new Scanner(System.in);
    }

    public void start()
    {
        while (true){
            System.out.println("================================================");
            System.out.println("===========welcome SchoolSystem=================");
            System.out.println("==========Teacher username:root=================");
            System.out.println("==========Teacher password:root=================");
            System.out.println("==========Student username:xuesheng=============");
            System.out.println("==========Student password:xuesheng=============");
            System.out.println("================================================");
            System.out.println("================================================");


            System.out.println("【1】ログイン　【2】登録");
            String selection=scanner.nextLine();


            if("1".equals(selection)){
                //获取账号密码
                System.out.println("ユーザー名を入力してください：");
                String username=scanner.nextLine();
                System.out.println("パスワードを入力してください：");
                String password=scanner.nextLine();
                //判断是否正确
                User user = userService.Login(username, password);
                if(user!=null){
                    System.out.println(user.getRole().toLowerCase()+"------------");
                    Role role= user.getRole().toLowerCase().equals("teacher")?Role.Teacher:Role.Student;
                    String viewRole=user.getRole().toLowerCase().equals("student")?"学生":"先生";
                    System.out.println(viewRole+": "+user.getUsername()+ "さん、ようこそ");
                    if("先生".equals(viewRole)){
                        while(true){
                            System.out.println("【0】查看所有学生成绩,【1】查看学生成绩，【2】修改学生成绩，【3】查看个人资料，【4】登出");
                            selection=scanner.nextLine();
                            if("0".equals(selection)){
                                studentDao.FindAllStudent().forEach(System.out::println);
                            }else if("1".equals(selection)){
                                System.out.println("请输入学生姓名:");
                                String studentName=scanner.nextLine();
                                Score studentScore=studentDao.getScoreByName(studentName);
                                if(studentScore!=null){
                                    System.out.println(studentName+": "+studentScore);
                                }else{
                                    System.out.println("没查到");
                                }
                            }else if("2".equals(selection)){
                                System.out.println("请输入学生姓名:");
                                String studentName=scanner.nextLine();
                                System.out.println("请输入分数，顺序为，语文，数学，英语,用空格分割");
                                String studentScore=scanner.nextLine();
                                Score score=new Score();

                                score.setChinese(Double.valueOf(studentScore.split(" ")[0]));
                                score.setMath(Double.valueOf(studentScore.split(" ")[1]));
                                score.setEnglish(Double.valueOf(studentScore.split(" ")[2]));

                                if(studentDao.updateScoreByName(studentName,score)<1){
                                    System.out.println("修改失败");
                                }else{
                                    System.out.println("修改成功");
                                }

                            }else if("3".equals(selection)){
                                System.out.println(teacherDao.getTeacherByUid(user.getId()));
                            }else if("4".equals(selection)){
                                break;
                            }
                        }
                    }else{
                        while(true){
                            System.out.println("【1】查看成绩,【2】登出");
                            selection=scanner.nextLine();
                            if("1".equals(selection)){
                                System.out.println(studentDao.getScoreById(user.getId()));
                            }else if("2".equals(selection)){
                                break;
                            }
                        }

                    }
                    continue;
                }
                System.out.println("入力内容に誤りがあります,再度入力をお願いいたします");

            } else if ("2".equals(selection)) {
                //注册
                System.out.println("役割を選んでください 【0】学生，【1】老师,【2】返回");
                selection=scanner.nextLine();
                if("0".equals(selection)){
                    System.out.println("请输入用户名");
                    String username=scanner.nextLine();
                    //判断username是否重复
                    if(userDao.isHaveUserByUsername(username)>0)
                    {
                        System.out.println("已存在用户名，请重新注册");
                        continue;
                    }
                    System.out.println("请输入密码");
                    String password=scanner.nextLine();
                    System.out.println("请输入班级");
                    String className=scanner.nextLine();
                    //判断是否有该班级
                    if(classDao.isHaveClass(className)<1)
                    {
                        System.out.println("班级不存在，请重新注册");
                        continue;
                    }
                    System.out.println("请输入名字");
                    String name=scanner.nextLine();
                    User createUser=new User();
                    createUser.setUsername(username);
                    createUser.setPassword(password);
                    createUser.setRole("student");
                    userDao.addUser(createUser);
                    Student student=new Student();
                    User u=userDao.CheckUser(username,password);
                    student.setUid(u.getId());
                    student.setName(name);
                    student.setClassName(className);
                    studentDao.addStudent(student);
                    System.out.println("注册成功");
                }else if("1".equals(selection)){
                    System.out.println("请输入用户名");
                    String username=scanner.nextLine();
                    //判断username是否重复
                    if(userDao.isHaveUserByUsername(username)>0)
                    {
                        System.out.println("已存在用户名，请重新注册");
                        continue;
                    }
                    System.out.println("请输入密码");
                    String password=scanner.nextLine();

                    System.out.println("请输入名字");
                    String name=scanner.nextLine();
                    System.out.println("请输入工资");
                    Double salary=scanner.nextDouble();
                    Teacher teacher=new Teacher();
                    User createUser=new User();
                    createUser.setUsername(username);
                    createUser.setPassword(password);
                    createUser.setRole("teacher");
                    userDao.addUser(createUser);
                    User u=userDao.CheckUser(username,password);
                    teacher.setUid(u.getId());
                    teacher.setName(name);
                    teacher.setSalary(salary);
                    teacherDao.addTeacher(teacher);
                    System.out.println("添加成功");

                }else if("2".equals(selection)){
                    continue;
                }
            }else{
                System.out.println("入力内容に誤りがあります。再度入力してください。");
                continue;
            }
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(HomeworkMybatisApplication.class, args);
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {


        Client client = new Client("127.0.0.1",23302);
        client.start();

        //start();
    }
}
