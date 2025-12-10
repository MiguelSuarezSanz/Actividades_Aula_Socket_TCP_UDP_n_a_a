package EjercicioTCP5;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Cliente {
    public static void main(String[] args) throws Exception {
        String Host = "localhost";
        int Puerto = 6000;// puerto remoto

        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Socket Cliente = new Socket(Host, Puerto);

        Scanner sc = new Scanner(System.in);
        System.out.println("Escriba un numero:\n");

        Numeros numero = new Numeros(sc.nextInt());

        // CREO FLUJO DE SALIDA AL SERVIDOR
        ObjectOutputStream outObjeto = new ObjectOutputStream(
                Cliente.getOutputStream());

        // ENVIO UN SALUDO AL SERVIDOR
        outObjeto.writeObject(numero);


        /*
         * // CREO FLUJO DE ENTRADA AL SERVIDOR
         * DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());
         * String texto = flujoEntrada.readUTF();
         * 
         * // EL SERVIDOR ME ENVIA UN MENSAJE
         * System.out.println("Recibiendo del SERVIDOR: \n\t" +
         * texto);
         * 
         * // CREO FLUJO DE SALIDA AL SERVIDOR
         * DataOutputStream flujoSalida = new
         * DataOutputStream(Cliente.getOutputStream());
         * 
         * // ENVIO UN SALUDO AL SERVIDOR
         * flujoSalida.writeUTF(texto.toLowerCase());
         * 
         * // cerrar
         *      flujoEntrada.close();
         */
        // CERRAR STREAMS Y SOCKETS
   
        outObjeto.close();
        Cliente.close();
    }// main
}//
