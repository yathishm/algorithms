import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of lowercase English letters.
 */
public class RemoveDuplicateLetter {

    /**
     * Step 1. Build a map which contains character with their Last Index Position in the input string
     * Step 2. Maintain a MAP/Array which stores which all the characters are already seen.
     * Step 3. Iterate through each character from input string and Maintain a stack of Unique lexicographically smaller character string with below steps :
     *          a) If the current character has no duplicates in the entire input string, push it to stack and mark the character as "Seen"
     *          b) If the current character is greater than the character on top of stack, then push it to stack if its not marked as "seen" and mark this character as "Seen".
     *          c) Keep popping the elements from top of the Stack if,
     *                      i. The current character is lesser than the character on top of stack AND
     *                     ii. The character on the top of stack is duplicate and this is not its last index position stored in the map.
     * @param inputString
     * @return 
     */
    public String removeDuplicateLetters(String inputString) {
        Stack<Character> uniqueLetterStack = new Stack<>();
        Map<Character, Integer> characterLastIndexMap = buildCharacterLastIndexMap(inputString);
        Map<Character, Boolean> characterSeenSoFar = new HashMap<>();
        uniqueLetterStack.push(inputString.charAt(0));
        characterSeenSoFar.put(inputString.charAt(0), true);
        for(int i = 1; i < inputString.length(); i++) {
            Character currentCharacter = inputString.charAt(i);
            if(!characterSeenSoFar.containsKey(currentCharacter)){
                while(!uniqueLetterStack.isEmpty() && uniqueLetterStack.peek().compareTo(currentCharacter) > 0 && characterLastIndexMap.get(uniqueLetterStack.peek()) > i) {
                    Character popedCharacter = uniqueLetterStack.pop();
                    characterSeenSoFar.remove(popedCharacter);
                }
                uniqueLetterStack.push(currentCharacter);
                characterSeenSoFar.put(currentCharacter, true);
            }
        }

        ListIterator<Character> iterator = uniqueLetterStack.listIterator();
        StringBuilder builder = new StringBuilder();
        while(iterator.hasNext()){
            builder.append(iterator.next());
        }
        return builder.toString();
    }

    private Map<Character, Integer> buildCharacterLastIndexMap(String inputString){
        Map<Character, Integer> characterLastIndexMap = new HashMap<>();
        for(int i = 0; i < inputString.length(); i++){
            characterLastIndexMap.put(inputString.charAt(i), i);
        }
        return characterLastIndexMap;
    }

    public static void main(String[] args){
        RemoveDuplicateLetter b = new RemoveDuplicateLetter();
        System.out.println(b.removeDuplicateLetters("leetcode"));
    }
}
