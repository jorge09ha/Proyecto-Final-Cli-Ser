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

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private static final String URL = "jdbc:mysql://localhost:3306/rentacar";
    private static final String USERNAME = "root";
    private static final String PASS = "admin01";
    private static String userL = "running";
    PreparedStatement ps;
    ResultSet rs;
    private Socket sc;
    private DataInputStream in;
    private DataOutputStream out;

    public ServidorHilo(Socket sc, DataInputStream in, DataOutputStream out) {
        this.sc = sc;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {

        try {

            String strFromServer = "", strToClient = "", id = "";

            strFromServer = in.readUTF();// Recibe la tarea

            while (!strFromServer.equals("stop")) {

                /*
                 *
                 * Switch ejecuta el metodo sugun la tarea.
                 *
                 */
                switch (strFromServer) {
                    /*----------------------Clientes----------------------*/
                    case "registrarCliente":

                        strToClient = registrarCliente(); // el metodo hace un return tipo String con el resultado de lo que hiso
                        out.writeUTF(strToClient); // Se envia a cliente el resulado del registro
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop del cliente

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "eliminarCliente":
                        out.writeUTF("id"); // preguta por id //linea 61
                        out.flush();

                        id = in.readUTF(); //recibe el ID

                        strToClient = eliminarCliente(id); // client                    
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "buscarCliente":
                        out.writeUTF("id"); // preguta por id 
                        out.flush();

                        id = in.readUTF(); //recibe el ID

                        strToClient = buscarCliente(id); // client                    
                        id = strToClient;
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if (id == "correcto") {

                            strFromServer = in.readUTF();

                            if ("servidorA".equals(strFromServer)) {
                                envioArchivoJson();
                            }
                            strToClient = "objetodeJasonCLIENTE()"; // client                    
                            out.writeUTF(strToClient); // cliente
                            out.flush();

                            strFromServer = in.readUTF(); // lee msg stop

                            strFromServer = "stop";
                            out.writeUTF(strFromServer); //se envia un stop al cliente
                            out.flush();
                        }

                        strFromServer = in.readUTF(); // lee msg stop

                        strFromServer = "stop";
                        out.writeUTF(strFromServer); //se envia un stop al cliente
                        out.flush();

                        break;

                    case "modificarCliente":

                        out.writeUTF("id"); // preguta por id //linea 61
                        out.flush();

                        id = in.readUTF(); //recibe el ID

                        strToClient = modificarCliente(id); // client                    
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if ("correcto".equals(strToClient)) {
                            strFromServer = in.readUTF();
                            //if("servidorA".equals(strFromServer)){
                            System.out.println("Server envio json: ");
                            envioArchivoJson();
                            //}
                            strToClient = "objetodeJasonCLIENTE()"; // client                    
                            out.writeUTF(strToClient); // cliente
                            out.flush();

                            strFromServer = in.readUTF(); // lee msg stop

                            strFromServer = "stop";
                            out.writeUTF(strFromServer); //se envia un stop al cliente
                            out.flush();
                        }
                        strFromServer = in.readUTF(); // lee msg stop

                        strFromServer = "stop";
                        out.writeUTF(strFromServer); //se envia un stop al cliente
                        out.flush();

                        break;


                    /*----------------------Usuarios------------------*/
                    case "registrarUsuario":

                        strToClient = registrarUsuario(); // el metodo hace un return tipo String con el resultado de lo que hiso
                        out.writeUTF(strToClient); // Se envia a cliente el resulado del registro
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop del cliente

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "eliminarUsuario":
                        out.writeUTF("id"); // preguta por id //linea 61
                        out.flush();

                        id = in.readUTF(); //recibe el ID

                        strToClient = eliminarUsuario(id); // client                    
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "buscarUsuario":
                        out.writeUTF("id"); // preguta por id 
                        out.flush();

                        id = in.readUTF(); //recibe el ID

                        strToClient = buscarUsuario(id); // client                    
                        id = strToClient;
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if (id == "correcto") {

                            strFromServer = in.readUTF();

                            if ("servidorA".equals(strFromServer)) {
                                envioArchivoJson();
                            }
                            strToClient = "objetodeJasonUSER()"; // client                    
                            out.writeUTF(strToClient); // cliente
                            out.flush();

                            strFromServer = in.readUTF(); // lee msg stop

                            strFromServer = "stop";
                            out.writeUTF(strFromServer); //se envia un stop al cliente
                            out.flush();
                        }

                        strFromServer = in.readUTF(); // lee msg stop

                        strFromServer = "stop";
                        out.writeUTF(strFromServer); //se envia un stop al cliente
                        out.flush();

                        break;

                    case "modificarUsuario":

                        out.writeUTF("id"); // preguta por id //linea 61
                        out.flush();

                        id = in.readUTF(); //recibe el ID

                        strToClient = modificarUsuario(id); // client                    
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if ("correcto".equals(strToClient)) {
                            strFromServer = in.readUTF();

                            System.out.println("Server envio json: ");
                            envioArchivoJson();

                            strToClient = "objetodeJasonUSER()"; // client                    
                            out.writeUTF(strToClient); // cliente
                            out.flush();

                            strFromServer = in.readUTF(); // lee msg stop

                            strFromServer = "stop";
                            out.writeUTF(strFromServer); //se envia un stop al cliente
                            out.flush();
                        }
                        strFromServer = in.readUTF(); // lee msg stop

                        strFromServer = "stop";
                        out.writeUTF(strFromServer); //se envia un stop al cliente
                        out.flush();

                        break;


                    /*----------------------Autos----------------------*/
                    case "registrarAuto":
                        strToClient = registrarAuto(); // el metodo hace un return tipo String con el resultado de lo que hiso
                        out.writeUTF(strToClient); // Se envia a cliente el resulado del registro
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop del cliente

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "eliminarAuto":
                        out.writeUTF("id"); // preguta por id //linea 61
                        out.flush();

                        id = in.readUTF(); //recibe el ID

                        strToClient = eliminarAuto(id); // client                    
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "buscarAuto":
                        out.writeUTF("id"); // preguta por id 
                        out.flush();

                        id = in.readUTF(); //recibe el ID

                        strToClient = buscarAuto(id); // client                    
                        id = strToClient;
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if (id == "correcto") {

                            strFromServer = in.readUTF();

                            if ("servidorA".equals(strFromServer)) {
                                envioArchivoJson();
                            }
                            strToClient = "objetodeJasonAUTO()"; // client                    
                            out.writeUTF(strToClient); // cliente
                            out.flush();

                            strFromServer = in.readUTF(); // lee msg stop

                            strFromServer = "stop";
                            out.writeUTF(strFromServer); //se envia un stop al cliente
                            out.flush();
                        }

                        strFromServer = in.readUTF(); // lee msg stop

                        strFromServer = "stop";
                        out.writeUTF(strFromServer); //se envia un stop al cliente
                        out.flush();

                        break;

                    case "modificarAuto":

                        out.writeUTF("id"); // preguta por id //linea 61
                        out.flush();

                        id = in.readUTF(); //recibe el ID

                        strToClient = modificarAuto(id); // client                    
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if ("correcto".equals(strToClient)) {
                            strFromServer = in.readUTF();
         
                            System.out.println("Server envio json: ");
                            envioArchivoJson();
                  
                            strToClient = "objetodeJasonAUTO()"; // client                    
                            out.writeUTF(strToClient); // cliente
                            out.flush();

                            strFromServer = in.readUTF(); // lee msg stop

                            strFromServer = "stop";
                            out.writeUTF(strFromServer); //se envia un stop al cliente
                            out.flush();
                        }
                        strFromServer = in.readUTF(); // lee msg stop

                        strFromServer = "stop";
                        out.writeUTF(strFromServer); //se envia un stop al cliente
                        out.flush();

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

                }
            }
        } catch (IOException exe) {
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
        System.out.println("Conexion cerrada con el cliente: " + sc.getInetAddress().getHostAddress());
    }

    /*--------Conexion puerto 5000 entrada de archivos---------*/
    public static void entradaArchivoJson() {
        try ( ServerSocket serverSocket = new ServerSocket(5000)) {// server archives
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            entradaFragmentosArchivo("NewFileFromClient.json");

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
            System.out.println("Archive reiceved ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*--------Conexion puerto 5007 salida de archivos---------*/
    public static void envioArchivoJson() {
        try ( Socket socket = new Socket("localhost", 5007)) {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            envioFragmentosArchivo("ServerSide.json");

            dataInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*----------------------Recibe  y envia archivo JSON----------------------*/
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

    /*----------------------JSON to Object----------------------*/
    public static boolean objetoaJsonCLIENTE(Cliente cli) {
        boolean done;
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("ServerSide.json"));

            gson.toJson(cli, writer);
            writer.close();

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
            Writer writer = Files.newBufferedWriter(Paths.get("ServerSide.json"));

            gson.toJson(usu, writer);
            writer.close();

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
            Writer writer = Files.newBufferedWriter(Paths.get("ServerSide.json"));

            gson.toJson(aut, writer);
            writer.close();

            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }

    /*-----------------Object to JSON-----------------*/
    public static Cliente archivoJsonAObjetoCLIENTE(String seleccion) {
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

    public static UserAdmin archivoJsonAObjetoUSER(String seleccion) {
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

    public static Auto archivoJsonAObjetoAUTO(String seleccion) {
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

                    objetoaJsonCLIENTE(cli);

                    msg = "correcto";
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
        return "error base";
    }//listo

    public static String eliminarCliente(String id) {
        Cliente cli = new Cliente();
        Connection conn = getConnection();
        int resultado = 0;
        String buscar = id;
        String msg;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la identificacion")) {
                String sql = "DELETE FROM clientes WHERE idcliente = '" + id + "'";
                Statement st = conn.createStatement();
                resultado = st.executeUpdate(sql);

                if (resultado > 0) {
                    msg = "correcto";
                    return msg;
                } else {
                    msg = "no existe";
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
    }//listo

    public static String registrarCliente() {
        int resultado = 0;
        String msg = "";
        Connection conn = getConnection();

        Cliente cli = archivoJsonAObjetoCLIENTE("default");

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

    public static String modificarCliente(String id) {

        Cliente cli = new Cliente();
        Connection conn = getConnection();
        String buscar = id;
        String msg;

        int resultado = 0;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la identificacion")) {
                msg = buscarCliente(id);
                if ("correcto".equals(msg)) {
                    cli = archivoJsonAObjetoCLIENTE("default");
                    String sql = "UPDATE clientes SET idcliente = '" + cli.getCedula() + "',nombre = '" + cli.getNombre() + "',apellido1 = '" + cli.getApellido1() + "',apellido2 = '" + cli.getApellido2() + "',correoElectronico = '" + cli.getEmail() + "',telefono = '" + cli.getTelefono() + "' WHERE idcliente = '" + id + "'";
                    Statement st = conn.createStatement();
                    resultado = st.executeUpdate(sql);

                    if (resultado > 0) {
                        msg = "correcto";
                        return msg;
                    } else {
                        msg = "no existe";
                        return msg;
                    }
                }
            }

        } catch (Exception e) {
            msg = "error base";
            return msg;
        }
        return "error base";

    }//listo

    /*
    *
    *                   CRUD Usuarios
    *
     */
    public static String buscarUsuario(String id) {
        UserAdmin usu = new UserAdmin();
        Connection conn = getConnection();
        String buscar = id;
        String msg;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la identificacion")) {
                String sql = "SELECT * FROM usuarios WHERE idusuario = '" + id + "'";
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

                    objetoaJsonUSER(usu);

                    msg = "correcto";
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
        return "error base";
    }//listo

    public static String eliminarUsuario(String id) {
        Cliente cli = new Cliente();
        Connection conn = getConnection();
        int resultado = 0;
        String buscar = id;
        String msg;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la identificacion")) {
                String sql = "DELETE FROM clientes WHERE idcliente = '" + id + "'";
                Statement st = conn.createStatement();
                resultado = st.executeUpdate(sql);

                if (resultado > 0) {
                    msg = "correcto";
                    return msg;
                } else {
                    msg = "no existe";
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
    }

    public static String registrarUsuario() {
        int resultado = 0;
        String msg = "";
        Connection conn = getConnection();

        UserAdmin usu = archivoJsonAObjetoUSER("default");

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

    public static String modificarUsuario(String id) {

        UserAdmin usu = new UserAdmin();
        Connection conn = getConnection();
        String buscar = id;
        String msg;

        int resultado = 0;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la identificacion")) {
                msg = buscarUsuario(id);
                if ("correcto".equals(msg)) {
                    usu = archivoJsonAObjetoUSER("default");
                    String sql = "UPDATE usuarios SET idusuario = '" + usu.getCedula() + "',nombre = '" + usu.getNombre() + "',apellido1 = '" + usu.getApellido1() + "',apellido2 = '" + usu.getApellido2() + "',correoElectronico = '" + usu.getUser() + "',telefono = '" + usu.getPass() + "' WHERE idusuario = '" + id + "'";
                    Statement st = conn.createStatement();
                    resultado = st.executeUpdate(sql);

                    if (resultado > 0) {
                        msg = "correcto";
                        return msg;
                    } else {
                        msg = "no existe";
                        return msg;
                    }
                }
            }

        } catch (Exception e) {
            msg = "error base";
            return msg;
        }
        return "error base";

    }

    /*
    *
    *                   CRUD Autos
    *
     */
    public static String buscarAuto(String id) {
        Auto aut = new Auto();
        Connection conn = getConnection();
        String buscar = id;
        String msg;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la placa")) {
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

                    objetoaJsonAUTO(aut);

                    msg = "correcto";
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
        return "error base";
    }//listo

    public static String eliminarAuto(String id) {
        Auto aut = new Auto();
        Connection conn = getConnection();
        int resultado = 0;
        String buscar = id;
        String msg;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la placa")) {
                String sql = "DELETE FROM autos WHERE idauto = '" + id + "'";
                Statement st = conn.createStatement();
                resultado = st.executeUpdate(sql);

                if (resultado > 0) {
                    msg = "correcto";
                    return msg;
                } else {
                    msg = "no existe";
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
    }//listo

    public static String registrarAuto() {
        int resultado = 0;
        String msg = "";
        Connection conn = getConnection();

        Auto aut = archivoJsonAObjetoAUTO("default");

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

    }//liso

    public static String modificarAuto(String id) {

        Auto aut = new Auto();
        Connection conn = getConnection();
        String buscar = id;
        String msg;

        int resultado = 0;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la placa")) {
                msg = buscarAuto(id);
                if ("correcto".equals(msg)) {
                    aut = archivoJsonAObjetoAUTO("default");
                    String sql = "UPDATE autos SET idauto = '" + aut.getPlaca() + "',marca = '" + aut.getMarca() + "',modelo = '" + aut.getModelo() + "',annio = '" + aut.getAnnio() + "',transmision = '" + aut.getTransmision() + "',rentar = '" + aut.getRentar() + "' WHERE idplaca = '" + id + "'";
                    Statement st = conn.createStatement();
                    resultado = st.executeUpdate(sql);

                    if (resultado > 0) {
                        msg = "correcto";
                        return msg;
                    } else {
                        msg = "no existe";
                        return msg;
                    }
                }
            }

        } catch (Exception e) {
            msg = "error base";
            return msg;
        }
        return "error base";

    }//listo
    /*
    *
    *                   CRUD Rentar
    *
     */
}
