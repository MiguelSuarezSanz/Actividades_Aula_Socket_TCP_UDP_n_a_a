package TCP_Ejercicio_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException { 

        Socket socketCliente = null; 
        BufferedReader entrada = null; 
        
        // Creamos un socket en el lado cliente, enlazado con un  
        // servidor que está en la misma máquina que el cliente 
        // y que escucha en el puerto 6000 
        
        try { 
            socketCliente = new Socket("localhost", 6000); 
            
            // Obtenemos el canal de entrada 
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream())); 
        
        } catch (IOException e) { 
            System.err.println("No puede establecer canales de E/S para la conexión"); 
            
            System.exit(-1); 
        
        }
                
        // Recibe la respuesta del servidor por el InputStream 
        String linea = entrada.readLine(); 
        
        // Envía a la salida estándar la respuesta del servidor
        System.out.println("Respuesta servidor: " + linea); 
        
        // Libera recursos 
        entrada.close(); 
        socketCliente.close(); 
    } 
    
}
