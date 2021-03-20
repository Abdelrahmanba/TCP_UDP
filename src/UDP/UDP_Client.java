package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDP_Client {
    public static void main(String[] argv){
        String serverName= "localhost";
        int serverPort = 9876;

        byte[] sendData,receiveData=new byte[1024];
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        try {
            //create client socket
            DatagramSocket clientSocket = new DatagramSocket();
            //get ip address for the servername
            InetAddress IPAddress = InetAddress.getByName(serverName);
            //read line from the keyboard
            String sentence = inFromUser.readLine();
            //encode read data to bytes
            sendData = sentence.getBytes();
            //send packet to server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
            clientSocket.send(sendPacket);
            //wait for server response
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            //decode data to string
            String modifiedSentence = new String(receivePacket.getData());
            //print string
            System.out.println("FROM SERVER:" + modifiedSentence);
            //close connection
            clientSocket.close();

        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
