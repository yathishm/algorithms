import java.util.HashMap;
import java.util.Map;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */

public class DecodeWay {

    private static final Integer MAX_CHARACTER_LENGTH = 26;
    private static final char ZERO = '0';

    public int numDecodings(String s) {
        if(null == s || s.length() == 0 || s.charAt(0) == ZERO || isValidZero(s)){
            return 0;
        }
        return decodeWays(s, s.length() - 1, new HashMap<Integer, Integer>());
    }

    /**
     * This method finds total ways to decode the string using Dynamic Programming memoization approach.
     * @param s
     * @param currentLastIndex
     * @param cache
     * @return
     */
    private int decodeWays(String s, int currentLastIndex, Map<Integer,Integer> cache){
        if(currentLastIndex <= 0){
            return 1;
        }
        if(cache.containsKey(currentLastIndex)){
            return cache.get(currentLastIndex);
        }
        int singleDigitDecode = 0;
        if(Integer.parseInt(s.substring(currentLastIndex, currentLastIndex + 1)) > 0){
            singleDigitDecode = decodeWays(s, currentLastIndex - 1, cache);
        }
        int doubleDigitDecode = 0;
        int temp = Integer.parseInt(s.substring(currentLastIndex - 1, currentLastIndex + 1));
        if(currentLastIndex - 1 >= 0 && temp > 0 && temp <= MAX_CHARACTER_LENGTH && s.charAt(currentLastIndex - 1) != ZERO){
            doubleDigitDecode = decodeWays(s, currentLastIndex - 2, cache);
        }
        int result = singleDigitDecode + doubleDigitDecode;
        cache.put(currentLastIndex, result);
        return result;
    }

    /**
     * This method returns true if there exists a 2 consecutive zero's in the input string, false otherwise.
     * @param s
     * @return
     */
    private boolean isValidZero(String s){
        int previousIndexOfZero = -1;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ZERO){
             if(previousIndexOfZero >= 0 && previousIndexOfZero == i - 1) {
                 return Boolean.TRUE;
             }else{
                 previousIndexOfZero = i;
             }
            }else{
                previousIndexOfZero = -1;
            }
        }
        return Boolean.FALSE;
    }

}
