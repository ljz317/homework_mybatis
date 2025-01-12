package cn.covn.homework_mybatis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client extends Thread {
    Socket socket=null;
    BufferedReader br=null;
    PrintWriter pw=null;
    Scanner sc=null;
    Client(String ip, int port) {
        try {
            socket=new Socket(ip,port);
            br=new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            pw=new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void startTesk()
    {
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
    }
    @Override
    public void run() {
        try {
            startTesk();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(socket!=null){
                    socket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                if(br!=null){
                    try {
                        br.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }finally {
                        if(pw!=null){
                            pw.close();
                        }
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1",23302);


    }
}
