package RentaCar;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

public class ClientSocket {

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private static DataInputStream din = null;
    private static ServerSocket serverSocket = null;
    private static DataOutputStream dout = null;
    private static BufferedReader br = null;

    public static Cliente SMsgStream(String task, String id) {
        try {
            serverSocket = new ServerSocket(7777);// server chat
            System.out.println("Server is Waiting for client request... ");

            Socket socket = serverSocket.accept();
            System.out.println("Socket connected ");
            din = new DataInputStream(socket.getInputStream());

            OutputStream outputStream = socket.getOutputStream();
            dout = new DataOutputStream(outputStream);

            String strFromClient = "", strToClient = task, msg = "200";
            while (!strFromClient.equals("stop")) {

                /*IF de buscar*/
                if ("buscarcliente".equals(strToClient)) {
                    dout.writeUTF(strToClient); //Task
                    dout.flush();

                    strFromClient = din.readUTF();

                    dout.writeUTF(id); //send id
                    dout.flush();

                    strFromClient = din.readUTF(); //doit

                    strToClient = "servidorA";
                    dout.writeUTF(strToClient);
                    dout.flush();
                }

                if ("doit".equals(strFromClient)) {
                    conexionSocket();
                    System.out.println("Im in if doit");

                    strFromClient = din.readUTF();

                    Cliente cli = new Cliente();
                    if ("objetodeJason()".equals(strFromClient)) {
                        cli = objetodeJasonCLIENTE();
                    }

                    dout.writeUTF("stop");
                    dout.flush();

                    return cli;
                }

                if ("no existe".equals(strFromClient)) {
                    JOptionPane.showMessageDialog(null, "No existe en la base de datos", "No existe", 1);
                    dout.writeUTF("stop");
                    dout.flush();
                }

                if ("id vacio".equals(strFromClient)) {
                    JOptionPane.showMessageDialog(null, "El campo de identificación no puede estar vacio", "Campo vacio", 2);
                    dout.writeUTF("stop");
                    dout.flush();
                }

                if ("error base".equals(strFromClient)) {
                    JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 1);
                    dout.writeUTF("stop");
                    dout.flush();
                }

                /*Fin IF de buscar*/
 /*If de registrar*/
                if ("registrarcliente()".equals(strToClient)) {

                }

                if ("correcto".equals(strFromClient)) {
                    JOptionPane.showMessageDialog(null, "Datos almacenados correctamente.", "Info", 1);
                    dout.writeUTF("stop");
                    dout.flush();
                }

                if ("error almacenar".equals(strFromClient)) {
                    JOptionPane.showMessageDialog(null, "Error al almacenar los datos", "Error", 0);
                    dout.writeUTF("stop");
                    dout.flush();
                }

                if ("duplicado".equals(strFromClient)) {
                    JOptionPane.showMessageDialog(null, "La Cédula ya existe.", "Error", 0);
                    dout.writeUTF("stop");
                    dout.flush();
                }

                //strToClient = msg;
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
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void envioJson() {
        try ( Socket socket = new Socket("localhost", 5000)) {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            sendFile("ClientSide.json");

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

    public static boolean toJson(Object obj) {
        boolean done;
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("ClientSide.json"));

            gson.toJson(obj, writer);
            writer.close();

            envioJson();
            //Abrir comunicacion manejo de errores
            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }

    public static void conexionSocket() {
        try ( ServerSocket serverSocket = new ServerSocket(5007)) {
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            receiveFile("NewFileFromServer.json");

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Cliente objetodeJasonCLIENTE() {
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

    public static UserAdmin objetodeJasonUSER() {
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
    
        public static Auto objetodeJasonAUTO() {
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
}
