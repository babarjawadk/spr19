public class OffByN implements CharacterComparator {

    private int N;

    OffByN(int n) {
        N = n;
    }

    public boolean equalChars(char x, char y) {
        return Math.abs((int) x - (int) y) == N;
    }
}
