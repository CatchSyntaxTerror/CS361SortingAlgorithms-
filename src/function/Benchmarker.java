package function;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
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

    private static int[] loadIntData(int dataSize) {
        String filePath = DATA_PATH + "ints_" + dataSize + ".txt";
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[0];
    }
    private static double[] loadDoubleData(int dataSize) {
        String filePath = DATA_PATH + "doubles_" + dataSize + ".txt";
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.mapToDouble(Double::parseDouble).toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new double[0];
    }

    private static int benchmarkOnArray(int[] array, String algorithmName) {
        long startTime = System.currentTimeMillis();
        switch (algorithmName) {
            case "MS3" -> MergeSort3.sort(array);
            case "RQS" -> RandomQuickSort.sort(array);
            case "QHS" -> QuadHeapSort.sort(array);
            case "TS" -> TimSort.sort(array);
            default -> {
                System.out.println("Invalid algorithm. Valid options: MS3, " +
                        "RQS, QHS, TS");
                return 0;
            }
        }
        long endTime = System.currentTimeMillis();
        System.gc();    // attempt to rid the system of the array clone
        return (int) (endTime - startTime);
    }
    private static int benchmarkOnArray(double[] array, String algorithmName) {
        long startTime = System.currentTimeMillis();
        switch (algorithmName) {
            case "MS3" -> MergeSort3.sort(array);
            case "RQS" -> RandomQuickSort.sort(array);
            case "QHS" -> QuadHeapSort.sort(array);
            case "TS" -> TimSort.sort(array);
            default -> {
                System.out.println("Invalid algorithm. Valid options: MS3, " +
                        "RQS, QHS, TS");
                return 0;
            }
        }
        long endTime = System.currentTimeMillis();
        System.gc();    // attempt to rid the system of the array clone
        return (int) (endTime - startTime);
    }

	public static void benchmarkSingle(String algorithmName, String dataType,
                                       int dataSize) {
        int time = switch(dataType) {
            case "ints" -> benchmarkOnArray(loadIntData(dataSize),
                    algorithmName);
            case "doubles" -> benchmarkOnArray(loadDoubleData(dataSize),
                    algorithmName);
            default -> {
                System.out.println("Invalid algorithm.");
                yield 0;
            }
        };
        System.out.println(time);
	}
}
