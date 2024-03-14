/**
 * This class contains an algorithm utilizing a stack or queue.
 */
public class MyAlgorithm {

    // Constants
    private static final String NULL_POINTER = "Null argument not allowed.";

    /**
     * Finds the average difference between consecutive elements in the
     * monotonically increasing subsequence starting from the first element in
     * the array
     * 
     * @param arr the array containing the monotonic subsequence
     * @throws NullPointerException if the specified array is null
     * @return the average difference between elements in the subsequence
     */
    public static double avgDiffMonotonicIncreasing(int[] arr) {

        if (arr == null) {
            throw new NullPointerException(NULL_POINTER);
        }

        if (arr.length <= 1) {
            return 0;

        } else {

            int lastIncluded = arr[0];
            double totalDiff = 0;
            int count = 0;

            for (int i = 1; i < arr.length; i++) {
                // Only add to the subsequence if current element is greater than the last
                // included element
                if (arr[i] > lastIncluded) {
                    totalDiff += arr[i] - lastIncluded; // Add the difference to totalDiff
                    lastIncluded = arr[i]; // Update the last included element
                    count++; // Increment count of differences
                }
            }
            if (count == 0) {
                return 0;
            } else {
                return totalDiff / count;
            }

        }
    }
}
