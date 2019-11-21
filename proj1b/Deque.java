public interface Deque<T> {
    private int size;

    public void addFirst(T item);
    public void addLast(T item);

    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);


    public boolean isEmpty() {
        return size == 0;
    }

}
