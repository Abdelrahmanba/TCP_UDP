package TCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static java.lang.System.exit;

public class TCP_Client {
    public static void main(String[] argv){
        int portNo = 6789;
        String serverName = "192.168.1.101";
        String inputSentence,receivedSentence;
        Socket clientSocket;

        try {
            //socket creation and connection
            clientSocket = new Socket(serverName,portNo);
            //initializing keyboard and server input streams
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //initializing server output stream
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            //readline from keyboard
            inputSentence=inFromUser.readLine()+"\n";
            //write to server
            outToServer.writeBytes(inputSentence);
            //wait for respond from server
            receivedSentence=inFromServer.readLine();
            //print the capitalized sentence out
            System.out.printf("Server response: %s",receivedSentence);
            //close socket
            clientSocket.close();
        } catch (IOException e) {
            System.err.printf("ERROR:Couldn't creat user socket, probably  port:%d is currently in use%n",portNo);
            exit(1);
        }

    }
}
