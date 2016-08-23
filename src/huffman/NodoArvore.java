package huffman;

public class NodoArvore implements Comparable<NodoArvore>{
    private String info;
    private Integer qtd;
    private NodoArvore esquerda;
    private NodoArvore direita;

    public NodoArvore(String info, int qtd, NodoArvore esquerda, NodoArvore direita) {
        this.setInfo(info);
        this.setQtd(qtd);
        this.setEsquerda(esquerda);
        this.setDireita(direita);
    }

    public String getInfo() {
            return info;
    }

    public void setInfo(String info) {
            this.info = info;
    }

    public NodoArvore getEsquerda() {
            return esquerda;
    }

    public void setEsquerda(NodoArvore esquerda) {
            this.esquerda = esquerda;
    }

    public NodoArvore getDireita() {
            return direita;
    }

    public void setDireita(NodoArvore direita) {
            this.direita = direita;
    }
    
    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    @Override
    public int compareTo(NodoArvore o) {
        return this.qtd - o.qtd;
    }       
}