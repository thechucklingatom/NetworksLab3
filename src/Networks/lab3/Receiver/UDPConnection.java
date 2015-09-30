package Networks.lab3.Receiver;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
/**
 * @author Jesse Sheppard, Robert Putnam
 */
public class UDPConnection
{
    int window = 0;
    int seqNum = 0;
    int numToDrop = 0;
    DatagramSocket senderSocket;
    InetAddress address;
    DatagramPacket sendPkt;
    DatagramPacket recvPkt;

    UDPConnection(int inWindow, int inSeqNum, int inNumToDrop) //for now, this just sends a packet on construction.
    //I plan to handle the actual sending, loss, and operations of the code in other methods,
    // I just wanted to get something you could see.

    {
        window = inWindow;
        seqNum = inSeqNum;
        numToDrop = inNumToDrop;
        byte[] tempIp = new byte[4];
        tempIp[0] = (byte) 153;
        tempIp[1] = (byte) 153;
        tempIp[2] = (byte) 153;
        tempIp[3] = (byte) 153;
        try{
            senderSocket = new DatagramSocket(9877);
            address = InetAddress.getByAddress(tempIp);
            byte[] sendData = new byte[1024];
            sendPkt = new DatagramPacket(sendData, sendData.length, address, 9876);
            senderSocket.send(sendPkt);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void Send(byte[] sendData, int length, InetAddress address, int port){
        try {
            sendPkt = new DatagramPacket(sendData, length, address, port);
            senderSocket.send(sendPkt);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public byte[] Receive(){
        try{
            senderSocket.receive(recvPkt);

        }catch(IOException e){
            e.printStackTrace();
        }
        return recvPkt.getData();
    }

    public void setAddress(InetAddress address){
        this.address = address;
    }
}
