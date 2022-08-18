package ClasesRentaCar;

import static Conexion.ClienteHilo.envioArchivoJson;
import com.google.gson.Gson;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Jorge Hernandez Araya
 */
public class UserAdmin extends Persona implements IntJsonObj<UserAdmin> {

    /*
    Esta clase hereda de persona e incorpora dos nuevas variables user y pass.
    Indica el usuario y la contraseña de inicio de sesión del aplicativo
     */
    protected String user, pass;

    public UserAdmin(String user, String pass, String cedula, String nombre, String apellido1, String apellido2) {
        super(cedula, nombre, apellido1, apellido2);
        this.user = user;
        this.pass = pass;
    }

    public UserAdmin() {
    }

    @Override
    public String getCedula() {
        return cedula;
    }

    @Override
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getApellido1() {
        return apellido1;
    }

    @Override
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    @Override
    public String getApellido2() {
        return apellido2;
    }

    @Override
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    /*------------------------JSON to Object---------------------*/
    @Override
    public UserAdmin archivoJsonAObjeto() {
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

    /*------------------------Object a JSON---------------------*/
    @Override
    public boolean objetoAJson(UserAdmin usu) {
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

}
