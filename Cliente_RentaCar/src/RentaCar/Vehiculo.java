package RentaCar;

/**
 *
 * @author jorge
 */
public abstract class Vehiculo {

    protected String placa, marca, modelo, annio, transmision;

    public Vehiculo(String placa, String marca, String modelo, String combustible, String transmision) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.annio = combustible;
        this.transmision = transmision;
    }

    public Vehiculo() {
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

    public void setAnnio(String combustible) {
        this.annio = combustible;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }
    
    

}
