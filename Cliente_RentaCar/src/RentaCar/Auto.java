package RentaCar;

/**
 *
 * @author jorge
 */
public class Auto extends Vehiculo implements IOperaciones {

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
