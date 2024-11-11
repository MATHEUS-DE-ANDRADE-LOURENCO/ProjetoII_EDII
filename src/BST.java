public class BST {

    Node raiz;

	public BST() {
		this.raiz = null;
	}

	public BST(Node raiz) {
		this.raiz = null;
	}

	public Node search(String data) {
		return search(data, false);
	}

	public Node searchIgnoreCase(String data) {
		return search(data, true);
	}

	private Node search(String data, boolean ignoreCase) {
		return search(raiz, data, ignoreCase);
	}
	
	private Node search(Node node, String data, boolean ignoreCase) {
		if (node == null) {
			return null;
		}
		
		int diff = diffCompare(data, node.getData(), ignoreCase);

		if (diff < 0) {
			return search(node.getEsq(), data, ignoreCase);
		} else if (diff > 0) {
			return search(node.getDir(), data, ignoreCase);
		} else {
			return node;
		}
	}
	
	public void insert(String data) {
		insert(data, false);
	}
	
	public void insertIgnoreCase(String data) {
		insert(data, true);
	}
	
	private void insert(String data, boolean ignoreCase) {
		raiz = insert(raiz, null, data, ignoreCase); 
	}
	
	private Node insert(Node node, Node pai, String data, boolean ignoreCase) {
		if (node == null) {
			return new Node(data, pai);
		}

		int diff = diffCompare(data, node.getData(), ignoreCase);
		
		if (diff < 0) {
			node.setEsq(pai);
            insert(node.getEsq(), node, data, ignoreCase);
		} else if (diff > 0) {
			node.setDir(insert(node.getDir(), node, data, ignoreCase));
		} else {
			// Nessa implementação, não é permitida a inserção de duplicatas na BST.
			// Portanto, não fazemos nada.
			throw new RuntimeException("Essa BST não pode ter duplicatas!");
		}
		
		return node;
	}
	
	public void remove(String data) {
		remove(data, false);
	}
	
	public void removeIgnoreCase(String data) {
		remove(data, true);
	}
	
	private void remove(String data, boolean ignoreCase) {
		raiz = remove(raiz, data, ignoreCase);
	}
	
	private Node remove(Node node, String data, boolean ignoreCase) {
		if (node == null) {
			//return null;
			throw new RuntimeException("Nó com chave " + data + " não existe na BST!");
		}
		
		int diff = diffCompare(data, node.getData(), ignoreCase);
				
		if (diff < 0) {
			node.setEsq(node.pai);
            remove(node.getEsq(), data, ignoreCase);
		} else if (diff > 0) {
			node.setDir(remove(node.getDir(), data, ignoreCase));
		} else {
			node = removeNode(node, ignoreCase);
		}
		
		return node;		
	}
	
	private Node removeNode(Node node, boolean ignoreCase) {
		if (node.ehFolha()) {
			return null;
		}
		
		if (!node.temFilhoEsquerda()) {
			return node.getDir();
		} else if (!node.temFilhoDireita()) {
			return node.getEsq();
		} else {
			Node predecessor = predecessor(node.getData(), ignoreCase);
			node.setData(predecessor.getData());
			node.setEsq(node.pai);
            remove(node.getEsq(), predecessor.getData(), ignoreCase);
		}
		
		return node;		
	}
	
	public Node findMin() {
		return findMin(raiz);
	}
	
	private Node findMin(Node node) {
		if (node == null) {
			return null;
		}

		while (node.temFilhoEsquerda()) {
			node = node.getEsq();
		}
		return node;
	}
	
	public Node findMax() {
		return findMax(raiz);
	}
	
	private Node findMax(Node node) {
		if (node == null) {
			return null;
		}

		while (node.temFilhoDireita()) {
			node = node.getDir();
		}
		return node;
	}
	
	public Node findPredecessor(String data) {
		return predecessor(data, false);
	}
	
	public Node findPredecessorIgnoreCase(String data) {
		return predecessor(data, true);
	}

	private Node predecessor(String data, boolean ignoreCase) {
		Node node = search(data, ignoreCase);
		return predecessor(node);
	}
	
	private Node predecessor(Node node) {
		if (node == null) {
			return null;
		}
		
		if (node.temFilhoEsquerda()) {
			return findMax(node.getEsq());
		} else {
			Node current = node;
			Node pai = node.getPai();

			while (pai != null && current == pai.getEsq()) {
				current = pai;
				pai = current.getPai();
			}
			
			return pai;
		}
	}
	
	public Node findSuccessor(String data) {
		return successor(data, false);
	}
	
	public Node findSuccessorIgnoreCase(String data) {
		return successor(data, true);
	}

	private Node successor(String data, boolean ignoreCase) {
		Node node = search(data, ignoreCase);
		return successor(node);		
	}
	
	private Node successor(Node node) {
		if (node == null) {
			return null;
		}

		if (node.temFilhoDireita()) {
			return findMin(node.getDir());
		} else {
			Node current = node;
			Node pai = node.getPai();

			while (pai != null && current == pai.getDir()) {
				current = pai;
				pai = current.getPai();
			}
			
			return pai;
		}
	}
	
	public void clear() {
		raiz = clear(raiz);
	}
	
	private Node clear(Node node) {
		if (node == null) {
			return null;
		}

		// Pós-ordem = percurso LRN.
		node.setEsq(node.pai);
        clear(node.getEsq());
		node.setDir(clear(node.getDir()));
		node.setPai(null);

		return null;
	}

	private int diffCompare(String s1, String s2, boolean ignoreCase) {
		return ignoreCase ? s1.compareToIgnoreCase(s2) : s1.compareTo(s2);
	}
	
}