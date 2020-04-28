import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */

public class WordBreak {

    public static void main(String[] args){
        WordBreak wordBreak = new WordBreak();
        wordBreak.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        return segmentAndVerify(s, wordDict, 0, new HashMap<Integer, Boolean>());
    }

    /**
     * Dyanmic programming memoization technique to find the input can be segmented.
     * @param s
     * @param wordDict
     * @param startIndex
     * @param cache
     * @return
     */
    private boolean segmentAndVerify(String s, List<String> wordDict, int startIndex, Map<Integer, Boolean> cache) {
        boolean result = Boolean.FALSE;
        if(startIndex >= s.length()){
            return Boolean.TRUE;
        }
        if(cache.containsKey(startIndex)){
            return cache.get(startIndex);
        }
        for(String word : wordDict){
            if(s.length() - startIndex  >= word.length() ){
                String temp = s.substring(startIndex, startIndex + word.length());
                if(word.equalsIgnoreCase(temp) && segmentAndVerify(s, wordDict, startIndex + word.length(), cache)){
                    result = Boolean.TRUE;
                    break;
                }
            }
        }
        cache.put(startIndex, result);
        return result;
    }
}
