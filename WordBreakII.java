import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * Example 2:
 *
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
public class WordBreakII {

    private final List<String> EMPTY_LIST = new ArrayList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, 0, wordDict, new HashMap<>());
    }

    public List<String> wordBreak(String inputString, int currentIndex, List<String> wordDict, Map<Integer, List<String>> cache) {
        if(cache.containsKey(currentIndex)){
            return cache.get(currentIndex);
        }

        List<String> newSentencesList = new ArrayList<>();
        for(String word : wordDict){
            if(inputString.length() - currentIndex >= word.length()) {
                String wordBreak = inputString.substring(currentIndex, currentIndex + word.length());
                List<String> temp = null;
                if(wordBreak.equalsIgnoreCase(word)){
                    if(currentIndex + word.length() - 1 == inputString.length() - 1){
                        newSentencesList.add(wordBreak);
                    } else if((temp = wordBreak(inputString, currentIndex + word.length(), wordDict, cache))!= null && temp.size() > 0){
                        for(String matchedWord : temp){
                            newSentencesList.add(wordBreak + " " + matchedWord);
                        }
                    }
                    cache.put(currentIndex, newSentencesList);
                }
            }
        }
        return cache.get(currentIndex) != null ? cache.get(currentIndex) : EMPTY_LIST;
    }
}
