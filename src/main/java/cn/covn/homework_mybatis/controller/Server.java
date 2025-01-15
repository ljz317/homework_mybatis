package cn.covn.homework_mybatis.controller;

import cn.covn.homework_mybatis.model.pojo.vo.Daos;
import cn.covn.homework_mybatis.repository.dao.ClassDao;
import cn.covn.homework_mybatis.repository.dao.StudentDao;
import cn.covn.homework_mybatis.repository.dao.TeacherDao;
import cn.covn.homework_mybatis.repository.dao.UserDao;
import cn.covn.homework_mybatis.model.pojo.entity.Student;
import cn.covn.homework_mybatis.model.pojo.entity.Teacher;
import cn.covn.homework_mybatis.model.pojo.entity.User;
import cn.covn.homework_mybatis.model.pojo.vo.Score;
import cn.covn.homework_mybatis.model.roles.Role;
import cn.covn.homework_mybatis.service.UserService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class ServiceClent implements Runnable
{
    Socket socket=null;
    BufferedReader br=null;
    PrintWriter pw=null;
    Scanner scanner=new Scanner(System.in);

    Daos daos=null;

    ServiceClent(Daos daos)
    {
        this.daos=daos;
    }
    public void setSocket(Socket socket)
    {
        this.socket=socket;
    }
    public void sendMes(String mes)
    {
        if(br==null||pw==null)
        {
            System.out.println("br pw is null+!!!!!!!!!!!!!!");
            return;
        }
        synchronized(Server.class)
        {
            pw.println(mes);
            pw.flush();
        }
    }
    public String readMes() throws Exception {
        if(br==null||pw==null)
        {
            System.out.println("pw br == null");
            return null;
        }

        String str=null;
        try{
            while(str==null)
            {
                str=br.readLine();
            }
        } catch (IOException e) {
            throw new Exception(e);
        }

        return str;
    }

    @Override
    public void run() {
        try {
            br=new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            pw=new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(socket.getRemoteSocketAddress());
        try{
            while (true){
                System.out.println("================================================");
                System.out.println("===========welcome SchoolSystem=================");
                System.out.println("==========Teacher username:root=================");
                System.out.println("==========Teacher password:root=================");
                System.out.println("==========Student username:xuesheng=============");
                System.out.println("==========Student password:xuesheng=============");
                System.out.println("================================================");
                System.out.println("================================================");
                sendMes("================================================");
                sendMes("===========welcome SchoolSystem=================");
                sendMes("==========Teacher username:root=================");
                sendMes("==========Teacher password:root=================");
                sendMes("==========Student username:xuesheng=============");
                sendMes("==========Student password:xuesheng=============");
                sendMes("================================================");
                sendMes("================================================");

                System.out.println("【1】ログイン　【2】登録");
                sendMes("【1】ログイン　【2】登録");
                String selection=readMes();


                if("1".equals(selection)){
                    //获取账号密码
                    System.out.println("ユーザー名を入力してください：");
                    sendMes("ユーザー名を入力してください：");
                    String username=readMes();
                    System.out.println("パスワードを入力してください：");
                    sendMes("パスワードを入力してください：");
                    String password=readMes();
                    //判断是否正确
                    User user = daos.getUserService().Login(username, password);
                    if(user!=null){
                        System.out.println(user.getRole().toLowerCase()+"------------");
                        Role role= user.getRole().toLowerCase().equals("teacher")?Role.Teacher:Role.Student;
                        String viewRole=user.getRole().toLowerCase().equals("student")?"学生":"先生";
                        System.out.println(viewRole+": "+user.getUsername()+ "さん、ようこそ");
                        sendMes(viewRole+": "+user.getUsername()+ "さん、ようこそ");
                        if("先生".equals(viewRole)){
                            while(true){
                                System.out.println("【0】すべての学生の名前,【1】学生の名前で成績を調べる，【2】学生の名前で成績を直す，【3】自分のデータ，【4】ログアウト");
                                sendMes("【0】すべての学生の名前,【1】学生の名前で成績を調べる，【2】学生の名前で成績を直す，【3】自分のデータ，【4】ログアウト");
                                selection=readMes();
                                if("0".equals(selection)){
                                    daos.getStudentDao().FindAllStudent().forEach(e->sendMes(e));
                                }else if("1".equals(selection)){
                                    System.out.println("学生の名前を入力してく打差:");
                                    sendMes("学生の名前を入力してく打差:");
                                    String studentName=readMes();
                                    Score studentScore=daos.getStudentDao().getScoreByName(studentName);
                                    if(studentScore!=null){
                                        System.out.println(studentName+": "+studentScore);
                                        sendMes(studentName+": "+studentScore);
                                    }else{
                                        System.out.println("データが存在しません");
                                        sendMes("データが存在しません");
                                    }
                                }else if("2".equals(selection)){
                                    System.out.println("学生の名前を入力してく打差:");
                                    sendMes("学生の名前を入力してく打差:");
                                    String studentName=readMes();
                                    System.out.println("直したい成績入力してください、順番が語学、数学、英語、スペースで分割してください");
                                    sendMes("直したい成績入力してください、順番が語学、数学、英語、スペースで分割してください");
                                    String studentScore=readMes();
                                    Score score=new Score();
                                    if(studentScore.split(" ").length<3)
                                    {
                                        System.out.println("入力内容に誤りがあります,再度入力をお願いいたします");
                                        sendMes("入力内容に誤りがあります,再度入力をお願いいたします");
                                        continue;
                                    }
                                    score.setChinese(Double.valueOf(studentScore.split(" ")[0]));
                                    score.setMath(Double.valueOf(studentScore.split(" ")[1]));
                                    score.setEnglish(Double.valueOf(studentScore.split(" ")[2]));

                                    if(daos.getStudentDao().updateScoreByName(studentName,score)<1){
                                        System.out.println("何か間違いがあります、もう一度入力してください");
                                        sendMes("何か間違いがあります、もう一度入力してください");
                                    }else{
                                        System.out.println("変更が成功しました");
                                        sendMes("変更が成功しました");
                                    }

                                }else if("3".equals(selection)){
                                    System.out.println(daos.getTeacherDao().getTeacherByUid(user.getId()));
                                    sendMes(String.valueOf(daos.getTeacherDao().getTeacherByUid(user.getId())));
                                }else if("4".equals(selection)){
                                    break;
                                }
                            }
                        }else{
                            while(true){
                                System.out.println("【1】自分の成績を見る,【2】ログアウト");
                                sendMes("【1】自分の成績を見る,【2】ログアウト");
                                selection=readMes();
                                if("1".equals(selection)){
                                    System.out.println(daos.getStudentDao().getScoreById(user.getId()));
                                    sendMes(String.valueOf(daos.getStudentDao().getScoreById(user.getId())));
                                }else if("2".equals(selection)){
                                    break;
                                }
                            }

                        }
                        continue;
                    }
                    System.out.println("入力内容に誤りがあります,再度入力をお願いいたします");
                    sendMes("入力内容に誤りがあります,再度入力をお願いいたします");

                } else if ("2".equals(selection)) {
                    //注册
                    System.out.println("役割を選んでください 【0】学生，【1】老师,【2】返回");
                    sendMes("役割を選んでください 【0】学生，【1】老师,【2】返回");
                    selection=readMes();
                    if("0".equals(selection)){
                        System.out.println("ユーザー名を入力してください");
                        sendMes("ユーザー名を入力してください");
                        String username=readMes();
                        //判断username是否重复
                        if(daos.getUserDao().isHaveUserByUsername(username)>0)
                        {
                            System.out.println("ユーザー名は既に存在します。別のユーザー名で登録してください。");
                            sendMes("ユーザー名は既に存在します。別のユーザー名で登録してください。");
                            continue;
                        }
                        System.out.println("パスワードを入力してください");
                        sendMes("パスワードを入力してください");
                        String password=readMes();
                        System.out.println("クラス名を入力してください");
                        sendMes("クラス名を入力してください");
                        String className=readMes();
                        //判断是否有该班级
                        if(daos.getClassDao().isHaveClass(className)<1)
                        {
                            System.out.println("クラス名が存在しません");
                            sendMes("クラス名が存在しません");
                            continue;
                        }
                        System.out.println("名前を入力してください");
                        sendMes("名前を入力してください");
                        String name=readMes();
                        User createUser=new User();
                        createUser.setUsername(username);
                        createUser.setPassword(password);
                        createUser.setRole("student");
                        daos.getUserDao().addUser(createUser);
                        Student student=new Student();
                        User u=daos.getUserDao().CheckUser(username,password);
                        student.setUid(u.getId());
                        student.setName(name);
                        student.setClassName(className);
                        daos.getStudentDao().addStudent(student);
                        System.out.println("登録が成功しました。");
                        sendMes("登録が成功しました。");
                    }else if("1".equals(selection)){
                        System.out.println("ユーザー名を入力してください");
                        sendMes("ユーザー名を入力してください");
                        String username=readMes();
                        //判断username是否重复
                        if(daos.getUserDao().isHaveUserByUsername(username)>0)
                        {
                            System.out.println("ユーザー名は既に存在します。別のユーザー名で登録してください。");
                            sendMes("ユーザー名は既に存在します。別のユーザー名で登録してください。");
                            continue;
                        }
                        System.out.println("パスワードを入力してください");
                        sendMes("パスワードを入力してください");
                        String password=readMes();

                        System.out.println("名前を入力してください");
                        sendMes("名前を入力してください");
                        String name=readMes();
                        System.out.println("給与を入力してください");
                        sendMes("給与を入力してください");
                        Double salary=Double.parseDouble(readMes());
                        Teacher teacher=new Teacher();
                        User createUser=new User();
                        createUser.setUsername(username);
                        createUser.setPassword(password);
                        createUser.setRole("teacher");
                        daos.getUserDao().addUser(createUser);
                        User u=daos.getUserDao().CheckUser(username,password);
                        teacher.setUid(u.getId());
                        teacher.setName(name);
                        teacher.setSalary(salary);
                        daos.getTeacherDao().addTeacher(teacher);
                        System.out.println("登録が成功しました。");
                        sendMes("登録が成功しました。");

                    }else if("2".equals(selection)){
                        continue;
                    }
                }else{
                    System.out.println("入力内容に誤りがあります。再度入力してください。");
                    sendMes("入力内容に誤りがあります。再度入力してください。");
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(br!=null)
                {
                  br.close();
                }
            }catch (Exception e){

            }finally {
                br=null;
                try {
                    if (pw != null) {
                        pw.close();
                    }
                }finally {
                    pw=null;
                    try {
                        if(socket!=null)
                        {
                            socket.close();
                        }
                    }catch (Exception e){

                    }finally {
                        socket=null;
                    }
                }
            }
        }

    }
}
@Component("server")
public class Server implements Runnable{
    ServerSocket serverSocket=null;
    Daos daos=null;
    Server(){
        try {
            serverSocket=new ServerSocket(23301);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void createClient(UserService userService,UserDao userDao,StudentDao studentDao,TeacherDao teacherDao,ClassDao classDao)
    {
        daos=new Daos(userService,userDao,studentDao,teacherDao,classDao);
    }

    @Override
    public void run() {
        try {
            while(true)
            {
                System.out.println("======クライアントからの接続を待っています=======");
                Socket socket = serverSocket.accept();
                ServiceClent serviceClent1=new ServiceClent(daos);
                serviceClent1.setSocket(socket);

                Thread thread=new Thread(serviceClent1);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
