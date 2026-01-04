import java.util.*;

class Solution {
    class Node {
        private int value;
        private int x;
        private int y;
        private Node left;
        private Node right;
        
        Node(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
            this.left = null;
            this.right = null;
        }
        
        Node getLeft() { return left; }
        Node getRight() { return right; }
        int getValue() { return value; }
        int getX() { return x; }
        int getY() { return y; }
        
        void setLeft(Node node) {
            this.left = node;
        }
        void setRight(Node node) {
            this.right = node;
        }
    }
    
    class AvlTree {
        private Node root;
        private int N;
        private int index;
        
        AvlTree() {
            this.root = null;
            N = 0;
            index = 0;
        }
        
        int size() { return N; }
        boolean isEmpty() { return size() == 0; }
        
        void add(int value, int x, int y) {
            Node node = new Node(value, x, y);
            if(isEmpty()) {
                root = node;
                ++N;
                return;
            }
            else {
                Node curr = root;
                Node parent = null;
                while(curr != null) {
                    parent = curr;
                    if(x < curr.getX()) curr = curr.getLeft();
                    else curr = curr.getRight();
                }
                curr = node;
                if(x < parent.getX()) parent.setLeft(node);
                else parent.setRight(node);
                ++N;
            }
        }
        
        int[] preorderTraversal() {
            index = 0;
            int[] arr = new int[N];
            preorderTraversal(root, arr);
            return arr;
        }
        private void preorderTraversal(Node node, int[] arr) {
            if(node == null) return;
            arr[index++] = node.getValue();
            preorderTraversal(node.getLeft(), arr);
            preorderTraversal(node.getRight(), arr);
        }
        
        int[] postorderTraversal() {
            index = 0;
            int[] arr = new int[N];
            postorderTraversal(root, arr);
            return arr;
        }
        private void postorderTraversal(Node node, int[] arr) {
            if(node == null) return;
            postorderTraversal(node.getLeft(), arr);
            postorderTraversal(node.getRight(), arr);
            arr[index++] = node.getValue();
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nodeinfo.length; ++i) {
            int num = nodeinfo[i][0];
            map.put(num, i + 1);
        }
        Arrays.sort(nodeinfo, (a,b) -> {
            int ax = a[0];
            int bx = b[0];
            int ay = a[1];
            int by = b[1];
            if(ay != by) return by - ay;
            else return ax - bx;
        });
        AvlTree avltree = new AvlTree();
        for(int i = 0; i < nodeinfo.length; ++i) {
            avltree.add(map.get(nodeinfo[i][0]), nodeinfo[i][0], nodeinfo[i][1]);
        }
        int[][] answer = new int [2][nodeinfo.length];
        answer[0] = avltree.preorderTraversal();
        answer[1] = avltree.postorderTraversal();
        return answer;
    }
}