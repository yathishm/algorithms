import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either'(' , ')', or lowercase English letter.
 */
public class MinimumRemoveMakeValidParentheses {

    private static final char OPEN_BRACET = '(';
    private static final char CLOSE_BRACET = ')';

    /**
     * removes the minimum number of parentheses from the given input string using Stack Data structure
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> parenthesesStack = new Stack<>();
        Set<Integer> indexOfInvalidParenthesesSet = new HashSet<>();
        for(int i = 0; i < s.length(); i++){
            char currentCharacter = s.charAt(i);
            if(currentCharacter == OPEN_BRACET){
                parenthesesStack.push(i);
            } else if(currentCharacter == CLOSE_BRACET){
                if(parenthesesStack.isEmpty()){
                    indexOfInvalidParenthesesSet.add(i);
                } else {
                    parenthesesStack.pop();
                }
            }
        }
        // Iterate through the left over '(' in the stack and add them to the invalid parentheses set to be removed
        while(!parenthesesStack.isEmpty()){
            indexOfInvalidParenthesesSet.add(parenthesesStack.pop());
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(!indexOfInvalidParenthesesSet.contains(i))
                result.append(s.charAt(i));
        }
        return result.toString();
    }
}
