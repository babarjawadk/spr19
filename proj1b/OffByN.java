/** This class implements CharacterComparator.
 * @author Jawad
 */
public class OffByN implements CharacterComparator {

    /** Character off by n. */
    private int offByN;

    /** The OffByN constructor should create objects whose
     * equalChars method return true for characters that are off by N.
     */
    public OffByN(int n) {
        offByN = n;
    }

    /** equalChars returns true for characters that are different by exactly N. */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs((int) x - (int) y) == offByN;
    }
}
