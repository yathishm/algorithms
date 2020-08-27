import java.util.HashMap;
import java.util.Map;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example 1:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public class Interleaving {

    public boolean isInterleave(String s1, String s2, String s3) {
        return isInterleave(s1, s2, s3,new HashMap<String, Boolean>(),0, 0, 0);
    }

    /**
     * This method uses Dynamic programming memoization to check if the string is interleaving of 2 strings.
     * @param s1
     * @param s2
     * @param s3
     * @param cache
     * @param i
     * @param j
     * @param k
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3, Map<String, Boolean> cache, int i, int j, int k) {
        if(i >= s1.length() && j >= s2.length() && k >= s3.length()){
            return Boolean.TRUE;
        }

        if(s1.length() + s2.length() != s3.length())
            return Boolean.FALSE;

        String key = i + "-" + j;
        if(cache.containsKey(key)){
            return cache.get(key);
        }

        boolean temp = false;
        if(i < s1.length() && k < s3.length() && s1.charAt(i) == s3.charAt(k))
            temp = isInterleave(s1, s2, s3, cache,i + 1, j, k + 1);
        if(!temp && j < s2.length() && k < s3.length() && s2.charAt(j) == s3.charAt(k))
            temp = isInterleave(s1, s2, s3, cache,i, j + 1, k + 1);
        cache.put(key, temp);
        return temp;
    }

    public static void main(String[] args){
        Interleaving interleaving = new Interleaving();
        interleaving.isInterleave("aabcc","dbbca","aadbbbaccc");
    }
}
