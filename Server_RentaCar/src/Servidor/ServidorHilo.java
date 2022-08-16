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
    private String id;

    public ServidorHilo(Socket sc, DataInputStream in, DataOutputStream out) {
        this.sc = sc;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {

        String msjSalida = null;
        String msjEntrada = null;
        String id = null;
        boolean exit = false;

        while (!exit) {

            try {

                //Envio la solicitid de tarea
                System.out.println("Tarea?");//print--------------->
                out.writeUTF("tarea");
                out.flush();

                // Pido al cliente la tarea.
                tarea = in.readUTF();
                System.out.println("Tarea: " + tarea);//print--------------->

                //Envio la solicitid de tarea
                System.out.println("ID?");//print--------------->
                out.writeUTF("ok");
                out.flush();

                // Pido al cliente el ID de busqueda (Placa o cedula).
                id = in.readUTF();
                System.out.println("Id: " + id);//print--------------->

                //Segun la tarea ejecuta...
                switch (tarea) {

                    /*----------------------Clientes----------------------*/
                    case "agregarCliente":
                        System.out.println("METODO agregarCliente");//print--------------->

                        msjSalida = agregarCliente(); // el metodo hace un return tipo String con el resultado de lo que hizo.

                        out.writeUTF(msjSalida); // Se envia a cliente el resulado del registro.
                        out.flush();

                        msjEntrada = in.readUTF(); // lee msg stop del cliente

                        break;

                    case "buscarCliente":
                        System.out.println("METODO buscarCliente");//print--------------->

                        msjSalida = buscarCliente(id); // client                    
                        id = msjSalida;
                        out.writeUTF(msjSalida); // cliente
                        out.flush();

                        msjEntrada = in.readUTF();
                        if ("servidorA".equals(msjEntrada)) {
                            serverSalidaJson();
                        }
                        msjSalida = "objetodeJasonCLIENTE"; // client                    
                        out.writeUTF(msjSalida); // cliente
                        out.flush();

                        if (id == "doit") {
                            out.writeUTF(id); // cliente
                            out.flush();
                        }

                        msjEntrada = in.readUTF(); // lee msg stop
                        sc.close();
                        break;

                    case "editarCliente":
//                        out.writeUTF("id"); // preguta por id //linea 61
//                        out.flush();
//
//                        id = in.readUTF(); //recibe el ID
//
//                        msjSalida = editarCliente(id); // client                    
//                        out.writeUTF(msjSalida); // cliente
//                        out.flush();
//
//                        msjEntrada = in.readUTF();
//                        if ("servidorA".equals(msjEntrada)) {
//                            System.out.println("Server envio json: ");
//                            serverSalidaJson();
//                        }
//                        msjSalida = "objetodeJason()"; // client                    
//                        out.writeUTF(msjSalida); // cliente
//                        out.flush();
//
//                        msjEntrada = in.readUTF(); // lee msg stop
                        System.out.println("METODO editarCliente");
                        sc.close();
                        break;

                    case "borrarCliente":

                        msjSalida = borrarCliente(id); // client                    
                        out.writeUTF(msjSalida); // cliente
                        out.flush();

                        msjEntrada = in.readUTF(); // lee msg stop
                        System.out.println("METODO borrarCliente");
                        break;

                    /*----------------------Usuarios------------------*/
                    case "agregarUsuario":
//                        msjSalida = agregarUsuario();
//                        out.writeUTF(msjSalida);
//                        out.flush();
//                        msjEntrada = in.readUTF();

                        break;

                    case "buscarUsuario":
//                        out.writeUTF("id");
//                        out.flush();
//                        id = in.readUTF();
//                        msjSalida = buscarUsusario(id);
//                        out.writeUTF(msjSalida);
//                        out.flush();
//                        msjEntrada = in.readUTF();
//                        if ("servidorA".equals(msjEntrada)) {
//                            serverSalidaJson();
//                        }
//                        msjSalida = "objetodeJsonUSER";
//                        out.writeUTF(msjSalida);
//                        out.flush();
//                        msjEntrada = in.readUTF();

                        break;

                    case "editarUsuario":

                        break;

                    case "eliminarUsuario":

                        break;

                    /*----------------------Autos----------------------*/
                    case "agregarAuto":
//                        msjSalida = agregarAuto();
//                        out.writeUTF(msjSalida);
//                        out.flush();
//                        msjEntrada = in.readUTF();

                        break;

                    case "buscarAuto":
//                        out.writeUTF("id");
//                        out.flush();
//                        id = in.readUTF();
//                        msjSalida = buscarAuto(id);
//                        out.writeUTF(msjSalida);
//                        out.flush();
//                        msjEntrada = in.readUTF();
//                        if ("servidorA".equals(msjEntrada)) {
//                            serverSalidaJson();
//                        }
//                        msjSalida = "objetodeJsonAUTO";
//                        out.writeUTF(msjSalida);
//                        out.flush();
//                        msjEntrada = in.readUTF();

                        break;

                    case "editarAuto":

                        break;

                    case "borrarAuto":

                        break;
                    /*----------------------Rentar---------------------*/
                    case "rentar":

                        break;

                    case "retornar":

                        break;

                    case "verRentados":

                        break;

                    case "verDisponibles":

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

    /*
    *
    *                   CRUD Clientes
    *
     */
    public static String buscarCliente(String id) {
        Cliente cli = new Cliente();
        Connection conn = getConnection();
        String msg;

        try {
            if (!id.equals("") && !id.equals(null) && !id.equals("Ingrese la identificacion")) {
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

                    objetoaJson(cli);

                    //envioJson();
                    msg = "doit";
                    return msg;

                }
            } else {
                msg = "id vacio";
                return msg;
            }
        } catch (Exception e) {
            msg = "error almacenar";
            return msg;
        }
        return "Error";
    }

    public static String agregarCliente() {
        int resultado = 0;
        String msg = "";
        Connection conn = getConnection();

        Cliente cli = (Cliente) objetodeJsonCLIENTE("default");

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

    }//liso

    public static String borrarCliente(String id) {

//        Cliente cli = new Cliente();
//        Connection conn = getConnection();
//        int resultado = 0;
//        String buscar = id;
        String msg;
//
//        try {
//            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la identificacion")) {
//                String sql = "DELETE FROM clientes WHERE idcliente = '" + id + "'";
//                Statement st = conn.createStatement();
//                resultado = st.executeUpdate(sql);
//
                msg = "correcto";
//
//                return msg;
//
//            } else {
//                msg = "id vacio";
//                return msg;
//            }
//        } catch (Exception e) {
//            msg = "error base";
//            return msg;
//        }
return msg;
    }

    public static String editarCliente(String id) {

        Cliente cli = new Cliente();
        Connection conn = getConnection();
        String buscar = id;
        String msg;

        int resultado = 0;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la identificacion")) {
                msg = borrarCliente(id);
                if ("doit".equals(msg)) {
                    cli = (Cliente) objetodeJsonCLIENTE("default");
                    String sql = "UPDATE clientes SET idcliente = '" + cli.getCedula() + "',nombre = '" + cli.getNombre() + "',apellido1 = '" + cli.getApellido1() + "',apellido2 = '" + cli.getApellido2() + "',correoElectronico = '" + cli.getEmail() + "',telefono = '" + cli.getTelefono() + "' WHERE idcliente = '" + id + "'";
                    Statement st = conn.createStatement();
                    resultado = st.executeUpdate(sql);

                    if (resultado > 0) {
                        msg = "doit";
                        return msg;
                    } else {
                        msg = "no existe";
                        return msg;
                    }
                }
                msg = "doit";
                return msg;
            }

        } catch (Exception e) {
            msg = "error base";
            return msg;
        }

        return "Error";

    }

    /*
    *
    *                   CRUD Usuarios
    *
     */
    public static String buscarUsusario(String id) {
        UserAdmin usu = new UserAdmin();
        Connection conn = getConnection();
        String msg;

        try {
            if (!id.equals("") && !id.equals(null) && !id.equals("Ingrese la identificacion")) {
                String sql = "SELECT * FROM usuarios WHERE idcliente = '" + id + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (!rs.isBeforeFirst()) {
                    msg = "no existe";
                    return msg;
                }

                while (rs.next()) {
                    usu.setCedula(rs.getString("idcliente"));
                    usu.setNombre(rs.getString("nombre"));
                    usu.setApellido1(rs.getString("apellido1"));
                    usu.setApellido2(rs.getString("apellido2"));
                    usu.setUser(rs.getString("usuario"));
                    usu.setPass(rs.getString("contrasena"));

                    objetoaJson(usu);

                    //envioJson();
                    msg = "doit";
                    return msg;

                }
            } else {
                msg = "id vacio";
                return msg;
            }
        } catch (Exception e) {
            msg = "error almacenar";
            return msg;
        }
        return "error";
    }//listo

    public static String agregarUsuario() {
        int resultado = 0;
        String msg = "";
        Connection conn = getConnection();

        UserAdmin usu = (UserAdmin) objetodeJsonUSER("default");
        try {

            String sql = "INSERT INTO usuarios  Values ('" + usu.getCedula() + "','" + usu.getNombre() + "','" + usu.getApellido1() + "','" + usu.getApellido2() + "','" + usu.getUser() + "','" + usu.getPass() + "')";
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

    }//listo

    /*
    *
    *                   CRUD Autos
    *
     */
    public static String buscarAuto(String id) {
        Auto aut = new Auto();
        Connection conn = getConnection();
        String msg;

        try {
            if (!id.equals("") && !id.equals(null) && !id.equals("Ingrese la placa")) {
                String sql = "SELECT * FROM autos WHERE idauto = '" + id + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (!rs.isBeforeFirst()) {
                    msg = "no existe";
                    return msg;
                }

                while (rs.next()) {
                    aut.setPlaca(rs.getString("idauto"));
                    aut.setMarca(rs.getString("marca"));
                    aut.setModelo(rs.getString("modelo"));
                    aut.setAnnio(rs.getString("annio"));
                    aut.setTransmision(rs.getString("transmision"));
                    aut.setRentar(rs.getString("rentar"));

                    objetoaJson(aut);

                    //envioJson();
                    msg = "doit";
                    return msg;

                }
            } else {
                msg = "id vacio";
                return msg;
            }
        } catch (Exception e) {
            msg = "error almacenar";
            return msg;
        }
        return "Error";

    }

    public static String agregarAuto() {
        int resultado = 0;
        String msg = "";
        Connection conn = getConnection();

        Auto aut = (Auto) objetodeJsonAUTO("default");

        try {

            String sql = "INSERT INTO autos  Values ('" + aut.getPlaca() + "','" + aut.getMarca() + "','" + aut.getModelo() + "','" + aut.getAnnio() + "','" + aut.getTransmision() + "','" + aut.getRentar() + "')";
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

    }//listo

    /*
    *
    *                   CRUD Rentar
    *
     */
}
