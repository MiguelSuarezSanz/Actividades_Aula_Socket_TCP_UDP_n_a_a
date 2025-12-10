package ejercicioTCP3;

import java.io.*;
import java.net.*;

public class Servidor {

	public static void main(String[] arg) throws IOException {

		int numeroPuerto = 6000;// Puerto
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		Socket clienteConectado = null;
		System.out.println("Esperando al cliente.....");
		clienteConectado = servidor.accept();
		System.out.println("Cliente conectado");
		// CREO FLUJO DE ENTRADA DEL CLIENTE
		InputStream entrada = null;
		entrada = clienteConectado.getInputStream();
		DataInputStream flujoEntrada = new DataInputStream(entrada);

		// RECIBO EL NUMERO POR EL FLUJO DE ENTRADA DEL CLIENTE, Y LO MULTIPLICO
		int numero = flujoEntrada.readInt();
		int cuadrado = numero * numero;

		// CREO FLUJO DE SALIDA AL CLIENTE
		OutputStream salida = null;
		salida = clienteConectado.getOutputStream();
		DataOutputStream flujoSalida = new DataOutputStream(salida);

		// ENVIO EL CUADRADO DEL NUMERO AL CLIENTE
		flujoSalida.writeInt(cuadrado);

		// CERRAR STREAMS Y SOCKETS
		entrada.close();
		flujoEntrada.close();
		salida.close();
		flujoSalida.close();
		clienteConectado.close();
		servidor.close();
	}
}