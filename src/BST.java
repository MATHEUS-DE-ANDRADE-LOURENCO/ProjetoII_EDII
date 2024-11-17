public class BST<T extends Comparable<T>> {

    protected Node<T> raiz;

    public BST() {
        this.raiz = null;
    }

    public BST(Node<T> raiz) {
        this.raiz = raiz;
    }

    public Node<T> search(T data) {
        return search(raiz, data);
    }

    private Node<T> search(Node<T> node, T data) {
        if (node == null || data.compareTo(node.getData()) == 0) {
            return node;
        }
        if (data.compareTo(node.getData()) < 0) {
            return search(node.getEsq(), data);
        } else {
            return search(node.getDir(), data);
        }
    }

    public void insert(T data) {
        raiz = insert(raiz, null, data);
    }

    private Node<T> insert(Node<T> node, Node<T> pai, T data) {
        if (node == null) {
            return new Node<>(data, pai);
        }

        int cmp = data.compareTo(node.getData());
        if (cmp < 0) {
            node.setEsq(insert(node.getEsq(), node, data));
        } else if (cmp > 0) {
            node.setDir(insert(node.getDir(), node, data));
        } else {
            throw new RuntimeException("Essa BST não pode ter duplicatas!");
        }
        return node;
    }

    public void remove(T data) {
        raiz = remove(raiz, data);
    }

    private Node<T> remove(Node<T> node, T data) {
        if (node == null) {
            throw new RuntimeException("Nó com chave " + data + " não existe na BST!");
        }
        int cmp = data.compareTo(node.getData());
        if (cmp < 0) {
            node.setEsq(remove(node.getEsq(), data));
        } else if (cmp > 0) {
            node.setDir(remove(node.getDir(), data));
        } else {
            if (node.ehFolha()) {
                return null;
            } else if (!node.temFilhoEsquerda()) {
                return node.getDir();
            } else if (!node.temFilhoDireita()) {
                return node.getEsq();
            } else {
                Node<T> successor = findMin(node.getDir());
                node.setData(successor.getData());
                node.setDir(remove(node.getDir(), successor.getData()));
            }
        }
        return node;
    }

    public Node<T> findMin() {
        return findMin(raiz);
    }

    private Node<T> findMin(Node<T> node) {
        if (node == null) return null;
        while (node.temFilhoEsquerda()) {
            node = node.getEsq();
        }
        return node;
    }

    public Node<T> findMax() {
        return findMax(raiz);
    }

    private Node<T> findMax(Node<T> node) {
        if (node == null) return null;
        while (node.temFilhoDireita()) {
            node = node.getDir();
        }
        return node;
    }

    public void clear() {
        raiz = clear(raiz);
    }

    private Node<T> clear(Node<T> node) {
        if (node == null) return null;
        node.setEsq(clear(node.getEsq()));
        node.setDir(clear(node.getDir()));
        node.setPai(null);
        return null;
    }
}