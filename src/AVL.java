public class AVL extends BST {

	public AVL() {
		super();
	}

	public AVL(Node root) {
		super(root);
	}

	private void updatepaiChild(Node pai, final Node child, Node newChild) {
		if (pai != null) {
			if (pai.getEsq() == child) {
				pai.setEsq(newChild);
			} else {
				pai.setDir(newChild);
			}
		} else {
			raiz = newChild;
			newChild.setPai(null);
		}
	}
	
	// Rotação LL.
	private Node rotateEsq(Node node) {
		if (node == null) {
			return null;
		}
		
		// O nó atual deve ter um filho direito, que será a nova raiz desta subárvore.
		Node newRoot = node.getDir();
		if (newRoot == null) {
			return null;
		}
		
		// Troca as conexões do nó pai (newRoot vira filho de pai, no lugar de node).
		Node pai = node.getPai();
		updatepaiChild(pai, node, newRoot);
		
		// newRoot é a nova raiz desta subárvore, então seu filho esquerdo se torna o
		// filho direito de node (que deixa de ser raiz desta subárvore).
		Node Esq = newRoot.getEsq();
		node.setDir(Esq);

		// node agora vira filho esquerdo de newRoot.
		newRoot.setEsq(node);
		
		return newRoot;
	}
	
	// Rotação RR.
	private Node rotateDir(Node node) {
		if (node == null) {
			return null;
		}
		
		// O nó atual deve ter um filho esquerdo, que será a nova raiz desta subárvore.
		Node newRoot = node.getEsq();
		if (newRoot == null) {
			return null;
		}
		
		// Troca as conexões do nó pai (newRoot vira filho de pai, no lugar de node).
		Node pai = node.getPai();
		updatepaiChild(pai, node, newRoot);
		
		// newRoot é a nova raiz desta subárvore, então seu filho direito se torna o
		// filho esquerdo de node (que deixa de ser raiz desta subárvore).
		Node Dir = newRoot.getDir();
		node.setEsq(Dir);
		
		// node agora vira filho direito de newRoot.
		newRoot.setDir(node);
		
		return newRoot;
	}
	
	// Rotação LR.
	private Node rotateEsqDir(Node node) {
		node.setEsq(rotateEsq(node.getEsq()));
		return rotateDir(node);
	}
	
	// Rotação RL.
	private Node rotateDirEsq(Node node) {
		node.setDir(rotateDir(node.getDir()));
		return rotateEsq(node);
	}
	

}