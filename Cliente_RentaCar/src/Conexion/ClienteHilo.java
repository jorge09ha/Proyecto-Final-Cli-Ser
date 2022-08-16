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
    private Socket sc;
    private DataInputStream in;
    private DataOutputStream out;
    private String tarea, id;

    public ClienteHilo(Socket sc, DataInputStream in, DataOutputStream out, String tarea, String id) {
        this.in = in;
        this.out = out;
        this.tarea = tarea;
        this.id = id;
        this.sc = sc;
    }

    @Override
    public void run() {

        String strFromClient = null;
        String strToClient = null;
        String msg = null;

        try {

            strToClient = tarea;

            Cliente cli = new Cliente();
            Auto aut = new Auto();
            UserAdmin usu = new UserAdmin();

            while (!strFromClient.equals("stop")) {

                switch (strToClient) {

                    /*----------------------Clientes----------------------*/
                    case "agregarCliente":
                        System.out.println("Cliente: Tarea=" + strToClient);//print--------------->
                        out.writeUTF(strToClient); //se envia el tipo de tarea al servidor. 
                        out.flush();

                        strFromClient = in.readUTF();
                        msg = strFromClient;
                        cli.setNombre(msg);

                        out.writeUTF("stop");
                        out.flush();

                        strFromClient = in.readUTF();

                        break;

                    case "borarCliente":
                        System.out.println("Cliente: Tarea=" + strToClient);//print--------------->
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
                        System.out.println("Cliente: Tarea=" + strToClient);//print--------------->
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

                            if ("objetodeJsonCLIENTE()".equals(strFromClient)) {
                                cli = objetodeJsonCLIENTE();
                            }

                            out.writeUTF("stop");
                            out.flush();

                            strFromClient = in.readUTF();

                        }

                        out.writeUTF("stop");
                        out.flush();

                        strFromClient = in.readUTF();

                        break;

                    case "editarCliente":
                        System.out.println("Cliente: Tarea=" + strToClient);//print--------------->
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
                            cli = objetodeJsonCLIENTE();

                            out.writeUTF("stop");
                            out.flush();

                            //return cli;
                        }

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
                        JOptionPane.showMessageDialog(null, "El ID ya existe.", "Error", 0);
                        out.writeUTF("stop");
                        out.flush();
                        break;

                    case "no existe":
                        JOptionPane.showMessageDialog(null, "No existe en la base de datos", "No existe", 1);
                        out.writeUTF("stop");
                        out.flush();
                        break;

                    case "id vacio":
                        JOptionPane.showMessageDialog(null, "El campo de ID no puede estar vacio", "Campo vacio", 2);
                        out.writeUTF("stop");
                        out.flush();
                        break;

                    case "error base":
                        JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 1);
                        out.writeUTF("stop");
                        out.flush();
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

    /*--------Conexion puerto 6000 salida de archivos---------*/
    public static void envioArchivoJson() {
        try ( Socket socket = new Socket("localhost", 6000)) {
            inF = new DataInputStream(socket.getInputStream());
            outF = new DataOutputStream(socket.getOutputStream());

            envioFragmentosArchivo("ClientSide.json");

            inF.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*--------Conexion puerto 7000 entrada de archivos---------*/
    public static void entradaArchivoJson() {
        try ( ServerSocket serverSocket = new ServerSocket(7000)) {
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");
            inF = new DataInputStream(clientSocket.getInputStream());
            outF = new DataOutputStream(clientSocket.getOutputStream());

            entradaFragmentosArchivo("NewFileFromServer.json");

            inF.close();
            outF.close();
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*----------------Recibe archivo JSON-----------------*/
    private static void entradaFragmentosArchivo(String fileName) throws Exception {
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
    public static void envioFragmentosArchivo(String path) throws Exception {
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
            Cliente cli = gson.fromJson(reader, Cliente.class
            );
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
            UserAdmin usu = gson.fromJson(reader, UserAdmin.class
            );
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
