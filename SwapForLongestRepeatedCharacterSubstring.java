import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * You are given a string text. You can swap two of the characters in the text.
 *
 * Return the length of the longest substring with repeated characters.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "ababa"
 * Output: 3
 * Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa" with length 3.
 * Example 2:
 *
 * Input: text = "aaabaaa"
 * Output: 6
 * Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa" with length 6.
 * Example 3:
 *
 * Input: text = "aaaaa"
 * Output: 5
 * Explanation: No need to swap, longest repeated character substring is "aaaaa" with length is 5.
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 2 * 104
 * text consist of lowercase English characters only.
 */
public class SwapForLongestRepeatedCharacterSubstring {

    /**
     * 1) For each distinct character, create a map with a list of {startIndex, endIndex, characterCount}
     * 2) Iterate through the above map created for each character and do the following :
     *      a) If the current character has only one entry in the list, Compare its count with the result and assign the maximum value to the result.
     *      b) If the size of the List is greater than 2 and end Index of the current element in the list - start index of the next element in the list is equal to 1, add current elements count along with next element count plus 1(swap element) and Compare its count with the result and assign the maximum value to the result.
     *      c) If the size of the List is exactly 2 and end Index of the current element in the list - start index of the next element in the list is equal to 1, add current elements count along with next element count minus 1 and Compare its count with the result and assign the maximum value to the result.
     *      d) If the size of the List is exactly 2 and end Index of the current element in the list - start index of the next element in the list is greater than 1, add current elements count plus 1(swap element) and Compare its count with the result and assign the maximum value to the result.
     * @param text
     * @return
     */
    public int longestRepeatedCharacterSubstring(String text) {
        Map<Character, List<List<Integer>>> characterCountMap = new HashMap<>();
        for(int i = 0; i < text.length();){
            char currentCharacter = text.charAt(i);
            int j = i + 1;
            int count = 1;
            while(j < text.length() && text.charAt(j) == currentCharacter){
                j++;
                count++;
            }
            List<Integer> currentCharacterList = new ArrayList<>();
            currentCharacterList.add(i);
            currentCharacterList.add(j - 1);
            currentCharacterList.add(count);
            if(characterCountMap.containsKey(currentCharacter)){
                characterCountMap.get(currentCharacter).add(currentCharacterList);
            } else {
                List<List<Integer>> outerList = new ArrayList<>();
                outerList.add(currentCharacterList);
                characterCountMap.put(currentCharacter, outerList);
            }
            i = j;
        }

        int result = 0;
        for(Map.Entry<Character, List<List<Integer>>> entrySet : characterCountMap.entrySet()){
            List<List<Integer>> values = entrySet.getValue();
            for(int x = 0; x < values.size(); x++){
                int y = x + 1;
                if(y < values.size() && (values.get(y).get(0) - values.get(x).get(1) - 1) == 1 && (y + 1 < values.size() || x - 1 >= 0) ){
                    result = Math.max(result, values.get(x).get(2) + values.get(y).get(2) + 1);
                } else if(y < values.size() && (values.get(y).get(0) - values.get(x).get(1) - 1) == 1){
                    result = Math.max(result, values.get(x).get(2) + values.get(y).get(2));
                } else if(values.size() > 2){
                    result = Math.max(result, values.get(x).get(2) + 1);
                } else {
                    result = Math.max(result, values.get(x).get(2));
                }
            }
        }
        return result;
    }
}
