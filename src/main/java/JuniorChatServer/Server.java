package JuniorChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;

    }
    public void runServer(){
        while(serverSocket.isClosed()){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("New client is connected ");
                ClientManager clientManager = new ClientManager(socket);


            }catch (IOException e){

            }

        }
    }
}
