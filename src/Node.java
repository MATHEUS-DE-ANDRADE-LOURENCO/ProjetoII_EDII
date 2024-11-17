public class Node<T> {
    protected T data;
    protected Node<T> pai;
    protected Node<T> esq;
    protected Node<T> dir;
    protected int altura;

    public Node(T data) {
        this(data, null);
    }

    public Node(T data, Node<T> pai) {
        this.data = data;
        this.pai = pai;
        this.esq = null;
        this.dir = null;
        this.altura = 1;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getPai() {
        return pai;
    }

    public void setPai(Node<T> pai) {
        this.pai = pai;
    }

    public Node<T> getEsq() {
        return esq;
    }

    public void setEsq(Node<T> esq) {
        this.esq = esq;
        if (this.esq != null) {
            this.esq.setPai(this);
        }
    }

    public Node<T> getDir() {
        return dir;
    }

    public void setDir(Node<T> dir) {
        this.dir = dir;
        if (this.dir != null) {
            this.dir.setPai(this);
        }
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
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
        if (temFilhoEsquerda()) ++grau;
        if (temFilhoDireita()) ++grau;
        return grau;
    }

    public int getLevel() {
        if (ehRaiz()) return 0;
        return pai.getLevel() + 1;
    }

    public int calcularAltura() {
        if (ehFolha()) return 0;
        int altura = 0;
        if (temFilhoEsquerda()) altura = Math.max(altura, esq.calcularAltura());
        if (temFilhoDireita()) altura = Math.max(altura, dir.calcularAltura());
        return altura + 1;
    }

    public void atualizarAltura() {
        this.altura = calcularAltura() + 1;
    }
}
