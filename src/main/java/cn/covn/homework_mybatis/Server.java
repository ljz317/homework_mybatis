package cn.covn.homework_mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ServiceClent implements Runnable
{
    Socket socket=null;
    BufferedReader br=null;
    PrintWriter pw=null;
    Scanner sc=null;
    ServiceClent(Socket socket)
    {
        this.socket=socket;
    }

    @Override
    public void run() {


    }
}
@Component
public class Server extends Thread{
    ServerSocket serverSocket=null;
    @Autowired
    HomeworkMybatisApplication homework;
    Server()
    {
        try {
            serverSocket=new ServerSocket(23302);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        homework.start();
        try {
            while(true)
            {
                Socket socket = serverSocket.accept();
                ServiceClent serviceClent = new ServiceClent(socket);
                Thread thread=new Thread(serviceClent);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server=new Server();
        server.start();

    }
}
