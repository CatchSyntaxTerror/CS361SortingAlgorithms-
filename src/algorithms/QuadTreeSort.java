package algorithms;

import src.SortingAlgorithm;

public class QuadTreeSort implements SortingAlgorithm {
    public static int[] sort(int[] array) {
        QuadTree tree = new QuadTree(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(tree.getTreeArray()[i] + ", ");
        }
        System.out.println();
        tree.minHeapSort(tree.root);
        for (int i = 0; i < array.length; i++) {
            System.out.print(tree.getTreeArray()[i] + ", ");
        }
        return tree.getTreeArray();
    }

    public static void main(String[] args) {
        int[] test = new int[]{1,2,3,4,5,6,5,4,3,2,1};
        sort(test);
    }
}