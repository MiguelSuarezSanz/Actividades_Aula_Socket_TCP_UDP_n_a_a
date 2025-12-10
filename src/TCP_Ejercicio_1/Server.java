package TCP_Ejercicio_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		int Puerto = 6000;// Puerto 
		ServerSocket Servidor = new ServerSocket(Puerto);
		System.out.println("Escuchando en " + Servidor.getLocalPort());	

		Socket cliente1 = Servidor.accept(); //esperando a un cliente 
		//realizar acciones con cliente1		
		visualizarCLiente(cliente1,"Cliente 1");
		
		Socket cliente2 = Servidor.accept(); //esperando a otro cliente
		//realizar acciones con cliente2
		visualizarCLiente(cliente2,"Cliente 2");
		
		Servidor.close();	//cierro socket servidor

	}

	private static void visualizarCLiente(Socket cliente, String nombre) {
		System.out.printf("%n%s%n",nombre);
		System.out.printf("Puerto Local: %s%n",cliente.getLocalPort());
		System.out.printf("Puerto Remoto: %s%n%n",cliente.getRemoteSocketAddress());
	}
}
