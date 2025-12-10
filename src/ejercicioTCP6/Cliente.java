package ejercicioTCP6;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws Exception {

		Socket socketCliente = null;
        BufferedReader entrada = null;
        BufferedWriter salida = null;

        /* Creamos un socket en el lado cliente, enlazado con un servidor que está en la misma máquina que el 
        cliente y que escucha en el puerto 4444 */
        try {
            socketCliente = new Socket("localhost", 4444);

            // Obtenemos el canal de entrada
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

            // Obtenemos el canal de salida
            salida = new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream()));
        } catch (IOException e) {
            System.err.println("No puede establecer canales de E/S para la conexión");
            System.exit(-1);
        }

        Scanner sc = new Scanner(System.in);
        String numero;
		int num;

        /* El programa cliente no analiza los mensajes enviados por el usuario, simplemente los reenvía al servidor 
        hasta que este se despide con "Adios" */
        try {
            while (true) {
				System.out.println("Introduce un numero: ");

                // Leo la entrada del usuario
                numero = sc.nextLine();

				if (numero.equals("*")) {
                    break;
                }

				num = Integer.parseInt(numero);

                // La envia al servidor por el OutputStream
                salida.write(num);

                // Recibe la respuesta del servidor por el InputStream
                numero = entrada.readLine();

				num = Integer.parseInt(numero);

                // Envía a la salida estándar la respuesta del servidor
                System.out.println("Respuesta servidor: " + num);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        // Libera recursos
        salida.close();
        entrada.close();
        sc.close();
        socketCliente.close();
	}// main
}