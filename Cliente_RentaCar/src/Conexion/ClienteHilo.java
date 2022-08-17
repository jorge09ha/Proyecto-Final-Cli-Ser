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

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private Socket sc;
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

        try {

            String strFromClient = "", strToClient = "", msg = "";
            strToClient = tarea;

            Cliente cli = new Cliente();
            Auto aut = new Auto();
            UserAdmin usu = new UserAdmin();

            while (!strFromClient.equals("stop")) {
                switch (strToClient) {

                    /*----------------------Clientes----------------------*/
                    case "modificarCliente":
                        out.writeUTF(strToClient); //Task
                        out.flush();

                        strFromClient = in.readUTF();

                        out.writeUTF(id); //send id
                        out.flush();

                        strFromClient = in.readUTF(); //doit
                        msg = strFromClient;

                        if ("correcto".equals(strFromClient)) {
                            strToClient = "servidorA";
                            out.writeUTF(strToClient);
                            out.flush();
                            entradaArchivoJson();

                            strFromClient = in.readUTF();

                            //if("objetodeJason()".equals(strFromClient))
                            cli = archivoJsonAObjetoCLIENTE();

                            out.writeUTF("stop");
                            out.flush();

                            //return cli;
                        }

                        out.writeUTF("stop");
                        out.flush();

                        strFromClient = in.readUTF();

                        break;

                    case "eliminarCliente":
                        out.writeUTF(strToClient); //Task
                        out.flush();

                        strFromClient = in.readUTF();

                        out.writeUTF(id); //send id
                        out.flush();

                        strFromClient = in.readUTF(); //correcto
                        msg = strFromClient;
                        cli.setNombre(msg);

                        out.writeUTF("stop");
                        out.flush();

                        strFromClient = in.readUTF();

                        break;

                    case "buscarCliente":
                        out.writeUTF(strToClient); //Task
                        out.flush();

                        strFromClient = in.readUTF();

                        out.writeUTF(id); //send id
                        out.flush();

                        strFromClient = in.readUTF(); // msg correcto
                        msg = strFromClient;

                        if ("correcto".equals(strFromClient)) {
                            strToClient = "servidorA";
                            out.writeUTF(strToClient);
                            out.flush();

                            entradaArchivoJson();

                            strFromClient = in.readUTF();

                            if ("objetodeJasonCLIENTE()".equals(strFromClient)) {
                                cli = archivoJsonAObjetoCLIENTE();
                            }

                            out.writeUTF("stop");
                            out.flush();

                            strFromClient = in.readUTF();

                        } else {

                            out.writeUTF("stop");
                            out.flush();

                            strFromClient = in.readUTF();
                        }
                        break;

                    case "registrarCliente":
                        out.writeUTF(strToClient); //se envia el tipo de tarea al servidor. 
                        out.flush();

                        strFromClient = in.readUTF();
                        msg = strFromClient;
                        cli.setNombre(msg);

                        out.writeUTF("stop");
                        out.flush();

                        strFromClient = in.readUTF();

                        break;

                    /*----------------------Usuarios----------------------*/
                    case "agregarUsuario":
                        break;

                    case "editarUsuario":

                        break;

                    case "buscarUsuario":
                        break;

                    case "borrarUsuario":
                        break;

                    /*----------------------Autos----------------------*/
                    case "agregarAuto":
                        break;

                    case "editarAuto":
                        break;

                    case "buscarAuto":
                        break;

                    case "borrarAuto":
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
                }

                /*----------------------Errores y notificaciones----------------------*/
                switch (msg) {

                    case "correcto":
                        JOptionPane.showMessageDialog(null, "Accion ejecutada de forma correcta.", "Info", 1);

                        break;

                    case "duplicado":
                        JOptionPane.showMessageDialog(null, "La Cédula ya existe.", "Error", 0);
                        break;

                    case "no existe":
                        JOptionPane.showMessageDialog(null, "No existe en la base de datos", "No existe", 1);
                        break;

                    case "id vacio":
                        JOptionPane.showMessageDialog(null, "El campo de identificación no puede estar vacio", "Campo vacio", 2);
                        break;

                    case "error base":
                        JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 1);
                        break;
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
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

    /*--------Conexion puerto 5007 entrada de archivos---------*/
    public static void entradaArchivoJson() {
        try ( ServerSocket serverSocket = new ServerSocket(5007)) {
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            entradaFragmentosArchivo("NewFileFromServer.json");

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*----------------Recibe y envia archivo JSON-----------------*/
    private static void entradaFragmentosArchivo(String fileName) throws Exception {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4 * 1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();

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

    /*------------------------JSON to Object---------------------*/
    public static Cliente archivoJsonAObjetoCLIENTE() {//objetodeJason
        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("NewFileFromServer.json"));

            Cliente cli = gson.fromJson(reader, Cliente.class);

            //System.out.println("Lectura del archivo: Cedula: "+cli.getCedula()+" Nombre: "+cli.getNombre()+" Apellido1: "+cli.getApellido1()+" Apellido2: "+cli.getApellido2()+" Correo: "+cli.getEmail()+" Telefono: "+cli.getTelefono());
            reader.close();

            return cli;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static UserAdmin archivoJsonAObjetoUSER() {
        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("NewFileFromServer.json"));
            UserAdmin usu = gson.fromJson(reader, UserAdmin.class
            );
            reader.close();

            return usu;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Auto archivoJsonAObjetoAUTO() {
        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("NewFileFromServer.json"));
            Auto auto = gson.fromJson(reader, Auto.class
            );
            reader.close();

            return auto;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*------------------------Object to JSON---------------------*/
    public static boolean objetoaJsonCLIENTE(Cliente cli) {
        boolean done;
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("ClientSide.json"));

            gson.toJson(cli, writer);
            writer.close();

            envioArchivoJson();

            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }

    public static boolean objetoaJsonUSER(UserAdmin usu) {
        boolean done;
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("ClientSide.json"));

            gson.toJson(usu, writer);
            writer.close();

            envioArchivoJson();

            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }

    public static boolean objetoaJsonAUTO(Auto aut) {
        boolean done;
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("ClientSide.json"));

            gson.toJson(aut, writer);
            writer.close();

            envioArchivoJson();

            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }
}
