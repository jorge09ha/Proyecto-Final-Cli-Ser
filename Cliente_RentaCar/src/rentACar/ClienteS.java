package rentACar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteS {

    public static Object clientToServer(String task, String id) {
        try {

            Socket sc = new Socket("127.0.0.1", 38000);

            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());


            // Escribe el nombre y se lo manda al servidor
            String tarea = task;
            out.writeUTF(tarea);
            System.out.println("Envio la tarea: "+tarea);//print---------------

            // ejecutamos el hilo
            ClienteHilo hilo = new ClienteHilo(in, out, task, id);
            hilo.start();
            hilo.join();

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ClienteS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
