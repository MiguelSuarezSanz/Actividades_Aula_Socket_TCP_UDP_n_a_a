package TCP_Ejercicio_4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 6000;
    public static int i = 1;

    public static void main(String[] args) {

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
                // Se bloquea hasta que recibe alguna petición de un cliente
                // abriendo un socket para el cliente
                socketCliente = socketServidor.accept();

                System.out.println("Conexión aceptada: " + socketCliente + " como Cliente " + i);

                // Para seguir aceptando peticiones de otros clientes
                // se crea un nuevo hilo que se encargará de la comunicación con el cliente
                new Worker(socketCliente, i).start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketCliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Worker extends Thread {

        private Socket socketCliente;
        private PrintWriter salida = null;
        private int nCliente;

        public Worker(Socket socketCliente, int nCliente) {
            this.socketCliente = socketCliente;
            this.nCliente = nCliente;
        }

        @Override
        public void run() {
            try {

                // Establece canal de salida
                salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),
                        true);

                // Hacemos un envío al cliente
                String mensajeEnviado = "Tu eres el Cliente " + nCliente ;
                salida.println(mensajeEnviado);
                System.out.println("--> Cliente"+nCliente+": " + mensajeEnviado);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                salida.close();
            }
        }
    }

}
