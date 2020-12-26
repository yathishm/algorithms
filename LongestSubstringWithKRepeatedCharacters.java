import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of only lowercase English letters.
 * 1 <= k <= 105
 */
public class LongestSubstringWithKRepeatedCharacters {

    public int longestSubstring(String s, int k) {
        if(s == null || s.length() <= 0) return 0;
        return divideAndConquerToFindlongestSubstring(s, k, 0, s.length() - 1);
    }

    /**
     * This method uses divide and conquer approach to find the longest substring with atleast k repeated characters
     * It builds a characterCountMap which contains the count of each character in the substring. It then uses the characterCountMap to check if the substring has atleast k repeated characters.
     * @param s
     * @param k
     * @param startIndex
     * @param endIndex
     * @return
     */
    private int divideAndConquerToFindlongestSubstring(String s, int k, int startIndex, int endIndex){
        Map<Character, Integer> characterCountMap = getCharactersCount(s, startIndex, endIndex);

        int mid = startIndex;
        while(mid <= endIndex && characterCountMap.get(s.charAt(mid)) >= k){
            mid++;
        }
        int countOfLongestSubstringLeftTree = 0;
        if(mid - 1 == endIndex){
            return endIndex - startIndex + 1;
        } else if(mid - 1 < endIndex){
            countOfLongestSubstringLeftTree = divideAndConquerToFindlongestSubstring(s, k, startIndex, mid - 1);
        }

        int j = mid + 1;
        while(j <= endIndex && characterCountMap.get(s.charAt(j)) < k){
            j++;
        }
        int countOfLongestSubstringRightTree = 0;
        if(j > endIndex){
            countOfLongestSubstringRightTree = 0;
        } else if(j <= endIndex ){
            countOfLongestSubstringRightTree = divideAndConquerToFindlongestSubstring(s, k, j, endIndex);
        }

        return Math.max(countOfLongestSubstringLeftTree, countOfLongestSubstringRightTree);
    }

    /**
     * This method will return a map of characters along with the count in input string
     * @param inputString
     * @param startIndex
     * @param endIndex
     * @return
     */
    private Map<Character, Integer> getCharactersCount(String inputString, int startIndex, int endIndex){
        Map<Character, Integer> characterCountMap = null;
        if(null != inputString && inputString.length() > 0){
            characterCountMap = new HashMap<>();
            for(int i = startIndex; i <= endIndex; i++){
                int count = 1;
                if(characterCountMap.containsKey(inputString.charAt(i))){
                    count = characterCountMap.get(inputString.charAt(i)) + count;
                }
                characterCountMap.put(inputString.charAt(i) , count);
            }
        }
        return characterCountMap;
    }
}
