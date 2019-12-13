public class DataIndexedCharMap<V> {
    private V[] items;

    public DataIndexedCharMap(int R) {
        items = (V[]) new Object[R];
    }

    public V get(int i) {
        return items[i];
    }

}
