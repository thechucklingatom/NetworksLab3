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
        UDPConnection myConnection = new UDPConnection(1,3,2);
        byte b[] = new byte[6];

        try {
            myConnection.Send(b, 1, InetAddress.getByAddress(address), 4789);
        }catch(UnknownHostException e){
            e.printStackTrace();
        }
    }

}
