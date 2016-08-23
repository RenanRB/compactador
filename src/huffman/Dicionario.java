/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author renanbertoldo
 */
public class Dicionario {

    private HashMap<String, String> dicionario = new HashMap<String, String>();

    public HashMap<String, String> getDicionario() {
        return dicionario;
    }

    public void setDicionario(HashMap<String, String> dicionario) {
        this.dicionario = dicionario;
    }

    public void inserirValor(String chave, String valor) {
        dicionario.put(chave, valor);
    }

    public void addValorDic(ArrayList al) {
        addValorDicR((NodoArvore) al.get(0), "");
    }

    public void addValorDicR(NodoArvore n, String seq) {
        if (n.getEsquerda() == null && n.getDireita() == null) {
            dicionario.put(n.getInfo(), seq);
            return;
        }

        addValorDicR(n.getEsquerda(), seq + "0");
        addValorDicR(n.getDireita(), seq + "1");

    }

}
