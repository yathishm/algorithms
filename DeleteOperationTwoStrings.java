import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 *
 * In one step, you can delete exactly one character in either string.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "sea", word2 = "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * Example 2:
 *
 * Input: word1 = "leetcode", word2 = "etco"
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 500
 * word1 and word2 consist of only lowercase English letters.
 */
public class DeleteOperationTwoStrings {

    public int minDistance(String word1, String word2) {
        return minDistance(word1, word2, 0, 0, new HashMap<>());
    }

    private int minDistance(String word1, String word2, int word1Index, int word2Index, Map<String, Integer> cache) {

        if(word1Index >= word1.length()){
            return word2.length() - word2Index;
        } else if(word2Index >= word2.length()){
            return word1.length() - word1Index;
        }

        String key = word1Index + "-" + word2Index;
        if(cache.containsKey(key)){
            return cache.get(key);
        }

        if(word1.charAt(word1Index) == word2.charAt(word2Index)){
            cache.put(key, minDistance(word1, word2, word1Index + 1, word2Index + 1, cache));
        } else {
            cache.put(key,
                    1 + Math.min(minDistance(word1, word2, word1Index + 1, word2Index, cache),
                            minDistance(word1, word2, word1Index, word2Index + 1, cache)
                    )
            );
        }
        return cache.get(key);
    }
}
