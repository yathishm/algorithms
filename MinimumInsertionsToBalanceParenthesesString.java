import java.util.Stack;

/**
 * Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:
 *
 * Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
 * Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
 * In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.
 *
 * For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
 * You can insert the characters '(' and ')' at any position of the string to balance it if needed.
 *
 * Return the minimum number of insertions needed to make s balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()))"
 * Output: 1
 * Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to to add one more ')' at the end of the string to be "(())))" which is balanced.
 * Example 2:
 *
 * Input: s = "())"
 * Output: 0
 * Explanation: The string is already balanced.
 * Example 3:
 *
 * Input: s = "))())("
 * Output: 3
 * Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of '(' and ')' only.
 */
public class MinimumInsertionsToBalanceParenthesesString {

    /**
     * Return the minimum number of insertions needed to make input string balanced using Stack data structure
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        Stack<Character> balanceParenthesesStack = new Stack<>();
        int missingParenthesesInsertionCount = 0;
        for(int i = 0; i < s.length(); i++){
            Character currentCharacter = s.charAt(i);
            if(currentCharacter == '('){
                balanceParenthesesStack.push(currentCharacter);
            } else if(currentCharacter == ')'){
                if(balanceParenthesesStack.isEmpty()){
                    missingParenthesesInsertionCount++; // we need to insert missing '(' to make it balance
                } else {
                    balanceParenthesesStack.pop(); // pop the '(' from the top of stack
                }
                if(i + 1 < s.length() && s.charAt(i + 1) != ')' || i == s.length() - 1){
                    missingParenthesesInsertionCount++; // we also need to insert another missing ')' to make it balance
                } else {
                    i++; // skip checking the ')' in the next iteration
                }
            }
        }
        // pop all the remaining '(' from the stack and balance it by inserting with '))'
        while(!balanceParenthesesStack.isEmpty()){
            balanceParenthesesStack.pop();
            missingParenthesesInsertionCount+= 2;
        }
        return missingParenthesesInsertionCount;
    }
}
