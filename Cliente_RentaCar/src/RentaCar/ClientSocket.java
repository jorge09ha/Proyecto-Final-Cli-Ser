package RentaCar;

import GUI.Dashboard;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.*;

public class ClientSocket {

    private static final String URL = "jdbc:mysql://localhost:3306/rentacar";
    private static final String USERNAME = "root";
    private static final String PASS = "admin01";
    private static String userL;
    PreparedStatement ps;
    ResultSet rs;

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

    public static void validarLogin(String userSelec, String passSelec) {
        int resultado = 0;

        Connection conn = getConnection();

        try {

            String sql = "SELECT * FROM usuarios WHERE usuario = '" + userSelec + "'and contrasena = '" + passSelec + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                resultado = 1;
                if (resultado == 1) {
                    userL = userSelec;
                    Dashboard form = new Dashboard();
                    form.setVisible(true);
                    //this.dispose();
                }
            } else {
                System.out.println(userSelec + " " + passSelec);
                JOptionPane.showMessageDialog(null, "Error usuario o contraseña incorrectos", "Error login", 0);
            }
        } catch (Exception e) {
            System.out.println(userSelec + " " + passSelec);
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);

        }
    }

    public static String getUserL() {
        return userL;
    }

    //**Metodos CRUD de Clientes**
    //Clientes
    public static void borrarcliente(String id) {

        Cliente cli = new Cliente();
        cli = buscarcliente(id);
        int resultado = 0;
        Connection conn = getConnection();

        try {
            String sql = "DELETE FROM clientes WHERE idcliente = '" + cli.getCedula() + "'";
            Statement st = conn.createStatement();
            resultado = st.executeUpdate(sql);
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Datos Eliminados de la base de datos.", "Info", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Error al borrar. " + id + " No exite en la base de datos", "Error", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }
    }

    public static boolean modificarcliente(Cliente cli, String search) {

        int resultado = 0;
        boolean doit = true;
        Connection conn = getConnection();
        String errorM = "";

        if ("".equals(cli.getCedula()) || cli.getCedula().equals(null)) {
            errorM = errorM + "Cédula, ";
            doit = false;
        }

        if ("".equals(cli.getNombre()) || cli.getNombre().equals(null)) {
            errorM = errorM + "Nombre, ";
            doit = false;
        }

        if ("".equals(cli.getApellido1()) || cli.getApellido1().equals(null)) {
            errorM = errorM + "Apellido 1, ";
            doit = false;
        }

        if ("".equals(cli.getApellido2()) || cli.getApellido2().equals(null)) {
            errorM = errorM + "Apellido 2, ";
            doit = false;
        }

        if ("".equals(cli.getEmail()) || cli.getEmail().equals(null)) {
            errorM = errorM + "Correo, ";
            doit = false;
        }

        if ("".equals(cli.getTelefono()) || cli.getTelefono() == null) {
            errorM = errorM + "Télefono, ";
            doit = false;
        }

        if (!"".equals(errorM)) {
            JOptionPane.showMessageDialog(null, "Los campos: " + errorM + "no pueden estar en blanco.", "Error", 0);
        }

        try {
            if (doit) {
                int response = JOptionPane.showConfirmDialog(null, "Desea Modificar al cliente?", "Modificar Cliente", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    String sql = "UPDATE clientes SET idcliente = '" + cli.getCedula() + "',nombre = '" + cli.getNombre() + "',apellido1 = '" + cli.getApellido1() + "',apellido2 = '" + cli.getApellido2() + "',correoElectronico = '" + cli.getEmail() + "',telefono = '" + cli.getTelefono() + "' WHERE idcliente = '" + search + "'";
                    Statement st = conn.createStatement();
                    resultado = st.executeUpdate(sql);

                    if (resultado > 0) {
                        JOptionPane.showMessageDialog(null, "Datos Modificados en la base de datos.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Modificar. No existe en la base de datos", "Error", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Datos no Modificados", "Info", JOptionPane.INFORMATION_MESSAGE);
                }

                return doit;

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }

        return doit;
    }

    public static Cliente buscarcliente(String id) {

        Cliente cli = new Cliente();
        Connection conn = getConnection();
        String buscar = id;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la identificacion")) {
                String sql = "SELECT * FROM clientes WHERE idcliente = '" + id + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "Cliente no existe en la base de datos", "No existe", 1);
                }

                while (rs.next()) {
                    cli.setCedula(rs.getString("idcliente"));
                    cli.setNombre(rs.getString("nombre"));
                    cli.setApellido1(rs.getString("apellido1"));
                    cli.setApellido2(rs.getString("apellido2"));
                    cli.setEmail(rs.getString("correoelectronico"));
                    cli.setTelefono(rs.getString("telefono"));

                    return cli;

                }
            } else {
                JOptionPane.showMessageDialog(null, "El campo de identificación no puede estar vacio", "Campo vacio", 2);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 1);

        }
        return cli;
    }

    public static boolean registrarcliente(Cliente cli) {

        int resultado = 0;
        boolean doit = true;
        String errorR = "";
        Connection conn = getConnection();

        if ("".equals(cli.getCedula()) || cli.getCedula().equals(null)) {
            errorR = errorR + "Cédula, ";
            doit = false;
        }

        if ("Ingrese la identificacion".equals(cli.getCedula())) {
            JOptionPane.showMessageDialog(null, "No se puede almacenar el valor por defecto '\"Ingrese la identificacion\"' del campo identificación.", "Error", 0);
            doit = false;
        }

        if ("".equals(cli.getNombre()) || cli.getNombre().equals(null)) {
            errorR = errorR + "Nombre, ";
            doit = false;
        }

        if ("".equals(cli.getApellido1()) || cli.getApellido1().equals(null)) {
            errorR = errorR + "Apellido 1, ";
            doit = false;
        }

        if ("".equals(cli.getApellido2()) || cli.getApellido2().equals(null)) {
            errorR = errorR + "Apellido 2, ";
            doit = false;
        }

        if ("".equals(cli.getEmail()) || cli.getEmail().equals(null)) {
            errorR = errorR + "Correo, ";
            doit = false;
        }

        if ("".equals(cli.getTelefono()) || cli.getTelefono() == null) {
            errorR = errorR + "Télefono, ";
            doit = false;
        }

        if (!"".equals(errorR)) {
            JOptionPane.showMessageDialog(null, "Los campos: " + errorR + "no pueden estar en blanco.", "Error", 0);
        }

        try {
            if (doit) {
                String sql = "INSERT INTO clientes  Values ('" + cli.getCedula() + "','" + cli.getNombre() + "','" + cli.getApellido1() + "','" + cli.getApellido2() + "','" + cli.getEmail() + "','" + cli.getTelefono() + "')";
                Statement st = conn.createStatement();
                resultado = st.executeUpdate(sql);

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Datos almacenados correctamente.", "Info", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al almacenar los datos", "Error", 0);
                }
            }
        } catch (Exception e) {
            String error = e.getMessage();

            JOptionPane.showMessageDialog(null, error, "Error", 0);
        }
        return doit;
    }

    public static ArrayList<Cliente> listClientes() {

        ArrayList<Cliente> clientesList = new ArrayList<Cliente>();
        Connection conn = getConnection();

        try {
            String sql = "SELECT * FROM clientes ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Cliente cli = new Cliente();

                cli.setCedula(rs.getString("idcliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido1(rs.getString("apellido1"));
                cli.setApellido2(rs.getString("apellido2"));
                cli.setEmail(rs.getString("correoelectronico"));
                cli.setTelefono(rs.getString("telefono"));
                clientesList.add(cli);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }
        return clientesList;

    }

    public static ArrayList<Auto> listEstadosHome(String status) {

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
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }
        return autosList;
    }

    //Usuarios
    public static void borrarUsuario(String id) {
        UserAdmin usu = new UserAdmin();
        usu = buscarUsuario(id);
        int resultado = 0;
        Connection conn = getConnection();

        try {
            String sql = "DELETE FROM usuarios WHERE idusuario = '" + usu.getCedula() + "'";
            Statement st = conn.createStatement();
            resultado = st.executeUpdate(sql);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Datos Eliminados de la base de datos.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al borrar. " + id + " No exite en la base de datos", "Error", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }
    }

    public static void modificarUsuario(UserAdmin usu) {

        int resultado = 0;
        Connection conn = getConnection();

        try {
            String sql = "UPDATE usuarios SET idusuario= '" + usu.cedula + "',nombre = '" + usu.nombre + "',apellido1 = '" + usu.apellido1 + "',apellido2 = '" + usu.apellido2 + "',usuario = '" + usu.user + "',contrasena = '" + usu.pass + "' WHERE idusuario = '" + usu.cedula + "'";
            Statement st = conn.createStatement();
            resultado = st.executeUpdate(sql);

            if (resultado > 0) {
                resultado = 1;
                if (resultado == 1) {
                    JOptionPane.showMessageDialog(null, "Se actualizo la informacion del usuario", "Info", 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al almacenar los datos", "Error", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);

        }
    }

    public static UserAdmin buscarUsuario(String id) {

        UserAdmin usu = new UserAdmin();
        Connection conn = getConnection();
        String buscar = id;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la identificacion")) {
                String sql = "SELECT * FROM usuarios WHERE idusuario = '" + id + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "Usuario no existe en la base de datos", "No existe", 1);
                }

                while (rs.next()) {
                    usu.setCedula(rs.getString("idusuario"));
                    usu.setNombre(rs.getString("nombre"));
                    usu.setApellido1(rs.getString("apellido1"));
                    usu.setApellido2(rs.getString("apellido2"));
                    usu.setUser(rs.getString("usuario"));
                    usu.setPass(rs.getString("contrasena"));

                    return usu;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El campo de identificación no puede estar vacio", "Campo vacio", 2);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }
        return usu;
    }

    public static void registrarUsuario(UserAdmin usu) {

        int resultado = 0;
        boolean doit = true;
        String errorR = "";
        Connection conn = getConnection();

        try {
            String sql = "INSERT INTO usuarios  Values ('" + usu.cedula + "','" + usu.nombre + "','" + usu.apellido1 + "','" + usu.apellido2 + "','" + usu.user + "','" + usu.pass + "')";
            Statement st = conn.createStatement();
            resultado = st.executeUpdate(sql);

            if (resultado > 0) {
                resultado = 1;
                if (resultado == 1) {
                    JOptionPane.showMessageDialog(null, "Datos almacenados correctamente.", "Usuario Registrado", 1);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al almacenar los datos", "Error", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos. \n" + e, "Error", 0);

        }
    }

    public static ArrayList<UserAdmin> listUsuarios() {

        ArrayList<UserAdmin> usersList = new ArrayList<UserAdmin>();
        Connection conn = getConnection();

        try {
            String sql = "SELECT * FROM usuarios ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                UserAdmin usu = new UserAdmin();

                usu.setCedula(rs.getString("idusuario"));
                usu.setNombre(rs.getString("nombre"));
                usu.setApellido1(rs.getString("apellido1"));
                usu.setApellido2(rs.getString("apellido2"));
                usu.setUser(rs.getString("usuario"));
                usu.setPass(rs.getString("contrasena"));
                usersList.add(usu);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }
        return usersList;

    }

    //Autos
    public static void borrarAuto(String id) {
        Auto aut = new Auto();
        aut = buscarAuto(id);
        int resultado = 0;
        Connection conn = getConnection();

        try {
            String sql = "DELETE FROM autos WHERE idauto = '" + id + "'";
            Statement st = conn.createStatement();
            resultado = st.executeUpdate(sql);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Datos Eliminados de la base de datos.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al borrar. " + id + " No exite en la base de datos", "Error", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }
    }

    public static void modificarAuto(Auto aut) {

        int resultado = 0;
        Connection conn = getConnection();

        try {
            String sql = "UPDATE autos SET idauto= '" + aut.placa + "',marca = '" + aut.marca + "',modelo = '" + aut.modelo + "',annio = '" + aut.annio + "',transmision = '" + aut.transmision + "',rentar = '" + aut.rentar + "' WHERE idauto = '" + aut.placa + "'";
            Statement st = conn.createStatement();
            resultado = st.executeUpdate(sql);

            if (resultado > 0) {
                resultado = 1;
                if (resultado == 1) {
                    JOptionPane.showMessageDialog(null, "Se actualizo la informacion del usuario", "Info", 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al almacenar los datos", "Error", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);

        }
    }

    public static Auto buscarAuto(String id) {

        Auto aut = new Auto();
        Connection conn = getConnection();
        String buscar = id;
        int resultado = 0;
        boolean statusAuto = true;

        try {
            if (!buscar.equals("") && !buscar.equals(null) && !buscar.equals("Ingrese la placa")) {
                String sql = "SELECT * FROM autos WHERE idauto = '" + id + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "El auto no existe en la base de datos", "No existe", 1);
                }

                while (rs.next()) {

                    aut.setPlaca(rs.getString("idauto"));
                    aut.setMarca(rs.getString("marca"));
                    aut.setModelo(rs.getString("modelo"));
                    aut.setAnnio(rs.getString("annio"));
                    aut.setTransmision(rs.getString("transmision"));

                    return aut;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El campo de placa no puede estar vacio", "Campo vacio", 2);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);

        }

        return aut;
    }

    public static void registrarAuto(Auto aut) {

        int resultado = 0;
        Connection conn = getConnection();

        try {
            String sql = "INSERT INTO autos  Values ('" + aut.placa + "','" + aut.marca + "','" + aut.modelo + "','" + aut.annio + "','" + aut.transmision + "','" + aut.rentar + "')";
            Statement st = conn.createStatement();
            resultado = st.executeUpdate(sql);

            if (resultado > 0) {
                resultado = 1;
                if (resultado == 1) {
                    JOptionPane.showMessageDialog(null, "Datos almacenados correctamente.", "Info", 1);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al almacenar los datos", "Error", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);

        }
    }

    //Rent a Car
    public static Auto verEstados(String id, String status) {
        Auto aut = new Auto();
        Connection conn = getConnection();
        int resultado = 0;
        String darPlaca;

        try {
            darPlaca = "";

            if (!id.equals("") && !id.equals(null) && !id.equals("Ingrese la placa")) {

                darPlaca = "idauto= '" + id + "' AND";

                String sql = " SELECT * FROM autos WHERE " + darPlaca + " rentar= '" + status + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                darPlaca = null;

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, " no existe en la base de datos", "No existe", 1);
                }

                while (rs.next()) {

                    aut.setPlaca(rs.getString("idauto"));
                    aut.setMarca(rs.getString("marca"));
                    aut.setModelo(rs.getString("modelo"));
                    aut.setAnnio(rs.getString("annio"));
                    aut.setTransmision(rs.getString("transmision"));
                    aut.setRentar(rs.getString("rentar"));

                }
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);

        }

        return aut;

    }

    public static ResultSet listEstados(String status) {

        ArrayList<Auto> autosList = new ArrayList<Auto>();
        Connection conn = getConnection();

        try {
            String sql = "SELECT * FROM autos WHERE rentar ='" + status + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            return rs;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }
        return null;
    }

    public static void addRentados(Auto aut, Cliente cli) {
        int resultado = 0;
        Connection conn = getConnection();

        try {

            String sql = "INSERT INTO rentados  Values ('" + aut.placa + "','" + aut.marca + "','" + aut.modelo + "','"
                    + cli.cedula + "','" + cli.nombre + "','" + cli.apellido1 + "','" + cli.apellido2 + "','" + cli.email + "','" + cli.telefono + "')";
            Statement st = conn.createStatement();
            resultado = st.executeUpdate(sql);

            if (resultado > 0) {
                resultado = 1;
                if (resultado == 1) {
                    sql = "UPDATE autos SET rentar= 'R' WHERE idauto = '" + aut.placa + "'";
                    resultado = st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Datos almacenados correctamente.", "Info", 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al almacenar los datos", "Error", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }
    }

    public static ResultSet listRentados() {

        //ArrayList<Auto> autosList = new ArrayList<Auto>();
        Connection conn = getConnection();
        Auto aut = new Auto();
        Cliente cli = new Cliente();

        try {
            String sql = "SELECT * FROM rentados  ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            return rs;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }
        return null;

    }

    //**Fin Metodos CRUD Cliente**
}
