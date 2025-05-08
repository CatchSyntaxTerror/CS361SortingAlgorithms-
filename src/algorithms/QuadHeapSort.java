package algorithms;

import java.util.Arrays;

/**
 * Author: Youssef Amin
 * this class is just here so you guys can see it works
 * Realistically after making the QuadHeap object,
 * you can just call heapName.heapsort()
 */
public class QuadHeapSort implements SortingAlgorithm {
    public static void main(String[] args) {
        int[] test = new int[]{2,3,3,4,5,5,6,7,78,8,89,9,9,3,2,2,2,34,4,5,6,7
                ,7,8,98,9,9,7,6,5,34,32,2,3,4,5,65756,7,567,567,567,5, 345,
                34,34,5345,346,45,645,4,34,5345,645,645,645,4,3,3,34,4};
        IntegerQuadHeap maxHeap = new IntegerQuadHeap(test);
        System.out.println(Arrays.toString(test));
        sort(test);;
        System.out.println(Arrays.toString(test));

    }

    public static int[] sort(int[] array) {
        IntegerQuadHeap heap = new IntegerQuadHeap(array);
        heap.heapSort();
        return array;
    }

    public static double[] sort(double[] array) {
        DoubleQuadHeap heap = new DoubleQuadHeap(array);
        heap.heapSort();
        return array;
    }
}
