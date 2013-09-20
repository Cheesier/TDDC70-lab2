import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: ostenip
 * Date: 9/19/13
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class IteratorTest {
    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(100, "");
        tree.insert(20, "");
        tree.insert(10, "");
        tree.insert(30, "");
        tree.insert(25, "");
        tree.insert(26, "");
        tree.insert(5, "");

        Iterator<Node> itr = tree.levelorder();

        while (itr.hasNext()) {
            System.out.println(itr.next().key);
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
