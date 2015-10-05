package Networks.lab3.Receiver;

import java.net.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 * @author Jesse Sheppard, Robert Putnam
 */
public class UDPConnection extends Thread{
    //i think the window is unneeded in this class
    int window = 0;
    int seqNum = 0;
    int numToDrop = 0;
    DatagramSocket socket;
    InetAddress address;
    DatagramPacket sendPkt;
    DatagramPacket recvPkt;

    public UDPConnection(){
        try {
            socket = new DatagramSocket(4789);
        }catch(SocketException e){
            System.out.println("Socket taken");
            e.printStackTrace();
        }

        recvPkt = new DatagramPacket(new byte[256], new byte[256].length);
    }

    public UDPConnection(InetAddress address) {
        try {
            socket = new DatagramSocket(4789);
        }catch(SocketException e){
            System.out.println("Socket taken");
            e.printStackTrace();
        }

        recvPkt = new DatagramPacket(new byte[256], new byte[256].length);

        this.address = address;
    }

    public void Send(byte[] sendData, int length, InetAddress address, int port){
        try {
            sendPkt = new DatagramPacket(sendData, length, address, port);
            socket.send(sendPkt);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public byte[] Receive(){
        try{
            socket.receive(recvPkt);

        }catch(IOException e){
            e.printStackTrace();
        }
        return recvPkt.getData();
    }

    public void setAddress(InetAddress address){
        this.address = address;
    }
}
