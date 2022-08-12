package Servidor;

/**
 *
 * @author jorge
 */
public class UserAdmin extends Persona implements IOperaciones {

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

    @Override
    public void Create(Object obj) {
    }

    @Override
    public Object Retrieve(String key) {
        return null;
    }

    @Override
    public void Update(Object obj) {
    }

    @Override
    public void Delete(String key) {
    }

}
