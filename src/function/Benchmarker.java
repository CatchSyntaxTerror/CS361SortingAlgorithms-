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
    private static final String DATA_PATH = "/data";

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

    private static Student[] loadStudentData(int dataSize) {
        String filePath = DATA_PATH + "students_" + dataSize + ".txt";
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines
                    .map(line -> {
                        String[] parts = line.split(",");
                        double gpa = Double.parseDouble(parts[0]);
                        String lastName = parts[1];
                        int id = Integer.parseInt(parts[2]);
                        return new Student(gpa, lastName, id);
                    })
                    .toArray(Student[]::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Student[0];
    }

    private static long benchmarkOnArray(Student[] array, String algorithmName) {
        long startTime = System.currentTimeMillis();
        switch (algorithmName) {
            case "RQS" -> RandomQuickSort.sort(array);
            default -> {
                System.out.println("Invalid student algorithm. Valid: RQS");
                return 0;
            }
        }
        long endTime = System.currentTimeMillis();
        System.gc();
        return (endTime - startTime);
    }

    private static long benchmarkOnArray(int[] array, String algorithmName) {
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
        return (endTime - startTime);
    }
    private static long benchmarkOnArray(double[] array, String algorithmName) {
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
        return (endTime - startTime);
    }

	public static void benchmarkSingle(String algorithmName, String dataType,
                                       int dataSize) {
        long time = switch(dataType) {
            case "ints" -> benchmarkOnArray(loadIntData(dataSize),
                    algorithmName);
            case "doubles" -> benchmarkOnArray(loadDoubleData(dataSize),
                    algorithmName);
            case "students" -> benchmarkOnArray(loadStudentData(dataSize),
                    algorithmName);
            default -> {
                System.out.println("Invalid algorithm.");
                yield 0;
            }
        };
        System.out.println(time);
        System.gc();
	}
}
