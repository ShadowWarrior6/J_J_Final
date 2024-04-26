package JuniorChatServer;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager {
    private Socket socket;
    private ServerSocket serverSocket;
    private  String name;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public static ArrayList<ClientManager> clents = new ArrayList<>();

    public ClientManager(Socket socket, String name, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clents.add(this);
            System.out.println(name + "Connected to chat ");
        } catch (IOException e) {

        }
    }

    public ClientManager(Socket socket) {
    }

    public void removeClient(){
        clents.remove(this);
        System.out.println(name + "Left the chat ");
    }
    private void closeEverything(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        removeClient();
      try {

          if(bufferedReader!=null){
              bufferedReader.close();
          }
          if(bufferedWriter!=null){
              bufferedWriter.close();
          }
          if(socket!=null){
              socket.close();
          }

      }
      catch (IOException e){
          e.printStackTrace();
      }

    }
    private void closeSocket(){
        try{
            if(serverSocket!=null)serverSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
