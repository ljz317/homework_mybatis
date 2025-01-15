package cn.covn.homework_mybatis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Serverに訪問するクライアント
 * 単独で起動してください
 */
public class Client implements Runnable {
    Socket socket=null;
    BufferedReader br=null;
    PrintWriter pw=null;
    Scanner sc=null;
    Client(String ip, int port) {
        try {
            socket=new Socket(ip,port);
            br=new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            pw=new PrintWriter(socket.getOutputStream(),true);
            sc=new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void startTesk()
    {
        System.out.println("开始tesk");
        Thread reader=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        String readLine = br.readLine();
                        System.out.println(readLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread writer=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    String selection=sc.nextLine();
                    pw.println(selection);
                    if(selection.equals("exit")){
                        break;
                    }
                }
            }
        });
        reader.start();
        writer.start();
    }
    @Override
    public void run() {
        try {
            startTesk();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost",23301);
        Thread reader=new Thread(client);
        reader.start();
    }
}
