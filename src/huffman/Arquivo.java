/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author renanbertoldo
 */
public class Arquivo {

    private final JFileChooser fc = new JFileChooser();
    private JPanel contentPane;

    public void salvar(int[] frequencia, Dicionario dicionario, char[] entrada) {
        StringBuilder dic = new StringBuilder();
        for (int i = 0; i < frequencia.length; i++) {
            if (frequencia[i] != 0) {
                dic.append((char) i).append("=").append(frequencia[i]).append(";");
            }
        }

        dic = dic.append("#");
        for (int i = 0; i < entrada.length; i++) {
            dic.append(dicionario.getDicionario().get("" + entrada[i]));
        }

        fc.showSaveDialog(contentPane);
        File arquivo = new File(fc.getSelectedFile().toPath().toString());
        try (FileWriter fw = new FileWriter(arquivo)) {
            fw.write(dic.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String carregar(Compactador compactador) throws FileNotFoundException, IOException {
        boolean dicio = false;
        boolean bin = false;

        char chaveT = 0;

        int[] frequencia = new int[256];

        String valor = "";
        String buscaBin = "";
        StringBuilder retornoSB = new StringBuilder();
        String verificacao;

        fc.showOpenDialog(contentPane);
        File arquivo = new File(fc.getSelectedFile().toPath().toString());
        FileReader fr = new FileReader(arquivo);

        int c = fr.read();
        while (c != -1) {
            if (!bin && '#' != c) {
                if (!dicio) {
                    if ('=' != c) {
                        chaveT = (char) c;
                    } else {
                        dicio = true;
                    }
                } else {
                    if (';' != c) {
                        valor = valor + (char) c;
                    } else {
                        dicio = false;
                        frequencia[(int) chaveT] = Integer.parseInt(valor);
                        chaveT = 0;
                        valor = "";
                    }
                }

            } else {
                if (!bin) {
                    compactador.criarArvore(frequencia);
                    bin = true;
                    c = fr.read();
                    continue;
                }

                buscaBin += (char) c;

                verificacao = compactador.buscaValor(buscaBin);
                if (verificacao != null) {
                    retornoSB.append(compactador.buscaValor(buscaBin));
                    buscaBin = "";
                }
            }
            c = fr.read();
        }
        return retornoSB.toString();
    }

    public String abrirTexto() throws FileNotFoundException, IOException {
        String retorno = "";
        fc.showOpenDialog(contentPane);
        File arquivo = new File(fc.getSelectedFile().toPath().toString());
        FileReader fr = new FileReader(arquivo);
        int c = fr.read();
        while (c != -1) {
            retorno += (char) c;
            c = fr.read();
        }
        return retorno;
    }
}
