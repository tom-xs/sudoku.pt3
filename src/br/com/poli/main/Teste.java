package br.com.poli.main;

import br.com.poli.classes.Cell;
import br.com.poli.classes.CellValueException;
import br.com.poli.classes.Player;
import br.com.poli.classes.Sudoku;
import br.com.poli.classes.SudokuEasy;
import br.com.poli.classes.SudokuNormal;
import br.com.poli.classes.SudokuHard;
import br.com.poli.classes.difficulty;

public class Teste {

	
	
	Teste(){	
	
	}
	
	
	public void testando() {

		
		 //primeiro teste

       SudokuEasy Game = new SudokuEasy();
       Player joao = new Player("joaozinho");

       Game.startGame(joao);

       //espera 1 segundo

       try {
           Thread.sleep(1000);
       } catch (InterruptedException ex) {
       }

       System.out.println("o nome do jogador eh :" + Game.getPlayer().getName());

       System.out.println("--------------------------");
       for (int i = 0; i < 9; i++) {
           for (int j = 0; j < 9; j++) {
               System.out.print(Game.getCell(i,j).getValue()+ " ");
           }
           System.out.println();
       }

       System.out.println();

       Game.solve();
       for (int i = 0; i < 9; i++) {
           for (int j = 0; j < 9; j++) {
               System.out.print(Game.getCell(i, j).getValue() + " ");
           }
           System.out.println();
       }
       System.out.println();

       Game.endGame();


       System.out.println("tempo decorrido na partida : " + Game.returnTotalTime() + " s");

       System.out.println("---------------------------------------------------");

       //comeca o segundo teste

       System.out.println("              segundo teste ");
       System.out.println("---------------------------------------------------");


       SudokuHard Game2 = new SudokuHard();
       Player zeca = new Player("zequinha");

       Game2.startGame(zeca);

       //espera 3 segundos
       try {
           Thread.sleep(3000);
       } catch (InterruptedException ex) {
       }

       System.out.println("o nome do jogador eh :" + Game2.getPlayer().getName());

       System.out.println("--------------------------");
       for (int i = 0; i < 9; i++) {
           for (int j = 0; j < 9; j++) {
               System.out.print(Game2.getCell(i, j).getValue() + " ");
           }
           System.out.println();
       }

       System.out.println();

       Game2.solve();
       for (int i = 0; i < 9; i++) {
           for (int j = 0; j < 9; j++) {
               System.out.print(Game2.getCell(i, j).getValue() + " ");
           }
           System.out.println();
       }
       System.out.println();

       Game2.endGame();


       System.out.println("tempo decorrido na partida : " + Game2.returnTotalTime() + " s");

       System.out.println("--------------------------");
	
				
	
	
	}
	
}
