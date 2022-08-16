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
            ServerSocket server = new ServerSocket(5000);
            Socket sc;

            System.out.println("Servidor iniciado");
            while (true) {

                // Espero la conexion del cliente
                sc = server.accept();

                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());

                // Pido al cliente la tarea.
                String tarea = in.readUTF();

                System.out.println(sc.getInetAddress().getAddress() + " conectado.");
                System.out.println("Tarea: " + tarea);

                // Inicio el hilo
                ServidorHilo hilo = new ServidorHilo(sc, in, out, tarea);
                hilo.start();

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
