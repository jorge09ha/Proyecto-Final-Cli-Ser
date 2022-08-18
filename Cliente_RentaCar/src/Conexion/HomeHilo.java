package Conexion;

import static GUI.Home.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ClasesRentaCar.Auto;
import ClasesRentaCar.Cliente;
import ClasesRentaCar.UserAdmin;
import java.net.Socket;

/**
 * @author Jorge Hernandez Araya
 */
public class HomeHilo extends Thread {

    /*
    Clase destinada para actualizar los datos que se presentan en la pantalla home
     */
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

    /*
    Y lo creado para realizar las distintas tareas de consulta y modificación de datos
     */
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
            System.out.println("-Envio json");//print----------------------#

            while (!strFromClient.equals("stop")) {

                if ("home".equals(strToClient)) {

                    out.writeUTF(strToClient); //Task
                    out.flush();

                    strFromClient = in.readUTF();// autos disponibles
                    System.out.println("-Resultado: " + strFromClient);//print---------------#
                    autosdisponibles = strFromClient;
                    autosDisponibles(strFromClient);

                    out.writeUTF("ok");
                    out.flush();

                    strFromClient = in.readUTF();//autos rentados
                    System.out.println("-Resultado: " + strFromClient);//print---------------#
                    autosrentados = strFromClient;
                    autosRentados(strFromClient);

                    out.writeUTF("ok");
                    out.flush();

                    strFromClient = in.readUTF();//adminuser
                    System.out.println("-Resultado: " + strFromClient);//print---------------#
                    adminuser = strFromClient;
                    adminUser(strFromClient);

                    out.writeUTF("ok");
                    out.flush();

                    strFromClient = in.readUTF();//clientuser
                    System.out.println("-Resultado: " + strFromClient);//print---------------#
                    clientuser = strFromClient;
                    clientUser(strFromClient);

                    out.writeUTF("stop");
                    out.flush();

                    strFromClient = in.readUTF();

                    break;

                }
            }
            System.out.println("***Cliente DESCONECTADO***\n");
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

    /*--------Conexion puerto 5000 salida de archivos---------*/
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

    /*----------------------Envia archivo JSON----------------------*/
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
