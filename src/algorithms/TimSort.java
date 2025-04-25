package algorithms;

public class TimSort implements SortingAlgorithm {
    public static int[] sort(int[] arr) {
        // n = the size of the array
        // size = the size of each run (or chunk) (32)
        int n = arr.length;
        int size = 32;

        // Insert sort each run
        for (int i = 0; i < n; i += size) {
            int temp = Math.min(i + size, n - 1);
            arr = insertionSort(arr, i, temp);
        }

        // left = the left side of the runs being merged
        // mid = the middle of the runs being merged
        int left = 0;
        int mid;
        // Merge sort each run
        // Runs until size surpasses length of arr
        while (size < n) {
            // Runs until left passes the length of arr
            while (left < n) {
                // Set right and middle
                int temp = Math.min(left + size * 2 - 1, n - 1);
                mid = left + size - 1;
                // Check if there is at least two runs left (mid < temp) and then merge
                if (mid < temp) {
                    arr = mergeSort(arr, left, mid, temp);
                }
                // Increment left to look at the next two runs
                left += size * 2;
            }
            // Reset left for inner loop and double size
            left = 0;
            size *= 2;
        }

        // Return sorted array
        return arr;
    }

    public static int[] insertionSort(int[] arr, int left, int right) {
        // Start i one past left (as the first number alone is automatically sorted)
        int i = left + 1;
        // Run through every number until the right side
        while (i <= right) {
            // Set j behind i
            // Set temp to the number inserted
            int j = i - 1;
            int temp = arr[i];
            // Run back until the beginning of the array or until smaller number found
            while (j >= left && temp < arr[j]) {
                // Push arr[j] forward
                arr[j + 1] = arr[j];
                j--;
            }
            // Insert arr[i]
            arr[j + 1] = temp;
            // Increment i
            i++;
        }
        return arr;
    }

    public static int[] mergeSort(int[] arr, int left, int mid, int right) {
        // Split two runs into two arrays
        int len1 = mid - left + 1;
        int len2 = right - mid;
        int[] l = new int[len1];
        int[] r = new int[len2];
        for (int i = 0; i < len1; i++) {
            l[i] = arr[left + i];
        }
        for (int i = 0; i < len2; i++) {
            r[i] = arr[mid + i + 1];
        }

        // Merge the left and right arrays
        int i = 0;
        int j = 0;
        int k = left;
        while (i < len1 && j < len2) {
            // Check lower number and add that to arr, then increment corresponding num
            if (l[i] <= r[j]) {
                arr[k] = l[i];
                i++;
            } else {
                arr[k] = r[j];
                j++;
            }
            k++;
        }

        // Add remaining numbers from l
        while (i < len1) {
            arr[k] = l[i];
            i++;
            k++;
        }
        // Add remaining numbers for r
        while (j < len2) {
            arr[k] = r[j];
            j++;
            k++;
        }

        return arr;
    }
}
