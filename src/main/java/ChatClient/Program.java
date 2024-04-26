package ChatClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Введи своё имя :");
        String name =scanner.nextLine();
        try {

            InetAddress address = InetAddress.getLocalHost();
            System.out.println("Connected To:" + address + ":" + 1300);
           Socket socket=new Socket(address,1300);
            Client client=new Client(socket,name);
           InetAddress inetAddress = socket.getInetAddress();
            System.out.println("Inetadress "+ inetAddress);
            String remoteIp=inetAddress.getHostAddress();
            System.out.println("Host "+remoteIp);
            System.out.println("Localport " + socket.getLocalPort());

        }catch (UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
