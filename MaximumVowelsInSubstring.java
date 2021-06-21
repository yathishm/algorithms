import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and an integer k.
 *
 * Return the maximum number of vowel letters in any substring of s with length k.
 *
 * Vowel letters in English are (a, e, i, o, u).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 * Example 2:
 *
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 * Example 3:
 *
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 * Example 4:
 *
 * Input: s = "rhythms", k = 4
 * Output: 0
 * Explanation: We can see that s doesn't have any vowel letters.
 * Example 5:
 *
 * Input: s = "tryhard", k = 4
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 * 1 <= k <= s.length
 */
public class MaximumVowelsInSubstring {

    /**
     * returns maximum vowels in substring of length K using Sliding Window.
     * @param inputString
     * @param k
     * @return
     */
    public int maxVowels(String inputString, int k) {
        Set<Character> vowelsSet = new HashSet<>();
        addAllVowelsToSet(vowelsSet);
        int result = 0, i = 0;
        for(; i < k; i++){
            Character currentCharacter = inputString.charAt(i);
            if(vowelsSet.contains(currentCharacter)){
                result++;
            }
        }
        int maxLengthSoFar = result;
        for(; i < inputString.length(); i++){
            Character characterToBeRemovedAfterSliding = inputString.charAt(i - k);
            if(vowelsSet.contains(characterToBeRemovedAfterSliding)){
                maxLengthSoFar--;
            }
            Character currentCharacter = inputString.charAt(i);
            if(vowelsSet.contains(currentCharacter)){
                maxLengthSoFar++;
            }
            result = Math.max(result, maxLengthSoFar);
        }
        return result;
    }

    private void addAllVowelsToSet(Set<Character> vowelsSet){
        vowelsSet.add('a');
        vowelsSet.add('e');
        vowelsSet.add('i');
        vowelsSet.add('o');
        vowelsSet.add('u');
    }
}
