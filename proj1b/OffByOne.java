/** This class implements CharacterComparator.
 * @author Jawad
 */
public class OffByOne implements CharacterComparator {

    /** equalChars returns true for characters that are different by exactly one. */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs((int) x - (int) y) == 1;
    }
}
