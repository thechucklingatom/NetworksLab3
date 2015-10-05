package Networks.lab3.Receiver;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by thechucklingatom on 9/28/2015.
 * This is the receiver for packets.
 */
public class Receiver {

    public static void main (String[] args) {
        byte address[] = {127,0,0,1};
        UDPConnection myConnection;
        boolean allReceived = false;

        try {
            myConnection = new UDPConnection(InetAddress.getByAddress(address));
        }catch(UnknownHostException e){
            myConnection = new UDPConnection();
        }
        byte b[];

        b = myConnection.Receive();

        boolean packetTracker[] = new boolean[(int)b[0]];

        for(boolean iter : packetTracker) iter = false;

        while(!allReceived) {
            b = myConnection.Receive();

            packetTracker[(int)b[0]] = true;
            try {
                myConnection.Send(b, b.length, InetAddress.getByAddress(address), 4897);
            }catch(UnknownHostException e){
                e.printStackTrace();
            }

            for(boolean iter : packetTracker){
                if(!iter){
                    allReceived = false;
                    break;
                }else{
                    allReceived = true;
                }
            }

        }
    }

}
