public class BST {
    /* Root of BST */
    private Node root;
    /* Number of nodes in BST */
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Is the BST empty?
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return root of BST
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Return number of key-value pairs in BST
     */
    public int size() {
        return size;
    }

    /**
     * Does there exist a key-value pair with given key?
     */
    public boolean contains(int key) {
        return find(key) != null;
    }

    /**
     * Return value associated with the given key, or null if no such key exists
     */
    public String find(int key) {
        return find(root, key);
    }

    /**
     * Return value associated with the given key, or null if no such key exists
     * in subtree rooted at x
     */
    private String find(Node x, int key) {
        if (x == null) {
            return null;
        }
        if (key < x.key) {
            return find(x.left, key);
        } else if (key > x.key) {
            return find(x.right, key);
        } else {
            return x.val;
        }
    }

    /**
     * Insert key-value pair into BST. If key already exists, update with new
     * value
     */
    public void insert(int key, String val) {
        if (val == null) {
            remove(key);
            return;
        }
        root = insert(root, key, val);
    }

    /**
     * Insert key-value pair into subtree rooted at x. If key already exists,
     * update with new value.
     */
    private Node insert(Node x, int key, String val) {
        if (x == null) {
            size++;
            return new Node(key, val);
        }
        if (key < x.key) {
            x.left = insert(x.left, key, val);
        } else if (key > x.key) {
            x.right = insert(x.right, key, val);
        } else {
            x.val = val;
        }

        return x;
    }

    /**
     * Remove node with given key from BST
     */
    public void remove(int key) {
        Node parent = findParentNode(key, root);

        if (parent == null) {
            if (root.key == key) {
                if (isLeaf(root)) { // has 0 children
                    root = null;
                }
                else if (hasOnlyOneChild(root)) { // has 1 child
                    root = getOnlyChild(root);
                }
                else { // has 2 children
                    Node newRootParent = getLeftmostNodeParent(root.right);

                    if (newRootParent != null) {
                        root.val = newRootParent.left.val;
                        root.key = newRootParent.left.key;
                        newRootParent.left = newRootParent.left.right; // handle all child elements right of LeftmostNode
                    }
                    else {
                        root.right.left = root.left;
                        root = root.right;
                    }
                }
            }
            else
                return; // not in tree or tree is empty
        }

        else {
            Node removeNode = (key > parent.key? parent.right: parent.left);

            if (isLeaf(removeNode)) { // has 0 children
                if (parent.right == removeNode)
                    parent.right = null;
                else
                    parent.left = null;

            }
            else if (hasOnlyOneChild(removeNode)) { // has 1 child
                if (parent.right == removeNode)
                    parent.right = getOnlyChild(removeNode);
                else
                    parent.left = getOnlyChild(removeNode);
            }
            else { // has 2 children
                if (parent.right == removeNode) {
                    Node newRootParent = getLeftmostNodeParent(removeNode.right);

                    if (newRootParent != null) {
                        System.out.println(1.1);
                        removeNode.val = newRootParent.left.val;
                        removeNode.key = newRootParent.left.key;
                        newRootParent.left = newRootParent.left.right; // handle all child elements right of LeftmostNode
                    }
                    else {
                        System.out.println(1.2);
                        removeNode.right.left = removeNode.left;
                        parent.right = removeNode.right;
                    }
                }
                else {
                    Node newRootParent = getLeftmostNodeParent(removeNode.right);

                    if (newRootParent != null) {
                        System.out.println(2.1);
                        removeNode.val = newRootParent.left.val;
                        removeNode.key = newRootParent.left.key;
                        newRootParent.left = newRootParent.left.right; // handle all child elements right of LeftmostNode
                    }
                    else {
                        System.out.println(2.2);
                        removeNode.right.left = removeNode.left;
                        parent.left = removeNode.right;
                    }
                }
            }

        }

    }

    public PreorderIterator preorder() {
        return new PreorderIterator(root);
    }

    public LevelorderIterator levelorder() {
        return new LevelorderIterator(root);
    }

    private boolean isLeaf(Node node) {
        return node.right == null && node.left == null;
    }

    /**
     *
     * @param node node to check
     * @return null if 0 or 2 nodes, else the only child node
     */
    private Node getOnlyChild(Node node) {
        if (node.left == null && node.right != null)
            return node.right;
        else if (node.left != null && node.right == null)
            return node.left;

        return null;
    }

    private boolean hasOnlyOneChild(Node node) {
        return getOnlyChild(node) != null;
    }

    private boolean hasTwoChildren(Node node) {
        return node.left != null && node.right != null;
    }

    private Node getLeftmostNodeParent(Node node) {
        if (node == null)
            return null;

        if (node.left != null)
            if (node.left.left == null)
                return node;
            else
                return getLeftmostNodeParent(node.left);
        else
            return null;
    }

    /**
     * Find Nodes parent
     */
    private Node findParentNode(int key, Node start) {
        if (start == null)
            return null;

        if (isLeaf(start))
            return null;

        if (key > start.key) {
            if (start.right != null) {
                if (key == start.right.key)
                    return start;
                else
                    return findParentNode(key, start.right);
            }
        }
        else {
            if (start.left != null) {
                if (key == start.left.key)
                    return start;
                else
                    return findParentNode(key, start.left);
            }
        }

        return null;
    }
}