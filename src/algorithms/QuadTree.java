package algorithms;

/**
 * Author: Youssef Amin
 * Quad-Heap implementation
 */
public class QuadTree {
    public static Node root;

    /**
     * constructor for tree
     * @param values array of integers
     */
    public QuadTree(int[] values ){
        root = new Node(0);
        fill(values, root, 0);
    }


    /**
     * fills the whole tree with an array of values
     * @param values a given array
     */
    public void fill(int[] values, Node node, int i) {
        node.value = values[i]; // set this node's value

        if (4 * i + 1 < values.length) {
            Node firstChild = new Node(0); // placeholder value
            node.setFirst(firstChild);
            fill(values, firstChild, 4 * i + 1);
        }
        if (4 * i + 2 < values.length) {
            Node secondChild = new Node(0);
            node.setSecond(secondChild);
            fill(values, secondChild, 4 * i + 2);
        }
        if (4 * i + 3 < values.length) {
            Node thirdChild = new Node(0);
            node.setThird(thirdChild);
            fill(values, thirdChild, 4 * i + 3);
        }
        if (4 * i + 4 < values.length) {
            Node fourthChild = new Node(0);
            node.setFourth(fourthChild);
            fill(values, fourthChild, 4 * i + 4);
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
        int size = getSize(root);
        int[] arr = new int[size];
        fillArray(root, arr, 0);
        return arr;
    }

    /**
     * this method is just a helper for getTreeArray()
     * @param node current node
     * @param arr array to fill
     * @param i index
     */
    private void fillArray(Node node, int[] arr, int i) {
        if (node == null || i >= arr.length) return;
        arr[i] = node.value;

        fillArray(node.first, arr, 4 * i + 1);
        fillArray(node.second, arr, 4 * i + 2);
        fillArray(node.third, arr, 4 * i + 3);
        fillArray(node.fourth, arr, 4 * i + 4);
    }


    public int DFS(Node node, int value){
        if (node == null) return Integer.MAX_VALUE;
        if (node.value == value) return value;

        int found = DFS(node.first, value);
        if (found != Integer.MAX_VALUE) return found;

        found = DFS(node.second, value);
        if (found != Integer.MAX_VALUE) return found;

        found = DFS(node.third, value);
        if (found != Integer.MAX_VALUE) return found;

        found = DFS(node.fourth, value);
        if (found != Integer.MAX_VALUE) return found;

        return Integer.MAX_VALUE;
    }

    public void minHeapSort(Node node){
        //Todo:  need:
        //heapify(Node node), to push down larger nodes.
        //minHeapSort() call heapify() starting from the bottom of the tree upward.
    }

    private void swapValues(Node fir, Node sec){
        int temp = fir.value;
        fir.value = sec.value;
        sec.value = temp;
    }

    public static class Node {
        int value;
        Node first;
        Node second;
        Node third;
        Node fourth;

        Node[] children = new Node[4];

        public Node(int value) {
            this.value = value;
        }

        //setters
        public void setFirst(Node node) {
            this.first = node;
            this.children[0] = node;
        }

        public void setSecond(Node node) {
            this.second = node;
            this.children[1] = node;
        }

        public void setThird(Node node) {
            this.third = node;
            this.children[2] = node;
        }

        public void setFourth(Node node) {
            this.fourth = node;
            this.children[3] = node;
        }

        /**
         * string representation of a node. for debugging purposes.
         *
         * @return node as string with children
         */
        public String nodeToString() {
            return "[" + value + "]";
        }
    }
}


