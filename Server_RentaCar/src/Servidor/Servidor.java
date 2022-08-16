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

                //Envio la solicitid de tarea
                out.writeUTF("tarea");

                // Pido al cliente la tarea.
                tarea = in.readUTF();
                System.out.println("Tarea: " + tarea);//print--------------->

                //Envio la solicitid de tarea
                out.writeUTF("id");

                // Pido al cliente el ID de busqueda (Placa o cedula).
                id = in.readUTF();
                System.out.println("Id: " + tarea);//print--------------->

                // Inicio el hilo
                System.out.println("Se inicia el Hilo. Tarea: " + tarea + "ID: " + id);//print--------------->
                ServidorHilo hilo = new ServidorHilo(sc, in, out, tarea, id);
                hilo.start();

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
