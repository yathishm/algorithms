import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 *
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * Example 2:
 *
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either 'A', 'C', 'G', or 'T'.
 */
public class RepeatedDNASequence {

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> dnaSequenceSet = new HashSet<>();
        Set<String> result = new HashSet<>();

        for(int i = s.length() - 10; i >= 0; i--){
            String currentDnaSequence = s.substring(i , i + 10);
            if(dnaSequenceSet.contains(currentDnaSequence)){
                result.add(currentDnaSequence);
            }
            dnaSequenceSet.add(currentDnaSequence);
        }
        return new ArrayList<String>(result);
    }
}
