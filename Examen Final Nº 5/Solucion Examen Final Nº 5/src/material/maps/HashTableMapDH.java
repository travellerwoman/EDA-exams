package material.maps;

/**
 * @param <K> The hey
 * @param <V> The stored value
 */
public class HashTableMapDH<K, V> extends AbstractHashTableMap<K, V> {

    private int capacidad;

    public HashTableMapDH(int size) {
        super(size);
    }

    public HashTableMapDH() {
        super();
    }

    public HashTableMapDH(int p, int cap) {
        super(p, cap);
    }

    @Override
    protected int offset(K key, int i) {
        //si cambia la capacidad
        //hay utilizar la funcion que recalcule
        return i + capacidad - (hashValue(key) % capacidad);
    }
}
