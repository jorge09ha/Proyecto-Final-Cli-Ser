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
        entradaArchivoJson();
        clienteProtocolo();
    }

    /*-----------------Conexion Port 7000-----------------*/
    public static void clienteProtocolo() {
        Socket socket = null;
        DataInputStream din = null;
        DataOutputStream dout = null;
        BufferedReader br = null;

        try {
            socket = new Socket("localhost", 7000);
            System.out.println(socket + " connected.");
            din = new DataInputStream(socket.getInputStream());

            OutputStream outputStream = socket.getOutputStream();
            dout = new DataOutputStream(outputStream);

            String strFromServer = "", strToClient = "", id = "";

            while (!strFromServer.equals("stop")) {

                switch (strFromServer) {
                    /*----------------------Clientes----------------------*/
                    case "agregarCliente":
                        strToClient = agregarCliente(); // el metodo hace un return tipo String con el resultado de lo que hiso

                        dout.writeUTF(strToClient); // Se envia a cliente el resulado del registro
                        dout.flush();

                        strFromServer = din.readUTF(); // lee msg stop del cliente

                        break;

                    case "buscarCliente":
                        dout.writeUTF("id"); // preguta por id 
                        dout.flush();

                        id = din.readUTF(); //recibe el ID

                        strToClient = buscarCliente(id); // client                    
                        id = strToClient;
                        dout.writeUTF(strToClient); // cliente
                        dout.flush();

                        strFromServer = din.readUTF();
                        if ("servidorA".equals(strFromServer)) {
                            envioArchivoJson();
                        }
                        strToClient = "objetodeJasonCLIENTE"; // client                    
                        dout.writeUTF(strToClient); // cliente
                        dout.flush();

                        if (id == "doit") {
                            dout.writeUTF(id); // cliente
                            dout.flush();
                        }

                        strFromServer = din.readUTF(); // lee msg stop

                        break;

                    case "editarCliente":
                        dout.writeUTF("id"); // preguta por id //linea 61
                        dout.flush();

                        id = din.readUTF(); //recibe el ID

                        strToClient = editarCliente(id); // client                    
                        dout.writeUTF(strToClient); // cliente
                        dout.flush();

                        strFromServer = din.readUTF();
                        if ("servidorA".equals(strFromServer)) {
                            System.out.println("Server envio json: ");
                            envioArchivoJson();
                        }
                        strToClient = "objetodeJason()"; // client                    
                        dout.writeUTF(strToClient); // cliente
                        dout.flush();

                        strFromServer = din.readUTF(); // lee msg stop

                        break;

                    case "eliminarCliente":
                        dout.writeUTF("id"); // preguta por id //linea 61
                        dout.flush();

                        id = din.readUTF(); //recibe el ID

                        strToClient = borrarCliente(id); // client                    
                        dout.writeUTF(strToClient); // cliente
                        dout.flush();

                        strFromServer = din.readUTF(); // lee msg stop

                        break;

                    /*----------------------Usuarios----------------------*/
                    case "agregarUsuario":
                        strToClient = agregarUsuario();
                        dout.writeUTF(strToClient);
                        dout.flush();
                        strFromServer = din.readUTF();

                    case "buscarUsuario":
                        dout.writeUTF("id");
                        dout.flush();
                        id = din.readUTF();
                        strToClient = buscarUsusario(id);
                        dout.writeUTF(strToClient);
                        dout.flush();
                        strFromServer = din.readUTF();
                        if ("servidorA".equals(strFromServer)) {
                            envioArchivoJson();
                        }
                        strToClient = "objetodeJsonUSER";
                        dout.writeUTF(strToClient);
                        dout.flush();
                        strFromServer = din.readUTF();

                    case "editarUsuario":

                    case "eliminarUsuario":
                    /*----------------------Autos----------------------*/
                    case "agregarAuto":
                        strToClient = agregarAuto();
                        dout.writeUTF(strToClient);
                        dout.flush();
                        strFromServer = din.readUTF();
                    case "buscarAuto":
                        dout.writeUTF("id");
                        dout.flush();
                        id = din.readUTF();
                        strToClient = buscarAuto(id);
                        dout.writeUTF(strToClient);
                        dout.flush();
                        strFromServer = din.readUTF();
                        if ("servidorA".equals(strFromServer)) {
                            envioArchivoJson();
                        }
                        strToClient = "objetodeJsonAUTO";
                        dout.writeUTF(strToClient);
                        dout.flush();
                        strFromServer = din.readUTF();

                    case "editarAuto":

                    case "eliminarAuto":

                    /*----------------------Rentar----------------------*/
                    case "rentarAuto":

                    case "retornar Auto":

                    case "verRentados":

                    case "verDisponibles":

                        break;
                    default:

                        throw new AssertionError();
                }

                strFromServer = "stop";
                dout.writeUTF(strFromServer);
                dout.flush();

            }

        } catch (IOException exe) {
        } finally {
            try {

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
            }
        }
    }

    /*-----------------Conexion Port 5000-----------------*/
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

    /*----------------------Port 5007 para archivo----------------------*/
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

    /*----------------------Recibe archivo JSON----------------------*/
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

    /*-----------------Envia archivo JSON-----------------*/
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
    public static Object archivoJsonAObjeto(String seleccion) {
        try {
            Gson gson = new Gson();

            Reader reader = null;

            if ("default".equals(seleccion)) {

                reader = Files.newBufferedReader(Paths.get("NewFileFromClient.json"));
            } else {
                reader = Files.newBufferedReader(Paths.get("ServerSide.json"));
            }
            Cliente cli = gson.fromJson(reader, Cliente.class);

            //System.out.println("Lectura del archivo: Cedula: "+cli.getCedula()+" Nombre: "+cli.getNombre()+" Apellido1: "+cli.getApellido1()+" Apellido2: "+cli.getApellido2()+" Correo: "+cli.getEmail()+" Telefono: "+cli.getTelefono());
            reader.close();

            return cli;

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
//                    System.out.println("The JSON representation of Object User is ");    
//                    System.out.println(new Gson().objetoaJson(cli));

            gson.toJson(obj, writer);
            writer.close();

            //envioJson();
            //Abir comunicacion manejo de errores
            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }

    /*-----------------Conexion SQL-----------------*/
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

        Cliente cli = (Cliente) archivoJsonAObjeto("default");

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

                msg = "correcto";

                return msg;

            } else {
                msg = "id vacio";
                return msg;
            }
        } catch (Exception e) {
            msg = "error base";
            return msg;
        }

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
                    cli = (Cliente) archivoJsonAObjeto("default");
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

        UserAdmin usu = (UserAdmin) archivoJsonAObjeto("default");
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

        Auto aut = (Auto) archivoJsonAObjeto("default");

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
