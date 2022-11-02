/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dcc025.resta1;

/**
 *
 * @author thiago
 */
public class Tabuleiro {
    
    String tabuleiroJogo[][] = new String[8][8];
    String val;
    
    public void inicializar() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(i == 0 && j != 0) {
                    val = "" + ((-1) + j);
                    tabuleiroJogo[i][j] = val;
                }
                else if(j == 0 && i != 0) {
                    val = "" + ((-1) + i);
                    tabuleiroJogo[i][j] = val;
                }
                else if(i >= 3 && i < 6) {
                    tabuleiroJogo[i][j] = "1";
                }
                else if (j >= 3 && j < 6) {
                    tabuleiroJogo[i][j] = "1";      
                }
                else {
                    tabuleiroJogo[i][j] = " ";
                }
            }
        }
        
        tabuleiroJogo[4][4] = "0";
        
        desenha(tabuleiroJogo);
    }
    
    public static void desenha(String jogo[][]) {
        System.out.println("\n************************************************************************");
        System.out.println("                             //RESTA 1//");
        System.out.println("************************************************************************");
        
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                    System.out.print(jogo[i][j] + "    ");                    
            }
            System.out.println("\n");
        }
    }
}
