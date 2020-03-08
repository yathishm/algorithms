import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.
 *
 * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 *
 * (As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [0,1,2,3,4,3,4]
 * Output: "dba"
 * Example 2:
 *
 *
 *
 * Input: [25,1,3,1,3,0,2]
 * Output: "adz"
 * Example 3:
 *
 *
 *
 * Input: [2,2,1,null,1,0,null,0]
 * Output: "abc"
 *
 *
 * Note:
 *
 * The number of nodes in the given tree will be between 1 and 8500.
 * Each node in the tree will have a value between 0 and 25.
 */

public class SmallestStringFromLeaf {

    private static final int ASCII_VALUE_OF_SMALLERCASE_A = 97;

    public String smallestFromLeaf(TreeNode root) {
        List<String> result = new ArrayList<>(1);
        result.add(0, null);
        smallestStringFromLeaf(root, new StringBuilder(), result);
        return result.get(0);
    }

    private void smallestStringFromLeaf(TreeNode root, StringBuilder tempBuilder, List<String> smallestStringSoFar){
        if(root == null)
            return;
        tempBuilder.append((char)(root.val + ASCII_VALUE_OF_SMALLERCASE_A));
        if(root.left == null && root.right == null){
            String reverseString = new StringBuilder(tempBuilder).reverse().toString();
            smallestStringSoFar.set(0, compareTo(reverseString , smallestStringSoFar.get(0)));
        }
        if(root.left != null){
            smallestStringFromLeaf(root.left, tempBuilder, smallestStringSoFar);
        }
        if(root.right != null){
            smallestStringFromLeaf(root.right, tempBuilder, smallestStringSoFar);
        }
        tempBuilder.deleteCharAt(tempBuilder.length() - 1);
    }

    /**
     * Compare's 2 string and return the lexicographically smaller/shorter string.
     * @param left
     * @param right
     * @return
     */
    public String compareTo(String left, String right) {
        int result;
        String smallerString = null;

        if(left == null){
            return right;
        }else if(right == null){
            return left;
        }
        int minLength = Math.min(left.length(), right.length());
        if(left.length() == right.length()){
            result = left.compareTo(right);
        } else{
            result = left.substring(0, minLength).compareTo(right.substring(0,minLength));
        }
        if(result < 0){
            smallerString = left;
        }else if(result > 0){
            smallerString = right;
        }else if(result == 0){
            if(left.length() == right.length()){
                smallerString = left;
            }else{
                smallerString = left.length() == minLength ? left : right;
            }
        }
        return smallerString;
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
