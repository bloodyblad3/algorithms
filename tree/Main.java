package tree;

public class Main {
    public static void main(String[] args) {

        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(1);
        tree.add(5);
        tree.add(3);

        tree.print();

    }
}
