import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 * Constraints:
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequence(text1, text2, 0, 0, new HashMap<>());
    }

    /**
     * returns the count of longest sunsequence using dynamic programming memoization approach
     * @param text1
     * @param text2
     * @param text1CurrentIndex
     * @param text2CurrentIndex
     * @param cache
     * @return
     */
    private int longestCommonSubsequence(String text1, String text2, int text1CurrentIndex, int text2CurrentIndex, Map<String, Integer> cache) {
        if(text1CurrentIndex >= text1.length() || text2CurrentIndex >= text2.length()){
            return 0;
        }

        String key = text1CurrentIndex + "_" + text2CurrentIndex;
        if(cache.containsKey(key)){
            return cache.get(key);
        }

        if(text1.charAt(text1CurrentIndex) == text2.charAt(text2CurrentIndex)){
            cache.put(key, 1 + longestCommonSubsequence(text1, text2, text1CurrentIndex + 1, text2CurrentIndex + 1, cache));
        } else {
            cache.put(key,
                    Math.max(longestCommonSubsequence(text1, text2, text1CurrentIndex, text2CurrentIndex + 1, cache),
                            longestCommonSubsequence(text1, text2, text1CurrentIndex + 1, text2CurrentIndex, cache))
            );
        }
        return cache.get(key);
    }
}
