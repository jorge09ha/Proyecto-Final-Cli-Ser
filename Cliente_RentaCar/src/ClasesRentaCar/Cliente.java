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
public class Cliente extends Persona implements IntJsonObj<Cliente> {

    /*
    Esta clase hereda de persona e incorpora dos nuevas variables email y teléfono.
     */
    protected String email, telefono;

    public Cliente(String email, String telefono, String cedula, String nombre, String apellido1, String apellido2) {
        super(cedula, nombre, apellido1, apellido2);
        this.email = email;
        this.telefono = telefono;
    }

    public Cliente() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /*------------------------JSON to Object---------------------*/
    @Override
    public Cliente archivoJsonAObjeto() {
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

    /*------------------------Object to JSON---------------------*/
    @Override
    public boolean objetoAJson(Cliente o) {
        boolean done;
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("ClientSide.json"));

            gson.toJson(o, writer);
            writer.close();

            envioArchivoJson();

            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }

}
