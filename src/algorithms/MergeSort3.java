package algorithms;

/**
 * Class implementing Three-Way Merge Sort
 * 
 * TODO: Possibly implement some comparator to allow sorting of abitrary values (instead of just ints)
 */
public final class MergeSort3 implements SortingAlgorithm {
    /**
     * Merge 3 arrays into one large, sorted array.
     * @param a
     * @param b
     * @param c
     * @return
     */
    private static Number[] merge(Number[] a, Number[] b, Number[] c) {
        int i = 0, j = 0, k = 0;
        int n = a.length + b.length + c.length;
        Number[] result = new Number[n];
        while (i+j+k < n) {
            Number min = Double.MAX_VALUE;
            if (i < a.length && a[i].doubleValue() < min.doubleValue()) min = a[i];
            if (j < b.length && b[j].doubleValue() < min.doubleValue()) min = b[j];
            if (k < c.length && c[k].doubleValue() < min.doubleValue()) min = c[k];
            result[i+j+k] = min;
            if (i < a.length && a[i].equals(min)) i++;
            else if (j < b.length && b[j].equals(min)) j++;
            else if (k < c.length && c[k].equals(min)) k++;
        }
        return result;
    }

    /**
     * Sorts the portion of the array from [low, high). Using indices instead of subarrays for memory efficiency.
     * @param array
     * @param low
     * @param high
     */
    private static Number[] sort(Number[] array, int low, int high) {
        int size = (high-low);
        int portion = size/3;
        if (portion == 0) {
            // array is too small to be 3-sorted
            if (size == 0) return new Number[0]; // this shouldn't happen
            if (size == 1) return new Number[]{array[low]};
            if (size == 2) {
                if (array[low].doubleValue() > array[high-1].doubleValue()) {
                    return new Number[]{array[high-1], array[low]};
                } else {
                    return new Number[]{array[low], array[high-1]};
                }
            }
        }
        // sort & merge:
        Number[] result = new Number[size];
        int mid1 = low + portion;
        int mid2 = mid1 + portion;
        Number[] a = sort(array, low, mid1);
        Number[] b = sort(array, mid1, mid2);
        Number[] c = sort(array, mid2, high);
        result = merge(a, b, c);
        return result;
    }
    /**
     * Sort the array using Three-Way Merge Sort.
     * @param array
     * @return
     */
    public static Number[] sort(Number[] array) {
        return sort(array, 0, array.length);
    }

    /**
     * Test MS3
     * @param args
     */
    public static void main(String[] args) {
        Number[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        Number[] sorted = sort(array);
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
    }
}