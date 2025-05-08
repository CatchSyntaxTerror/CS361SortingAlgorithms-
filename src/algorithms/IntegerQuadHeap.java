package algorithms;

import java.util.Arrays;

/**
 * Author: Youssef Amin
 * Quad-Heap implementation
 * A heap where each node can have up to four children instead of two.
 * This structure maintains the max-heap property, supports heapifying, sorting,
 * and faster insertions and deletions compared to a traditional binary heap.

 * Why QuadHeap is better than a normal binary heap:
 * - Faster operations: Shorter tree height means fewer steps when bubbling up or down.
 * - Lower height: QuadHeap has a height of log₄(n) instead of log₂(n), making traversal quicker.
 * - Better cache performance: A shallower, array-based structure improves memory access speed.

 * In short: QuadHeap climbs up and down faster.
 *
 */
public class IntegerQuadHeap {
    private int[] HEAP;
    private int SIZE;

    /**
     * constructor for empty array
     * @param capacity the size of the array
     */
    public IntegerQuadHeap(int capacity) {
        HEAP = new int[capacity];
        SIZE = 0;
    }

    /**
     * constructor for filled array
     * @param data the array of data to be heapified
     */
    public IntegerQuadHeap(int[] data) {
        HEAP = data;
        SIZE = data.length;
        heapify();
    }

    /**
     * allows you to get the parent of any index
     * @param childIndex the index for which you wish to get the parent
     * @return the given child's parent index.
     */
    private int getParentIndex(int childIndex) {
        if (childIndex <= 0) return -1;
        return (childIndex - 1) / 4;
    }

    /**
     * allows you to get any child of a particular index
     * @param parentIndex the index for which you wish to get the child
     * @param childNumber the child you wish to get i.e. 1, 2, 3, 4
     * @return the index of the specified child
     */
    private int getChildIndex(int parentIndex, int childNumber) {
        int child = 4 * parentIndex + childNumber;
        if(!inArray(child)){
            return -1;
        }
        return child;
    }

    /**
     * checks parent and swaps to maintain heap properties
     * @param index the starting index to bubble up from
     */
    private void bubbleUp(int index) {
        while(index > 0){
            int parentIndex = getParentIndex(index);
            if (HEAP[index] > HEAP[parentIndex]) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    /**
     * checks children and swaps to maintain maxHeap properties
     * @param index The starting index to bubble down from
     * @param last the index at the end of this portion of the array
     */
    private void bubbleDown(int index, int last) {
        while (hasChild(index, last)) {
            int largestChildIndex = findLargestChildIndex(index, last);
                if (largestChildIndex >= 0 && HEAP[index] < HEAP[largestChildIndex]) {
                    swap(index, largestChildIndex);
                    index = largestChildIndex;
                } else {
                    break;
                }
        }
    }

    /**
     * maxHeapifies the array
     */
    private void heapify(){
        for(int i = getParentIndex(SIZE - 1); i >= 0; i--){
            bubbleDown(i, SIZE - 1);
        }
    }

    /****
     * sorts the heap in ascending order
     */
    public void heapSort() {
        for(int i = SIZE - 1; i >= 0; i--) {
            swap(0,i);
            bubbleDown(0, i);
        }
    }

    /**
     * Inserts a new value into the heap, maintaining max-heap properties.
     * @param value the value to insert into the heap
     */
    public void insert(int value) {
        if (SIZE >= HEAP.length) {
            HEAP = Arrays.copyOf(HEAP, HEAP.length + 1);
        }
        HEAP[SIZE] = value;
        bubbleUp(SIZE);
        SIZE++;
    }

    /**
     * Deletes and returns the maximum element from the heap,
     * maintaining max-heap properties.
     * @return the maximum value from the heap
     */
    public int delete() {
        if(SIZE == 0){
            System.out.println("You cant delete something that doesn't exist");
            return Integer.MAX_VALUE;
        }
        int maxValue = HEAP[0];
        HEAP[0] = HEAP[SIZE - 1];
        SIZE--;
        bubbleDown(0, SIZE);
        return maxValue;
    }


    /**
     * checks if a given index has a child
     * @param parentIndex the given index
     * @return if a given index has a child.
     */
    private boolean hasChild(int parentIndex, int last) {
        return 4 * parentIndex + 1 < last;
    }

    /**
     * returns the smallest child value of a given index
     * @param parentIndex the given index
     * @return smallest child
     */
    private int findLargestChildIndex(int parentIndex, int last) {
        double largestValue = Double.MIN_VALUE;
        int largestChildIndex = -1;

        for (int childNumber = 1; childNumber <= 4; childNumber++) {
            int childIndex = getChildIndex(parentIndex, childNumber);
            if (childIndex < last && childIndex >= 0) {
                if (HEAP[childIndex] > largestValue) {
                    largestValue = HEAP[childIndex];
                    largestChildIndex = childIndex;
                }
            }
        }
        return largestChildIndex;
    }

    /**
     * checks a specific index is in the array
     * @param index index to check
     * @return true if index is less than the arrays length i.e. in the array
     */
    private boolean inArray(int index){
        return index < SIZE;
    }

    /**
     * swaps two elements in the heap
     * @param indexOne index of one element
     * @param indexTwo index of another element
     */
    private void swap(int indexOne, int indexTwo) {
        int temp = HEAP[indexOne];
        HEAP[indexOne] = HEAP[indexTwo];
        HEAP[indexTwo] = temp;
    }


    /**
     * allows you to print the heap. for debugging purposes
     */
    public void printHeap() {
        System.out.println("Heap: ");
        for(int i: HEAP){
            System.out.print(i + ", ");
        }
        System.out.println();
    }

}



