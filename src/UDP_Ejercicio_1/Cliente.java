package UDP_Ejercicio_1;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Cliente {

    @SuppressWarnings("deprecation")
    public static void main(String args[]) throws Exception {
        // Se crea el socket multicast
        // El puerto debe ser el mismo en todos los clientes, ya que el
        // servidor multicast envía la información a la IP multicast y a un puerto
        int puerto = 12345;// Puerto multicast
        MulticastSocket ms = new MulticastSocket(puerto);
        // Nos unimos al grupo multicast
        InetAddress grupo = InetAddress.getByName("225.0.0.1");
        ms.joinGroup(grupo);
        String cadena = "";
        Scanner in = new Scanner(System.in);

        // Se escoge una dirección para el grupo
        InetAddress grupoMulticast = InetAddress.getByName("225.0.0.1");
        while (!cadena.trim().equals("*")) {
            System.out.print("Datos a enviar al Servidor: ");
            cadena = in.nextLine();
            // Enviamos el mensaje a todos los clientes que se hayan unido al grupo
            DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), grupoMulticast, puerto);
            ms.send(paquete);
        }

        in.close();
        // Abandonamos grupo
        ms.leaveGroup(grupo);
        // Cerramos recursos
        ms.close();
        System.out.println("Socket cerrado...");

    }
    
}
