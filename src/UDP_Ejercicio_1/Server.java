package UDP_Ejercicio_1;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server {

    @SuppressWarnings("deprecation")
    public static void main(String args[]) throws Exception {

        // Enviamos la información introducida por teclado hasta que se envíe un *
        // Se crea el socket multicast.
        MulticastSocket ms = new MulticastSocket();
        String msg = "";
        // Nos unimos al grupo multicast
        InetAddress grupo = InetAddress.getByName("225.0.0.1");
        ms.joinGroup(grupo);
        while (!msg.trim().equals("*")) {
            // El buffer se crea dentro del bucle para que se sobrescriba
            // con cada nuevo mensaje
            byte[] buf = new byte[1000];
            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            // Recibe el paquete del servidor multicast
            ms.receive(paquete);
            msg = new String(paquete.getData());
            System.out.println("Recibo: " + msg.trim().toUpperCase());
        }
        
        // Cerramos recursos
        ms.close();
        System.out.println("Socket cerrado...");
    }
    
}
