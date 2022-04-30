/**
 * Given two strings s and part, perform the following operation on s until all occurrences of the substring part are removed:
 *
 * Find the leftmost occurrence of the substring part and remove it from s.
 * Return s after removing all occurrences of part.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "daabcbaabcbc", part = "abc"
 * Output: "dab"
 * Explanation: The following operations are done:
 * - s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
 * - s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
 * - s = "dababc", remove "abc" starting at index 3, so s = "dab".
 * Now s has no occurrences of "abc".
 * Example 2:
 *
 * Input: s = "axxxxyyyyb", part = "xy"
 * Output: "ab"
 * Explanation: The following operations are done:
 * - s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
 * - s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
 * - s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
 * - s = "axyb", remove "xy" starting at index 1 so s = "ab".
 * Now s has no occurrences of "xy".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * 1 <= part.length <= 1000
 * s​​​​​​ and part consists of lowercase English letters.
 */
public class RemoveAllOccurrencesofSubstring {

    public String removeOccurrences(String s, String part) {
        if(part.length() > s.length()){
            return s;
        }
        StringBuilder charBuilder = new StringBuilder();

        for(int i = 0; i < s.length(); i++){
            charBuilder.append(s.charAt(i));

            int j = part.length() - 1;
            int count = 0;
            while(charBuilder.length() > 0 && j >= 0 && part.charAt(j) == charBuilder.charAt(charBuilder.length() - 1)){
                charBuilder.deleteCharAt(charBuilder.length() - 1);
                j--;
                count++;
            }
            //some of the characters didn't match, push the characters which were poped back to the builder from the original string.
            if(count != part.length()){
                j++;
                while(j < part.length()){
                    charBuilder.append(part.charAt(j));
                    j++;
                }
            }
        }
        return charBuilder.toString();
    }
}