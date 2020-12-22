import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 *
 * Constraints:
 *
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 */

public class PermutationInString {

    /**
     * This method will return true if s2 contains the permutation of s1.
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        // insert all the characters in input string s1 along with their count into Map data structure for faster access.
        Map<Character, CountWithCharacterFirstIndex> inputStringToMap = stringToMap(s1);
        Integer subStringStartIndex = null, subStringEndIndex = null;
        Map<Character, CountWithCharacterFirstIndex> cloneOfinputStringToMap = new HashMap<>();
        for(int i = 0; i < s2.length();){
            char ch = s2.charAt(i);
            if(inputStringToMap.containsKey(ch)){
                CountWithCharacterFirstIndex countWithCharacterFirstIndex = cloneOfinputStringToMap.get(ch);
                if(null == countWithCharacterFirstIndex){
                    CountWithCharacterFirstIndex clone = inputStringToMap.get(ch).clone();
                    clone.count--;
                    clone.characterFirstIndex = i;
                    cloneOfinputStringToMap.put(ch, clone);
                    subStringStartIndex = subStringStartIndex == null ? i : subStringStartIndex;
                    subStringEndIndex = i;
                } else{
                    countWithCharacterFirstIndex.count--;
                    // Handle cases when input string s1 = "adc" and s2 ="dcda".
                    if(countWithCharacterFirstIndex.count < 0){
                        // Re-initialze the substring start index and substring end index.
                        subStringStartIndex = subStringEndIndex = null;
                        cloneOfinputStringToMap.clear();
                        i = countWithCharacterFirstIndex.characterFirstIndex + 1;
                        continue;
                    } else {
                        subStringEndIndex = i;
                    }
                }
            } else {
                //if we encounter a character in s2 which is not present in input string s1 then, re-initialze the substring start index and substring end index.
                subStringStartIndex = subStringEndIndex = null;
                cloneOfinputStringToMap.clear();
            }
            i++;
            // When the length of subStringEndIndex - subStringStartIndex is equal to the length of input string s1, then the first string's permutations is the substring of the second string.
            if(null != subStringEndIndex && null != subStringStartIndex && subStringEndIndex - subStringStartIndex == s1.length() - 1) return true;
        }
        return false;
    }

    /**
     * This method will insert all the characters in the input string into the map along with the count.
     * @param firstString
     * @return Map<Character, CountWithCharacterFirstIndex>
     */
    private Map<Character, CountWithCharacterFirstIndex> stringToMap(String firstString){
        Map<Character, CountWithCharacterFirstIndex> mapOfCharacters = new HashMap<>();
        for(int i = 0; i < firstString.length(); i++){
            char ch = firstString.charAt(i);
            CountWithCharacterFirstIndex countWithCharacterFirstIndex = null;
            if(mapOfCharacters.containsKey(ch)){
                countWithCharacterFirstIndex = mapOfCharacters.get(ch);
                countWithCharacterFirstIndex.count = countWithCharacterFirstIndex.count + 1;
            }else{
                mapOfCharacters.put(ch, new CountWithCharacterFirstIndex(1));
            }
        }
        return mapOfCharacters;
    }

    private class CountWithCharacterFirstIndex implements Cloneable {
        Integer count;
        Integer characterFirstIndex;

        public CountWithCharacterFirstIndex(Integer count){
            this.count = count;
        }

        public CountWithCharacterFirstIndex clone() {
            CountWithCharacterFirstIndex countWithCharacterFirstIndex = null;
            try {
                countWithCharacterFirstIndex = (CountWithCharacterFirstIndex) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return countWithCharacterFirstIndex;
        }
    }
}
