/** A class for palindrome operations.
 * @author jawad
 */
public class Palindrome {

    /** Converts a String to an ArrayDeque.
     * @param word to be converted
     * @return ArrayDeque
     */
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> output = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            output.addLast(word.charAt(i));
        }
        return output;
    }

    /** Returns whether or not word is a palindrome.
     * @param word to be checked
     * @return
     */
    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    /** Helper method that check if a word is a palindrome.
     * @param arrayWord ArrayDeque of the String word
     * @return boolean
     */
    private static boolean isPalindrome(Deque arrayWord) {
        if (arrayWord.size() <= 1) {
            return true;
        } else if (arrayWord.removeFirst() != arrayWord.removeLast()) {
            return false;
        } else {
            return isPalindrome(arrayWord);
        }
    }

    /** Returns whether or not word is an "N-palindrome".
     * @param word to be checked
     * @param cc comparator (char off by 1, 2, 3, ...)
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

    /** Helper method that check if a word is an "N-palindrome".
     * @param arrayWord ArrayDeque of the String word
     * @param cc comparator (char off by 1, 2, 3, ...)
     * @return boolean
     */
    private static boolean isPalindrome(Deque arrayWord, CharacterComparator cc) {
        if (arrayWord.size() <= 1) {
            return true;
        } else if (!cc.equalChars((char) arrayWord.removeFirst(), (char) arrayWord.removeLast())) {
            return false;
        } else {
            return isPalindrome(arrayWord, cc);
        }

    }
}
