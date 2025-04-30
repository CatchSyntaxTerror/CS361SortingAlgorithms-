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

    private static int[] loadIntData(String fileName) {
        String filePath = DATA_PATH + fileName;
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[0];
    }
    private static double[] loadDoubleData(String fileName) {
        String filePath = DATA_PATH + fileName;
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.mapToDouble(Double::parseDouble).toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new double[0];
    }

    /**
     * Benchmark an algorithm, calculating average ms to sort
     * @param sortingAlgorithm Algorithm to sort with
     * @param array Template array used for each sort
     */
    private static void benchmark(Function<int[], int[]> sortingAlgorithm, int[] array) {
        // TODO: benchmark the algorithm according to project instructions
        int[] times = new int[BENCHMARKS];
        for (int i = 0; i < BENCHMARKS; i++ ) {
            long startTime = System.currentTimeMillis();
            // can we ensure this array clone is discarded every time? does
            // it matter for benchmarking?
            int[] sorted = sortingAlgorithm.apply(array.clone());
            long endTime = System.currentTimeMillis();
            times[i] = (int) (endTime - startTime);
            System.gc();    // attempt to rid the system of the array clone
        }
        int average = Arrays.stream(times).sum() / times.length;
        System.out.printf("Average sort time: %dms\n", average);
    }

    private static void benchmarkSet(String fileName) {
        System.out.println("Benchmarking: " + fileName);
        int[] intData = loadIntData(fileName);
        if (intData.length == 0) return;
        System.out.println("3 Merge Sort:");
        benchmark(MergeSort3::sort, intData);
        System.out.println("Random Quick Sort:");
        benchmark(RandomQuickSort::sort, intData);
        System.out.println("QuadTree Sort:");
        benchmark(QuadHeapSort::sort, intData);
        System.out.println("Tim Sort:");
        benchmark(TimSort::sort, intData);
        System.out.println();
    }

    public static void benchmark(int startExp, int endExp) {
        for (int exp = startExp; exp <= endExp; exp++) {
            System.gc();
            // i wonder if memory will be an issue for the larger files
            String fileName = "ints_" + exp + ".txt";
            benchmarkSet(fileName);
        }
        for (int exp = startExp; exp <= endExp; exp++) {
            System.gc();
            // i wonder if memory will be an issue for the larger files
            String fileName = "doubles_" + exp + ".txt";
            benchmarkSet(fileName);
        }
    }
}
