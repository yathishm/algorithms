import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 *
 * Constraints:
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        return minDistance(word1, word2, 0, 0, new HashMap<>());
    }

    private int minDistance(String word1, String word2, int word1CurrentIndex, int word2CurrentIndex, Map<String, Integer> cache) {
        if(word1CurrentIndex >= word1.length()){
            return word2.length() - word2CurrentIndex;
        } else if(word2CurrentIndex >= word2.length()){
            return word1.length() - word1CurrentIndex;
        }

        String key = word1CurrentIndex + "-" + word2CurrentIndex;
        if(cache.containsKey(key))
            return cache.get(key);

        if(word1.charAt(word1CurrentIndex) == word2.charAt(word2CurrentIndex)){
            cache.put(key, minDistance(word1, word2, word1CurrentIndex + 1, word2CurrentIndex + 1, cache));
        } else {
            cache.put(key, 1 + Math.min(Math.min(minDistance(word1, word2, word1CurrentIndex, word2CurrentIndex + 1, cache), minDistance(word1, word2, word1CurrentIndex + 1, word2CurrentIndex, cache)),
                                        minDistance(word1, word2, word1CurrentIndex + 1, word2CurrentIndex + 1, cache) )
                    );
        }
        return cache.get(key);
    }
}
