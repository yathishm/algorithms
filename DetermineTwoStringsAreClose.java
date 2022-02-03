import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1657. Determine if Two Strings Are Close
 * Medium
 *
 * 610
 *
 * 35
 *
 * Add to List
 *
 * Share
 * Two strings are considered close if you can attain one from the other using the following operations:
 *
 * Operation 1: Swap any two existing characters.
 * For example, abcde -> aecdb
 * Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
 * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 * You can use the operations on either string as many times as necessary.
 *
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "abc", word2 = "bca"
 * Output: true
 * Explanation: You can attain word2 from word1 in 2 operations.
 * Apply Operation 1: "abc" -> "acb"
 * Apply Operation 1: "acb" -> "bca"
 * Example 2:
 *
 * Input: word1 = "a", word2 = "aa"
 * Output: false
 * Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
 * Example 3:
 *
 * Input: word1 = "cabbba", word2 = "abbccc"
 * Output: true
 * Explanation: You can attain word2 from word1 in 3 operations.
 * Apply Operation 1: "cabbba" -> "caabbb"
 * Apply Operation 2: "caabbb" -> "baaccc"
 * Apply Operation 2: "baaccc" -> "abbccc"
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 105
 * word1 and word2 contain only lowercase English letters.
 */
public class DetermineTwoStringsAreClose {

    public boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length())
            return false;

        Map<Character, Integer>  word1CountMap = new TreeMap<>();
        for(int i = 0; i < word1.length(); i++){
            word1CountMap.merge(word1.charAt(i), 1, Integer::sum);
        }

        Map<Character, Integer>  word2CountMap = new TreeMap<>();
        for(int i = 0; i < word1.length(); i++){
            word2CountMap.merge(word2.charAt(i), 1, Integer::sum);
        }

        if(word1CountMap.size() != word2CountMap.size())
            return false;
        
        if(!word1CountMap.keySet().containsAll(word2CountMap.keySet()))
           return false;

        List<Integer> word1CharactersCount = new ArrayList<>(word1CountMap.values());
        for(Integer value : word2CountMap.values()){
            if(word1CharactersCount.contains(value)){
                word1CharactersCount.remove(value);
            }
        }
        if(word1CharactersCount.size() == 0)
            return true;

        return false;
    }
}
