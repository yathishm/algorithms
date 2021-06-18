import java.util.HashMap;
import java.util.Map;


/**
 * You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q where its concatenation is equal to s and the number of distinct letters in p and q are the same.
 *
 * Return the number of good splits you can make in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aacaba"
 * Output: 2
 * Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
 * ("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
 * Example 2:
 *
 * Input: s = "abcd"
 * Output: 1
 * Explanation: Split the string as follows ("ab", "cd").
 * Example 3:
 *
 * Input: s = "aaaaa"
 * Output: 4
 * Explanation: All possible splits are good.
 * Example 4:
 *
 * Input: s = "acbadbaada"
 * Output: 2
 *
 *
 * Constraints:
 *
 * s contains only lowercase English letters.
 * 1 <= s.length <= 10^5
 */
public class NumberOfGoodWaysToSplitString {

    /**
     *
     * 1. Build a map {characterCountMap} with count of each character in the inputString.
     * 2. Iterate through the inputString, on each iteration,
     *      a. Decrement the count of current character from map {characterCountMap}
     *      b. Insert/Incrememt the count of current character from map {splitCountMap}
     *      c. Compare the size of both the maps {characterCountMap} and {splitCountMap}, if equal, then add it to the result.
     *
     * @param inputString
     * @return numberOfGoodSplits
     */
    public int numSplits(String inputString) {
        int numberOfGoodSplits = 0;
        Map<Character, Integer> characterCountMap = new HashMap<>();
        for(int i = 0; i < inputString.length(); i++) {
            char currentCharacter = inputString.charAt(i);
            if(characterCountMap.containsKey(currentCharacter)){
                characterCountMap.put(currentCharacter, characterCountMap.get(currentCharacter) + 1);
            } else{
                characterCountMap.put(currentCharacter, 1);
            }
        }

        Map<Character, Integer> splitCountMap = new HashMap<>();
        for(int i = 0; i < inputString.length(); i++) {
            char currentCharacter = inputString.charAt(i);
            if(splitCountMap.containsKey(currentCharacter)){
                splitCountMap.put(currentCharacter, splitCountMap.get(currentCharacter) + 1);
            } else{
                splitCountMap.put(currentCharacter, 1);
            }

            if(characterCountMap.containsKey(currentCharacter)){
                int count = characterCountMap.get(currentCharacter) - 1;
                if(count <= 0){
                    characterCountMap.remove(currentCharacter);
                } else {
                    characterCountMap.put(currentCharacter, count);

                }
            }

            if(characterCountMap.size() == splitCountMap.size()){
                numberOfGoodSplits++;
            }
        }
        return numberOfGoodSplits;
    }
}
