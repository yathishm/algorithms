import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParenthesis {

    private static final Character LEFT_BRACET ='(';
    private static final Character RIGHT_BRACET = ')';

    /**
     * This method uses Stack DS to determine the longest length of valid parenthesis from a input String
     * if any other charcter other than "(" or ")" found, it will throw InvalidParenthesisException
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stackOfParenthesis = new Stack<>();
        stackOfParenthesis.push(-1);
        int longestLengthValidParenthesis = 0;
        for(int i = 0; i < s.length(); i++){
           if(LEFT_BRACET == s.charAt(i)){
               stackOfParenthesis.push(i);
           }else if(RIGHT_BRACET == s.charAt(i)){
               int topOfStack = stackOfParenthesis.peek();
               if(topOfStack != -1 && LEFT_BRACET == s.charAt(topOfStack)){
                   stackOfParenthesis.pop();
                   longestLengthValidParenthesis = Math.max(longestLengthValidParenthesis, i - stackOfParenthesis.peek());
               }else{
                   stackOfParenthesis.push(i);
               }
           }else{
               throw new InvalidParenthesisException("Invalid Character");
           }
        }
        return longestLengthValidParenthesis;
    }
}

class InvalidParenthesisException extends RuntimeException{
    public InvalidParenthesisException(String s){
        super(s);
    }
}

