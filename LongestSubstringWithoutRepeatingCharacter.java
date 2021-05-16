import java.util.HashMap;
import java.util.Map;
/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Example 4:
 *
 * Input: s = ""
 * Output: 0
 */
public class LongestSubstringWithoutRepeatingCharacter {

    public int lengthOfLongestSubstring(String s) {
        if(null == s && s.isEmpty())
            return 0;
        return lengthOfLongestSubstringWithoutRepeatingCharacter(s);
    }

    /**
     * returns length of longest substring using { SLIDING WINDOW } approach.
     * @param s
     * @return
     */
    private int lengthOfLongestSubstringWithoutRepeatingCharacter(String s) {
        Map<Character, Integer> characterIndexMap = new HashMap<>();
        int startIndex = 0;
        int maxLongestSubstringWithoutRepeatingCharacterSeenSoFar = 0;
        int i = 0;
        for(; i < s.length(); i++){
            Character currentCharacter = s.charAt(i);
            Integer charIndex = characterIndexMap.get(currentCharacter);
            if(null != charIndex && charIndex >= startIndex){
                maxLongestSubstringWithoutRepeatingCharacterSeenSoFar = Math.max(maxLongestSubstringWithoutRepeatingCharacterSeenSoFar, (i - startIndex));
                characterIndexMap.put(currentCharacter, i);
                startIndex = charIndex + 1;
            } else {
                characterIndexMap.put(currentCharacter, i);
            }
        }
        return Math.max(maxLongestSubstringWithoutRepeatingCharacterSeenSoFar, (i - startIndex));
    }
}
