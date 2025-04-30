package function;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;
import algorithms.*;

public class Benchmarker {
    /**
     * How many times to run the algorithm for each dataset (averages the run
     * time of these all). This gets really slow for big sets.
     */
    private static final int BENCHMARKS = 50;
    private static final String DATA_PATH = "data/";

    private static Number[] loadData(String fileName) {
        String filePath = DATA_PATH + fileName;
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            if (fileName.contains("int")) {
                return (Number[]) lines.map(Integer::parseInt).toArray();
            } else {
                return (Number[]) lines.map(Double::parseDouble).toArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Number[0];
    }

    /**
     * Benchmark an algorithm, calculating average ms to sort
     * @param sortingAlgorithm Algorithm to sort with
     * @param array Template array used for each sort
     */
    private static void benchmark(Function<Number[], Number[]> sortingAlgorithm,
                                  Number[] array, int trials) {
        // TODO: benchmark the algorithm according to project instructions
        int[] times = new int[trials];
        for (int i = 0; i < trials; i++ ) {
            long startTime = System.currentTimeMillis();
            // can we ensure this array clone is discarded every time? does
            // it matter for benchmarking?
            Number[] sorted = sortingAlgorithm.apply(array.clone());
            long endTime = System.currentTimeMillis();
            times[i] = (int) (endTime - startTime);
            System.gc();    // attempt to rid the system of the array clone
        }
        int average = Arrays.stream(times).sum() / times.length;
        System.out.printf("Average sort time (%d trials): %dms\n", trials, average);
    }

    private static void benchmarkSet(String fileName, int trials) {
        System.out.println("Benchmarking: " + fileName);
        Number[] data = loadData(fileName);
        if (data.length == 0) return;
        System.out.println("3 Merge Sort:");
        benchmark(MergeSort3::sort, data, trials);
        System.out.println("Random Quick Sort:");
        benchmark(RandomQuickSort::sort, data, trials);
        System.out.println("QuadTree Sort:");
        benchmark(QuadHeapSort::sort, data, trials);
        System.out.println("Tim Sort:");
        benchmark(TimSort::sort, data, trials);
        System.out.println();
    }

    public static void benchmark(int startExp, int endExp, int trials) {
        for (int exp = startExp; exp <= endExp; exp++) {
            System.gc();
            // i wonder if memory will be an issue for the larger files
            String fileName = "ints_" + exp + ".txt";
            benchmarkSet(fileName, trials);
        }
        for (int exp = startExp; exp <= endExp; exp++) {
            System.gc();
            // i wonder if memory will be an issue for the larger files
            String fileName = "doubles_" + exp + ".txt";
            benchmarkSet(fileName, trials);
        }
    }
}
