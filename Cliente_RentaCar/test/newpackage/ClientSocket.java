package newpackage;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import rentACar.Auto;
import rentACar.Cliente;
import rentACar.UserAdmin;

public class ClientSocket {

 
    private static DataInputStream din = null;
    private static ServerSocket serverSocket = null;
    private static DataOutputStream dout = null;
    private static BufferedReader br = null;

    /*-----------------Conexion Port 5000-----------------*/
    public static Object clientToServer(String task, String id) {
        try {
            serverSocket = new ServerSocket(5000);// server chat
            System.out.println("Server is Waiting for client request... ");

            Socket socket = serverSocket.accept();
            System.out.println("Socket connected ");
            din = new DataInputStream(socket.getInputStream());

            OutputStream outputStream = socket.getOutputStream();
            dout = new DataOutputStream(outputStream);

            String strFromClient = "", strToClient = task, msg = "200";
            while (!strFromClient.equals("stop")) {
//Switch---------------------------------
                switch (strFromClient) {
                    case "buscarCliente":
                        dout.writeUTF(strToClient); //Task
                        dout.flush();
                        strFromClient = din.readUTF();
                        dout.writeUTF(id); //send id
                        dout.flush();
                        strFromClient = din.readUTF(); //doit
                        strToClient = "servidorA";
                        dout.writeUTF(strToClient);
                        dout.flush();

                    case "buscarUsuario":
                        dout.writeUTF(strToClient); //Task
                        dout.flush();
                        strFromClient = din.readUTF();
                        dout.writeUTF(id); //send id
                        dout.flush();
                        strFromClient = din.readUTF(); //doit
                        strToClient = "servidorA";
                        dout.writeUTF(strToClient);
                        dout.flush();

                    case "buscarAuto":
                        dout.writeUTF(strToClient); //Task
                        dout.flush();
                        strFromClient = din.readUTF();
                        dout.writeUTF(id); //send id
                        dout.flush();
                        strFromClient = din.readUTF(); //doit
                        strToClient = "servidorA";
                        dout.writeUTF(strToClient);
                        dout.flush();

                    case "doit":
                        serverInJson();
                        System.out.println("Im in if doit");
                        strFromClient = din.readUTF();

                    case "objetodeJsonCLIENTE":
                        Cliente cli = new Cliente();
                        cli = objetodeJsonCLIENTE();
                        dout.writeUTF("stop");
                        dout.flush();
                        return cli;

                    case "objetodeJsonUSER":
                        UserAdmin usu = new UserAdmin();
                        usu = objetodeJsonUSER();
                        dout.writeUTF("stop");
                        dout.flush();
                        return usu;

                    case "objetodeJsonAUTO":
                        Auto aut = new Auto();
                        aut = objetodeJsonAUTO();
                        dout.writeUTF("stop");
                        dout.flush();
                        return aut;

                    case "no existe":
                        JOptionPane.showMessageDialog(null, "No existe en la base de datos", "No existe", 1);
                        dout.writeUTF("stop");
                        dout.flush();

                    case "id vacio":
                        JOptionPane.showMessageDialog(null, "El campo de identificación no puede estar vacio", "Campo vacio", 2);
                        dout.writeUTF("stop");
                        dout.flush();

                    case "correcto":
                        JOptionPane.showMessageDialog(null, "Datos almacenados correctamente.", "Info", 1);
                        dout.writeUTF("stop");
                        dout.flush();

                    case "error almacenar":
                        JOptionPane.showMessageDialog(null, "Error al almacenar los datos", "Error", 0);
                        dout.writeUTF("stop");
                        dout.flush();

                    case "duplicado":
                        JOptionPane.showMessageDialog(null, "El dato ya existe.", "Error", 0);
                        dout.writeUTF("stop");
                        dout.flush();

                    case "error":
                        JOptionPane.showMessageDialog(null, "Se precento un error.", "Error", 0);
                        dout.writeUTF("stop");
                        dout.flush();

                    default:
                        return null;

                }
            }
        } catch (Exception exe) {
            exe.printStackTrace();
        } finally {
            try {

                if (din != null) {
                    din.close();
                }

                if (dout != null) {
                    dout.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*--------Conexion puerto 6000 salida de archivos---------*/
    public static void serverOutJson() {
        try ( Socket socket = new Socket("localhost", 6000)) {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            outFileJson("ClientSide.json");

            dataInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*--------Conexion puerto 7000 entrada de archivos---------*/
    public static void serverInJson() {
        try ( ServerSocket serverSocket = new ServerSocket(7000)) {
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            inFileJson("NewFileFromServer.json");

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*----------------Recibe archivo JSON-----------------*/
    private static void inFileJson(String fileName) throws Exception {
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
    public static void outFileJson(String path) throws Exception {
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

            serverOutJson();

            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }

}
