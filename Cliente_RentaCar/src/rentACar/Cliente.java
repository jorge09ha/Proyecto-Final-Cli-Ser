package rentACar;

/**
 *
 * @author jorge
 */
public class Cliente extends Persona implements IOperaciones {

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