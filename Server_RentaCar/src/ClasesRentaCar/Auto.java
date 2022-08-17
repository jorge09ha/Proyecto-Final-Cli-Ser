package ClasesRentaCar;

/**
 * @author Jorge Hernandez Araya
 */
public class Auto extends Vehiculo {

    /*
    Esta clase hereda de auto e incorpora una nueva variable rentar.
    indica con una R si el vehículo está rentado o una D si el vehículo está disponible.
     */
    protected String rentar;

    public Auto(String rentar, String placa, String marca, String modelo, String annio, String transmision) {
        super(placa, marca, modelo, annio, transmision);
        this.rentar = rentar;
    }

    public Auto() {
    }

    public String getRentar() {
        return rentar;
    }

    public void setRentar(String rentar) {
        this.rentar = rentar;
    }

    @Override
    public String getPlaca() {
        return placa;
    }

    @Override
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String getMarca() {
        return marca;
    }

    @Override
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String getAnnio() {
        return annio;
    }

    @Override
    public void setAnnio(String annio) {
        this.annio = annio;
    }

    @Override
    public String getTransmision() {
        return transmision;
    }

    @Override
    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

}
