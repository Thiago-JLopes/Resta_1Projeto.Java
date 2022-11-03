/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcc025.resta1;

/**
 *
 * @author thiago
 */
import java.util.Scanner;

public class Tabuleiro {

    Scanner entrada = new Scanner(System.in);
    String tabuleiroJogo[][] = new String[8][8];
    String val;
//Preenche as posições iniciais das peças no tabuleiro

    public void inicializar() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 && j != 0) {
                    val = "" + ((-1) + j);
                    tabuleiroJogo[i][j] = val;
                } else if (j == 0 && i != 0) {
                    val = "" + ((-1) + i);
                    tabuleiroJogo[i][j] = val;
                } else if (i >= 3 && i < 6) {
                    tabuleiroJogo[i][j] = "1";
                } else if (j >= 3 && j < 6) {
                    tabuleiroJogo[i][j] = "1";
                } else {
                    tabuleiroJogo[i][j] = " ";
                }
            }
        }
        tabuleiroJogo[4][4] = "0";

        definirJogada(tabuleiroJogo);//chamada para a primeira jogada
    }
//------------------------------------------------------------------------------

//Desenha matriz tabuleiro
    public static void desenha(String jogo[][]) {
        System.out.println("\n************************************************************************");
        System.out.println("                             //RESTA 1//");
        System.out.println("************************************************************************");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(jogo[i][j] + "    ");
            }
            System.out.println("\n");
        }
    }
//------------------------------------------------------------------------------

    public void definirJogada(String tabuleiroResta1[][]) {
        boolean fimDojogo = false, validado = true;
        int linhaP, linhaE, colunaP, colunaE;

        // fimDojogo = verificarFimJogo(tabuleiroResta1);
        if (!fimDojogo) {
            do {
                desenha(tabuleiroResta1);
                System.out.println("\nInforme números de 0 a 6 para fazer uma jogada!");
                System.out.println("formato [linha da peca], [coluna da peca], [linha do espaco em branco], [coluna do espaco em branco]\n");
                do {
                    System.out.print("[linha da peca]--> ");
                    linhaP = entrada.nextInt();

                    System.out.print("[coluna da peca]--> ");
                    colunaP = entrada.nextInt();

                    System.out.print("[linha do espaco em branco]--> ");
                    linhaE = entrada.nextInt();

                    System.out.print("[coluna do espaco em branco]--> ");
                    colunaE = entrada.nextInt();

                    validado = verificarJogada(tabuleiroResta1, linhaP, colunaP, linhaE, colunaE);

                    if (validado) {
                        inserirJogada(tabuleiroJogo, linhaP, colunaP, linhaE, colunaE);
                        //fimDojogo = verificarFimJogo(tabuleiroJogo);
                    } else {
                        System.out.println("ERRO! posição e/ou movimento incorreto.");
                    }
                } while (validado);
            } while (!fimDojogo);
        } else {
            System.out.println("FIM DO JOGO");//teste
        }
    }

    public static void inserirJogada(String tabuleiroValido[][], int lPeca, int cPeca, int lEvazio, int cEvazio) {
        int x, y;

        x = encontrarXpecaRemover(lPeca, cPeca, lEvazio, cEvazio);
        y = encontrarYpecaRemover(lPeca, cPeca, lEvazio, cEvazio);

        tabuleiroValido[lPeca + 1][cPeca + 1] = "0";
        tabuleiroValido[lEvazio + 1][cEvazio + 1] = "1";
        tabuleiroValido[x + 1][y + 1] = "0";

        desenha(tabuleiroValido);
    }

    public static boolean verificarJogada(String tabuleiro[][], int linhaP, int colunaP, int linhaE, int colunaE) {
        boolean posicaoP = true, posicaoE = true, posicaoPaRemover = true;

        if ((linhaP < 0 || linhaP > 6) || (linhaE < 0 || linhaE > 6) || (colunaP < 0 || colunaP > 6) || (colunaE < 0 || colunaE > 6)) {
            return false;
        } else {
            posicaoP = validarPosicaoPeca(tabuleiro, linhaP, colunaP);
            posicaoE = validarPosicaoVazio(tabuleiro, linhaE, colunaE);
            posicaoPaRemover = validarPosicaoRemover(tabuleiro, linhaP, colunaP, linhaE, colunaE);
        }

        return ((posicaoP && posicaoE) && posicaoPaRemover);
    }
//Validar Posições--------------------------------------------------------------

    public static boolean validarPosicaoPeca(String tabuleiro[][], int linhaP, int colunaP) {

        if (tabuleiro[linhaP + 1][colunaP + 1].contains("1")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validarPosicaoVazio(String tabuleiro[][], int linhaE, int colunaE) {

        if (tabuleiro[linhaE + 1][colunaE + 1].contains("0")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validarPosicaoRemover(String tabuleiro[][], int linhaP, int colunaP, int linhaE, int colunaE) {
        int x, y;

        x = encontrarXpecaRemover(linhaP, colunaP, linhaE, colunaE);
        y = encontrarYpecaRemover(linhaP, colunaP, linhaE, colunaE);

        if (tabuleiro[x + 1][y + 1].contains("1")) {
            return true;
        } else {
            return false;
        }
    }
//------------------------------------------------------------------------------

    public static int encontrarXpecaRemover(int linhaP, int colunaP, int linhaE, int colunaE) {
        int x = linhaP;
        if (linhaP == linhaE) {
            return x;
        } else {
            if (linhaP > linhaE) {
                x = linhaP - 1;
            } else {
                x = linhaP + 1;
            }
            return x;
        }
    }

    public static int encontrarYpecaRemover(int linhaP, int colunaP, int linhaE, int colunaE) {
        int y = colunaP;
        if (colunaP == colunaE) {
            return y;
        } else {
            if (linhaP > linhaE) {
                y = colunaP - 1;
            } else {
                y = colunaP + 1;
            }
        }
        return y;
    }
}
