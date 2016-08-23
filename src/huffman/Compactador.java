/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author renanbertoldo
 */
public class Compactador {

    private ArrayList<NodoArvore> al;
    private Dicionario dicionario;
    private int[] frequencia;

    public ArrayList<NodoArvore> getAl() {
        return al;
    }

    public void setAl(ArrayList<NodoArvore> al) {
        this.al = al;
    }

    public Dicionario getDicionario() {
        return dicionario;
    }

    public void setDicionario(Dicionario dicionario) {
        this.dicionario = dicionario;
    }

    public int[] getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int[] frequencia) {
        this.frequencia = frequencia;
    }

    public void frequencia(char[] entrada) {
        frequencia = new int[256];
        for (int i = 0; i < entrada.length; i++) {
            frequencia[entrada[i]]++;
        }
    }

    public void criarArvore(int[] frequencia) {
        al = new ArrayList<NodoArvore>();
        dicionario = new Dicionario();
        NodoArvore esq;
        NodoArvore dir;
        int somaXY;
        String info;

        for (int i = 0; i < 246; i++) {
            if (frequencia[i] > 0) {
                al.add(new NodoArvore("" + (char) i, frequencia[i], null, null));
                dicionario.inserirValor("" + (char) i, "");
            }
        }

        Collections.sort(al);

        while (al.size() > 1) {
            esq = (NodoArvore) al.get(0);
            dir = (NodoArvore) al.get(1);
            somaXY = esq.getQtd() + dir.getQtd();
            info = esq.getInfo() + dir.getInfo();
            al.add(new NodoArvore(info, somaXY, esq, dir));
            al.remove(0);
            al.remove(0);
            Collections.sort(al);
        }

        dicionario.addValorDic(al);
    }

    public String buscaValor(String localizacao) {
        return buscaValorR(al.get(0), localizacao);
    }

    public String buscaValorR(NodoArvore n, String localizacao) {
        if (localizacao.length() == 1) {
            if (localizacao.charAt(0) == '0') {
                n = n.getEsquerda();
            } else {
                n = n.getDireita();
            }

            if (n.getEsquerda() == null && n.getDireita() == null) {
                return n.getInfo();
            } else {
                return null;
            }
        } else {

            if (localizacao.charAt(0) == '0') {
                return buscaValorR(n.getEsquerda(), localizacao.substring(1));
            } else {
                return buscaValorR(n.getDireita(), localizacao.substring(1));
            }
        }
    }

}
