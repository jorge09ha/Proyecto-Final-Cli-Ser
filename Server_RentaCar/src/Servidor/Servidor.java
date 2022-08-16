package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(38000);
            Socket sc;

            System.out.println("Servidor iniciado. Puerto: 38000");//print--------------->
            while (true) {

                // Espero la conexion del cliente
                sc = server.accept();

                String tarea = null;
                String id = null;

                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());

                System.out.println("*****Cliente conectado.******");//print--------------->

                // Inicio el hilo
                System.out.println("Se inicia el Hilo.");//print--------------->
                ServidorHilo hilo = new ServidorHilo(sc, in, out);
                hilo.start();

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
