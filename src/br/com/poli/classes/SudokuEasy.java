package br.com.poli.classes;

public class SudokuEasy extends Sudoku {
   

	public SudokuEasy() {
	super ();
	}

	public  void initializeSudoku() {

		 setRandom();
		 setHoles(difficulty.Easy);
		 
	 }


}
