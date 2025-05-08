package algorithms;

public class TimSort implements SortingAlgorithm {
    public static int[] sort(int[] arr) {
        int n = arr.length;
        int size = 32;
        for (int i = 0; i < n; i += size) {
            int temp = Math.min(i + size, n - 1);
            arr = insertionSort(arr, i, temp);
        }
        int left = 0;
        int mid;
        while (size < n) {
            while (left < n) {
                int temp = Math.min(left + size * 2 - 1, n - 1);
                mid = left + size - 1;
                if (mid < temp) {
                    arr = mergeSort(arr, left, mid, temp);
                }
                left += size * 2;
            }
            left = 0;
            size *= 2;
        }
        return arr;
    }

    public static double[] sort(double[] arr) {
        int n = arr.length;
        int size = 32;
        for (int i = 0; i < n; i += size) {
            int temp = Math.min(i + size, n - 1);
            arr = insertionSort(arr, i, temp);
        }
        int left = 0;
        int mid;
        while (size < n) {
            while (left < n) {
                int temp = Math.min(left + size * 2 - 1, n - 1);
                mid = left + size - 1;
                if (mid < temp) {
                    arr = mergeSort(arr, left, mid, temp);
                }
                left += size * 2;
            }
            left = 0;
            size *= 2;
        }
        return arr;
    }

    public static int[] insertionSort(int[] arr, int left, int right) {
        int i = left + 1;
        while (i <= right) {
            int j = i - 1;
            int temp = arr[i];
            while (j >= left && temp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
            i++;
        }
        return arr;
    }

    public static double[] insertionSort(double[] arr, int left, int right) {
        int i = left + 1;
        while (i <= right) {
            int j = i - 1;
            double temp = arr[i];
            while (j >= left && temp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
            i++;
        }
        return arr;
    }

    public static int[] mergeSort(int[] arr, int left, int mid, int right) {
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
        int i = 0;
        int j = 0;
        int k = left;
        while (i < len1 && j < len2) {
            if (l[i] <= r[j]) {
                arr[k] = l[i];
                i++;
            } else {
                arr[k] = r[j];
                j++;
            }
            k++;
        }
        while (i < len1) {
            arr[k] = l[i];
            i++;
            k++;
        }
        while (j < len2) {
            arr[k] = r[j];
            j++;
            k++;
        }
        return arr;
    }

    public static double[] mergeSort(double[] arr, int left, int mid,
                                     int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;
        double[] l = new double[len1];
        double[] r = new double[len2];
        for (int i = 0; i < len1; i++) {
            l[i] = arr[left + i];
        }
        for (int i = 0; i < len2; i++) {
            r[i] = arr[mid + i + 1];
        }
        int i = 0;
        int j = 0;
        int k = left;
        while (i < len1 && j < len2) {
            if (l[i] <= r[j]) {
                arr[k] = l[i];
                i++;
            } else {
                arr[k] = r[j];
                j++;
            }
            k++;
        }
        while (i < len1) {
            arr[k] = l[i];
            i++;
            k++;
        }
        while (j < len2) {
            arr[k] = r[j];
            j++;
            k++;
        }
        return arr;
    }
}
