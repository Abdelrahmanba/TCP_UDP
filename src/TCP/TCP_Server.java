package TCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.exit;

public class TCP_Server {
    public static void main(String[] argv) {
        ServerSocket listeningSocket = null;
        int portNo = 6789;
        String receivedSentence,capitalizedSentence;

        try {
            listeningSocket = new ServerSocket(6789);
        } catch (IOException e) {
            System.err.printf("ERROR:Couldn't creat socket, probably port:%d is currently in use.", portNo);
            exit(1);
        }

        while (true) {
            try{
                Socket connectionSocket = listeningSocket.accept();
                BufferedReader inFormUser = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                receivedSentence=inFormUser.readLine();
                System.out.printf("Received from client: %s",receivedSentence);
                capitalizedSentence=receivedSentence.toUpperCase();
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                outToClient.writeBytes(capitalizedSentence);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
