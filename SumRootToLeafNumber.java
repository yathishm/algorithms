import java.util.ArrayList;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * Example 2:
 *
 * Input: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
public class SumRootToLeafNumber {

    public int sumNumbers(TreeNode root) {
        ArrayList<Integer> totalSum = new ArrayList<>(1);
        totalSum.add(0,0);
        sumNumbers(root, new StringBuilder(), totalSum);
        return totalSum.get(0);
    }

    /**
     *
     * @param root
     * @param currentSumBuilder
     * @param totalSum
     */
    private void sumNumbers(TreeNode root, StringBuilder currentSumBuilder, ArrayList<Integer> totalSum){
        if(root == null)
            return;

        currentSumBuilder.append(root.val);
        if(root.left == null && root.right == null){
            totalSum.add(0, totalSum.get(0) + Integer.parseInt(currentSumBuilder.toString()));
        }
        sumNumbers(root.left, currentSumBuilder , totalSum);
        sumNumbers(root.right, currentSumBuilder, totalSum);
        currentSumBuilder.deleteCharAt(currentSumBuilder.length()-1);
    }
}

/**
*  Definition for a binary tree node.
 **/
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

