package ejercicioTCP3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws Exception {

		String host = "localhost";
		int puerto = 6000;
		Socket Cliente = new Socket(host, puerto);

		// CREO FLUJO DE SALIDA AL SERVIDOR
		DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserte un numero: ");
		int numero = sc.nextInt();

		// ENVIO UN SALUDO AL SERVIDOR
		flujoSalida.writeInt(numero);

		// CREO FLUJO DE ENTRADA AL SERVIDOR
		DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

		// EL SERVIDOR ME ENVIA UN MENSAJE
		System.out.println("Numero recibido del servidor: " + flujoEntrada.readInt());

		// CERRAR STREAMS Y SOCKETS
		sc.close();
		flujoEntrada.close();
		flujoSalida.close();
		Cliente.close();
	}// main
}