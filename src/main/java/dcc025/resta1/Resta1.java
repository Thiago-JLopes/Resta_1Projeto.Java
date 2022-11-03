/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package dcc025.resta1;

/**
 *
 * @author thiago
 */
import java.util.Scanner;

public class Resta1 {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("----------------------------------------------------------------------------------\n                                    RESTA 1");
        System.out.println("----------------------------------------------------------------------------------");

        System.out.println("\n\nREGRAS DO JOGO:");
        System.out.println("""
                           1 - Para eliminar uma peca voce precisa pular sobre ela, como no jogo de Damas.
                           2 - O movimento deve ser sempre na horizontal ou na vertical, terminando em um
                           espaco vazio.
                           3 - A peça que foi "saltada" é retirada do tabuleiro. O jogo
                           termina quando não é mais possível fazer nenhum outro movimento.
                           4 - Você ganha o jogo quando resta apenas uma peca.""");

        System.out.println("\nCOMO JOGAR:");
        System.out.println("""
                           Para fazer uma jogada, entre com o seguinte formato [linha da peca], [coluna da peca], [linha do espaco em
                           branco], [coluna do espaco em branco].""");

        System.out.println("\n\n\nBORA JOGAR!!");
        
        Tabuleiro jogo = new Tabuleiro();
        jogo.inicializar();
        
        
    } 
}
