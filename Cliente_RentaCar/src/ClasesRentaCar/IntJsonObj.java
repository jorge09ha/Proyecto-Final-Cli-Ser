package ClasesRentaCar;

/**
 * @author Jorge Hernandez Araya
 * @param <T>
 */
public interface IntJsonObj<T> {

    /*
    Interfaz para pasar de un archivo json a un objeto y viceversa
     */
    public T archivoJsonAObjeto();

    public boolean objetoAJson(T o);

}
