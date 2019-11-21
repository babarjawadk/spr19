public class Palindrome {
    public Deque<Character> wordToDeque(String word) {

        ArrayDeque<Character> output = new ArrayDeque<>();

        for (int i = 0; i < word.length(); i++) {
            output.addLast(word.charAt(i));
        }
        return output;
    }


    public boolean isPalindrome(String word) {



        return isPalindrome(wordToDeque(word));
    }

    private static boolean isPalindrome(Deque arrayWord) {
        if (arrayWord.size() <= 1) {
            return true;
        } else if (arrayWord.removeFirst() != arrayWord.removeLast()) {
            return false;
        } else {
            return isPalindrome(arrayWord);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

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
