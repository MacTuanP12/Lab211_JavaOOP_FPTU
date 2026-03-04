
public class BubbleSortAlgorithm implements SortAlgorithm {


    @Override
    public int[] sort(int[] array, boolean ascending) {
        if (array == null || array.length <= 1) {
            return array;
        }

        // Create a copy to avoid modifying original array
        int[] sortedArray = array.clone();
        int n = sortedArray.length;

        // Bubble sort algorithm
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements based on sort order
                boolean shouldSwap = ascending
                    ? sortedArray[j] > sortedArray[j + 1]  // Ascending: swap if current > next
                    : sortedArray[j] < sortedArray[j + 1]; // Descending: swap if current < next

                if (shouldSwap) {
                    // Swap elements
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no swapping occurred, array is already sorted
            if (!swapped) {
                break;
            }
        }

        return sortedArray;
    }
}

