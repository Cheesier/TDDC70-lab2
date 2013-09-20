import java.util.Iterator;

public class LevelorderIterator implements Iterator<Node> {
    protected Queue<Node> q;

    public LevelorderIterator(Node tree) {
        q = new Queue<Node>();
        q.enqueue(tree);
    }

    public void remove() {
        // Leave empty
    }

    public Node next() {
        Node node = q.dequeue();

        if (node.left != null) q.enqueue(node.left);
        if (node.right != null) q.enqueue(node.right);

        return node;
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }
}