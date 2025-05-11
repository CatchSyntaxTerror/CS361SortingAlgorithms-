package algorithms;

import java.util.Random;

/**
 * The RandomQuickSort class implements the randomized quick sort algorithm. In
 * each iteration, the pivot must be randomly chosen using a random int
 * generator.
 * Author: Nickolas Chacon
 */
public class RandomQuickSort implements SortingAlgorithm {

    private static Random rand = new Random();

    /**
     * Chooses a random pivot and calls normal partition.
     * @param a The array to sort
     * @param p Starting index
     * @param r Ending index
     * @return The pivots final location
     */
    private static int randomizedPartition(int[] a, int p, int r) {
        //nextInt(r - p + 1): 0-(r-p)
        //p + rand.nextInt(r - p + 1): p-r
        int randomIndex = p + rand.nextInt(r - p + 1);

        //swapping a[randomIndex] with a[r] to use it as pivot in normal
        //partition(end expected)
        int temp = a[randomIndex];
        a[randomIndex] = a[r];
        a[r] = temp;

        //normal partition
        return partition(a, p, r);
    }
    private static int randomizedPartition(double[] a, int p, int r) {
        //nextInt(r - p + 1): 0-(r-p)
        //p + rand.nextInt(r - p + 1): p-r
        int randomIndex = p + rand.nextInt(r - p + 1);

        //swapping a[randomIndex] with a[r] to use it as pivot in normal
        //partition(end expected)
        double temp = a[randomIndex];
        a[randomIndex] = a[r];
        a[r] = temp;

        //normal partition
        return partition(a, p, r);
    }

    /**
     * The partition step of quicksort does one round of sorting so that all
     * elements less than or equal to the pivot are on the left and all elements
     * greater than the pivot are on the right.
     * @param a The array to sort
     * @param p Starting index
     * @param r Ending index
     * @return The pivots final location
     */
    private static int partition(int[] a, int p, int r) {
        //x is the pivot
        int x = a[r];
        int i = (p - 1);
        for (int j = p; j < r; j++) {
            //If the current element is smaller or equal to pivot, it
            // increments the smallest element space and swaps.
            if (a[j] <= x) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        //put pivot in correct pos
        int temp = a[i + 1];
        a[i + 1] = a[r];
        a[r] = temp;
        //return pivot
        return i + 1;
    }
    private static int partition(double[] a, int p, int r) {
        //x is the pivot
        double x = a[r];
        int i = (p - 1);
        for (int j = p; j < r; j++) {
            //If the current element is smaller or equal to pivot, it
            // increments the smallest element space and swaps.
            if (a[j] <= x) {
                i++;
                double temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        //put pivot in correct pos
        double temp = a[i + 1];
        a[i + 1] = a[r];
        a[r] = temp;
        //return pivot
        return i + 1;
    }


    /**
     * randQuickSort sorts an array in O(nlogn) time from smallest to largest.
     * @param a The array to sort
     * @param p Starting index
     * @param r Ending index
     */
    private static void randQuickSort(int[] a, int p, int r) {
        if (p < r) {
            int q = randomizedPartition(a, p, r);

            //Recursive calls using pivot
            randQuickSort(a, p, q - 1);
            randQuickSort(a, q + 1, r);
        }
    }
    private static void randQuickSort(double[] a, int p, int r) {
        if (p < r) {
            int q = randomizedPartition(a, p, r);

            //Recursive calls using pivot
            randQuickSort(a, p, q - 1);
            randQuickSort(a, q + 1, r);
        }
    }

    public static int[] sort(int[] arr) {
        randQuickSort(arr, 0, arr.length-1);
        return arr;
    }
    public static double[] sort(double[] arr) {
        randQuickSort(arr, 0, arr.length-1);
        return arr;
    }


    /**
     * Students sort
     * @param a
     * @param p
     * @param r
     * @return
     */
    private static int partition(Student[] a, int p, int r) {
        Student pivot = a[r];
        int i = p - 1;

        for (int j = p; j < r; j++) {
            if (compare(a[j], pivot) <= 0) {
                i++;
                Student temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        Student temp = a[i + 1];
        a[i + 1] = a[r];
        a[r] = temp;

        return i + 1;
    }

    private static int randomizedPartition(Student[] a, int p, int r) {
        int randomIndex = p + rand.nextInt(r - p + 1);
        Student temp = a[randomIndex];
        a[randomIndex] = a[r];
        a[r] = temp;

        return partition(a, p, r);
    }

    private static void randQuickSort(Student[] a, int p, int r) {
        if (p < r) {
            int q = randomizedPartition(a, p, r);
            randQuickSort(a, p, q - 1);
            randQuickSort(a, q + 1, r);
        }
    }

    //sort descending by gpa, ascending by lastName
    private static int compare(Student s1, Student s2) {
        if (Double.compare(s2.gpa, s1.gpa) != 0)
            return Double.compare(s2.gpa, s1.gpa);
        return s1.lastName.compareTo(s2.lastName);
    }

    public static Student[] sort(Student[] arr) {
        randQuickSort(arr, 0, arr.length - 1);
        return arr;
    }


    /**
     * Testing
     * @param args
     */
    public static void main(String args[]) {
        Student[] students = {
                new Student(3.5, "jon", 1),
                new Student(3.9, "bob", 2),
                new Student(3.5, "rob", 3),
                new Student(4.0, "mack", 4),
                new Student(3.5, "finn", 5)
        };

        sort(students);

        for (Student s : students)
            System.out.println(s);
    }

}


class Student {
    double gpa;
    String lastName;
    int id;

    public Student(double gpa, String lastName, int id) {
        this.gpa = gpa;
        this.lastName = lastName;
        this.id = id;
    }

    public String toString() {
        return "[" + gpa + ", " + lastName + ", ID: " + id + "]";
    }
}
