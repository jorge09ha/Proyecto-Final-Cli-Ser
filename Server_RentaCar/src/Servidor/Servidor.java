package Servidor;

import static Servidor.ServidorHilo.entradaArchivoJson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jorge Hernandez Araya
 */
public class Servidor {

    /*
    Esta clase habilita la conexión de los sockets en el puerto 7777 
    queda esperando a que ingrese un cliente y posterior lo manda a un hilo aparte.
     */

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(7777);
            Socket sc;
            System.out.println("\n***SERVER ON Puerto: 7777***\n");//print--------------->
            while (true) {

                // Espero la conexion del cliente
                entradaArchivoJson();
                sc = server.accept();

                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());

                // Inicio el hilo
                System.out.println("*Cliente CONECTADO: " + sc.getInetAddress().getHostAddress() + ":" + sc.getPort());
                System.out.println("-Se inicia el hilo");//print--------------->
                ServidorHilo hilo = new ServidorHilo(sc, in, out);
                hilo.start();

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
