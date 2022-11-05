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

        opcoes(tabuleiroJogo);//chamada para a primeira jogada
    }
//------------------------------------------------------------------------------

//Desenha matriz tabuleiro
    public static void desenha(String jogo[][]) {
        System.out.println("\n************************************************************************");
        System.out.println("                             //RESTA UM//");
        System.out.println("************************************************************************");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(jogo[i][j] + "    ");
            }
            System.out.println("\n");
        }
    }
//------------------------------------------------------------------------------

//Opções do jogo
    public void opcoes(String tabuleiroResta1[][]) {
        String opcaoJogo;
        boolean verificaFim = fim(tabuleiroResta1);
        desenha(tabuleiroResta1);

        if (verificaFim) {

            System.out.println("Digite para selecionar alguma das opções: ");
            System.out.println("[A] Para adicionar uma jogada.");
            System.out.println("[reiniciar] Para começar de novo.");
            System.out.println("[sair] Para encerrar o jogo.");

            opcaoJogo = entrada.nextLine();

            if (opcaoJogo.equalsIgnoreCase("A")) {
                definirJogada(tabuleiroResta1);
            } else if (opcaoJogo.equalsIgnoreCase("reiniciar")) {
                inicializar();
            } else if(opcaoJogo.equalsIgnoreCase("sair")){
               
            }
            else {
                System.out.println("OPÇÃO INVÁLIDA!");
                opcoes(tabuleiroResta1);
            }
        } else {
            int cont = 0;
            for (int i = 1; i < 8; i++) {
                for (int j = 1; j < 8; j++) {
                    if (tabuleiroResta1[i][j].contains("1")) {
                        cont++;
                    }
                }
            }

            if (cont == 1) {
                System.out.println("Parabéns vc ganhou!!");
            } else {
                System.out.println("Não há mais jogadas possíveis :(");
            }

            System.out.println("Deseja jogar novamente? (S/N)");
            String decidir = entrada.next();

            if (decidir.equalsIgnoreCase("s")) {
                inicializar();
            } else {

            }
        }
    }
//------------------------------------------------------------------------------

//Definir a jogada
    public void definirJogada(String tabuleiroResta1[][]) {

        String jogada;
        int lPeca, cPeca, lEBranco, cEBranco;
        boolean jogadaValida;

        System.out.println("Entre com o seguinte formato");
        System.out.println("([linha da peça], [coluna da peça], [linha do espaço em branco], [coluna do espaço em branco])");

        jogada = entrada.nextLine();
        String[] posicoes = jogada.split(", ");

        if (posicoes.length != 4) {
            System.out.println("valor inválido, TENTE NOVAMENTE.");
            definirJogada(tabuleiroResta1);
        } else {

            lPeca = Integer.parseInt(posicoes[0]);
            cPeca = Integer.parseInt(posicoes[1]);
            lEBranco = Integer.parseInt(posicoes[2]);
            cEBranco = Integer.parseInt(posicoes[3]);

            jogadaValida = verificarJogada(tabuleiroResta1, lPeca, cPeca, lEBranco, cEBranco);

            if (jogadaValida) {
                inserirJogada(tabuleiroResta1, lPeca, cPeca, lEBranco, cEBranco);
            } else {
                System.out.println("Jogada inválida! TENTE NOVAMENTE.");
                opcoes(tabuleiroResta1);
            }
        }

    }
//------------------------------------------------------------------------------
//Inserir jogada válida

    public void inserirJogada(String tabuleiroValido[][], int lPeca, int cPeca, int lEvazio, int cEvazio) {
        int x, y;

        x = encontrarXpecaRemover(lPeca, cPeca, lEvazio, cEvazio);
        y = encontrarYpecaRemover(lPeca, cPeca, lEvazio, cEvazio);

        tabuleiroValido[lPeca + 1][cPeca + 1] = "0";
        tabuleiroValido[lEvazio + 1][cEvazio + 1] = "1";
        tabuleiroValido[x + 1][y + 1] = "0";

        opcoes(tabuleiroValido);
    }
//------------------------------------------------------------------------------

    public static boolean verificarJogada(String tabuleiro[][], int linhaP, int colunaP, int linhaE, int colunaE) {
        boolean posicaoP = true, posicaoE = true, posicaoPaRemover = true;

        if ((linhaP < 0 || linhaP > 6) || (linhaE < 0 || linhaE > 6) || (colunaP < 0 || colunaP > 6) || (colunaE < 0 || colunaE > 6)) {
            return false;
        } else if (linhaP != linhaE && colunaP != colunaE) {
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

        if (tabuleiro[x + 1][y + 1].contains("1") && (x == linhaE + 1 || x == linhaE - 1 | y == colunaE + 1 || y == colunaE - 1)) {
            return true;
        } else {
            return false;
        }
    }
//------------------------------------------------------------------------------
//Verificar a posição da peça para remover
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
            if (colunaP > colunaE) {
                y = colunaP - 1;
            } else {
                y = colunaP + 1;
            }
        }
        return y;
    }
//------------------------------------------------------------------------------
//Verificar o fim do jogo
    public boolean fim(String tabuleiroResta1[][]) {

        boolean verificafimJogo = false;

        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                if (tabuleiroResta1[i][j].contains("1")) {
                    if (tabuleiroResta1[i - 1][j].contains("1") && tabuleiroResta1[i - 2][j].contains("0")) {
                        return true;
                    } else if (tabuleiroResta1[i + 1][j].contains("1") && tabuleiroResta1[i + 2][j].contains("0")) {
                        return true;
                    } else if (tabuleiroResta1[i][j - 1].contains("1") && tabuleiroResta1[i][j - 2].contains("0")) {
                        return true;
                    } else if (tabuleiroResta1[i][j + 1].contains("1") && tabuleiroResta1[i][j + 2].contains("0")) {
                        return true;
                    }
                }
            }
        }
        return verificafimJogo;
    }
//------------------------------------------------------------------------------
}
