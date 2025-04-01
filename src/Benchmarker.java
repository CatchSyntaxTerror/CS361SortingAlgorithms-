package src;

import java.util.function.Function;

import src.Algorithms.MergeSort3;

public class Benchmarker {
    private static void benchmark(Function<int[], int[]> sortingAlgorithm) {
        // TODO: benchmark the algorithm according to project instructions
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] sorted = sortingAlgorithm.apply(array);
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        benchmark(MergeSort3::sort);
    }
}
