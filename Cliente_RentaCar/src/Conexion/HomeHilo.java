package Conexion;

import GUI.Client;
import static GUI.Home.*;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import rentACar.Auto;
import rentACar.Cliente;
import rentACar.UserAdmin;

public class HomeHilo extends Thread {

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private Socket sc;
    private DataInputStream in;
    private DataOutputStream out;
    private String tarea;

    public HomeHilo(DataInputStream in, DataOutputStream out, String tarea) {
        this.in = in;
        this.out = out;
        this.tarea = tarea;
    }

    @Override
    public void run() {

        try {

            String strFromClient = "", strToClient = "", msg = "";
            strToClient = tarea;

            Cliente cli = new Cliente();
            Auto aut = new Auto();
            UserAdmin usu = new UserAdmin();

            String autosdisponibles, autosrentados, adminuser, clientuser;

            envioArchivoJson();

            while (!strFromClient.equals("stop")) {

                if ("home".equals(strToClient)) {

                    out.writeUTF(strToClient); //Task
                    out.flush();

                    strFromClient = in.readUTF();// autos disponibles
                    autosdisponibles = strFromClient;
                    autosDisponibles(strFromClient);

                    out.writeUTF("ok");
                    out.flush();

                    strFromClient = in.readUTF();//autos rentados
                    autosrentados = strFromClient;
                    autosRentados(strFromClient);

                    out.writeUTF("ok");
                    out.flush();

                    strFromClient = in.readUTF();//adminuser
                    adminuser = strFromClient;
                    adminUser(strFromClient);

                    out.writeUTF("ok");
                    out.flush();

                    strFromClient = in.readUTF();//clientuser
                    clientuser = strFromClient;
                    clientUser(strFromClient);

                    out.writeUTF("stop");
                    out.flush();

                    strFromClient = in.readUTF();

                    break;

                }

                /*----------------------Errores y notificaciones----------------------*/
                //Client.mensajes(msg);
            }
        } catch (IOException ex) {
            Logger.getLogger(HomeHilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                if (in != null) {
                    in.close();
                }

                if (out != null) {
                    out.close();
                }
                if (sc != null) {
                    sc.close();
                }
            } catch (IOException e) {
            }
        }

    }

    public static void envioArchivoJson() {
        try ( Socket socket = new Socket("localhost", 5000)) {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            envioFragmentosArchivo("ClientSide.json");

            dataInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void envioFragmentosArchivo(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        // send file size
        dataOutputStream.writeLong(file.length());
        // break file into chunks
        byte[] buffer = new byte[4 * 1024];
        while ((bytes = fileInputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
    }
}
