package function;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
    private static boolean VERBOSE = false;

    private static Number[] loadData(String fileName) {
        String filePath = DATA_PATH + fileName;
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            int i = 0;
            if (fileName.contains("int")) {
                int[] ints = lines.mapToInt(Integer::parseInt).toArray();
                Number[] numbers = new Number[ints.length];
                for (int x : ints) {
                    numbers[i++] = x;
                }
                return numbers;
            } else {
                double[] doubles = lines.mapToDouble(Double::parseDouble).toArray();
                Number[] numbers = new Number[doubles.length];
                for (double x : doubles) {
                    numbers[i++] = x;
                }
                return numbers;
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
    private static int benchmark(Function<Number[], Number[]> sortingAlgorithm,
                                  Number[] array, int trials) {
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
        if (VERBOSE) System.out.printf("Average sort time (%d trials): %dms\n", trials, average);
        return average;
    }

    private static int[] benchmarkSet(String fileName, int trials) {
        if (VERBOSE) System.out.println("Benchmarking: " + fileName);
        int[] times = new int[4];
        Number[] data = loadData(fileName);
        if (data.length == 0) return null;
        if (VERBOSE) System.out.println("3 Merge Sort:");
        times[0] = benchmark(MergeSort3::sort, data, trials);
        if (VERBOSE) System.out.println("Random Quick Sort:");
        times[1] = benchmark(RandomQuickSort::sort, data, trials);
        if (VERBOSE) System.out.println("QuadTree Sort:");
        times[2] = benchmark(QuadHeapSort::sort, data, trials);
        if (VERBOSE) System.out.println("Tim Sort:");
        times[3] = benchmark(TimSort::sort, data, trials);
        if (VERBOSE) System.out.println();
        return times;
    }

    public static void benchmark(int startExp, int endExp, int trials) {
        if (endExp<startExp) {
            System.out.println("Invalid range");
            return;
        }
        int range = endExp-startExp+1;
        int[][] intTimesBySize = new int[range][];
        int[][] doubleTimesBySize = new int[range][];
        for (int exp = startExp; exp <= endExp; exp++) {
            System.gc();
            // i wonder if memory will be an issue for the larger files
            String fileName = "ints_" + exp + ".txt";
            intTimesBySize[exp-startExp] = benchmarkSet(fileName, trials);
        }
        for (int exp = startExp; exp <= endExp; exp++) {
            System.gc();
            // i wonder if memory will be an issue for the larger files
            String fileName = "doubles_" + exp + ".txt";
            doubleTimesBySize[exp-startExp] = benchmarkSet(fileName, trials);
        }
        System.out.println("Size,3MS Int,3MS Double,RQS Int,RQS Double," +
                "QTS Int,QTS Double,TS Int,TS Double");
        for (int i = 0; i<range;i++){
            System.out.print(startExp+i);
            for (int j = 0; j<4;j++){
                System.out.printf(",%d,%d", intTimesBySize[i][j], doubleTimesBySize[i][j]);
            }
            System.out.println();
        }
    }
    public static void benchmark(int startExp, int endExp, int trials, boolean verbose) {
        VERBOSE = verbose;
        benchmark(startExp, endExp, trials);
    }
}
