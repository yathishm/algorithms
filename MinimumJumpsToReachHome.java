import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * A certain bug's home is on the x-axis at position x. Help them get there from position 0.
 *
 * The bug jumps according to the following rules:
 *
 * It can jump exactly a positions forward (to the right).
 * It can jump exactly b positions backward (to the left).
 * It cannot jump backward twice in a row.
 * It cannot jump to any forbidden positions.
 * The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.
 *
 * Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i], and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home. If there is no possible sequence of jumps that lands the bug on position x, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * Output: 3
 * Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.
 * Example 2:
 *
 * Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * Output: -1
 * Example 3:
 *
 * Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * Output: 2
 * Explanation: One jump forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.
 *
 *
 * Constraints:
 *
 * 1 <= forbidden.length <= 1000
 * 1 <= a, b, forbidden[i] <= 2000
 * 0 <= x <= 2000
 * All the elements in forbidden are distinct.
 * Position x is not forbidden.
 */
public class MinimumJumpsToReachHome {

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> forbiddenSet = forbiddenPositionSet(forbidden);
        Set<Integer> alreadyVisitedPositionSet = new HashSet<>();
        Queue<JumpPositionData> queue = new LinkedList<>();
        queue.add(new JumpPositionData(0, false, 0));
        alreadyVisitedPositionSet.add(0);

        while(!queue.isEmpty()){
            JumpPositionData jumpPositionData = queue.poll();
            if(jumpPositionData.position == x)
                return jumpPositionData.numberOfJumps;

            if(isValidForwardJump(jumpPositionData.position + a, alreadyVisitedPositionSet, forbiddenSet)){
                JumpPositionData jumpPositionDataTemp = new JumpPositionData(jumpPositionData.position + a, false, jumpPositionData.numberOfJumps + 1);
                queue.add(jumpPositionDataTemp);
                alreadyVisitedPositionSet.add(jumpPositionData.position + a);
            }
            if(isValidBackwardJump(jumpPositionData.position - b, jumpPositionData.isbackward, alreadyVisitedPositionSet, forbiddenSet)){
                JumpPositionData jumpPositionDataTemp = new JumpPositionData(jumpPositionData.position - b, true, jumpPositionData.numberOfJumps + 1);
                queue.add(jumpPositionDataTemp);
                alreadyVisitedPositionSet.add(jumpPositionData.position + a);
            }
        }
        return -1;
    }

    private boolean isValidForwardJump(int positionToJump, Set<Integer> alreadyVisitedPositionSet, Set<Integer> forbiddenSet){
        if(alreadyVisitedPositionSet.contains(positionToJump) || forbiddenSet.contains(positionToJump) || positionToJump > 6000)
            return false;
        return true;
    }

    private boolean isValidBackwardJump(int positionToJump, boolean isPreviousBackwardJump, Set<Integer> alreadyVisitedPositionSet, Set<Integer> forbiddenSet){
        if(alreadyVisitedPositionSet.contains(positionToJump) || forbiddenSet.contains(positionToJump) || positionToJump < 0 || isPreviousBackwardJump)
            return false;
        return true;
    }

    private Set<Integer> forbiddenPositionSet(int[] forbidden){
        Set<Integer> forbiddenSet = new HashSet<>();
        for(int i = 0; i < forbidden.length; i++) {
            forbiddenSet.add(forbidden[i]);
        }
        return forbiddenSet;
    }
}

class JumpPositionData {
    int position;
    boolean isbackward;
    int numberOfJumps;

    JumpPositionData(int position, boolean isbackward, int numberOfJumps){
        this.position = position;
        this.isbackward = isbackward;
        this.numberOfJumps = numberOfJumps;
    }
}
