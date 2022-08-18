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
public class Auto extends Vehiculo implements IntJsonObj<Auto> {

    /*
    Esta clase hereda de Vehiculo e incorpora una nueva variable rentar.
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

    /*------------------------JSON to Object---------------------*/
    @Override
    public Auto archivoJsonAObjeto() {
        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("NewFileFromServer.json"));
            Auto auto = gson.fromJson(reader, Auto.class
            );
            reader.close();

            return auto;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*------------------------Object to JSON---------------------*/
    @Override
    public boolean objetoAJson(Auto aut) {
        boolean done;
        try {
            Gson gson = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get("ClientSide.json"));

            gson.toJson(aut, writer);
            writer.close();

            envioArchivoJson();

            return done = true;

        } catch (Exception e) {
            e.printStackTrace();
            return done = false;
        }
    }

}
