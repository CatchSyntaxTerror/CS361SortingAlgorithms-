package MergeSort3;

class MergeSort3 {
    private static int[] merge(int[] a, int[] b, int[] c) {
        int i = 0, j = 0, k = 0;
        int n = a.length + b.length + c.length;
        int[] result = new int[n];
        while (i < a.length || j < b.length || k < c.length) {
            // set the next element of result to the minimum from the available lists
        }
        return result;
    }
    /**
     * Sorts the portion of the array from [low, high)
     * @param array
     * @param low
     * @param high
     */
    private static int[] sort(int[] array, int low, int high) {
        int size = (high-low);
        int portion = size/3;
        if (portion == 0) {
            // array is too small to be 3-sorted
            if (size == 0) return new int[0]; // this shouldn't happen
            if (size == 1) return new int[]{array[low]};
            if (size == 2) {
                if (array[low] > array[high-1]) {
                    return new int[]{array[high-1], array[low]};
                } else {
                    return new int[]{array[low], array[high-1]};
                }
            }
        }
        // sort & merge:
        int[] result = new int[size];
        int mid1 = low + portion;
        int mid2 = mid1 + portion;
        int[] a = sort(array, low, mid1);
        int[] b = sort(array, mid1, mid2);
        int[] c = sort(array, mid2, high);
        result = merge(a, b, c);
        return result;
    }
    public static int[] sort(int[] array) {
        return sort(array, 0, array.length);
    }

    public static void main(String[] args) {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] sorted = sort(array);
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.println();
    }
}