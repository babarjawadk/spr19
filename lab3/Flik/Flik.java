/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) { // problem is here: Integer (reference type) != int (primitive type), we are comparing pointers/addresses
        return a == b;
    }
}
