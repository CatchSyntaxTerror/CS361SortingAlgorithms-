package src.algorithms;

import src.SortingAlgorithm;
import src.datastructures.QuadTree;;

public class QuadTreeSort implements SortingAlgorithm {
    public static int[] sort(int[] array) {
        QuadTree tree = new QuadTree(array);
        return tree.toArray();
    }
}