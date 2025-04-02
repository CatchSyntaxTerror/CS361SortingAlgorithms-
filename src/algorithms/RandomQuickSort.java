package src.algorithms;

import java.util.Random;

/**
 * The RandomQuickSort class implements the randomized quick sort algorithm. In
 * each iteration, the pivot must be randomly chosen using a random number
 * generator.
 * Student: Nickolas Chacon
 */
public class RandomQuickSort implements SortingAlgorithm {

    private static Random rand = new Random();

    /**
     * Chooses a random pivot and calls normal partition.
     * @param a The array to sort
     * @param p Starting index
     * @param r Ending index
     * @return The pivots final location
     */
    private static int randomizedPartition(int a[], int p, int r) {
        //nextInt(r - p + 1): 0-(r-p)
        //p + rand.nextInt(r - p + 1): p-r
        int randomIndex = p + rand.nextInt(r - p + 1);

        //swapping a[randomIndex] with a[r] to use it as pivot in normal
        //partition(end expected)
        int temp = a[randomIndex];
        a[randomIndex] = a[r];
        a[r] = temp;

        //normal partition
        return partition(a, p, r);
    }

    /**
     * The partition step of quicksort does one round of sorting so that all
     * elements less than or equal to the pivot are on the left and all elements
     * greater than the pivot are on the right.
     * @param a The array to sort
     * @param p Starting index
     * @param r Ending index
     * @return The pivots final location
     */
    private static int partition(int a[], int p, int r) {
        //x is the pivot
        int x = a[r];
        int i = (p - 1);
        for (int j = p; j < r; j++) {
            //If the current element is smaller or equal to pivot, it
            // increments the smallest element space and swaps.
            if (a[j] <= x) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        //put pivot in correct pos
        int temp = a[i + 1];
        a[i + 1] = a[r];
        a[r] = temp;
        //return pivot
        return i + 1;
    }


    /**
     * randQuickSort sorts an array in O(nlogn) time from smallest to largest.
     * @param a The array to sort
     * @param p Starting index
     * @param r Ending index
     */
    private static void randQuickSort(int a[], int p, int r) {
        if (p < r) {
            int q = randomizedPartition(a, p, r);

            //Recursive calls using pivot
            randQuickSort(a, p, q - 1);
            randQuickSort(a, q + 1, r);
        }
    }

    public static int[] sort(int[] arr) {
        randQuickSort(arr, 0, arr.length-1);
        return arr;
    }

    /**
     * Testing
     * @param args
     */
    public static void main(String args[]) {
        int a[] = {10, 7, 8, 9, 1, 5};
        int len = a.length;
        sort(a);

        for (int i = 0; i < len; ++i)
            System.out.print(a[i] + " ");
    }
}