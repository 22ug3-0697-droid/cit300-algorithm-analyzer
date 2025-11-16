import java.util.Arrays;
import java.util.Random;

/**
 * CIT300 - Algorithm Analyzer
 * This program runs and measures the performance of
 * Linear Search, Binary Search, Bubble Sort, and Quick Sort.
 *
 * Simulating contributions from:
 * - Mithun (Linear Search)
 * - Vishwa (Binary Search)
 * - Rashmi (Bubble Sort)
 * - Dilan (Quick Sort)
 */
public class AlgorithmAnalyzer {

    // Define the input sizes
    private static final int[] SIZES = {100, 500, 1000};
    private static final Random rand = new Random();
    // Value to search for (we'll pick a random one from the array)
    private static int searchValue; 

    public static void main(String[] args) {
        // --- Mithun's Part ---
        runLinearSearch();
        
        // --- Vishwa's Part ---
        runBinarySearch();
        
        // --- Rashmi's Part ---
        runBubbleSort();
        
        // --- Dilan's Part ---
        runQuickSort();
    }

    // ===================================================================
    //  HELPER METHOD: Generate Random Array
    // ===================================================================
    /**
     * Creates an array of a given size filled with random integers.
     * It also sets a 'searchValue' to be a random element from the array.
     */
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(size * 10); // Random numbers up to 10x the size
        }
        
        // Pick a random element from the array to search for
        // This check prevents an error if the array size is 0
        if (size > 0) {
            searchValue = array[rand.nextInt(size)];
        } else {
            searchValue = 0;
        }
        return array;
    }

    // ===================================================================
    //  MEMBER 1: MITHUN - LINEAR SEARCH
    // ===================================================================
    public static void runLinearSearch() {
        System.out.println("Algorithm: Linear Search");
        System.out.println("Input Size | Time (ms)");
        System.out.println("-------------------------");

        for (int size : SIZES) {
            int[] array = generateRandomArray(size);
            int valueToFind = searchValue; // Use the value set by the helper
            
            long startTime = System.nanoTime();
            linearSearch(array, valueToFind);
            long endTime = System.nanoTime();
            
            double durationMs = (endTime - startTime) / 1_000_000.0;
            System.out.printf("%-10d | %.4f%n", size, durationMs);
        }
        System.out.println(); // Add a blank line for spacing
    }

    public static int linearSearch(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i; // Found
            }
        }
        return -1; // Not found
    }

    // ===================================================================
    //  MEMBER 2: VISHWA - BINARY SEARCH
    //  This is Vishwa's simulated commit.
    // ===================================================================
    public static void runBinarySearch() {
        System.out.println("Algorithm: Binary Search");
        System.out.println("Input Size | Time (ms)");
        System.out.println("-------------------------");

        for (int size : SIZES) {
            int[] array = generateRandomArray(size);
            
            // **IMPORTANT**: Binary Search requires a sorted array.
            // We sort it *before* starting the timer for the search itself.
            Arrays.sort(array); 
            
            // Make sure the value we search for is in the sorted array
            // (This is more robust than using the pre-sort searchValue)
            int valueToFind;
             if (size > 0) {
                 valueToFind = array[rand.nextInt(size)];
            } else {
                 valueToFind = 0; // Handle empty array
            }


            long startTime = System.nanoTime();
            binarySearch(array, valueToFind);
            long endTime = System.nanoTime();
            
            double durationMs = (endTime - startTime) / 1_000_000.0;
            System.out.printf("%-10d | %.4f%n", size, durationMs);
        }
        System.out.println();
    }

    public static int binarySearch(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == x)
                return m;
            if (arr[m] < x)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }

    // ===================================================================
    //  MEMBER 3: RASHMI - BUBBLE SORT
    //  This is Rashmi's simulated commit.
    // ===================================================================
    public static void runBubbleSort() {
        System.out.println("Algorithm: Bubble Sort");
        System.out.println("Input Size | Time (ms)");
        System.out.println("-------------------------");

        for (int size : SIZES) {
            int[] array = generateRandomArray(size);
            
            long startTime = System.nanoTime();
            bubbleSort(array);
            long endTime = System.nanoTime();
            
            double durationMs = (endTime - startTime) / 1_000_000.0;
            System.out.printf("%-10d | %.4f%n", size, durationMs);
        }
        System.out.println();
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // ===================================================================
    //  MEMBER 4: DILAN - QUICK SORT
    // ===================================================================
    public static void runQuickSort() {
        System.out.println("Algorithm: Quick Sort");
        System.out.println("Input Size | Time (.ms)");
        System.out.println("-------------------------");

        for (int size : SIZES) {
            int[] array = generateRandomArray(size);
            
            long startTime = System.nanoTime();
            quickSort(array, 0, array.length - 1);
            long endTime = System.nanoTime();
            
            double durationMs = (endTime - startTime) / 1_000_000.0;
            System.out.printf("%-10d | %.4f%n", size, durationMs);
        }
        System.out.println();
    }

    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is 
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /* This function takes last element as pivot, places
       the pivot element at its correct position in sorted
       array, and places all smaller (smaller than pivot)
       to left of pivot and all greater elements to right
       of pivot */
    public static int partition(int[] arr, int low, int high) {
         if (arr == null || arr.length == 0 || low > high) {
            return 0; // Or handle as error
        }
       
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}