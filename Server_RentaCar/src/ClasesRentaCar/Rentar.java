package ClasesRentaCar;

/**
 * @author Jorge Hernandez Araya
 */
public class Rentar extends Cliente {

    /*
    Esta clase hereda de Cliente e incorpora las variables de vehiculo.
     */
    protected String placa, marca, modelo, annio, transmision;

    public Rentar(String cedula, String nombre, String apellido1, String apellido2, String email, String telefono) {
        super(cedula, nombre, apellido1, apellido2, email, telefono);
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

    @Override

    public String getEmail() {
        return email;
    }

    @Override

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getTelefono() {
        return telefono;
    }

    @Override
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnnio() {
        return annio;
    }

    public void setAnnio(String annio) {
        this.annio = annio;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

}
