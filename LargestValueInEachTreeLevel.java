import java.util.ArrayList;
import java.util.List;

/**
 * Find Largest Value in Each Tree Row
 *
 *  You need to find the largest value in each row of a binary tree.
 *
 * Example:
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 *
 * Output: [1, 3, 9]
 */
public class LargestValueInEachTreeLevel {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> largestValueList = new ArrayList<>();
        findLargestValueInEachLevel(root, -1, largestValueList);
        return largestValueList;
    }

    /**
     * preorder tree traversal to find Largest Value in Each Tree Row
     * @param root
     * @param level
     * @param largestValueList
     */
    private void findLargestValueInEachLevel(TreeNode root, int level, List<Integer> largestValueList){
        if(null == root)
            return;
        if(level + 1 == largestValueList.size()){
            largestValueList.add(level+1 , root.val);
        }else if(level < largestValueList.size()){
            largestValueList.set(level + 1 , Math.max(largestValueList.get(level + 1), root.val));
        }
        findLargestValueInEachLevel(root.left, level + 1, largestValueList);
        findLargestValueInEachLevel(root.right, level + 1, largestValueList);
    }
}

/**
 * Definition for a binary tree node.
 *  */
class TreeNode {
        int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
  }
