package br.com.poli.classes;

public class SudokuHard extends Sudoku{


	public SudokuHard() {
		super ();
	
	}
	public  void initializeSudoku() {

		setRandom();
		setHoles(difficulty.Hard);
		 
	 }
}
