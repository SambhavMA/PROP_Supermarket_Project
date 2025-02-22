package utils;

/**
 * @author Joan Gomez Catala (joan.gomez.catala@estudiantat.upc.edu)
 * <p>Clase que representa un par de objetos</p>
 */
public class Pair <T1,T2>{
    public T1 first;
    public T2 second;

    public Pair() {}

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    public T1 first() {
        return first;
    }
    public T2 second() {
        return second;
    }
}
