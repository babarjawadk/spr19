public class OffByOne implements CharacterComparator {

    public boolean equalChars(char x, char y) {
        return Math.abs((int) x - (int) y) == 1;
    }
}
