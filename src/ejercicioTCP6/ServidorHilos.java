package ejercicioTCP6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServidorHilos extends Thread {

    private Socket socketCliente;
    private BufferedReader entrada = null;
    private PrintWriter salida = null;

    public ServidorHilos(Socket socket) {
        this.socketCliente = socket;
    }

    @Override
    public void run() {
        try {
            // Establece canal de entrada
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

            // Establece canal de salida
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);

            // Realizamos la comunicación entre servidor y cliente
            // **** ES LO QUE CAMBIA EN CADA EJERCICIO ****
            // Hacemos una recepción de información desde el cliente
            String mensajeRecibido;

            while ((mensajeRecibido = entrada.readLine()) != null) {
                System.out.println("<-- Cliente: " + mensajeRecibido);

                // Hacemos un envío al cliente
                String mensajeEnviado = "Mensaje enviado desde el servidor al cliente";
                salida.println(mensajeEnviado);
                System.out.println("--> Cliente: " + mensajeEnviado);

                if (mensajeRecibido.equals("Adios")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
    }
}