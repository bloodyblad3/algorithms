package tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {
    private Node root;

    public boolean add(T value) {
        if (root == null) {
            root = new Node();
            root.value = value;
            root.color = Color.BLACK;
            return true;
        }
        return addNode(root, value);
    }

    private boolean addNode(Node node, T value) {
        if (node.value.compareTo(value) == 0) {
            return false;
        }
        if (node.value.compareTo(value) > 0) {
            if (node.left != null) {
                boolean result = addNode(node.left, value);
                node.left = rebalance(node.left);
                return result;
            } else {
                node.left = new Node();
                node.left.color = Color.RED;
                node.left.value = value;
                return true;
            }
        } else {
            if (node.right != null) {
                boolean result = addNode(node.right, value);
                node.right = rebalance(node.right);
                return result;
            } else {
                node.right = new Node();
                node.right.color = Color.RED;
                node.right.value = value;
                return true;
            }
        }
    }

    public boolean contains(T value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                return true;
            }
            if (currentNode.value.compareTo(value) > 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return false;
    }

    public boolean remove(T value) {
        if (!contains(value)) {
            return false;
        }
        Node deleteNode = root;
        Node prevNode = root;
        while (deleteNode != null) {
            if (deleteNode.value.compareTo(value) == 0) {
                Node currentNode = deleteNode.right;
                if (currentNode == null) {
                    if (deleteNode == root) {
                        root = root.left;
                        if (root != null) {
                            root.color = Color.BLACK;
                        }
                        return true;
                    }
                    if (deleteNode.left == null) {
                        if (prevNode.left == deleteNode) {
                            prevNode.left = null;
                        } else {
                            prevNode.right = null;
                        }
                        return true;
                    }
                    if (prevNode.left == deleteNode) {
                        prevNode.left = deleteNode.left;
                    } else {
                        prevNode.right = deleteNode.left;
                    }
                    return true;
                }
                while (currentNode.left != null) {
                    currentNode = currentNode.left;
                }
                deleteNode.value = currentNode.value;
                currentNode = null;
                return true;
            }
            if (prevNode != deleteNode) {
                if (prevNode.value.compareTo(value) > 0) {
                    prevNode = prevNode.left;
                } else {
                    prevNode = prevNode.right;
                }
            }
            if (deleteNode.value.compareTo(value) > 0) {
                deleteNode = deleteNode.left;
            } else {
                deleteNode = deleteNode.right;
            }
        }
        return false;
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.right != null && result.right.color == Color.RED
                    && (result.left == null || result.left.color == Color.BLACK)) {
                needRebalance = true;
                result = rotateLeft(result);
            }
            if (!needRebalance && result.left != null && result.left.color == Color.RED
                    && result.left.left != null && result.left.left.color == Color.RED) {
                needRebalance = true;
                result = rotateRight(result);
            }
            if (!needRebalance && result.left != null && result.left.color == Color.RED
                    && result.right != null && result.right.color == Color.RED) {
                flipColors(result);
            }
        } while (needRebalance);
        return result;
    }

    private Node rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = Color.RED;
        return temp;
    }

    private Node rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = Color.RED;
        return temp;
    }

    private void flipColors(Node node) {
        node.color = Color.RED;
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }

    public void print() {
        List<List<String>> lines = new ArrayList<>();
        List<Node> level = new ArrayList<>();
        List<Node> next = new ArrayList<>();

        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;
            for (Node n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.value.toString();
                    line.add(aa);
                    if (aa.length() > widest) {
                        widest = aa.length();
                    }

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null) {
                        nn++;
                    }
                    if (n.right != null) {
                        nn++;
                    }
                }
            }

            if (widest % 2 == 1) {
                widest++;
            }

            lines.add(line);

            List<Node> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) {
                                c = '└';
                            }
                        }
                    }
                    System.out.print(c);

                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(' ');
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? '┌' : '┐');
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null) {
                    f = "";
                }
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                for (int k = 0; k < gap1; k++) {
                    System.out.print(' ');
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(' ');
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }

    private enum Color {
        RED,
        BLACK
    }

    private class Node {
        T value;
        Color color;
        Node left;
        Node right;
    }
}