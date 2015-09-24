package carserveur.findprotocole;

import java.io.IOException;
import java.net.*;

/**
 * Created by Marc-Antoine on 24/09/2015.
 */
public class BroadcastListener implements Runnable {

    private int port = -1;

    public BroadcastListener(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        if(port == -1) return;

        try {
            DatagramSocket socket = new DatagramSocket(port, InetAddress.getByName("0.0.0.0"));
            socket.setBroadcast(true);
            System.out.println("Listen on " + socket.getLocalAddress() + " from " + socket.getInetAddress());

            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf,buf.length);

            while(true){
                System.out.println("Waiting for request");
                socket.receive(packet);
                // TODO Gestion renvoie packet

                String response = "It's me !";

                buf = response.getBytes();
                InetAddress address = packet.getAddress();
                int tmp_port = packet.getPort();
                packet = new DatagramPacket(buf,buf.length,address,tmp_port);
                socket.send(packet);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
