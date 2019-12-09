import java.util.Comparator;
import java.util.PriorityQueue;

public class KSmallestSortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        if(null == matrix)
            throw new RuntimeException("Input matrix is empty");

        if(k < 0){
            throw new RuntimeException("Value for K is invalid");
        }
        // Initialize priority queue with size 'K'
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(k, new DescendingOrderComparator());
        addKElementsInMaxPriorityQueue(k, matrix, maxPriorityQueue);
        return maxPriorityQueue.peek();
    }

    /**
     * 
     * @param k
     * @param matrix
     * @param maxPriorityQueue
     */
    private void addKElementsInMaxPriorityQueue(int k, int[][] matrix, PriorityQueue<Integer> maxPriorityQueue){
        for(int i=0; i < matrix.length; i++){
            for(int j =0; j <matrix[0].length; j++){
                if(k > maxPriorityQueue.size()){
                    maxPriorityQueue.add(matrix[i][j]);
                } else if(maxPriorityQueue.peek() > matrix[i][j] ){
                    maxPriorityQueue.poll();
                    maxPriorityQueue.add(matrix[i][j]);
                }
            }
        }
    }

    private static class DescendingOrderComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

    public static void main(String[] args){
        KSmallestSortedMatrix kSmallestSortedMatrix = new KSmallestSortedMatrix();
        int[][] matrix = {
                            {1, 5, 9},
                            {10, 11, 13},
                            {12, 13, 15}
        };
        System.out.println(kSmallestSortedMatrix.kthSmallest(matrix,9));
    }
}


