package ejercicioTCP6;

import java.io.*;
import java.net.*;

public class Servidor {

	public static final int PORT = 6000;
	public static void main(String[] arg) throws IOException {

		// Establece el puerto en el que escucha peticiones
        ServerSocket socketServidor = null;

        try {
            socketServidor = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("No puede escuchar en el puerto: " + PORT);
            System.exit(-1);
        }

        Socket socketCliente = null;
        System.out.println("Escuchando: " + socketServidor);

        try {
            while (true) {
                // Se bloquea hasta que recibe alguna petición de un cliente abriendo un socket para el cliente
                socketCliente = socketServidor.accept();
                System.out.println("Conexión aceptada: " + socketCliente);

                /* Para seguir aceptando peticiones de otros clientes se crea un nuevo hilo que se encargará de la 
                comunicación con el cliente */
                new ServidorHilos(socketCliente).start();
            }
        } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
        }
	}
}