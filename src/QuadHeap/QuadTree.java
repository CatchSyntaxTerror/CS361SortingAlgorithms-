package src.QuadHeap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class QuadTree {
    public class Node {
        private int value;
        Node first;
        Node second;
        Node third;
        Node fourth;

        public Node(int value) {
            this.value = value;
        }

        public HashSet<Node> getLeftChildren() {
            HashSet<Node> set = new HashSet<>();
            if (first != null) set.add(first);
            if (second != null) set.add(second);
            return set;
        }

        public HashSet<Node> getRightChildren() {
            HashSet<Node> set = new HashSet<>();
            if (third != null) set.add(third);
            if (fourth != null) set.add(fourth);
            return set;
        }
    }

    private static Node root;

    int[] exImp = new int[]{12, 10, 8, 6, 11, 9, 7, 4, 3, 5, 2, 1};

    /**
     * constructor for a whole array of data
     * @param arr Data set, right now only int arrays
     */
    public QuadTree(int[] arr) {
        fill(arr);
    }

    /**
     * constructor with one value to create the root
     * @param value root nodes value
     */
    public QuadTree(int value){
        root = new Node(value);
    }

    /**
     * adds a value to the tree
     * @param value data to be added
     */
    public void add(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            add(root, value);
        }
    }

    /**
     * recursively finds a leftmost null node to add
     * @param value int to be added
     */
    private void add(Node node, int value) {
        if (value < node.value) {
            if(node.first == null) {
                node.first = new Node(value);
            } else if(node.second == null) {
                node.second = new Node(value);
            } else {
                //Todo: alternate stepping first or second
            }
        } else if (value > node.value) {
            if(node.third == null) {
                node.third = new Node(value);
            } else if(node.fourth == null){
                node.fourth = new Node(value);
            } else {
                //Todo: alternate stepping third or fourth
            }
        }
    }


    /**
     * goes to the next left node
     * @param current the current node
     * @return the current nodes left child
     */
    private Node stepToFirst(Node current) {
        return current.first;
    }

    private Node stepToSecond(Node current){
        return current.second;
    }

    /**
     * goes to the next right node
     * @param current the current node
     * @return the current nodes right child
     */
    private Node stepToThird(Node current) {
        return current.third;
    }

    private Node stepToFourth(Node current){
        return current.fourth;
    }

    /**
     * adds all elements of the array to the tree
     * @param data the set of integers
     */
    private void fill(int[] data){
        for (int value: data) {
            add(root, value);
        }
    }

    public static void main(String[] args) {

    }

}
