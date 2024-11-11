public class Node {
    protected String data;
	protected Node pai;
	protected Node esq;
	protected Node dir;

	public Node() {
		this("", null);
	}

	public Node(String data) {
		this(data, null);
	}

	public Node(String data, Node pai) {
		this.data = data;
		this.pai = pai;
		this.esq = null;
		this.dir = null;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Node getPai() {
		return pai;
	}

	public void setPai(Node pai) {
		this.pai = pai;
	}

	public Node getEsq() {
		return esq;
	}

	public void setEsq(Node Esq) {
		this.esq = Esq;
		
		if (this.esq != null) {
			this.esq.setPai(this);
		}
	}

	public Node getDir() {
		return dir;
	}

	public void setDir(Node dir) {
		this.dir = dir;
		
		if (this.dir != null) {
			this.dir.setPai(this);
		}
	}

	public boolean temFilhoEsquerda() {
		return esq != null;
	}

	public boolean temFilhoDireita() {
		return dir != null;
	}

	public boolean ehRaiz() {
		return pai == null;
	}

	public boolean ehFolha() {
		return esq == null && dir == null;
	}

	public int getGrau() {
		int grau = 0;
		
		if (temFilhoEsquerda()) {
			++grau;
		}
		
		if (temFilhoDireita()) {
			++grau;
		}
		
		return grau;
	}

	public int getLevel() {
		if (ehRaiz()) {
			return 0;
		}

		return pai.getLevel() + 1;
	}

	public int getAltura() {
		if (ehFolha()) {
			return 0;
		}

		int altura = 0;
		
		if (temFilhoEsquerda()) {
			altura = Math.max(altura, esq.getAltura());
		}
		
		if (temFilhoDireita()) {
			altura = Math.max(altura, dir.getAltura());
		}
		
		return altura + 1;
	}

	@Override
	public String toString() {
		return "data: " + data
				+ ", pai: " + (pai != null ? pai.getData() : "null")
				+ ", Esq: " + (esq != null ? esq.getData() : "null")
				+ ", Dir: " + (dir != null ? dir.getData() : "null")
				+ ", ehRaiz(): " + ehRaiz()
				+ ", ehFolha(): " + ehFolha()
				+ ", getGrau(): " + getGrau()
				+ ", getLevel(): " + getLevel()
				+ ", getAltura(): " + getAltura();
	}

}
