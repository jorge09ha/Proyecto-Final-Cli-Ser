package rentACar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteS {

    public static Object clientToServer(String task, String id) {
        try {
            Scanner sn = new Scanner(System.in);
            sn.useDelimiter("\n");

            Socket sc = new Socket("127.0.0.1", 5000);

            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            // Leer mensaje del servidor
            String mensaje = in.readUTF();
            System.out.println(mensaje);

            // Escribe el nombre y se lo manda al servidor
            String nombre = sn.next();
            out.writeUTF(nombre);
            System.out.println("Envio el nombre");//print---------------

            // ejecutamos el hilo
            ClienteHilo hilo = new ClienteHilo(in, out, task, id);
            System.out.println("Inicio el hilo");//print---------------
            hilo.start();
            hilo.join();

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ClienteS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
