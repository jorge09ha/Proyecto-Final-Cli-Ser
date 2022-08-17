package ClasesRentaCar;

/**
 * @author Jorge Hernandez Araya
 */

public interface IOperaciones<T> {

    void Create(T obj);

    T Retrieve(String key);

    void Update(T obj);

    void Delete(String key);

}
