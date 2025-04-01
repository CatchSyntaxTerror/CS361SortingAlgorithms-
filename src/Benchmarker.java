package src;

public class Benchmarker {
    private static void benchmark(Function<int[], int[]> sort) {
        // benchmark the algorithm
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] sorted = sort(array);
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
    }
    public static void main(String[] args) {
        benchmark(MergeSort3::sort)
    }
}
