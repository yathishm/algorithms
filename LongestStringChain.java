import java.util.Arrays;

/**
 * You are given an array of words where each word consists of lowercase English letters.
 *
 * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.
 *
 * For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
 * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
 *
 * Return the length of the longest possible word chain with words chosen from the given list of words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
 * Example 2:
 *
 * Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * Output: 5
 * Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
 * Example 3:
 *
 * Input: words = ["abcd","dbqca"]
 * Output: 1
 * Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
 * ["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] only consists of lowercase English letters.
 */
public class LongestStringChain {

    /**
     * returns length of longest string chain using Dynamic programming memoization approach
     * @param words
     * @return
     */
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        int arr[] = new int[words.length];
        for(int k = 0; k < words.length; k++){
            arr[k] = 1;
        }

        for(int j = 1; j < words.length; j++){
            for(int i = 0; i < j; i++){
                if(isPredecessor(words[i], words[j])){
                    arr[j] = Math.max(arr[j] , arr[i] + 1);
                }
            }
        }

        int result = 0;
        for(int k = 0; k < words.length; k++){
            result = Math.max(result, arr[k]);
        }
        return result;
    }

    /**
     * Return true if word2 is a predecessor of word1 if and only if we can insert exactly one letter anywhere in word2 without changing the order of the other characters to make it equal to word1.
     * Return false, otherwise
     * @param word1
     * @param word2
     * @return
     */
    private boolean isPredecessor(String word1, String word2){
        if(word1.length() == word2.length() || word2.length() > word1.length() + 1) return false;

        int k = 0, j = 0;
        for(int i = 0; i < word1.length() && j < word2.length(); j++){
            if(word1.charAt(i) != word2.charAt(j)){
                k++;
            } else {
                i++;
            }
        }
        k = k + word2.length() - j;
        return k == 1 ? true : false;
    }
}
