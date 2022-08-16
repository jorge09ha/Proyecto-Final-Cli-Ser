package Conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ClienteSocket {

    //Clase de conexion al server, recive la tarea y el ID (Placa o cedula).
    public static Object clientToServer(String tarea, String id) {
        try {
            //Creo el socket.
            Socket sc = new Socket("127.0.0.1", 38000);

            //Defino la entrada y la salida.
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            //Espero msg del servidor
            String msg = in.readUTF();
            System.out.println("Servidor: " + msg);

            if (msg.equals("tarea")) {

                // Envia la tarea y se lo manda al servidor.
                out.writeUTF(tarea);
                out.flush();
                System.out.println("Envio la tarea: " + tarea);//print--------------->

                if (msg.equals("id")) {

                    // Envia el id y se lo manda al servidor.
                    out.writeUTF(id);
                    out.flush();
                    System.out.println("Envio la id: " + id);//print--------------->

                    // Ejecutamos el hilo.
                    System.out.println("Se inicia el Hilo. Tarea: " + tarea + "ID: " + id);//print--------------->
                    ClienteHilo hilo = new ClienteHilo(in, out, tarea, id);
                    hilo.start();
                    hilo.join();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error del msg del servidor", "Error", 0);
            }

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ClienteSocket.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
