package Conexion;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rentACar.Auto;
import rentACar.Cliente;
import rentACar.UserAdmin;

public class ClienteHilo extends Thread {

    private static DataOutputStream outF = null;
    private static DataInputStream inF = null;
    private DataInputStream in;
    private DataOutputStream out;
    private String tarea, id;

    public ClienteHilo(DataInputStream in, DataOutputStream out, String tarea, String id) {
        this.in = in;
        this.out = out;
        this.tarea = tarea;
        this.id = id;
    }

    @Override
    public void run() {

        String mensaje = null;
        boolean exit = false;

        try {

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
                }

                while (!exit) {

                    try {
                        switch (mensaje) {
                            /*----------------------Clientes----------------------*/
                            case "agregarCliente":
                                break;

                            case "editarCliente":
                                out.writeUTF(mensaje); //Task
                                out.flush();
                                mensaje = in.readUTF();
                                out.writeUTF(id); //send id
                                out.flush();
                                mensaje = in.readUTF(); //doit
                                mensaje = "servidorA";
                                out.writeUTF(mensaje);
                                out.flush();
                                break;

                            case "buscarCliente":

                                break;

                            case "borrarCliente":
                                out.writeUTF(mensaje); //Task
                                out.flush();
                                mensaje = in.readUTF();
                                out.writeUTF(id); //send id
                                out.flush();
                                mensaje = in.readUTF(); //doit
                                out.writeUTF("stop");
                                out.flush();
                                break;

                            /*----------------------Usuarios----------------------*/
                            case "agregarUsuario":
                                break;

                            case "editarUsuario":
                                out.writeUTF(mensaje); //Task
                                out.flush();
                                mensaje = in.readUTF();
                                out.writeUTF(id); //send id
                                out.flush();
                                mensaje = in.readUTF(); //doit
                                mensaje = "servidorA";
                                out.writeUTF(mensaje);
                                out.flush();
                                break;

                            case "buscarUsuario":
                                break;

                            case "borrarUsuario":
                                out.writeUTF(mensaje); //Task
                                out.flush();
                                mensaje = in.readUTF();
                                out.writeUTF(id); //send id
                                out.flush();
                                mensaje = in.readUTF(); //doit
                                out.writeUTF("stop");
                                out.flush();
                                break;

                            /*----------------------Autos----------------------*/
                            case "agregarAuto":
                                break;

                            case "editarAuto":
                                out.writeUTF(mensaje); //Task
                                out.flush();
                                mensaje = in.readUTF();
                                out.writeUTF(id); //send id
                                out.flush();
                                mensaje = in.readUTF(); //doit
                                mensaje = "servidorA";
                                out.writeUTF(mensaje);
                                out.flush();
                                break;

                            case "buscarAuto":
                                break;

                            case "borrarAuto":
                                out.writeUTF(mensaje); //Task
                                out.flush();
                                mensaje = in.readUTF();
                                out.writeUTF(id); //send id
                                out.flush();
                                mensaje = in.readUTF(); //doit
                                out.writeUTF("stop");
                                out.flush();
                                break;

                            /*----------------------Rentar----------------------*/
                            case "rentar":
                                break;

                            case "retornar":
                                break;

                            case "verRentados":
                                break;

                            case "verDisponibles":
                                break;

                            /*----------------------Errores y notificaciones----------------------*/
                            case "no existe":
                                JOptionPane.showMessageDialog(null, "No existe en la base de datos", "No existe", 1);
                                out.writeUTF("stop");
                                out.flush();
                                break;

                            case "id vacio":
                                JOptionPane.showMessageDialog(null, "El campo de id no puede estar vacio", "Campo vacio", 2);
                                out.writeUTF("stop");
                                out.flush();
                                break;

                            case "correcto":
                                JOptionPane.showMessageDialog(null, "Datos almacenados correctamente.", "Info", 1);
                                out.writeUTF("stop");
                                out.flush();
                                break;

                            case "error almacenar":
                                JOptionPane.showMessageDialog(null, "Error al almacenar los datos", "Error", 0);
                                out.writeUTF("stop");
                                out.flush();
                                break;

                            case "duplicado":
                                JOptionPane.showMessageDialog(null, "El dato ya existe.", "Error", 0);
                                out.writeUTF("stop");
                                out.flush();
                                break;

                            case "error":
                                JOptionPane.showMessageDialog(null, "Se precento un error.", "Error", 0);
                                out.writeUTF("stop");
                                out.flush();
                                break;

                            default:
                                mensaje = "exit";
                                System.out.println(mensaje);
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*--------Conexion puerto 6000 salida de archivos---------*/
    public static void serverSalidaJson() {
        try ( Socket socket = new Socket("localhost", 6000)) {
            inF = new DataInputStream(socket.getInputStream());
            outF = new DataOutputStream(socket.getOutputStream());

            salidaFileJson("ClientSide.json");

            inF.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*--------Conexion puerto 7000 entrada de archivos---------*/
    public static void serverEntradaJson() {
        try ( ServerSocket serverSocket = new ServerSocket(7000)) {
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");
            inF = new DataInputStream(clientSocket.getInputStream());
            outF = new DataOutputStream(clientSocket.getOutputStream());

            entradaFileJson("NewFileFromServer.json");

            inF.close();
            outF.close();
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*----------------Recibe archivo JSON-----------------*/
    private static void entradaFileJson(String fileName) throws Exception {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = inF.readLong();     // read file size
        byte[] buffer = new byte[4 * 1024];
        while (size > 0 && (bytes = inF.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }

    /*-----------------Envia archivo JSON-----------------*/
    public static void salidaFileJson(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        // send file size
        outF.writeLong(file.length());
        // break file into chunks
        byte[] buffer = new byte[4 * 1024];
        while ((bytes = fileInputStream.read(buffer)) != -1) {
            outF.write(buffer, 0, bytes);
            outF.flush();
        }
        fileInputStream.close();
    }

    /*------------------------JSON to Object---------------------*/
    public static Cliente objetodeJsonCLIENTE() {
        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("NewFileFromServer.json"));
            Cliente cli = gson.fromJson(reader, Cliente.class);
            reader.close();

            return cli;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static UserAdmin objetodeJsonUSER() {
        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("NewFileFromServer.json"));
            UserAdmin usu = gson.fromJson(reader, UserAdmin.class);
            reader.close();

            return usu;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Auto objetodeJsonAUTO() {
        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("NewFileFromServer.json"));
            Auto auto = gson.fromJson(reader, Auto.class);
            reader.close();

            return auto;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*------------------------Object to JSON---------------------*/
    public static boolean toJson(Cliente cli) {
        boolean done;
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("ClientSide.json"));

            gson.toJson(cli, writer);
            writer.close();

            serverSalidaJson();

            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }
}
