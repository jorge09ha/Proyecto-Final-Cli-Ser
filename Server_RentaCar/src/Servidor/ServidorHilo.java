package Servidor;

import ClasesRentaCar.UserAdmin;
import ClasesRentaCar.Auto;
import ClasesRentaCar.Cliente;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import ClasesRentaCar.Cliente;
import ClasesRentaCar.Rentar;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Jorge Hernandez Araya
 */
public class ServidorHilo extends Thread {

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private static final String URL = "jdbc:mysql://localhost:3306/rentacar";
    private static final String USERNAME = "root";
    private static final String PASS = "admin01";
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

    /*
    Clase donde se inicia la comunicación con el cliente y el servidor para hacer la ejecución según la tarea
     */
    @Override
    public void run() {

        try {

            String strFromServer = "", strToClient = "", id = "";

            strFromServer = in.readUTF();// Recibe la tarea
            System.out.println("-Se resive la tarea: " + strFromServer);//print--------------->

            while (!strFromServer.equals("stop")) {

                /*
                 *
                 * Switch ejecuta el metodo sugun la tarea.
                 *
                 */
                switch (strFromServer) {
                    /*----------------------Clientes----------------------*/
                    case "registrarCliente"://------------------------------------> Reguistra Clientes

                        strToClient = registrarCliente(); // el metodo hace un return tipo String con el resultado de lo que hiso
                        System.out.println("-Resultado: " + strToClient);//print---------------#

                        out.writeUTF(strToClient); // Se envia a cliente el resulado del registro
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop del cliente

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "eliminarCliente"://------------------------------------> Elimina Clientes
                        out.writeUTF("id"); // preguta por id
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = eliminarCliente(id); // client     
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "buscarCliente"://------------------------------------> Busca Clentes
                        out.writeUTF("id"); // preguta por id 
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = buscarCliente(id); // client
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        id = strToClient;
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if (id == "correcto") {

                            strFromServer = in.readUTF();

                            if ("servidorA".equals(strFromServer)) {
                                envioArchivoJson();
                                System.out.println("-Server envio json");//print----------------------#
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

                    case "modificarCliente"://------------------------------------> Modifica Clientes

                        out.writeUTF("id"); // preguta por id
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = modificarCliente(id); // client       
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if ("correcto".equals(strToClient)) {
                            strFromServer = in.readUTF();

                            System.out.println("-Server envio json");//print----------------------#
                            envioArchivoJson();

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
                    case "registrarUsuario"://------------------------------------> Reguistra Usuarios

                        strToClient = registrarUsuario(); // el metodo hace un return tipo String con el resultado de lo que hiso
                        System.out.println("-Resultado: " + strToClient);//print---------------#

                        out.writeUTF(strToClient); // Se envia a cliente el resulado del registro
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop del cliente

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "eliminarUsuario"://------------------------------------> Elimina Usuarios
                        out.writeUTF("id"); // preguta por id
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = eliminarUsuario(id); // client            
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "buscarUsuario"://------------------------------------> Busca Usuarios
                        out.writeUTF("id"); // preguta por id 
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = buscarUsuario(id); // client  
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        id = strToClient;
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if (id == "correcto") {

                            strFromServer = in.readUTF();

                            if ("servidorA".equals(strFromServer)) {
                                envioArchivoJson();
                                System.out.println("-Server envio json");//print----------------------#
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

                    case "modificarUsuario"://------------------------------------> Medifica Usuarios

                        out.writeUTF("id"); // preguta por id
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = modificarUsuario(id); // client         
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if ("correcto".equals(strToClient)) {
                            strFromServer = in.readUTF();

                            envioArchivoJson();
                            System.out.println("-Server envio json");//print----------------------#

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

                    case "validarUsuario"://------------------------------------> Validacion de Usuarios login

                        out.writeUTF("id"); // preguta por id 
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = validarUsuario(id); // client                    
                        id = strToClient;
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if (id == "correcto") {

                            strFromServer = in.readUTF();

                            if ("servidorA".equals(strFromServer)) {
                                envioArchivoJson();
                                System.out.println("-Server envio json");//print----------------------#
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


                    /*----------------------Autos----------------------*/
                    case "registrarAuto"://------------------------------------> Reguistra Auto

                        strToClient = registrarAuto(); // el metodo hace un return tipo String con el resultado de lo que hiso
                        System.out.println("-Resultado: " + strToClient);//print---------------#

                        out.writeUTF(strToClient); // Se envia a cliente el resulado del registro
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop del cliente
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "eliminarAuto"://------------------------------------> Elimina Auto

                        out.writeUTF("id"); // preguta por id
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = eliminarAuto(id); // client    
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "buscarAuto"://------------------------------------> Busca Auto

                        out.writeUTF("id"); // preguta por id 
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = buscarAuto(id); // client      
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        id = strToClient;
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if (id == "correcto") {

                            strFromServer = in.readUTF();

                            if ("servidorA".equals(strFromServer)) {
                                envioArchivoJson();
                                System.out.println("-Server envio json");//print----------------------#
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

                    case "modificarAuto"://------------------------------------> Modifica Auto

                        out.writeUTF("id"); // preguta por id //linea 61
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = modificarAuto(id); // client   
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if ("correcto".equals(strToClient)) {
                            strFromServer = in.readUTF();

                            envioArchivoJson();
                            System.out.println("-Server envio json");//print----------------------#

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
                    case "rentar"://------------------------------------> Rentar

                        strToClient = rentar(); // el metodo hace un return tipo String con el resultado de lo que hiso
                        System.out.println("-Resultado: " + strToClient);//print---------------#

                        out.writeUTF(strToClient); // Se envia a cliente el resulado del registro
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop del cliente

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "retornar":
                        out.writeUTF("id"); // preguta por id
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = retornar(id); // client    
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();

                        break;

                    case "buscarRentar"://------------------------------------> Buscar rentados
                        out.writeUTF("id"); // preguta por id 
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = buscarRentar(id); // client      
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        id = strToClient;
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if (id == "correcto") {

                            strFromServer = in.readUTF();

                            if ("servidorA".equals(strFromServer)) {
                                envioArchivoJson();
                                System.out.println("-Server envio json");//print----------------------#
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

                    case "buscarDisponibles"://------------------------------------> Buscar autos disponibles
                        out.writeUTF("id"); // preguta por id 
                        out.flush();

                        id = in.readUTF(); //recibe el ID
                        System.out.println("-ID: " + id);//print-------------------------------------#

                        strToClient = buscarDisponibles(id); // client      
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        id = strToClient;
                        out.writeUTF(strToClient); // cliente
                        out.flush();

                        if (id == "correcto") {

                            strFromServer = in.readUTF();

                            if ("servidorA".equals(strFromServer)) {
                                envioArchivoJson();
                                System.out.println("-Server envio json");//print----------------------#
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

                    case "home": //------------------------------------> Datos de la ventana HOME

                        strToClient = contarDisponibles(); // el metodo hace un return tipo String con el resultado de lo que hiso
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient); // Se envia a cliente el resulado del registro
                        out.flush();

                        strFromServer = in.readUTF(); //ok

                        strToClient = contarRentados();
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient);
                        out.flush();

                        strFromServer = in.readUTF(); //ok

                        strToClient = contarUser();
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient);
                        out.flush();

                        strFromServer = in.readUTF();//ok

                        strToClient = contarClientes();
                        System.out.println("-Resultado: " + strToClient);//print---------------#
                        out.writeUTF(strToClient);
                        out.flush();

                        strFromServer = in.readUTF(); // lee msg stop del cliente

                        strToClient = "stop";
                        out.writeUTF(strToClient);
                        out.flush();
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
        System.out.println("*Cliente DESCONECTADO: " + sc.getInetAddress().getHostAddress() + ":" + sc.getPort() + "\n");
    }

    /*--------Conexion puerto 5000 entrada de archivos---------*/
    public static void entradaArchivoJson() {

        try ( ServerSocket serverSocket = new ServerSocket(5000)) {// server archivos
            Socket clientSocket = serverSocket.accept();
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            entradaFragmentosArchivo("NewFileFromClient.json");

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*--------Conexion puerto 5007 salida de archivos---------*/
    public void envioArchivoJson() {

        try ( Socket socket = new Socket(sc.getInetAddress().getHostAddress(), 5007)) {
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

        long size = dataInputStream.readLong();     // Lee el tamaño del archivo
        byte[] buffer = new byte[4 * 1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;      // Lee el tamaño del archivo enviado
        }
        fileOutputStream.close();
    }

    public static void envioFragmentosArchivo(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        //Envia el tamaño del archivo
        dataOutputStream.writeLong(file.length());

        //Dividir el archivo en fragmentos
        byte[] buffer = new byte[4 * 1024];
        while ((bytes = fileInputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
    }

    /*----------------------JSON a Objecto----------------------*/
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

    public static boolean objetoaJsonRENTAR(Rentar rentar) {
        boolean done;
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("ServerSide.json"));

            gson.toJson(rentar, writer);
            writer.close();

            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }

    /*-----------------Objecto a JSON-----------------*/
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

    public static Rentar archivoJsonAObjetoRENTAR(String seleccion) {
        try {
            Gson gson = new Gson();

            Reader reader = null;

            if ("default".equals(seleccion)) {

                reader = Files.newBufferedReader(Paths.get("NewFileFromClient.json"));
            } else {
                reader = Files.newBufferedReader(Paths.get("ServerSide.json"));
            }
            Rentar rentar = gson.fromJson(reader, Rentar.class);

            reader.close();

            return rentar;

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

    public static void crearFactura(Rentar rent) {
        File archivo;
        FileWriter escribir;
        PrintWriter linea;
        String separador = "\n----------------------------------------";

        try {
            File carpeta = new File("reports");
            File[] lista = carpeta.listFiles();
            int facturaNueva = lista.length + 1;

            archivo = new File("reports\\rent-report#" + facturaNueva + ".txt");

            if (archivo.createNewFile()) {
                System.out.println("-Archivo creado: " + archivo);

                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);

                String placa = rent.getPlaca();
                String marca = rent.getMarca();
                String modelo = rent.getModelo();
                String annio = rent.getAnnio();
                String transmision = rent.getTransmision();
                String cedula = rent.getCedula();
                String nombre = rent.getNombre();
                String apellido1 = rent.getApellido1();
                String apellido2 = rent.getApellido2();
                String correoElectronico = rent.getEmail();
                String telefono = rent.getTelefono();

                linea.println("Reporte #" + facturaNueva + separador);
                linea.println("Placa:" + placa + "\nMarca: " + marca + "\nModelo: " + modelo + "\nAño: " + annio + "\nTransmicion: " + transmision + separador
                        + "\nCliente: " + nombre + " " + apellido1 + " " + apellido2 + "\nCedula: " + cedula + "\nCorreo: " + correoElectronico + "\nTelefono: " + telefono + separador);
                linea.close();
            } else {
                System.out.println("-No se creado el archivo: " + archivo);
            }
        } catch (Throwable e) {
            System.err.println("-NO SE CREO EL ARCHIVO\n" + e);
        }

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
                    usu.setCedula(rs.getString("idusuario"));
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
                String sql = "DELETE FROM usuarios WHERE idusuario = '" + id + "'";
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
                    String sql = "UPDATE usuarios SET idusuario = '" + usu.getCedula() + "',nombre = '" + usu.getNombre() + "',apellido1 = '" + usu.getApellido1() + "',apellido2 = '" + usu.getApellido2() + "',usuario = '" + usu.getUser() + "',contrasena = '" + usu.getPass() + "' WHERE idusuario = '" + id + "'";
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

    public static String validarUsuario(String id) {

        UserAdmin usu = new UserAdmin();
        Connection conn = getConnection();
        String buscar = id;
        String msg;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese su nombre de usuario")) {
                String sql = "SELECT * FROM usuarios WHERE usuario = '" + id + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (!rs.isBeforeFirst()) {
                    msg = "no existe";
                    return msg;
                }

                while (rs.next()) {
                    usu.setCedula(rs.getString("idusuario"));
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
                    String sql = "UPDATE autos SET idauto = '" + aut.getPlaca() + "',marca = '" + aut.getMarca() + "',modelo = '" + aut.getModelo() + "',annio = '" + aut.getAnnio() + "',transmision = '" + aut.getTransmision() + "' WHERE idauto = '" + id + "'";
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
    *                   Cuenta datos tablas
    *
     */
    public static String contarDisponibles() {

//        Connection conn = getConnection();
        String msg;

        try {

//            String sql = "SELECT COUNT(rentar) AS cuenta FROM autos WHERE rentar = \"D\";";
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            rs.next();
//            int count = rs.getInt("cuenta");
//            rs.close();
//            msg = Integer.toString(count);
            String aDisponibles;
            ArrayList<Auto> aD = listEstadosHome("D");
            aDisponibles = Integer.toString(aD.size());
            msg = aDisponibles;

            return msg;

        } catch (Exception e) {
            msg = "error base";
            return msg;
        }
    }

    public static String contarClientes() {
        Auto aut = new Auto();
        Connection conn = getConnection();

        String msg;

        try {

            String sql = "SELECT COUNT(idcliente) AS cuenta FROM clientes;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            rs.next();
            int count = rs.getInt("cuenta");
            rs.close();

            //objetoaJsonAUTO(aut);
            msg = Integer.toString(count);

            return msg;

        } catch (Exception e) {
            msg = "error base";
            return msg;
        }
    }

    public static String contarRentados() {

//        Connection conn = getConnection();
        String msg;

        try {

//            String sql = "SELECT COUNT(rentar) AS cuenta FROM autos WHERE rentar = \"R\";";
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            rs.next();
//            int count = rs.getInt("cuenta");
//            rs.close();
//
//            msg = Integer.toString(count);
            String aDisponibles;
            ArrayList<Auto> aD = listEstadosHome("R");
            aDisponibles = Integer.toString(aD.size());
            msg = aDisponibles;

            return msg;

        } catch (Exception e) {
            msg = "error base";
            return msg;
        }
    }

    public static String contarUser() {
        Auto aut = new Auto();
        Connection conn = getConnection();

        String msg;

        try {

            String sql = "SELECT COUNT(usuario) AS cuenta FROM usuarios;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            rs.next();
            int count = rs.getInt("cuenta");
            rs.close();

            //objetoaJsonAUTO(aut);
            msg = Integer.toString(count);

            return msg;

        } catch (Exception e) {
            msg = "error base";
            return msg;
        }
    }

    public static ArrayList<Auto> listEstadosHome(String status) {

        String msg;
        ArrayList<Auto> autosList = new ArrayList<Auto>();
        Connection conn = getConnection();

        try {
            String sql = "SELECT * FROM autos WHERE rentar ='" + status + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Auto aut = new Auto();

                aut.setPlaca(rs.getString("idauto"));
                aut.setMarca(rs.getString("marca"));
                aut.setModelo(rs.getString("modelo"));
                aut.setAnnio(rs.getString("annio"));
                aut.setTransmision(rs.getString("transmision"));
                aut.setRentar(rs.getString("rentar"));
                autosList.add(aut);

            }

        } catch (Exception e) {
            msg = "error base";
        }
        return autosList;
    }


    /*
    *
    *                   Cuenta datos tablas
    *
     */
    public static String tablaAutos() {
        Connection conn = getConnection();

        String msg = "";

        try {
            String sql = "SELECT * FROM autos WHERE rentar = \"D\";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();

        } catch (Exception e) {
            msg = "error base";
            return msg;
        }
        return msg;
    }

    public static String rentar() {
        int resultado = 0;
        String msg = "";

        Connection conn = getConnection();

        Rentar rent = archivoJsonAObjetoRENTAR("default");

        String placa = rent.getPlaca();
        String marca = rent.getMarca();
        String modelo = rent.getModelo();
        String annio = rent.getAnnio();
        String transmision = rent.getTransmision();
        String cedula = rent.getCedula();
        String nombre = rent.getNombre();
        String apellido1 = rent.getApellido1();
        String apellido2 = rent.getApellido2();
        String correoElectronico = rent.getEmail();
        String telefono = rent.getTelefono();

        String sql = "INSERT INTO rentados  Values ('" + placa + "','" + marca + "','" + modelo + "','" + annio
                + "','" + transmision + "','" + cedula + "','" + nombre + "','" + apellido1 + "','" + apellido2
                + "','" + correoElectronico + "','" + telefono + "')";

        try {

            Statement st = conn.createStatement();
            resultado = st.executeUpdate(sql);

            if (resultado > 0) {
                sql = "UPDATE autos SET rentar= 'R' WHERE idauto = '" + rent.getPlaca() + "'";
                conn.createStatement();
                resultado = st.executeUpdate(sql);
                crearFactura(rent);
                msg = "correcto";
                return msg;
            } else {
                msg = "error almacenar";
                return msg;
            }

        } catch (Exception e) {
            System.out.println(e);
            msg = "duplicado";
            return msg;
        }
    }

    public static String retornar(String id) {
        Auto aut = new Auto();
        Connection conn = getConnection();
        int resultado = 0;
        String buscar = id;
        String msg;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la placa")) {
                String sql = "DELETE FROM rentados WHERE idauto = '" + id + "'";
                Statement st = conn.createStatement();
                resultado = st.executeUpdate(sql);

                if (resultado > 0) {
                    sql = "UPDATE autos SET rentar= 'D' WHERE idauto = '" + id + "'";
                    conn.createStatement();
                    resultado = st.executeUpdate(sql);
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

    public static String buscarRentar(String id) {

        Rentar rent = new Rentar();
        Connection conn = getConnection();
        String buscar = id;
        String msg;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la placa")) {
                String sql = "SELECT * FROM rentados WHERE idauto = '" + id + "'";

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (!rs.isBeforeFirst()) {
                    msg = "no existe";
                    return msg;
                }

                while (rs.next()) {
                    rent.setPlaca(rs.getString("idauto"));
                    rent.setMarca(rs.getString("marca"));
                    rent.setModelo(rs.getString("modelo"));
                    rent.setAnnio(rs.getString("annio"));
                    rent.setTransmision(rs.getString("transmision"));
                    rent.setCedula(rs.getString("idcliente"));
                    rent.setNombre(rs.getString("nombre"));
                    rent.setApellido1(rs.getString("apellido1"));
                    rent.setApellido2(rs.getString("apellido2"));
                    rent.setEmail(rs.getString("correoElectronico"));
                    rent.setTelefono(rs.getString("telefono"));

                    objetoaJsonRENTAR(rent);

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

    }

    public static String buscarDisponibles(String id) {
        Auto aut = new Auto();
        Connection conn = getConnection();
        String buscar = id;
        String msg;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la placa")) {
                String sql = "SELECT * FROM autos where idauto='" + id + "' AND rentar='D'";

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
    }

}
