package Conexion;

import static Conexion.ClienteSocket.ipServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jorge Hernandez Araya
 */
public class HomeSocket {

    public static Object homeToServer(String tarea, String id) {
        try {
            //Creo el socket.
            Socket sc = new Socket(ipServer, 7777);
            System.out.println("\n***CONECTADO AL SERVIDOR***");//print--------------->

            //Defino la entrada y la salida.
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            // Ejecutamos el hilo.
            
             System.out.println("-Se inicia el Hilo" + "\n-Tarea= " + tarea + "\n-ID=" + id);//print--------------->
            HomeHilo hilo = new HomeHilo(in, out, tarea);
            hilo.start();
            hilo.join();

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ClienteSocket.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
