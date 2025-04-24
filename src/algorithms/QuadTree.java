package algorithms;

/**
 * Author: Youssef Amin
 * Quad-Heap implementation
 */
public class QuadTree implements SortingAlgorithm {
    public static Node root;
    public int[] treeArray;

    /**
     * constructor for a whole array of data
     * @param arr Data set, right now only int arrays
     */
    public QuadTree(int[] arr) {
        fill(arr);
        treeArray = new int[getSize(root)];
        //Todo: constructor not right
    }

    /**
     * constructor with one value to create the root
     * @param value root nodes value
     */
    public QuadTree(int value) {
        root = new Node(value);
    }


    /**
     * recursively and nodes from left to right
     *
     * @param node node to add
     */
    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            if (node.first == null) {
                node.first = insert(node.first, value);
            } else if (value > node.first.value) {
                node.second = insert(node.second, value);
            }
        } else {
            if (node.third == null) {
                node.third = insert(node.third, value);
            } else if (value > node.third.value) {
                node.fourth = insert(node.fourth, value);
            }
        }
        return node;
    }

    /**
     * fills the whole tree with an array of values
     * @param values a given array
     */
    public void fill(int[] values) {
        for (int val : values) {
            root = insert(root, val);
        }
    }

    /**
     * Thanks haskell ;)
     * @param node the root node of the tree
     * @return number of nodes in the tree
     */
    public int getSize(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + getSize(node.first)
                + getSize(node.second)
                + getSize(node.third)
                + getSize(node.fourth);
    }


    /**
     * returns an array representation of the tree.
     * remember, in this case:
     * children would be 4n+1, 4n+2, 4n+3 and 4n+4, n = parent node
     *
     * @return the array of values
     */
    public int[] getTreeArray() {
        if (root == null) {
            return new int[0];
        }
        //Todo: heapify and just pop off the first element each time
        return null;
    }


    public int DFS(Node node, int value){
        if(node != null && value == node.value) return value;
        DFS(node.first, value);
        DFS(node.second, value);
        DFS(node.third, value);
        DFS(node.fourth, value);
        return Integer.MAX_VALUE;
    }

    public void minHeapSort(Node node){
        Node[] children = new Node[]{node.first, node.second, node.third, node.fourth};
        for (int i = 0; i < children.length; i++) {
            if(children[i] == null) return;
            if(children[i].value < node.value){
                swapValues(children[i], node);
            }
            minHeapSort(children[i]);
        }
    }

    private void swapValues(Node fir, Node sec){
        int temp = fir.value;
        fir.value = sec.value;
        sec.value = temp;
    }

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

    /**
     * this main method is just for me to test the tree
     * @param args nada
     */
    public static void main(String[] args) {
        int[] exImp = new int[]{12, 10, 8, 6, 11, 9, 7, 4, 3, 5, 2, 1};

    }

    public class Node {
        private int value;
        Node first;
        Node second;
        Node third;
        Node fourth;

        public Node(int value) {
            this.value = value;
        }

        /**
         * string representation of a node. for debugging purposes.
         *
         * @return node as string with children
         */
        public String nodeToString() {
            return "[" + value + "], Children: 1. [" + first + "], " +
                    "2. [" + second + "], 3. [" + third + "], 4. [" + fourth + "]";
        }
    }
}


