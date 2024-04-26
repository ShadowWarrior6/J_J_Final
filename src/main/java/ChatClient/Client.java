package ChatClient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public final Socket socket;
    public final String name;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;


    public Client(Socket socket, String userName) {
        this.socket = socket;
        name = userName;
        try {

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }

    public void sendMessage() {
        try {
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner scanner=new Scanner(System.in);
            while(socket.isConnected()){
                String message=scanner.nextLine();
                bufferedWriter.write(name+":"+message);
                bufferedWriter.newLine();
                bufferedWriter.flush();

            }
        }
        catch (IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void listenForMessage() {
        new Thread() {
            @Override
            public void run() {
                String message;
            while(socket.isConnected()){
                try{
                   message= bufferedReader.readLine();
                    System.out.println(message );
                }
                catch (IOException e){
                    closeEverything(socket,bufferedReader,bufferedWriter);
                }
            }
            }
        }.start();
    }



        public void closeEverything(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){

            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
                if (socket!=null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



