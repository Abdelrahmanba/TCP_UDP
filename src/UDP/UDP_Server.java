package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP_Server {
public static void main(String[] argv){
    int portNo= 9876;

    DatagramSocket serverSocket;
    byte[] sendData,receivedData=new byte[1024];
    try {
        //server socket
        serverSocket =new DatagramSocket(portNo);
        while(true){
            //receive data from client
            DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
            serverSocket.receive(receivePacket);
            //decode bytes into string
            String sentence = new String(receivePacket.getData());
            //print received sentence
            System.out.printf("Server received:%s",sentence);
            //get sender ip
            InetAddress IPAddress = receivePacket.getAddress();
            //get sender port
            int clientPort = receivePacket.getPort();
            //change the sentence for the desired output
            String capitalizedSentence = sentence.toUpperCase();
            //encode into bytes
            sendData = capitalizedSentence.getBytes();
            //send data
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
            serverSocket.send(sendPacket);
        }
    } catch (SocketException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
