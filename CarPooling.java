import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 *
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
 *
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi <= 100
 * 0 <= fromi < toi <= 1000
 * 1 <= capacity <= 105
 */
public class CarPooling {

    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> pqOfCurrentTrip = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        int carCapacitySoFar = 0;
        for(int i = 0; i < trips.length; i++){
            int[] currentTrip = trips[i];
            if(!pqOfCurrentTrip.isEmpty()){
                int[] peekElement = null;
                while(!pqOfCurrentTrip.isEmpty() && (peekElement = pqOfCurrentTrip.peek())[2] <= currentTrip[1]){
                    pqOfCurrentTrip.poll();
                    carCapacitySoFar -= peekElement[0];
                }
            }
            if(carCapacitySoFar + currentTrip[0] > capacity){
                return false;
            }
            pqOfCurrentTrip.offer(currentTrip);
            carCapacitySoFar += currentTrip[0];
        }
        return true;
    }
}
