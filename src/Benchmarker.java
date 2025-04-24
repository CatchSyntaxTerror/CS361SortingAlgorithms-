import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;
import algorithms.*;

public class Benchmarker {
    private static int[] loadIntData(String fileName) {
        String filePath = "data/" + fileName;
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[0];
    }
    private static double[] loadDoubleData(String fileName) {
        String filePath = "data/" + fileName;
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.mapToDouble(Double::parseDouble).toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new double[0];
    }

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
        System.out.println(loadIntData("testData.txt"));
        System.out.println("3 Merge Sort:");
        benchmark(MergeSort3::sort);
        System.out.println("Random Quick Sort:");
        benchmark(RandomQuickSort::sort);
        System.out.println("QuadTree Sort:");
        benchmark(QuadTree::sort);
        System.out.println("Tim Sort:");
        benchmark(TimSort::sort);
    }
}
