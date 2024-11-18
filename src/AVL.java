import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Analisador.AnalisadorEstatistico;
import Filtragem.Filtravel;
import Filtragem.Filtro;

public class AVL<T extends Comparable<T> & Filtravel> extends BST<T> {
    public AVL() {
        super();
    }

    public AVL(Node<T> root) {
        super(root);
    }

    private void updateParentChild(Node<T> parent, final Node<T> child, Node<T> newChild) {
        if (parent != null) {
            if (parent.getEsq() == child) {
                parent.setEsq(newChild);
            } else {
                parent.setDir(newChild);
            }
        } else {
            raiz = newChild;
            if (newChild != null) {
                newChild.setPai(null);
            }
        }
    }

    // Rotação LL.
    private Node<T> rotateLeft(Node<T> node) {
        if (node == null) {
            return null;
        }

        Node<T> newRoot = node.getDir();
        if (newRoot == null) {
            return null;
        }

        Node<T> parent = node.getPai();
        updateParentChild(parent, node, newRoot);

        Node<T> leftChild = newRoot.getEsq();
        node.setDir(leftChild);

        newRoot.setEsq(node);

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    // Rotação RR.
    private Node<T> rotateRight(Node<T> node) {
        if (node == null) {
            return null;
        }

        Node<T> newRoot = node.getEsq();
        if (newRoot == null) {
            return null;
        }

        Node<T> parent = node.getPai();
        updateParentChild(parent, node, newRoot);

        Node<T> rightChild = newRoot.getDir();
        node.setEsq(rightChild);

        newRoot.setDir(node);

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    // Rotação LR.
    private Node<T> rotateLeftRight(Node<T> node) {
        node.setEsq(rotateLeft(node.getEsq()));
        return rotateRight(node);
    }

    // Rotação RL.
    private Node<T> rotateRightLeft(Node<T> node) {
        node.setDir(rotateRight(node.getDir()));
        return rotateLeft(node);
    }

    private void updateHeight(Node<T> node) {
        if (node != null) {
            node.setAltura(1 + Math.max(getHeight(node.getEsq()), getHeight(node.getDir())));
        }
    }

    @Override
    public int getHeight() {
      return getHeight(raiz);
    }

    private int getHeight(Node<T> node) {
        return node == null ? 0 : node.getAltura();
    }

    private int getBalance(Node<T> node) {
        return node == null ? 0 : getHeight(node.getEsq()) - getHeight(node.getDir());
    }

    @Override
    public void insert(T data) {
        raiz = insert(raiz, null, data);
    }

    private Node<T> insert(Node<T> node, Node<T> parent, T data) {
        if (node == null) {
            return new Node<>(data, parent);
        }

        int cmp = data.compareTo(node.getData());

        if (cmp < 0) {
            node.setEsq(insert(node.getEsq(), node, data));
        } else if (cmp > 0) {
            node.setDir(insert(node.getDir(), node, data));
        } else {
            throw new RuntimeException("Essa AVL não pode ter duplicatas!");
        }

        updateHeight(node);

        int balance = getBalance(node);

        // Casos de rotação
        if (balance > 1 && data.compareTo(node.getEsq().getData()) < 0) {
            return rotateRight(node);
        }
        if (balance < -1 && data.compareTo(node.getDir().getData()) > 0) {
            return rotateLeft(node);
        }
        if (balance > 1 && data.compareTo(node.getEsq().getData()) > 0) {
            return rotateLeftRight(node);
        }
        if (balance < -1 && data.compareTo(node.getDir().getData()) < 0) {
            return rotateRightLeft(node);
        }

        return node;
    }

    public List<Node<T>> filtrar(Filtro<T> filtro) {
      List<Node<T>> resultados = new ArrayList<>();
      filtrarRecursivo(raiz, filtro, resultados);
      return resultados;
    }

    private void filtrarRecursivo(Node<T> node, Filtro<T> filtro, List<Node<T>> resultados) {
      if (node == null)
        return;

      if (filtro.aceita(node.getData())) {
        resultados.add(node);
      }

      filtrarRecursivo(node.getEsq(), filtro, resultados);
      filtrarRecursivo(node.getDir(), filtro, resultados);
    }

    public <R> R analisar(Filtro<T> filtro, AnalisadorEstatistico<T, R> analisador) {
      List<Node<T>> dadosFiltrados = filtrar(filtro);
      List<T> dados = dadosFiltrados.stream().map(Node::getData).collect(Collectors.toList());
      return analisador.calcular(dados);
    }
}