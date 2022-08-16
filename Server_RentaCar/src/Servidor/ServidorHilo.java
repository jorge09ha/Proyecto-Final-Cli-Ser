package Servidor;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorHilo extends Thread {

    private static DataOutputStream outF = null;
    private static DataInputStream inF = null;
    private static final String URL = "jdbc:mysql://localhost:3306/rentacar";
    private static final String USERNAME = "root";
    private static final String PASS = "admin01";
    private static String userL;
    PreparedStatement ps;
    ResultSet rs;
    private Socket sc;
    private DataInputStream in;
    private DataOutputStream out;
    private String tarea;

    public ServidorHilo(Socket sc, DataInputStream in, DataOutputStream out, String tarea) {
        this.sc = sc;
        this.in = in;
        this.out = out;
        this.tarea = tarea;
    }

    @Override
    public void run() {

        boolean exit = false;

        while (!exit) {

            try {
//Segun la tarea ejecuta...
                switch (tarea) {
                    case "agregarCliente":

                        break;

                    case "exit":

                        exit = true;

                        break;

                    default:
                        out.writeUTF("FIN");
                }

            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            // Cierro el socket
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Conexion cerrada con el cliente: " + sc.getInetAddress().getHostAddress());

    }

    /*--------Conexion puerto 6000 entrada de archivos---------*/
    public static void serverEntradaJson() {
        try ( ServerSocket serverSocket = new ServerSocket(6000)) {// server archives
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");
            inF = new DataInputStream(clientSocket.getInputStream());
            outF = new DataOutputStream(clientSocket.getOutputStream());

            entradaFileJson("NewFileFromClient.json");

            inF.close();
            outF.close();
            clientSocket.close();
            System.out.println("Archive reiceved ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*--------Conexion puerto 7000 salida de archivos---------*/
    public static void serverSalidaJson() {
        try ( Socket socket = new Socket("localhost", 7000)) {
            inF = new DataInputStream(socket.getInputStream());
            outF = new DataOutputStream(socket.getOutputStream());

            salidaFileJson("ServerSide.json");

            inF.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*----------------------Recibe archivo JSON----------------------*/
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

    /*---------------------Envia archivo JSON---------------------*/
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

    /*----------------------JSON to Object----------------------*/
    public static Cliente objetodeJsonCLIENTE(String seleccion) {
        try {
            Gson gson = new Gson();

            Reader reader = null;

            if ("default".equals(seleccion)) {

                reader = Files.newBufferedReader(Paths.get("NewFileFromClient.json"));
            } else {
                reader = Files.newBufferedReader(Paths.get("ServerSide.json"));
            }
            Cliente cli = gson.fromJson(reader, Cliente.class);

            reader.close();

            return cli;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static UserAdmin objetodeJsonUSER(String seleccion) {
        try {
            Gson gson = new Gson();

            Reader reader = null;

            if ("default".equals(seleccion)) {

                reader = Files.newBufferedReader(Paths.get("NewFileFromClient.json"));
            } else {
                reader = Files.newBufferedReader(Paths.get("ServerSide.json"));
            }
            UserAdmin usu = gson.fromJson(reader, UserAdmin.class);

            reader.close();

            return usu;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Auto objetodeJsonAUTO(String seleccion) {
        try {
            Gson gson = new Gson();

            Reader reader = null;

            if ("default".equals(seleccion)) {

                reader = Files.newBufferedReader(Paths.get("NewFileFromClient.json"));
            } else {
                reader = Files.newBufferedReader(Paths.get("ServerSide.json"));
            }
            Auto aut = gson.fromJson(reader, Auto.class);

            reader.close();

            return aut;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*-----------------Object to JSON-----------------*/
    public static boolean objetoaJson(Object obj) {
        boolean done;
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("ServerSide.json"));

            gson.toJson(obj, writer);
            writer.close();

            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }

    /*-----------------Conexion MySQL-----------------*/
    public static Connection getConnection() {

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(URL, USERNAME, PASS);

        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }
}
