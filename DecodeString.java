import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * Example 4:
 *
 * Input: s = "abc3[cd]xyz"
 * Output: "abccdcdcdxyz"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */
public class DecodeString {

    private static final String CLOSE_BRACET = "]";
    private static final String OPEN_BRACET = "[";

    /**
     * This method uses Stack data structure to decode the encoded string
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if(s == null || s.length() == 0) return s;

        Stack<String> decodeStringStack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            String ch = String.valueOf(s.charAt(i));
            // Pop all the characters from the stack till "[" found and repeateadly push the characters inside [] for exactly K times.
            if(CLOSE_BRACET.equals(ch)){
                StringBuilder decodeInnerString = new StringBuilder();
                while(!OPEN_BRACET.equals(decodeStringStack.peek())){
                    decodeInnerString.append(decodeStringStack.pop());
                }
                // pop the "[" character from the stack
                decodeStringStack.pop();
                // pop the digit from the stack
                int digit = Integer.valueOf(decodeStringStack.pop());
                // push the character/String digit number of times.
                for(int j = 0; j < digit; j++){
                    decodeStringStack.push(decodeInnerString.toString());
                }
                // check if current character is digit and push it to stack. Example : 3, 300, 30.
            } else if(Character.isDigit(s.charAt(i))){
                int startIndex = i;
                while(Character.isDigit(s.charAt(i + 1))){
                    i++;
                }
                decodeStringStack.push(s.substring(startIndex, i + 1));
            } else{
                decodeStringStack.push(ch);
            }
        }
        StringBuilder decodedResult = new StringBuilder();
        while(!decodeStringStack.isEmpty()){
            decodedResult.append(decodeStringStack.pop());
        }
        return decodedResult.reverse().toString();
    }
}
