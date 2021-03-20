package TCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCP_Client {
    public static void main(String[] argv){
        int portNo = 6789;
        String serverName = "192.168.1.101";
        Socket clientSocket;
        BufferedReader stdUserReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            clientSocket = new Socket(serverName,portNo);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(stdUserReader.readLine()+"\n");
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.printf("ERROR:Couldn't creat user socket, probably  port:%d is currently in use%n",portNo);
        }



    }
}
