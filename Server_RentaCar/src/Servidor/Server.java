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

public class Server {

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private static final String URL = "jdbc:mysql://localhost:3306/rentacar";
    private static final String USERNAME = "root";
    private static final String PASS = "admin01";
    private static String userL;
    PreparedStatement ps;
    ResultSet rs;

    public static void main(String[] args) {
        conexionSocket();
        CMsgStream();
    }

    public static void CMsgStream() {
        Socket socket = null;
        DataInputStream din = null;
        DataOutputStream dout = null;
        BufferedReader br = null;

        try {
            socket = new Socket("localhost", 7777);
            System.out.println(socket + " connected.");
            din = new DataInputStream(socket.getInputStream());

            OutputStream outputStream = socket.getOutputStream();
            dout = new DataOutputStream(outputStream);

            String strFromServer = "", strToClient = "";

            while (!strToClient.equals("stop")) {
                strFromServer = din.readUTF();// Recibe task

                if ("registrarcliente()".equals(strFromServer)) { //server

                    strToClient = registrarcliente(); // client

                    dout.writeUTF(strToClient); // cliente
                    dout.flush();

                    strFromServer = din.readUTF(); // lee msg stop

                }

                if ("buscarcliente".equals(strFromServer)) {

                    dout.writeUTF("id"); // preguta por id //linea 61
                    dout.flush();

                    String id;
                    id = din.readUTF(); //recibe el ID

                    strToClient = buscarcliente(id); // client                    
                    dout.writeUTF(strToClient); // cliente
                    dout.flush();

                    strFromServer = din.readUTF();
                    if ("servidorA".equals(strFromServer)) {
                        envioJson();
                    }
                    strToClient = "objetodeJason()"; // client                    
                    dout.writeUTF(strToClient); // cliente
                    dout.flush();

                    strFromServer = din.readUTF(); // lee msg stop

                }

                strFromServer = "stop";
                dout.writeUTF(strFromServer);
                dout.flush();

            }

        } catch (Exception exe) {
            exe.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (din != null) {
                    din.close();
                }

                if (dout != null) {
                    dout.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void conexionSocket() {
        try ( ServerSocket serverSocket = new ServerSocket(5000)) {// server archives
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            receiveFile("NewFileFromClient.json");

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
            System.out.println("Archive reiceved ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Cliente objetodeJason() {
        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("NewFileFromClient.json"));

            Cliente cli = gson.fromJson(reader, Cliente.class);

            //System.out.println("Lectura del archivo: Cedula: "+cli.getCedula()+" Nombre: "+cli.getNombre()+" Apellido1: "+cli.getApellido1()+" Apellido2: "+cli.getApellido2()+" Correo: "+cli.getEmail()+" Telefono: "+cli.getTelefono());
            reader.close();

            return cli;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*Metodo Recibe un archivo por la conexion de Socket*/
    private static void receiveFile(String fileName) throws Exception {
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

    /*  Fin Metodo Recibe un archivo por la conexion de Socket*/

 /*  Conexion con la base de datos */
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

    /* Fin Conexion con la base de datos */
    public static void envioJson() {
        try ( Socket socket = new Socket("localhost", 5007)) {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            sendFile("ServerSide.json");

            dataInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendFile(String path) throws Exception {
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

    public static boolean toJson(Cliente cli) {
        boolean done;
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("ServerSide.json"));
//                    System.out.println("The JSON representation of Object User is ");    
//                    System.out.println(new Gson().toJson(cli));

            gson.toJson(cli, writer);
            writer.close();

            //envioJson();
            //Abir comunicacion manejo de errores
            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }

//    
//    
//    
//    
    /*CRUD Clientes*/
//    
//    
//    
//    
    public static String buscarcliente(String id) {

        Cliente cli = new Cliente();
        Connection conn = getConnection();
        String buscar = id;
        String msg;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la identificacion")) {
                String sql = "SELECT * FROM clientes WHERE idcliente = '" + id + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (!rs.isBeforeFirst()) {
                    msg = "no existe";
                    return msg;
                }

                while (rs.next()) {
                    cli.setCedula(rs.getString("idcliente"));
                    cli.setNombre(rs.getString("nombre"));
                    cli.setApellido1(rs.getString("apellido1"));
                    cli.setApellido2(rs.getString("apellido2"));
                    cli.setEmail(rs.getString("correoelectronico"));
                    cli.setTelefono(rs.getString("telefono"));

                    toJson(cli);

                    //envioJson();
                    msg = "doit";
                    return msg;

                }
            } else {
                msg = "id vacio";
                return msg;
            }
        } catch (Exception e) {
            msg = "error base";
            return msg;
        }
        return "Error";
    }

    public static String registrarcliente() {

        int resultado = 0;
        String msg = "";
        Connection conn = getConnection();

        Cliente cli = objetodeJason();

        try {

            String sql = "INSERT INTO clientes  Values ('" + cli.getCedula() + "','" + cli.getNombre() + "','" + cli.getApellido1() + "','" + cli.getApellido2() + "','" + cli.getEmail() + "','" + cli.getTelefono() + "')";
            Statement st = conn.createStatement();
            resultado = st.executeUpdate(sql);

            if (resultado > 0) {
                msg = "correcto";
                return msg;
            } else {
                msg = "error almacenar";
                return msg;
            }

        } catch (Exception e) {
            msg = "duplicado";
            return msg;
        }
    }

//     
//     
//     
//     
    /*Fin CRUD CLientes*/
//     
//     
//     
}
