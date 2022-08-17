package ClasesRentaCar;

/**
 * @author Jorge Hernandez Araya
 */
public abstract class Persona {

    /*
    Personas una clase abstracta que incorpora la información básica que 
    después va a ser heredada a clientes y usuarios administradores
     */
    protected String cedula, nombre, apellido1, apellido2;

    public Persona(String cedula, String nombre, String apellido1, String apellido2) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public Persona() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

}
