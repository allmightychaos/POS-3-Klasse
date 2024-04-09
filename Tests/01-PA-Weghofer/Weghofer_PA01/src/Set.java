public class Set {

    private Node root;

    public boolean contains(int number) {
        if (root != null) {
            return root.contains(number);
        } else {
            return false;
        }
    }

    public void insert(int number) {
        if (root != null) {
            root.insert(number);
        } else {
            root = new Node(number);
        }
    }

    @Override
    public String toString() {
        return root == null ? "" : root.toString();
    }

    Set getLimits() {
        if (root == null) {
            return new Set();
        }
        Set limitSet = new Set();
        limitSet.insert(root.getMinNode().getNumber());
        limitSet.insert(root.getMaxNode().getNumber());
        return limitSet;
    }

    int getNext(int n) {
        if (root == null) {
            return 0;
        }

        int next = root.getNext(n);
        return next == Integer.MAX_VALUE ? root.getMaxNode().getNumber() : next;
    }

    public static void main(String[] args) {
        Set s = new Set();
        s.insert(15);
        s.insert(2);
        s.insert(-7);
        s.insert(5);
        s.insert(11);
        s.insert(0);
        System.out.println(s);
        System.out.println("Limits");
        System.out.println(s.getLimits()); //"-7 15"
        System.out.println("Next");
        System.out.println(s.getNext(3)); //"5"
        System.out.println(s.getNext(100)); //"15"
    }
}